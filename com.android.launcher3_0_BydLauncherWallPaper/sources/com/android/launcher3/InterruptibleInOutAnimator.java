package com.android.launcher3;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.Property;

/* JADX INFO: loaded from: classes4.dex */
public class InterruptibleInOutAnimator {
    private static final int IN = 1;
    private static final int OUT = 2;
    private static final int STOPPED = 0;
    private static final Property<InterruptibleInOutAnimator, Float> VALUE = new Property<InterruptibleInOutAnimator, Float>(Float.TYPE, "value") { // from class: com.android.launcher3.InterruptibleInOutAnimator.1
        @Override // android.util.Property
        public Float get(InterruptibleInOutAnimator anim) {
            return Float.valueOf(anim.mValue);
        }

        @Override // android.util.Property
        public void set(InterruptibleInOutAnimator anim, Float value) {
            anim.mValue = value.floatValue();
        }
    };
    private ValueAnimator mAnimator;
    private long mOriginalDuration;
    private float mOriginalFromValue;
    private float mOriginalToValue;
    private float mValue;
    private boolean mFirstRun = true;
    private Object mTag = null;
    int mDirection = 0;

    public InterruptibleInOutAnimator(long duration, float fromValue, float toValue) {
        ObjectAnimator duration2 = ObjectAnimator.ofFloat(this, VALUE, fromValue, toValue).setDuration(duration);
        this.mAnimator = duration2;
        this.mOriginalDuration = duration;
        this.mOriginalFromValue = fromValue;
        this.mOriginalToValue = toValue;
        duration2.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.InterruptibleInOutAnimator.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                InterruptibleInOutAnimator.this.mDirection = 0;
            }
        });
    }

    private void animate(int direction) {
        long currentPlayTime = this.mAnimator.getCurrentPlayTime();
        float f = direction == 1 ? this.mOriginalToValue : this.mOriginalFromValue;
        float f2 = this.mFirstRun ? this.mOriginalFromValue : this.mValue;
        cancel();
        this.mDirection = direction;
        long j = this.mOriginalDuration;
        this.mAnimator.setDuration(Math.max(0L, Math.min(j - currentPlayTime, j)));
        this.mAnimator.setFloatValues(f2, f);
        this.mAnimator.start();
        this.mFirstRun = false;
    }

    public void cancel() {
        this.mAnimator.cancel();
        this.mDirection = 0;
    }

    public void end() {
        this.mAnimator.end();
        this.mDirection = 0;
    }

    public boolean isStopped() {
        return this.mDirection == 0;
    }

    public void animateIn() {
        animate(1);
    }

    public void animateOut() {
        animate(2);
    }

    public void setTag(Object tag) {
        this.mTag = tag;
    }

    public Object getTag() {
        return this.mTag;
    }

    public ValueAnimator getAnimator() {
        return this.mAnimator;
    }
}
