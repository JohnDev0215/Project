package defpackage;

import J.N;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.HashSet;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.b;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.customtabs.features.TabInteractionRecorder;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;

/* JADX INFO: compiled from: CustomTabActivityTabController.java */
/* JADX INFO: loaded from: classes.dex */
public final class x20 implements sz0 {
    public final p61<o30> e;
    public final AppCompatActivity f;
    public final CustomTabsConnection g;
    public final oj h;
    public final o23 i;
    public final p61<CompositorViewHolder> j;
    public final zo3 k;
    public final h40 l;
    public final z20 m;
    public final p61<b40> n;
    public final pq3 o;
    public final y30 p;
    public final z2 q;
    public final a30 r;
    public final d70 s;
    public final p61<s30> t;
    public final p61<f9> u;
    public final nw2<Bundle> v;
    public final g3 w;
    public final s13 x;
    public final CustomTabsSessionToken y;
    public final Intent z;

    /* JADX INFO: compiled from: CustomTabActivityTabController.java */
    public class a extends nh0 {
        public a() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void u(Tab tab) {
            if (tab.c() != null) {
                x20 x20Var = x20.this;
                CustomTabsConnection customTabsConnection = x20Var.g;
                CustomTabsSessionToken customTabsSessionToken = x20Var.y;
                tab.c();
                customTabsConnection.getClass();
            }
        }
    }

    public x20(AppCompatActivity appCompatActivity, p61 p61Var, CustomTabsConnection customTabsConnection, oj ojVar, z2 z2Var, o23 o23Var, p61 p61Var2, q2 q2Var, zo3 zo3Var, h40 h40Var, z20 z20Var, p61 p61Var3, pq3 pq3Var, y30 y30Var, a30 a30Var, d70 d70Var, p61 p61Var4, p61 p61Var5, nw2 nw2Var, g3 g3Var, s13 s13Var) {
        this.e = p61Var;
        this.f = appCompatActivity;
        this.g = customTabsConnection;
        this.h = ojVar;
        this.i = o23Var;
        this.j = p61Var2;
        this.k = zo3Var;
        this.l = h40Var;
        this.m = z20Var;
        this.n = p61Var3;
        this.o = pq3Var;
        this.p = y30Var;
        this.q = z2Var;
        this.r = a30Var;
        this.s = d70Var;
        this.t = p61Var4;
        this.u = p61Var5;
        this.v = nw2Var;
        this.w = g3Var;
        this.x = s13Var;
        this.y = ojVar.z();
        this.z = ojVar.t();
        customTabsConnection.a.getClass();
        a30Var.d = null;
        q2Var.f(this);
    }

    public final void a() {
        TabModel tabModelZ = this.m.c().z();
        Tab tab = this.r.b;
        if (e(tab)) {
            return;
        }
        tabModelZ.m(tab, false, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final org.chromium.chrome.browser.tab.TabImpl b() {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.x20.b():org.chromium.chrome.browser.tab.TabImpl");
    }

    public final void c() {
        Tab tabI;
        Intent intent = this.z;
        if (b.g(intent) != null) {
            CustomTabsConnection customTabsConnection = this.g;
            customTabsConnection.getClass();
            Object obj = ThreadUtils.a;
            customTabsConnection.a.getClass();
        }
        z20 z20Var = this.m;
        if (z20Var.k == null) {
            z20Var.k = new k50();
        }
        k50 k50Var = z20Var.k;
        a23 a23Var = k50Var.b;
        oj ojVar = this.h;
        ojVar.getClass();
        TabModel tabModelG = a23Var.g(ojVar instanceof xx0);
        tabModelG.j(this.i);
        a30 a30Var = this.r;
        Tab tab = a30Var.b;
        int i = a30Var.c;
        int i2 = 1;
        if (this.v.get() == null) {
            tabI = null;
        } else {
            k50Var.c(true);
            k50Var.e(true);
            tabI = k50Var.b.i();
            if (tabI != null) {
                d(tabI);
            }
        }
        if (tabI != null) {
            i = 2;
        } else {
            tabI = tab;
        }
        if (tabI == null) {
            tabI = b();
        } else {
            i2 = i;
        }
        if (i2 != 2) {
            tabModelG.i(tabI, 0, tabI.getLaunchType(), 0);
        }
        if (i2 == 4) {
            d33 d33Var = (d33) this.u.get().remove(tabI.getId());
            this.s.getClass();
            bb2 bb2VarB = bb2.b(tabI);
            CompositorViewHolder compositorViewHolder = this.j.get();
            o30 o30Var = this.e.get();
            Runnable runnable = d33Var != null ? d33Var.b : null;
            bb2VarB.getClass();
            compositorViewHolder.v();
            Tab tab2 = bb2VarB.e;
            tab2.E(this.w, o30Var);
            N.MM6uB79X(tab2.c());
            if (runnable != null) {
                runnable.run();
            }
        }
        if (tabI != tab) {
            a30Var.b(i2, tabI);
        }
        this.q.p(new so(20, a30Var));
        Tab tab3 = a30Var.b;
        a23Var.D();
        if (intent.hasExtra("org.chromium.chrome.browser.ServiceTabLauncher.LAUNCH_REQUEST_ID")) {
            N.MMtVSAe3(intent.getIntExtra("org.chromium.chrome.browser.ServiceTabLauncher.LAUNCH_REQUEST_ID", 0), tab3.c());
        }
    }

    public final void d(Tab tab) {
        int iE;
        oj ojVar = this.h;
        boolean zU = ojVar.U();
        Intent intent = this.z;
        if (!zU) {
            ba2.z1(tab, intent);
            tab.getView().requestFocus();
        }
        if (!tab.isIncognito()) {
            a aVar = new a();
            tab.x(aVar);
            aVar.u(tab);
        }
        boolean zI = CustomTabsConnection.e().i("CCTRealTimeEngagementSignals");
        CustomTabsConnection customTabsConnection = this.g;
        CustomTabsSessionToken customTabsSessionToken = this.y;
        o23 o23Var = this.i;
        if (zI) {
            new n92(o23Var, customTabsConnection, customTabsSessionToken);
        }
        p61<b40> p61Var = this.n;
        o23Var.g.add(p61Var.get());
        o23Var.g.add(this.p);
        yu1 yu1Var = new yu1(customTabsConnection, customTabsSessionToken, tab);
        HashSet hashSet = o23Var.f;
        hashSet.add(yu1Var);
        hashSet.add(new an0(p61Var.get(), tab));
        o23Var.b(tab);
        if (t30.h0(intent, customTabsSessionToken) && (iE = ojVar.m().e()) != 0) {
            tab.getView().setBackgroundColor(iE);
            tab.x(new y20(this));
        }
    }

    public final boolean e(Tab tab) {
        boolean z;
        boolean z2;
        if (tab != null) {
            CustomTabsConnection customTabsConnection = this.g;
            if (customTabsConnection.f() != null) {
                dj2 dj2VarF = customTabsConnection.f();
                TabInteractionRecorder tabInteractionRecorder = (TabInteractionRecorder) N.MJqYRCCN(tab);
                if (tabInteractionRecorder != null) {
                    long j = tabInteractionRecorder.a;
                    if (N.Mmu6DTJh(j) || N.MAViEDX6(j)) {
                        ej2 ej2Var = (ej2) dj2VarF;
                        um3 um3Var = ej2Var.a;
                        if (((Tab) um3Var.f) != null) {
                            ll llVar = ej2Var.c;
                            if (llVar != null) {
                                llVar.a();
                                ej2Var.c = null;
                            }
                            ej2Var.a(2);
                        }
                        TabInteractionRecorder tabInteractionRecorder2 = (TabInteractionRecorder) N.MJqYRCCN(tab);
                        bb2.b(tab).a();
                        tabInteractionRecorder2.a();
                        um3Var.f = tab;
                        try {
                            tab.hide(2);
                            z = true;
                        } catch (Exception e) {
                            Log.e("cr_SessionRestore", "Store freeze with exception: " + e);
                            z = false;
                        }
                        if (z) {
                            long j2 = ej2Var.d;
                            if (j2 > 0) {
                                ll llVar2 = new ll();
                                ej2Var.c = llVar2;
                                PostTask.b(rh3.b, llVar2.c(new androidx.appcompat.widget.a(26, ej2Var)), j2);
                            }
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (!z2) {
                            return false;
                        }
                        this.r.a();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override // defpackage.sz0
    public final void q() {
        this.f.A1().x(10);
        if (this.v.get() == null) {
            CustomTabsConnection customTabsConnection = this.g;
            if (customTabsConnection.f.get()) {
                this.x.c1();
                oj ojVar = this.h;
                ojVar.getClass();
                boolean z = ojVar instanceof xx0;
                a30 a30Var = this.r;
                if (z) {
                    a30Var.b(3, b());
                    return;
                }
                ojVar.K();
                b.i(this.z);
                org.chromium.chrome.browser.customtabs.a aVar = customTabsConnection.c;
                aVar.getClass();
                ((Boolean) aVar.a(this.y, Boolean.FALSE, new wd0(6))).booleanValue();
                n3 n3Var = customTabsConnection.a;
                n3Var.getClass();
                TraceEvent traceEventH0 = TraceEvent.H0("CustomTabsConnection.takeHiddenTab", null);
                try {
                    n3Var.getClass();
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                    a30Var.b(3, b());
                } catch (Throwable th) {
                    if (traceEventH0 != null) {
                        try {
                            traceEventH0.close();
                        } catch (Throwable unused) {
                        }
                    }
                    throw th;
                }
            }
        }
    }

    @Override // defpackage.sz0
    public final void Y0() {
    }
}
