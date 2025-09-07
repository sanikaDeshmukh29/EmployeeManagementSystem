package com.example.EmployeeManagementSystem.mapper;

import com.example.EmployeeManagementSystem.dto.EmployeeRequest;
import com.example.EmployeeManagementSystem.dto.EmployeeResponse;
import com.example.EmployeeManagementSystem.entity.Department;
import com.example.EmployeeManagementSystem.entity.Employee;

public class EmployeeMapper {

    public static EmployeeResponse toResponse(Employee emp) {
        if (emp == null) {
            return null;
        }
        EmployeeResponse response = new EmployeeResponse();
        response.setId(emp.getId());
        response.setFirstName(emp.getFirstName());
        response.setLastName(emp.getLastName());
        response.setEmail(emp.getEmail());
        response.setPhone(emp.getPhone());
        response.setSalary(emp.getSalary());
        response.setDepartmentName(emp.getDepartment() != null ? emp.getDepartment().getName() : null);
        return response;
    }

    // Convert Request DTO → Entity
    public static Employee toEntity(EmployeeRequest request, Department department) {
        if (request == null) {
            return null;
        }
        Employee emp = new Employee();
        emp.setFirstName(request.getFirstName());
        emp.setLastName(request.getLastName());
        emp.setEmail(request.getEmail());
        emp.setPhone(request.getPhone());
        emp.setSalary(request.getSalary());
        emp.setDepartment(department);
        return emp;
    }

    // Update Entity from Request DTO
    public static void updateEntity(Employee emp, EmployeeRequest request, Department department) {
        emp.setFirstName(request.getFirstName());
        emp.setLastName(request.getLastName());
        emp.setEmail(request.getEmail());
        emp.setPhone(request.getPhone());
        emp.setSalary(request.getSalary());
        emp.setDepartment(department);
    }
}
