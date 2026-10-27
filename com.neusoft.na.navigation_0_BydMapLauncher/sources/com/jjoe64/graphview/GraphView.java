package com.jjoe64.graphview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.core.view.ViewCompat;
import com.jjoe64.graphview.series.Series;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GraphView extends View {
    private GridLabelRenderer mGridLabelRenderer;
    private LegendRenderer mLegendRenderer;
    private Paint mPaintTitle;
    private Paint mPreviewPaint;
    protected SecondScale mSecondScale;
    private List<Series> mSeries;
    private Styles mStyles;
    private TapDetector mTapDetector;
    private String mTitle;
    private Viewport mViewport;

    private static final class Styles {
        int titleColor;
        float titleTextSize;

        private Styles() {
        }
    }

    private class TapDetector {
        private long lastDown;
        private PointF lastPoint;

        private TapDetector() {
        }

        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0) {
                this.lastDown = System.currentTimeMillis();
                this.lastPoint = new PointF(motionEvent.getX(), motionEvent.getY());
                return false;
            }
            if (this.lastDown <= 0 || motionEvent.getAction() != 2) {
                return motionEvent.getAction() == 1 && System.currentTimeMillis() - this.lastDown < 400;
            }
            if (Math.abs(motionEvent.getX() - this.lastPoint.x) <= 60.0f && Math.abs(motionEvent.getY() - this.lastPoint.y) <= 60.0f) {
                return false;
            }
            this.lastDown = 0L;
            return false;
        }
    }

    public GraphView(Context context) {
        super(context);
        init();
    }

    public GraphView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public GraphView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    protected void init() {
        Paint paint = new Paint();
        this.mPreviewPaint = paint;
        paint.setTextAlign(Paint.Align.CENTER);
        this.mPreviewPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
        this.mPreviewPaint.setTextSize(50.0f);
        this.mStyles = new Styles();
        this.mViewport = new Viewport(this);
        this.mGridLabelRenderer = new GridLabelRenderer(this);
        this.mLegendRenderer = new LegendRenderer(this);
        this.mSeries = new ArrayList();
        this.mPaintTitle = new Paint();
        this.mTapDetector = new TapDetector();
        loadStyles();
    }

    protected void loadStyles() {
        this.mStyles.titleColor = this.mGridLabelRenderer.getHorizontalLabelsColor();
        this.mStyles.titleTextSize = this.mGridLabelRenderer.getTextSize();
    }

    public GridLabelRenderer getGridLabelRenderer() {
        return this.mGridLabelRenderer;
    }

    public void addSeries(Series series) {
        series.onGraphViewAttached(this);
        this.mSeries.add(series);
        onDataChanged(false, false);
    }

    public List<Series> getSeries() {
        return this.mSeries;
    }

    public void onDataChanged(boolean z, boolean z2) {
        this.mViewport.calcCompleteRange();
        SecondScale secondScale = this.mSecondScale;
        if (secondScale != null) {
            secondScale.calcCompleteRange();
        }
        this.mGridLabelRenderer.invalidate(z, z2);
        postInvalidate();
    }

    protected void drawGraphElements(Canvas canvas) {
        if (!canvas.isHardwareAccelerated()) {
            throw new IllegalStateException("GraphView must be used in hardware accelerated mode.You can use android:hardwareAccelerated=\"true\" on your activity. Read this for more info:https://developer.android.com/guide/topics/graphics/hardware-accel.html");
        }
        drawTitle(canvas);
        this.mViewport.drawFirst(canvas);
        this.mGridLabelRenderer.draw(canvas);
        Iterator<Series> it = this.mSeries.iterator();
        while (it.hasNext()) {
            it.next().draw(this, canvas, false);
        }
        SecondScale secondScale = this.mSecondScale;
        if (secondScale != null) {
            Iterator<Series> it2 = secondScale.getSeries().iterator();
            while (it2.hasNext()) {
                it2.next().draw(this, canvas, true);
            }
        }
        this.mViewport.draw(canvas);
        this.mLegendRenderer.draw(canvas);
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        if (isInEditMode()) {
            canvas.drawColor(Color.rgb(200, 200, 200));
            canvas.drawText("GraphView: No Preview available", canvas.getWidth() / 2, canvas.getHeight() / 2, this.mPreviewPaint);
        } else {
            drawGraphElements(canvas);
        }
    }

    protected void drawTitle(Canvas canvas) {
        String str = this.mTitle;
        if (str == null || str.length() <= 0) {
            return;
        }
        this.mPaintTitle.setColor(this.mStyles.titleColor);
        this.mPaintTitle.setTextSize(this.mStyles.titleTextSize);
        this.mPaintTitle.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(this.mTitle, canvas.getWidth() / 2, this.mPaintTitle.getTextSize(), this.mPaintTitle);
    }

    protected int getTitleHeight() {
        String str = this.mTitle;
        if (str == null || str.length() <= 0) {
            return 0;
        }
        return (int) this.mPaintTitle.getTextSize();
    }

    public Viewport getViewport() {
        return this.mViewport;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        onDataChanged(false, false);
    }

    public int getGraphContentLeft() {
        return getGridLabelRenderer().getStyles().padding + getGridLabelRenderer().getLabelVerticalWidth() + getGridLabelRenderer().getVerticalAxisTitleWidth();
    }

    public int getGraphContentTop() {
        return getGridLabelRenderer().getStyles().padding + getTitleHeight();
    }

    public int getGraphContentHeight() {
        return (((getHeight() - (getGridLabelRenderer().getStyles().padding * 2)) - getGridLabelRenderer().getLabelHorizontalHeight()) - getTitleHeight()) - getGridLabelRenderer().getHorizontalAxisTitleHeight();
    }

    public int getGraphContentWidth() {
        return this.mSecondScale != null ? (int) ((r1 - getGridLabelRenderer().getLabelVerticalSecondScaleWidth()) - this.mSecondScale.getVerticalAxisTitleTextSize()) : (getWidth() - (getGridLabelRenderer().getStyles().padding * 2)) - getGridLabelRenderer().getLabelVerticalWidth();
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean zOnTouchEvent = this.mViewport.onTouchEvent(motionEvent);
        boolean zOnTouchEvent2 = super.onTouchEvent(motionEvent);
        if (this.mTapDetector.onTouchEvent(motionEvent)) {
            Iterator<Series> it = this.mSeries.iterator();
            while (it.hasNext()) {
                it.next().onTap(motionEvent.getX(), motionEvent.getY());
            }
            SecondScale secondScale = this.mSecondScale;
            if (secondScale != null) {
                Iterator<Series> it2 = secondScale.getSeries().iterator();
                while (it2.hasNext()) {
                    it2.next().onTap(motionEvent.getX(), motionEvent.getY());
                }
            }
        }
        return zOnTouchEvent || zOnTouchEvent2;
    }

    @Override // android.view.View
    public void computeScroll() {
        super.computeScroll();
        this.mViewport.computeScroll();
    }

    public LegendRenderer getLegendRenderer() {
        return this.mLegendRenderer;
    }

    public void setLegendRenderer(LegendRenderer legendRenderer) {
        this.mLegendRenderer = legendRenderer;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public float getTitleTextSize() {
        return this.mStyles.titleTextSize;
    }

    public void setTitleTextSize(float f) {
        this.mStyles.titleTextSize = f;
    }

    public int getTitleColor() {
        return this.mStyles.titleColor;
    }

    public void setTitleColor(int i) {
        this.mStyles.titleColor = i;
    }

    public SecondScale getSecondScale() {
        if (this.mSecondScale == null) {
            SecondScale secondScale = new SecondScale(this);
            this.mSecondScale = secondScale;
            secondScale.setVerticalAxisTitleTextSize(this.mGridLabelRenderer.mStyles.textSize);
        }
        return this.mSecondScale;
    }

    public void clearSecondScale() {
        SecondScale secondScale = this.mSecondScale;
        if (secondScale != null) {
            secondScale.removeAllSeries();
            this.mSecondScale = null;
        }
    }

    public void removeAllSeries() {
        this.mSeries.clear();
        onDataChanged(false, false);
    }

    public void removeSeries(Series<?> series) {
        this.mSeries.remove(series);
        onDataChanged(false, false);
    }
}
