package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: SimpleAnimationLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class tm2 extends AnimatorListenerAdapter {
    public final /* synthetic */ int e;
    public final /* synthetic */ um2 f;

    public tm2(um2 um2Var, int i) {
        this.f = um2Var;
        this.e = i;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.f.I(this.e, false);
    }
}
