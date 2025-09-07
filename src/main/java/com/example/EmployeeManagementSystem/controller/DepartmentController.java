package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.entity.Department;
import com.example.EmployeeManagementSystem.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
@Tag(name = "Department Management", description = "APIs for managing departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "Get all departments", description = "Fetches a list of all departments")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of departments retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAll();
        return ResponseEntity.ok(departments);
    }

    @Operation(summary = "Get department by ID", description = "Fetches details of a department by its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Department found"),
            @ApiResponse(responseCode = "404", description = "Department not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = departmentService.getById(id);
        return ResponseEntity.ok(department);
    }

    @Operation(summary = "Create a new department", description = "Adds a new department into the system")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Department created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid department details")
    })
    @PostMapping
    public ResponseEntity<Department> createDepartment(@Valid @RequestBody Department department) {
        Department saved = departmentService.create(department);
        return ResponseEntity.created(URI.create("/api/departments/" + saved.getId()))
                .body(saved);
    }
}
