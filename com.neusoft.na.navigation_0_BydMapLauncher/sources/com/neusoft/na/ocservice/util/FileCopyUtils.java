package com.neusoft.na.ocservice.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class FileCopyUtils {
    public static void copyFile(String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream = null;
        try {
            File file = new File(str);
            File file2 = new File(str2);
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
            } catch (Throwable th) {
                th = th;
                fileOutputStream = null;
            }
            try {
                byte[] bArr = new byte[2097152];
                while (fileInputStream2.read(bArr) != -1) {
                    fileOutputStream.write(bArr);
                }
                fileInputStream2.close();
                fileOutputStream.close();
            } catch (Throwable th2) {
                th = th2;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
        }
    }

    public static void copyDir(String str, String str2) throws Throwable {
        String[] list = new File(str).list();
        if (!new File(str2).exists()) {
            new File(str2).mkdir();
        }
        for (int i = 0; i < list.length; i++) {
            if (new File(str + File.separator + list[i]).isDirectory()) {
                copyDir(str + File.separator + list[i], str2 + File.separator + list[i]);
            }
            if (new File(str + File.separator + list[i]).isFile()) {
                copyFile(str + File.separator + list[i], str2 + File.separator + list[i]);
            }
        }
    }

    public static boolean deleteDir(File file) {
        if (file.isDirectory()) {
            for (String str : file.list()) {
                if (!deleteDir(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }
}
