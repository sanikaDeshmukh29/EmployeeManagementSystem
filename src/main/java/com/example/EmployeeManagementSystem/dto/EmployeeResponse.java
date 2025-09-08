package com.example.EmployeeManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeResponse {

    @Schema(description = "Unique identifier of the employee", example = "1")
    private Long id;

    @Schema(description = "First name of the employee", example = "John")
    private String firstName;

    @Schema(description = "Last name of the employee", example = "Doe")
    private String lastName;

    @Schema(description = "Email address of the employee", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Phone number of the employee", example = "9876543210")
    private String phone;

    @Schema(description = "Salary of the employee", example = "55000.75")
    private BigDecimal salary;

    @Schema(description = "Name of the department the employee belongs to", example = "IT")
    private String departmentName;
}
