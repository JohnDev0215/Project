package com.android.inputmethod.keyboard.internal;

import android.content.res.TypedArray;

/* JADX INFO: loaded from: classes2.dex */
final class GestureTrailDrawingParams {
    private static final int FADEOUT_DURATION_FOR_DEBUG = 200;
    private static final int FADEOUT_START_DELAY_FOR_DEBUG = 2000;
    public final int mFadeoutDuration;
    public final int mFadeoutStartDelay;
    public final float mTrailBodyRatio;
    public final int mTrailColor;
    public final float mTrailEndWidth;
    public final int mTrailLingerDuration;
    public boolean mTrailShadowEnabled;
    public final float mTrailShadowRatio;
    public final float mTrailStartWidth;
    public final int mUpdateInterval;

    public GestureTrailDrawingParams(final TypedArray mainKeyboardViewAttr) {
        this.mTrailColor = mainKeyboardViewAttr.getColor(23, 0);
        this.mTrailStartWidth = mainKeyboardViewAttr.getDimension(32, 0.0f);
        this.mTrailEndWidth = mainKeyboardViewAttr.getDimension(24, 0.0f);
        this.mTrailBodyRatio = mainKeyboardViewAttr.getInt(22, 100) / 100.0f;
        int i = mainKeyboardViewAttr.getInt(31, 0);
        this.mTrailShadowEnabled = i > 0;
        this.mTrailShadowRatio = i / 100.0f;
        int i2 = mainKeyboardViewAttr.getInt(26, 0);
        this.mFadeoutStartDelay = i2;
        int i3 = mainKeyboardViewAttr.getInt(25, 0);
        this.mFadeoutDuration = i3;
        this.mTrailLingerDuration = i2 + i3;
        this.mUpdateInterval = mainKeyboardViewAttr.getInt(33, 0);
    }
}
