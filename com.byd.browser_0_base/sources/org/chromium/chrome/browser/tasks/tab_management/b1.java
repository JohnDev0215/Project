package org.chromium.chrome.browser.tasks.tab_management;

import defpackage.h23;
import defpackage.y13;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabSelectionEditorMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b1 extends h23 {
    public final /* synthetic */ a1 g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public b1(a1 a1Var, y13 y13Var) {
        super(y13Var);
        this.g = a1Var;
    }

    @Override // defpackage.t13
    public final void f1(Tab tab, int i, int i2, boolean z) {
        a1 a1Var = this.g;
        if (a1Var.f.t()) {
            if (i == 3 || i == 6) {
                a1Var.d(false);
            }
        }
    }

    @Override // defpackage.t13
    public final void t(Tab tab, boolean z, boolean z2) {
        this.g.d(false);
    }
}
