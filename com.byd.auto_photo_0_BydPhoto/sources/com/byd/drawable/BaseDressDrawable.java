package com.byd.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import androidx.core.internal.view.SupportMenu;
import com.byd.imageedit.DressUpImageView;

/* JADX INFO: loaded from: classes2.dex */
public class BaseDressDrawable extends Drawable {
    public static final int CENTER = 16;
    public static final int LEFT_BOTTOM = 4;
    public static final int LEFT_TOP = 1;
    public static final int RIGHT_BOTTOM = 8;
    public static final int RIGHT_TOP = 2;
    protected int alpha;
    protected Bitmap bitmapLeftBottom;
    protected Bitmap bitmapLeftTop;
    private Paint bitmapPaint;
    protected Bitmap bitmapRightBottom;
    protected Bitmap bitmapRightTop;
    protected Rect bound;
    protected Point center;
    protected boolean controllMode;
    private int controllerBgRadius;
    private int controllerPos;
    protected int controllerRadius;
    protected int controllers;
    private boolean dashLine;
    private DashPathEffect dashPathEffect;
    protected float degree;
    protected Rect imageRect;
    protected DressUpImageView imageView;
    private float lastDegrees;
    private float lastDis;
    private Point lastPoint;
    private int lineColor;
    protected String name;
    protected Paint paint;
    private Rect tempRect;
    protected int viewHeight;
    protected int viewWidth;

    public BaseDressDrawable(DressUpImageView dressUpImageView) {
        this(dressUpImageView, "未命名");
    }

    public BaseDressDrawable(DressUpImageView dressUpImageView, String str) {
        this.controllerRadius = 30;
        this.controllerBgRadius = 24;
        this.controllMode = true;
        this.alpha = 255;
        this.dashPathEffect = new DashPathEffect(new float[]{10.0f, 8.0f}, 0.0f);
        this.lineColor = -1;
        this.dashLine = true;
        this.imageView = dressUpImageView;
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setColor(SupportMenu.CATEGORY_MASK);
        this.bound = new Rect();
        this.lastPoint = new Point();
        this.center = new Point();
        this.name = str;
        Rect imageInfo = dressUpImageView.getImageInfo();
        this.imageRect = imageInfo;
        this.viewWidth = imageInfo.width();
        this.viewHeight = this.imageRect.height();
        if (this.viewWidth <= 0) {
            this.viewWidth = dressUpImageView.getMeasuredWidth();
        }
        if (this.viewHeight <= 0) {
            this.viewHeight = dressUpImageView.getMeasuredHeight();
        }
        Paint paint2 = new Paint();
        this.bitmapPaint = paint2;
        paint2.setAntiAlias(true);
        this.bitmapPaint.setFilterBitmap(true);
    }

    private void drawBorderAndController(Canvas canvas) {
        this.paint.setAlpha(200);
        this.paint.setStyle(Paint.Style.STROKE);
        if (this.dashLine) {
            this.paint.setPathEffect(this.dashPathEffect);
        }
        this.paint.setColor(this.lineColor);
        canvas.drawRect(this.bound, this.paint);
        this.paint.setPathEffect(null);
        this.paint.setStyle(Paint.Style.FILL);
        drawLeftTopArea(canvas);
        drawLeftBottomArea(canvas);
        drawRightTopArea(canvas);
        drawRightBottomArea(canvas);
        this.paint.setAlpha(this.alpha);
    }

    private Bitmap getBitmap(int i) {
        if (i == 0) {
            return null;
        }
        return ((BitmapDrawable) this.imageView.getResources().getDrawable(i)).getBitmap();
    }

    private float getDegreesH(float f, float f2) {
        Point point = this.center;
        if (f2 == point.y) {
            return f > ((float) point.x) ? 0.0f : 180.0f;
        }
        float degrees = (float) Math.toDegrees((float) Math.atan(Math.abs(f2 - r1) / Math.abs(f - this.center.x)));
        Point point2 = this.center;
        return f < ((float) point2.x) ? f2 > ((float) point2.y) ? 180.0f - degrees : degrees + 180.0f : f2 < ((float) point2.y) ? -degrees : degrees;
    }

    private float getDegreesV(float f, float f2, float f3) {
        Point point = this.center;
        if (f == point.x) {
            return f2 < ((float) point.y) ? -90.0f : 90.0f;
        }
        float degrees = ((float) Math.toDegrees((float) Math.atan(((f2 - point.y) + f3) / Math.abs(f - r1)))) * 2.0f;
        return f < ((float) this.center.x) ? 360.0f - degrees : degrees;
    }

    private Rect getPointerRect(int i, int i2) {
        Rect rect = this.tempRect;
        if (rect == null) {
            int i3 = this.controllerRadius;
            this.tempRect = new Rect(i - i3, i2 - i3, i + i3, i2 + i3);
        } else {
            int i4 = this.controllerRadius;
            rect.set(i - i4, i2 - i4, i + i4, i2 + i4);
        }
        return this.tempRect;
    }

    private float getRadius(int i, int i2) {
        return (float) Math.sqrt(Math.pow(i - this.center.x, 2.0d) + Math.pow(i2 - this.center.y, 2.0d));
    }

    private int[] getRotatePoint(int i, int i2) {
        if (this.degree != 0.0f) {
            float radius = getRadius(i, i2);
            double radians = (float) Math.toRadians(getDegreesH(i, i2) - this.degree);
            double d = radius;
            int iCos = (int) (((double) this.center.x) + (Math.cos(radians) * d));
            i2 = (int) (((double) this.center.y) + (Math.sin(radians) * d));
            i = iCos;
        }
        return new int[]{i, i2};
    }

    public int checkControllerPos(int i, int i2) {
        int[] rotatePoint = getRotatePoint(i, i2);
        int i3 = rotatePoint[0];
        int i4 = rotatePoint[1];
        Rect rect = this.bound;
        int i5 = rect.left;
        int i6 = this.controllerRadius;
        if (i3 < i5 - i6 || i3 > i5 + i6) {
            int i7 = rect.top;
            if (i4 < i7 - i6 || i4 > i7 + i6) {
                int i8 = rect.right;
                if (i3 < i8 - i6 || i3 > i8 + i6) {
                    int i9 = rect.bottom;
                    if (i4 >= i9 - i6 && i4 <= i9 + i6) {
                        int i10 = this.controllers;
                        if ((i10 & 4) != 0 && i3 <= i5 - i6 && i3 <= i5 + i6) {
                            return 4;
                        }
                        if ((i10 & 8) != 0 && i3 >= i8 - i6 && i3 <= i8 + i6) {
                            return 8;
                        }
                    }
                } else {
                    int i11 = this.controllers;
                    if ((i11 & 2) != 0 && i4 >= i7 - i6 && i4 <= i7 + i6) {
                        return 2;
                    }
                    if ((i11 & 8) != 0) {
                        int i12 = rect.bottom;
                        if (i4 >= i12 - i6 && i4 <= i12 + i6) {
                            return 8;
                        }
                    }
                }
            } else {
                int i13 = this.controllers;
                if ((i13 & 1) != 0 && i3 >= i5 - i6 && i3 <= i5 + i6) {
                    return 1;
                }
                if ((i13 & 2) != 0) {
                    int i14 = rect.right;
                    if (i3 >= i14 - i6 && i3 <= i14 + i6) {
                        return 2;
                    }
                }
            }
        } else {
            int i15 = this.controllers;
            if ((i15 & 1) != 0) {
                int i16 = rect.top;
                if (i4 >= i16 - i6 && i4 <= i16 + i6) {
                    return 1;
                }
            }
            if ((i15 & 4) != 0) {
                int i17 = rect.bottom;
                if (i4 >= i17 - i6 && i4 <= i17 + i6) {
                    return 4;
                }
            }
        }
        return rect.contains(i3, i4) ? 16 : 0;
    }

    public void doActionDown(int i, int i2, int i3) {
        this.controllerPos = i3;
        if (i3 != 8) {
            if (i3 != 16) {
                return;
            }
            this.lastPoint.set(i, i2);
        } else {
            float radius = getRadius(i, i2);
            this.lastDis = radius;
            this.lastDegrees = getDegreesV(i, i2, radius);
            int[] rotatePoint = getRotatePoint(i, i2);
            this.lastPoint.set(rotatePoint[0], rotatePoint[1]);
        }
    }

    public void doActionMove(int i, int i2) {
        int i3 = this.controllerPos;
        if (i3 == 8) {
            float radius = getRadius(i, i2);
            float degreesV = getDegreesV(i, i2, radius);
            float f = this.degree + (degreesV - this.lastDegrees);
            this.degree = f;
            if (f % 360.0f == 0.0f) {
                this.degree = 0.0f;
            }
            this.lastDegrees = degreesV;
            int[] rotatePoint = getRotatePoint(i, i2);
            float f2 = radius / this.lastDis;
            this.lastDis = radius;
            onScale(f2);
            this.lastPoint.set(rotatePoint[0], rotatePoint[1]);
            return;
        }
        if (i3 != 16) {
            return;
        }
        Point point = this.lastPoint;
        int i4 = i - point.x;
        int i5 = i2 - point.y;
        Point point2 = this.center;
        int i6 = point2.x;
        int i7 = i6 + i4;
        int i8 = point2.y;
        int i9 = i8 + i5;
        Rect rect = this.imageRect;
        int i10 = rect.left;
        if (i7 < i10 || i7 > (i10 = rect.right)) {
            i4 = i10 - i6;
        }
        int i11 = rect.top;
        if (i9 < i11 || i9 > (i11 = rect.bottom)) {
            i5 = i11 - i8;
        }
        this.bound.offset(i4, i5);
        setBounds(this.bound);
        this.lastPoint.set(i, i2);
    }

    public void doActionUp(int i, int i2) {
        int i3 = this.controllerPos;
        if (i3 != 16) {
            this.imageView.f(this, i3);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.save();
        float f = this.degree;
        Point point = this.center;
        canvas.rotate(f, point.x, point.y);
        drawContent(canvas);
        if (this.controllMode) {
            drawBorderAndController(canvas);
        }
        canvas.restore();
    }

    protected void drawContent(Canvas canvas) {
    }

    protected void drawLeftBottomArea(Canvas canvas) {
        Bitmap bitmap = this.bitmapLeftBottom;
        if (bitmap == null) {
            return;
        }
        Rect rect = this.bound;
        canvas.drawBitmap(bitmap, (Rect) null, getPointerRect(rect.left, rect.bottom), this.bitmapPaint);
    }

    protected void drawLeftTopArea(Canvas canvas) {
        Bitmap bitmap = this.bitmapLeftTop;
        if (bitmap == null) {
            return;
        }
        Rect rect = this.bound;
        canvas.drawBitmap(bitmap, (Rect) null, getPointerRect(rect.left, rect.top), this.bitmapPaint);
    }

    protected void drawRightBottomArea(Canvas canvas) {
        Bitmap bitmap = this.bitmapRightBottom;
        if (bitmap == null) {
            return;
        }
        Rect rect = this.bound;
        canvas.drawBitmap(bitmap, (Rect) null, getPointerRect(rect.right, rect.bottom), this.bitmapPaint);
    }

    protected void drawRightTopArea(Canvas canvas) {
        Bitmap bitmap = this.bitmapRightTop;
        if (bitmap == null) {
            return;
        }
        Rect rect = this.bound;
        canvas.drawBitmap(bitmap, (Rect) null, getPointerRect(rect.right, rect.top), this.bitmapPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.alpha;
    }

    public float getDegree() {
        return this.degree;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return getBounds().height();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return getBounds().width();
    }

    public String getName() {
        return this.name;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public void initControllerItems(int i, int i2, int i3, int i4) {
        initControllerItems(getBitmap(i), getBitmap(i2), getBitmap(i3), getBitmap(i4));
    }

    public void initControllerItems(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4) {
        this.controllers = 0;
        this.bitmapLeftTop = bitmap;
        if (bitmap != null) {
            this.controllers = 0 | 1;
        }
        this.bitmapRightTop = bitmap2;
        if (bitmap2 != null) {
            this.controllers |= 2;
        }
        this.bitmapLeftBottom = bitmap3;
        if (bitmap3 != null) {
            this.controllers |= 4;
        }
        this.bitmapRightBottom = bitmap4;
        if (bitmap4 != null) {
            this.controllers |= 8;
        }
    }

    public boolean isControllMode() {
        return this.controllMode;
    }

    public void onScale(float f) {
        int iWidth = this.bound.width();
        int iHeight = this.bound.height();
        int i = (int) ((iWidth * f) / 2.0f);
        int i2 = (int) ((iHeight * f) / 2.0f);
        int i3 = this.controllerRadius;
        if (i < i3) {
            i2 = (i3 * iHeight) / iWidth;
            i = i3;
        }
        if (i2 < i3) {
            i = (i3 * iWidth) / iHeight;
        } else {
            i3 = i2;
        }
        int i4 = this.viewWidth;
        int i5 = this.viewHeight;
        if (i4 >= i5 && i3 > i5) {
            i = (iWidth * i5) / iHeight;
            i3 = i5;
        } else if (i4 < i5 && i > i4) {
            i3 = (iHeight * i4) / iWidth;
            i = i4;
        }
        Rect rect = this.bound;
        Point point = this.center;
        int i6 = point.x;
        int i7 = point.y;
        rect.set(i6 - i, i7 - i3, i6 + i, i7 + i3);
        setBounds(this.bound);
    }

    protected void onSizeChange() {
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.paint.setAlpha(i);
        this.alpha = i;
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(Rect rect) {
        super.setBounds(rect);
        if (!this.bound.equals(rect)) {
            this.bound = rect;
        }
        this.center.set(this.bound.centerX(), this.bound.centerY());
        onSizeChange();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }

    public void setControllMode(boolean z) {
        this.controllMode = z;
    }

    public void setControllerRadius(int i) {
        this.controllerRadius = i;
    }

    public void setDashLine(boolean z) {
        this.dashLine = z;
    }

    public void setDegree(float f) {
        this.degree = f;
    }

    public void setImageView(DressUpImageView dressUpImageView) {
        this.imageView = dressUpImageView;
    }

    public void setLineColor(int i) {
        this.lineColor = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String toString() {
        return getClass().getName() + " -> " + this.name;
    }
}
