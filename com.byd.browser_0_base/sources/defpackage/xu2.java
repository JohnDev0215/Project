package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: StripLayoutHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class xu2 extends AnimatorListenerAdapter {
    public final /* synthetic */ vu2 e;

    public xu2(vu2 vu2Var) {
        this.e = vu2Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.e.X = false;
    }
}
