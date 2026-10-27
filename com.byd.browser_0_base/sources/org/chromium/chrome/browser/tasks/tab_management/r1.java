package org.chromium.chrome.browser.tasks.tab_management;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

/* JADX INFO: compiled from: TabSwitcherLayout.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r1 extends AnimatorListenerAdapter {
    public final /* synthetic */ q1 e;

    public r1(q1 q1Var) {
        this.e = q1Var;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        q1 q1Var = this.e;
        q1Var.t = null;
        q1Var.z.Z0(true);
        q1.P(q1Var, true);
    }
}
