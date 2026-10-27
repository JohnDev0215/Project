package com.byd.vrassistant.utils;

import android.content.res.Resources;

/* JADX INFO: loaded from: classes2.dex */
public class SizeUtils {
    public static int dp2px(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }
}
