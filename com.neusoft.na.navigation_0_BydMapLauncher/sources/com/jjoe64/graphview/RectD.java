package com.jjoe64.graphview;

import android.graphics.RectF;

/* JADX INFO: loaded from: classes2.dex */
public class RectD {
    public double bottom;
    public double left;
    public double right;
    public double top;

    public RectD() {
    }

    public RectD(double d, double d2, double d3, double d4) {
        set(d, d2, d3, d4);
    }

    public double width() {
        return this.right - this.left;
    }

    public double height() {
        return this.bottom - this.top;
    }

    public void set(double d, double d2, double d3, double d4) {
        this.left = d;
        this.right = d3;
        this.top = d2;
        this.bottom = d4;
    }

    public RectF toRectF() {
        return new RectF((float) this.left, (float) this.top, (float) this.right, (float) this.bottom);
    }
}
