package com.example.EmployeeManagementSystem.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "employees")
@Schema(description = "Represents an employee in the company")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the employee", example = "101")
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Schema(description = "First name of the employee", example = "John")
    private String firstName;

    @NotBlank
    @Size(max = 50)
    @Schema(description = "Last name of the employee", example = "Doe")
    private String lastName;

    @NotBlank
    @Email
    @Column(unique = true)
    @Schema(description = "Email address of the employee", example = "john.doe@example.com")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone must be 10 digits")
    @Schema(description = "Phone number of the employee ", example = "2345678912")
    private String phone;

    @NotNull
    @Positive
    @Schema(description = "Salary of the employee", example = "75000.50")
    private BigDecimal salary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonManagedReference
    @Schema(description = "Department to which the employee belongs")
    private Department department;



}
