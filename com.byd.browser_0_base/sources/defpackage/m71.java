package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: LinearIndeterminateDisjointAnimatorDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class m71 extends AnimatorListenerAdapter {
    public final /* synthetic */ o71 e;

    public m71(o71 o71Var) {
        this.e = o71Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
        super.onAnimationRepeat(animator);
        o71 o71Var = this.e;
        o71Var.h = (o71Var.h + 1) % o71Var.g.c.length;
        o71Var.i = true;
    }
}
