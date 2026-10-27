package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import defpackage.ef2;

/* JADX INFO: compiled from: ScrimMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hf2 extends AnimatorListenerAdapter {
    public final /* synthetic */ if2 e;

    public hf2(if2 if2Var) {
        this.e = if2Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        ef2.a aVar = this.e.b;
        if (aVar != null) {
            aVar.c(0);
        }
    }
}
