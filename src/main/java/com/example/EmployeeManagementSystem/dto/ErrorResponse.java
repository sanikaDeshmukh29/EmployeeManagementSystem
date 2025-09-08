package com.example.EmployeeManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    @Schema(example = "2025-09-08T12:30:15")
    private String timestamp;

    @Schema(example = "404")
    private int status;

    @Schema(example = "Not Found")
    private String error;

    @Schema(example = "Employee with ID 10 not found")
    private String message;

    @Schema(example = "/api/employees/10")
    private String path;
}
