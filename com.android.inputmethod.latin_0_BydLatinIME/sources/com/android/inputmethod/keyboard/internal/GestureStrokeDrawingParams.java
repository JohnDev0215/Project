package com.android.inputmethod.keyboard.internal;

import android.content.res.TypedArray;

/* JADX INFO: loaded from: classes2.dex */
public final class GestureStrokeDrawingParams {
    private static final int DEFAULT_MAX_INTERPOLATION_ANGULAR_THRESHOLD = 15;
    private static final float DEFAULT_MAX_INTERPOLATION_DISTANCE_THRESHOLD = 0.0f;
    private static final int DEFAULT_MAX_INTERPOLATION_SEGMENTS = 4;
    private static final float DEFAULT_MIN_SAMPLING_DISTANCE = 0.0f;
    public final double mMaxInterpolationAngularThreshold;
    public final double mMaxInterpolationDistanceThreshold;
    public final int mMaxInterpolationSegments;
    public final double mMinSamplingDistance;

    public GestureStrokeDrawingParams(final TypedArray mainKeyboardViewAttr) {
        double radians;
        this.mMinSamplingDistance = mainKeyboardViewAttr.getDimension(30, 0.0f);
        int integer = mainKeyboardViewAttr.getInteger(27, 0);
        if (integer <= 0) {
            radians = Math.toRadians(15.0d);
        } else {
            radians = Math.toRadians(integer);
        }
        this.mMaxInterpolationAngularThreshold = radians;
        this.mMaxInterpolationDistanceThreshold = mainKeyboardViewAttr.getDimension(28, 0.0f);
        this.mMaxInterpolationSegments = mainKeyboardViewAttr.getInteger(29, 4);
    }
}
