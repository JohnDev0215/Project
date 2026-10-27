package com.amazon.music.voice.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.amazon.music.voice.ui.util.DynamicAnimator;

/* JADX INFO: loaded from: classes4.dex */
public class SmoothResizeFrameLayout extends FrameLayout {
    private boolean mAnimate;
    private DynamicAnimator mHeightAnimator;
    private DynamicAnimator mWidthAnimator;

    public SmoothResizeFrameLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        final Runnable runnable = new Runnable() { // from class: com.amazon.music.voice.ui.widget.SmoothResizeFrameLayout.1
            @Override // java.lang.Runnable
            public void run() {
                SmoothResizeFrameLayout.this.requestLayout();
            }
        };
        DynamicAnimator.Callback callback = new DynamicAnimator.Callback() { // from class: com.amazon.music.voice.ui.widget.SmoothResizeFrameLayout.2
            @Override // com.amazon.music.voice.ui.util.DynamicAnimator.Callback
            public void onPositionChanged(float f, float f2) {
                SmoothResizeFrameLayout.this.post(runnable);
            }
        };
        float f = getContext().getResources().getDisplayMetrics().density * 1.0f;
        this.mWidthAnimator = new DynamicAnimator(callback, f, 16);
        this.mHeightAnimator = new DynamicAnimator(callback, f, 16);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        int mode = View.MeasureSpec.getMode(i);
        if (this.mAnimate && mode != 1073741824) {
            this.mWidthAnimator.setTargetPosition(getMeasuredWidth());
            setMeasuredDimension((int) this.mWidthAnimator.getCurrentPosition(), getMeasuredHeight());
        }
        int mode2 = View.MeasureSpec.getMode(i2);
        if (!this.mAnimate || mode2 == 1073741824) {
            return;
        }
        this.mHeightAnimator.setTargetPosition(getMeasuredHeight());
        setMeasuredDimension(getMeasuredWidth(), (int) this.mHeightAnimator.getCurrentPosition());
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mAnimate) {
            return;
        }
        this.mAnimate = true;
        this.mWidthAnimator.snapToPosition(getMeasuredWidth());
        this.mHeightAnimator.snapToPosition(getMeasuredHeight());
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mWidthAnimator.setAnimate(true);
        this.mHeightAnimator.setAnimate(true);
        requestLayout();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mWidthAnimator.setAnimate(false);
        this.mHeightAnimator.setAnimate(false);
        this.mAnimate = false;
    }
}
