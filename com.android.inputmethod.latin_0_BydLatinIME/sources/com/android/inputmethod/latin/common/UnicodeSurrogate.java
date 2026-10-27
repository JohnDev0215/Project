package com.android.inputmethod.latin.common;

/* JADX INFO: loaded from: classes2.dex */
public final class UnicodeSurrogate {
    private static final char HIGH_SURROGATE_MAX = 57343;
    private static final char HIGH_SURROGATE_MIN = 56320;
    private static final char LOW_SURROGATE_MAX = 56319;
    private static final char LOW_SURROGATE_MIN = 55296;

    public static boolean isHighSurrogate(final char c) {
        return c >= 56320 && c <= 57343;
    }

    public static boolean isLowSurrogate(final char c) {
        return c >= 55296 && c <= 56319;
    }
}
