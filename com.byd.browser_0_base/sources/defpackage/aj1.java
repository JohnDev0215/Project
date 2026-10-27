package defpackage;

import android.view.animation.Interpolator;

/* JADX INFO: compiled from: MotionController.java */
/* JADX INFO: loaded from: classes.dex */
public final class aj1 implements Interpolator {
    public final /* synthetic */ hf0 a;

    public aj1(hf0 hf0Var) {
        this.a = hf0Var;
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        return (float) this.a.a(f);
    }
}
