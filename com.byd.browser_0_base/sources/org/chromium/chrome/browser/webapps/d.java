package org.chromium.chrome.browser.webapps;

import defpackage.ds3;
import org.chromium.chrome.browser.webapps.WebappRegistry;

/* JADX INFO: compiled from: WebappDeferredStartupWithStorageHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d implements WebappRegistry.a {
    public final /* synthetic */ e e;

    public d(e eVar) {
        this.e = eVar;
    }

    @Override // org.chromium.chrome.browser.webapps.WebappRegistry.a
    public final void a(ds3 ds3Var) {
        this.e.a(ds3Var, true);
    }
}
