package org.chromium.chrome.browser.webapps;

import defpackage.bp3;
import defpackage.oj;
import defpackage.p61;
import defpackage.pk;
import defpackage.q2;
import defpackage.v80;
import defpackage.zs;

/* JADX INFO: compiled from: WebApkActivityCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements v80 {
    public final oj e;
    public final p61<WebApkUpdateManager> f;
    public final pk g;

    public a(e eVar, q2 q2Var, oj ojVar, p61 p61Var, pk pkVar) {
        this.e = ojVar;
        this.f = p61Var;
        this.g = pkVar;
        eVar.d.add(new bp3(this, q2Var, 0));
        q2Var.f(this);
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        zs.b(true);
    }
}
