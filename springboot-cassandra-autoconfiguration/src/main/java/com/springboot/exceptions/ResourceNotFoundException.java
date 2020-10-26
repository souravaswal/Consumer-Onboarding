package com.springboot.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 4435789625483940471L;

    public ResourceNotFoundException(final String message) {
        super(message);
    }

    public ResourceNotFoundException(final String message, final Throwable ex) {
        super(message, ex);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }

}
