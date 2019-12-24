package com.stepwiselabs.stately.core.exceptions;

/**
 * Exception that occurs when transforming an object to a persistent format.
 *
 */
public class SerializationException extends AppException {

    private static final long serialVersionUID = -2091627819848977662L;

    /**
     * {@inheritDoc AppException#AppException(String, Object...)}
     */
    public SerializationException(String format, Object... args) {
        super(format, args);
    }

    /**
     * {@inheritDoc AppException#AppException(Throwable, String, Object...) }
     */
    public SerializationException(Throwable cause, String format, Object... args) {
        super(cause, format, args);
    }

}
