package defpackage;

import android.view.animation.Interpolator;

/* JADX INFO: compiled from: ViewTransition.java */
/* JADX INFO: loaded from: classes.dex */
public final class tn3 implements Interpolator {
    public final /* synthetic */ hf0 a;

    public tn3(hf0 hf0Var) {
        this.a = hf0Var;
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        return (float) this.a.a(f);
    }
}
