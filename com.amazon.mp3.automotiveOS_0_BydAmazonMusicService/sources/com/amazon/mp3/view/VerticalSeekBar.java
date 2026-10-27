package com.amazon.mp3.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.SeekBar;

/* JADX INFO: loaded from: classes4.dex */
public class VerticalSeekBar extends SeekBar {
    public VerticalSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(h, w, oldh, oldw);
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(heightMeasureSpec, widthMeasureSpec);
        setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth());
    }

    @Override // android.widget.AbsSeekBar, android.widget.ProgressBar, android.view.View
    protected void onDraw(Canvas c) {
        c.rotate(-90.0f);
        c.translate(-getHeight(), 0.0f);
        super.onDraw(c);
    }

    @Override // android.widget.AbsSeekBar, android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == 1) {
            super.onTouchEvent(event);
        }
        if (!isEnabled()) {
            return false;
        }
        int action = event.getAction();
        if (action == 0 || action == 1 || action == 2) {
            setProgress(getMax() - ((int) ((getMax() * event.getY()) / getHeight())));
            onSizeChanged(getWidth(), getHeight(), 0, 0);
        }
        return true;
    }

    @Override // android.widget.ProgressBar
    public void setProgress(int progress) {
        super.setProgress(progress);
        onSizeChanged(getWidth(), getHeight(), 0, 0);
    }
}
