package com.android.launcher3.util;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.graphics.Rect;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import com.android.launcher3.ButtonDropTarget;
import com.android.launcher3.DropTarget;
import com.android.launcher3.Launcher;
import com.android.launcher3.LauncherState;
import com.android.launcher3.dragndrop.DragLayer;
import com.android.launcher3.dragndrop.DragOptions;
import com.android.launcher3.dragndrop.DragView;

/* JADX INFO: loaded from: classes4.dex */
public class FlingAnimation implements ValueAnimator.AnimatorUpdateListener, Runnable {
    private static final int DRAG_END_DELAY = 300;
    private static final float MAX_ACCELERATION = 0.5f;
    protected float mAX;
    protected float mAY;
    protected final TimeInterpolator mAlphaInterpolator = new DecelerateInterpolator(0.75f);
    protected float mAnimationTimeFraction;
    protected final DragLayer mDragLayer;
    protected final DropTarget.DragObject mDragObject;
    protected final DragOptions mDragOptions;
    private final ButtonDropTarget mDropTarget;
    protected int mDuration;
    protected Rect mFrom;
    protected Rect mIconRect;
    private final Launcher mLauncher;
    protected final float mUX;
    protected final float mUY;

    public FlingAnimation(DropTarget.DragObject d, PointF vel, ButtonDropTarget dropTarget, Launcher launcher, DragOptions options) {
        this.mDropTarget = dropTarget;
        this.mLauncher = launcher;
        this.mDragObject = d;
        this.mUX = vel.x / 1000.0f;
        this.mUY = vel.y / 1000.0f;
        this.mDragLayer = launcher.getDragLayer();
        this.mDragOptions = options;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.mIconRect = this.mDropTarget.getIconRect(this.mDragObject);
        this.mFrom = new Rect();
        this.mDragLayer.getViewRectRelativeToSelf(this.mDragObject.dragView, this.mFrom);
        float scaleX = this.mDragObject.dragView.getScaleX() - 1.0f;
        float measuredWidth = (this.mDragObject.dragView.getMeasuredWidth() * scaleX) / 2.0f;
        float measuredHeight = (scaleX * this.mDragObject.dragView.getMeasuredHeight()) / 2.0f;
        this.mFrom.left = (int) (r2.left + measuredWidth);
        this.mFrom.right = (int) (r2.right - measuredWidth);
        this.mFrom.top = (int) (r1.top + measuredHeight);
        this.mFrom.bottom = (int) (r1.bottom - measuredHeight);
        int iInitFlingUpDuration = Math.abs(this.mUY) > Math.abs(this.mUX) ? initFlingUpDuration() : initFlingLeftDuration();
        this.mDuration = iInitFlingUpDuration;
        this.mAnimationTimeFraction = iInitFlingUpDuration / (iInitFlingUpDuration + 300);
        this.mDragObject.dragView.setColor(0);
        final int i = this.mDuration + 300;
        final long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        TimeInterpolator timeInterpolator = new TimeInterpolator() { // from class: com.android.launcher3.util.FlingAnimation.1
            private int mCount = -1;
            private float mOffset = 0.0f;

            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float t) {
                int i2 = this.mCount;
                if (i2 < 0) {
                    this.mCount = i2 + 1;
                } else if (i2 == 0) {
                    this.mOffset = Math.min(0.5f, (AnimationUtils.currentAnimationTimeMillis() - jCurrentAnimationTimeMillis) / i);
                    this.mCount++;
                }
                return Math.min(1.0f, this.mOffset + t);
            }
        };
        Runnable runnable = new Runnable() { // from class: com.android.launcher3.util.FlingAnimation.2
            @Override // java.lang.Runnable
            public void run() {
                FlingAnimation.this.mLauncher.getStateManager().goToState(LauncherState.NORMAL);
                FlingAnimation.this.mDropTarget.completeDrop(FlingAnimation.this.mDragObject);
            }
        };
        this.mDropTarget.onDrop(this.mDragObject, this.mDragOptions);
        this.mDragLayer.animateView(this.mDragObject.dragView, this, i, timeInterpolator, runnable, 0, null);
    }

    protected int initFlingUpDuration() {
        float f = -this.mFrom.bottom;
        float f2 = this.mUY;
        float f3 = (f2 * f2) + (f * 2.0f * 0.5f);
        if (f3 >= 0.0f) {
            this.mAY = 0.5f;
        } else {
            this.mAY = (f2 * f2) / ((-f) * 2.0f);
            f3 = 0.0f;
        }
        double dSqrt = (((double) (-f2)) - Math.sqrt(f3)) / ((double) this.mAY);
        this.mAX = (float) (((((double) ((-this.mFrom.exactCenterX()) + this.mIconRect.exactCenterX())) - (((double) this.mUX) * dSqrt)) * 2.0d) / (dSqrt * dSqrt));
        return (int) Math.round(dSqrt);
    }

    protected int initFlingLeftDuration() {
        float f = -this.mFrom.right;
        float f2 = this.mUX;
        float f3 = (f2 * f2) + (f * 2.0f * 0.5f);
        if (f3 >= 0.0f) {
            this.mAX = 0.5f;
        } else {
            this.mAX = (f2 * f2) / ((-f) * 2.0f);
            f3 = 0.0f;
        }
        double dSqrt = (((double) (-f2)) - Math.sqrt(f3)) / ((double) this.mAX);
        this.mAY = (float) (((((double) ((-this.mFrom.exactCenterY()) + this.mIconRect.exactCenterY())) - (((double) this.mUY) * dSqrt)) * 2.0d) / (dSqrt * dSqrt));
        return (int) Math.round(dSqrt);
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator animation) {
        float animatedFraction = animation.getAnimatedFraction();
        float f = this.mAnimationTimeFraction;
        float f2 = animatedFraction > f ? 1.0f : animatedFraction / f;
        DragView dragView = (DragView) this.mDragLayer.getAnimatedView();
        float f3 = this.mDuration * f2;
        dragView.setTranslationX((this.mUX * f3) + this.mFrom.left + (((this.mAX * f3) * f3) / 2.0f));
        dragView.setTranslationY((this.mUY * f3) + this.mFrom.top + (((this.mAY * f3) * f3) / 2.0f));
        dragView.setAlpha(1.0f - this.mAlphaInterpolator.getInterpolation(f2));
    }
}
