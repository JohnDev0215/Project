package org.apache.shiro.util;

/* JADX INFO: loaded from: classes.dex */
@Deprecated
public abstract class JavaEnvironment {
    public static final int JAVA_13 = 0;
    public static final int JAVA_14 = 1;
    public static final int JAVA_15 = 2;
    public static final int JAVA_16 = 3;
    public static final int JAVA_17 = 4;
    public static final int JAVA_18 = 5;
    private static final int majorVersion;
    private static final String version;

    static {
        String property = System.getProperty("java.version");
        version = property;
        if (property.contains("1.8.")) {
            majorVersion = 5;
            return;
        }
        if (property.contains("1.7.")) {
            majorVersion = 4;
            return;
        }
        if (property.contains("1.6.")) {
            majorVersion = 3;
            return;
        }
        if (property.contains("1.5.")) {
            majorVersion = 2;
        } else if (property.contains("1.4.")) {
            majorVersion = 1;
        } else {
            majorVersion = 0;
        }
    }

    public static String getVersion() {
        return version;
    }

    public static int getMajorVersion() {
        return majorVersion;
    }

    public static boolean isAtLeastVersion14() {
        return getMajorVersion() >= 1;
    }

    public static boolean isAtLeastVersion15() {
        return getMajorVersion() >= 2;
    }

    public static boolean isAtLeastVersion16() {
        return getMajorVersion() >= 3;
    }
}
