package com.byd.data.common.i;

import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/* JADX INFO: compiled from: FileUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f3424a = "FileUtils";

    public static boolean a(String str) {
        f.g(f3424a, "delete2:" + str);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        f.g(f3424a, "delete3:" + str);
        File file = new File(str);
        f.g(f3424a, "file.exists():" + file.exists());
        if (file.exists()) {
            try {
                f.g(f3424a, "file.isFile():" + file.isFile());
                if (!file.isFile()) {
                    return b(str);
                }
                f.g(f3424a, "delete file.isFile():true");
                return c(str);
            } catch (Exception e2) {
                f.k(f3424a, Log.getStackTraceString(e2));
            }
        }
        return false;
    }

    private static boolean b(String str) {
        String str2 = File.separator;
        if (!str.endsWith(str2)) {
            str = str + str2;
        }
        File file = new File(str);
        if (!file.exists() || !file.isDirectory()) {
            return false;
        }
        File[] fileArrListFiles = file.listFiles();
        boolean zB = true;
        for (int i2 = 0; i2 < fileArrListFiles.length; i2++) {
            if (!fileArrListFiles[i2].isFile()) {
                if (fileArrListFiles[i2].isDirectory() && !(zB = b(fileArrListFiles[i2].getAbsolutePath()))) {
                    break;
                }
            } else {
                zB = c(fileArrListFiles[i2].getAbsolutePath());
                if (!zB) {
                    break;
                }
            }
        }
        if (zB) {
            return file.delete();
        }
        return false;
    }

    public static boolean c(String str) {
        File file = new File(str);
        boolean zDelete = !file.exists();
        if (!zDelete && file.isFile()) {
            zDelete = file.delete();
        }
        f.g(f3424a, "deleteFile path =" + str + ", ret =" + zDelete);
        return zDelete;
    }

    public static long d(File file) throws IOException {
        if (file.exists()) {
            return new FileInputStream(file).available();
        }
        file.createNewFile();
        return 0L;
    }

    public static long e(File file) {
        File[] fileArrListFiles = file.listFiles();
        long jE = 0;
        if (fileArrListFiles == null) {
            return 0L;
        }
        for (int i2 = 0; i2 < fileArrListFiles.length; i2++) {
            jE += fileArrListFiles[i2].isDirectory() ? e(fileArrListFiles[i2]) : d(fileArrListFiles[i2]);
        }
        return jE;
    }

    public static int f(int i2) {
        if (i2 > 100000) {
            return 6;
        }
        if (i2 > 10000) {
            return 5;
        }
        if (i2 > 1000) {
            return 4;
        }
        if (i2 > 100) {
            return 3;
        }
        return i2 > 10 ? 2 : 1;
    }
}
