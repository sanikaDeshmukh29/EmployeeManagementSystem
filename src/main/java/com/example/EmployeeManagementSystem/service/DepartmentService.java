package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department create(Department department);

    List<Department> getAll();

    Department getById(Long id);
}
