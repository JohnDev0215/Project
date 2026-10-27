package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.kj;

/* JADX INFO: compiled from: TabSwitcherMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v1 implements kj.a {
    public final /* synthetic */ s1 e;

    public v1(s1 s1Var) {
        this.e = s1Var;
    }

    @Override // kj.a
    public final void c(boolean z, int i, int i2, int i3) {
        s1 s1Var = this.e;
        if (s1Var.M == 2) {
            return;
        }
        s1Var.u();
    }

    @Override // kj.a
    public final void l(int i) {
        this.e.i.n(b0.g, i);
    }

    @Override // kj.a
    public final void n() {
        s1 s1Var = this.e;
        if (s1Var.M == 2) {
            return;
        }
        s1Var.u();
    }
}
