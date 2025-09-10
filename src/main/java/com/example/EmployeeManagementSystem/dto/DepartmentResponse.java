package com.example.EmployeeManagementSystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * DTO for sending department details in API responses.
 */
@Data
@Schema(description = "Response payload containing department details")
public class DepartmentResponse {

    @Schema(
            description = "Unique identifier of the department",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Name of the department",
            example = "Human Resources"
    )
    private String name;

    @Schema(
            description = "Location of the department",
            example = "Mumbai Office"
    )
    private String location;

    @Schema(
            description = "List of employees belonging to this department",
            implementation = EmployeeResponse.class
    )
    private List<EmployeeResponse> employees; // optional

    // Lombok @Data generates getters, setters, toString, etc.
}
