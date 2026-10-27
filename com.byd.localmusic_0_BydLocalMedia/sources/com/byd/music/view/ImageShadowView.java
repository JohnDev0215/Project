package com.byd.music.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes5.dex */
public class ImageShadowView extends ImageView {
    private int h;
    private int w;

    public ImageShadowView(Context context) {
        super(context);
    }

    public ImageShadowView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ImageShadowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.w = getWidth();
        this.h = getHeight();
        canvas.drawBitmap(setAlpha(setShadow(getmBitmap()), 50), 0.0f, this.h, (Paint) null);
    }

    private Bitmap getmBitmap() {
        setDrawingCacheEnabled(true);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(getDrawingCache());
        setDrawingCacheEnabled(false);
        return bitmapCreateBitmap;
    }

    public static Bitmap setAlpha(Bitmap bitmap, int i) {
        int width = bitmap.getWidth() * bitmap.getHeight();
        int[] iArr = new int[width];
        bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        double d = (i * 255) / 100;
        double d2 = d / ((double) width);
        for (int i2 = 0; i2 < width; i2++) {
            double d3 = d - (((double) i2) * d2);
            if (d3 > 10.0d) {
                iArr[i2] = (((int) d3) << 24) | (iArr[i2] & 16777215);
            } else {
                iArr[i2] = 167772160 | (iArr[i2] & 16777215);
            }
        }
        return Bitmap.createBitmap(iArr, bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
    }

    public static Bitmap setShadow(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.preScale(1.0f, -1.0f);
        return Bitmap.createBitmap(bitmap, 0, height, width, height, matrix, false);
    }
}
