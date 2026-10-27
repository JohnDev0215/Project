package com.airbnb.lottie;

import androidx.annotation.RestrictTo;
import androidx.core.os.TraceCompat;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f657a = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f658b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static String[] f659c;
    private static long[] d;
    private static int e;
    private static int f;

    public static void a(String str) {
        if (f658b) {
            int i = e;
            if (i == 20) {
                f++;
                return;
            }
            f659c[i] = str;
            d[i] = System.nanoTime();
            TraceCompat.beginSection(str);
            e++;
        }
    }

    public static float b(String str) {
        int i = f;
        if (i > 0) {
            f = i - 1;
            return 0.0f;
        }
        if (!f658b) {
            return 0.0f;
        }
        int i2 = e - 1;
        e = i2;
        if (i2 == -1) {
            throw new IllegalStateException("Can't end trace section. There are none.");
        }
        if (str.equals(f659c[i2])) {
            TraceCompat.endSection();
            return (System.nanoTime() - d[e]) / 1000000.0f;
        }
        throw new IllegalStateException("Unbalanced trace call " + str + ". Expected " + f659c[e] + ".");
    }
}
