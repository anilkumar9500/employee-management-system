package com.vakinfo.employee.controller;

import com.vakinfo.employee.dto.EmployeeRequestDTO;
import com.vakinfo.employee.dto.EmployeeResponseDTO;
import com.vakinfo.employee.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/employeecode/{employeeCode}")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployeesByEmployeeCode(
            @PathVariable String employeeCode) {

        return ResponseEntity.ok(
                employeeService.getEmployeesByEmployeeCode(employeeCode)
        );
    }

    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @Valid @RequestBody EmployeeRequestDTO request) {

        EmployeeResponseDTO response =
                employeeService.createEmployee(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponseDTO>> getAllEmployees() {

        return ResponseEntity.ok(
                employeeService.getAllEmployees()
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeResponseDTO>> searchEmployees(
            @RequestParam String name) {

        return ResponseEntity.ok(
                employeeService.searchEmployees(name)
        );
    }

    @GetMapping("/designation/{designation}")
    public ResponseEntity<List<EmployeeResponseDTO>> getEmployeesByDesignation(
            @PathVariable String designation) {

        return ResponseEntity.ok(
                employeeService.getEmployeesByDesignation(designation)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                employeeService.getEmployeeById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequestDTO request) {

        return ResponseEntity.ok(
                employeeService.updateEmployee(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }
}