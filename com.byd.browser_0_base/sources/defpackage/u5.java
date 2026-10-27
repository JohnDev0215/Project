package defpackage;

import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

/* JADX INFO: compiled from: AnimationUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class u5 {
    public static final LinearInterpolator a = new LinearInterpolator();
    public static final jk0 b = new jk0();
    public static final ik0 c = new ik0();
    public static final p71 d = new p71();
    public static final DecelerateInterpolator e = new DecelerateInterpolator();

    public static float a(float f, float f2, float f3, float f4, float f5) {
        return f5 < f3 ? f : f5 > f4 ? f2 : C0173a.f(f2, f, (f5 - f3) / (f4 - f3), f);
    }

    public static int b(int i, int i2, float f) {
        return Math.round(f * (i2 - i)) + i;
    }
}
