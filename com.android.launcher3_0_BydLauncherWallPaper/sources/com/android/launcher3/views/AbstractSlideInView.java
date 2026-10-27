package com.android.launcher3.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Interpolator;
import com.android.launcher3.AbstractFloatingView;
import com.android.launcher3.Launcher;
import com.android.launcher3.Utilities;
import com.android.launcher3.anim.Interpolators;
import com.android.launcher3.touch.SwipeDetector;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractSlideInView extends AbstractFloatingView implements SwipeDetector.Listener {
    protected static Property<AbstractSlideInView, Float> TRANSLATION_SHIFT = new Property<AbstractSlideInView, Float>(Float.class, "translationShift") { // from class: com.android.launcher3.views.AbstractSlideInView.1
        @Override // android.util.Property
        public Float get(AbstractSlideInView view) {
            return Float.valueOf(view.mTranslationShift);
        }

        @Override // android.util.Property
        public void set(AbstractSlideInView view, Float value) {
            view.setTranslationShift(value.floatValue());
        }
    };
    protected static final float TRANSLATION_SHIFT_CLOSED = 1.0f;
    protected static final float TRANSLATION_SHIFT_OPENED = 0.0f;
    protected View mContent;
    protected final Launcher mLauncher;
    protected boolean mNoIntercept;
    protected final ObjectAnimator mOpenCloseAnimator;
    protected Interpolator mScrollInterpolator;
    protected final SwipeDetector mSwipeDetector;
    protected float mTranslationShift;

    @Override // com.android.launcher3.touch.SwipeDetector.Listener
    public void onDragStart(boolean start) {
    }

    public AbstractSlideInView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mTranslationShift = 1.0f;
        this.mLauncher = Launcher.getLauncher(context);
        this.mScrollInterpolator = Interpolators.SCROLL_CUBIC;
        this.mSwipeDetector = new SwipeDetector(context, this, SwipeDetector.VERTICAL);
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, new PropertyValuesHolder[0]);
        this.mOpenCloseAnimator = objectAnimatorOfPropertyValuesHolder;
        objectAnimatorOfPropertyValuesHolder.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.views.AbstractSlideInView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                AbstractSlideInView.this.mSwipeDetector.finishedScrolling();
                AbstractSlideInView.this.announceAccessibilityChanges();
            }
        });
    }

    protected void setTranslationShift(float translationShift) {
        this.mTranslationShift = translationShift;
        this.mContent.setTranslationY(translationShift * r1.getHeight());
    }

    @Override // com.android.launcher3.util.TouchController
    public boolean onControllerInterceptTouchEvent(MotionEvent ev) {
        if (this.mNoIntercept) {
            return false;
        }
        this.mSwipeDetector.setDetectableScrollConditions(this.mSwipeDetector.isIdleState() ? 2 : 0, false);
        this.mSwipeDetector.onTouchEvent(ev);
        return this.mSwipeDetector.isDraggingOrSettling() || !getPopupContainer().isEventOverView(this.mContent, ev);
    }

    @Override // com.android.launcher3.AbstractFloatingView, com.android.launcher3.util.TouchController
    public boolean onControllerTouchEvent(MotionEvent ev) {
        this.mSwipeDetector.onTouchEvent(ev);
        if (ev.getAction() == 1 && this.mSwipeDetector.isIdleState() && !isOpeningAnimationRunning() && !getPopupContainer().isEventOverView(this.mContent, ev)) {
            close(true);
        }
        return true;
    }

    private boolean isOpeningAnimationRunning() {
        return this.mIsOpen && this.mOpenCloseAnimator.isRunning();
    }

    @Override // com.android.launcher3.touch.SwipeDetector.Listener
    public boolean onDrag(float displacement) {
        float height = this.mContent.getHeight();
        setTranslationShift(Utilities.boundToRange(displacement, 0.0f, height) / height);
        return true;
    }

    @Override // com.android.launcher3.touch.SwipeDetector.Listener
    public void onDragEnd(float velocity, boolean fling) {
        if ((fling && velocity > 0.0f) || this.mTranslationShift > 0.5f) {
            this.mScrollInterpolator = Interpolators.scrollInterpolatorForVelocity(velocity);
            this.mOpenCloseAnimator.setDuration(SwipeDetector.calculateDuration(velocity, 1.0f - this.mTranslationShift));
            close(true);
        } else {
            this.mOpenCloseAnimator.setValues(PropertyValuesHolder.ofFloat(TRANSLATION_SHIFT, 0.0f));
            this.mOpenCloseAnimator.setDuration(SwipeDetector.calculateDuration(velocity, this.mTranslationShift)).setInterpolator(Interpolators.DEACCEL);
            this.mOpenCloseAnimator.start();
        }
    }

    protected void handleClose(boolean animate, long defaultDuration) {
        if (this.mIsOpen && !animate) {
            this.mOpenCloseAnimator.cancel();
            setTranslationShift(1.0f);
            onCloseComplete();
        } else if (this.mIsOpen) {
            this.mOpenCloseAnimator.setValues(PropertyValuesHolder.ofFloat(TRANSLATION_SHIFT, 1.0f));
            this.mOpenCloseAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.launcher3.views.AbstractSlideInView.3
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    AbstractSlideInView.this.onCloseComplete();
                }
            });
            if (this.mSwipeDetector.isIdleState()) {
                this.mOpenCloseAnimator.setDuration(defaultDuration).setInterpolator(Interpolators.ACCEL);
            } else {
                this.mOpenCloseAnimator.setInterpolator(this.mScrollInterpolator);
            }
            this.mOpenCloseAnimator.start();
        }
    }

    protected void onCloseComplete() {
        this.mIsOpen = false;
        getPopupContainer().removeView(this);
    }

    protected BaseDragLayer getPopupContainer() {
        return this.mLauncher.getDragLayer();
    }
}
