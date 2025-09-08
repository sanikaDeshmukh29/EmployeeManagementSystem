package com.example.EmployeeManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Request payload for creating or updating an employee")
public class EmployeeRequest {

    @NotBlank
    @Size(max = 50)
    @Schema(description = "First name of the employee", example = "John")
    private String firstName;

    @NotBlank @Size(max = 50)
    @Schema(description = "Last name of the employee", example = "Doe")
    private String lastName;

    @NotBlank @Email
    @Schema(description = "Email address of the employee", example = "john.doe@example.com")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    @Schema(description = "Phone number of the employee", example = "9876543210")
    private String phone;

    @NotNull
    @Positive
    @Schema(description = "Salary of the employee", example = "55000.75")
    private BigDecimal salary;

    @Schema(description = "ID of the department to which employee belongs", example = "2")

    private Long departmentId;
}
