package com.google.android.material.shape;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes2.dex */
public class CutCornerTreatment extends CornerTreatment {
    public float size;

    public CutCornerTreatment() {
        this.size = -1.0f;
    }

    @Override // com.google.android.material.shape.CornerTreatment
    public void getCornerPath(@NonNull ShapePath shapePath, float f, float f2, float f3) {
        shapePath.reset(0.0f, f3 * f2, 180.0f, 180.0f - f);
        double d2 = f3;
        double d3 = f2;
        shapePath.lineTo((float) (Math.sin(Math.toRadians(f)) * d2 * d3), (float) (Math.sin(Math.toRadians(90.0f - f)) * d2 * d3));
    }

    @Deprecated
    public CutCornerTreatment(float f) {
        this.size = -1.0f;
        this.size = f;
    }
}
