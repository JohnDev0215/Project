package com.google.android.material.shape;

/* JADX INFO: loaded from: classes8.dex */
public class EdgeTreatment implements Cloneable {
    @Deprecated
    public void getEdgePath(float length, float interpolation, ShapePath shapePath) {
        float center = length / 2.0f;
        getEdgePath(length, center, interpolation, shapePath);
    }

    public void getEdgePath(float length, float center, float interpolation, ShapePath shapePath) {
        shapePath.lineTo(length, 0.0f);
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public EdgeTreatment m14clone() {
        try {
            return (EdgeTreatment) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
