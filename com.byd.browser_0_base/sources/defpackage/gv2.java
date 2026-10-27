package defpackage;

import defpackage.lw;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: StripLayoutTab.java */
/* JADX INFO: loaded from: classes.dex */
public final class gv2 implements lw.c {
    public final /* synthetic */ fv2 a;

    public gv2(fv2 fv2Var) {
        this.a = fv2Var;
    }

    @Override // lw.c
    public final void a(long j) {
        fv2 fv2Var = this.a;
        vu2 vu2Var = (vu2) fv2Var.b;
        vu2Var.getClass();
        if (fv2Var.h) {
            return;
        }
        vu2Var.X = false;
        vu2Var.g();
        fv2[] fv2VarArr = vu2Var.h;
        int length = fv2VarArr.length;
        int i = fv2Var.a;
        boolean z = length == 0 || fv2VarArr[fv2VarArr.length - 1].a == i;
        boolean z2 = vu2Var.Z && fv2VarArr.length > 1;
        kw kwVarG = kw.g(((h61) vu2Var.a).G, fv2Var, fv2.D, fv2Var.q, fv2Var.v, 150L);
        fv2Var.h = true;
        Tab tabG = vu2Var.c.g(i, false);
        kwVarG.addListener(new wu2(vu2Var, z2, fv2Var, tabG, z));
        vu2Var.X = true;
        vu2Var.m = kwVarG;
        kwVarG.start();
        if (z2 || tabG == null) {
            return;
        }
        vu2Var.C(j, tabG.getId(), fv2Var.a, false);
    }
}
