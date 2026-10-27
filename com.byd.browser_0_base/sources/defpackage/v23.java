package defpackage;

import defpackage.k23;
import defpackage.p33;
import defpackage.z23;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TabPersistentStore.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v23 implements k23.b {
    public final /* synthetic */ p33.b a;
    public final /* synthetic */ z23 b;

    public v23(z23 z23Var, u23 u23Var) {
        this.b = z23Var;
        this.a = u23Var;
    }

    @Override // k23.b
    public final void a(Tab tab) {
        z23.j jVar;
        if (!tab.isDestroyed()) {
            p33.d(tab).f.c(this.a);
        }
        if (tab.n()) {
            HashMap map = l02.l;
            tab.A(false);
            xl2 xl2Var = (xl2) tab.B().b(xl2.class);
            if (xl2Var != null) {
                xl2Var.r.r(Boolean.FALSE);
            }
            z23 z23Var = this.b;
            z23Var.g.remove(tab);
            ArrayDeque arrayDeque = z23Var.i;
            int id = tab.getId();
            Iterator it = arrayDeque.iterator();
            while (true) {
                if (!it.hasNext()) {
                    jVar = null;
                    break;
                } else {
                    jVar = (z23.j) it.next();
                    if (jVar.a == id) {
                        break;
                    }
                }
            }
            arrayDeque.remove(jVar);
            if (z23.i() || z23.h()) {
                z23Var.h.remove(tab);
            }
            z23.g gVar = z23Var.k;
            if (gVar != null && gVar.a.a == tab.getId()) {
                z23.g gVar2 = z23Var.k;
                z23.c cVar = gVar2.b;
                if (cVar != null) {
                    cVar.a(false);
                }
                gVar2.c.a();
                z23Var.k = null;
                z23Var.k();
            }
            z23.f fVar = z23Var.l;
            if (fVar != null && fVar.i == tab.getId()) {
                z23Var.l.a(false);
                z23Var.l = null;
                z23Var.r();
            }
            z23Var.d(t33.a(tab.getId(), tab.isIncognito()), false);
        }
    }

    @Override // k23.b
    public final void b(Tab tab) {
        p33 p33VarD = p33.d(tab);
        p33VarD.f.a(this.a);
        if (p33VarD.j == 2) {
            z23 z23Var = this.b;
            z23Var.b(tab);
            z23Var.r();
        }
    }
}
