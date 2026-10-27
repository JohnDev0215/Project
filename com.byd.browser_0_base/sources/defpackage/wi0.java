package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: ExtendedFloatingActionButton.java */
/* JADX INFO: loaded from: classes.dex */
public final class wi0 extends AnimatorListenerAdapter {
    public boolean e;
    public final /* synthetic */ gj1 f;

    public wi0(qd qdVar) {
        this.f = qdVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        this.e = true;
        this.f.c();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        gj1 gj1Var = this.f;
        gj1Var.a();
        if (this.e) {
            return;
        }
        gj1Var.b();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f.onAnimationStart(animator);
        this.e = false;
    }
}
