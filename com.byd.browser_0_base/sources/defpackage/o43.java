package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: TabSwitcherAndStartSurfaceLayout.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o43 extends AnimatorListenerAdapter {
    public final /* synthetic */ l43 e;

    public o43(l43 l43Var) {
        this.e = l43Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        l43 l43Var = this.e;
        l43Var.t = null;
        l43Var.x.n().setY(0.0f);
        l43Var.g();
        l43Var.getClass();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        l43 l43Var = this.e;
        l43Var.x.n().setVisibility(0);
        l43Var.x.r(true);
        lr2 lr2Var = l43Var.x;
        lr2Var.i(lr2Var.n());
    }
}
