package org.stepwiselabs.core.resource;

import org.stepwiselabs.core.Strings;
import org.stepwiselabs.core.exceptions.ResourceAccessException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.stepwiselabs.core.resource.ClasspathReadableResource.CLASSPATH_PREFIX;
import static org.stepwiselabs.core.resource.ClasspathReadableResource.canOpenClasspathLocation;

public class ReadableResourceLoader {

    /**
     * Loads a {@link ReadableResource} from the given {@literal location}.
     *
     * @param location - The location of the Resource
     * @return ReadableResource
     */
    public static ReadableResource load(String location) {

        if (Strings.isBlank(location)) {
            throw new IllegalArgumentException("resource location is required");
        }

        return location.startsWith(CLASSPATH_PREFIX) ?
                loadClasspathLocation(location) :
                loadFileLocation(Paths.get(location));
    }

    private static ReadableResource loadClasspathLocation(String location) {

        if (!canOpenClasspathLocation(location)) {
            throw ResourceAccessException.build("Cannot open classpath location")
                    .withParam("location", location)
                    .build();
        }
        return new ClasspathReadableResource(location);
    }

    private static ReadableResource loadFileLocation(Path path) {

        if (!Files.exists(path)) {
            throw ResourceAccessException.build("File does not exist")
                    .withParam("path", path.toString()).build();
        }
        if (!Files.isReadable(path)) {
            throw ResourceAccessException.build("File cannot be read from")
                    .withParam("path", path.toString()).build();
        }

        return new FileSystemReadableResource(path);
    }
}
