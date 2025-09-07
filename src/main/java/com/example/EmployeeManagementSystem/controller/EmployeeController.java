package com.example.EmployeeManagementSystem.controller;

import com.example.EmployeeManagementSystem.dto.EmployeeRequest;
import com.example.EmployeeManagementSystem.dto.EmployeeResponse;
import com.example.EmployeeManagementSystem.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employees", description = "Employee management APIs")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Create new employee")
    @ApiResponse(responseCode = "201", description = "Employee created successfully",
            content = @Content(schema = @Schema(implementation = EmployeeResponse.class)))
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        EmployeeResponse created = employeeService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Operation(summary = "Get all employees",
            description = "Returns paginated, sortable list of employees with optional department filter")
    @ApiResponse(responseCode = "200", description = "Employees fetched successfully")
    @GetMapping
    public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(
            @RequestParam(required = false) String department,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        Page<EmployeeResponse> employees = employeeService.getAll(department, pageable);
        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Get employee by ID")
    @ApiResponse(responseCode = "200", description = "Employee found",
            content = @Content(schema = @Schema(implementation = EmployeeResponse.class)))
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        EmployeeResponse response = employeeService.getById(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Update employee by ID")
    @ApiResponse(responseCode = "200", description = "Employee updated successfully")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {
        EmployeeResponse updated = employeeService.updateEmployee(id, request);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Delete employee by ID")
    @ApiResponse(responseCode = "204", description = "Employee deleted successfully")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

}
