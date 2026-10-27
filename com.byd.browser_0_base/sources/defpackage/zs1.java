package defpackage;

import defpackage.ck;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.top.ToolbarLayout;
import org.chromium.chrome.browser.toolbar.top.ToolbarPhone;

/* JADX INFO: compiled from: OptionalBrowsingModeButtonController.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zs1 {
    public final pk3 a;
    public final HashMap b;
    public ck c;
    public final List<ck> d;
    public final ToolbarLayout e;
    public final nw2<Tab> f;

    public zs1(List list, pk3 pk3Var, ToolbarLayout toolbarLayout, yo yoVar) {
        this.d = list;
        this.a = pk3Var;
        this.e = toolbarLayout;
        this.f = yoVar;
        this.b = new HashMap(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            final ck ckVar = (ck) it.next();
            ck.a aVar = new ck.a() { // from class: ys1
                @Override // ck.a
                public final void a(boolean z) {
                    zs1 zs1Var = this.e;
                    ck ckVar2 = zs1Var.c;
                    ck ckVar3 = ckVar;
                    if (ckVar3 != ckVar2) {
                        if (z) {
                            zs1Var.b();
                            return;
                        }
                        return;
                    }
                    bk bkVarC = ckVar3.c(zs1Var.f.get());
                    if (bkVarC != null && bkVarC.a) {
                        zs1Var.a(ckVar3, bkVarC);
                        return;
                    }
                    zs1Var.e.i();
                    zs1Var.c = null;
                    zs1Var.b();
                }
            };
            ckVar.d(aVar);
            this.b.put(ckVar, aVar);
        }
    }

    public final void a(ck ckVar, ak akVar) {
        this.c = ckVar;
        ToolbarLayout toolbarLayout = this.e;
        toolbarLayout.I(akVar);
        if (akVar.b().f == null || (toolbarLayout instanceof ToolbarPhone)) {
            return;
        }
        this.a.a(akVar.b().f.a());
    }

    public final void b() {
        List<ck> list = this.d;
        if (list == null) {
            return;
        }
        for (ck ckVar : list) {
            bk bkVarC = ckVar.c(this.f.get());
            if (bkVarC != null && bkVarC.a) {
                if (ckVar == this.c) {
                    return;
                }
                a(ckVar, bkVarC);
                return;
            }
        }
        this.e.i();
        this.c = null;
    }
}
