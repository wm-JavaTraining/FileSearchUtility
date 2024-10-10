package com.wavemaker.file.exception;

public class FileWriteException extends RuntimeException {
    private int statusCode;


    public FileWriteException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }


    public FileWriteException(String message, Throwable cause, int statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
