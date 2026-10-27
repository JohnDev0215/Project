package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: TabSwitcherAndStartSurfaceLayout.java */
/* JADX INFO: loaded from: classes2.dex */
public final class n43 extends AnimatorListenerAdapter {
    public final /* synthetic */ l43 e;

    public n43(l43 l43Var) {
        this.e = l43Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        l43 l43Var = this.e;
        l43Var.t = null;
        if (zb2.d(l43Var.i)) {
            l43Var.x.e();
        } else {
            l43Var.R().p();
        }
        l43Var.u = false;
        l43Var.f();
        l43.P(l43Var, false);
    }
}
