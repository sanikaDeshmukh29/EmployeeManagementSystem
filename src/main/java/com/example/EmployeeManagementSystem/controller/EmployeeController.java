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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for managing Employee entities.
 * Provides endpoints to create, read, update, and delete employees.
 */
@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employees", description = "Employee management APIs")
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Create a new employee.
     *
     * @param request EmployeeRequest containing employee details
     * @return Created EmployeeResponse
     */
    @Operation(summary = "Create employee", description = "Create a new employee")
    @ApiResponse(responseCode = "201", description = "Employee created successfully",
            content = @Content(schema = @Schema(implementation = EmployeeResponse.class)))
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeRequest request) {
        EmployeeResponse created = employeeService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Get all employees with optional department filter and pagination.
     *
     * @param department Optional department name to filter employees
     * @param pageable   Pageable object for pagination and sorting
     * @return Page of EmployeeResponse
     */
    @Operation(summary = "Get all employees", description = "Retrieve all employees with optional department filter and pagination")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved employees",
            content = @Content(schema = @Schema(implementation = EmployeeResponse.class)))
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<Page<EmployeeResponse>> getAllEmployees(
            @RequestParam(required = false) String departmentName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<EmployeeResponse> employees = employeeService.getAll(departmentName, pageable);
        return ResponseEntity.ok(employees);
    }
    /**
     * Get an employee by ID.
     *
     * @param id Employee ID
     * @return EmployeeResponse
     */
    @Operation(summary = "Get employee by ID", description = "Retrieve an employee by its ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved employee",
            content = @Content(schema = @Schema(implementation = EmployeeResponse.class)))
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        EmployeeResponse response = employeeService.getById(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Update an existing employee.
     *
     * @param id      Employee ID
     * @param request EmployeeRequest with updated details
     * @return Updated EmployeeResponse
     */
    @Operation(summary = "Update employee", description = "Update employee details")
    @ApiResponse(responseCode = "200", description = "Employee updated successfully",
            content = @Content(schema = @Schema(implementation = EmployeeResponse.class)))
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {
        EmployeeResponse updated = employeeService.updateEmployee(id, request);
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete an employee by ID.
     *
     * @param id Employee ID
     */
    @Operation(summary = "Delete employee", description = "Delete an employee by its ID")
    @ApiResponse(responseCode = "204", description = "Employee deleted successfully")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
