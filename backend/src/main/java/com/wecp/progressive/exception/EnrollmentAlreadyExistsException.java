
package com.wecp.progressive.exception;

public class EnrollmentAlreadyExistsException extends RuntimeException{
    public EnrollmentAlreadyExistsException(String msg){
        super(msg);
    }
}