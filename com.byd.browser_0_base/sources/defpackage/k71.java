package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: LinearIndeterminateContiguousAnimatorDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class k71 extends AnimatorListenerAdapter {
    public final /* synthetic */ l71 e;

    public k71(l71 l71Var) {
        this.e = l71Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
        super.onAnimationRepeat(animator);
        l71 l71Var = this.e;
        l71Var.g = (l71Var.g + 1) % l71Var.f.c.length;
        l71Var.h = true;
    }
}
