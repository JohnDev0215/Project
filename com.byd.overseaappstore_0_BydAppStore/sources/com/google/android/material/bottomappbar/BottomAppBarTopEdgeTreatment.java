package com.google.android.material.bottomappbar;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;
import spi.d;

/* JADX INFO: loaded from: classes2.dex */
public class BottomAppBarTopEdgeTreatment extends EdgeTreatment implements Cloneable {
    private static final int ANGLE_LEFT = 180;
    private static final int ANGLE_UP = 270;
    private static final int ARC_HALF = 180;
    private static final int ARC_QUARTER = 90;
    private static final float ROUNDED_CORNER_FAB_OFFSET = 1.75f;
    private float cradleVerticalOffset;
    private float fabCornerSize = -1.0f;
    private float fabDiameter;
    private float fabMargin;
    private float horizontalOffset;
    private float roundedCornerRadius;

    public BottomAppBarTopEdgeTreatment(float f, float f2, float f3) {
        this.fabMargin = f;
        this.roundedCornerRadius = f2;
        setCradleVerticalOffset(f3);
        this.horizontalOffset = 0.0f;
    }

    public float getCradleVerticalOffset() {
        return this.cradleVerticalOffset;
    }

    @Override // com.google.android.material.shape.EdgeTreatment
    public void getEdgePath(float f, float f2, float f3, @NonNull ShapePath shapePath) {
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        float f9;
        float f10;
        float f11;
        float f12 = this.fabDiameter;
        if (f12 == 0.0f) {
            shapePath.lineTo(f, 0.0f);
            return;
        }
        float f13 = ((this.fabMargin * 2.0f) + f12) / 2.0f;
        float f14 = f3 * this.roundedCornerRadius;
        float f15 = f2 + this.horizontalOffset;
        float fA = d.a(1.0f, f3, f13, this.cradleVerticalOffset * f3);
        if (fA / f13 >= 1.0f) {
            shapePath.lineTo(f, 0.0f);
            return;
        }
        float f16 = this.fabCornerSize;
        float f17 = f16 * f3;
        boolean z = f16 == -1.0f || Math.abs((f16 * 2.0f) - f12) < 0.1f;
        if (z) {
            f4 = fA;
            f5 = 0.0f;
        } else {
            f5 = ROUNDED_CORNER_FAB_OFFSET;
            f4 = 0.0f;
        }
        float f18 = f13 + f14;
        float f19 = f4 + f14;
        float fSqrt = (float) Math.sqrt((f18 * f18) - (f19 * f19));
        float f20 = f15 - fSqrt;
        float f21 = f15 + fSqrt;
        float degrees = (float) Math.toDegrees(Math.atan(fSqrt / f19));
        float f22 = (90.0f - degrees) + f5;
        shapePath.lineTo(f20, 0.0f);
        float f23 = f14 * 2.0f;
        shapePath.addArc(f20 - f14, 0.0f, f20 + f14, f23, 270.0f, degrees);
        if (z) {
            f7 = f15 - f13;
            f8 = (-f13) - f4;
            f6 = f15 + f13;
            float f24 = 180.0f - f22;
            f9 = (f22 * 2.0f) - 180.0f;
            f11 = f24;
            f10 = f13 - f4;
        } else {
            float f25 = this.fabMargin;
            float f26 = f17 * 2.0f;
            float f27 = f15 - f13;
            shapePath.addArc(f27, -(f17 + f25), f27 + f25 + f26, f25 + f17, 180.0f - f22, ((f22 * 2.0f) - 180.0f) / 2.0f);
            f6 = f15 + f13;
            float f28 = this.fabMargin;
            shapePath.lineTo(f6 - ((f28 / 2.0f) + f17), f28 + f17);
            float f29 = this.fabMargin;
            f7 = f6 - (f26 + f29);
            f8 = -(f17 + f29);
            f9 = f22 - 90.0f;
            f10 = f29 + f17;
            f11 = 90.0f;
        }
        shapePath.addArc(f7, f8, f6, f10, f11, f9);
        shapePath.addArc(f21 - f14, 0.0f, f21 + f14, f23, 270.0f - degrees, degrees);
        shapePath.lineTo(f, 0.0f);
    }

    public float getFabCornerRadius() {
        return this.fabCornerSize;
    }

    public float getFabCradleMargin() {
        return this.fabMargin;
    }

    public float getFabCradleRoundedCornerRadius() {
        return this.roundedCornerRadius;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getFabDiameter() {
        return this.fabDiameter;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public float getHorizontalOffset() {
        return this.horizontalOffset;
    }

    public void setCradleVerticalOffset(@FloatRange(from = 0.0d) float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }
        this.cradleVerticalOffset = f;
    }

    public void setFabCornerSize(float f) {
        this.fabCornerSize = f;
    }

    public void setFabCradleMargin(float f) {
        this.fabMargin = f;
    }

    public void setFabCradleRoundedCornerRadius(float f) {
        this.roundedCornerRadius = f;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setFabDiameter(float f) {
        this.fabDiameter = f;
    }

    public void setHorizontalOffset(float f) {
        this.horizontalOffset = f;
    }
}
