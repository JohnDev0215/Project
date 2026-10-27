package defpackage;

import defpackage.ll;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: CurrentTabObserver.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i20 {
    public final zp1<Tab> a;
    public final n23 b;
    public final ll.b c;
    public ll d;
    public Tab e;

    public i20(zp1<Tab> zp1Var, n23 n23Var, Callback<Tab> callback) {
        this.a = zp1Var;
        this.b = n23Var;
        ll llVar = new ll();
        this.d = llVar;
        ll.b bVarB = llVar.b(new xm1(this, 11, callback));
        this.c = bVarB;
        zp1Var.p(bVarB);
    }

    public final void a() {
        Tab tab = this.e;
        if (tab != null) {
            tab.t(this.b);
        }
        this.a.d(this.c);
        this.d.a();
        this.d = null;
    }
}
