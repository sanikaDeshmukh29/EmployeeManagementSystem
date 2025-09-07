package com.example.EmployeeManagementSystem.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class EmployeeRequest {

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank @Size(max = 50)
    private String lastName;

    @NotBlank @Email
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    private String phone;

    @NotNull
    @Positive
    private BigDecimal salary;

    private Long departmentId;
}
