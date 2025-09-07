package com.example.EmployeeManagementSystem.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class EmployeeResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private BigDecimal salary;
    private String departmentName;
}
