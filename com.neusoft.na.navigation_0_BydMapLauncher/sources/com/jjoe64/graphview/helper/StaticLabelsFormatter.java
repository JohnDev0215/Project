package com.jjoe64.graphview.helper;

import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LabelFormatter;
import com.jjoe64.graphview.Viewport;

/* JADX INFO: loaded from: classes2.dex */
public class StaticLabelsFormatter implements LabelFormatter {
    protected LabelFormatter mDynamicLabelFormatter;
    protected final GraphView mGraphView;
    protected String[] mHorizontalLabels;
    protected String[] mVerticalLabels;
    protected Viewport mViewport;

    public StaticLabelsFormatter(GraphView graphView) {
        this.mGraphView = graphView;
        init(null, null, null);
    }

    public StaticLabelsFormatter(GraphView graphView, LabelFormatter labelFormatter) {
        this.mGraphView = graphView;
        init(null, null, labelFormatter);
    }

    public StaticLabelsFormatter(GraphView graphView, String[] strArr, String[] strArr2) {
        this.mGraphView = graphView;
        init(strArr, strArr2, null);
    }

    public StaticLabelsFormatter(GraphView graphView, String[] strArr, String[] strArr2, LabelFormatter labelFormatter) {
        this.mGraphView = graphView;
        init(strArr, strArr2, labelFormatter);
    }

    protected void init(String[] strArr, String[] strArr2, LabelFormatter labelFormatter) {
        this.mDynamicLabelFormatter = labelFormatter;
        if (labelFormatter == null) {
            this.mDynamicLabelFormatter = new DefaultLabelFormatter();
        }
        this.mHorizontalLabels = strArr;
        this.mVerticalLabels = strArr2;
    }

    public void setDynamicLabelFormatter(LabelFormatter labelFormatter) {
        this.mDynamicLabelFormatter = labelFormatter;
        adjust();
    }

    public void setHorizontalLabels(String[] strArr) {
        this.mHorizontalLabels = strArr;
        adjust();
    }

    public void setVerticalLabels(String[] strArr) {
        this.mVerticalLabels = strArr;
        adjust();
    }

    @Override // com.jjoe64.graphview.LabelFormatter
    public String formatLabel(double d, boolean z) {
        if (z && this.mHorizontalLabels != null) {
            double minX = this.mViewport.getMinX(false);
            double maxX = (d - minX) / (this.mViewport.getMaxX(false) - minX);
            return this.mHorizontalLabels[(int) (maxX * ((double) (r8.length - 1)))];
        }
        if (!z && this.mVerticalLabels != null) {
            double minY = this.mViewport.getMinY(false);
            double maxY = (d - minY) / (this.mViewport.getMaxY(false) - minY);
            return this.mVerticalLabels[(int) (maxY * ((double) (r8.length - 1)))];
        }
        return this.mDynamicLabelFormatter.formatLabel(d, z);
    }

    @Override // com.jjoe64.graphview.LabelFormatter
    public void setViewport(Viewport viewport) {
        this.mViewport = viewport;
        adjust();
    }

    protected void adjust() {
        this.mDynamicLabelFormatter.setViewport(this.mViewport);
        String[] strArr = this.mVerticalLabels;
        if (strArr != null) {
            if (strArr.length < 2) {
                throw new IllegalStateException("You need at least 2 vertical labels if you use static label formatter.");
            }
            this.mGraphView.getGridLabelRenderer().setNumVerticalLabels(this.mVerticalLabels.length);
        }
        String[] strArr2 = this.mHorizontalLabels;
        if (strArr2 != null) {
            if (strArr2.length < 2) {
                throw new IllegalStateException("You need at least 2 horizontal labels if you use static label formatter.");
            }
            this.mGraphView.getGridLabelRenderer().setNumHorizontalLabels(this.mHorizontalLabels.length);
        }
    }
}
