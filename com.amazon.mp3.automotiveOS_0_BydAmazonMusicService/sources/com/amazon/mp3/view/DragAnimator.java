package com.amazon.mp3.view;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;

/* JADX INFO: loaded from: classes4.dex */
public class DragAnimator {
    private View mView;
    private int mVisibility;
    private float mCenterX = 0.0f;
    private float mCenterY = 0.0f;
    private float mMinDragDetectionBufferX = -1.0f;
    private float mMinDragDetectionBufferY = -1.0f;
    private float mDragBufferDistanceX = 0.0f;
    private float mDragBufferDistanceY = 0.0f;
    private long mResetAnimationDuration = 0;

    public void initView(final View view) {
        this.mView = view;
        calculateViewPosition();
        init();
    }

    public void drag(final float x, final float y, final float alpha) {
        if (this.mView != null) {
            if (Math.abs(x) > this.mDragBufferDistanceX || Math.abs(y) > this.mDragBufferDistanceY) {
                this.mView.setX(this.mCenterX + x);
                this.mView.setY(this.mCenterY + y);
                this.mView.setAlpha(alpha);
            }
        }
    }

    public void setMinDragDetectionBufferX(final float minDragDetectionBufferX) {
        this.mMinDragDetectionBufferX = minDragDetectionBufferX;
    }

    public void setMinDragDetectionBufferY(final float minDragDetectionBufferY) {
        this.mMinDragDetectionBufferY = minDragDetectionBufferY;
    }

    public void resetView() {
        View view = this.mView;
        if (view != null) {
            view.setVisibility(this.mVisibility);
            this.mView.animate().x(this.mCenterX).y(this.mCenterY).alpha(1.0f).setInterpolator(new LinearInterpolator()).setDuration(this.mResetAnimationDuration).start();
        }
    }

    public void setView(View view) {
        this.mView = view;
    }

    private void init() {
        if (this.mMinDragDetectionBufferX < 0.0f) {
            this.mMinDragDetectionBufferX = 15.0f;
        }
        if (this.mMinDragDetectionBufferY < 0.0f) {
            this.mMinDragDetectionBufferY = 15.0f;
        }
        View view = this.mView;
        if (view != null) {
            this.mVisibility = view.getVisibility();
            DisplayMetrics displayMetrics = this.mView.getResources().getDisplayMetrics();
            this.mDragBufferDistanceX = TypedValue.applyDimension(1, this.mMinDragDetectionBufferX, displayMetrics);
            this.mDragBufferDistanceY = TypedValue.applyDimension(1, this.mMinDragDetectionBufferY, displayMetrics);
        }
    }

    private void calculateViewPosition() {
        View view = this.mView;
        if (view != null) {
            this.mCenterX = view.getX();
            this.mCenterY = this.mView.getY();
        }
    }
}
