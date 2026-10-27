package w0;

import android.view.animation.Interpolator;

/* JADX INFO: compiled from: LookupTableInterpolator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d implements Interpolator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float[] f8668a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f8669b;

    public d(float[] fArr) {
        this.f8668a = fArr;
        this.f8669b = 1.0f / (fArr.length - 1);
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f8) {
        if (f8 >= 1.0f) {
            return 1.0f;
        }
        if (f8 <= 0.0f) {
            return 0.0f;
        }
        float[] fArr = this.f8668a;
        int iMin = Math.min((int) ((fArr.length - 1) * f8), fArr.length - 2);
        float f9 = this.f8669b;
        float f10 = (f8 - (iMin * f9)) / f9;
        float[] fArr2 = this.f8668a;
        return fArr2[iMin] + (f10 * (fArr2[iMin + 1] - fArr2[iMin]));
    }
}
