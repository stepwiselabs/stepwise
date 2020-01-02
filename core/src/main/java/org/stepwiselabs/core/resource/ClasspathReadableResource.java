package org.stepwiselabs.core.resource;

import org.stepwiselabs.core.exceptions.ResourceAccessException;

import java.io.InputStream;
import java.util.stream.Stream;

class ClasspathReadableResource extends AbstractReadableResource {

    public static final String CLASSPATH_PREFIX = "classpath:";

    ClasspathReadableResource(String location) {
        super(location);
    }

    @Override
    public InputStream open() {
        return openClasspathLocation(location);
    }

    static boolean canOpenClasspathLocation(String location) {
        try (InputStream in = openClasspathLocation(location)) {
            return true;
        } catch (Throwable t) {
            return false;
        }
    }

    private static InputStream openClasspathLocation(String classpathLocation) {

        final String location = stripClasspath(classpathLocation);
        return Stream.of(Thread.currentThread().getContextClassLoader(), ReadableResourceLoader.class.getClassLoader())
                .map(cl -> cl.getResourceAsStream(location))
                .findFirst()
                .orElseThrow(() -> ResourceAccessException.build("Error opening classpath resource")
                        .withParam("location", location)
                        .build());
    }

    private static String stripClasspath(String location) {
        return location.startsWith(CLASSPATH_PREFIX) ? location.substring(CLASSPATH_PREFIX.length()) : location;
    }

}
