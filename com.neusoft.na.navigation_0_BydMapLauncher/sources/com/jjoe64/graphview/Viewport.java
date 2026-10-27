package com.jjoe64.graphview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.OverScroller;
import androidx.core.view.ViewCompat;
import androidx.core.widget.EdgeEffectCompat;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.Series;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Viewport {
    private int mBackgroundColor;
    private Integer mBorderColor;
    private Paint mBorderPaint;
    protected RectD mCompleteRange;
    protected RectD mCurrentViewport;
    private boolean mDrawBorder;
    private EdgeEffectCompat mEdgeEffectBottom;
    private EdgeEffectCompat mEdgeEffectLeft;
    private EdgeEffectCompat mEdgeEffectRight;
    private EdgeEffectCompat mEdgeEffectTop;
    protected GestureDetector mGestureDetector;
    private final GestureDetector.SimpleOnGestureListener mGestureListener;
    private final GraphView mGraphView;
    private boolean mIsScalable;
    private boolean mIsScrollable;
    protected double mMaxXAxisSize;
    protected double mMaxYAxisSize;
    protected OnXAxisBoundsChangedListener mOnXAxisBoundsChangedListener;
    private Paint mPaint;
    protected ScaleGestureDetector mScaleGestureDetector;
    private final ScaleGestureDetector.OnScaleGestureListener mScaleGestureListener;
    protected boolean mScalingActive;
    protected OverScroller mScroller;
    private boolean mXAxisBoundsManual;
    protected AxisBoundsStatus mXAxisBoundsStatus;
    private boolean mYAxisBoundsManual;
    protected AxisBoundsStatus mYAxisBoundsStatus;
    protected boolean scalableY;
    private boolean scrollableY;
    protected double referenceY = Double.NaN;
    protected double referenceX = Double.NaN;

    public enum AxisBoundsStatus {
        INITIAL,
        AUTO_ADJUSTED,
        FIX
    }

    public interface OnXAxisBoundsChangedListener {

        public enum Reason {
            SCROLL,
            SCALE
        }

        void onXAxisBoundsChanged(double d, double d2, Reason reason);
    }

    public void computeScroll() {
    }

    protected double getReferenceX() {
        if (!isXAxisBoundsManual() || this.mGraphView.getGridLabelRenderer().isHumanRounding()) {
            return 0.0d;
        }
        if (Double.isNaN(this.referenceX)) {
            this.referenceX = getMinX(false);
        }
        return this.referenceX;
    }

    Viewport(GraphView graphView) {
        ScaleGestureDetector.OnScaleGestureListener onScaleGestureListener = new ScaleGestureDetector.OnScaleGestureListener() { // from class: com.jjoe64.graphview.Viewport.1
            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                float scaleFactor;
                double dWidth = Viewport.this.mCurrentViewport.width();
                if (Viewport.this.mMaxXAxisSize != 0.0d && dWidth > Viewport.this.mMaxXAxisSize) {
                    dWidth = Viewport.this.mMaxXAxisSize;
                }
                double d = Viewport.this.mCurrentViewport.left + (dWidth / 2.0d);
                if (Viewport.this.scalableY) {
                    scaleFactor = scaleGestureDetector.getCurrentSpanX() / scaleGestureDetector.getPreviousSpanX();
                } else {
                    scaleFactor = scaleGestureDetector.getScaleFactor();
                }
                double d2 = dWidth / ((double) scaleFactor);
                Viewport.this.mCurrentViewport.left = d - (d2 / 2.0d);
                Viewport.this.mCurrentViewport.right = Viewport.this.mCurrentViewport.left + d2;
                double minX = Viewport.this.getMinX(true);
                if (Viewport.this.mCurrentViewport.left < minX) {
                    Viewport.this.mCurrentViewport.left = minX;
                    Viewport.this.mCurrentViewport.right = Viewport.this.mCurrentViewport.left + d2;
                }
                double maxX = Viewport.this.getMaxX(true);
                if (d2 == 0.0d) {
                    Viewport.this.mCurrentViewport.right = maxX;
                }
                double d3 = (Viewport.this.mCurrentViewport.left + d2) - maxX;
                if (d3 > 0.0d) {
                    if (Viewport.this.mCurrentViewport.left - d3 > minX) {
                        Viewport.this.mCurrentViewport.left -= d3;
                        Viewport.this.mCurrentViewport.right = Viewport.this.mCurrentViewport.left + d2;
                    } else {
                        Viewport.this.mCurrentViewport.left = minX;
                        Viewport.this.mCurrentViewport.right = maxX;
                    }
                }
                if (Viewport.this.scalableY) {
                    boolean z = Viewport.this.mGraphView.mSecondScale != null;
                    double dHeight = Viewport.this.mCurrentViewport.height() * (-1.0d);
                    if (Viewport.this.mMaxYAxisSize != 0.0d && dHeight > Viewport.this.mMaxYAxisSize) {
                        dHeight = Viewport.this.mMaxYAxisSize;
                    }
                    double d4 = Viewport.this.mCurrentViewport.bottom + (dHeight / 2.0d);
                    double currentSpanY = dHeight / ((double) (scaleGestureDetector.getCurrentSpanY() / scaleGestureDetector.getPreviousSpanY()));
                    Viewport.this.mCurrentViewport.bottom = d4 - (currentSpanY / 2.0d);
                    Viewport.this.mCurrentViewport.top = Viewport.this.mCurrentViewport.bottom + currentSpanY;
                    if (z) {
                        double dHeight2 = Viewport.this.mGraphView.mSecondScale.mCurrentViewport.height() * (-1.0d);
                        double d5 = Viewport.this.mGraphView.mSecondScale.mCurrentViewport.bottom + (dHeight2 / 2.0d);
                        double currentSpanY2 = dHeight2 / ((double) (scaleGestureDetector.getCurrentSpanY() / scaleGestureDetector.getPreviousSpanY()));
                        Viewport.this.mGraphView.mSecondScale.mCurrentViewport.bottom = d5 - (currentSpanY2 / 2.0d);
                        Viewport.this.mGraphView.mSecondScale.mCurrentViewport.top = Viewport.this.mGraphView.mSecondScale.mCurrentViewport.bottom + currentSpanY2;
                    } else {
                        double minY = Viewport.this.getMinY(true);
                        if (Viewport.this.mCurrentViewport.bottom < minY) {
                            Viewport.this.mCurrentViewport.bottom = minY;
                            Viewport.this.mCurrentViewport.top = Viewport.this.mCurrentViewport.bottom + currentSpanY;
                        }
                        double maxY = Viewport.this.getMaxY(true);
                        if (currentSpanY == 0.0d) {
                            Viewport.this.mCurrentViewport.top = maxY;
                        }
                        double d6 = (Viewport.this.mCurrentViewport.bottom + currentSpanY) - maxY;
                        if (d6 > 0.0d) {
                            if (Viewport.this.mCurrentViewport.bottom - d6 > minY) {
                                Viewport.this.mCurrentViewport.bottom -= d6;
                                Viewport.this.mCurrentViewport.top = Viewport.this.mCurrentViewport.bottom + currentSpanY;
                            } else {
                                Viewport.this.mCurrentViewport.bottom = minY;
                                Viewport.this.mCurrentViewport.top = maxY;
                            }
                        }
                    }
                }
                Viewport.this.mGraphView.onDataChanged(true, false);
                ViewCompat.postInvalidateOnAnimation(Viewport.this.mGraphView);
                return true;
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                if (!Viewport.this.mIsScalable) {
                    return false;
                }
                Viewport.this.mScalingActive = true;
                return true;
            }

            @Override // android.view.ScaleGestureDetector.OnScaleGestureListener
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
                Viewport.this.mScalingActive = false;
                if (Viewport.this.mOnXAxisBoundsChangedListener != null) {
                    Viewport.this.mOnXAxisBoundsChangedListener.onXAxisBoundsChanged(Viewport.this.getMinX(false), Viewport.this.getMaxX(false), OnXAxisBoundsChangedListener.Reason.SCALE);
                }
                ViewCompat.postInvalidateOnAnimation(Viewport.this.mGraphView);
            }
        };
        this.mScaleGestureListener = onScaleGestureListener;
        GestureDetector.SimpleOnGestureListener simpleOnGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.jjoe64.graphview.Viewport.2
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                if (!Viewport.this.mIsScrollable || Viewport.this.mScalingActive) {
                    return false;
                }
                Viewport.this.releaseEdgeEffects();
                Viewport.this.mScroller.forceFinished(true);
                ViewCompat.postInvalidateOnAnimation(Viewport.this.mGraphView);
                return true;
            }

            /* JADX WARN: Removed duplicated region for block: B:44:0x01b5 A[PHI: r1 r17 r18 r27 r28
  0x01b5: PHI (r1v67 double) = (r1v66 double), (r1v79 double) binds: [B:43:0x01b3, B:40:0x0196] A[DONT_GENERATE, DONT_INLINE]
  0x01b5: PHI (r17v3 int) = (r17v2 int), (r17v5 int) binds: [B:43:0x01b3, B:40:0x0196] A[DONT_GENERATE, DONT_INLINE]
  0x01b5: PHI (r18v3 int) = (r18v2 int), (r18v5 int) binds: [B:43:0x01b3, B:40:0x0196] A[DONT_GENERATE, DONT_INLINE]
  0x01b5: PHI (r27v5 int) = (r27v4 int), (r27v7 int) binds: [B:43:0x01b3, B:40:0x0196] A[DONT_GENERATE, DONT_INLINE]
  0x01b5: PHI (r28v4 int) = (r28v3 int), (r28v6 int) binds: [B:43:0x01b3, B:40:0x0196] A[DONT_GENERATE, DONT_INLINE]] */
            /* JADX WARN: Removed duplicated region for block: B:47:0x01d0  */
            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public boolean onScroll(android.view.MotionEvent r26, android.view.MotionEvent r27, float r28, float r29) {
                /*
                    Method dump skipped, instruction units count: 789
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.jjoe64.graphview.Viewport.AnonymousClass2.onScroll(android.view.MotionEvent, android.view.MotionEvent, float, float):boolean");
            }
        };
        this.mGestureListener = simpleOnGestureListener;
        this.mCurrentViewport = new RectD();
        this.mMaxXAxisSize = 0.0d;
        this.mMaxYAxisSize = 0.0d;
        this.mCompleteRange = new RectD();
        this.mScroller = new OverScroller(graphView.getContext());
        this.mEdgeEffectTop = new EdgeEffectCompat(graphView.getContext());
        this.mEdgeEffectBottom = new EdgeEffectCompat(graphView.getContext());
        this.mEdgeEffectLeft = new EdgeEffectCompat(graphView.getContext());
        this.mEdgeEffectRight = new EdgeEffectCompat(graphView.getContext());
        this.mGestureDetector = new GestureDetector(graphView.getContext(), simpleOnGestureListener);
        this.mScaleGestureDetector = new ScaleGestureDetector(graphView.getContext(), onScaleGestureListener);
        this.mGraphView = graphView;
        this.mXAxisBoundsStatus = AxisBoundsStatus.INITIAL;
        this.mYAxisBoundsStatus = AxisBoundsStatus.INITIAL;
        this.mBackgroundColor = 0;
        this.mPaint = new Paint();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return this.mGestureDetector.onTouchEvent(motionEvent) | this.mScaleGestureDetector.onTouchEvent(motionEvent);
    }

    public void setXAxisBoundsStatus(AxisBoundsStatus axisBoundsStatus) {
        this.mXAxisBoundsStatus = axisBoundsStatus;
    }

    public void setYAxisBoundsStatus(AxisBoundsStatus axisBoundsStatus) {
        this.mYAxisBoundsStatus = axisBoundsStatus;
    }

    public boolean isScrollable() {
        return this.mIsScrollable;
    }

    public void setScrollable(boolean z) {
        this.mIsScrollable = z;
    }

    public AxisBoundsStatus getXAxisBoundsStatus() {
        return this.mXAxisBoundsStatus;
    }

    public AxisBoundsStatus getYAxisBoundsStatus() {
        return this.mYAxisBoundsStatus;
    }

    public void calcCompleteRange() {
        List<Series> series = this.mGraphView.getSeries();
        ArrayList<Series> arrayList = new ArrayList(this.mGraphView.getSeries());
        if (this.mGraphView.mSecondScale != null) {
            arrayList.addAll(this.mGraphView.mSecondScale.getSeries());
        }
        this.mCompleteRange.set(0.0d, 0.0d, 0.0d, 0.0d);
        if (!arrayList.isEmpty() && !((Series) arrayList.get(0)).isEmpty()) {
            double lowestValueX = ((Series) arrayList.get(0)).getLowestValueX();
            for (Series series2 : arrayList) {
                if (!series2.isEmpty() && lowestValueX > series2.getLowestValueX()) {
                    lowestValueX = series2.getLowestValueX();
                }
            }
            this.mCompleteRange.left = lowestValueX;
            double highestValueX = ((Series) arrayList.get(0)).getHighestValueX();
            for (Series series3 : arrayList) {
                if (!series3.isEmpty() && highestValueX < series3.getHighestValueX()) {
                    highestValueX = series3.getHighestValueX();
                }
            }
            this.mCompleteRange.right = highestValueX;
            if (!series.isEmpty() && !series.get(0).isEmpty()) {
                double lowestValueY = series.get(0).getLowestValueY();
                for (Series series4 : series) {
                    if (!series4.isEmpty() && lowestValueY > series4.getLowestValueY()) {
                        lowestValueY = series4.getLowestValueY();
                    }
                }
                this.mCompleteRange.bottom = lowestValueY;
                double highestValueY = series.get(0).getHighestValueY();
                for (Series series5 : series) {
                    if (!series5.isEmpty() && highestValueY < series5.getHighestValueY()) {
                        highestValueY = series5.getHighestValueY();
                    }
                }
                this.mCompleteRange.top = highestValueY;
            }
        }
        if (this.mYAxisBoundsStatus == AxisBoundsStatus.AUTO_ADJUSTED) {
            this.mYAxisBoundsStatus = AxisBoundsStatus.INITIAL;
        }
        if (this.mYAxisBoundsStatus == AxisBoundsStatus.INITIAL) {
            this.mCurrentViewport.top = this.mCompleteRange.top;
            this.mCurrentViewport.bottom = this.mCompleteRange.bottom;
        }
        if (this.mXAxisBoundsStatus == AxisBoundsStatus.AUTO_ADJUSTED) {
            this.mXAxisBoundsStatus = AxisBoundsStatus.INITIAL;
        }
        if (this.mXAxisBoundsStatus == AxisBoundsStatus.INITIAL) {
            this.mCurrentViewport.left = this.mCompleteRange.left;
            this.mCurrentViewport.right = this.mCompleteRange.right;
        } else if (this.mXAxisBoundsManual && !this.mYAxisBoundsManual && this.mCompleteRange.width() != 0.0d) {
            Iterator<Series> it = series.iterator();
            double d = Double.MAX_VALUE;
            while (it.hasNext()) {
                Iterator values = it.next().getValues(this.mCurrentViewport.left, this.mCurrentViewport.right);
                while (values.hasNext()) {
                    double y = ((DataPointInterface) values.next()).getY();
                    if (d > y) {
                        d = y;
                    }
                }
            }
            if (d != Double.MAX_VALUE) {
                this.mCurrentViewport.bottom = d;
            }
            Iterator<Series> it2 = series.iterator();
            double d2 = Double.MIN_VALUE;
            while (it2.hasNext()) {
                Iterator values2 = it2.next().getValues(this.mCurrentViewport.left, this.mCurrentViewport.right);
                while (values2.hasNext()) {
                    double y2 = ((DataPointInterface) values2.next()).getY();
                    if (d2 < y2) {
                        d2 = y2;
                    }
                }
            }
            if (d2 != Double.MIN_VALUE) {
                this.mCurrentViewport.top = d2;
            }
        }
        if (this.mCurrentViewport.left == this.mCurrentViewport.right) {
            this.mCurrentViewport.right += 1.0d;
        }
        if (this.mCurrentViewport.top == this.mCurrentViewport.bottom) {
            this.mCurrentViewport.top += 1.0d;
        }
    }

    public double getMinX(boolean z) {
        if (z) {
            return this.mCompleteRange.left;
        }
        return this.mCurrentViewport.left;
    }

    public double getMaxX(boolean z) {
        if (z) {
            return this.mCompleteRange.right;
        }
        return this.mCurrentViewport.right;
    }

    public double getMinY(boolean z) {
        if (z) {
            return this.mCompleteRange.bottom;
        }
        return this.mCurrentViewport.bottom;
    }

    public double getMaxY(boolean z) {
        if (z) {
            return this.mCompleteRange.top;
        }
        return this.mCurrentViewport.top;
    }

    public void setMaxY(double d) {
        this.mCurrentViewport.top = d;
    }

    public void setMinY(double d) {
        this.mCurrentViewport.bottom = d;
    }

    public void setMaxX(double d) {
        this.mCurrentViewport.right = d;
    }

    public void setMinX(double d) {
        this.mCurrentViewport.left = d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseEdgeEffects() {
        this.mEdgeEffectLeft.onRelease();
        this.mEdgeEffectRight.onRelease();
        this.mEdgeEffectTop.onRelease();
        this.mEdgeEffectBottom.onRelease();
    }

    private void fling(int i, int i2) {
        releaseEdgeEffects();
        int iWidth = ((int) ((this.mCurrentViewport.width() / this.mCompleteRange.width()) * ((double) this.mGraphView.getGraphContentWidth()))) - this.mGraphView.getGraphContentWidth();
        int iHeight = ((int) ((this.mCurrentViewport.height() / this.mCompleteRange.height()) * ((double) this.mGraphView.getGraphContentHeight()))) - this.mGraphView.getGraphContentHeight();
        int iWidth2 = ((int) ((this.mCurrentViewport.left - this.mCompleteRange.left) / this.mCompleteRange.width())) * iWidth;
        int iHeight2 = ((int) ((this.mCurrentViewport.top - this.mCompleteRange.top) / this.mCompleteRange.height())) * iHeight;
        this.mScroller.forceFinished(true);
        this.mScroller.fling(iWidth2, iHeight2, i, 0, 0, iWidth, 0, iHeight, this.mGraphView.getGraphContentWidth() / 2, this.mGraphView.getGraphContentHeight() / 2);
        ViewCompat.postInvalidateOnAnimation(this.mGraphView);
    }

    private void drawEdgeEffectsUnclipped(Canvas canvas) {
        boolean zDraw;
        if (this.mEdgeEffectTop.isFinished()) {
            zDraw = false;
        } else {
            int iSave = canvas.save();
            canvas.translate(this.mGraphView.getGraphContentLeft(), this.mGraphView.getGraphContentTop());
            this.mEdgeEffectTop.setSize(this.mGraphView.getGraphContentWidth(), this.mGraphView.getGraphContentHeight());
            zDraw = this.mEdgeEffectTop.draw(canvas);
            canvas.restoreToCount(iSave);
        }
        if (!this.mEdgeEffectBottom.isFinished()) {
            int iSave2 = canvas.save();
            canvas.translate(this.mGraphView.getGraphContentLeft(), this.mGraphView.getGraphContentTop() + this.mGraphView.getGraphContentHeight());
            canvas.rotate(180.0f, this.mGraphView.getGraphContentWidth() / 2, 0.0f);
            this.mEdgeEffectBottom.setSize(this.mGraphView.getGraphContentWidth(), this.mGraphView.getGraphContentHeight());
            if (this.mEdgeEffectBottom.draw(canvas)) {
                zDraw = true;
            }
            canvas.restoreToCount(iSave2);
        }
        if (!this.mEdgeEffectLeft.isFinished()) {
            int iSave3 = canvas.save();
            canvas.translate(this.mGraphView.getGraphContentLeft(), this.mGraphView.getGraphContentTop() + this.mGraphView.getGraphContentHeight());
            canvas.rotate(-90.0f, 0.0f, 0.0f);
            this.mEdgeEffectLeft.setSize(this.mGraphView.getGraphContentHeight(), this.mGraphView.getGraphContentWidth());
            if (this.mEdgeEffectLeft.draw(canvas)) {
                zDraw = true;
            }
            canvas.restoreToCount(iSave3);
        }
        if (!this.mEdgeEffectRight.isFinished()) {
            int iSave4 = canvas.save();
            canvas.translate(this.mGraphView.getGraphContentLeft() + this.mGraphView.getGraphContentWidth(), this.mGraphView.getGraphContentTop());
            canvas.rotate(90.0f, 0.0f, 0.0f);
            this.mEdgeEffectRight.setSize(this.mGraphView.getGraphContentHeight(), this.mGraphView.getGraphContentWidth());
            boolean z = this.mEdgeEffectRight.draw(canvas) ? true : zDraw;
            canvas.restoreToCount(iSave4);
            zDraw = z;
        }
        if (zDraw) {
            ViewCompat.postInvalidateOnAnimation(this.mGraphView);
        }
    }

    public void drawFirst(Canvas canvas) {
        int i = this.mBackgroundColor;
        if (i != 0) {
            this.mPaint.setColor(i);
            canvas.drawRect(this.mGraphView.getGraphContentLeft(), this.mGraphView.getGraphContentTop(), this.mGraphView.getGraphContentLeft() + this.mGraphView.getGraphContentWidth(), this.mGraphView.getGraphContentTop() + this.mGraphView.getGraphContentHeight(), this.mPaint);
        }
        if (this.mDrawBorder) {
            Paint paint = this.mBorderPaint;
            if (paint == null) {
                paint = this.mPaint;
                paint.setColor(getBorderColor());
            }
            Paint paint2 = paint;
            canvas.drawLine(this.mGraphView.getGraphContentLeft(), this.mGraphView.getGraphContentTop(), this.mGraphView.getGraphContentLeft(), this.mGraphView.getGraphContentTop() + this.mGraphView.getGraphContentHeight(), paint2);
            canvas.drawLine(this.mGraphView.getGraphContentLeft(), this.mGraphView.getGraphContentTop() + this.mGraphView.getGraphContentHeight(), this.mGraphView.getGraphContentLeft() + this.mGraphView.getGraphContentWidth(), this.mGraphView.getGraphContentTop() + this.mGraphView.getGraphContentHeight(), paint2);
            if (this.mGraphView.mSecondScale != null) {
                canvas.drawLine(this.mGraphView.getGraphContentLeft() + this.mGraphView.getGraphContentWidth(), this.mGraphView.getGraphContentTop(), this.mGraphView.getGraphContentLeft() + this.mGraphView.getGraphContentWidth(), this.mGraphView.getGraphContentTop() + this.mGraphView.getGraphContentHeight(), paint);
            }
        }
    }

    public void draw(Canvas canvas) {
        drawEdgeEffectsUnclipped(canvas);
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public void setBackgroundColor(int i) {
        this.mBackgroundColor = i;
    }

    public boolean isScalable() {
        return this.mIsScalable;
    }

    public void setScalable(boolean z) {
        this.mIsScalable = z;
        if (z) {
            this.mIsScrollable = true;
            setXAxisBoundsManual(true);
        }
    }

    public boolean isXAxisBoundsManual() {
        return this.mXAxisBoundsManual;
    }

    public void setXAxisBoundsManual(boolean z) {
        this.mXAxisBoundsManual = z;
        if (z) {
            this.mXAxisBoundsStatus = AxisBoundsStatus.FIX;
        }
    }

    public boolean isYAxisBoundsManual() {
        return this.mYAxisBoundsManual;
    }

    public void setYAxisBoundsManual(boolean z) {
        this.mYAxisBoundsManual = z;
        if (z) {
            this.mYAxisBoundsStatus = AxisBoundsStatus.FIX;
        }
    }

    public void scrollToEnd() {
        if (this.mXAxisBoundsManual) {
            double dWidth = this.mCurrentViewport.width();
            this.mCurrentViewport.right = this.mCompleteRange.right;
            this.mCurrentViewport.left = this.mCompleteRange.right - dWidth;
            this.mGraphView.onDataChanged(true, false);
            return;
        }
        Log.w("GraphView", "scrollToEnd works only with manual x axis bounds");
    }

    public OnXAxisBoundsChangedListener getOnXAxisBoundsChangedListener() {
        return this.mOnXAxisBoundsChangedListener;
    }

    public void setOnXAxisBoundsChangedListener(OnXAxisBoundsChangedListener onXAxisBoundsChangedListener) {
        this.mOnXAxisBoundsChangedListener = onXAxisBoundsChangedListener;
    }

    public void setDrawBorder(boolean z) {
        this.mDrawBorder = z;
    }

    public int getBorderColor() {
        Integer num = this.mBorderColor;
        if (num != null) {
            return num.intValue();
        }
        return this.mGraphView.getGridLabelRenderer().getGridColor();
    }

    public void setBorderColor(Integer num) {
        this.mBorderColor = num;
    }

    public void setBorderPaint(Paint paint) {
        this.mBorderPaint = paint;
    }

    public void setScrollableY(boolean z) {
        this.scrollableY = z;
    }

    protected double getReferenceY() {
        if (!isYAxisBoundsManual() || this.mGraphView.getGridLabelRenderer().isHumanRounding()) {
            return 0.0d;
        }
        if (Double.isNaN(this.referenceY)) {
            this.referenceY = getMinY(false);
        }
        return this.referenceY;
    }

    public void setScalableY(boolean z) {
        if (z) {
            this.scrollableY = true;
            setScalable(true);
        }
        this.scalableY = z;
    }

    public double getMaxXAxisSize() {
        return this.mMaxXAxisSize;
    }

    public double getMaxYAxisSize() {
        return this.mMaxYAxisSize;
    }

    public void setMaxXAxisSize(double d) {
        this.mMaxXAxisSize = d;
    }

    public void setMaxYAxisSize(double d) {
        this.mMaxYAxisSize = d;
    }
}
