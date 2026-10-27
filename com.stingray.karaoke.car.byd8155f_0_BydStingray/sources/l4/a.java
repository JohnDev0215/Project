package L4;

import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import h2.AbstractC1347d;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final LinearInterpolator f4007a = new LinearInterpolator();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Z1.a f4008b = new Z1.a(Z1.a.d);
    public static final Z1.a c = new Z1.a(Z1.a.c);
    public static final Z1.a d = new Z1.a(Z1.a.f10809e);

    static {
        new DecelerateInterpolator();
    }

    public static float a(float f10, float f11, float f12) {
        return AbstractC1347d.a(f11, f10, f12, f10);
    }
}
