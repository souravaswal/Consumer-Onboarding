package com.springboot.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 4435789625483940471L;

    public ResourceAlreadyExistsException(final String message) {
        super(message);
    }

    public ResourceAlreadyExistsException(final String message, final Throwable ex) {
        super(message, ex);
    }

    @Override
    public String toString() {
        return this.getMessage();
    }

}
