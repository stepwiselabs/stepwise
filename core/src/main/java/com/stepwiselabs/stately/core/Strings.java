package com.stepwiselabs.stately.core;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Static utility methods related to {@link java.lang.String strings}.
 */
public final class Strings {

    // static class
    private Strings() {
        throw new InstantiationError();
    }

    /**
     * Determines if the given {@code sequence} is 'blank'. Blank is defined as
     * being either {@code null} or containing all whitespace characters as
     * defined by {@link Character#isWhitespace(char)}.
     *
     * @param sequence
     * @return {@code true} if the given {@code sequence} is blank.
     */
    public static boolean isBlank(final CharSequence sequence) {
        int sequenceLength;
        if (sequence == null || (sequenceLength = sequence.length()) == 0) {
            return true;
        }
        for (int i = 0; i < sequenceLength; i++) {
            if (Character.isWhitespace(sequence.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }


    /**
     * Converts a {@link String} to an {@link InputStream}.
     *
     * @param toConvert
     * @return
     */
    public static InputStream toInputStream(String toConvert) {
        return new ByteArrayInputStream(toConvert.getBytes(Charset.defaultCharset()));
    }

    /**
     * Returns the inverse of {@link #isBlank(CharSequence)}
     *
     * @param sequence
     * @return the inverse of {@link #isBlank(CharSequence)}
     */
    public static boolean notBlank(final CharSequence sequence) {
        return !isBlank(sequence);
    }
}

