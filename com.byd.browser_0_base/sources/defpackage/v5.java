package defpackage;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/* JADX INFO: compiled from: AnimationUtilsCompat.java */
/* JADX INFO: loaded from: classes.dex */
public final class v5 {
    public static Interpolator a(Context context, int i) {
        Interpolator interpolatorLoadInterpolator = AnimationUtils.loadInterpolator(context, i);
        if (interpolatorLoadInterpolator != null) {
            return interpolatorLoadInterpolator;
        }
        throw new NullPointerException("Failed to parse interpolator, no start tag found");
    }
}
