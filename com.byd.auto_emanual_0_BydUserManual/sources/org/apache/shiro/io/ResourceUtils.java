package org.apache.shiro.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.shiro.util.ClassUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes.dex */
public class ResourceUtils {
    public static final String CLASSPATH_PREFIX = "classpath:";
    public static final String FILE_PREFIX = "file:";
    public static final String URL_PREFIX = "url:";
    private static final Logger log = LoggerFactory.getLogger(ResourceUtils.class);

    private ResourceUtils() {
    }

    public static boolean hasResourcePrefix(String str) {
        return str != null && (str.startsWith(CLASSPATH_PREFIX) || str.startsWith(URL_PREFIX) || str.startsWith(FILE_PREFIX));
    }

    public static boolean resourceExists(String str) {
        try {
            InputStream inputStreamForPath = getInputStreamForPath(str);
            if (inputStreamForPath == null) {
                return true;
            }
            try {
                inputStreamForPath.close();
                return true;
            } catch (IOException unused) {
                return true;
            }
        } catch (IOException unused2) {
            return false;
        }
    }

    public static InputStream getInputStreamForPath(String str) throws IOException {
        InputStream inputStreamLoadFromFile;
        if (str.startsWith(CLASSPATH_PREFIX)) {
            inputStreamLoadFromFile = loadFromClassPath(stripPrefix(str));
        } else if (str.startsWith(URL_PREFIX)) {
            inputStreamLoadFromFile = loadFromUrl(stripPrefix(str));
        } else if (str.startsWith(FILE_PREFIX)) {
            inputStreamLoadFromFile = loadFromFile(stripPrefix(str));
        } else {
            inputStreamLoadFromFile = loadFromFile(str);
        }
        if (inputStreamLoadFromFile != null) {
            return inputStreamLoadFromFile;
        }
        throw new IOException("Resource [" + str + "] could not be found.");
    }

    private static InputStream loadFromFile(String str) throws IOException {
        Logger logger = log;
        if (logger.isDebugEnabled()) {
            logger.debug("Opening file [" + str + "]...");
        }
        return new FileInputStream(str);
    }

    private static InputStream loadFromUrl(String str) throws IOException {
        log.debug("Opening url {}", str);
        return new URL(str).openStream();
    }

    private static InputStream loadFromClassPath(String str) {
        log.debug("Opening resource from class path [{}]", str);
        return ClassUtils.getResourceAsStream(str);
    }

    private static String stripPrefix(String str) {
        return str.substring(str.indexOf(":") + 1);
    }

    public static void close(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.warn("Error closing input stream.", e);
            }
        }
    }
}
