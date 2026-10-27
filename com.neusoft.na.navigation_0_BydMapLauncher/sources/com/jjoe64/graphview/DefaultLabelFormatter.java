package com.jjoe64.graphview;

import java.text.NumberFormat;

/* JADX INFO: loaded from: classes2.dex */
public class DefaultLabelFormatter implements LabelFormatter {
    protected NumberFormat[] mNumberFormatter;
    protected Viewport mViewport;

    public DefaultLabelFormatter() {
        this.mNumberFormatter = new NumberFormat[2];
    }

    public DefaultLabelFormatter(NumberFormat numberFormat, NumberFormat numberFormat2) {
        this.mNumberFormatter = new NumberFormat[]{numberFormat2, numberFormat};
    }

    @Override // com.jjoe64.graphview.LabelFormatter
    public void setViewport(Viewport viewport) {
        this.mViewport = viewport;
    }

    @Override // com.jjoe64.graphview.LabelFormatter
    public String formatLabel(double d, boolean z) {
        NumberFormat[] numberFormatArr = this.mNumberFormatter;
        if (numberFormatArr[z ? 1 : 0] == null) {
            numberFormatArr[z ? 1 : 0] = NumberFormat.getNumberInstance();
            Viewport viewport = this.mViewport;
            double maxX = z ? viewport.getMaxX(false) : viewport.getMaxY(false);
            Viewport viewport2 = this.mViewport;
            double minX = maxX - (z ? viewport2.getMinX(false) : viewport2.getMinY(false));
            if (minX < 0.1d) {
                this.mNumberFormatter[z ? 1 : 0].setMaximumFractionDigits(6);
            } else if (minX < 1.0d) {
                this.mNumberFormatter[z ? 1 : 0].setMaximumFractionDigits(4);
            } else if (minX < 20.0d) {
                this.mNumberFormatter[z ? 1 : 0].setMaximumFractionDigits(3);
            } else if (minX < 100.0d) {
                this.mNumberFormatter[z ? 1 : 0].setMaximumFractionDigits(1);
            } else {
                this.mNumberFormatter[z ? 1 : 0].setMaximumFractionDigits(0);
            }
        }
        return this.mNumberFormatter[z ? 1 : 0].format(d);
    }
}
