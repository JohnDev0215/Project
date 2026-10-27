package org.chromium.chrome.browser.webapps;

import defpackage.ds3;
import defpackage.i9;
import org.chromium.chrome.browser.webapps.WebappRegistry;

/* JADX INFO: compiled from: WebappRegistry.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f extends i9<ds3> {
    public final /* synthetic */ String h;
    public final /* synthetic */ WebappRegistry.a i;
    public final /* synthetic */ WebappRegistry j;

    public f(WebappRegistry webappRegistry, String str, WebappRegistry.a aVar) {
        this.j = webappRegistry;
        this.h = str;
        this.i = aVar;
    }

    @Override // defpackage.i9
    public final ds3 b() {
        ds3 ds3Var = new ds3(this.h);
        ds3Var.b.getLong("last_used", 0L);
        return ds3Var;
    }

    @Override // defpackage.i9
    public final void l(ds3 ds3Var) {
        ds3 ds3Var2 = ds3Var;
        WebappRegistry webappRegistry = this.j;
        webappRegistry.b.put(this.h, ds3Var2);
        webappRegistry.c.edit().putStringSet("webapp_set", webappRegistry.b.keySet()).apply();
        ds3Var2.b.edit().putLong("last_used", System.currentTimeMillis()).apply();
        WebappRegistry.a aVar = this.i;
        if (aVar != null) {
            aVar.a(ds3Var2);
        }
    }
}
