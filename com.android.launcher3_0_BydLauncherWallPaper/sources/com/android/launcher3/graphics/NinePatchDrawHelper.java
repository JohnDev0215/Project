package com.android.launcher3.graphics;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

/* JADX INFO: loaded from: classes4.dex */
public class NinePatchDrawHelper {
    public static final int EXTENSION_PX = 20;
    private final Rect mSrc = new Rect();
    private final RectF mDst = new RectF();
    public final Paint paint = new Paint(3);

    public void draw(Bitmap bitmap, Canvas canvas, float left, float top, float right) {
        int height = bitmap.getHeight();
        this.mSrc.top = 0;
        this.mSrc.bottom = height;
        this.mDst.top = top;
        this.mDst.bottom = top + height;
        draw3Patch(bitmap, canvas, left, right);
    }

    public void drawVerticallyStretched(Bitmap bitmap, Canvas canvas, float left, float top, float right, float bottom) {
        draw(bitmap, canvas, left, top, right);
        int height = bitmap.getHeight();
        this.mSrc.top = height - 5;
        this.mSrc.bottom = height;
        this.mDst.top = top + height;
        this.mDst.bottom = bottom;
        draw3Patch(bitmap, canvas, left, right);
    }

    private void draw3Patch(Bitmap bitmap, Canvas canvas, float left, float right) {
        int width = bitmap.getWidth();
        int i = width / 2;
        float f = i;
        float f2 = left + f;
        drawRegion(bitmap, canvas, 0, i, left, f2);
        float f3 = right - f;
        drawRegion(bitmap, canvas, i, width, f3, right);
        drawRegion(bitmap, canvas, i - 5, i + 5, f2, f3);
    }

    private void drawRegion(Bitmap bitmap, Canvas c, int srcLeft, int srcRight, float dstLeft, float dstRight) {
        this.mSrc.left = srcLeft;
        this.mSrc.right = srcRight;
        this.mDst.left = dstLeft;
        this.mDst.right = dstRight;
        c.drawBitmap(bitmap, this.mSrc, this.mDst, this.paint);
    }
}
