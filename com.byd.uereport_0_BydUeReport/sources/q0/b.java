package q0;

import android.os.Build;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

/* JADX INFO: compiled from: PathInterpolatorCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {
    public static Interpolator a(float f8, float f9, float f10, float f11) {
        return Build.VERSION.SDK_INT >= 21 ? new PathInterpolator(f8, f9, f10, f11) : new a(f8, f9, f10, f11);
    }
}
