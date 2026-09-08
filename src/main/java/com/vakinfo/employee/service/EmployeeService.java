package com.vakinfo.employee.service;

import com.vakinfo.employee.dto.EmployeeRequestDTO;
import com.vakinfo.employee.dto.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO request);

    List<EmployeeResponseDTO> getAllEmployees();

    EmployeeResponseDTO getEmployeeById(Long id);

    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO request);

    void deleteEmployee(Long id);

    List<EmployeeResponseDTO> searchEmployees(String name);

    List<EmployeeResponseDTO> getEmployeesByDesignation(String designation);

    List<EmployeeResponseDTO> getEmployeesByEmployeeCode(String employeeCode);
}