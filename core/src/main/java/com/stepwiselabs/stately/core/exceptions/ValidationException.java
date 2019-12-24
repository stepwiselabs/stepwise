package com.stepwiselabs.stately.core.exceptions;

public class ValidationException extends AppException {

    private static final long serialVersionUID = -3042686053652247285L;
    private final Object subject;

    /**
     * {@inheritDoc AppException#AppException(String, Object...)}
     */
    public ValidationException(String format, Object... args) {
        super(format, args);
        this.subject = null;
    }

    /**
     * {@inheritDoc AppException#AppException(String, Object...)}
     */
    public ValidationException(Object subject, String format, Object... args) {
        super(format, args);
        this.subject = subject;
    }

    /**
     * {@inheritDoc AppException#AppException(Throwable, String, Object...) }
     */
    public ValidationException(Throwable cause, String format, Object... args) {
        super(cause, format, args);
        this.subject = null;
    }

    /**
     * {@inheritDoc AppException#AppException(Throwable, String, Object...) }
     */
    public ValidationException(Object subject, Throwable cause, String format, Object... args) {
        super(cause, format, args);
        this.subject = subject;
    }

    public Object getSubject(){
        return subject;
    }
}
