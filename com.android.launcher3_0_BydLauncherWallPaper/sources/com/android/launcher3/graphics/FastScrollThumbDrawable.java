package com.android.launcher3.graphics;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

/* JADX INFO: loaded from: classes4.dex */
public class FastScrollThumbDrawable extends Drawable {
    private static final Matrix sMatrix = new Matrix();
    private final boolean mIsRtl;
    private final Paint mPaint;
    private final Path mPath = new Path();

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
    }

    public FastScrollThumbDrawable(Paint paint, boolean isRtl) {
        this.mPaint = paint;
        this.mIsRtl = isRtl;
    }

    @Override // android.graphics.drawable.Drawable
    public void getOutline(Outline outline) {
        if (this.mPath.isConvex()) {
            outline.setConvexPath(this.mPath);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect bounds) {
        this.mPath.reset();
        float fHeight = bounds.height() * 0.5f;
        float f = 2.0f * fHeight;
        float f2 = fHeight / 5.0f;
        this.mPath.addRoundRect(bounds.left, bounds.top, bounds.left + f, bounds.top + f, new float[]{fHeight, fHeight, fHeight, fHeight, f2, f2, fHeight, fHeight}, Path.Direction.CCW);
        Matrix matrix = sMatrix;
        matrix.setRotate(-45.0f, bounds.left + fHeight, bounds.top + fHeight);
        if (this.mIsRtl) {
            matrix.postTranslate(bounds.width(), 0.0f);
            matrix.postScale(-1.0f, 1.0f, bounds.width(), 0.0f);
        }
        this.mPath.transform(matrix);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.drawPath(this.mPath, this.mPaint);
    }
}
