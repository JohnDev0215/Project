package com.byd.vrsettings.utils;

import android.content.res.Resources;

/* JADX INFO: loaded from: classes.dex */
public class DimenUtil {
    public static int dp2px(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }
}
