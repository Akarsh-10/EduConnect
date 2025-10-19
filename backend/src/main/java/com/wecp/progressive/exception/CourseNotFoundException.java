package com.wecp.progressive.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String msg)
    {
        super(msg);
    }
}