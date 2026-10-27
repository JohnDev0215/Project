package com.byd.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;
import b.a.d;
import b.a.h.a;
import com.byd.util.w;

/* JADX INFO: loaded from: classes2.dex */
public class OverlayImageView extends ImageView {
    private static final int MSG_HIDE_BORDER = 1;
    public static final int RECT_HEIGHT_FIX = 1;
    public static final int RECT_WIDTH_FIX = 2;
    public static final int SHAPE_CIRCLE = 1;
    public static final int SHAPE_RECT = 2;
    private boolean addOverlay;
    private float blurRadius;
    private Context context;
    private Rect imageBound;
    private int imgHeight;
    private float[] imgValues;
    private int imgWidth;
    private int lastX;
    private int lastY;
    private Handler mHandler;
    private Matrix matrixOrigenal;
    private Rect maxBound;
    private int maxRadius;
    private boolean moving;
    private int oH;
    private int oW;
    private Bitmap overlayBitmap;
    private Paint overlayBorderPaint;
    private Paint overlayPaint;
    private int overlayRadius;
    private Path overlayShape;
    private Rect overlayZoneRect;
    private float rectCx;
    private float rectCy;
    private int rectMode;
    private int rectRadius;
    private RectF rectShape;
    private int shape;
    private int startX;
    private int startY;

    private static class UpdateHandler extends d<OverlayImageView> {
        public UpdateHandler(OverlayImageView overlayImageView) {
            super(overlayImageView);
        }

        @Override // b.a.d
        public void handleMessage(OverlayImageView overlayImageView, Message message) {
            if (message.what != 1) {
                return;
            }
            overlayImageView.moving = false;
            overlayImageView.invalidate();
        }
    }

    public OverlayImageView(Context context) {
        this(context, null);
    }

    public OverlayImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.imgValues = new float[9];
        this.context = context;
        setLayerType(1, null);
        this.overlayShape = new Path();
        Paint paint = new Paint(1);
        this.overlayPaint = paint;
        paint.setMaskFilter(new BlurMaskFilter(100.0f, BlurMaskFilter.Blur.INNER));
        Paint paint2 = new Paint();
        this.overlayBorderPaint = paint2;
        paint2.setAntiAlias(true);
        this.overlayBorderPaint.setStrokeWidth(2.0f);
        this.overlayBorderPaint.setColor(-1);
        this.overlayBorderPaint.setStyle(Paint.Style.STROKE);
        this.maxBound = new Rect();
        this.mHandler = new UpdateHandler(this);
    }

    private void drawOverlay(Canvas canvas, float f, boolean z) {
        float f2;
        int i;
        int i2;
        if (!this.addOverlay || this.overlayBitmap == null) {
            return;
        }
        int iCenterX = this.overlayZoneRect.centerX();
        int iCenterY = this.overlayZoneRect.centerY();
        int i3 = 1;
        if (this.moving) {
            this.overlayShape.reset();
            if (this.shape == 1) {
                this.overlayShape.addCircle(iCenterX, iCenterY, this.overlayRadius, Path.Direction.CW);
            } else {
                this.overlayShape.addRect(getRectShape(iCenterX, iCenterY, this.overlayRadius), Path.Direction.CCW);
            }
            canvas.save();
            canvas.clipPath(this.overlayShape);
            canvas.drawBitmap(this.overlayBitmap, this.matrixOrigenal, null);
            canvas.restore();
            if (this.shape == 1) {
                canvas.drawCircle(iCenterX, iCenterY, this.overlayRadius, this.overlayBorderPaint);
                return;
            } else {
                canvas.drawRect(getRectShape(iCenterX, iCenterY, this.overlayRadius), this.overlayBorderPaint);
                return;
            }
        }
        int i4 = this.overlayRadius + ((int) (15.0f * f));
        w.c(this.overlayZoneRect + " x:" + iCenterX + " y" + iCenterY + " " + i4);
        float f3 = this.blurRadius;
        float f4 = f3 / 10.0f;
        canvas.save();
        int width = canvas.getWidth();
        int height = canvas.getHeight();
        int i5 = i4;
        int i6 = 0;
        while (true) {
            if (i6 >= 10) {
                break;
            }
            float f5 = f3 - f4;
            this.overlayShape.reset();
            if (this.shape == i3) {
                this.overlayShape.addCircle(iCenterX, iCenterY, i5, Path.Direction.CW);
                f2 = f5;
                i = i6;
                i2 = i5;
            } else {
                f2 = f5;
                i = i6;
                i2 = i5;
                this.overlayShape.addRect(getRectShapeToSave(iCenterX, iCenterY, i5, width, height), Path.Direction.CCW);
            }
            canvas.clipPath(this.overlayShape);
            if (f2 > 0.0f) {
                Bitmap bitmapA = a.a(this.context, this.overlayBitmap, f2, false);
                if (z) {
                    canvas.drawBitmap(bitmapA, 0.0f, 0.0f, (Paint) null);
                } else {
                    canvas.drawBitmap(bitmapA, this.matrixOrigenal, null);
                }
                bitmapA.recycle();
                i5 = (int) (i2 - (5.0f * f));
                i6 = i + 1;
                f3 = f2;
                i3 = 1;
            } else if (z) {
                canvas.drawBitmap(this.overlayBitmap, 0.0f, 0.0f, (Paint) null);
            } else {
                canvas.drawBitmap(this.overlayBitmap, this.matrixOrigenal, null);
            }
        }
        canvas.restore();
    }

    private RectF getRectShape(float f, float f2, int i) {
        if (this.rectCx == f && this.rectCy == f2 && this.rectRadius == i) {
            return this.rectShape;
        }
        this.rectCx = f;
        this.rectCy = f2;
        this.rectRadius = i;
        if (this.rectShape == null) {
            this.rectShape = new RectF();
        }
        this.imageBound = getDrawable().getBounds();
        getImageMatrix().getValues(this.imgValues);
        int i2 = this.rectMode;
        if (i2 == 1) {
            float f3 = this.imgValues[2];
            float f4 = i;
            this.rectShape.set(f3, f2 - f4, (this.imageBound.width() * this.imgValues[0]) + f3, f2 + f4);
        } else if (i2 != 2) {
            float f5 = i >> 1;
            this.rectShape.set(f - f5, f2 - f5, f + f5, f2 + f5);
        } else {
            float f6 = this.imgValues[5];
            float f7 = i;
            this.rectShape.set(f - f7, f6, f + f7, (this.imageBound.height() * this.imgValues[4]) + f6);
        }
        return this.rectShape;
    }

    private RectF getRectShapeToSave(float f, float f2, int i, int i2, int i3) {
        if (this.rectCx == f && this.rectCy == f2 && this.rectRadius == i) {
            return this.rectShape;
        }
        this.rectCx = f;
        this.rectCy = f2;
        this.rectRadius = i;
        if (this.rectShape == null) {
            this.rectShape = new RectF();
        }
        int i4 = this.rectMode;
        if (i4 == 1) {
            float f3 = i;
            this.rectShape.set(0.0f, f2 - f3, i2, f2 + f3);
        } else if (i4 != 2) {
            float f4 = i >> 1;
            this.rectShape.set(f - f4, f2 - f4, f + f4, f2 + f4);
        } else {
            float f5 = i;
            this.rectShape.set(f - f5, 0.0f, f + f5, i3);
        }
        return this.rectShape;
    }

    private void initOverlayShape() {
        if (this.matrixOrigenal != null || getDrawable() == null) {
            return;
        }
        Matrix matrix = new Matrix();
        this.matrixOrigenal = matrix;
        matrix.set(getImageMatrix());
        Rect bounds = getDrawable().getBounds();
        float[] fArr = new float[9];
        getImageMatrix().getValues(fArr);
        this.imgWidth = (int) (bounds.width() * fArr[0]);
        this.imgHeight = (int) (bounds.height() * fArr[4]);
        Point point = new Point(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        int i = this.imgWidth;
        int i2 = this.imgHeight;
        int i3 = i < i2 ? i / 2 : i2 / 2;
        this.overlayRadius = i3;
        this.maxRadius = i3;
        int i4 = point.x;
        int i5 = point.y;
        Rect rect = new Rect(i4 - i3, i5 - i3, i4 + i3, i5 + i3);
        this.overlayZoneRect = rect;
        if (this.rectMode == 0) {
            int i6 = this.imgWidth;
            int i7 = this.imgHeight;
            if (i6 > i7) {
                this.rectMode = 2;
            } else if (i6 < i7) {
                this.rectMode = 1;
            }
        }
        this.oW = rect.width();
        int iHeight = this.overlayZoneRect.height();
        this.oH = iHeight;
        int i8 = (int) fArr[2];
        int i9 = (int) fArr[5];
        Rect rect2 = this.maxBound;
        int i10 = this.oW;
        rect2.set((i10 / 2) + i8, (iHeight / 2) + i9, i8 + this.imgWidth + (i10 / 2), i9 + this.imgHeight + (iHeight / 2));
        w.c("rectMode : " + this.rectMode);
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0095  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean dispatchTouchEvent(android.view.MotionEvent r8) {
        /*
            Method dump skipped, instruction units count: 216
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.view.OverlayImageView.dispatchTouchEvent(android.view.MotionEvent):boolean");
    }

    public Bitmap getResultBitmap(int i, int i2) {
        if (!this.addOverlay || this.overlayBitmap == null) {
            return ((BitmapDrawable) getDrawable()).getBitmap();
        }
        this.mHandler.removeMessages(1);
        this.moving = false;
        Rect bounds = getDrawable().getBounds();
        float[] fArr = new float[9];
        getImageMatrix().getValues(fArr);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bounds.width(), bounds.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawBitmap(((BitmapDrawable) getDrawable()).getBitmap(), 0.0f, 0.0f, (Paint) null);
        float f = 1.0f / fArr[0];
        float f2 = fArr[2];
        float f3 = fArr[5];
        Rect rect = new Rect(this.overlayZoneRect);
        int i3 = (int) ((rect.left - f2) * f);
        rect.left = i3;
        rect.top = (int) ((rect.top - f3) * f);
        rect.right = (int) (i3 + (this.overlayZoneRect.width() * f));
        rect.bottom = (int) (rect.top + (this.overlayZoneRect.height() * f));
        this.overlayZoneRect.set(rect);
        this.overlayRadius = (int) (this.overlayRadius * f);
        drawOverlay(canvas, f, true);
        return (i == 0 || i2 == 0) ? bitmapCreateBitmap : (i == bitmapCreateBitmap.getWidth() && i2 == bitmapCreateBitmap.getHeight()) ? bitmapCreateBitmap : Bitmap.createScaledBitmap(bitmapCreateBitmap, i, i2, true);
    }

    public int getShape() {
        return this.shape;
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initOverlayShape();
        drawOverlay(canvas, 1.0f, false);
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        if (this.matrixOrigenal == null || i == i3 || i2 == i4) {
            return;
        }
        resetOverlayShape();
    }

    public void resetOverlayShape() {
        this.matrixOrigenal = null;
        initOverlayShape();
    }

    public void setAddOverlay(boolean z) {
        if (this.addOverlay != z) {
            this.addOverlay = z;
            invalidate();
        }
    }

    public void setBlurRadius(float f) {
        this.blurRadius = f;
    }

    public void setOverlayBitmap(Bitmap bitmap) {
        this.overlayBitmap = bitmap;
    }

    public void setOverlayRadius(int i) {
        int i2 = this.maxRadius;
        if (i > i2) {
            i = i2;
        }
        if (this.overlayRadius != i) {
            this.overlayRadius = i;
            Rect rect = this.overlayZoneRect;
            if (rect == null) {
                Point point = new Point(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
                int i3 = point.x;
                int i4 = point.y;
                this.overlayZoneRect = new Rect(i3 - i, i4 - i, i3 + i, i4 + i);
            } else {
                Point point2 = new Point(rect.centerX(), this.overlayZoneRect.centerY());
                Rect rect2 = this.overlayZoneRect;
                int i5 = point2.x;
                int i6 = point2.y;
                rect2.set(i5 - i, i6 - i, i5 + i, i6 + i);
            }
            invalidate();
        }
    }

    public void setOverlayRadiusRatio(float f) {
        setOverlayRadius((int) (this.maxRadius * f));
    }

    public void setRectMode(int i) {
        this.rectMode = i;
    }

    public void setShape(int i) {
        if (this.shape != i) {
            this.shape = i;
            this.moving = true;
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessageDelayed(1, 2000L);
            if (this.overlayRadius != 0) {
                Point point = new Point(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
                int i2 = point.x;
                int i3 = this.overlayRadius;
                int i4 = point.y;
                this.overlayZoneRect = new Rect(i2 - i3, i4 - i3, i2 + i3, i4 + i3);
            }
            invalidate();
        }
    }
}
