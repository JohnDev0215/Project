package defpackage;

import java.util.LinkedHashMap;
import java.util.List;
import org.chromium.chrome.browser.tasks.tab_management.a0;
import org.chromium.chrome.browser.tasks.tab_management.b0;
import org.chromium.chrome.browser.tasks.tab_management.d;
import org.chromium.chrome.browser.tasks.tab_management.f;
import org.chromium.chrome.browser.tasks.tab_management.i;
import org.chromium.chrome.browser.tasks.tab_management.j0;
import org.chromium.chrome.browser.tasks.tab_management.o1;
import org.chromium.chrome.browser.tasks.tab_management.s1;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class m03 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;
    public final /* synthetic */ Object g;

    public /* synthetic */ m03(Object obj, int i, Object obj2) {
        this.e = i;
        this.f = obj;
        this.g = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        s1.c cVar;
        int i = this.e;
        Object obj = this.g;
        Object obj2 = this.f;
        switch (i) {
            case 0:
                a0 a0Var = (a0) obj2;
                a0Var.getClass();
                a0Var.f.p(n03.f, Integer.valueOf(((List) obj).indexOf(a0Var.i.i())));
                break;
            default:
                j0.k kVar = (j0.k) obj2;
                xl2 xl2Var = (xl2) obj;
                kVar.getClass();
                if (k42.b() && (cVar = kVar.b) != null && xl2Var != null && xl2Var.q() != null) {
                    i.b bVar = new i.b(kVar.a.getId(), xl2Var.q());
                    o1 o1Var = (o1) cVar;
                    if (o1Var.D != null && k42.b()) {
                        LinkedHashMap linkedHashMap = o1Var.q.a.d;
                        if (!(linkedHashMap.containsKey(3) && ((d.a) linkedHashMap.get(3)).b.h(f.b) == 0) && o1Var.D.i(0, bVar)) {
                            o1Var.u(3);
                            int iIndex = o1Var.o.u().c().index();
                            s1 s1Var = o1Var.j;
                            s1Var.getClass();
                            s1Var.i.p(b0.d, Integer.valueOf(iIndex));
                        }
                        break;
                    }
                }
                break;
        }
    }
}
