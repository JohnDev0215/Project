package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: CircularIndeterminateAnimatorDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class gt extends AnimatorListenerAdapter {
    public final /* synthetic */ jt e;

    public gt(jt jtVar) {
        this.e = jtVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationRepeat(Animator animator) {
        super.onAnimationRepeat(animator);
        jt jtVar = this.e;
        jtVar.h = (jtVar.h + 4) % jtVar.g.c.length;
    }
}
