package com.amazon.music.metrics.mts.android;

import android.content.Context;
import android.os.Environment;
import java.io.File;

/* JADX INFO: loaded from: classes4.dex */
public class MetricsFilePathUtil {
    private MetricsFilePathUtil() {
    }

    public static String getDefaultMetricsPath(Context context) throws StorageNotAvailableException {
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            return String.format("%s/%s/%s", filesDir.getAbsolutePath(), "metrics", "MTSMetricsReport.txt");
        }
        throw new StorageNotAvailableException("Unable to generate file path for metrics file.  Consider providing an explicit file path in the constructor");
    }

    public static String getPriorityMetricsPath(Context context) throws StorageNotAvailableException {
        File filesDir = context.getFilesDir();
        if (filesDir != null) {
            return String.format("%s/%s/%s", filesDir.getAbsolutePath(), "metrics", "MTSPriorityMetricsReport.txt");
        }
        throw new StorageNotAvailableException("Unable to generate file path for Priority metrics file.  Consider providing an explicit file path in the constructor");
    }

    public static String getDefaultDebugMetricsPath() throws StorageNotAvailableException {
        return getDefaultDebugMetricsPath(externalStorageAvailable());
    }

    static String getDefaultDebugMetricsPath(boolean z) throws StorageNotAvailableException {
        if (!z) {
            throw new StorageNotAvailableException("Unable to generate file path for debug file.  Consider providing an explicit file path in the constructor");
        }
        return String.format("%s/%s/%s", Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC).getPath(), "metrics", "MTSMetricsDebugReport.txt");
    }

    private static boolean externalStorageAvailable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
}
