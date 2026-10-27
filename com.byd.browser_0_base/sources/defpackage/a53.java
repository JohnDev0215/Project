package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import org.chromium.chrome.browser.tasks.tab_management.q1;

/* JADX INFO: compiled from: TabSwitcherLayout.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a53 extends AnimatorListenerAdapter {
    public final /* synthetic */ q1 e;

    public a53(q1 q1Var) {
        this.e = q1Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        q1 q1Var = this.e;
        q1Var.t = null;
        q1Var.D.p();
        q1Var.u = false;
        q1Var.f();
        q1.P(q1Var, false);
    }
}
