package defpackage;

import org.chromium.chrome.browser.webapps.WebappRegistry;
import org.chromium.chrome.browser.webapps.e;

/* JADX INFO: compiled from: WebappDisclosureController.java */
/* JADX INFO: loaded from: classes.dex */
public final class is3 extends oa0 {
    public final oj h;

    public is3(oj ojVar, e eVar, ag3 ag3Var, q2 q2Var, h20 h20Var) {
        super(ag3Var, q2Var, h20Var, ojVar.j());
        this.h = ojVar;
        eVar.d.add(new hs3(this, q2Var, 0));
    }

    @Override // defpackage.oa0, ag3.a
    public final void a() {
        WebappRegistry.b.a.c(this.h.M().a).b.edit().putBoolean("show_disclosure", false).apply();
        this.e.n(ag3.c, 2);
    }

    @Override // defpackage.oa0
    public final boolean c() {
        return false;
    }

    @Override // defpackage.oa0
    public final boolean d() {
        ds3 ds3VarC;
        oj ojVar = this.h;
        if (ojVar.j() == null || ojVar.j().startsWith("org.chromium.webapk") || (ds3VarC = WebappRegistry.b.a.c(ojVar.M().a)) == null) {
            return false;
        }
        return ds3VarC.b.getBoolean("show_disclosure", false);
    }
}
