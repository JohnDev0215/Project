package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import defpackage.l5;

/* JADX INFO: compiled from: LinearIndeterminateDisjointAnimatorDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class n71 extends AnimatorListenerAdapter {
    public final /* synthetic */ o71 e;

    public n71(o71 o71Var) {
        this.e = o71Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        o71 o71Var = this.e;
        o71Var.a();
        l5.a aVar = o71Var.k;
        if (aVar != null) {
            aVar.b(o71Var.a);
        }
    }
}
