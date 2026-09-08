package com.vakinfo.employee.service;

import com.vakinfo.employee.dto.EmployeeRequestDTO;
import com.vakinfo.employee.dto.EmployeeResponseDTO;
import com.vakinfo.employee.entity.Employee;
import com.vakinfo.employee.exception.EmployeeNotFoundException;
import com.vakinfo.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeResponseDTO> searchEmployees(String name) {

        List<EmployeeResponseDTO> employees = employeeRepository
                .findByFirstNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponse)
                .toList();

        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException(
                    "No employees found with name: " + name
            );
        }

        return employees;
    }

    @Override
    public List<EmployeeResponseDTO> getEmployeesByEmployeeCode(String employeeCode) {

        List<EmployeeResponseDTO> employees = employeeRepository
                .findByEmployeeCodeContainingIgnoreCase(employeeCode)
                .stream()
                .map(this::mapToResponse)
                .toList();

        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException(
                    "No employees found with employee code: " + employeeCode
            );
        }

        return employees;
    }

    @Override
    public List<EmployeeResponseDTO> getEmployeesByDesignation(String designation) {

        List<EmployeeResponseDTO> employees = employeeRepository
                .findByDesignationContainingIgnoreCase(designation)
                .stream()
                .map(this::mapToResponse)
                .toList();

        if (employees.isEmpty()) {
            throw new EmployeeNotFoundException(
                    "No employees found with designation: " + designation
            );
        }

        return employees;
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO request) {

        Employee employee = mapToEntity(request);

        Employee savedEmployee = employeeRepository.save(employee);

        return mapToResponse(savedEmployee);
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: " + id
                        ));

        return mapToResponse(employee);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(
            Long id,
            EmployeeRequestDTO request) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: " + id
                        ));

        employee.setEmployeeCode(request.getEmployeeCode());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setDepartment(request.getDepartment());
        employee.setDesignation(request.getDesignation());
        employee.setSalary(request.getSalary());
        employee.setJoiningDate(request.getJoiningDate());

        Employee updatedEmployee = employeeRepository.save(employee);

        return mapToResponse(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() ->
                        new EmployeeNotFoundException(
                                "Employee not found with id: " + id
                        ));

        employeeRepository.delete(employee);
    }

    private Employee mapToEntity(EmployeeRequestDTO request) {

        return Employee.builder()
                .employeeCode(request.getEmployeeCode())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .department(request.getDepartment())
                .designation(request.getDesignation())
                .salary(request.getSalary())
                .joiningDate(request.getJoiningDate())
                .build();
    }

    private EmployeeResponseDTO mapToResponse(Employee employee) {

        return EmployeeResponseDTO.builder()
                .id(employee.getId())
                .employeeCode(employee.getEmployeeCode())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phone(employee.getPhone())
                .department(employee.getDepartment())
                .designation(employee.getDesignation())
                .salary(employee.getSalary())
                .joiningDate(employee.getJoiningDate())
                .build();
    }
}