package com.jjoe64.graphview.helper;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.R;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.BaseSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;

/* JADX INFO: loaded from: classes2.dex */
public class GraphViewXML extends GraphView {
    public GraphViewXML(Context context, AttributeSet attributeSet) {
        BaseSeries pointsGraphSeries;
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.GraphViewXML);
        String string = typedArrayObtainStyledAttributes.getString(R.styleable.GraphViewXML_seriesData);
        int color = typedArrayObtainStyledAttributes.getColor(R.styleable.GraphViewXML_seriesColor, 0);
        String string2 = typedArrayObtainStyledAttributes.getString(R.styleable.GraphViewXML_seriesType);
        String string3 = typedArrayObtainStyledAttributes.getString(R.styleable.GraphViewXML_seriesTitle);
        String string4 = typedArrayObtainStyledAttributes.getString(R.styleable.GraphViewXML_android_title);
        typedArrayObtainStyledAttributes.recycle();
        if (string == null || string.isEmpty()) {
            throw new IllegalArgumentException("Attribute seriesData is required in the format: 0=5.0;1=5;2=4;3=9");
        }
        String[] strArrSplit = string.split(";");
        try {
            DataPoint[] dataPointArr = new DataPoint[strArrSplit.length];
            int i = 0;
            for (String str : strArrSplit) {
                String[] strArrSplit2 = str.split("=");
                dataPointArr[i] = new DataPoint(Double.parseDouble(strArrSplit2[0]), Double.parseDouble(strArrSplit2[1]));
                i++;
            }
            string2 = (string2 == null || string2.isEmpty()) ? "line" : string2;
            if (string2.equals("line")) {
                pointsGraphSeries = new LineGraphSeries(dataPointArr);
            } else if (string2.equals("bar")) {
                pointsGraphSeries = new BarGraphSeries(dataPointArr);
            } else if (string2.equals("points")) {
                pointsGraphSeries = new PointsGraphSeries(dataPointArr);
            } else {
                throw new IllegalArgumentException("unknown graph type: " + string2 + ". Possible is line|bar|points");
            }
            if (color != 0) {
                pointsGraphSeries.setColor(color);
            }
            addSeries(pointsGraphSeries);
            if (string3 != null && !string3.isEmpty()) {
                pointsGraphSeries.setTitle(string3);
                getLegendRenderer().setVisible(true);
            }
            if (string4 == null || string4.isEmpty()) {
                return;
            }
            setTitle(string4);
        } catch (Exception e) {
            Log.e("GraphViewXML", e.toString());
            throw new IllegalArgumentException("Attribute seriesData is broken. Use this format: 0=5.0;1=5;2=4;3=9");
        }
    }
}
