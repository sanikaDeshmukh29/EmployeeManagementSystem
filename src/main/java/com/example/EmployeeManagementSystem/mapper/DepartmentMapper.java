package com.example.EmployeeManagementSystem.mapper;

import com.example.EmployeeManagementSystem.dto.DepartmentRequest;
import com.example.EmployeeManagementSystem.dto.DepartmentResponse;
import com.example.EmployeeManagementSystem.dto.EmployeeResponse;
import com.example.EmployeeManagementSystem.entity.Department;
import com.example.EmployeeManagementSystem.entity.Employee;

import java.util.stream.Collectors;

public class DepartmentMapper {

    // Request -> Entity
    public static Department toEntity(DepartmentRequest dto) {
        Department department = new Department();
        department.setName(dto.getName());
        department.setLocation(dto.getLocation());
        return department;
    }

    // Entity -> Response
    public static DepartmentResponse toResponse(Department department) {
        DepartmentResponse dto = new DepartmentResponse();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setLocation(department.getLocation());

        if (department.getEmployees() != null) {
            dto.setEmployees(
                    department.getEmployees().stream()
                            .map(DepartmentMapper::mapEmployeeToResponse)
                            .collect(Collectors.toList())
            );
        }

        return dto;
    }

    // Helper: Employee -> EmployeeResponse
    private static EmployeeResponse mapEmployeeToResponse(Employee emp) {
        EmployeeResponse empDto = new EmployeeResponse();
        empDto.setId(emp.getId());
        empDto.setFirstName(emp.getFirstName());
        empDto.setLastName(emp.getLastName());
        empDto.setEmail(emp.getEmail());
        empDto.setPhone(emp.getPhone());
        empDto.setSalary(emp.getSalary());
        empDto.setDepartmentName(emp.getDepartment() != null ? emp.getDepartment().getName() : null);

        return empDto;
    }

    // Existing utility
    public static String toName(Department dept) {
        return dept != null ? dept.getName() : null;
    }
}
