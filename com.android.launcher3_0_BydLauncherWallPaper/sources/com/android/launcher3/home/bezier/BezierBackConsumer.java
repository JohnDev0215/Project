package com.android.launcher3.home.bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;

/* JADX INFO: loaded from: classes4.dex */
public class BezierBackConsumer extends SwipeConsumer {
    private static float FACTOR = 0.99f;
    private static final String TAG = "BezierBackConsumer";
    private String content;
    protected int mArrowColor;
    protected int mArrowSize;
    protected int mBezierColor;
    protected boolean mCenter;
    protected int mColor;
    private Context mContext;
    protected Rect mDrawRect;
    protected float mLastThickness;
    protected final Paint mPaint;
    protected final Paint mPaintArrow;
    protected final Paint mPaintText;
    protected final Path mPath;
    protected final Path mPathArrow;
    protected final PointF mPathControl;
    protected final PointF mPathControl1;
    protected final PointF mPathControl2;
    protected final PointF mPathControl3;
    protected final PointF mPathControl4;
    protected final PointF mPathEnd;
    protected final PointF mPathStart;
    protected int mSize;
    protected int mTextColor;
    protected float mThickness;

    public BezierBackConsumer(Context context) {
        Paint paint = new Paint();
        this.mPaint = paint;
        this.mPath = new Path();
        this.mPathStart = new PointF();
        this.mPathControl1 = new PointF();
        this.mPathControl2 = new PointF();
        this.mPathControl = new PointF();
        this.mPathControl3 = new PointF();
        this.mPathControl4 = new PointF();
        this.mPathEnd = new PointF();
        Paint paint2 = new Paint();
        this.mPaintArrow = paint2;
        this.mPathArrow = new Path();
        Paint paint3 = new Paint();
        this.mPaintText = paint3;
        this.mDrawRect = new Rect();
        this.mArrowColor = -855310;
        this.mBezierColor = -2007607722;
        this.mTextColor = -1;
        this.mContext = context;
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.mBezierColor);
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(this.mArrowColor);
        paint2.setStrokeWidth(4.0f);
        paint2.setStrokeCap(Paint.Cap.ROUND);
        paint3.setAntiAlias(true);
        paint3.setStyle(Paint.Style.FILL);
        paint3.setColor(this.mTextColor);
    }

    @Override // com.android.launcher3.home.bezier.SwipeConsumer
    public void onAttachToWrapper(SmartSwipeWrapper wrapper, SwipeHelper swipeHelper) {
        Context context = wrapper.getContext();
        if (this.mSize == 0) {
            this.mSize = SmartSwipe.dp2px(500, context);
        }
        if (this.mArrowSize == 0) {
            this.mArrowSize = SmartSwipe.dp2px(4, context);
        }
        if (this.mOpenDistance == 0) {
            this.mOpenDistance = SmartSwipe.dp2px(100, context);
        }
        swipeHelper.getDragState();
        super.onAttachToWrapper(wrapper, swipeHelper);
        this.mPaintText.setTextSize(SmartSwipe.dp2px(12, context));
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0092  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0096  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00ef  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0109  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x011a  */
    @Override // com.android.launcher3.home.bezier.SwipeConsumer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onSwipeAccepted(int r10, boolean r11, float r12, float r13) {
        /*
            Method dump skipped, instruction units count: 290
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.launcher3.home.bezier.BezierBackConsumer.onSwipeAccepted(int, boolean, float, float):void");
    }

    @Override // com.android.launcher3.home.bezier.SwipeConsumer
    public void onDisplayDistanceChanged(int distanceXToDisplay, int distanceYToDisplay, int dx, int dy) {
        if ((this.mDirection & 3) != 0) {
            this.mThickness = Math.abs(distanceXToDisplay);
        } else if ((this.mDirection & 12) == 0) {
            return;
        } else {
            this.mThickness = Math.abs(distanceYToDisplay);
        }
        if (this.mThickness != this.mLastThickness) {
            ViewCompat.postInvalidateOnAnimation(this.mWrapper);
        }
        this.mLastThickness = this.mThickness;
    }

    @Override // com.android.launcher3.home.bezier.SwipeConsumer
    public void dispatchDraw(Canvas canvas) {
        int i = this.mDirection;
        if (i == 1) {
            PointF pointF = this.mPathControl3;
            PointF pointF2 = this.mPathControl2;
            PointF pointF3 = this.mPathControl;
            float f = this.mThickness;
            pointF3.x = f;
            pointF2.x = f;
            pointF.x = f;
        } else if (i == 2) {
            PointF pointF4 = this.mPathControl3;
            PointF pointF5 = this.mPathControl2;
            PointF pointF6 = this.mPathControl;
            float f2 = this.mWidth - this.mThickness;
            pointF6.x = f2;
            pointF5.x = f2;
            pointF4.x = f2;
        }
        float progress = getProgress();
        this.mPaint.setAlpha(100);
        this.mPath.reset();
        this.mPath.moveTo(this.mPathStart.x, this.mPathStart.y);
        this.mPath.cubicTo(this.mPathControl1.x, this.mPathControl1.y, this.mPathControl2.x, this.mPathControl2.y, this.mPathControl.x, this.mPathControl.y);
        this.mPath.cubicTo(this.mPathControl3.x, this.mPathControl3.y, this.mPathControl4.x, this.mPathControl4.y, this.mPathEnd.x, this.mPathEnd.y);
        canvas.drawPath(this.mPath, this.mPaint);
        drawText(canvas, this.mThickness, progress);
    }

    protected void drawText(Canvas canvas, float curThickness, float percent) {
        float f;
        float length;
        int i = this.mDirection;
        if (i == 1) {
            f = curThickness / 2.0f;
        } else if (i != 2) {
            return;
        } else {
            f = this.mWidth - (curThickness / 2.0f);
        }
        int i2 = 0;
        this.mPaintText.measureText(this.content.substring(0, 1));
        Paint.FontMetrics fontMetrics = this.mPaintText.getFontMetrics();
        float f2 = fontMetrics.bottom - fontMetrics.top;
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(-65536);
        while (i2 < this.content.length()) {
            int i3 = i2 + 1;
            String strSubstring = this.content.substring(i2, i3);
            if (i2 < this.content.length() / 2) {
                length = (this.mHeight / 2.0f) - (((this.content.length() / 2.0f) - i2) * f2);
            } else {
                length = (this.mHeight / 2.0f) + ((i2 - (this.content.length() / 2.0f)) * f2);
            }
            canvas.drawText(strSubstring, f, length + (f2 - (fontMetrics.bottom - fontMetrics.descent)), this.mPaintText);
            i2 = i3;
        }
    }

    protected void drawArrow(Canvas canvas, Paint paint, float curThickness, float percent) {
        boolean z;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float fEnsureBetween = SmartSwipe.ensureBetween(percent, 0.0f, 1.0f);
        float f6 = ((double) fEnsureBetween) >= 0.5d ? (fEnsureBetween - 0.5f) * this.mArrowSize * 2.0f : 0.0f;
        float f7 = curThickness / 2.0f;
        int i = this.mDirection;
        if (i == 1 || i == 2) {
            z = this.mDirection == 1;
            if (!z) {
                f7 = this.mWidth - f7;
            }
            float f8 = this.mPathControl.y;
            f = f7 + (f6 * (z ? 1 : -1));
            f2 = this.mPathControl.y - this.mArrowSize;
            f3 = this.mPathControl.y + this.mArrowSize;
            f4 = f;
            f5 = f7;
            f7 = f8;
        } else {
            if (i != 4 && i != 8) {
                return;
            }
            z = this.mDirection == 4;
            if (!z) {
                f7 = this.mHeight - f7;
            }
            f5 = this.mPathControl.x;
            f2 = (f6 * (z ? 1 : -1)) + f7;
            f = this.mPathControl.x - this.mArrowSize;
            f4 = this.mPathControl.x + this.mArrowSize;
            f3 = f2;
        }
        this.mPaintArrow.setAlpha((int) (fEnsureBetween * 255.0f));
        this.mPathArrow.reset();
        this.mPathArrow.moveTo(f, f2);
        this.mPathArrow.lineTo(f5, f7);
        this.mPathArrow.lineTo(f4, f3);
        canvas.drawPath(this.mPathArrow, paint);
    }

    public BezierBackConsumer setSize(int size) {
        this.mSize = size;
        return this;
    }

    protected int getSize() {
        return this.mSize;
    }

    public int getColor() {
        return this.mColor;
    }

    public BezierBackConsumer setColor(int color) {
        this.mColor = color;
        this.mPaint.setColor(color);
        return this;
    }

    public int getArrowColor() {
        return this.mArrowColor;
    }

    public BezierBackConsumer setArrowColor(int color) {
        this.mArrowColor = color;
        this.mPaintArrow.setColor(color);
        return this;
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public BezierBackConsumer setTextColor(int color) {
        this.mTextColor = color;
        this.mPaintText.setColor(color);
        return this;
    }

    public boolean isCenter() {
        return this.mCenter;
    }

    public BezierBackConsumer setCenter(boolean center) {
        this.mCenter = center;
        return this;
    }
}
