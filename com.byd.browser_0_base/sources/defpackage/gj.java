package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: BrowserControlsManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class gj extends AnimatorListenerAdapter {
    public final /* synthetic */ int e;
    public final /* synthetic */ fj f;

    public gj(fj fjVar, int i) {
        this.f = fjVar;
        this.e = i;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationCancel(Animator animator) {
        fj fjVar = this.f;
        fjVar.C(false, 0, 0, this.e, fjVar.m, fjVar.o);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.f.A = null;
    }
}
