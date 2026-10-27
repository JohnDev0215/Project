package defpackage;

import android.view.animation.Interpolator;

/* JADX INFO: compiled from: PulseInterpolator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class q72 implements Interpolator {
    public final Interpolator a;

    public q72(Interpolator interpolator) {
        this.a = interpolator;
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        double d = f;
        Interpolator interpolator = this.a;
        if (d < 0.2d) {
            return interpolator.getInterpolation(f / 0.2f);
        }
        if (d < 0.6d) {
            return 1.0f;
        }
        return interpolator.getInterpolation(1.0f - ((f - 0.6f) / 0.4f));
    }
}
