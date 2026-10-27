package com.alibaba.fastjson.support.geo;

import com.alibaba.fastjson.annotation.JSONType;
import com.loostone.libtuning.component.provider.TuningProvider;

/* JADX INFO: loaded from: classes.dex */
@JSONType(orders = {TuningProvider.TYPE, "bbox", "coordinates"}, typeName = "MultiPoint")
public class MultiPoint extends Geometry {
    private double[][] coordinates;

    public MultiPoint() {
        super("MultiPoint");
    }

    public double[][] getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(double[][] dArr) {
        this.coordinates = dArr;
    }
}
