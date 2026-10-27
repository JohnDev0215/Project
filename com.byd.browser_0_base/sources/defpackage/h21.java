package defpackage;

import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.view.animation.PathInterpolator;

/* JADX INFO: compiled from: Interpolators.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h21 {
    public static final DecelerateInterpolator a;
    public static final PathInterpolator b;
    public static final PathInterpolator c;
    public static final ik0 d;
    public static final jk0 e;
    public static final LinearInterpolator f;
    public static final p71 g;
    public static final OvershootInterpolator h;

    static {
        new AccelerateInterpolator();
        a = new DecelerateInterpolator();
        b = new PathInterpolator(0.3f, 0.0f, 0.8f, 0.15f);
        c = new PathInterpolator(0.05f, 0.7f, 0.1f, 1.0f);
        d = new ik0();
        e = new jk0();
        f = new LinearInterpolator();
        g = new p71();
        h = new OvershootInterpolator();
    }
}
