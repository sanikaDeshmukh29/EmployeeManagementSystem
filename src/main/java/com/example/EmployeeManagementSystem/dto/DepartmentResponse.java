package com.example.EmployeeManagementSystem.dto;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentResponse {
    private Long id;
    private String name;
    private String location;
    private List<EmployeeResponse> employees; // optional (for response)

    // Getters & Setters
}
