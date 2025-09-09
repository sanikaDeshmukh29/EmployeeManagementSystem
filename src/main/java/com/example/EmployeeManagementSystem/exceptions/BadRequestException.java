package com.example.EmployeeManagementSystem.exceptions;

public class BadRequestException extends ApiException{

    public BadRequestException(String message) {
        super(message, 400);
    }
}
