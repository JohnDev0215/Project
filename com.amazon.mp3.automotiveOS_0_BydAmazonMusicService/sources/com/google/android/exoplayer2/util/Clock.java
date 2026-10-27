package com.google.android.exoplayer2.util;

/* JADX INFO: loaded from: classes6.dex */
public interface Clock {
    public static final Clock DEFAULT = new SystemClock();

    long elapsedRealtime();
}
