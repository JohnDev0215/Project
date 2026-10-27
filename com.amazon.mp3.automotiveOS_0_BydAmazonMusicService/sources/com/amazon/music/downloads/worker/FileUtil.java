package com.amazon.music.downloads.worker;

import java.io.File;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class FileUtil {
    private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class.getSimpleName());

    public static boolean mkdirs(File file) throws IOException {
        if (file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return mkdirs(file.getParentFile());
        }
        if (file.mkdirs()) {
            LOG.debug("Created directories: " + file.getAbsolutePath());
            return true;
        }
        File file2 = new File(file, "00file.txt");
        try {
            try {
                if (!file.exists() && !file2.createNewFile()) {
                    Logger logger = LOG;
                    logger.error("Could not create directory: " + file.getAbsolutePath());
                    if (file2.exists() && !file2.delete()) {
                        logger.error("Could not delete the temp file: {}", file2.getAbsolutePath());
                    }
                    return false;
                }
                return file.exists();
            } catch (IOException e) {
                LOG.error("Could not create directory: " + e.getMessage() + ", " + file.getAbsolutePath(), (Throwable) e);
                throw e;
            }
        } finally {
            if (file2.exists()) {
                LOG.error("Could not delete the temp file: {}", file2.getAbsolutePath());
            }
        }
        if (file2.exists() && !file2.delete()) {
            LOG.error("Could not delete the temp file: {}", file2.getAbsolutePath());
        }
    }
}
