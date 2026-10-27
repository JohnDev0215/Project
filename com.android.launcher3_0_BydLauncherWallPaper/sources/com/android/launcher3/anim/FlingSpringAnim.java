package com.android.launcher3.anim;

import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.FlingAnimation;
import androidx.dynamicanimation.animation.FloatPropertyCompat;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

/* JADX INFO: loaded from: classes4.dex */
public class FlingSpringAnim {
    private static final float FLING_FRICTION = 1.5f;
    private static final float SPRING_DAMPING = 0.8f;
    private static final float SPRING_STIFFNESS = 200.0f;
    private final FlingAnimation mFlingAnim;
    private SpringAnimation mSpringAnim;
    private float mTargetPosition;

    public <K> FlingSpringAnim(final K object, final FloatPropertyCompat<K> property, float startPosition, float targetPosition, float startVelocity, float minVisChange, float minValue, float maxValue, final float springVelocityFactor, final DynamicAnimation.OnAnimationEndListener onEndListener) {
        FlingAnimation maxValue2 = new FlingAnimation(object, property).setFriction(1.5f).setMinimumVisibleChange(minVisChange).setStartVelocity(startVelocity).setMinValue(minValue).setMaxValue(maxValue);
        this.mFlingAnim = maxValue2;
        this.mTargetPosition = targetPosition;
        maxValue2.addEndListener(new DynamicAnimation.OnAnimationEndListener() { // from class: com.android.launcher3.anim.-$$Lambda$FlingSpringAnim$DwoeAVs6DMnXoi20i1-4Gs3tVws
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
            public final void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f, float f2) {
                this.f$0.lambda$new$0$FlingSpringAnim(object, property, springVelocityFactor, onEndListener, dynamicAnimation, z, f, f2);
            }
        });
    }

    public /* synthetic */ void lambda$new$0$FlingSpringAnim(Object obj, FloatPropertyCompat floatPropertyCompat, float f, DynamicAnimation.OnAnimationEndListener onAnimationEndListener, DynamicAnimation dynamicAnimation, boolean z, float f2, float f3) {
        SpringAnimation spring = new SpringAnimation(obj, floatPropertyCompat).setStartValue(f2).setStartVelocity(f3 * f).setSpring(new SpringForce(this.mTargetPosition).setStiffness(200.0f).setDampingRatio(SPRING_DAMPING));
        this.mSpringAnim = spring;
        spring.addEndListener(onAnimationEndListener);
        this.mSpringAnim.animateToFinalPosition(this.mTargetPosition);
    }

    public float getTargetPosition() {
        return this.mTargetPosition;
    }

    public void updatePosition(float startPosition, float targetPosition) {
        this.mFlingAnim.setMinValue(Math.min(startPosition, targetPosition)).setMaxValue(Math.max(startPosition, targetPosition));
        this.mTargetPosition = targetPosition;
        SpringAnimation springAnimation = this.mSpringAnim;
        if (springAnimation != null) {
            springAnimation.animateToFinalPosition(targetPosition);
        }
    }

    public void start() {
        this.mFlingAnim.start();
    }

    public void end() {
        this.mFlingAnim.cancel();
        if (this.mSpringAnim.canSkipToEnd()) {
            this.mSpringAnim.skipToEnd();
        }
    }
}
