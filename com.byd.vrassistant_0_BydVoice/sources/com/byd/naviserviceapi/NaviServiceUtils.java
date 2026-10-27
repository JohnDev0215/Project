package com.byd.naviserviceapi;

import android.os.Build;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class NaviServiceUtils {
    public static int getNaviApiVersionCode() {
        return 21;
    }

    public static String getPublishDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(new Date(Build.TIME));
    }
}
