package org.stepwiselabs.core;

import org.stepwiselabs.core.exceptions.ResourceAccessException;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    /**
     * Returns the path to the resources root directory. This is for the edge case of accessing resources as files.
     *
     * @return the resources root directory path
     */
    public static Path getResourcesRoot() {
        URL rootURL = FileUtils.class.getResource("/");
        if (rootURL == null) {
            throw new ResourceAccessException("Unable to find url for resources root");
        }
        String filePath = rootURL.getFile();
        if (filePath == null) {
            throw new ResourceAccessException("Unable to find file directory for resources root");
        }
        return Paths.get(filePath);
    }
}
