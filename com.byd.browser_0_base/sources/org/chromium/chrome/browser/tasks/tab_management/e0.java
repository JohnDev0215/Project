package org.chromium.chrome.browser.tasks.tab_management;

import org.chromium.base.Callback;
import org.chromium.chrome.browser.tasks.tab_management.h0;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabListFaviconProvider.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e0 implements h0.d {
    public final /* synthetic */ GURL a;
    public final /* synthetic */ boolean b;
    public final /* synthetic */ h0 c;

    public e0(h0 h0Var, GURL gurl, boolean z) {
        this.c = h0Var;
        this.a = gurl;
        this.b = z;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.h0.d
    public final void a(Callback<h0.c> callback) {
        this.c.c(this.a, this.b, callback);
    }
}
