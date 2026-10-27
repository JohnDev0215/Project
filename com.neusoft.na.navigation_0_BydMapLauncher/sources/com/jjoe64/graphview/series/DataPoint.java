package com.jjoe64.graphview.series;

import java.io.Serializable;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public class DataPoint implements DataPointInterface, Serializable {
    private static final long serialVersionUID = 1428263322645L;
    private double x;
    private double y;

    public DataPoint(double d, double d2) {
        this.x = d;
        this.y = d2;
    }

    public DataPoint(Date date, double d) {
        this.x = date.getTime();
        this.y = d;
    }

    @Override // com.jjoe64.graphview.series.DataPointInterface
    public double getX() {
        return this.x;
    }

    @Override // com.jjoe64.graphview.series.DataPointInterface
    public double getY() {
        return this.y;
    }

    public String toString() {
        return "[" + this.x + "/" + this.y + "]";
    }
}
