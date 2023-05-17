package com.example.map;

public class EmployeeAbsentException extends RuntimeException {
    public EmployeeAbsentException(String message) {
        super(message);
    }
}
