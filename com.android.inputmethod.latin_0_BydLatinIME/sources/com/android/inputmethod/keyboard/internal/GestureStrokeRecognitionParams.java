package com.android.inputmethod.keyboard.internal;

import android.content.res.TypedArray;
import com.android.inputmethod.latin.utils.ResourceUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class GestureStrokeRecognitionParams {
    public static final GestureStrokeRecognitionParams DEFAULT = new GestureStrokeRecognitionParams();
    public final float mDetectFastMoveSpeedThreshold;
    public final float mDynamicDistanceThresholdFrom;
    public final float mDynamicDistanceThresholdTo;
    public final int mDynamicThresholdDecayDuration;
    public final int mDynamicTimeThresholdFrom;
    public final int mDynamicTimeThresholdTo;
    public final int mRecognitionMinimumTime;
    public final float mRecognitionSpeedThreshold;
    public final float mSamplingMinimumDistance;
    public final int mStaticTimeThresholdAfterFastTyping;

    private GestureStrokeRecognitionParams() {
        this.mStaticTimeThresholdAfterFastTyping = 350;
        this.mDetectFastMoveSpeedThreshold = 1.5f;
        this.mDynamicThresholdDecayDuration = 450;
        this.mDynamicTimeThresholdFrom = 300;
        this.mDynamicTimeThresholdTo = 20;
        this.mDynamicDistanceThresholdFrom = 6.0f;
        this.mDynamicDistanceThresholdTo = 0.35f;
        this.mSamplingMinimumDistance = 0.16666667f;
        this.mRecognitionMinimumTime = 100;
        this.mRecognitionSpeedThreshold = 5.5f;
    }

    public GestureStrokeRecognitionParams(final TypedArray mainKeyboardViewAttr) {
        GestureStrokeRecognitionParams gestureStrokeRecognitionParams = DEFAULT;
        this.mStaticTimeThresholdAfterFastTyping = mainKeyboardViewAttr.getInt(21, gestureStrokeRecognitionParams.mStaticTimeThresholdAfterFastTyping);
        this.mDetectFastMoveSpeedThreshold = ResourceUtils.getFraction(mainKeyboardViewAttr, 3, gestureStrokeRecognitionParams.mDetectFastMoveSpeedThreshold);
        this.mDynamicThresholdDecayDuration = mainKeyboardViewAttr.getInt(6, gestureStrokeRecognitionParams.mDynamicThresholdDecayDuration);
        this.mDynamicTimeThresholdFrom = mainKeyboardViewAttr.getInt(7, gestureStrokeRecognitionParams.mDynamicTimeThresholdFrom);
        this.mDynamicTimeThresholdTo = mainKeyboardViewAttr.getInt(8, gestureStrokeRecognitionParams.mDynamicTimeThresholdTo);
        this.mDynamicDistanceThresholdFrom = ResourceUtils.getFraction(mainKeyboardViewAttr, 4, gestureStrokeRecognitionParams.mDynamicDistanceThresholdFrom);
        this.mDynamicDistanceThresholdTo = ResourceUtils.getFraction(mainKeyboardViewAttr, 5, gestureStrokeRecognitionParams.mDynamicDistanceThresholdTo);
        this.mSamplingMinimumDistance = ResourceUtils.getFraction(mainKeyboardViewAttr, 20, gestureStrokeRecognitionParams.mSamplingMinimumDistance);
        this.mRecognitionMinimumTime = mainKeyboardViewAttr.getInt(17, gestureStrokeRecognitionParams.mRecognitionMinimumTime);
        this.mRecognitionSpeedThreshold = ResourceUtils.getFraction(mainKeyboardViewAttr, 18, gestureStrokeRecognitionParams.mRecognitionSpeedThreshold);
    }
}
