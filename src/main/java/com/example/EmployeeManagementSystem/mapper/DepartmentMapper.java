package com.example.EmployeeManagementSystem.mapper;

import com.example.EmployeeManagementSystem.entity.Department;

public class DepartmentMapper {

    // You can expand this later if you add DepartmentRequest/Response DTOs
    public static String toName(Department dept) {
        return dept != null ? dept.getName() : null;
    }
}
