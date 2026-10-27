package defpackage;

import J.N;
import android.os.Handler;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.b;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.ntp.RecentlyClosedBridge;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: TabModelSelectorImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d23 extends a23 {
    public final AtomicBoolean m;
    public final boolean n;
    public final int o;
    public final x13 p;
    public final f9 q;
    public final zm1 r;
    public TabContentManager s;
    public RecentlyClosedBridge t;
    public Tab u;
    public final nw2<WindowAndroid> v;

    /* JADX INFO: compiled from: TabModelSelectorImpl.java */
    public class a implements Runnable {
        public final /* synthetic */ TabModel e;

        public a(TabModel tabModel) {
            this.e = tabModel;
        }

        @Override // java.lang.Runnable
        public final void run() {
            d23 d23Var = d23.this;
            d23Var.E();
            if (d23Var.h == null || this.e.isIncognito()) {
                return;
            }
            ((iy0) d23Var.h).g(12);
        }
    }

    public d23(hs hsVar, gz2 gz2Var, qr qrVar, zm1 zm1Var, f9 f9Var, boolean z, int i) {
        super(gz2Var, qrVar);
        this.m = new AtomicBoolean(true);
        this.v = hsVar;
        this.n = z;
        this.p = new x13(this);
        this.r = zm1Var;
        this.q = f9Var;
        this.o = i;
    }

    @Override // defpackage.a23
    public final void B() {
        super.B();
        RecentlyClosedBridge recentlyClosedBridge = this.t;
        if (recentlyClosedBridge != null) {
            N.MN6LZLAP(recentlyClosedBridge.a);
            recentlyClosedBridge.a = 0L;
            recentlyClosedBridge.c = null;
        }
    }

    @Override // defpackage.a23
    public final void D() {
        r13 r13Var;
        super.D();
        if (!this.m.getAndSet(false) || (r13Var = (r13) g(false)) == null) {
            return;
        }
        N.MY_BqaOA(r13Var.g, r13Var);
        if (r13Var.B() && r13Var.r == -1) {
            if (r13Var.s) {
                r13Var.q(0, 3, false);
            } else {
                r13Var.r = 0;
            }
        }
        Iterator<t13> it = r13Var.n.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((t13) aVar.next()).p0();
            }
        }
    }

    @Override // defpackage.a23
    public final void F(TabContentManager tabContentManager) {
        k13 k13VarR;
        gz2 gz2Var = this.l;
        or orVar = (or) gz2Var.p0(false);
        or orVar2 = (or) gz2Var.p0(true);
        this.t = new RecentlyClosedBridge(Profile.d(), this);
        Profile profileD = Profile.d();
        int i = this.o;
        x13 x13Var = this.p;
        r13 r13Var = new r13(profileD, i, orVar, orVar2, x13Var, tabContentManager, this.r, this.q, this, this.n);
        orVar.d = r13Var;
        orVar.e = x13Var;
        fz0 fz0Var = new fz0(new gz0(this.v, orVar, orVar2, x13Var, tabContentManager, this.r, this.q, this.o, this));
        orVar2.d = fz0Var;
        orVar2.e = x13Var;
        this.s = tabContentManager;
        ArrayList arrayList = this.a;
        arrayList.add(r13Var);
        arrayList.add(fz0Var);
        this.b = fz0Var;
        this.e = C(this.j);
        q13 q13Var = this.c;
        q13Var.getClass();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            TabModel tabModel = (TabModel) arrayList.get(i2);
            arrayList2.add((!oj2.D(this.d.a) || (k13VarR = z7.r()) == null) ? new mh0(tabModel) : k13VarR.a(tabModel));
        }
        q13Var.e = Collections.unmodifiableList(arrayList2);
        ArrayList<t13> arrayList3 = q13Var.f;
        for (t13 t13Var : arrayList3) {
            Iterator<p13> it = q13Var.e.iterator();
            while (it.hasNext()) {
                it.next().f.a(t13Var);
            }
        }
        arrayList3.clear();
        m(q13Var);
        q13Var.a(new z13(this));
        ((fz0) this.b).g.a(this);
        fz0Var.setActive(this.j);
        r13Var.s = !this.j;
        E();
        m(new b23(this));
        new c23(this, this);
    }

    public final void G(int i, Tab tab) {
        Tab tab2;
        boolean z = tab != null && tab.getLaunchType() == 1;
        if (this.u != tab && tab != null && !tab.isNativePage()) {
            n3.y = SystemClock.uptimeMillis();
            n3.z = i;
            n3.A = false;
        }
        Tab tab3 = this.u;
        if (tab3 != null && tab3 != tab && !tab3.f()) {
            boolean z2 = (this.u.c() == null || this.u.c().V() == null) ? false : true;
            if (this.u.isInitialized() && z2) {
                if (!this.u.n() && ((!z || i != 2) && (tab2 = this.u) != null)) {
                    this.s.a(tab2);
                }
                this.u.hide(0);
                Iterator<e23> it = this.f.iterator();
                while (true) {
                    b.a aVar = (b.a) it;
                    if (!aVar.hasNext()) {
                        break;
                    } else {
                        ((e23) aVar.next()).o();
                    }
                }
            }
            this.u = null;
        }
        if (tab == null) {
            E();
            return;
        }
        Tab tab4 = this.u;
        if (tab4 == tab && !tab4.isHidden()) {
            tab.C(3);
            return;
        }
        this.u = tab;
        if (i != 1) {
            tab.l(i, 4);
            tab.getId();
            tab.r();
        }
    }

    @Override // defpackage.a23, defpackage.y13
    public final void c() {
        int i = 0;
        while (true) {
            ArrayList arrayList = this.a;
            if (i >= arrayList.size()) {
                return;
            }
            ((TabModel) arrayList.get(i)).c();
            i++;
        }
    }

    @Override // defpackage.a23, defpackage.y13, defpackage.o13
    public final void d(boolean z) {
        TabModel tabModelZ = z();
        super.d(z);
        TabModel tabModelZ2 = z();
        if (tabModelZ != tabModelZ2) {
            tabModelZ2.q(tabModelZ2.index(), 3, false);
            new Handler().post(new a(tabModelZ2));
        }
    }

    @Override // defpackage.o13
    public final void o(TabModel tabModel) {
        N.MhbdtZVX(this.t.a, tabModel);
    }
}
