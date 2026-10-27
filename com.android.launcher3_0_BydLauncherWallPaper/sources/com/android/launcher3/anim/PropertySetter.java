package com.android.launcher3.anim;

import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.util.Property;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public class PropertySetter {
    public static final PropertySetter NO_ANIM_PROPERTY_SETTER = new PropertySetter();

    public void setViewAlpha(View view, float alpha, TimeInterpolator interpolator) {
        if (view != null) {
            view.setAlpha(alpha);
            AlphaUpdateListener.updateVisibility(view);
        }
    }

    public <T> void setFloat(T target, Property<T, Float> property, float value, TimeInterpolator interpolator) {
        property.set(target, Float.valueOf(value));
    }

    public <T> void setInt(T target, Property<T, Integer> property, int value, TimeInterpolator interpolator) {
        property.set(target, Integer.valueOf(value));
    }

    public static class AnimatedPropertySetter extends PropertySetter {
        private final long mDuration;
        private final AnimatorSetBuilder mStateAnimator;

        public AnimatedPropertySetter(long duration, AnimatorSetBuilder builder) {
            this.mDuration = duration;
            this.mStateAnimator = builder;
        }

        @Override // com.android.launcher3.anim.PropertySetter
        public void setViewAlpha(View view, float alpha, TimeInterpolator interpolator) {
            if (view == null || view.getAlpha() == alpha) {
                return;
            }
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, View.ALPHA, alpha);
            objectAnimatorOfFloat.addListener(new AlphaUpdateListener(view));
            objectAnimatorOfFloat.setDuration(this.mDuration).setInterpolator(interpolator);
            this.mStateAnimator.play(objectAnimatorOfFloat);
        }

        @Override // com.android.launcher3.anim.PropertySetter
        public <T> void setFloat(T target, Property<T, Float> property, float value, TimeInterpolator interpolator) {
            if (property.get(target).floatValue() == value) {
                return;
            }
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(target, property, value);
            objectAnimatorOfFloat.setDuration(this.mDuration).setInterpolator(interpolator);
            this.mStateAnimator.play(objectAnimatorOfFloat);
        }

        @Override // com.android.launcher3.anim.PropertySetter
        public <T> void setInt(T target, Property<T, Integer> property, int value, TimeInterpolator interpolator) {
            if (property.get(target).intValue() == value) {
                return;
            }
            ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(target, property, value);
            objectAnimatorOfInt.setDuration(this.mDuration).setInterpolator(interpolator);
            this.mStateAnimator.play(objectAnimatorOfInt);
        }
    }
}
