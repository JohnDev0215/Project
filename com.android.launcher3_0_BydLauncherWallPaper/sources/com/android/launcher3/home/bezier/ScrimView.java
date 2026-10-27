package com.android.launcher3.home.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
public class ScrimView extends View {
    public static float MAX_PROGRESS = 1.0f;
    public static float MIN_PROGRESS;
    private int mBaseAlpha;
    private Rect mBounds;
    private int mDirection;
    private final Paint mPaint;
    private int mScrimColor;
    private int mShadowColor;
    private int mShadowDirection;
    private Paint mShadowPaint;
    private Rect mShadowRect;
    private int mSize;

    public ScrimView(Context context) {
        super(context);
        this.mSize = 60;
        this.mBounds = new Rect();
        this.mShadowRect = new Rect();
        this.mShadowColor = Integer.MIN_VALUE;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint();
        this.mShadowPaint = paint2;
        paint2.setDither(true);
        this.mShadowPaint.setAntiAlias(true);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mBounds.right = w;
        this.mBounds.bottom = h;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.mScrimColor != 0) {
            canvas.drawRect(this.mBounds, this.mPaint);
        }
        if (this.mSize <= 0 || this.mShadowColor == 0 || (this.mDirection & 15) <= 0) {
            return;
        }
        canvas.save();
        int i = this.mShadowDirection;
        if (i == 2) {
            canvas.translate(this.mBounds.right - this.mSize, 0.0f);
        } else if (i == 8) {
            canvas.translate(0.0f, this.mBounds.bottom - this.mSize);
        }
        canvas.clipRect(this.mShadowRect);
        canvas.drawPaint(this.mShadowPaint);
        canvas.restore();
    }

    public void setProgress(float progress) {
        this.mPaint.setColor((((int) (this.mBaseAlpha * SmartSwipe.ensureBetween(progress, MIN_PROGRESS, MAX_PROGRESS))) << 24) | (this.mScrimColor & 16777215));
    }

    public void setScrimColor(int scrimColor) {
        this.mScrimColor = scrimColor;
        this.mBaseAlpha = (scrimColor & (-16777216)) >>> 24;
    }

    public void setDirection(int direction, int shadowColor, int shadowDirection, int shadowSize, int parentWidth, int parentHeight) {
        int i;
        int i2;
        this.mDirection = direction;
        this.mShadowColor = shadowColor;
        this.mShadowDirection = shadowDirection;
        int i3 = shadowSize;
        this.mSize = i3;
        if (shadowColor == 0) {
            return;
        }
        int i4 = 0;
        boolean z = true;
        if (shadowDirection == 1 || shadowDirection == 2) {
            i = parentHeight;
            i2 = i;
        } else {
            if (shadowDirection != 4 && shadowDirection != 8) {
                return;
            }
            i = i3;
            i2 = 0;
            i3 = parentWidth;
        }
        this.mShadowRect.right = i3;
        this.mShadowRect.bottom = i;
        int i5 = (this.mShadowColor & (-16777216)) >>> 24;
        float[] fArr = new float[31];
        int[] iArr = new int[31];
        int i6 = this.mShadowDirection;
        boolean z2 = i6 == 1 || i6 == 4;
        for (int i7 = 0; i7 <= 30; i7++) {
            fArr[i7] = (i7 * 1.0f) / 30;
        }
        for (int i8 = 0; i8 <= 30; i8++) {
            float f = fArr[z2 ? 30 - i8 : i8];
            iArr[i8] = (((int) ((i5 * f) * f)) << 24) | (this.mShadowColor & 16777215);
        }
        if (direction != 1 && direction != 2) {
            z = false;
        }
        if (z) {
            i >>= 1;
            i2 = i;
        } else {
            i4 = i3 >> 1;
            i3 = i4;
        }
        this.mShadowPaint.setShader(new LinearGradient(i4, i2, i3, i, iArr, fArr, Shader.TileMode.CLAMP));
    }

    public int getShadowColor() {
        return this.mShadowColor;
    }
}
