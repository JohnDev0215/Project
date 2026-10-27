package com.ts.appservice.caradapter.e;

/* JADX INFO: compiled from: DataConversionUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    public static int a(int i) {
        return (i & 12) >> 2;
    }

    public static int b(int i) {
        return (i & 224) >> 5;
    }

    public static int c(int i) {
        return (i & 240) >> 4;
    }

    public static int d(int i) {
        return (i & 248) >> 3;
    }

    public static int e(int i) {
        return i & 3;
    }

    public static int f(int i) {
        return i & 7;
    }

    public static int g(int i) {
        return i & 15;
    }

    public static int h(int i) {
        return i & 31;
    }
}
