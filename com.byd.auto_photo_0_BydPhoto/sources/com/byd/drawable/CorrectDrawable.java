package com.byd.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes2.dex */
public class CorrectDrawable extends Drawable {
    private int controlPointerIndex;
    private DashPathEffect dashPathEffect;
    private float[] dst;
    private Rect imageRect;
    private ImageView imageView;
    private Bitmap mBitmap;
    private Paint paint;
    private int pointWidth = 50;
    private Rect[] rectPinter;
    private float[] src;
    private float[] viewPointer;

    private int checkControlIndex(int i, int i2) {
        int i3 = 0;
        while (true) {
            Rect[] rectArr = this.rectPinter;
            if (i3 >= rectArr.length) {
                return -1;
            }
            if (rectArr[i3].contains(i, i2)) {
                return i3;
            }
            i3++;
        }
    }

    private void drawBorderAndLine(Canvas canvas) {
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setPathEffect(this.dashPathEffect);
        float[] fArr = this.viewPointer;
        canvas.drawLine(fArr[0], fArr[1], fArr[2], fArr[3], this.paint);
        float[] fArr2 = this.viewPointer;
        canvas.drawLine(fArr2[2], fArr2[3], fArr2[6], fArr2[7], this.paint);
        float[] fArr3 = this.viewPointer;
        canvas.drawLine(fArr3[6], fArr3[7], fArr3[4], fArr3[5], this.paint);
        float[] fArr4 = this.viewPointer;
        canvas.drawLine(fArr4[4], fArr4[5], fArr4[0], fArr4[1], this.paint);
        this.paint.setPathEffect(null);
        this.paint.setStyle(Paint.Style.FILL);
    }

    private void drawPointRect(Canvas canvas) {
        int i = 0;
        while (true) {
            Rect[] rectArr = this.rectPinter;
            if (i >= rectArr.length) {
                return;
            }
            canvas.drawRect(rectArr[i], this.paint);
            i++;
        }
    }

    private void initPointer() {
        if (this.dst == null) {
            this.dst = new float[8];
            this.src = new float[8];
            initSrcValue();
            this.viewPointer = new float[]{i, i, i, i, i, i, i, i};
            Rect rect = this.imageRect;
            int i = rect.left;
            int i2 = rect.top;
            int i3 = rect.right;
            int i4 = rect.bottom;
            setPointerRect(-1);
        }
    }

    private void initSrcValue() {
        int width = this.mBitmap.getWidth();
        int height = this.mBitmap.getHeight();
        float[] fArr = this.src;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        float f = width;
        fArr[2] = f;
        fArr[3] = 0.0f;
        fArr[4] = 0.0f;
        float f2 = height;
        fArr[5] = f2;
        fArr[6] = f;
        fArr[7] = f2;
    }

    private void setPointRectZone(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8 = i * 2;
        int i9 = 0;
        if (i != 0) {
            if (i == 1) {
                float[] fArr = this.viewPointer;
                int i10 = (int) fArr[i8];
                int i11 = (int) fArr[i8 + 1];
                int i12 = this.pointWidth;
                i6 = i10 - i12;
                i7 = i10;
                i4 = i12 + i11;
                i5 = i11;
            } else if (i == 2) {
                float[] fArr2 = this.viewPointer;
                int i13 = (int) fArr2[i8];
                int i14 = (int) fArr2[i8 + 1];
                int i15 = this.pointWidth;
                i2 = i13 + i15;
                i5 = i14 - i15;
                i3 = i13;
                i4 = i14;
            } else {
                if (i != 3) {
                    i5 = 0;
                    i2 = 0;
                    i4 = 0;
                    this.rectPinter[i].set(i9, i5, i2, i4);
                }
                float[] fArr3 = this.viewPointer;
                int i16 = (int) fArr3[i8];
                int i17 = (int) fArr3[i8 + 1];
                int i18 = this.pointWidth;
                i6 = i16 - i18;
                i5 = i17 - i18;
                i7 = i16;
                i4 = i17;
            }
            i9 = i6;
            i2 = i7;
            this.rectPinter[i].set(i9, i5, i2, i4);
        }
        float[] fArr4 = this.viewPointer;
        int i19 = (int) fArr4[i8];
        int i20 = (int) fArr4[i8 + 1];
        int i21 = this.pointWidth;
        i2 = i19 + i21;
        i3 = i19;
        i4 = i21 + i20;
        i5 = i20;
        i9 = i3;
        this.rectPinter[i].set(i9, i5, i2, i4);
    }

    private void setPointerRect(int i) {
        if (i != -1) {
            setPointRectZone(i);
            return;
        }
        for (int i2 = 0; i2 < this.rectPinter.length; i2++) {
            setPointRectZone(i2);
        }
    }

    public Bitmap createCorrectBitmap() {
        float[] fArr = this.viewPointer;
        float f = fArr[0];
        Rect rect = this.imageRect;
        int i = rect.left;
        if (f == i) {
            float f2 = fArr[1];
            int i2 = rect.top;
            if (f2 == i2) {
                float f3 = fArr[2];
                int i3 = rect.right;
                if (f3 == i3 && fArr[3] == i2 && fArr[4] == i) {
                    float f4 = fArr[5];
                    int i4 = rect.bottom;
                    if (f4 == i4 && fArr[6] == i3 && fArr[7] == i4) {
                        return this.mBitmap;
                    }
                }
            }
        }
        float[] fArr2 = new float[9];
        this.imageView.getImageMatrix().getValues(fArr2);
        float f5 = fArr2[0];
        float f6 = fArr2[4];
        for (int i5 = 0; i5 < this.rectPinter.length; i5++) {
            int i6 = i5 * 2;
            float[] fArr3 = this.src;
            float[] fArr4 = this.viewPointer;
            float f7 = fArr4[i6];
            Rect rect2 = this.imageRect;
            fArr3[i6] = (f7 - rect2.left) / f5;
            int i7 = i6 + 1;
            fArr3[i7] = (fArr4[i7] - rect2.top) / f6;
        }
        float[] fArr5 = this.src;
        float fMin = Math.min(fArr5[0], fArr5[4]);
        float[] fArr6 = this.src;
        float fMin2 = Math.min(fArr6[1], fArr6[3]);
        float[] fArr7 = this.src;
        float fMax = Math.max(fArr7[2], fArr7[6]);
        float[] fArr8 = this.src;
        float fMax2 = Math.max(fArr8[5], fArr8[7]);
        int i8 = (int) (fMax - fMin);
        int i9 = (int) (fMax2 - fMin2);
        if (i8 <= 0 || i9 <= 0) {
            return null;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i8, i9, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(this.mBitmap, new Rect((int) fMin, (int) fMin2, (int) fMax, (int) fMax2), new Rect(0, 0, i8, i9), (Paint) null);
        float[] fArr9 = this.src;
        int i10 = ((int) ((fArr9[5] + fArr9[7]) / 2.0f)) - ((int) ((fArr9[1] + fArr9[3]) / 2.0f));
        if (i10 <= 0) {
            bitmapCreateBitmap.recycle();
            return null;
        }
        fArr9[0] = fArr9[0] - fMin;
        fArr9[1] = fArr9[1] - fMin2;
        fArr9[2] = fArr9[2] - fMin;
        fArr9[3] = fArr9[3] - fMin2;
        fArr9[4] = fArr9[4] - fMin;
        fArr9[5] = fArr9[5] - fMin2;
        fArr9[6] = fArr9[6] - fMin;
        fArr9[7] = fArr9[7] - fMin2;
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(i8, i10, Bitmap.Config.RGB_565);
        canvas.setBitmap(bitmapCreateBitmap2);
        Matrix matrix = new Matrix();
        float[] fArr10 = this.dst;
        float f8 = i8;
        fArr10[2] = f8;
        float f9 = i10;
        fArr10[5] = f9;
        fArr10[6] = f8;
        fArr10[7] = f9;
        matrix.setPolyToPoly(this.src, 0, fArr10, 0, 4);
        Paint paint = new Paint(6);
        paint.setAntiAlias(true);
        canvas.drawBitmap(bitmapCreateBitmap, matrix, paint);
        bitmapCreateBitmap.recycle();
        return bitmapCreateBitmap2;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            int iCheckControlIndex = checkControlIndex(x, y);
            this.controlPointerIndex = iCheckControlIndex;
            if (iCheckControlIndex == -1) {
                motionEvent.setAction(3);
                return false;
            }
        } else if (action == 2) {
            int i = this.controlPointerIndex;
            int i2 = i * 2;
            float[] fArr = this.viewPointer;
            fArr[i2] = x;
            fArr[i2 + 1] = y;
            setPointerRect(i);
            this.imageView.invalidate();
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        initPointer();
        drawBorderAndLine(canvas);
        drawPointRect(canvas);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mBitmap.getHeight();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mBitmap.getWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public void init(ImageView imageView, Bitmap bitmap, Rect rect) {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setStrokeWidth(2.0f);
        this.paint.setColor(-1711341568);
        this.dashPathEffect = new DashPathEffect(new float[]{10.0f, 8.0f}, 0.0f);
        Rect[] rectArr = new Rect[4];
        this.rectPinter = rectArr;
        rectArr[0] = new Rect();
        this.rectPinter[1] = new Rect();
        this.rectPinter[2] = new Rect();
        this.rectPinter[3] = new Rect();
        this.mBitmap = bitmap;
        this.imageRect = rect;
        this.imageView = imageView;
    }

    public void reset() {
        initSrcValue();
        float[] fArr = this.viewPointer;
        Rect rect = this.imageRect;
        int i = rect.left;
        fArr[0] = i;
        int i2 = rect.top;
        fArr[1] = i2;
        int i3 = rect.right;
        fArr[2] = i3;
        fArr[3] = i2;
        fArr[4] = i;
        int i4 = rect.bottom;
        fArr[5] = i4;
        fArr[6] = i3;
        fArr[7] = i4;
        setPointerRect(-1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.paint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }
}
