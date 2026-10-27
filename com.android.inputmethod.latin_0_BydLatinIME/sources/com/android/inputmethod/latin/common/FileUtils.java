package com.android.inputmethod.latin.common;

import java.io.File;
import java.io.FilenameFilter;

/* JADX INFO: loaded from: classes2.dex */
public class FileUtils {
    private static final String TAG = "FileUtils";

    public static boolean deleteRecursively(final File path) {
        File[] fileArrListFiles;
        if (path.isDirectory() && (fileArrListFiles = path.listFiles()) != null) {
            for (File file : fileArrListFiles) {
                deleteRecursively(file);
            }
        }
        return path.delete();
    }

    public static boolean deleteFilteredFiles(final File dir, final FilenameFilter fileNameFilter) {
        File[] fileArrListFiles;
        if (!dir.isDirectory() || (fileArrListFiles = dir.listFiles(fileNameFilter)) == null) {
            return false;
        }
        boolean z = true;
        for (File file : fileArrListFiles) {
            if (!deleteRecursively(file)) {
                z = false;
            }
        }
        return z;
    }

    public static boolean renameTo(final File fromFile, final File toFile) {
        toFile.delete();
        return fromFile.renameTo(toFile);
    }
}
