package com.amazon.music.skyfire.ui.managers;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;

/* JADX INFO: loaded from: classes4.dex */
public class ObjectAnimatorManager {
    private ObjectAnimator animator;
    private Animator.AnimatorListener animatorListener;
    private TimeInterpolator interpolator;
    private String propertyName;
    private Object target;
    private long totalDurationMs;
    private ValueType valueType;
    private int[] intValues = {0, 0};
    private float[] floatValues = {0.0f, 0.0f};

    private enum ValueType {
        FLOAT,
        INT
    }

    public ObjectAnimatorManager(Object obj, String str) {
        this.target = obj;
        this.propertyName = str;
    }

    public void start() {
        recreateAnimator();
        this.animator.start();
    }

    public void stop() {
        ObjectAnimator objectAnimator = this.animator;
        if (objectAnimator != null) {
            objectAnimator.removeAllListeners();
            this.animator.cancel();
            this.animator = null;
        }
    }

    public void setListener(Animator.AnimatorListener animatorListener) {
        this.animatorListener = animatorListener;
    }

    public void setInterpolator(TimeInterpolator timeInterpolator) {
        this.interpolator = timeInterpolator;
    }

    public void setIntValues(int... iArr) {
        this.intValues = iArr;
        this.valueType = ValueType.INT;
    }

    public void setFloatValues(float... fArr) {
        this.floatValues = fArr;
        this.valueType = ValueType.FLOAT;
    }

    public void setDuration(long j) {
        this.totalDurationMs = j;
    }

    private void recreateAnimator() {
        stop();
        if (this.valueType.equals(ValueType.FLOAT)) {
            this.animator = ObjectAnimator.ofFloat(this.target, this.propertyName, this.floatValues);
        } else {
            this.animator = ObjectAnimator.ofInt(this.target, this.propertyName, this.intValues);
        }
        this.animator.setDuration(this.totalDurationMs);
        TimeInterpolator timeInterpolator = this.interpolator;
        if (timeInterpolator != null) {
            this.animator.setInterpolator(timeInterpolator);
        }
        Animator.AnimatorListener animatorListener = this.animatorListener;
        if (animatorListener != null) {
            this.animator.addListener(animatorListener);
        }
    }
}
