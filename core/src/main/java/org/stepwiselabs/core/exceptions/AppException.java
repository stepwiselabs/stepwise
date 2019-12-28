package org.stepwiselabs.core.exceptions;


import java.util.IllegalFormatException;

/**
 * Application level exception.  Most other app level {@linkplain RuntimeException}s that
 * are explicitly thrown extend {@linkplain AppException}.  This allows for the ability
 * to easily roll up exception types.
 *
 * @author sterrasi
 */
public class AppException extends RuntimeException {

    private static final long serialVersionUID = -2091627819848377632L;

    /**
     * Constructs an {@linkplain AppException}.  The exception message is constructed by passing the given
     * {@literal format} and {@literal args} directly to {@link String#format(String, Object...)}.
     *
     * @param format A <a href="../util/Formatter.html#syntax">format string</a>
     * @param args   Arguments referenced by the format specifiers in the format
     *               string.  If there are more arguments than format specifiers, the
     *               extra arguments are ignored.  The number of arguments is
     *               variable and may be zero.
     * @throws IllegalFormatException If a format string contains an illegal syntax, a format
     *                                specifier that is incompatible with the given arguments,
     *                                insufficient arguments given the format string, or other
     *                                illegal conditions.  For specification of all possible
     *                                formatting errors, see the <a
     *                                href="../util/Formatter.html#detail">Details</a> section of the
     *                                formatter class specification.
     * @throws NullPointerException   If the <tt>format</tt> is <tt>null</tt>
     */
    public AppException(String format, Object... args) {
        super(String.format(format, args));
    }

    /**
     * Constructs an {@linkplain AppException}. The exception message is constructed by passing the given
     * {@code format} and {@code args} directly to {@link String#format(String, Object...)}.  The
     * {@code cause} is passed along to the super class.
     *
     * @param cause  {@link Throwable Cause} of the {@code Exception}.
     * @param format A <a href="../util/Formatter.html#syntax">format string</a>
     * @param args   Arguments referenced by the format specifiers in the format
     *               string.  If there are more arguments than format specifiers, the
     *               extra arguments are ignored.  The number of arguments is
     *               variable and may be zero.
     * @throws IllegalFormatException If a format string contains an illegal syntax, a format
     *                                specifier that is incompatible with the given arguments,
     *                                insufficient arguments given the format string, or other
     *                                illegal conditions.  For specification of all possible
     *                                formatting errors, see the <a
     *                                href="../util/Formatter.html#detail">Details</a> section of the
     *                                formatter class specification.
     * @throws NullPointerException   If the <tt>format</tt> is <tt>null</tt>
     */
    public AppException(Throwable cause, String format, Object... args) {
        super(String.format(format, args), cause);
    }
}
