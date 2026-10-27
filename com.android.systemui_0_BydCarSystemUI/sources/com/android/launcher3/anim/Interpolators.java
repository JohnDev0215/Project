package com.android.launcher3.anim;

import android.graphics.Path;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.PathInterpolator;

/* JADX INFO: loaded from: classes.dex */
public class Interpolators {
    public static final Interpolator EXAGGERATED_EASE;
    public static final Interpolator LINEAR = new LinearInterpolator();
    public static final Interpolator AGGRESSIVE_EASE = new PathInterpolator(0.2f, 0.2f, 0.1f, 1.0f);
    public static final Interpolator DEACCEL_1_7 = new DecelerateInterpolator(1.7f);

    static {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(0.05f, 0.0f, 0.133333f, 0.08f, 0.166666f, 0.4f);
        path.cubicTo(0.225f, 0.94f, 0.5f, 1.0f, 1.0f, 1.0f);
        EXAGGERATED_EASE = new PathInterpolator(path);
    }
}
