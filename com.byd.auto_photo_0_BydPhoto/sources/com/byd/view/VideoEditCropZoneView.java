package com.byd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.internal.view.SupportMenu;
import com.byd.auto_photo.R;
import com.byd.util.w;

/* JADX INFO: loaded from: classes2.dex */
public class VideoEditCropZoneView extends View {
    private static final int BORDER_WIDTH = 3;
    private static final int BOTTOM = 8;
    private static final int CENTER = 9;
    private static final int LEFT = 5;
    private static final int LEFT_BOTTOM = 3;
    private static final int LEFT_TOP = 1;
    private static final int MIN_SPACING = 140;
    private static final int RIGHT = 7;
    private static final int RIGHT_BOTTOM = 4;
    private static final int RIGHT_TOP = 2;
    private static final int TOP = 6;
    private final Rect clipBound;
    private Point clipCenter;
    private int controlPos;
    private boolean cropEnable;
    private boolean innerLine;
    private final Point lastPoint;
    private int lineColor;
    private int mCH;
    private int mCW;
    private Paint paint;
    private final int[] ratio;
    private int viewHeight;
    private int viewWidth;

    public VideoEditCropZoneView(Context context) {
        super(context);
        this.mCW = 60;
        this.mCH = 5;
        this.clipBound = new Rect();
        this.lastPoint = new Point();
        this.ratio = new int[2];
        this.clipCenter = new Point();
        init();
    }

    public VideoEditCropZoneView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public VideoEditCropZoneView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, 0);
    }

    public VideoEditCropZoneView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mCW = 60;
        this.mCH = 5;
        this.clipBound = new Rect();
        this.lastPoint = new Point();
        this.ratio = new int[2];
        this.clipCenter = new Point();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.VideoEditCropZoneView, i, i2);
        this.innerLine = typedArrayObtainStyledAttributes.getBoolean(1, true);
        this.lineColor = typedArrayObtainStyledAttributes.getColor(0, SupportMenu.CATEGORY_MASK);
        typedArrayObtainStyledAttributes.recycle();
        init();
    }

    private void calcHeightByRatio(boolean z) {
        int[] iArr = this.ratio;
        if (iArr[0] <= 0 || iArr[1] <= 0) {
            return;
        }
        int iWidth = this.clipBound.width();
        int[] iArr2 = this.ratio;
        int i = (iWidth * iArr2[1]) / iArr2[0];
        int i2 = this.viewHeight;
        if (i <= i2) {
            if (z) {
                Rect rect = this.clipBound;
                rect.bottom = rect.top + i;
            } else {
                Rect rect2 = this.clipBound;
                rect2.top = rect2.bottom - i;
            }
            checkBound(0, i);
            return;
        }
        Rect rect3 = this.clipBound;
        rect3.top = 0;
        rect3.bottom = i2;
        int i3 = (i2 * iArr2[0]) / iArr2[1];
        int iWidth2 = rect3.width() - i3;
        Rect rect4 = this.clipBound;
        int i4 = iWidth2 / 2;
        rect4.left += i4;
        rect4.right -= i4;
        checkBound(i3, 0);
    }

    private void calcWidthByRatio(boolean z) {
        int[] iArr = this.ratio;
        if (iArr[0] <= 0 || iArr[1] <= 0) {
            return;
        }
        int iHeight = this.clipBound.height();
        int[] iArr2 = this.ratio;
        int i = (iHeight * iArr2[0]) / iArr2[1];
        int i2 = this.viewWidth;
        if (i <= i2) {
            if (z) {
                Rect rect = this.clipBound;
                rect.right = rect.left + i;
            } else {
                Rect rect2 = this.clipBound;
                rect2.left = rect2.right - i;
            }
            checkBound(i, 0);
            return;
        }
        Rect rect3 = this.clipBound;
        rect3.left = 0;
        rect3.right = i2;
        int i3 = (i2 * iArr2[1]) / iArr2[0];
        int iHeight2 = rect3.height() - i3;
        Rect rect4 = this.clipBound;
        int i4 = iHeight2 / 2;
        rect4.top += i4;
        rect4.bottom -= i4;
        checkBound(0, i3);
    }

    private void changeBottom(int i) {
        Rect rect = this.clipBound;
        int i2 = rect.bottom + i;
        int i3 = this.viewHeight;
        if (i2 > i3) {
            i2 = i3;
        } else {
            int i4 = rect.top;
            if (i2 < i4 + 140) {
                i2 = i4 + 140;
            }
        }
        rect.bottom = i2;
    }

    private void changeCenter(int i, int i2) {
        this.clipBound.offset(i, i2);
        Rect rect = this.clipBound;
        int i3 = rect.left;
        if (i3 < 0 || rect.right > this.viewWidth) {
            int i4 = i3 < 0 ? -i3 : this.viewWidth - rect.right;
            rect.left = i3 + i4;
            rect.right += i4;
        }
        int i5 = rect.top;
        if (i5 < 0 || rect.bottom > this.viewHeight) {
            int i6 = i5 < 0 ? -i5 : this.viewHeight - rect.bottom;
            rect.top = i5 + i6;
            rect.bottom += i6;
        }
    }

    private void changeLeft(int i) {
        Rect rect = this.clipBound;
        int i2 = rect.left + i;
        if (i2 < 0) {
            i2 = 0;
        } else {
            int i3 = this.viewWidth;
            if (i2 > i3 - 140) {
                i2 = i3 - 140;
            }
        }
        rect.left = i2;
    }

    private void changeRight(int i) {
        Rect rect = this.clipBound;
        int i2 = rect.right + i;
        int i3 = this.viewWidth;
        if (i2 > i3) {
            i2 = i3;
        }
        int i4 = rect.left;
        if (i2 < i4 + 140) {
            i2 = i4 + 140;
        }
        rect.right = i2;
    }

    private void changeTop(int i) {
        Rect rect = this.clipBound;
        int i2 = rect.top + i;
        if (i2 < 0) {
            i2 = 0;
        } else {
            int i3 = this.viewHeight;
            if (i2 > i3 - 140) {
                i2 = i3 - 140;
            }
        }
        rect.top = i2;
    }

    private void checkBound(int i, int i2) {
        if (i2 == 0) {
            i2 = this.clipBound.height();
        }
        Rect rect = this.clipBound;
        if (rect.top < 0) {
            rect.top = 0;
            rect.bottom = i2 + 0;
        } else {
            int i3 = rect.bottom;
            int i4 = this.viewHeight;
            if (i3 > i4) {
                rect.bottom = i4;
                rect.top = i4 - i2;
            }
        }
        if (i == 0) {
            i = rect.width();
        }
        Rect rect2 = this.clipBound;
        if (rect2.left < 0) {
            rect2.left = 0;
            rect2.right = 0 + i;
            return;
        }
        int i5 = rect2.right;
        int i6 = this.viewWidth;
        if (i5 > i6) {
            rect2.right = i6;
            rect2.left = i6 - i;
        }
    }

    private void drawControllerPoint(Canvas canvas, Rect rect) {
        this.paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rect.left, rect.top, r0 + this.mCH, r1 + this.mCW, this.paint);
        canvas.drawRect(rect.left, rect.top, r0 + this.mCW, r1 + this.mCH, this.paint);
        int i = rect.right;
        canvas.drawRect(i - this.mCW, rect.top, i, r1 + this.mCH, this.paint);
        int i2 = rect.right;
        canvas.drawRect(i2 - this.mCH, rect.top, i2, r1 + this.mCW, this.paint);
        canvas.drawRect(rect.left, r1 - this.mCW, r0 + this.mCH, rect.bottom, this.paint);
        canvas.drawRect(rect.left, r1 - this.mCH, r0 + this.mCW, rect.bottom, this.paint);
        int i3 = rect.right;
        canvas.drawRect(i3 - this.mCW, r1 - this.mCH, i3, rect.bottom, this.paint);
        int i4 = rect.right;
        canvas.drawRect(i4 - this.mCH, r10 - this.mCW, i4, rect.bottom, this.paint);
    }

    private int getControlPosition(int i, int i2) {
        if (!this.clipBound.contains(i, i2)) {
            return 0;
        }
        Rect rect = this.clipBound;
        int i3 = rect.left;
        if (i >= i3) {
            int i4 = this.mCW;
            if (i <= i3 + i4) {
                int i5 = rect.top;
                if (i2 >= i5 && i2 <= i5 + i4) {
                    return 1;
                }
                int i6 = rect.bottom;
                return (i2 < i6 - i4 || i2 > i6) ? 5 : 3;
            }
        }
        int i7 = rect.top;
        if (i2 >= i7) {
            int i8 = this.mCW;
            if (i2 <= i7 + i8) {
                if (i >= i3 && i <= i3 + i8) {
                    return 1;
                }
                int i9 = rect.right;
                return (i < i9 - i8 || i > i9) ? 6 : 2;
            }
        }
        int i10 = rect.right;
        int i11 = this.mCW;
        if (i >= i10 - i11 && i < i10) {
            if (i2 >= i7 && i2 <= i7 + i11) {
                return 2;
            }
            int i12 = rect.bottom;
            return (i2 < i12 - i11 || i2 >= i12) ? 7 : 4;
        }
        int i13 = rect.bottom;
        if (i2 < i13 - i11 || i2 > i13) {
            return 9;
        }
        if (i > i3 || i > i3 + i11) {
            return (i < i10 - i11 || i > i10) ? 8 : 4;
        }
        return 3;
    }

    private void init() {
        Paint paint = new Paint();
        this.paint = paint;
        paint.setAntiAlias(true);
        this.paint.setStrokeWidth(3.0f);
        this.paint.setColor(this.lineColor);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int i;
        if (!this.cropEnable) {
            w.c("video crop touch -> disable");
            return super.dispatchTouchEvent(motionEvent);
        }
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int action = motionEvent.getAction();
        if (action == 0) {
            int controlPosition = getControlPosition(x, y);
            this.controlPos = controlPosition;
            if (controlPosition > 0) {
                this.lastPoint.set(x, y);
            }
            this.clipCenter.set(this.clipBound.centerX(), this.clipBound.centerY());
        } else if (action == 2 && (i = this.controlPos) > 0) {
            Point point = this.lastPoint;
            int i2 = x - point.x;
            int i3 = y - point.y;
            if (i2 != 0 || i3 != 0) {
                switch (i) {
                    case 1:
                        changeLeft(i2);
                        changeTop(i3);
                        calcWidthByRatio(false);
                        break;
                    case 2:
                        changeRight(i2);
                        changeTop(i3);
                        calcHeightByRatio(false);
                        break;
                    case 3:
                        changeLeft(i2);
                        changeBottom(i3);
                        calcHeightByRatio(true);
                        break;
                    case 4:
                        changeRight(i2);
                        changeBottom(i3);
                        calcWidthByRatio(true);
                        break;
                    case 5:
                        changeLeft(i2);
                        calcHeightByRatio(false);
                        break;
                    case 6:
                        changeTop(i3);
                        calcWidthByRatio(false);
                        break;
                    case 7:
                        changeRight(i2);
                        calcHeightByRatio(true);
                        break;
                    case 8:
                        changeBottom(i3);
                        calcWidthByRatio(true);
                        break;
                    case 9:
                        changeCenter(i2, i3);
                        break;
                }
                this.lastPoint.set(x, y);
                invalidate();
            }
        }
        return true;
    }

    public Rect getClipBound() {
        return this.clipBound;
    }

    public boolean isCropEnable() {
        return this.cropEnable;
    }

    public boolean isCropZoneNoChange() {
        if (!this.clipBound.isEmpty()) {
            Rect rect = this.clipBound;
            if (rect.left != 0 || rect.right != this.viewWidth || rect.top != 0 || rect.bottom != this.viewHeight) {
                return false;
            }
        }
        return true;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (this.cropEnable) {
            drawControllerPoint(canvas, this.clipBound);
            this.paint.setStyle(Paint.Style.STROKE);
            canvas.drawRect(this.clipBound, this.paint);
            if (this.innerLine) {
                int iHeight = this.clipBound.height() / 3;
                Rect rect = this.clipBound;
                int i = rect.top;
                int iWidth = rect.width() / 3;
                int i2 = this.clipBound.left;
                this.paint.setStrokeWidth(1.0f);
                for (int i3 = 0; i3 < 2; i3++) {
                    i += iHeight;
                    Rect rect2 = this.clipBound;
                    float f = i;
                    canvas.drawLine(rect2.left, f, rect2.right, f, this.paint);
                    i2 += iWidth;
                    float f2 = i2;
                    Rect rect3 = this.clipBound;
                    canvas.drawLine(f2, rect3.top, f2, rect3.bottom, this.paint);
                }
            }
        } else if (isCropZoneNoChange()) {
            return;
        }
        canvas.save();
        canvas.clipRect(this.clipBound, Region.Op.DIFFERENCE);
        canvas.drawColor(-1442840576);
        canvas.restore();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.viewWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        this.viewHeight = measuredHeight;
        this.clipBound.set(0, 0, this.viewWidth, measuredHeight);
        int[] iArr = this.ratio;
        iArr[0] = this.viewWidth;
        iArr[1] = this.viewHeight;
    }

    public void resetCrop() {
        this.clipBound.set(0, 0, this.viewWidth, this.viewHeight);
        invalidate();
    }

    public void setClipBound(Rect rect) {
        if (this.clipBound.equals(rect)) {
            return;
        }
        this.clipBound.set(rect);
        invalidate();
    }

    public void setCropEnable(boolean z) {
        this.cropEnable = z;
        invalidate();
    }

    public void setLineColor(int i) {
        this.lineColor = i;
        Paint paint = this.paint;
        if (paint != null) {
            paint.setColor(i);
            invalidate();
        }
    }
}
