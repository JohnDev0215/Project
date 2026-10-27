package org.chromium.chrome.browser.toolbar.top;

import android.content.Context;
import defpackage.bz0;
import defpackage.yp;
import defpackage.z7;
import org.chromium.chrome.browser.toolbar.top.m;

/* JADX INFO: compiled from: ToolbarColorObserverManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i implements m.a, m.b {
    public m.b e;
    public final Context f;
    public bz0 g;
    public float h = 0.0f;
    public int i;

    public i(Context context, ToolbarLayout toolbarLayout) {
        this.f = context;
        if (toolbarLayout instanceof ToolbarPhone) {
            this.i = ((ToolbarPhone) toolbarLayout).getToolbarBackgroundColor();
        }
    }

    public final void a() {
        bz0 bz0Var;
        if (this.e == null || (bz0Var = this.g) == null) {
            return;
        }
        this.e.c(z7.o(this.i, yp.b(this.f, bz0Var.b()), this.h, false));
    }

    @Override // org.chromium.chrome.browser.toolbar.top.m.b
    public final void c(int i) {
        this.i = i;
        a();
    }
}
