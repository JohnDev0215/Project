package com.byd.card.drag;

import android.os.Handler;
import android.view.animation.Animation;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FloatValueHolder;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

/* JADX INFO: loaded from: classes.dex */
public class TranslateSpringAnimation {
    private volatile boolean isRunning;
    private int mAniX;
    private int mAniY;
    private Handler mHandle;
    private Animation.AnimationListener mListener;
    private int mStartOffset;
    private final SpringAnimation mXSpringAnimation;
    private final SpringAnimation mYSpringAnimation;

    public TranslateSpringAnimation(int fromX, int toX, int fromY, int toY) {
        SpringAnimation spring = new SpringAnimation(new FloatValueHolder()).setSpring(new SpringForce().setDampingRatio(1.0f).setStiffness(1500.0f));
        this.mXSpringAnimation = spring;
        SpringAnimation spring2 = new SpringAnimation(new FloatValueHolder()).setSpring(new SpringForce().setDampingRatio(1.0f).setStiffness(1500.0f));
        this.mYSpringAnimation = spring2;
        this.isRunning = false;
        spring.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() { // from class: com.byd.card.drag.TranslateSpringAnimation.1
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener
            public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
                TranslateSpringAnimation.this.mAniX = (int) value;
            }
        });
        spring.addEndListener(new DynamicAnimation.OnAnimationEndListener() { // from class: com.byd.card.drag.TranslateSpringAnimation.2
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                TranslateSpringAnimation.this.checkEndAnimation();
            }
        });
        spring2.addUpdateListener(new DynamicAnimation.OnAnimationUpdateListener() { // from class: com.byd.card.drag.TranslateSpringAnimation.3
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationUpdateListener
            public void onAnimationUpdate(DynamicAnimation animation, float value, float velocity) {
                TranslateSpringAnimation.this.mAniY = (int) value;
            }
        });
        spring2.addEndListener(new DynamicAnimation.OnAnimationEndListener() { // from class: com.byd.card.drag.TranslateSpringAnimation.4
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                TranslateSpringAnimation.this.checkEndAnimation();
            }
        });
        this.mAniX = fromX;
        this.mAniY = fromY;
        spring.setStartValue(fromX);
        spring.getSpring().setFinalPosition(toX);
        spring2.setStartValue(fromY);
        spring2.getSpring().setFinalPosition(toY);
    }

    public TranslateSpringAnimation setHandler(Handler handler) {
        this.mHandle = handler;
        return this;
    }

    public TranslateSpringAnimation setAnimationListener(Animation.AnimationListener listener) {
        this.mListener = listener;
        return this;
    }

    public TranslateSpringAnimation setStartOffset(long startOffset) {
        this.mStartOffset = (int) startOffset;
        return this;
    }

    public TranslateSpringAnimation start() {
        this.isRunning = true;
        Animation.AnimationListener animationListener = this.mListener;
        if (animationListener != null) {
            animationListener.onAnimationStart(null);
        }
        Handler handler = this.mHandle;
        if (handler == null || this.mStartOffset == 0) {
            this.mXSpringAnimation.start();
            this.mYSpringAnimation.start();
        } else {
            handler.postDelayed(new Runnable() { // from class: com.byd.card.drag.TranslateSpringAnimation.5
                @Override // java.lang.Runnable
                public void run() {
                    TranslateSpringAnimation.this.mXSpringAnimation.start();
                    TranslateSpringAnimation.this.mYSpringAnimation.start();
                }
            }, this.mStartOffset);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkEndAnimation() {
        if (this.mXSpringAnimation.isRunning() || this.mYSpringAnimation.isRunning()) {
            return;
        }
        this.isRunning = false;
        Animation.AnimationListener animationListener = this.mListener;
        if (animationListener != null) {
            animationListener.onAnimationEnd(null);
        }
    }

    public boolean isRunning() {
        return this.isRunning || this.mXSpringAnimation.isRunning() || this.mYSpringAnimation.isRunning();
    }

    public int getAniX() {
        return this.mAniX;
    }

    public int getAniY() {
        return this.mAniY;
    }

    public void destroy() {
        this.mHandle = null;
        this.mListener = null;
    }
}
