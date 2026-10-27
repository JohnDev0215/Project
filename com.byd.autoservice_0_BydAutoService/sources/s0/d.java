package s0;

import android.view.animation.Interpolator;

/* JADX INFO: compiled from: LookupTableInterpolator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d implements Interpolator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f4899a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f4900b;

    public d(float[] fArr) {
        this.f4899a = fArr;
        this.f4900b = 1.0f / (fArr.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f3) {
        if (f3 >= 1.0f) {
            return 1.0f;
        }
        if (f3 <= 0.0f) {
            return 0.0f;
        }
        float[] fArr = this.f4899a;
        int iMin = Math.min((int) ((fArr.length - 1) * f3), fArr.length - 2);
        float f4 = this.f4900b;
        float f5 = (f3 - (iMin * f4)) / f4;
        float[] fArr2 = this.f4899a;
        return fArr2[iMin] + (f5 * (fArr2[iMin + 1] - fArr2[iMin]));
    }
}
