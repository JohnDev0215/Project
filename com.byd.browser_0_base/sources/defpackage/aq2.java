package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: SnackbarView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class aq2 extends AnimatorListenerAdapter {
    public final /* synthetic */ yp2 e;

    public aq2(yp2 yp2Var) {
        this.e = yp2Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        yp2 yp2Var = this.e;
        yp2Var.l.removeOnLayoutChangeListener(yp2Var.p);
        yp2Var.j.removeView(yp2Var.b);
    }
}
