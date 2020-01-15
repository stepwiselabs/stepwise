package org.stepwiselabs.core.resource;

import org.stepwiselabs.core.Strings;
import org.stepwiselabs.core.exceptions.ResourceAccessException;
import org.stepwiselabs.core.functions.ConsumerWithIOException;
import org.stepwiselabs.core.functions.FunctionWithIOException;

import java.io.IOException;
import java.io.InputStream;

abstract class AbstractReadableResource implements ReadableResource {

    protected final String location;

    AbstractReadableResource(String location) {
        this.location = location;
    }

    /**
     * The location of the Resource is the original location passed into the constructor
     *
     * @return the location of the resources root
     */
    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public <T> T withResource(FunctionWithIOException<InputStream, T> callback) {
        try (InputStream in = open()) {
            return callback.apply(in);
        } catch (IOException e) {
            throw ResourceAccessException.build(e.getMessage())
                    .withParam("resource", getLocation())
                    .withCause(e)
                    .build();
        }
    }

    @Override
    public void useResource(ConsumerWithIOException<InputStream> consumer) {
        try (InputStream in = open()) {
            consumer.accept(in);
        } catch (IOException e) {
            throw ResourceAccessException.build(e.getMessage())
                    .withParam("resource", getLocation())
                    .withCause(e)
                    .build();
        }
    }

    @Override
    public String readContents() {
        return withResource(in -> Strings.readContents(in));
    }
}