package com.jjoe64.graphview.series;

import android.graphics.PointF;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPointInterface;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseSeries<E extends DataPointInterface> implements Series<E> {
    protected OnDataPointTapListener mOnDataPointTapListener;
    private String mTitle;
    private final List<E> mData = new ArrayList();
    private Map<PointF, E> mDataPoints = new HashMap();
    private int mColor = -16746548;
    private double mLowestYCache = Double.NaN;
    private double mHighestYCache = Double.NaN;
    private List<GraphView> mGraphViews = new ArrayList();

    public BaseSeries() {
    }

    public BaseSeries(E[] eArr) {
        for (E e : eArr) {
            this.mData.add(e);
        }
    }

    @Override // com.jjoe64.graphview.series.Series
    public double getLowestValueX() {
        if (this.mData.isEmpty()) {
            return 0.0d;
        }
        return this.mData.get(0).getX();
    }

    @Override // com.jjoe64.graphview.series.Series
    public double getHighestValueX() {
        if (this.mData.isEmpty()) {
            return 0.0d;
        }
        return this.mData.get(r0.size() - 1).getX();
    }

    @Override // com.jjoe64.graphview.series.Series
    public double getLowestValueY() {
        if (this.mData.isEmpty()) {
            return 0.0d;
        }
        if (!Double.isNaN(this.mLowestYCache)) {
            return this.mLowestYCache;
        }
        double y = this.mData.get(0).getY();
        for (int i = 1; i < this.mData.size(); i++) {
            double y2 = this.mData.get(i).getY();
            if (y > y2) {
                y = y2;
            }
        }
        this.mLowestYCache = y;
        return y;
    }

    @Override // com.jjoe64.graphview.series.Series
    public double getHighestValueY() {
        if (this.mData.isEmpty()) {
            return 0.0d;
        }
        if (!Double.isNaN(this.mHighestYCache)) {
            return this.mHighestYCache;
        }
        double y = this.mData.get(0).getY();
        for (int i = 1; i < this.mData.size(); i++) {
            double y2 = this.mData.get(i).getY();
            if (y < y2) {
                y = y2;
            }
        }
        this.mHighestYCache = y;
        return y;
    }

    @Override // com.jjoe64.graphview.series.Series
    public Iterator<E> getValues(double d, double d2) {
        if (d <= getLowestValueX() && d2 >= getHighestValueX()) {
            return this.mData.iterator();
        }
        return (Iterator<E>) new Iterator<E>(d, d2) { // from class: com.jjoe64.graphview.series.BaseSeries.1
            E nextNextValue;
            E nextValue;

            /* JADX INFO: renamed from: org, reason: collision with root package name */
            Iterator<E> f3org;
            boolean plusOne;
            final /* synthetic */ double val$from;
            final /* synthetic */ double val$until;

            {
                this.val$from = d;
                this.val$until = d2;
                Iterator<E> it = BaseSeries.this.mData.iterator();
                this.f3org = it;
                this.nextValue = null;
                this.nextNextValue = null;
                boolean z = true;
                this.plusOne = true;
                E next = it.hasNext() ? this.f3org.next() : null;
                if (next == null) {
                    z = false;
                } else if (next.getX() >= d) {
                    this.nextValue = next;
                } else {
                    while (this.f3org.hasNext()) {
                        E next2 = this.f3org.next();
                        this.nextValue = next2;
                        if (next2.getX() >= this.val$from) {
                            this.nextNextValue = this.nextValue;
                            this.nextValue = next;
                            break;
                        }
                        next = this.nextValue;
                    }
                    z = false;
                }
                if (z) {
                    return;
                }
                this.nextValue = null;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }

            @Override // java.util.Iterator
            public E next() {
                if (hasNext()) {
                    E e = this.nextValue;
                    if (e.getX() > this.val$until) {
                        this.plusOne = false;
                    }
                    E e2 = this.nextNextValue;
                    if (e2 != null) {
                        this.nextValue = e2;
                        this.nextNextValue = null;
                    } else if (this.f3org.hasNext()) {
                        this.nextValue = this.f3org.next();
                    } else {
                        this.nextValue = null;
                    }
                    return e;
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                E e = this.nextValue;
                return e != null && (e.getX() <= this.val$until || this.plusOne);
            }
        };
    }

    @Override // com.jjoe64.graphview.series.Series
    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    @Override // com.jjoe64.graphview.series.Series
    public int getColor() {
        return this.mColor;
    }

    public void setColor(int i) {
        this.mColor = i;
    }

    @Override // com.jjoe64.graphview.series.Series
    public void setOnDataPointTapListener(OnDataPointTapListener onDataPointTapListener) {
        this.mOnDataPointTapListener = onDataPointTapListener;
    }

    @Override // com.jjoe64.graphview.series.Series
    public void onTap(float f, float f2) {
        DataPointInterface dataPointInterfaceFindDataPoint;
        if (this.mOnDataPointTapListener == null || (dataPointInterfaceFindDataPoint = findDataPoint(f, f2)) == null) {
            return;
        }
        this.mOnDataPointTapListener.onTap(this, dataPointInterfaceFindDataPoint);
    }

    protected E findDataPoint(float f, float f2) {
        float f3 = Float.NaN;
        E value = null;
        for (Map.Entry<PointF, E> entry : this.mDataPoints.entrySet()) {
            float f4 = entry.getKey().x - f;
            float f5 = entry.getKey().y - f2;
            float fSqrt = (float) Math.sqrt((f4 * f4) + (f5 * f5));
            if (value == null || fSqrt < f3) {
                value = entry.getValue();
                f3 = fSqrt;
            }
        }
        if (value == null || f3 >= 120.0f) {
            return null;
        }
        return value;
    }

    protected void registerDataPoint(float f, float f2, E e) {
        if (this.mOnDataPointTapListener != null) {
            this.mDataPoints.put(new PointF(f, f2), e);
        }
    }

    protected void resetDataPoints() {
        this.mDataPoints.clear();
    }

    public void resetData(E[] eArr) {
        this.mData.clear();
        for (E e : eArr) {
            this.mData.add(e);
        }
        checkValueOrder(null);
        this.mLowestYCache = Double.NaN;
        this.mHighestYCache = Double.NaN;
        Iterator<GraphView> it = this.mGraphViews.iterator();
        while (it.hasNext()) {
            it.next().onDataChanged(true, false);
        }
    }

    @Override // com.jjoe64.graphview.series.Series
    public void onGraphViewAttached(GraphView graphView) {
        this.mGraphViews.add(graphView);
    }

    public void appendData(E e, boolean z, int i, boolean z2) {
        checkValueOrder(e);
        if (!this.mData.isEmpty()) {
            double x = e.getX();
            List<E> list = this.mData;
            if (x < list.get(list.size() - 1).getX()) {
                throw new IllegalArgumentException("new x-value must be greater then the last value. x-values has to be ordered in ASC.");
            }
        }
        synchronized (this.mData) {
            if (this.mData.size() < i) {
                this.mData.add(e);
            } else {
                this.mData.remove(0);
                this.mData.add(e);
            }
            double y = e.getY();
            if (!Double.isNaN(this.mHighestYCache) && y > this.mHighestYCache) {
                this.mHighestYCache = y;
            }
            if (!Double.isNaN(this.mLowestYCache) && y < this.mLowestYCache) {
                this.mLowestYCache = y;
            }
        }
        if (z2) {
            return;
        }
        boolean z3 = this.mData.size() != 1;
        for (GraphView graphView : this.mGraphViews) {
            if (z) {
                graphView.getViewport().scrollToEnd();
            } else {
                graphView.onDataChanged(z3, z);
            }
        }
    }

    public void appendData(E e, boolean z, int i) {
        appendData(e, z, i, false);
    }

    @Override // com.jjoe64.graphview.series.Series
    public boolean isEmpty() {
        return this.mData.isEmpty();
    }

    protected void checkValueOrder(DataPointInterface dataPointInterface) {
        if (this.mData.size() > 1) {
            if (dataPointInterface != null) {
                double x = dataPointInterface.getX();
                List<E> list = this.mData;
                if (x < list.get(list.size() - 1).getX()) {
                    throw new IllegalArgumentException("new x-value must be greater then the last value. x-values has to be ordered in ASC.");
                }
                return;
            }
            double x2 = this.mData.get(0).getX();
            for (int i = 1; i < this.mData.size(); i++) {
                if (this.mData.get(i).getX() != Double.NaN) {
                    if (x2 > this.mData.get(i).getX()) {
                        throw new IllegalArgumentException("The order of the values is not correct. X-Values have to be ordered ASC. First the lowest x value and at least the highest x value.");
                    }
                    x2 = this.mData.get(i).getX();
                }
            }
        }
    }
}
