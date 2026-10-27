package com.amazon.mls.config.internal.sushi.util;

import com.amazon.mls.config.internal.core.logcat.LogLevel;
import com.amazon.mls.config.internal.core.logcat.LogcatProxy;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class FileUtils {
    public static File getChildPath(File file, String str) {
        return new File(file, str.toLowerCase(Locale.US));
    }

    public static void setupDir(File file) {
        if (!file.exists() && !file.mkdirs()) {
            LogcatProxy.log(LogLevel.ERROR, "Unable to create file ring directory" + file.getAbsolutePath());
        }
        if (file.isDirectory() && file.canWrite()) {
            return;
        }
        throw new IllegalArgumentException("The configured writer dir is not valid! Path = " + file.getAbsolutePath());
    }

    public static File[] listFiles(File file) {
        return file.listFiles(new FilenameFilter() { // from class: com.amazon.mls.config.internal.sushi.util.FileUtils.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str) {
                File file3 = new File(file2, str);
                if (!file3.exists()) {
                    LogcatProxy.log(LogLevel.WARN, "File does not exist, ignoring " + file3.getAbsolutePath());
                    return false;
                }
                if (!file3.canWrite()) {
                    LogcatProxy.log(LogLevel.WARN, "Cannot write to file, ignoring " + file3.getAbsolutePath());
                    return false;
                }
                if (!file3.isDirectory()) {
                    return true;
                }
                LogcatProxy.log(LogLevel.INFO, "File is a directory, ignoring " + file3.getAbsolutePath());
                return false;
            }
        });
    }

    public static void safelyDeleteFromDisk(File file) {
        try {
            if (file.delete()) {
                return;
            }
            LogcatProxy.log(String.format(Locale.US, "Failed to delete file from disk: %s", file.getAbsolutePath()));
        } catch (SecurityException e) {
            LogcatProxy.log(String.format(Locale.US, "Failed to delete file file from disk: %s", file.getAbsolutePath()), e);
        }
    }
}
