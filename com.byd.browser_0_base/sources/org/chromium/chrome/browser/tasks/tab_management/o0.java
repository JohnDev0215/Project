package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.ef0;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class o0 implements Runnable {
    public final /* synthetic */ TabListRecyclerView e;
    public final /* synthetic */ Runnable f;

    public /* synthetic */ o0(TabListRecyclerView tabListRecyclerView, Runnable runnable) {
        this.e = tabListRecyclerView;
        this.f = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TabListRecyclerView tabListRecyclerView = this.e;
        s0 s0Var = tabListRecyclerView.U0;
        Runnable runnable = this.f;
        if (s0Var == null) {
            runnable.run();
        } else {
            s0Var.a(new ef0(s0Var, new q0(tabListRecyclerView, 0, runnable)));
            tabListRecyclerView.U0.i();
        }
    }
}
