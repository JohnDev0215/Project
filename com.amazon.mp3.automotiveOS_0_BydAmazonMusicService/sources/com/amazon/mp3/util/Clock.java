package com.amazon.mp3.util;

import java.util.Date;

/* JADX INFO: loaded from: classes4.dex */
public class Clock {
    public Date nowInUTC() {
        return new Date();
    }

    public static long currentTimeSeconds() {
        return System.currentTimeMillis() / 1000;
    }
}
