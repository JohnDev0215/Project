package com.blankj.utilcode.util;

import android.content.res.Resources;

/* JADX INFO: compiled from: SizeUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class u {
    public static int a(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }
}
