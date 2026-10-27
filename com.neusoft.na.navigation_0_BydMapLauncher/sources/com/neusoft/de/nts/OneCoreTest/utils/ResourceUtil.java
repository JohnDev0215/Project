package com.neusoft.de.nts.OneCoreTest.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class ResourceUtil {
    private static final Path tmpDir = Paths.get(System.getProperty("java.io.tmpdir"), new String[0]);

    private ResourceUtil() {
    }

    public static Path saveAsTempFile(String str) {
        return saveAsFile(str, tmpDir);
    }

    public static Path saveAsFile(String str, Path path) {
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException(String.format("The path '%s' do not denote a directory", path));
        }
        Path pathResolve = path.resolve(new File(str).getName());
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(str);
        Objects.requireNonNull(resourceAsStream, String.format("Cannot open the resource '%s'", str));
        try {
            Files.copy(resourceAsStream, pathResolve, StandardCopyOption.REPLACE_EXISTING);
            return pathResolve;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
