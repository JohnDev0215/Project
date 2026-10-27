package com.android.settingslib.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import com.android.settingslib.R$dimen;

/* JADX INFO: loaded from: classes.dex */
public class CircleFramedDrawable extends Drawable {
    private final Bitmap mBitmap;
    private RectF mDstRect;
    private Paint mIconPaint;
    private float mScale;
    private final int mSize;
    private Rect mSrcRect;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
    }

    public static CircleFramedDrawable getInstance(Context context, Bitmap bitmap) {
        return new CircleFramedDrawable(bitmap, (int) context.getResources().getDimension(R$dimen.circle_avatar_size));
    }

    public CircleFramedDrawable(Bitmap bitmap, int i) {
        this.mSize = i;
        this.mBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(this.mBitmap);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int iMin = Math.min(width, height);
        Rect rect = new Rect((width - iMin) / 2, (height - iMin) / 2, iMin, iMin);
        int i2 = this.mSize;
        RectF rectF = new RectF(0.0f, 0.0f, i2, i2);
        Path path = new Path();
        path.addArc(rectF, 0.0f, 360.0f);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(-16777216);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPath(path, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rectF, paint);
        paint.setXfermode(null);
        this.mScale = 1.0f;
        int i3 = this.mSize;
        this.mSrcRect = new Rect(0, 0, i3, i3);
        int i4 = this.mSize;
        this.mDstRect = new RectF(0.0f, 0.0f, i4, i4);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        float f = this.mScale;
        int i = this.mSize;
        float f2 = (i - (f * i)) / 2.0f;
        this.mDstRect.set(f2, f2, i - f2, i - f2);
        canvas.drawBitmap(this.mBitmap, this.mSrcRect, this.mDstRect, this.mIconPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        if (this.mIconPaint == null) {
            this.mIconPaint = new Paint();
        }
        this.mIconPaint.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mSize;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mSize;
    }
}
