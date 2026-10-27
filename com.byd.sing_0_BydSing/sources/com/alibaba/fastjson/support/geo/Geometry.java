package com.alibaba.fastjson.support.geo;

import com.alibaba.fastjson.annotation.JSONType;
import com.loostone.libtuning.component.provider.TuningProvider;

/* JADX INFO: loaded from: classes.dex */
@JSONType(seeAlso = {GeometryCollection.class, LineString.class, MultiLineString.class, Point.class, MultiPoint.class, Polygon.class, MultiPolygon.class, Feature.class, FeatureCollection.class}, typeKey = TuningProvider.TYPE)
public abstract class Geometry {
    private double[] bbox;
    private final String type;

    protected Geometry(String str) {
        this.type = str;
    }

    public double[] getBbox() {
        return this.bbox;
    }

    public String getType() {
        return this.type;
    }

    public void setBbox(double[] dArr) {
        this.bbox = dArr;
    }
}
