package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.dto.DepartmentRequest;
import com.example.EmployeeManagementSystem.dto.DepartmentResponse;
import com.example.EmployeeManagementSystem.entity.Department;

import java.util.List;

public interface DepartmentService {

    // Create a new department and return its DTO
    DepartmentResponse create(DepartmentRequest request);

    // Get a list of all departments as DTOs
    List<DepartmentResponse> getAll();

    // Get a single department by ID as DTO
    DepartmentResponse getById(Long id);

    // Optionally, if you want to add update and delete
    DepartmentResponse update(Long id, DepartmentRequest request);

    void delete(Long id);  // Delete department
}
