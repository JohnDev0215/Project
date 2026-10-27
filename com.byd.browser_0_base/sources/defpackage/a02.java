package defpackage;

import defpackage.r13;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.chromium.base.b;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: PendingTabClosureManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a02 {
    public final w03 a;
    public final a b;
    public final LinkedList<c> c = new LinkedList<>();
    public final b d = new b();

    /* JADX INFO: compiled from: PendingTabClosureManager.java */
    public interface a {
    }

    /* JADX INFO: compiled from: PendingTabClosureManager.java */
    public class b implements w03 {
        public final ArrayList e = new ArrayList();

        public b() {
        }

        @Override // defpackage.w03
        public final int getCount() {
            return this.e.size();
        }

        @Override // defpackage.w03
        public final Tab getTabAt(int i) {
            if (i < 0) {
                return null;
            }
            ArrayList arrayList = this.e;
            if (i >= arrayList.size()) {
                return null;
            }
            return (Tab) arrayList.get(i);
        }

        @Override // defpackage.w03
        public final int index() {
            a02 a02Var = a02.this;
            int iIndex = a02Var.a.index();
            ArrayList arrayList = this.e;
            return iIndex != -1 ? arrayList.indexOf(z7.p(a02Var.a)) : !arrayList.isEmpty() ? 0 : -1;
        }

        @Override // defpackage.w03
        public final boolean isIncognito() {
            return a02.this.a.isIncognito();
        }

        @Override // defpackage.w03
        public final int r(Tab tab) {
            return this.e.indexOf(tab);
        }

        public final Tab t(int i) {
            if (z7.F(a02.this.a, i) != null) {
                return null;
            }
            return z7.F(this, i);
        }
    }

    /* JADX INFO: compiled from: PendingTabClosureManager.java */
    public class c {
        public final LinkedList<Tab> a;
        public final HashSet<Tab> b;

        public c(List list) {
            LinkedList<Tab> linkedList = new LinkedList<>(list);
            this.a = linkedList;
            this.b = new HashSet<>(linkedList);
        }
    }

    public a02(w03 w03Var, r13.a aVar) {
        this.a = w03Var;
        this.b = aVar;
    }

    public final void a(Tab tab) {
        tab.G(false);
        b bVar = this.d;
        int iR = bVar.r(tab);
        int i = -1;
        for (int i2 = 0; i2 < iR; i2++) {
            Tab tabAt = bVar.getTabAt(i2);
            w03 w03Var = this.a;
            if (i == w03Var.getCount() - 1) {
                break;
            }
            int i3 = i + 1;
            if (tabAt == w03Var.getTabAt(i3)) {
                i = i3;
            }
        }
        int i4 = i + 1;
        r13 r13Var = r13.this;
        int i5 = r13Var.r;
        if (i5 >= i4) {
            r13Var.r = i5 + 1;
        }
        r13Var.h.add(i4, tab);
        WebContents webContentsC = tab.c();
        if (webContentsC != null) {
            webContentsC.A(false);
        }
        boolean z = r13Var.s;
        if (r13Var.r == -1) {
            if (z) {
                r13Var.q(i4, 5, false);
            } else {
                r13Var.r = i4;
            }
        }
        Iterator<t13> it = r13Var.n.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).C(tab);
            }
        }
    }

    public final void b(LinkedList linkedList) {
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            this.d.e.remove((Tab) it.next());
        }
        r13.a aVar = (r13.a) this.b;
        r13.this.C(linkedList);
        Iterator it2 = linkedList.iterator();
        while (it2.hasNext()) {
            r13.this.z((Tab) it2.next(), true);
        }
    }

    public final void c() {
        b bVar = this.d;
        ArrayList arrayList = bVar.e;
        arrayList.clear();
        int i = 0;
        while (true) {
            a02 a02Var = a02.this;
            if (i >= a02Var.a.getCount()) {
                return;
            }
            arrayList.add(a02Var.a.getTabAt(i));
            i++;
        }
    }
}
