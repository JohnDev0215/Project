package com.android.launcher3.keyboard;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.RectEvaluator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Property;
import android.view.View;
import com.android.launcher3.R;

/* JADX INFO: loaded from: classes4.dex */
public abstract class FocusIndicatorHelper implements View.OnFocusChangeListener, ValueAnimator.AnimatorUpdateListener {
    private static final long ANIM_DURATION = 150;
    private static final float MIN_VISIBLE_ALPHA = 0.2f;
    private float mAlpha;
    private final View mContainer;
    private ObjectAnimator mCurrentAnimation;
    private View mCurrentView;
    private final Rect mDirtyRect = new Rect();
    private boolean mIsDirty = false;
    private View mLastFocusedView;
    private final int mMaxAlpha;
    private final Paint mPaint;
    private float mShift;
    private View mTargetView;
    public static final Property<FocusIndicatorHelper, Float> ALPHA = new Property<FocusIndicatorHelper, Float>(Float.TYPE, "alpha") { // from class: com.android.launcher3.keyboard.FocusIndicatorHelper.1
        @Override // android.util.Property
        public void set(FocusIndicatorHelper object, Float value) {
            object.setAlpha(value.floatValue());
        }

        @Override // android.util.Property
        public Float get(FocusIndicatorHelper object) {
            return Float.valueOf(object.mAlpha);
        }
    };
    public static final Property<FocusIndicatorHelper, Float> SHIFT = new Property<FocusIndicatorHelper, Float>(Float.TYPE, "shift") { // from class: com.android.launcher3.keyboard.FocusIndicatorHelper.2
        @Override // android.util.Property
        public void set(FocusIndicatorHelper object, Float value) {
            object.mShift = value.floatValue();
        }

        @Override // android.util.Property
        public Float get(FocusIndicatorHelper object) {
            return Float.valueOf(object.mShift);
        }
    };
    private static final RectEvaluator RECT_EVALUATOR = new RectEvaluator(new Rect());
    private static final Rect sTempRect1 = new Rect();
    private static final Rect sTempRect2 = new Rect();

    public abstract void viewToRect(View v, Rect outRect);

    public FocusIndicatorHelper(View container) {
        this.mContainer = container;
        Paint paint = new Paint(1);
        this.mPaint = paint;
        int color = container.getResources().getColor(R.color.focused_background);
        this.mMaxAlpha = Color.alpha(color);
        paint.setColor(color | (-16777216));
        setAlpha(0.0f);
        this.mShift = 0.0f;
    }

    protected void setAlpha(float alpha) {
        this.mAlpha = alpha;
        this.mPaint.setAlpha((int) (alpha * this.mMaxAlpha));
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public void onAnimationUpdate(ValueAnimator animation) {
        invalidateDirty();
    }

    protected void invalidateDirty() {
        if (this.mIsDirty) {
            this.mContainer.invalidate(this.mDirtyRect);
            this.mIsDirty = false;
        }
        Rect drawRect = getDrawRect();
        if (drawRect != null) {
            this.mContainer.invalidate(drawRect);
        }
    }

    public void draw(Canvas c) {
        Rect drawRect;
        if (this.mAlpha <= 0.0f || (drawRect = getDrawRect()) == null) {
            return;
        }
        this.mDirtyRect.set(drawRect);
        c.drawRect(this.mDirtyRect, this.mPaint);
        this.mIsDirty = true;
    }

    private Rect getDrawRect() {
        View view;
        View view2 = this.mCurrentView;
        if (view2 == null || !view2.isAttachedToWindow()) {
            return null;
        }
        View view3 = this.mCurrentView;
        Rect rect = sTempRect1;
        viewToRect(view3, rect);
        if (this.mShift <= 0.0f || (view = this.mTargetView) == null) {
            return rect;
        }
        Rect rect2 = sTempRect2;
        viewToRect(view, rect2);
        return RECT_EVALUATOR.evaluate(this.mShift, rect, rect2);
    }

    @Override // android.view.View.OnFocusChangeListener
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            endCurrentAnimation();
            if (this.mAlpha > 0.2f) {
                this.mTargetView = v;
                ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat(ALPHA, 1.0f), PropertyValuesHolder.ofFloat(SHIFT, 1.0f));
                this.mCurrentAnimation = objectAnimatorOfPropertyValuesHolder;
                objectAnimatorOfPropertyValuesHolder.addListener(new ViewSetListener(v, true));
            } else {
                setCurrentView(v);
                this.mCurrentAnimation = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat(ALPHA, 1.0f));
            }
            this.mLastFocusedView = v;
        } else if (this.mLastFocusedView == v) {
            this.mLastFocusedView = null;
            endCurrentAnimation();
            ObjectAnimator objectAnimatorOfPropertyValuesHolder2 = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofFloat(ALPHA, 0.0f));
            this.mCurrentAnimation = objectAnimatorOfPropertyValuesHolder2;
            objectAnimatorOfPropertyValuesHolder2.addListener(new ViewSetListener(null, false));
        }
        invalidateDirty();
        if (!hasFocus) {
            v = null;
        }
        this.mLastFocusedView = v;
        ObjectAnimator objectAnimator = this.mCurrentAnimation;
        if (objectAnimator != null) {
            objectAnimator.addUpdateListener(this);
            this.mCurrentAnimation.setDuration(ANIM_DURATION).start();
        }
    }

    protected void endCurrentAnimation() {
        ObjectAnimator objectAnimator = this.mCurrentAnimation;
        if (objectAnimator != null) {
            objectAnimator.cancel();
            this.mCurrentAnimation = null;
        }
    }

    protected void setCurrentView(View v) {
        this.mCurrentView = v;
        this.mShift = 0.0f;
        this.mTargetView = null;
    }

    private class ViewSetListener extends AnimatorListenerAdapter {
        private final boolean mCallOnCancel;
        private boolean mCalled = false;
        private final View mViewToSet;

        public ViewSetListener(View v, boolean callOnCancel) {
            this.mViewToSet = v;
            this.mCallOnCancel = callOnCancel;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animation) {
            if (this.mCallOnCancel) {
                return;
            }
            this.mCalled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
            if (this.mCalled) {
                return;
            }
            FocusIndicatorHelper.this.setCurrentView(this.mViewToSet);
            this.mCalled = true;
        }
    }

    public static class SimpleFocusIndicatorHelper extends FocusIndicatorHelper {
        public SimpleFocusIndicatorHelper(View container) {
            super(container);
        }

        @Override // com.android.launcher3.keyboard.FocusIndicatorHelper
        public void viewToRect(View v, Rect outRect) {
            outRect.set(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        }
    }
}
