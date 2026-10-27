package com.amazon.mp3.util;

import java.util.Random;

/* JADX INFO: loaded from: classes4.dex */
public class ThreadUtil {
    private static Random sRandom = new Random();

    public static void dumpStacktraceOnMainThread() {
    }

    public static void randomSleep(long minTime, long maxTime) {
        try {
            Thread.sleep(minTime + Math.abs(((long) sRandom.nextInt()) % (maxTime - minTime)));
        } catch (InterruptedException unused) {
        }
    }
}
