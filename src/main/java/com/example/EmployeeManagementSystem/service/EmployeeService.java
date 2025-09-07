package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.dto.EmployeeRequest;
import com.example.EmployeeManagementSystem.dto.EmployeeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    EmployeeResponse create(EmployeeRequest request);

    Page<EmployeeResponse> getAll(String departmentName, Pageable pageable);

    EmployeeResponse getById(Long id);
}
