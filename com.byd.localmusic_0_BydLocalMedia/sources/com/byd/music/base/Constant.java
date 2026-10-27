package com.byd.music.base;

import android.os.Build;
import android.os.Environment;
import com.blankj.utilcode.util.Utils;

/* JADX INFO: loaded from: classes5.dex */
public class Constant {
    public static final String BLLOG_DIR;
    public static final String DEMOLOG_DIR;
    public static final String PATH;
    public static final String SD_PATH;

    static {
        String str;
        if (Build.VERSION.SDK_INT > 29) {
            str = Utils.getApp().getExternalFilesDir(null).getAbsolutePath() + "/";
        } else {
            str = Environment.getExternalStorageDirectory().getPath() + "/";
        }
        SD_PATH = str;
        PATH = SD_PATH + "BydMusic/";
        BLLOG_DIR = PATH + "bllog/";
        DEMOLOG_DIR = BLLOG_DIR + "demolog/";
    }
}
