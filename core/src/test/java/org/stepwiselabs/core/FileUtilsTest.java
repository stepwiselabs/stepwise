package org.stepwiselabs.core;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stepwiselabs.core.resource.ReadableResourceLoaderTest;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.assertTrue;

public class FileUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadableResourceLoaderTest.class);

    /**
     * The resources root directory path is accessible from the ReadableResource Impl, and should resolve to a
     * readable filesystem resource
     */
    @Test
    public void assertResourceFilePathExists() {

        // given
        Path readableFile = FileUtils.getResourcesRoot().resolve("readableResourceTest/readable-resource.txt");

        // then
        LOGGER.info("readable file: " + readableFile);
        assertTrue(Files.isReadable(readableFile));
    }
}
