package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: StripLayoutHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class yu2 extends AnimatorListenerAdapter {
    public final /* synthetic */ boolean e;
    public final /* synthetic */ vu2 f;

    public yu2(vu2 vu2Var, boolean z) {
        this.f = vu2Var;
        this.e = z;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        vu2 vu2Var = this.f;
        vu2Var.Y = false;
        if (this.e) {
            vu2Var.D = 0.0f;
        }
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.f.Y = true;
    }
}
