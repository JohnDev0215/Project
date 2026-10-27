package defpackage;

import android.animation.ValueAnimator;

/* JADX INFO: compiled from: FocusAnimator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class eo0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ go0 e;

    public eo0(go0 go0Var) {
        this.e = go0Var;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        go0 go0Var = this.e;
        go0Var.a.setBottom(((Integer) valueAnimator.getAnimatedValue()).intValue());
        go0Var.b();
    }
}
