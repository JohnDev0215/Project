package com.byd.data.common.i;

/* JADX INFO: compiled from: TimePeriodLogUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static long f3431a;

    public static void a(String str, String str2) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - f3431a >= 30000) {
            f.g(str, str2);
            f3431a = jCurrentTimeMillis;
        }
    }
}
