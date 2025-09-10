package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Page<Employee> findByDepartment_Name(String departmentName, Pageable pageable);

    List<Employee> findByDepartmentId(Long departmentId);
}
