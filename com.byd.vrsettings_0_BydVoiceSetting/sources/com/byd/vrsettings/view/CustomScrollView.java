package com.byd.vrsettings.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.widget.NestedScrollView;
import com.byd.vrsettings.utils.RLog;

/* JADX INFO: loaded from: classes.dex */
public class CustomScrollView extends NestedScrollView {
    private int INVALID_POINTER;
    private int RESET_DURATION;
    private float SCROLL_RATIO;
    private int mActivePointerId;
    private int mDragStartY;
    private View mDragView;
    private boolean mIsBeingDragged;
    private boolean mIsReset;
    private int mLastMotionY;
    private OnScrollListener mOnScrollListener;
    private ValueAnimator mResetAnimation;

    public interface OnScrollListener {
        void onScrollY(int i);
    }

    public CustomScrollView(Context context) {
        super(context);
        this.INVALID_POINTER = -1;
        this.RESET_DURATION = 300;
        this.SCROLL_RATIO = 0.5f;
        this.mLastMotionY = 0;
        this.mActivePointerId = -1;
        this.mIsBeingDragged = false;
        this.mIsReset = false;
        this.mDragStartY = 0;
        this.mResetAnimation = null;
        this.mOnScrollListener = null;
    }

    public CustomScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.INVALID_POINTER = -1;
        this.RESET_DURATION = 300;
        this.SCROLL_RATIO = 0.5f;
        this.mLastMotionY = 0;
        this.mActivePointerId = -1;
        this.mIsBeingDragged = false;
        this.mIsReset = false;
        this.mDragStartY = 0;
        this.mResetAnimation = null;
        this.mOnScrollListener = null;
    }

    public CustomScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.INVALID_POINTER = -1;
        this.RESET_DURATION = 300;
        this.SCROLL_RATIO = 0.5f;
        this.mLastMotionY = 0;
        this.mActivePointerId = -1;
        this.mIsBeingDragged = false;
        this.mIsReset = false;
        this.mDragStartY = 0;
        this.mResetAnimation = null;
        this.mOnScrollListener = null;
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        initView();
        super.onFinishInflate();
    }

    private void initView() {
        setOverScrollMode(2);
        if (getChildAt(0) != null) {
            this.mDragView = getChildAt(0);
        }
        setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: com.byd.vrsettings.view.CustomScrollView.1
            @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                CustomScrollView.this.mOnScrollListener.onScrollY(i2 + CustomScrollView.this.mDragView.getScrollY());
            }
        });
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.mOnScrollListener = onScrollListener;
    }

    @Override // androidx.core.widget.NestedScrollView, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            RLog.i("wjn", "onInterceptTouchEvent按下");
            this.mLastMotionY = (int) motionEvent.getY();
            this.mActivePointerId = motionEvent.getPointerId(0);
            if (this.mIsReset) {
                this.mResetAnimation.cancel();
                this.mIsReset = false;
                this.mIsBeingDragged = true;
            }
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x00d8  */
    @Override // androidx.core.widget.NestedScrollView, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            Method dump skipped, instruction units count: 251
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.vrsettings.view.CustomScrollView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    private void resetDragView() {
        this.mIsReset = true;
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.mDragView.getScrollY(), 0);
        this.mResetAnimation = valueAnimatorOfInt;
        valueAnimatorOfInt.setDuration(this.RESET_DURATION);
        this.mResetAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.byd.vrsettings.view.CustomScrollView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                CustomScrollView.this.mDragView.scrollTo(CustomScrollView.this.mDragView.getScrollX(), ((Integer) valueAnimator.getAnimatedValue()).intValue());
                CustomScrollView.this.mOnScrollListener.onScrollY(CustomScrollView.this.mDragView.getScrollY() + CustomScrollView.this.getScrollY());
                if (CustomScrollView.this.mDragView.getScrollY() == 0) {
                    CustomScrollView.this.mIsReset = false;
                }
            }
        });
        this.mResetAnimation.start();
    }

    @Override // androidx.core.widget.NestedScrollView, android.view.View
    protected void onOverScrolled(int i, int i2, boolean z, boolean z2) {
        if (this.mIsBeingDragged || this.mIsReset) {
            return;
        }
        super.onOverScrolled(i, i2, z, z2);
    }
}
