package com.vakinfo.employee.repository;

import com.vakinfo.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByFirstNameContainingIgnoreCase(String firstName);

    List<Employee> findByDepartmentIgnoreCase(String department);

    List<Employee> findByDesignationContainingIgnoreCase(String designation);

    List<Employee> findByEmployeeCodeContainingIgnoreCase(String employeeCode);
}