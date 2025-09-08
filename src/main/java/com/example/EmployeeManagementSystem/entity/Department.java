package com.example.EmployeeManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "departments", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the department", example = "10")
    private Long id;

    @NotBlank
    @Schema(description = "Name of the department", example = "Human Resources")
    private String name;

    @Schema(description = "Location of the department", example = "New York")
    private String location;

    @OneToMany(mappedBy = "department")
    @JsonBackReference
    @Schema(description = "employees belongs to the department")
    private List<Employee> employees;
}
