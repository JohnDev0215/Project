package a.k.a.a;

import android.view.animation.Interpolator;

/* JADX INFO: loaded from: classes.dex */
abstract class d implements Interpolator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final float[] f486a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final float f487b;

    protected d(float[] fArr) {
        this.f486a = fArr;
        this.f487b = 1.0f / (fArr.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (f >= 1.0f) {
            return 1.0f;
        }
        if (f <= 0.0f) {
            return 0.0f;
        }
        float[] fArr = this.f486a;
        int iMin = Math.min((int) ((fArr.length - 1) * f), fArr.length - 2);
        float f2 = this.f487b;
        float f3 = (f - (iMin * f2)) / f2;
        float[] fArr2 = this.f486a;
        return fArr2[iMin] + (f3 * (fArr2[iMin + 1] - fArr2[iMin]));
    }
}
