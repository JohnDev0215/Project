package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.kh0;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabGroupUiMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class z extends kh0 {
    public final /* synthetic */ a0 a;

    public z(a0 a0Var) {
        this.a = a0Var;
    }

    @Override // defpackage.kh0, g03.a
    public final void c(int i, Tab tab) {
        a0 a0Var = this.a;
        if (a0Var.w && tab == a0Var.i.i()) {
            a0Var.e(tab.getId());
        }
    }
}
