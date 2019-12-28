package org.stepwiselabs.core.exceptions;

public class StateException extends AppException {

    private static final long serialVersionUID = -2091623619848977662L;

    /**
     * {@inheritDoc AppException#AppException(String, Object...)}
     */
    public StateException(String format, Object... args) {
        super(format, args);
    }

    /**
     * {@inheritDoc AppException#AppException(Throwable, String, Object...) }
     */
    public StateException(Throwable cause, String format, Object... args) {
        super(cause, format, args);
    }

}