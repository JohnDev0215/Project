package org.chromium.chrome.browser.tasks.tab_management;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.tasks.tab_management.h0;

/* JADX INFO: compiled from: TabListFaviconProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d0 implements h0.d {
    public final /* synthetic */ boolean a;
    public final /* synthetic */ h0 b;

    public d0(h0 h0Var, boolean z) {
        this.b = h0Var;
        this.a = z;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.h0.d
    public final void a(Callback<h0.c> callback) {
        callback.onResult(this.b.e(this.a));
    }
}
