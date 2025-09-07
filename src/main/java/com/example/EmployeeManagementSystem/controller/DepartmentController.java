package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.entity.Department;
import com.example.EmployeeManagementSystem.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;


    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAll();
        return ResponseEntity.ok(departments);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getById(id);
        return ResponseEntity.ok(department);
    }


    @PostMapping
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) {
        Department saved = departmentService.create(department);
        return ResponseEntity.created(URI.create("/api/departments/" + saved.getId()))
                .body(saved);
    }
}
