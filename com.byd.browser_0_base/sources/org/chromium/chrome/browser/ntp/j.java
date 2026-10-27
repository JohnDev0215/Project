package org.chromium.chrome.browser.ntp;

import J.N;
import android.app.Activity;
import defpackage.a8;
import defpackage.a9;
import defpackage.gj2;
import defpackage.h62;
import defpackage.n3;
import defpackage.oj2;
import defpackage.pq2;
import defpackage.ql0;
import defpackage.s92;
import defpackage.t92;
import defpackage.u92;
import defpackage.v92;
import defpackage.wb2;
import defpackage.xx2;
import defpackage.y13;
import defpackage.z1;
import defpackage.z7;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.browser.ntp.ForeignSessionHelper;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.signin.services.SigninManager;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.ui.favicon.FaviconHelper;
import org.chromium.components.signin.AccountManagerFacadeProvider;

/* JADX INFO: compiled from: RecentTabsManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j implements SigninManager.b, h62.b, z1 {
    public final Profile e;
    public final Tab f;
    public final y13 g;
    public final Runnable h;
    public TabModel i;
    public ForeignSessionHelper l;
    public List<ForeignSessionHelper.ForeignSession> m;
    public ArrayList n;
    public l o;
    public RecentlyClosedBridge p;
    public SigninManager q;
    public a r;
    public boolean s;
    public final h62 t;
    public final xx2 u;
    public int j = 0;
    public final HashMap v = new HashMap();
    public final HashMap w = new HashMap();
    public final HashMap x = new HashMap();
    public FaviconHelper k = new FaviconHelper();

    /* JADX INFO: compiled from: RecentTabsManager.java */
    public interface a {
    }

    public j(Tab tab, y13 y13Var, Profile profile, Activity activity, a9 a9Var) {
        this.e = profile;
        this.f = tab;
        this.g = y13Var;
        this.h = a9Var;
        this.l = new ForeignSessionHelper(profile);
        this.o = new l(profile);
        this.p = new RecentlyClosedBridge(profile, y13Var);
        z7.m().getClass();
        this.q = z7.D(profile);
        h62 h62VarC = h62.c(activity);
        this.t = h62VarC;
        this.u = new xx2(16, a8.y());
        this.p.c = new ql0(17, this);
        h();
        ForeignSessionHelper foreignSessionHelper = this.l;
        N.MiRrgRsD(foreignSessionHelper.a, new pq2(22, this));
        f();
        N.Mwf$8a3o(this.l.a);
        this.q.i(this);
        h62VarC.b(this);
        AccountManagerFacadeProvider.getInstance().a(this);
        g();
        Object obj = ThreadUtils.a;
        if (gj2.i == null) {
            gj2.i = new gj2(profile, new wb2());
        }
        gj2 gj2Var = gj2.i;
        int i = gj2Var.h + 1;
        gj2Var.h = i;
        if (i == 1) {
            gj2Var.a(20000L, true);
        }
    }

    public static void d(Map map, String str) {
        int size = map.size();
        oj2.P(size, "Tabs.RecentlyClosed.EntriesShownInPage.".concat(str));
        if (size > 0) {
            Iterator it = map.values().iterator();
            int i = 0;
            while (it.hasNext()) {
                i += ((Boolean) it.next()).booleanValue() ? 1 : 0;
            }
            oj2.P(i, "Tabs.RecentlyClosed.EntriesRestoredInPage.".concat(str));
            oj2.Z(Math.round((i * 100.0f) / size), "Tabs.RecentlyClosed.PercentOfEntriesRestoredInPage.".concat(str));
        }
    }

    @Override // defpackage.z1
    public final void F() {
        e();
    }

    public final void a(ForeignSessionHelper.ForeignSession foreignSession, ForeignSessionHelper.a aVar, int i) {
        if (this.s) {
            return;
        }
        n3.X0("MobileRecentTabManagerTabFromOtherDeviceOpened");
        ForeignSessionHelper foreignSessionHelper = this.l;
        N.MRO3CB0d(foreignSessionHelper.a, this.f, foreignSession.a, aVar.c, i);
    }

    public final void b(v92 v92Var, int i) {
        if (this.s) {
            return;
        }
        this.v.put(Integer.valueOf(v92Var.a), Boolean.TRUE);
        n3.X0("MobileRecentTabManagerRecentTabOpened");
        RecentlyClosedBridge recentlyClosedBridge = this.p;
        TabModel tabModelJ = this.i;
        if (tabModelJ == null) {
            tabModelJ = this.g.j(this.f.getId());
            this.i = tabModelJ;
        }
        N.MvBdqLcK(recentlyClosedBridge.a, tabModelJ, v92Var.a, i);
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.b
    public final void c() {
        e();
    }

    public final void e() {
        g();
        if (this.s) {
            return;
        }
        f();
        a aVar = this.r;
        if (aVar != null) {
            ((k) aVar).b();
        }
    }

    public final void f() {
        List<ForeignSessionHelper.ForeignSession> listEmptyList;
        ForeignSessionHelper foreignSessionHelper = this.l;
        if (N.MWH8i2u_(foreignSessionHelper.a)) {
            ArrayList arrayList = new ArrayList();
            listEmptyList = !N.MrEqlTFQ(foreignSessionHelper.a, arrayList) ? Collections.emptyList() : arrayList;
        } else {
            listEmptyList = Collections.emptyList();
        }
        this.m = listEmptyList;
        a aVar = this.r;
        if (aVar != null) {
            ((k) aVar).b();
        }
    }

    public final void g() {
        int i;
        boolean z = true;
        if (this.q.l().b(1)) {
            i = 3;
        } else {
            this.q.j();
            i = 0;
        }
        int i2 = this.j;
        if (i == i2) {
            return;
        }
        if ((i2 != 0 && i2 != 3) || (i != 2 && i != 1)) {
            z = false;
        }
        if (z) {
            this.u.d();
        }
        this.j = i;
    }

    public final void h() {
        RecentlyClosedBridge recentlyClosedBridge = this.p;
        recentlyClosedBridge.getClass();
        ArrayList<t92> arrayList = new ArrayList();
        if (!N.Mihu$Iyc(recentlyClosedBridge.a, arrayList, 5)) {
            arrayList = null;
        }
        this.n = arrayList;
        for (t92 t92Var : arrayList) {
            if (t92Var instanceof v92) {
                Integer numValueOf = Integer.valueOf(t92Var.a);
                HashMap map = this.v;
                if (!map.containsKey(numValueOf)) {
                    map.put(Integer.valueOf(t92Var.a), Boolean.FALSE);
                }
            }
            if (t92Var instanceof u92) {
                Integer numValueOf2 = Integer.valueOf(t92Var.a);
                HashMap map2 = this.w;
                if (!map2.containsKey(numValueOf2)) {
                    map2.put(Integer.valueOf(t92Var.a), Boolean.FALSE);
                }
            }
            if (t92Var instanceof s92) {
                Integer numValueOf3 = Integer.valueOf(t92Var.a);
                HashMap map3 = this.x;
                if (!map3.containsKey(numValueOf3)) {
                    map3.put(Integer.valueOf(t92Var.a), Boolean.FALSE);
                }
            }
        }
        a aVar = this.r;
        if (aVar != null) {
            ((k) aVar).b();
        }
    }

    @Override // org.chromium.chrome.browser.signin.services.SigninManager.b
    public final void j() {
        e();
    }

    @Override // h62.b
    public final void n(String str) {
        e();
    }
}
