package defpackage;

import J.N;
import defpackage.a02;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModelJniBridge;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.url.GURL;
import org.chromium.url.Origin;

/* JADX INFO: compiled from: TabModelImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class r13 extends TabModelJniBridge {
    public final ArrayList h;
    public final fz2 i;
    public final fz2 j;
    public final w13 k;
    public final TabContentManager l;
    public final o13 m;
    public final b<t13> n;
    public final zm1 o;
    public final f9 p;
    public final a02 q;
    public int r;
    public boolean s;

    /* JADX INFO: compiled from: TabModelImpl.java */
    public class a implements a02.a {
        public a() {
        }
    }

    public r13(Profile profile, int i, fz2 fz2Var, fz2 fz2Var2, w13 w13Var, TabContentManager tabContentManager, zm1 zm1Var, f9 f9Var, o13 o13Var, boolean z) {
        super(profile, i);
        this.h = new ArrayList();
        this.r = -1;
        this.i = fz2Var;
        this.j = fz2Var2;
        this.k = w13Var;
        this.l = tabContentManager;
        this.o = zm1Var;
        this.p = f9Var;
        this.m = o13Var;
        if (z && !this.e) {
            this.q = new a02(this, new a());
        }
        this.n = new b<>();
        this.g = N.M15HofTq(this, profile, this.f);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x004c A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final org.chromium.chrome.browser.tab.Tab A(int r10, int r11, boolean r12) {
        /*
            Method dump skipped, instruction units count: 201
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.r13.A(int, int, boolean):org.chromium.chrome.browser.tab.Tab");
    }

    public final boolean B() {
        ArrayList arrayList = this.h;
        if (arrayList.size() <= 0) {
            return false;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            if (!((Tab) arrayList.get(i)).n()) {
                return true;
            }
        }
        return false;
    }

    public final void C(List<Tab> list) {
        Iterator<t13> it = this.n.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).Y(list);
            }
        }
    }

    public final void D(Tab tab, Tab tab2, int i, boolean z, boolean z2, int i2) {
        WebContents webContentsC;
        int id = tab.getId();
        int iR = r(tab);
        Tab tabP = z7.p(this);
        Tab tabAt = getTabAt(iR == 0 ? 1 : iR - 1);
        if (tab2 == null) {
            tab2 = A(id, i2, false);
        }
        if (z2) {
            c();
        }
        if (z && (webContentsC = tab.c()) != null) {
            webContentsC.f0();
            webContentsC.A(true);
        }
        this.h.remove(tab);
        boolean zIsIncognito = tab2 == null ? false : tab2.isIncognito();
        int id2 = tab2 == null ? -1 : tab2.getId();
        o13 o13Var = this.m;
        int iG = tab2 != null ? z7.G(((a23) o13Var).g(zIsIncognito), id2) : -1;
        if (tab2 != tabP) {
            if (zIsIncognito != this.e) {
                this.r = r(tabAt);
            }
            ((a23) o13Var).g(zIsIncognito).q(iG, i, false);
        } else {
            this.r = iG;
        }
        if (z2 && u()) {
            this.q.c();
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean a(Tab tab) {
        return m(tab, true, false);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void b() {
        e(false);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void c() {
        if (!u()) {
            return;
        }
        a02 a02Var = this.q;
        ListIterator<a02.c> listIterator = a02Var.c.listIterator();
        while (listIterator.hasNext()) {
            a02.c next = listIterator.next();
            listIterator.remove();
            a02Var.b(next.a);
        }
        Iterator<t13> it = this.n.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).P(this.e);
            }
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge
    public final boolean closeTabAt(int i) {
        return a(getTabAt(i));
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge
    public final boolean createTabWithWebContents(Tab tab, Profile profile, WebContents webContents) {
        fz2 fz2VarT = t(profile.h());
        fz2VarT.getClass();
        return fz2VarT.d(tab, webContents, 17, webContents.s());
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void d() {
        boolean z;
        if (u()) {
            a02 a02Var = this.q;
            LinkedList<a02.c> linkedList = a02Var.c;
            if (linkedList.isEmpty()) {
                z = false;
            } else {
                Iterator<Tab> it = linkedList.removeLast().a.iterator();
                while (it.hasNext()) {
                    a02Var.a(it.next());
                }
                z = true;
            }
            if (z) {
                return;
            }
        }
        this.m.o(this);
        if (getCount() == 1) {
            q(0, 2, false);
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge, org.chromium.chrome.browser.tabmodel.TabModel
    public final void destroy() {
        o13 o13Var;
        c();
        ArrayList arrayList = this.h;
        Iterator it = arrayList.iterator();
        while (true) {
            boolean zHasNext = it.hasNext();
            o13Var = this.m;
            if (!zHasNext) {
                break;
            }
            Tab tab = (Tab) it.next();
            if (!((a23) o13Var).k || !this.p.a(tab.getId())) {
                if (tab.isInitialized()) {
                    tab.destroy();
                }
            }
        }
        a02 a02Var = this.q;
        if (a02Var != null) {
            boolean z = ((a23) o13Var).k;
            LinkedList<a02.c> linkedList = a02Var.c;
            if (z) {
                linkedList.clear();
            } else {
                ArrayList<Tab> arrayList2 = a02Var.d.e;
                for (Tab tab2 : arrayList2) {
                    if (tab2.isInitialized()) {
                        tab2.destroy();
                    }
                }
                arrayList2.clear();
                linkedList.clear();
            }
        }
        arrayList.clear();
        this.n.clear();
        super.destroy();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void e(boolean z) {
        b<t13> bVar = this.n;
        Iterator<t13> it = bVar.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                break;
            } else {
                ((t13) aVar.next()).X0(this.e);
            }
        }
        ArrayList arrayList = this.h;
        if (!z) {
            if (!(qv0.e() && !bk3.k(qv0.c()))) {
                for (int i = 0; i < getCount(); i++) {
                    getTabAt(i).G(true);
                }
                ArrayList arrayList2 = new ArrayList(arrayList);
                if (!u()) {
                    C(arrayList2);
                }
                while (getCount() > 0) {
                    y(getTabAt(0), null, false, false, true, false, 2);
                }
                if (!u()) {
                    return;
                }
                this.q.c.add(new a02.c(arrayList2));
                Iterator<t13> it2 = bVar.iterator();
                while (true) {
                    b.a aVar2 = (b.a) it2;
                    if (!aVar2.hasNext()) {
                        return;
                    } else {
                        ((t13) aVar2.next()).w0(arrayList2, true);
                    }
                }
            }
        }
        c();
        for (int i2 = 0; i2 < getCount(); i2++) {
            getTabAt(i2).G(true);
        }
        C(arrayList);
        while (getCount() > 0) {
            y(getTabAt(0), null, true, z, false, false, 2);
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final Tab g(int i, boolean z) {
        return A(i, 0, z);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge, defpackage.w03
    public final int getCount() {
        return this.h.size();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge, defpackage.w03
    public final Tab getTabAt(int i) {
        if (i < 0) {
            return null;
        }
        ArrayList arrayList = this.h;
        if (i >= arrayList.size()) {
            return null;
        }
        return (Tab) arrayList.get(i);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean h(Tab tab, Tab tab2, boolean z, boolean z2) {
        return y(tab, tab2, false, z, z2, z2, 0);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void i(Tab tab, int i, int i2, int i3) {
        boolean z = this.e;
        w13 w13Var = this.k;
        b<t13> bVar = this.n;
        try {
            TraceEvent.q("TabModelImpl.addTab", null);
            Iterator<t13> it = bVar.iterator();
            while (true) {
                b.a aVar = (b.a) it;
                if (!aVar.hasNext()) {
                    break;
                } else {
                    ((t13) aVar.next()).d1(i2, tab);
                }
            }
            boolean zB = ((x13) w13Var).b(i2, z);
            ArrayList arrayList = this.h;
            boolean z2 = true;
            boolean z3 = zB || (arrayList.size() == 0 && i2 == 5);
            int iA = ((x13) w13Var).a(i2, i, tab);
            if (tab.isIncognito() != z) {
                throw new IllegalStateException("Attempting to open tab in wrong model");
            }
            c();
            if (iA < 0 || iA > arrayList.size()) {
                arrayList.add(tab);
            } else {
                arrayList.add(iA, tab);
                int i4 = this.r;
                if (iA <= i4) {
                    this.r = i4 + 1;
                }
            }
            if (!this.s) {
                this.r = Math.max(this.r, 0);
            }
            if (u()) {
                this.q.c();
            }
            int iR = r(tab);
            long j = this.g;
            if (j == 0) {
                z2 = false;
            }
            if (z2) {
                N.M2Pb3$9r(j, this, tab);
            }
            Iterator<t13> it2 = bVar.iterator();
            while (true) {
                b.a aVar2 = (b.a) it2;
                if (!aVar2.hasNext()) {
                    break;
                } else {
                    ((t13) aVar2.next()).f1(tab, i2, i3, z3);
                }
            }
            if (z3) {
                q(iR, 2, false);
            }
        } finally {
            TraceEvent.C("TabModelImpl.addTab");
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge, defpackage.w03
    public final int index() {
        return this.r;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge, org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean isActiveModel() {
        return this.s;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge
    public final boolean isSessionRestoreInProgress() {
        return ((d23) this.m).m.get();
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void j(t13 t13Var) {
        this.n.a(t13Var);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final w03 k() {
        return !u() ? this : this.q.d;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void l(t13 t13Var) {
        this.n.c(t13Var);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean m(Tab tab, boolean z, boolean z2) {
        return y(tab, null, z, false, z2, z2, 0);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void n(int i) {
        if (u()) {
            a02 a02Var = this.q;
            Tab tabT = a02Var.d.t(i);
            if (tabT == null) {
                return;
            }
            ListIterator<a02.c> listIterator = a02Var.c.listIterator();
            while (listIterator.hasNext()) {
                a02.c next = listIterator.next();
                boolean zRemove = next.b.remove(tabT);
                if (zRemove) {
                    next.a.remove(tabT);
                }
                if (zRemove) {
                    a02Var.a(tabT);
                    if (next.b.isEmpty()) {
                        listIterator.remove();
                        return;
                    }
                    return;
                }
            }
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void o(Tab tab) {
        D(tab, null, 0, false, true, 0);
        Iterator<t13> it = this.n.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).n0(tab);
            }
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge
    public final void openNewTab(Tab tab, GURL gurl, Origin origin, String str, ResourceRequestBody resourceRequestBody, int i, boolean z, boolean z2) {
        if (tab.n()) {
            return;
        }
        boolean zIsIncognito = tab.isIncognito();
        int i2 = 4;
        if (i != 3) {
            if (i == 4 || i == 5) {
                i2 = 5;
            } else if (i != 6 && i == 8) {
                zIsIncognito = true;
            }
        }
        LoadUrlParams loadUrlParams = new LoadUrlParams(gurl.j(), 0);
        loadUrlParams.b = origin;
        loadUrlParams.g = str;
        loadUrlParams.i = resourceRequestBody;
        if (resourceRequestBody != null) {
            loadUrlParams.c = 1;
        }
        loadUrlParams.n = z2;
        fz2 fz2VarT = t(zIsIncognito);
        if (!z) {
            tab = null;
        }
        fz2VarT.b(i2, tab, loadUrlParams);
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void p(List list) {
        Iterator it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Tab tab = (Tab) it.next();
            if (this.h.contains(tab)) {
                tab.G(true);
            }
        }
        boolean z = u();
        if (!z) {
            C(list);
        }
        b<t13> bVar = this.n;
        Iterator<t13> it2 = bVar.iterator();
        while (true) {
            b.a aVar = (b.a) it2;
            if (!aVar.hasNext()) {
                break;
            } else {
                ((t13) aVar.next()).k0(list, z);
            }
        }
        Iterator it3 = list.iterator();
        while (it3.hasNext()) {
            y((Tab) it3.next(), null, false, false, true, false, 1);
        }
        if (!z) {
            return;
        }
        this.q.c.add(new a02.c(list));
        Iterator<t13> it4 = bVar.iterator();
        while (true) {
            b.a aVar2 = (b.a) it4;
            if (!aVar2.hasNext()) {
                return;
            } else {
                ((t13) aVar2.next()).w0(list, false);
            }
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void q(int i, int i2, boolean z) {
        Tab tabP;
        try {
            TraceEvent.q("TabModelImpl.setIndex", null);
            o13 o13Var = this.m;
            boolean z2 = true;
            int id = (i2 == 0 || i2 == 1 || (tabP = z7.p(((a23) o13Var).z())) == null) ? -1 : tabP.getId();
            if (!this.s) {
                o13Var.d(this.e);
            }
            if (B()) {
                this.r = a8.q(i, 0, this.h.size() - 1);
            } else {
                this.r = -1;
            }
            Tab tabP2 = z7.p(this);
            if (!z || tabP2 == null) {
                ((d23) o13Var).G(i2, tabP2);
            }
            if (tabP2 != null) {
                Iterator<t13> it = this.n.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (!aVar.hasNext()) {
                        break;
                    } else {
                        ((t13) aVar.next()).B0(i2, id, tabP2);
                    }
                }
                if (tabP2.getId() != id) {
                    z2 = false;
                }
                if (!z2 && i2 == 3) {
                    n3.X0("MobileTabSwitched");
                }
            }
        } finally {
            TraceEvent.C("TabModelImpl.setIndex");
        }
    }

    @Override // defpackage.w03
    public final int r(Tab tab) {
        int iIndexOf;
        if (tab == null || (iIndexOf = this.h.indexOf(tab)) == -1) {
            return -1;
        }
        return iIndexOf;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void s(int i, int i2) {
        ArrayList arrayList = this.h;
        int iQ = a8.q(i2, 0, arrayList.size());
        int iG = z7.G(this, i);
        if (iG == -1 || iG == iQ || iG + 1 == iQ) {
            return;
        }
        c();
        Tab tab = (Tab) arrayList.remove(iG);
        if (iG < iQ) {
            iQ--;
        }
        arrayList.add(iQ, tab);
        int i3 = this.r;
        if (iG == i3) {
            this.r = iQ;
        } else if (iG < i3 && iQ >= i3) {
            this.r = i3 - 1;
        } else if (iG > i3 && iQ <= i3) {
            this.r = i3 + 1;
        }
        if (u()) {
            this.q.c();
        }
        Iterator<t13> it = this.n.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).h1(iQ, iG, tab);
            }
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void setActive(boolean z) {
        this.s = z;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModelJniBridge
    public final fz2 t(boolean z) {
        return z ? this.j : this.i;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean u() {
        return this.q != null;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void v() {
        if (!u()) {
            return;
        }
        Iterator<t13> it = r13.this.n.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).T();
            }
        }
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final boolean w(int i) {
        return u() && this.q.d.t(i) != null;
    }

    @Override // org.chromium.chrome.browser.tabmodel.TabModel
    public final void x(int i) {
        if (u()) {
            a02 a02Var = this.q;
            Tab tabT = a02Var.d.t(i);
            if (tabT == null) {
                return;
            }
            ListIterator<a02.c> listIterator = a02Var.c.listIterator();
            while (listIterator.hasNext()) {
                a02.c next = listIterator.next();
                if (next.b.remove(tabT)) {
                    if (next.b.isEmpty()) {
                        listIterator.remove();
                        a02Var.b(next.a);
                        return;
                    }
                    return;
                }
            }
        }
    }

    public final boolean y(Tab tab, Tab tab2, boolean z, boolean z2, boolean z3, boolean z4, int i) {
        if (tab == null || !this.h.contains(tab)) {
            return false;
        }
        boolean zU = z3 & u();
        tab.G(true);
        b<t13> bVar = this.n;
        Iterator<t13> it = bVar.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                break;
            }
            ((t13) aVar.next()).t(tab, z, i == 0);
        }
        D(tab, tab2, z2 ? 1 : 0, zU, !zU, i);
        if (z4 && zU) {
            this.q.c.add(new a02.c(Collections.singletonList(tab)));
            Iterator<t13> it2 = bVar.iterator();
            while (true) {
                b.a aVar2 = (b.a) it2;
                if (!aVar2.hasNext()) {
                    break;
                }
                ((t13) aVar2.next()).h0(tab);
            }
        }
        if (!zU) {
            if (i == 0) {
                C(Collections.singletonList(tab));
            }
            z(tab, false);
        }
        return true;
    }

    public final void z(Tab tab, boolean z) {
        this.l.f(tab.getId());
        b<t13> bVar = this.n;
        Iterator<t13> it = bVar.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                break;
            } else {
                ((t13) aVar.next()).V0(tab);
            }
        }
        if (z) {
            Iterator<t13> it2 = bVar.iterator();
            while (true) {
                b.a aVar2 = (b.a) it2;
                if (!aVar2.hasNext()) {
                    break;
                } else {
                    ((t13) aVar2.next()).i1(tab);
                }
            }
        }
        tab.destroy();
    }
}
