package com.example.demo.exception;

public class ItemNotFoundException extends Exception {
    private static final long serialVersionUID = 6707545011486014659L;

	public ItemNotFoundException(String message) {
        super(message);
    }
}
