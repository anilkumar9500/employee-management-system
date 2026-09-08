package com.vakinfo.employee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee code is required")
    @Column(name = "employee_code", unique = true, nullable = false)
    private String employeeCode;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Please enter a valid email")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Column(nullable = false)
    private String phone;

    @NotBlank(message = "Department is required")
    @Column(nullable = false)
    private String department;

    @NotBlank(message = "Designation is required")
    @Column(nullable = false)
    private String designation;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than 0")
    @Column(nullable = false)
    private BigDecimal salary;

    @NotNull(message = "Joining date is required")
    @Column(name = "joining_date", nullable = false)
    private LocalDate joiningDate;
}