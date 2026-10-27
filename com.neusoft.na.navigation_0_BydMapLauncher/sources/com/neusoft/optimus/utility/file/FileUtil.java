package com.neusoft.optimus.utility.file;

import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public class FileUtil {
    public static boolean isFile(String str) {
        return str != null && str.length() > 0 && new File(str).isFile();
    }

    public static boolean isFolder(String str) {
        return str != null && str.length() > 0 && new File(str).isDirectory();
    }

    public static boolean isSpecialFile(String str, String str2) {
        return str.endsWith(str2);
    }

    public static boolean checkFile(String[] strArr, String str) {
        for (String str2 : strArr) {
            if (str.endsWith(str2)) {
                return true;
            }
        }
        return false;
    }
}
