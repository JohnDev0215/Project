package com.neusoft.optimus.utility.filesize;

import com.neusoft.optimus.utility.log.LogTool;

/* JADX INFO: loaded from: classes2.dex */
public class FileTools {
    private static final String TAG = "FileTools";

    public static String conUnit(long j) {
        String str;
        LogTool.d(TAG, "conUnit() size: " + j);
        if (j < 1024) {
            return j + "Byte";
        }
        double d = j / 1024;
        LogTool.d(TAG, "conUnit() kb: " + d);
        if (d >= 1024.0d) {
            double d2 = d / 1024.0d;
            str = d2 >= 1024.0d ? String.format("%.2f", Double.valueOf(d2 / 1024.0d)) + "G" : String.format("%.2f", Double.valueOf(d2)) + "M";
        } else {
            str = String.format("%.2f", Double.valueOf(d)) + "K";
        }
        LogTool.d(TAG, "conUnit() filesSize: " + str);
        return str;
    }
}
