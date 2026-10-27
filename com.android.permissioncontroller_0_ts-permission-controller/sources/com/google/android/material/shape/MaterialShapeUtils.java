package com.google.android.material.shape;

/* JADX INFO: loaded from: classes8.dex */
public class MaterialShapeUtils {
    static CornerTreatment createCornerTreatment(int cornerFamily, int cornerSize) {
        if (cornerFamily == 0) {
            return new RoundedCornerTreatment(cornerSize);
        }
        if (cornerFamily == 1) {
            return new CutCornerTreatment(cornerSize);
        }
        return createDefaultCornerTreatment();
    }

    static CornerTreatment createDefaultCornerTreatment() {
        return new RoundedCornerTreatment(0.0f);
    }

    static EdgeTreatment createDefaultEdgeTreatment() {
        return new EdgeTreatment();
    }
}
