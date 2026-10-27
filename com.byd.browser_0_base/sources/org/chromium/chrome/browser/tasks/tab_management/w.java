package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.l61;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabGroupUiMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class w implements l61.a {
    public final /* synthetic */ a0 e;

    public w(a0 a0Var) {
        this.e = a0Var;
    }

    @Override // l61.a
    public final void f(int i) {
        if (i == 2 || i == 16) {
            a0 a0Var = this.e;
            a0Var.x = false;
            Tab tabI = a0Var.i.i();
            if (tabI == null) {
                return;
            }
            a0Var.e(tabI.getId());
        }
    }

    @Override // l61.a
    public final void g(int i, boolean z) {
        if (i == 2 || i == 16) {
            a0 a0Var = this.e;
            a0Var.x = true;
            a0Var.e(-1);
        }
    }
}
