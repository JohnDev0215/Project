package com.facebook.yoga;

/* JADX INFO: loaded from: classes6.dex */
public class YogaConstants {
    public static float getUndefined() {
        return Float.NaN;
    }

    public static boolean isUndefined(float f) {
        return Float.compare(f, Float.NaN) == 0;
    }
}
