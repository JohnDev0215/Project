package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: FocusAnimator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fo0 extends AnimatorListenerAdapter {
    public final /* synthetic */ Runnable e;
    public final /* synthetic */ go0 f;

    public fo0(go0 go0Var, Runnable runnable) {
        this.f = go0Var;
        this.e = runnable;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        go0 go0Var = this.f;
        go0Var.b();
        this.e.run();
        ao3.e(go0Var.a, "FocusAnimator.startAnimator.AnimatorListenerAdapter.onAnimationEnd");
    }
}
