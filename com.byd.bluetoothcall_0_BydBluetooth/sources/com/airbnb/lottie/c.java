package com.airbnb.lottie;

/* JADX INFO: compiled from: L.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f2820a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f2821b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String[] f2822c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static long[] f2823d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static int f2824e;
    private static int f;

    public static void a(String str) {
        if (f2821b) {
            int i = f2824e;
            if (i == 20) {
                f++;
                return;
            }
            f2822c[i] = str;
            f2823d[i] = System.nanoTime();
            b.g.g.a.a(str);
            f2824e++;
        }
    }

    public static float b(String str) {
        int i = f;
        if (i > 0) {
            f = i - 1;
            return 0.0f;
        }
        if (!f2821b) {
            return 0.0f;
        }
        int i2 = f2824e - 1;
        f2824e = i2;
        if (i2 == -1) {
            throw new IllegalStateException("Can't end trace section. There are none.");
        }
        if (str.equals(f2822c[i2])) {
            b.g.g.a.b();
            return (System.nanoTime() - f2823d[f2824e]) / 1000000.0f;
        }
        throw new IllegalStateException("Unbalanced trace call " + str + ". Expected " + f2822c[f2824e] + ".");
    }
}
