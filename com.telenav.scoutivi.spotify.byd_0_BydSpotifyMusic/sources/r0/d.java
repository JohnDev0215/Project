package r0;

import android.view.animation.Interpolator;

/* JADX INFO: compiled from: LookupTableInterpolator.java */
/* JADX INFO: loaded from: classes.dex */
abstract class d implements Interpolator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float[] f9330a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f9331b;

    protected d(float[] fArr) {
        this.f9330a = fArr;
        this.f9331b = 1.0f / (fArr.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f7) {
        if (f7 >= 1.0f) {
            return 1.0f;
        }
        if (f7 <= 0.0f) {
            return 0.0f;
        }
        float[] fArr = this.f9330a;
        int iMin = Math.min((int) ((fArr.length - 1) * f7), fArr.length - 2);
        float f8 = this.f9331b;
        float f9 = (f7 - (iMin * f8)) / f8;
        float[] fArr2 = this.f9330a;
        return fArr2[iMin] + (f9 * (fArr2[iMin + 1] - fArr2[iMin]));
    }
}
