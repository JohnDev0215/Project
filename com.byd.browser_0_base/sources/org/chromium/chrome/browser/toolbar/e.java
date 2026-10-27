package org.chromium.chrome.browser.toolbar;

import defpackage.x10;
import org.chromium.chrome.browser.toolbar.g;

/* JADX INFO: compiled from: ToolbarManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e extends g.c {
    public final /* synthetic */ g d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(g gVar) {
        super();
        this.d = gVar;
    }

    @Override // org.chromium.chrome.browser.toolbar.g.c, defpackage.km1
    public final void c(x10 x10Var) {
        if (this.b == this.d.j()) {
            return;
        }
        super.c(x10Var);
    }

    @Override // org.chromium.chrome.browser.toolbar.g.c
    public final boolean l() {
        return true;
    }
}
