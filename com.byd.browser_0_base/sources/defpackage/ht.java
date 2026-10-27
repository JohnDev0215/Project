package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import defpackage.l5;

/* JADX INFO: compiled from: CircularIndeterminateAnimatorDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class ht extends AnimatorListenerAdapter {
    public final /* synthetic */ jt e;

    public ht(jt jtVar) {
        this.e = jtVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        jt jtVar = this.e;
        jtVar.a();
        l5.a aVar = jtVar.k;
        if (aVar != null) {
            aVar.b(jtVar.a);
        }
    }
}
