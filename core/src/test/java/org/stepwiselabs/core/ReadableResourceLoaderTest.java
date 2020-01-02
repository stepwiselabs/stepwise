package org.stepwiselabs.core;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stepwiselabs.core.resource.ReadableResource;
import org.stepwiselabs.core.resource.ReadableResourceLoader;

import java.nio.file.Path;

public class ReadableResourceLoaderTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReadableResourceLoaderTest.class);
    private static Path readableFile = FileUtils.getResourcesRoot().resolve("readable-resource.txt");

    @Test
    public void canLoadFileResource(){

        ReadableResource rr = ReadableResourceLoader.load(readableFile.toString());

    }




}
