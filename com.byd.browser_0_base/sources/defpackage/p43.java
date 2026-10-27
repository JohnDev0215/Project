package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: TabSwitcherAndStartSurfaceLayout.java */
/* JADX INFO: loaded from: classes2.dex */
public final class p43 extends AnimatorListenerAdapter {
    public final /* synthetic */ l43 e;

    public p43(l43 l43Var) {
        this.e = l43Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        l43 l43Var = this.e;
        l43Var.t = null;
        l43Var.x.g(true);
        l43Var.x.n().setVisibility(8);
        l43Var.getClass();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        l43 l43Var = this.e;
        l43Var.x.I();
        l43Var.x.i(null);
    }
}
