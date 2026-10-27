package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: DropdownMenuEndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class oe0 extends AnimatorListenerAdapter {
    public final /* synthetic */ pe0 e;

    public oe0(pe0 pe0Var) {
        this.e = pe0Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        pe0 pe0Var = this.e;
        pe0Var.q();
        pe0Var.o.start();
    }
}
