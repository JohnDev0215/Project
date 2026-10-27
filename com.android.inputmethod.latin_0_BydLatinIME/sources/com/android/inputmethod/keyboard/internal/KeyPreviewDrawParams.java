package com.android.inputmethod.keyboard.internal;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.res.TypedArray;
import android.util.Property;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

/* JADX INFO: loaded from: classes2.dex */
public final class KeyPreviewDrawParams {
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    private static final DecelerateInterpolator DECELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final float KEY_PREVIEW_SHOW_UP_END_SCALE = 1.0f;
    private final int mDismissAnimatorResId;
    private int mDismissDuration;
    private float mDismissEndXScale;
    private float mDismissEndYScale;
    private boolean mHasCustomAnimationParams;
    private int mLingerTimeout;
    public final int mPreviewBackgroundResId;
    public final int mPreviewHeight;
    public final int mPreviewOffset;
    private boolean mShowPopup = true;
    private final int mShowUpAnimatorResId;
    private int mShowUpDuration;
    private float mShowUpStartXScale;
    private float mShowUpStartYScale;
    private int mVisibleHeight;
    private int mVisibleOffset;
    private int mVisibleWidth;

    public KeyPreviewDrawParams(final TypedArray mainKeyboardViewAttr) {
        this.mPreviewOffset = mainKeyboardViewAttr.getDimensionPixelOffset(41, 0);
        this.mPreviewHeight = mainKeyboardViewAttr.getDimensionPixelSize(39, 0);
        this.mPreviewBackgroundResId = mainKeyboardViewAttr.getResourceId(37, 0);
        this.mLingerTimeout = mainKeyboardViewAttr.getInt(40, 0);
        this.mShowUpAnimatorResId = mainKeyboardViewAttr.getResourceId(42, 0);
        this.mDismissAnimatorResId = mainKeyboardViewAttr.getResourceId(38, 0);
    }

    public void setVisibleOffset(final int previewVisibleOffset) {
        this.mVisibleOffset = previewVisibleOffset;
    }

    public int getVisibleOffset() {
        return this.mVisibleOffset;
    }

    public void setGeometry(final View previewTextView) {
        int measuredWidth = previewTextView.getMeasuredWidth();
        int i = this.mPreviewHeight;
        this.mVisibleWidth = (measuredWidth - previewTextView.getPaddingLeft()) - previewTextView.getPaddingRight();
        this.mVisibleHeight = (i - previewTextView.getPaddingTop()) - previewTextView.getPaddingBottom();
        setVisibleOffset(this.mPreviewOffset - previewTextView.getPaddingBottom());
    }

    public int getVisibleWidth() {
        return this.mVisibleWidth;
    }

    public int getVisibleHeight() {
        return this.mVisibleHeight;
    }

    public void setPopupEnabled(final boolean enabled, final int lingerTimeout) {
        this.mShowPopup = enabled;
        this.mLingerTimeout = lingerTimeout;
    }

    public boolean isPopupEnabled() {
        return this.mShowPopup;
    }

    public int getLingerTimeout() {
        return this.mLingerTimeout;
    }

    public void setAnimationParams(final boolean hasCustomAnimationParams, final float showUpStartXScale, final float showUpStartYScale, final int showUpDuration, final float dismissEndXScale, final float dismissEndYScale, final int dismissDuration) {
        this.mHasCustomAnimationParams = hasCustomAnimationParams;
        this.mShowUpStartXScale = showUpStartXScale;
        this.mShowUpStartYScale = showUpStartYScale;
        this.mShowUpDuration = showUpDuration;
        this.mDismissEndXScale = dismissEndXScale;
        this.mDismissEndYScale = dismissEndYScale;
        this.mDismissDuration = dismissDuration;
    }

    public Animator createShowUpAnimator(final View target) {
        if (this.mHasCustomAnimationParams) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(target, (Property<View, Float>) View.SCALE_X, this.mShowUpStartXScale, 1.0f);
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(target, (Property<View, Float>) View.SCALE_Y, this.mShowUpStartYScale, 1.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(objectAnimatorOfFloat).with(objectAnimatorOfFloat2);
            animatorSet.setDuration(this.mShowUpDuration);
            animatorSet.setInterpolator(DECELERATE_INTERPOLATOR);
            return animatorSet;
        }
        Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(target.getContext(), this.mShowUpAnimatorResId);
        animatorLoadAnimator.setTarget(target);
        animatorLoadAnimator.setInterpolator(DECELERATE_INTERPOLATOR);
        return animatorLoadAnimator;
    }

    public Animator createDismissAnimator(final View target) {
        if (this.mHasCustomAnimationParams) {
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(target, (Property<View, Float>) View.SCALE_X, this.mDismissEndXScale);
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(target, (Property<View, Float>) View.SCALE_Y, this.mDismissEndYScale);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.play(objectAnimatorOfFloat).with(objectAnimatorOfFloat2);
            animatorSet.setDuration(Math.min(this.mDismissDuration, this.mLingerTimeout));
            animatorSet.setInterpolator(ACCELERATE_INTERPOLATOR);
            return animatorSet;
        }
        Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(target.getContext(), this.mDismissAnimatorResId);
        animatorLoadAnimator.setTarget(target);
        animatorLoadAnimator.setInterpolator(ACCELERATE_INTERPOLATOR);
        return animatorLoadAnimator;
    }
}
