package com.airbnb.lottie;

/* JADX INFO: compiled from: L.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f2434a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f2435b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String[] f2436c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static long[] f2437d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static int f2438e;
    private static int f;

    public static void a(String str) {
        if (f2435b) {
            int i = f2438e;
            if (i == 20) {
                f++;
                return;
            }
            f2436c[i] = str;
            f2437d[i] = System.nanoTime();
            b.g.g.a.a(str);
            f2438e++;
        }
    }

    public static float b(String str) {
        int i = f;
        if (i > 0) {
            f = i - 1;
            return 0.0f;
        }
        if (!f2435b) {
            return 0.0f;
        }
        int i2 = f2438e - 1;
        f2438e = i2;
        if (i2 == -1) {
            throw new IllegalStateException("Can't end trace section. There are none.");
        }
        if (str.equals(f2436c[i2])) {
            b.g.g.a.b();
            return (System.nanoTime() - f2437d[f2438e]) / 1000000.0f;
        }
        throw new IllegalStateException("Unbalanced trace call " + str + ". Expected " + f2436c[f2438e] + ".");
    }
}
