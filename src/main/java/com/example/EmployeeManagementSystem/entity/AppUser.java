package com.example.EmployeeManagementSystem.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
@Schema(description = "Entity representing an application user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @Column(unique = true, nullable = false)
    @Schema(description = "Username of the user", example = "john_doe", required = true)
    private String username;

    @Column(nullable = false)
    @Schema(description = "Password of the user", hidden = true)
    private String password; // hidden from Swagger for security reasons

    @Schema(description = "Role assigned to the user", example = "ROLE_ADMIN")
    private String role; // e.g. "ROLE_ADMIN" or "ROLE_USER"
}
