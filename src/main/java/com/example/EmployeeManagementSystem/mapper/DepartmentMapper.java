package com.example.EmployeeManagementSystem.mapper;

import com.example.EmployeeManagementSystem.entity.Department;

public class DepartmentMapper {


    public static String toName(Department dept) {
        return dept != null ? dept.getName() : null;
    }
}
