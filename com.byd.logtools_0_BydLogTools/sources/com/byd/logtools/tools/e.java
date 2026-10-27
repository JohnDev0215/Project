package com.byd.logtools.tools;

import android.os.SystemProperties;
import android.util.Log;
import java.text.SimpleDateFormat;

/* JADX INFO: compiled from: CommonUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class e {
    public static SimpleDateFormat a(String str) {
        return new SimpleDateFormat(str);
    }

    public static String b(String str) {
        String str2 = SystemProperties.get(str, "116.63.88.150");
        Log.d("CommonUtils", "get hostName is : " + str2);
        return str2;
    }
}
