package com.neusoft.de.nts.OneCoreTest.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

/* JADX INFO: loaded from: classes2.dex */
public class Config {
    private static final String PROPERTIES_PATH = "config.properties";
    private static final Logger log = Logger.getLogger((Class<?>) Config.class);
    private static final Properties properties = new Properties();

    static {
        Properties properties2 = new Properties();
        Properties properties3 = new Properties();
        if (Files.exists(Paths.get(PROPERTIES_PATH, new String[0]), new LinkOption[0])) {
            try {
                properties2.load(new FileInputStream(PROPERTIES_PATH));
            } catch (IOException e) {
                log.error(e, "Cannot load properties from cwd file config.properties", new Object[0]);
            }
        }
        try {
            InputStream resourceAsStream = Config.class.getClassLoader().getResourceAsStream(PROPERTIES_PATH);
            if (resourceAsStream != null) {
                properties3.load(resourceAsStream);
            } else {
                log.warn("Cannot open properties path '%s'", PROPERTIES_PATH);
            }
        } catch (Exception e2) {
            log.warn(e2);
        }
        Properties properties4 = properties;
        properties4.putAll(properties3);
        properties4.putAll(properties2);
        properties4.putAll(System.getenv());
        properties4.putAll(System.getProperties());
        dumpLog();
    }

    private static void assertKeyExists(String str) {
        if (!properties.containsKey(str)) {
            throw new IllegalArgumentException("Property not available: " + str);
        }
    }

    public static void dumpLog() {
        for (Map.Entry entry : properties.entrySet()) {
            log.debug("Property: %s=%s", entry.getKey(), entry.getValue());
        }
    }

    public static boolean containsKey(String str) {
        return properties.containsKey(str);
    }

    public static String getString(String str) {
        assertKeyExists(str);
        return properties.getProperty(str);
    }

    public static String getString(String str, String str2) {
        return properties.containsKey(str) ? getString(str) : str2;
    }

    public static int getInt(String str) {
        assertKeyExists(str);
        return Integer.parseInt(properties.getProperty(str));
    }

    public static int getInt(String str, int i) {
        return properties.containsKey(str) ? getInt(str) : i;
    }

    public static float getFloat(String str) {
        assertKeyExists(str);
        return Float.parseFloat(properties.getProperty(str));
    }

    public static float getFloat(String str, int i) {
        return properties.containsKey(str) ? getFloat(str) : i;
    }
}
