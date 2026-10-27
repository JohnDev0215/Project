package com.android.providers.downloads;

import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class RawDocumentsHelper {
    public static boolean isRawDocId(String str) {
        return str != null && str.startsWith("raw:");
    }

    public static String getDocIdForFile(File file) {
        return "raw:" + file.getAbsolutePath();
    }

    public static String getAbsoluteFilePath(String str) {
        return str.substring(4);
    }
}
