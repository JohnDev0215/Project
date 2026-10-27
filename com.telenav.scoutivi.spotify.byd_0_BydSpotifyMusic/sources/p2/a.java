package p2;

import android.animation.TimeInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/* JADX INFO: compiled from: AnimationUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final TimeInterpolator f8820a = new LinearInterpolator();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final TimeInterpolator f8821b = new r0.b();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final TimeInterpolator f8822c = new r0.a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final TimeInterpolator f8823d = new r0.c();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final TimeInterpolator f8824e = new DecelerateInterpolator();

    public static float a(float f7, float f8, float f9) {
        return f7 + (f9 * (f8 - f7));
    }

    public static float b(float f7, float f8, float f9, float f10, float f11) {
        return f11 < f9 ? f7 : f11 > f10 ? f8 : a(f7, f8, (f11 - f9) / (f10 - f9));
    }

    public static int c(int i7, int i8, float f7) {
        return i7 + Math.round(f7 * (i8 - i7));
    }
}
