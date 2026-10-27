package com.jjoe64.graphview.series;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.animation.AccelerateInterpolator;
import com.jjoe64.graphview.series.DataPointInterface;

/* JADX INFO: loaded from: classes2.dex */
public class LineGraphSeries<E extends DataPointInterface> extends BaseSeries<E> {
    private static final long ANIMATION_DURATION = 333;
    private boolean mAnimated;
    private AccelerateInterpolator mAnimationInterpolator;
    private long mAnimationStart;
    private int mAnimationStartFrameNo;
    private Paint mCustomPaint;
    private boolean mDrawAsPath;
    private double mLastAnimatedValue;
    private Paint mPaint;
    private Paint mPaintBackground;
    private Path mPath;
    private Path mPathBackground;
    private LineGraphSeries<E>.Styles mStyles;

    private final class Styles {
        private int backgroundColor;
        private float dataPointsRadius;
        private boolean drawBackground;
        private boolean drawDataPoints;
        private int thickness;

        private Styles() {
            this.thickness = 5;
            this.drawBackground = false;
            this.drawDataPoints = false;
            this.dataPointsRadius = 10.0f;
            this.backgroundColor = Color.argb(100, 172, 218, 255);
        }
    }

    public LineGraphSeries() {
        this.mLastAnimatedValue = Double.NaN;
        this.mDrawAsPath = false;
        init();
    }

    public LineGraphSeries(E[] eArr) {
        super(eArr);
        this.mLastAnimatedValue = Double.NaN;
        this.mDrawAsPath = false;
        init();
    }

    protected void init() {
        this.mStyles = new Styles();
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaintBackground = new Paint();
        this.mPathBackground = new Path();
        this.mPath = new Path();
        this.mAnimationInterpolator = new AccelerateInterpolator(2.0f);
    }

    /* JADX WARN: Removed duplicated region for block: B:81:0x0211  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0254  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0273  */
    @Override // com.jjoe64.graphview.series.Series
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void draw(com.jjoe64.graphview.GraphView r55, android.graphics.Canvas r56, boolean r57) {
        /*
            Method dump skipped, instruction units count: 920
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jjoe64.graphview.series.LineGraphSeries.draw(com.jjoe64.graphview.GraphView, android.graphics.Canvas, boolean):void");
    }

    private void renderLine(Canvas canvas, float[] fArr, Paint paint) {
        canvas.drawLines(fArr, paint);
    }

    public int getThickness() {
        return ((Styles) this.mStyles).thickness;
    }

    public void setThickness(int i) {
        ((Styles) this.mStyles).thickness = i;
    }

    public boolean isDrawBackground() {
        return ((Styles) this.mStyles).drawBackground;
    }

    public void setDrawBackground(boolean z) {
        ((Styles) this.mStyles).drawBackground = z;
    }

    public boolean isDrawDataPoints() {
        return ((Styles) this.mStyles).drawDataPoints;
    }

    public void setDrawDataPoints(boolean z) {
        ((Styles) this.mStyles).drawDataPoints = z;
    }

    public float getDataPointsRadius() {
        return ((Styles) this.mStyles).dataPointsRadius;
    }

    public void setDataPointsRadius(float f) {
        ((Styles) this.mStyles).dataPointsRadius = f;
    }

    public int getBackgroundColor() {
        return ((Styles) this.mStyles).backgroundColor;
    }

    public void setBackgroundColor(int i) {
        ((Styles) this.mStyles).backgroundColor = i;
    }

    public void setCustomPaint(Paint paint) {
        this.mCustomPaint = paint;
    }

    public void setAnimated(boolean z) {
        this.mAnimated = z;
    }

    public boolean isDrawAsPath() {
        return this.mDrawAsPath;
    }

    public void setDrawAsPath(boolean z) {
        this.mDrawAsPath = z;
    }

    @Override // com.jjoe64.graphview.series.BaseSeries
    public void appendData(E e, boolean z, int i, boolean z2) {
        if (!isAnimationActive()) {
            this.mAnimationStart = 0L;
        }
        super.appendData(e, z, i, z2);
    }

    private boolean isAnimationActive() {
        return this.mAnimated && System.currentTimeMillis() - this.mAnimationStart <= ANIMATION_DURATION;
    }
}
