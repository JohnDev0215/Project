package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: ClearTextEndIconDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public final class xt extends AnimatorListenerAdapter {
    public final /* synthetic */ zt e;

    public xt(zt ztVar) {
        this.e = ztVar;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.e.b.g(true);
    }
}
