package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: TabSwitcherAndStartSurfaceLayout.java */
/* JADX INFO: loaded from: classes2.dex */
public final class m43 extends AnimatorListenerAdapter {
    public final /* synthetic */ l43 e;

    public m43(l43 l43Var) {
        this.e = l43Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        l43 l43Var = this.e;
        l43Var.t = null;
        l43Var.x.r(true);
        l43.P(l43Var, true);
    }
}
