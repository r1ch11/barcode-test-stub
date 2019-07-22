package com.contentbeach.jna.input.keyboard;

/**
 * Throw when a window cannot be found for a given window title.
 */
public class NoSuchWindowException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new exception with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public NoSuchWindowException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified detail message.
     * @param message the detail message
     */
    public NoSuchWindowException(final String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified cause.
     * @param cause the cause
     */
    public NoSuchWindowException(final Throwable cause) {
        super(cause);
    }
}
