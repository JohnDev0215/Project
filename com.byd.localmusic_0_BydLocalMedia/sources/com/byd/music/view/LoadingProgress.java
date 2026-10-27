package com.byd.music.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import com.byd.music.R;

/* JADX INFO: loaded from: classes5.dex */
public class LoadingProgress extends View {
    private int circleColor;
    private boolean isClockwise;
    private Handler mHandler;
    private int maxRadius;
    private int minRadius;
    private int numOfCircles;
    private int numOfRotate;
    private Paint paint;
    private float rotateDegrees;
    private int rotateSpeedInMillis;
    private boolean stop;
    private Runnable uiRunnable;

    public LoadingProgress(Context context) {
        this(context, null);
    }

    public LoadingProgress(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LoadingProgress(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.uiRunnable = new Runnable() { // from class: com.byd.music.view.LoadingProgress.1
            @Override // java.lang.Runnable
            public void run() {
                LoadingProgress.this.invalidate();
            }
        };
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LoadingProgress);
        this.numOfCircles = typedArrayObtainStyledAttributes.getInt(R.styleable.LoadingProgress_numOfCircles, 10);
        this.maxRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.LoadingProgress_maxRadius, dp2px(10));
        this.minRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.LoadingProgress_minRadius, dp2px(2));
        this.rotateSpeedInMillis = typedArrayObtainStyledAttributes.getInt(R.styleable.LoadingProgress_rotateSpeedInMillis, 200);
        this.isClockwise = typedArrayObtainStyledAttributes.getBoolean(R.styleable.LoadingProgress_isClockwise, true);
        this.circleColor = context.getColor(R.color.color_protocol);
        typedArrayObtainStyledAttributes.recycle();
        this.paint = new Paint();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setAntiAlias(true);
        this.paint.setColor(this.circleColor);
        this.rotateDegrees = 360 / this.numOfCircles;
        this.numOfRotate = 0;
        this.stop = false;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        int i = 0;
        if (this.numOfRotate == this.numOfCircles) {
            this.numOfRotate = 0;
        }
        if (this.isClockwise) {
            canvas.rotate(this.rotateDegrees * this.numOfRotate, getWidth() / 2, getHeight() / 2);
        } else {
            canvas.rotate((-this.rotateDegrees) * this.numOfRotate, getWidth() / 2, getHeight() / 2);
        }
        this.numOfRotate++;
        int iMin = Math.min(getWidth(), getHeight()) / 2;
        int i2 = this.maxRadius;
        int i3 = iMin - i2;
        float f = i2 - this.minRadius;
        int i4 = this.numOfCircles;
        float f2 = f / i4;
        double d = 6.283185307179586d / ((double) i4);
        if (this.isClockwise) {
            while (i < this.numOfCircles) {
                double d2 = ((double) i) * d;
                double d3 = i3;
                canvas.drawCircle((float) (((double) (getWidth() / 2)) + (Math.cos(d2) * d3)), (float) (((double) (getHeight() / 2)) - (Math.sin(d2) * d3)), this.maxRadius - (i * f2), this.paint);
                i++;
            }
        } else {
            while (i < this.numOfCircles) {
                double d4 = ((double) i) * d;
                double d5 = i3;
                canvas.drawCircle((float) (((double) (getWidth() / 2)) + (Math.cos(d4) * d5)), (float) (((double) (getHeight() / 2)) - (Math.sin(d4) * d5)), this.minRadius + (i * f2), this.paint);
                i++;
            }
        }
        this.mHandler.removeCallbacks(this.uiRunnable);
        if (this.stop) {
            return;
        }
        this.mHandler.postDelayed(this.uiRunnable, this.rotateSpeedInMillis);
    }

    public void setCircleColor(int i) {
        this.paint.setColor(i);
    }

    public void stopProgress() {
        this.stop = true;
    }

    public void reStartProgress() {
        this.stop = false;
        invalidate();
    }

    public void removeFromParent() {
        stopProgress();
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopProgress();
    }

    private int dp2px(int i) {
        return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
    }
}
