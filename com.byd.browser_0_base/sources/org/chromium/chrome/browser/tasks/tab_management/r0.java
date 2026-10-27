package org.chromium.chrome.browser.tasks.tab_management;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.ao3;
import defpackage.df0;
import defpackage.oj2;

/* JADX INFO: compiled from: TabListRecyclerView.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r0 extends AnimatorListenerAdapter {
    public final /* synthetic */ TabListRecyclerView e;

    public r0(TabListRecyclerView tabListRecyclerView) {
        this.e = tabListRecyclerView;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        df0 df0Var;
        TabListRecyclerView tabListRecyclerView = this.e;
        tabListRecyclerView.Q0 = null;
        tabListRecyclerView.c1 = true;
        tabListRecyclerView.S0.e();
        RecyclerView.j jVar = tabListRecyclerView.a1;
        if (jVar != null) {
            tabListRecyclerView.setItemAnimator(jVar);
            tabListRecyclerView.a1 = null;
        }
        tabListRecyclerView.q0(tabListRecyclerView.computeVerticalScrollOffset() > 0);
        if (tabListRecyclerView.U0 != null) {
            if (tabListRecyclerView.V0 && (df0Var = tabListRecyclerView.T0) != null) {
                df0Var.e(tabListRecyclerView.P0);
                tabListRecyclerView.V0 = false;
            }
            tabListRecyclerView.U0.i.b();
        }
        if (oj2.H()) {
            ao3.e(tabListRecyclerView, "TabListRecyclerView.startShowing.AnimatorListenerAdapter.onAnimationEnd");
        }
    }
}
