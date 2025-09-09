package com.example.EmployeeManagementSystem.exceptions;

public class ResourceNotFoundException extends ApiException{
    public ResourceNotFoundException(String message) {
        super(message, 404);
    }
}
