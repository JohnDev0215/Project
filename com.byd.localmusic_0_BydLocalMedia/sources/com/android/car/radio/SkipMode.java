package com.android.car.radio;

/* JADX INFO: loaded from: classes4.dex */
public enum SkipMode {
    FAVORITES,
    TUNE,
    BROWSE;

    public static final SkipMode DEFAULT_MODE = TUNE;

    public static SkipMode valueOf(int i) {
        if (i < 0 || i > values().length - 1) {
            return null;
        }
        return values()[i];
    }
}
