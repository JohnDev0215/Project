package b.a.a.i.o;

import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.interpolator.view.animation.FastOutLinearInInterpolator;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Interpolator f929a = new LinearInterpolator();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Interpolator f930b = new FastOutSlowInInterpolator();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final Interpolator f931c = new FastOutLinearInInterpolator();
    public static final Interpolator d = new LinearOutSlowInInterpolator();
    public static final Interpolator e = new DecelerateInterpolator();

    public static int a(int i, int i2, float f) {
        return i + Math.round(f * (i2 - i));
    }
}
