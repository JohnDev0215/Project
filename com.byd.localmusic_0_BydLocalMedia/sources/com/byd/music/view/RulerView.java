package com.byd.music.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.byd.music.R;
import com.byd.music.style.BydUiStyleConfig;
import com.byd.music.utils.LogUtil;

/* JADX INFO: loaded from: classes5.dex */
public class RulerView extends View {
    private static final String TAG = "FMRulerView";
    private int MIN_STEP;
    private float downPointX;
    private float height31;
    private float height32;
    private float height51;
    private int logicValue;
    private int lostFirst;
    private int lostSecond;
    private Paint mBoldLinePaint;
    public OnRuleValueChangeListener mChangeListener;
    private int mHeight;
    private Paint mLinePaint;
    private Paint mPointPaint;
    private int mWidth;
    private int maxStep;
    private int maxValue;
    private int minStep;
    private int minStepWidth;
    private int minValue;
    private int offsetX;
    private int rulerStep;
    private int tempValue;
    private int viewValue;

    public interface OnRuleValueChangeListener {
        void onRulerLogicValueChange(int i);

        void onRulerViewValueChange(int i);
    }

    public RulerView(Context context) {
        this(context, null);
    }

    public RulerView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RulerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.maxStep = 6;
        this.rulerStep = 1;
        this.minStep = 1;
        this.minStepWidth = 15;
        this.offsetX = 0;
        this.downPointX = 0.0f;
        this.minValue = 0;
        this.maxValue = 0;
        this.logicValue = 0;
        this.MIN_STEP = 0;
        init();
    }

    public int getLogicValue() {
        return this.logicValue;
    }

    public void setValidScope(int i, int i2, int i3) {
        LogUtil.d(TAG, "setValidScope enter with minValue = " + i + ", maxValue = " + i2 + ", step = " + i3);
        this.minValue = i;
        this.maxValue = i2;
        this.MIN_STEP = i3;
        this.logicValue = i;
    }

    private void init() {
        Paint paint = new Paint();
        this.mLinePaint = paint;
        paint.setColor(getResources().getColor(R.color.color_ruler_normal));
        this.mLinePaint.setAntiAlias(true);
        this.mLinePaint.setStyle(Paint.Style.STROKE);
        this.mLinePaint.setStrokeWidth(2.0f);
        Paint paint2 = new Paint();
        this.mBoldLinePaint = paint2;
        paint2.setColor(getResources().getColor(R.color.color_ruler_bold));
        this.mBoldLinePaint.setAntiAlias(true);
        this.mBoldLinePaint.setStyle(Paint.Style.STROKE);
        this.mBoldLinePaint.setStrokeWidth(2.0f);
        this.mPointPaint = new Paint();
        if (BydUiStyleConfig.isFancy()) {
            this.mPointPaint.setARGB(255, 255, 255, 255);
        } else {
            this.mPointPaint.setARGB(255, 0, 172, 235);
        }
        this.mPointPaint.setAntiAlias(true);
        this.mPointPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.mPointPaint.setStrokeWidth(3.0f);
        this.viewValue = this.logicValue;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mWidth = View.MeasureSpec.getSize(i);
        int size = View.MeasureSpec.getSize(i2);
        this.mHeight = size;
        this.height31 = (float) (((double) size) / 3.0d);
        this.height51 = (float) (((double) size) / 5.0d);
        this.height32 = (float) (((double) size) / 1.5d);
        LogUtil.d(TAG, "onMeasure: widthMeasureSpec = " + this.mWidth + ", heightMeasureSpec = " + this.mHeight);
    }

    @Override // android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        LogUtil.d(TAG, "onLayout: left = " + i + ", top = " + i2 + ", right = " + i3 + ", bottom = " + i4);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        int i = (this.mWidth / 2) + this.offsetX;
        boolean z = false;
        drawPointLine(canvas, i, 0);
        boolean z2 = false;
        int i2 = 1;
        while (true) {
            int i3 = this.minStepWidth;
            int i4 = i - (i2 * i3);
            int i5 = (i3 * i2) + i;
            if (z || i4 <= 0) {
                z = true;
            } else {
                drawPointLine(canvas, i4, (-i2) * this.MIN_STEP);
            }
            if (z2 || i5 >= this.mWidth) {
                z2 = true;
            } else {
                drawPointLine(canvas, i5, this.MIN_STEP * i2);
            }
            if (z && z2) {
                break;
            } else {
                i2++;
            }
        }
        int i6 = this.mWidth;
        canvas.drawLine(i6 / 2, 0.0f, i6 / 2, this.height32, this.mPointPaint);
        OnRuleValueChangeListener onRuleValueChangeListener = this.mChangeListener;
        if (onRuleValueChangeListener != null) {
            onRuleValueChangeListener.onRulerViewValueChange(this.logicValue);
        }
        canvas.restore();
    }

    private void drawPointLine(Canvas canvas, int i, int i2) {
        int i3 = (this.viewValue + i2) - this.minValue;
        int i4 = this.MIN_STEP;
        int i5 = i3 % (this.maxStep * i4);
        this.lostFirst = i5;
        if (i5 == 0) {
            if (isPointValid(i2)) {
                this.mBoldLinePaint.setAlpha(255);
            } else {
                this.mBoldLinePaint.setAlpha(50);
            }
            float f = i;
            canvas.drawLine(f, this.height51, f, this.height32, this.mBoldLinePaint);
            return;
        }
        int i6 = i5 % (i4 * this.rulerStep);
        this.lostSecond = i6;
        if (i6 == 0) {
            if (isPointValid(i2)) {
                this.mLinePaint.setAlpha(255);
            } else {
                this.mLinePaint.setAlpha(100);
            }
            float f2 = i;
            canvas.drawLine(f2, this.height31, f2, this.height32, this.mLinePaint);
        }
    }

    private boolean isPointValid(int i) {
        if (i == 0) {
            return true;
        }
        int i2 = this.viewValue + i;
        if (i >= 0 || this.minValue > i2) {
            return i > 0 && i2 <= this.maxValue;
        }
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0079  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            boolean r0 = com.byd.music.utils.DeviceInfoUtils.isInCall()
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            int r0 = r8.getAction()
            r2 = 1
            if (r0 == 0) goto La4
            if (r0 == r2) goto L79
            r3 = 2
            if (r0 == r3) goto L19
            r8 = 3
            if (r0 == r8) goto L79
            goto Laa
        L19:
            float r8 = r8.getX()
            float r0 = r7.downPointX
            float r8 = r8 - r0
            int r8 = (int) r8
            r7.offsetX = r8
            if (r8 <= 0) goto L3c
            int r0 = r7.viewValue
            float r8 = (float) r8
            int r1 = r7.minStepWidth
            float r1 = (float) r1
            float r8 = r8 / r1
            double r3 = (double) r8
            double r3 = java.lang.Math.floor(r3)
            int r8 = r7.MIN_STEP
            double r5 = (double) r8
            double r3 = r3 * r5
            double r5 = (double) r8
            double r3 = r3 + r5
            int r8 = (int) r3
            int r0 = r0 - r8
            r7.tempValue = r0
            goto L4c
        L3c:
            int r0 = r7.viewValue
            int r8 = -r8
            float r8 = (float) r8
            int r1 = r7.minStepWidth
            float r1 = (float) r1
            float r8 = r8 / r1
            int r1 = r7.MIN_STEP
            float r1 = (float) r1
            float r8 = r8 * r1
            int r8 = (int) r8
            int r0 = r0 + r8
            r7.tempValue = r0
        L4c:
            int r8 = r7.tempValue
            int r0 = r7.minValue
            int r8 = r8 - r0
            int r1 = r7.MIN_STEP
            int r8 = r8 / r1
            int r8 = r8 * r1
            int r8 = r8 + r0
            r7.logicValue = r8
            if (r8 >= r0) goto L66
            r7.logicValue = r0
            int r8 = r7.viewValue
            int r8 = r8 - r0
            int r8 = r8 / r1
            int r0 = r7.minStepWidth
            int r8 = r8 * r0
            r7.offsetX = r8
            goto L75
        L66:
            int r0 = r7.maxValue
            if (r8 < r0) goto L75
            r7.logicValue = r0
            int r8 = r7.viewValue
            int r8 = r8 - r0
            int r8 = r8 / r1
            int r0 = r7.minStepWidth
            int r8 = r8 * r0
            r7.offsetX = r8
        L75:
            r7.invalidate()
            goto Laa
        L79:
            int r8 = r7.logicValue
            r7.viewValue = r8
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r0 = "onTouchEvent: up with logicValue = "
            r8.append(r0)
            int r0 = r7.logicValue
            r8.append(r0)
            java.lang.String r8 = r8.toString()
            java.lang.String r0 = "FMRulerView"
            com.byd.music.utils.LogUtil.d(r0, r8)
            r7.offsetX = r1
            r7.invalidate()
            com.byd.music.view.RulerView$OnRuleValueChangeListener r8 = r7.mChangeListener
            if (r8 == 0) goto Laa
            int r0 = r7.logicValue
            r8.onRulerLogicValueChange(r0)
            goto Laa
        La4:
            float r8 = r8.getX()
            r7.downPointX = r8
        Laa:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.view.RulerView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setRulerValue(int i) {
        if (i < this.minValue || i > this.maxValue) {
            return;
        }
        this.viewValue = i;
        this.logicValue = i;
        invalidate();
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(motionEvent);
    }

    public void setOnRulerValueChangedListener(OnRuleValueChangeListener onRuleValueChangeListener) {
        this.mChangeListener = onRuleValueChangeListener;
    }
}
