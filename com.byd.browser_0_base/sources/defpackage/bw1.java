package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: PartialCustomTabBottomSheetStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public final class bw1 extends AnimatorListenerAdapter {
    public final /* synthetic */ Runnable e;

    public bw1(zv1 zv1Var) {
        this.e = zv1Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.e.run();
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
    }
}
