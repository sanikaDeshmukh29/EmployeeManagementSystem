package com.example.EmployeeManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * DTO for creating or updating a Department.
 */
@Data
@Schema(description = "Request payload for creating or updating a department")
public class DepartmentRequest {

    @Schema(
            description = "Name of the department",
            example = "Human Resources",
            required = true
    )
    private String name;

    @Schema(
            description = "Location of the department",
            example = "Mumbai Office",
            required = false
    )
    private String location;

    // Lombok @Data generates getters, setters, toString, etc.
}
