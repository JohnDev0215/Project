package com.byd.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import com.byd.imageedit.DressUpImageView;
import java.util.Random;

/* JADX INFO: loaded from: classes2.dex */
public class BitmapDressDrawable extends BaseDressDrawable {
    private Bitmap bitmap;
    private boolean mirror;
    private Random random;

    public BitmapDressDrawable(DressUpImageView dressUpImageView, Bitmap bitmap) {
        this(dressUpImageView, bitmap, "未命名");
    }

    public BitmapDressDrawable(DressUpImageView dressUpImageView, Bitmap bitmap, String str) {
        super(dressUpImageView, str);
        this.mirror = false;
        this.random = new Random();
        this.bitmap = bitmap;
        changeBound();
    }

    private void changeBound() {
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            int width = bitmap.getWidth();
            int height = this.bitmap.getHeight();
            int i = this.viewWidth;
            int i2 = i - width;
            if (i2 > 0) {
                i = i2;
            }
            int i3 = this.viewHeight;
            int i4 = i3 - height;
            if (i4 > 0) {
                i3 = i4;
            }
            int iNextInt = this.imageRect.left + this.random.nextInt(i);
            int iNextInt2 = this.imageRect.top + this.random.nextInt(i3);
            Rect rect = this.bound;
            rect.left = iNextInt;
            rect.right = iNextInt + width;
            rect.top = iNextInt2;
            rect.bottom = iNextInt2 + height;
            setBounds(rect);
        }
    }

    @Override // com.byd.drawable.BaseDressDrawable
    protected void drawContent(Canvas canvas) {
        Bitmap bitmap = this.bitmap;
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, (Rect) null, this.bound, this.paint);
        }
    }

    public Bitmap getBitmap() {
        return this.bitmap;
    }

    public boolean isMirror() {
        return this.mirror;
    }

    public void mirrorImage() {
        this.mirror = !this.mirror;
        Matrix matrix = new Matrix();
        matrix.postScale(-1.0f, 1.0f);
        Bitmap bitmap = this.bitmap;
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), this.bitmap.getHeight(), matrix, true);
        this.bitmap.recycle();
        this.bitmap = bitmapCreateBitmap;
    }

    @Override // com.byd.drawable.BaseDressDrawable
    protected void onSizeChange() {
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
        changeBound();
    }
}
