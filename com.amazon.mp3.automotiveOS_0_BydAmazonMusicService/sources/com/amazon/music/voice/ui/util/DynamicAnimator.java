package com.amazon.music.voice.ui.util;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import androidx.annotation.NonNull;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicAnimator {
    private boolean mAnimate;
    private final Callback mCallback;
    private float mCurrentPosition;
    private long mLastUpdateTime;
    private final float mMaxSpeed;
    private boolean mMessagePosted;
    private float mTargetPosition;
    private final int mUpdateInterval;
    private float mVelocity;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Runnable mAnimationRunner = new Runnable() { // from class: com.amazon.music.voice.ui.util.DynamicAnimator.1
        @Override // java.lang.Runnable
        public void run() {
            DynamicAnimator.this.mMessagePosted = false;
            if (DynamicAnimator.this.mAnimate) {
                DynamicAnimator.this.animate();
            }
        }
    };

    public interface Callback {
        void onPositionChanged(float f, float f2);
    }

    public DynamicAnimator(@NonNull Callback callback, float f, int i) {
        Validate.notNull(callback);
        Validate.isTrue(f > 0.0f);
        Validate.isTrue(i > 0);
        this.mCallback = callback;
        this.mMaxSpeed = f;
        this.mUpdateInterval = i;
    }

    public void snapToPosition(float f) {
        this.mTargetPosition = f;
        this.mVelocity = 0.0f;
        setPosition(f);
    }

    public void setTargetPosition(float f) {
        this.mTargetPosition = f;
        if (!this.mAnimate || this.mMessagePosted) {
            return;
        }
        this.mLastUpdateTime = SystemClock.elapsedRealtime();
        animate();
    }

    public void setAnimate(boolean z) {
        if (this.mAnimate != z) {
            this.mAnimate = z;
            if (!z || this.mMessagePosted) {
                return;
            }
            this.mLastUpdateTime = SystemClock.elapsedRealtime();
            animate();
        }
    }

    public float getCurrentPosition() {
        return this.mCurrentPosition;
    }

    public float getTargetPosition() {
        return this.mTargetPosition;
    }

    public float getVelocity() {
        return this.mVelocity;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void animate() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        advanceMillis(jElapsedRealtime - this.mLastUpdateTime);
        this.mLastUpdateTime = jElapsedRealtime;
        if (this.mCurrentPosition == this.mTargetPosition && this.mVelocity == 0.0f) {
            return;
        }
        this.mMessagePosted = true;
        this.mHandler.postDelayed(this.mAnimationRunner, this.mUpdateInterval);
    }

    private void advanceMillis(long j) {
        float f = this.mMaxSpeed;
        float f2 = j * f;
        float f3 = this.mCurrentPosition;
        float f4 = this.mTargetPosition;
        if (f3 < f4 - f2) {
            this.mVelocity = f;
            setPosition(f3 + f2);
        } else if (f3 > f4 + f2) {
            this.mVelocity = -f;
            setPosition(f3 - f2);
        } else {
            this.mVelocity = 0.0f;
            setPosition(f4);
        }
    }

    private void setPosition(float f) {
        float f2 = this.mCurrentPosition;
        if (f2 != f) {
            this.mCurrentPosition = f;
            this.mCallback.onPositionChanged(f2, f);
        }
    }
}
