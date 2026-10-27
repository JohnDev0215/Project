package com.google.android.material.shape;

/* JADX INFO: loaded from: classes.dex */
public class EdgeTreatment implements Cloneable {
    public void getEdgePath(float f, float f2, float f3, ShapePath shapePath) {
        shapePath.lineTo(f, 0.0f);
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public EdgeTreatment m9clone() {
        try {
            return (EdgeTreatment) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
