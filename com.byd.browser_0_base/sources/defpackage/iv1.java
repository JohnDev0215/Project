package defpackage;

import J.N;
import android.content.SharedPreferences;
import defpackage.xz;
import java.util.Arrays;

/* JADX INFO: compiled from: PageZoomUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class iv1 {
    public static final /* synthetic */ int a = 0;

    static {
        c(0.0d);
    }

    public static double a(int i) {
        return Math.round((Math.log10(((i / 250.0f) * 2.5f) + 0.5f) / Math.log10(1.2000000476837158d)) * 100.0d) / 100.0d;
    }

    public static double b(int i) {
        return ((i / 250.0f) * 2.5f) + 0.5f;
    }

    public static int c(double d) {
        return (int) Math.round(((Math.pow(1.2000000476837158d, d) - 0.5d) / 2.5d) * 250.0d);
    }

    public static int d(boolean z, double d) {
        double[] dArr = n3.k;
        if (z && d <= dArr[0]) {
            throw new IllegalArgumentException("currentZoomFactor should be greater than " + dArr[0]);
        }
        if (!z && d >= dArr[13]) {
            throw new IllegalArgumentException("currentZoomFactor should be less than " + dArr[13]);
        }
        int iBinarySearch = Arrays.binarySearch(dArr, d);
        if (iBinarySearch >= 0) {
            iBinarySearch = z ? iBinarySearch - 1 : iBinarySearch + 1;
        }
        if (iBinarySearch >= 0) {
            return iBinarySearch;
        }
        int i = (iBinarySearch + 1) * (-1);
        return z ? i - 1 : i;
    }

    public static boolean e() {
        if (!N.Mudil8Bg("AccessibilityPageZoom")) {
            return false;
        }
        SharedPreferences sharedPreferences = xz.a.a;
        if (sharedPreferences.contains("Chrome.PageZoom.AlwaysShowMenuItem")) {
            if (sharedPreferences.getBoolean("Chrome.PageZoom.AlwaysShowMenuItem", false)) {
                int i = hv1.a;
                oj2.U(1, 4, "Accessibility.Android.PageZoom.AppMenuEnabledState");
                return true;
            }
            int i2 = hv1.a;
            oj2.U(3, 4, "Accessibility.Android.PageZoom.AppMenuEnabledState");
            return false;
        }
        if (a8.h(n3.l, 1.0f)) {
            int i3 = hv1.a;
            oj2.U(0, 4, "Accessibility.Android.PageZoom.AppMenuEnabledState");
            return false;
        }
        int i4 = hv1.a;
        oj2.U(2, 4, "Accessibility.Android.PageZoom.AppMenuEnabledState");
        return true;
    }
}
