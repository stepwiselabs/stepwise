package org.stepwiselabs.core.resource;

import org.stepwiselabs.core.functions.ConsumerWithIOException;
import org.stepwiselabs.core.functions.FunctionWithIOException;

import java.io.InputStream;
import java.nio.file.Path;

public interface ReadableResource {

    String getLocation();

    InputStream open();

    <T> T withResource(FunctionWithIOException<InputStream, T> callback);

    void useResource(ConsumerWithIOException<InputStream> consumer);

    String readContents();

    ReadableResource resolve(String path);

    ReadableResource resolve(Path path);
}
