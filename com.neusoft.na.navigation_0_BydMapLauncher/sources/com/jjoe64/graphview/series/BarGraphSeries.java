package com.jjoe64.graphview.series;

import android.graphics.Paint;
import android.view.animation.AccelerateInterpolator;
import com.jjoe64.graphview.RectD;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.DataPointInterface;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class BarGraphSeries<E extends DataPointInterface> extends BaseSeries<E> {
    private static final long ANIMATION_DURATION = 333;
    private boolean mAnimated;
    private AccelerateInterpolator mAnimationInterpolator;
    private long mAnimationStart;
    private int mAnimationStartFrameNo;
    private Paint mCustomPaint;
    private Map<RectD, E> mDataPoints;
    private double mDataWidth;
    private boolean mDrawValuesOnTop;
    private double mLastAnimatedValue;
    private Paint mPaint;
    private int mSpacing;
    private ValueDependentColor<E> mValueDependentColor;
    private int mValuesOnTopColor;
    private float mValuesOnTopSize;

    public BarGraphSeries() {
        this.mDataPoints = new HashMap();
        this.mLastAnimatedValue = Double.NaN;
        this.mPaint = new Paint();
    }

    public BarGraphSeries(E[] eArr) {
        super(eArr);
        this.mDataPoints = new HashMap();
        this.mLastAnimatedValue = Double.NaN;
        this.mPaint = new Paint();
        this.mAnimationInterpolator = new AccelerateInterpolator(2.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00bd A[PHI: r13 r14
  0x00bd: PHI (r13v5 int) = (r13v1 int), (r13v7 int) binds: [B:13:0x0076, B:20:0x008a] A[DONT_GENERATE, DONT_INLINE]
  0x00bd: PHI (r14v21 int) = (r14v1 int), (r14v23 int) binds: [B:13:0x0076, B:20:0x008a] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // com.jjoe64.graphview.series.Series
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(com.jjoe64.graphview.GraphView r55, android.graphics.Canvas r56, boolean r57) {
        /*
            Method dump skipped, instruction units count: 771
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jjoe64.graphview.series.BarGraphSeries.draw(com.jjoe64.graphview.GraphView, android.graphics.Canvas, boolean):void");
    }

    public ValueDependentColor<E> getValueDependentColor() {
        return this.mValueDependentColor;
    }

    public void setValueDependentColor(ValueDependentColor<E> valueDependentColor) {
        this.mValueDependentColor = valueDependentColor;
    }

    public int getSpacing() {
        return this.mSpacing;
    }

    public void setSpacing(int i) {
        this.mSpacing = i;
    }

    public double getDataWidth() {
        return this.mDataWidth;
    }

    public void setDataWidth(double d) {
        this.mDataWidth = d;
    }

    public boolean isDrawValuesOnTop() {
        return this.mDrawValuesOnTop;
    }

    public void setDrawValuesOnTop(boolean z) {
        this.mDrawValuesOnTop = z;
    }

    public int getValuesOnTopColor() {
        return this.mValuesOnTopColor;
    }

    public void setValuesOnTopColor(int i) {
        this.mValuesOnTopColor = i;
    }

    public float getValuesOnTopSize() {
        return this.mValuesOnTopSize;
    }

    public void setValuesOnTopSize(float f) {
        this.mValuesOnTopSize = f;
    }

    @Override // com.jjoe64.graphview.series.BaseSeries
    protected void resetDataPoints() {
        this.mDataPoints.clear();
    }

    @Override // com.jjoe64.graphview.series.BaseSeries
    protected E findDataPoint(float f, float f2) {
        for (Map.Entry<RectD, E> entry : this.mDataPoints.entrySet()) {
            double d = f;
            if (d >= entry.getKey().left && d <= entry.getKey().right) {
                double d2 = f2;
                if (d2 >= entry.getKey().top && d2 <= entry.getKey().bottom) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public Paint getCustomPaint() {
        return this.mCustomPaint;
    }

    public void setCustomPaint(Paint paint) {
        this.mCustomPaint = paint;
    }

    public void setAnimated(boolean z) {
        this.mAnimated = z;
    }

    public boolean isAnimated() {
        return this.mAnimated;
    }
}
