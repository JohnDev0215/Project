package com.jjoe64.graphview;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.jjoe64.graphview.series.Series;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SecondScale {
    protected final GraphView mGraph;
    protected LabelFormatter mLabelFormatter;
    private Paint mPaintAxisTitle;
    private String mVerticalAxisTitle;
    public int mVerticalAxisTitleColor;
    public float mVerticalAxisTitleTextSize;
    private boolean mYAxisBoundsManual = true;
    protected RectD mCompleteRange = new RectD();
    protected RectD mCurrentViewport = new RectD();
    protected double mReferenceY = Double.NaN;
    protected List<Series> mSeries = new ArrayList();

    SecondScale(GraphView graphView) {
        this.mGraph = graphView;
        DefaultLabelFormatter defaultLabelFormatter = new DefaultLabelFormatter();
        this.mLabelFormatter = defaultLabelFormatter;
        defaultLabelFormatter.setViewport(graphView.getViewport());
    }

    public void addSeries(Series series) {
        series.onGraphViewAttached(this.mGraph);
        this.mSeries.add(series);
        this.mGraph.onDataChanged(false, false);
    }

    public void setMinY(double d) {
        this.mReferenceY = d;
        this.mCurrentViewport.bottom = d;
    }

    public void setMaxY(double d) {
        this.mCurrentViewport.top = d;
    }

    public List<Series> getSeries() {
        return this.mSeries;
    }

    public double getMinY(boolean z) {
        return (z ? this.mCompleteRange : this.mCurrentViewport).bottom;
    }

    public double getMaxY(boolean z) {
        return (z ? this.mCompleteRange : this.mCurrentViewport).top;
    }

    public boolean isYAxisBoundsManual() {
        return this.mYAxisBoundsManual;
    }

    public LabelFormatter getLabelFormatter() {
        return this.mLabelFormatter;
    }

    public void setLabelFormatter(LabelFormatter labelFormatter) {
        this.mLabelFormatter = labelFormatter;
        labelFormatter.setViewport(this.mGraph.getViewport());
    }

    public void removeAllSeries() {
        this.mSeries.clear();
        this.mGraph.onDataChanged(false, false);
    }

    public void removeSeries(Series series) {
        this.mSeries.remove(series);
        this.mGraph.onDataChanged(false, false);
    }

    public void calcCompleteRange() {
        List<Series> series = getSeries();
        this.mCompleteRange.set(0.0d, 0.0d, 0.0d, 0.0d);
        if (series.isEmpty() || series.get(0).isEmpty()) {
            return;
        }
        double lowestValueX = series.get(0).getLowestValueX();
        for (Series series2 : series) {
            if (!series2.isEmpty() && lowestValueX > series2.getLowestValueX()) {
                lowestValueX = series2.getLowestValueX();
            }
        }
        this.mCompleteRange.left = lowestValueX;
        double highestValueX = series.get(0).getHighestValueX();
        for (Series series3 : series) {
            if (!series3.isEmpty() && highestValueX < series3.getHighestValueX()) {
                highestValueX = series3.getHighestValueX();
            }
        }
        this.mCompleteRange.right = highestValueX;
        if (series.isEmpty() || series.get(0).isEmpty()) {
            return;
        }
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

    public String getVerticalAxisTitle() {
        return this.mVerticalAxisTitle;
    }

    public void setVerticalAxisTitle(String str) {
        if (this.mPaintAxisTitle == null) {
            Paint paint = new Paint();
            this.mPaintAxisTitle = paint;
            paint.setTextSize(getVerticalAxisTitleTextSize());
            this.mPaintAxisTitle.setTextAlign(Paint.Align.CENTER);
        }
        this.mVerticalAxisTitle = str;
    }

    public float getVerticalAxisTitleTextSize() {
        if (getVerticalAxisTitle() == null || getVerticalAxisTitle().length() == 0) {
            return 0.0f;
        }
        return this.mVerticalAxisTitleTextSize;
    }

    public void setVerticalAxisTitleTextSize(float f) {
        this.mVerticalAxisTitleTextSize = f;
    }

    public int getVerticalAxisTitleColor() {
        return this.mVerticalAxisTitleColor;
    }

    public void setVerticalAxisTitleColor(int i) {
        this.mVerticalAxisTitleColor = i;
    }

    protected void drawVerticalAxisTitle(Canvas canvas) {
        String str = this.mVerticalAxisTitle;
        if (str == null || str.length() <= 0) {
            return;
        }
        this.mPaintAxisTitle.setColor(getVerticalAxisTitleColor());
        this.mPaintAxisTitle.setTextSize(getVerticalAxisTitleTextSize());
        float width = canvas.getWidth() - (getVerticalAxisTitleTextSize() / 2.0f);
        float height = canvas.getHeight() / 2;
        canvas.save();
        canvas.rotate(-90.0f, width, height);
        canvas.drawText(this.mVerticalAxisTitle, width, height, this.mPaintAxisTitle);
        canvas.restore();
    }
}
