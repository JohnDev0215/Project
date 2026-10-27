package com.android.inputmethod.compat;

import android.os.Build;

/* JADX INFO: loaded from: classes2.dex */
public final class BuildCompatUtils {
    public static final int EFFECTIVE_SDK_INT;
    private static final boolean IS_RELEASE_BUILD;

    private BuildCompatUtils() {
    }

    static {
        int i;
        boolean zEquals = Build.VERSION.CODENAME.equals("REL");
        IS_RELEASE_BUILD = zEquals;
        if (zEquals) {
            i = Build.VERSION.SDK_INT;
        } else {
            i = Build.VERSION.SDK_INT + 1;
        }
        EFFECTIVE_SDK_INT = i;
    }
}
