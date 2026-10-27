package com.byd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.byd.auto_photo.R;

/* JADX INFO: loaded from: classes2.dex */
public class PositiveAndNegativeSeekBar extends View {
    private static final int MIN_OFFSET = 8;
    private int backgroundColor;
    private Point center;
    private int height;
    private boolean isStartInCenter;
    private boolean isVertical;
    private int lastX;
    private int lastY;
    private int max;
    private int negativeProgressColor;
    private OnProgressChangeListener onProgressChangeListener;
    private Paint paint;
    private int positiveProgressColor;
    private int progress;
    private int progressDragSize;
    private int progressRadius;
    private Rect progressRect;
    private Point thumbCenter;
    private int thumbColor;
    private int thumbRadius;
    private int width;

    public interface OnProgressChangeListener {
        void onProgressChange(PositiveAndNegativeSeekBar positiveAndNegativeSeekBar, int i, boolean z);

        void onStartTracking(PositiveAndNegativeSeekBar positiveAndNegativeSeekBar);

        void onStopTracking(PositiveAndNegativeSeekBar positiveAndNegativeSeekBar);
    }

    public PositiveAndNegativeSeekBar(Context context) {
        this(context, null);
    }

    public PositiveAndNegativeSeekBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.PositiveAndNegativeSeekBar);
        this.backgroundColor = typedArrayObtainStyledAttributes.getColor(0, -10066330);
        this.thumbColor = typedArrayObtainStyledAttributes.getColor(8, -49023);
        this.positiveProgressColor = typedArrayObtainStyledAttributes.getColor(5, -49023);
        this.negativeProgressColor = typedArrayObtainStyledAttributes.getColor(4, -1426112383);
        this.max = typedArrayObtainStyledAttributes.getInteger(3, 100);
        this.progress = typedArrayObtainStyledAttributes.getInteger(6, 0);
        this.isVertical = typedArrayObtainStyledAttributes.getBoolean(2, true);
        this.isStartInCenter = typedArrayObtainStyledAttributes.getBoolean(7, true);
        this.progressRadius = typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        typedArrayObtainStyledAttributes.recycle();
        this.center = new Point();
        this.thumbCenter = new Point();
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setStrokeWidth(1.0f);
        this.progressRect = new Rect();
    }

    private void changeProgress() {
        int i;
        int i2;
        int i3;
        if (this.isVertical) {
            i = this.center.y;
            i2 = this.thumbCenter.y;
        } else {
            i = this.thumbCenter.x;
            i2 = this.center.x;
        }
        int i4 = i - i2;
        if (this.isStartInCenter) {
            i3 = ((i4 * this.max) * 2) / this.progressDragSize;
        } else {
            i3 = (i4 * this.max) / this.progressDragSize;
            if (i3 < 0) {
                i3 = 0;
            }
        }
        if (i3 != this.progress) {
            this.progress = i3;
            OnProgressChangeListener onProgressChangeListener = this.onProgressChangeListener;
            if (onProgressChangeListener != null) {
                onProgressChangeListener.onProgressChange(this, i3, true);
            }
        }
    }

    private void drawProgress(Canvas canvas) {
        int i;
        Point point = this.thumbCenter;
        int i2 = point.y;
        Point point2 = this.center;
        int i3 = point2.y;
        if (i2 == i3 && point.x == point2.x) {
            return;
        }
        if (this.isVertical) {
            i = i2 < i3 ? this.positiveProgressColor : this.negativeProgressColor;
            if (this.isStartInCenter) {
                int i4 = i3 < i2 ? i3 : i2;
                if (i3 > i2) {
                    i2 = i3;
                }
                Rect rect = this.progressRect;
                int i5 = point2.x;
                int i6 = this.progressRadius;
                rect.set(i5 - i6, i4, i5 + i6, i2);
            } else {
                Rect rect2 = this.progressRect;
                int i7 = point.x;
                int i8 = this.progressRadius;
                rect2.set(i7 - i8, i2, i7 + i8, i3);
            }
        } else {
            int i9 = point.x;
            int i10 = point2.x;
            i = i9 > i10 ? this.positiveProgressColor : this.negativeProgressColor;
            Rect rect3 = this.progressRect;
            int i11 = this.progressRadius;
            rect3.set(i10, i3 - i11, i9, i3 + i11);
        }
        canvas.save();
        canvas.clipRect(this.progressRect);
        drawProgressEffect(canvas, i);
        canvas.restore();
    }

    private void drawProgressEffect(Canvas canvas, int i) {
        this.paint.setColor(i);
        if (!this.isVertical) {
            canvas.drawCircle(this.thumbRadius, this.center.y, this.progressRadius, this.paint);
            canvas.drawCircle(this.progressDragSize + this.thumbRadius, this.center.y, this.progressRadius, this.paint);
            int i2 = this.thumbRadius;
            int i3 = this.center.y;
            int i4 = this.progressRadius;
            canvas.drawRect(i2, i3 - i4, this.progressDragSize + i2, i3 + i4, this.paint);
            return;
        }
        canvas.drawCircle(this.center.x, this.thumbRadius, this.progressRadius, this.paint);
        canvas.drawCircle(this.center.x, this.progressDragSize + this.thumbRadius, this.progressRadius, this.paint);
        int i5 = this.center.x;
        int i6 = this.progressRadius;
        canvas.drawRect(i5 - i6, this.thumbRadius, i5 + i6, this.progressDragSize + r1, this.paint);
    }

    private int getDefultSize(int i, int i2) {
        return View.MeasureSpec.getMode(i) == 1073741824 ? View.MeasureSpec.getSize(i) : i2;
    }

    private void updateThumbPos() {
        float f = ((this.isStartInCenter ? this.progressDragSize / 2 : this.progressDragSize) * this.progress) / this.max;
        if (this.isVertical) {
            this.thumbCenter.y = (int) (this.center.y - f);
        } else {
            this.thumbCenter.x = (int) (this.center.x + f);
        }
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (action == 0) {
            this.lastY = (int) motionEvent.getY();
            this.lastX = (int) motionEvent.getX();
            OnProgressChangeListener onProgressChangeListener = this.onProgressChangeListener;
            if (onProgressChangeListener != null) {
                onProgressChangeListener.onStartTracking(this);
            }
        } else if (action == 1) {
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            if (x == this.lastX && y == this.lastY) {
                Point point = this.thumbCenter;
                if (y != point.y || x != point.x) {
                    if (this.isVertical) {
                        point.y = y;
                    } else {
                        point.x = x;
                    }
                    changeProgress();
                    invalidate();
                }
            }
            OnProgressChangeListener onProgressChangeListener2 = this.onProgressChangeListener;
            if (onProgressChangeListener2 != null) {
                onProgressChangeListener2.onStopTracking(this);
            }
        } else if (action == 2) {
            if (this.isVertical) {
                int y2 = (int) motionEvent.getY();
                int i = y2 - this.lastY;
                if (Math.abs(i) >= 8) {
                    Point point2 = this.thumbCenter;
                    int i2 = point2.y + i;
                    int i3 = this.thumbRadius;
                    if (i2 < i3) {
                        i2 = i3;
                    } else {
                        int i4 = this.progressDragSize;
                        if (i2 > i4 + i3) {
                            i2 = i4 + i3;
                        }
                    }
                    point2.y = i2;
                    this.lastY = y2;
                    changeProgress();
                    invalidate();
                }
            } else {
                int x2 = (int) motionEvent.getX();
                int i5 = x2 - this.lastX;
                if (Math.abs(i5) >= 8) {
                    Point point3 = this.thumbCenter;
                    int i6 = point3.x + i5;
                    int i7 = this.thumbRadius;
                    if (i6 < i7) {
                        i6 = i7;
                    } else {
                        int i8 = this.progressDragSize;
                        if (i6 > i8 + i7) {
                            i6 = i8 + i7;
                        }
                    }
                    point3.x = i6;
                    this.lastX = x2;
                    changeProgress();
                    invalidate();
                }
            }
        }
        return true;
    }

    public int getMax() {
        return this.max;
    }

    public int getProgress() {
        return this.progress;
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.width = 0;
        this.height = 0;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        drawProgressEffect(canvas, this.backgroundColor);
        drawProgress(canvas);
        this.paint.setColor(this.thumbColor);
        Point point = this.thumbCenter;
        canvas.drawCircle(point.x, point.y, this.thumbRadius, this.paint);
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onMeasure(int r4, int r5) {
        /*
            r3 = this;
            boolean r0 = r3.isVertical
            r1 = 40
            r2 = 120(0x78, float:1.68E-43)
            if (r0 == 0) goto La
            r0 = r1
            goto Lb
        La:
            r0 = r2
        Lb:
            int r4 = r3.getDefultSize(r4, r0)
            boolean r0 = r3.isVertical
            if (r0 == 0) goto L14
            r1 = r2
        L14:
            int r5 = r3.getDefultSize(r5, r1)
            if (r4 <= 0) goto L25
            int r0 = r3.width
            if (r4 != r0) goto L25
            if (r5 <= 0) goto L25
            int r0 = r3.height
            if (r0 != r5) goto L25
            return
        L25:
            r3.setMeasuredDimension(r4, r5)
            r3.width = r4
            r3.height = r5
            boolean r0 = r3.isVertical
            if (r0 == 0) goto L33
            int r1 = r4 >> 1
            goto L35
        L33:
            int r1 = r5 >> 1
        L35:
            r3.thumbRadius = r1
            boolean r2 = r3.isStartInCenter
            if (r2 == 0) goto L45
            android.graphics.Point r0 = r3.center
            int r4 = r4 >> 1
            int r5 = r5 >> 1
        L41:
            r0.set(r4, r5)
            goto L54
        L45:
            if (r0 == 0) goto L4d
            android.graphics.Point r0 = r3.center
            int r4 = r4 >> 1
            int r5 = r5 - r1
            goto L41
        L4d:
            android.graphics.Point r4 = r3.center
            int r5 = r5 >> 1
            r4.set(r1, r5)
        L54:
            android.graphics.Point r4 = r3.thumbCenter
            android.graphics.Point r5 = r3.center
            int r0 = r5.x
            int r5 = r5.y
            r4.set(r0, r5)
            int r4 = r3.progressRadius
            if (r4 != 0) goto L69
            int r4 = r3.thumbRadius
            int r4 = r4 >> 1
            r3.progressRadius = r4
        L69:
            boolean r4 = r3.isVertical
            if (r4 == 0) goto L70
            int r5 = r3.height
            goto L72
        L70:
            int r5 = r3.width
        L72:
            int r0 = r3.thumbRadius
            int r0 = r0 << 1
            int r5 = r5 - r0
            r3.progressDragSize = r5
            android.graphics.Paint r5 = r3.paint
            if (r4 == 0) goto L80
            int r4 = r3.width
            goto L82
        L80:
            int r4 = r3.height
        L82:
            int r4 = r4 / 2
            float r4 = (float) r4
            r5.setTextSize(r4)
            int r4 = r3.progress
            if (r4 == 0) goto L8f
            r3.updateThumbPos()
        L8f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.view.PositiveAndNegativeSeekBar.onMeasure(int, int):void");
    }

    public void setMax(int i) {
        this.max = i;
    }

    public void setOnProgressChangeListener(OnProgressChangeListener onProgressChangeListener) {
        this.onProgressChangeListener = onProgressChangeListener;
    }

    public void setProgress(int i) {
        if (this.progress != i) {
            this.progress = i;
            updateThumbPos();
            invalidate();
            OnProgressChangeListener onProgressChangeListener = this.onProgressChangeListener;
            if (onProgressChangeListener != null) {
                onProgressChangeListener.onProgressChange(this, i, false);
            }
        }
    }
}
