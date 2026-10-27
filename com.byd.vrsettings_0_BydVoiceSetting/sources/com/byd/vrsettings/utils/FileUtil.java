package com.byd.vrsettings.utils;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

/* JADX INFO: loaded from: classes.dex */
public class FileUtil {
    public static long getFileSizes(File file) throws Exception {
        if (file.exists()) {
            FileInputStream fileInputStream = new FileInputStream(file);
            long jAvailable = fileInputStream.available();
            fileInputStream.close();
            return jAvailable;
        }
        file.createNewFile();
        System.out.println("文件夹不存在");
        return 0L;
    }

    public static String getFileSize(String str) {
        return FormentFileSize(getFileSize(new File(str)));
    }

    public static long getFileSize(File file) {
        long length;
        File[] fileArrListFiles = file.listFiles();
        long j = 0;
        if (fileArrListFiles != null && fileArrListFiles.length != 0) {
            for (int i = 0; i < fileArrListFiles.length; i++) {
                if (fileArrListFiles[i].isDirectory()) {
                    length = getFileSize(fileArrListFiles[i]);
                } else {
                    length = fileArrListFiles[i].length();
                }
                j += length;
            }
        }
        return j;
    }

    public static String FormentFileSize(long j) {
        if (j == 0) {
            return "--";
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.0");
        if (j < 1024) {
            return ((int) j) + "B";
        }
        if (j < 1048576) {
            return ((int) (j / 1024)) + "K";
        }
        if (j < 1073741824) {
            return ((int) (j / 1048576)) + "M";
        }
        return decimalFormat.format(j / 1.073741824E9d) + "G";
    }
}
