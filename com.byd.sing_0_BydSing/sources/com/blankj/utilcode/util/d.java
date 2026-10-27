package com.blankj.utilcode.util;

import android.content.res.Resources;

/* JADX INFO: compiled from: BarUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static int a() {
        Resources resources = w.a().getResources();
        int identifier = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier != 0) {
            return resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static int b() {
        Resources resources = w.a().getResources();
        return resources.getDimensionPixelSize(resources.getIdentifier("status_bar_height", "dimen", "android"));
    }
}
