package com.android.networkstack.apishim.common;

import android.os.Build;

/* JADX INFO: loaded from: classes.dex */
public final class ShimUtils {
    public static boolean isReleaseOrDevelopmentApiAbove(int i) {
        return Build.VERSION.SDK_INT + (!"REL".equals(Build.VERSION.CODENAME) ? 1 : 0) > i;
    }
}
