package org.stepwiselabs.core.resource;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.function.Consumer;
import java.util.function.Function;

public interface ReadableResource {

    String getLocation();

    InputStream open();

    <T> T withResource(Function<InputStream, T> callback);

    void useResource(Consumer<InputStream> consumer);

    String readContents();

    ReadableResource resolve(String path);

    ReadableResource resolve(Path path);
}
