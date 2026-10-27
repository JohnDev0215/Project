package com.byd.card.floatcard;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
public class CubicBezierInterpolator implements Interpolator {
    private final float mControlPoint1x;
    private final float mControlPoint1y;
    private final float mControlPoint2x;
    private final float mControlPoint2y;

    public CubicBezierInterpolator(float controlPoint1x, float controlPoint1y, float controlPoint2x, float controlPoint2y) {
        this.mControlPoint1x = controlPoint1x;
        this.mControlPoint1y = controlPoint1y;
        this.mControlPoint2x = controlPoint2x;
        this.mControlPoint2y = controlPoint2y;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float input) {
        return ((float) (Math.cos(((double) (input + 1.0f)) * 3.141592653589793d) / 2.0d)) + 0.5f;
    }
}
