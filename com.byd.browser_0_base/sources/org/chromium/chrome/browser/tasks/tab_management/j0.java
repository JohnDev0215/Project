package org.chromium.chrome.browser.tasks.tab_management;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Size;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.C0175b;
import defpackage.ac2;
import defpackage.bk3;
import defpackage.c13;
import defpackage.d13;
import defpackage.e13;
import defpackage.f13;
import defpackage.g03;
import defpackage.g33;
import defpackage.g72;
import defpackage.h03;
import defpackage.i03;
import defpackage.k42;
import defpackage.kh0;
import defpackage.n3;
import defpackage.nh0;
import defpackage.oj2;
import defpackage.om1;
import defpackage.p13;
import defpackage.r03;
import defpackage.t02;
import defpackage.xz;
import defpackage.y13;
import defpackage.z7;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.h0;
import org.chromium.chrome.browser.tasks.tab_management.m;
import org.chromium.chrome.browser.tasks.tab_management.p;
import org.chromium.chrome.browser.tasks.tab_management.s1;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: TabListMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class j0 {
    public static final c13 I = new Comparator() { // from class: c13
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return Long.compare(((g72) obj2).h(), ((g72) obj).h());
        }
    };

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public static final HashMap f79J = new HashMap();
    public static final HashSet K = new HashSet();
    public final m0 D;
    public f E;
    public e F;
    public i0 G;
    public boolean a;
    public boolean b;
    public Tab c;
    public final Context e;
    public final n0 f;
    public final int g;
    public final y13 h;
    public final l0 i;
    public final g72.a j;
    public final j k;
    public final h l;
    public final n m;
    public final h0 n;
    public final s1.c o;
    public final String p;
    public final p q;
    public final boolean r;
    public d13 s;
    public final org.chromium.chrome.browser.tasks.tab_management.p t;
    public final int v;
    public GridLayoutManager w;
    public RecyclerView x;
    public f13 y;
    public final a d = new a();
    public int u = -1;
    public final b z = new b();
    public final c A = new c();
    public final d B = new d();
    public int H = -1;
    public final k0 C = new k0(this);

    /* JADX INFO: compiled from: TabListMediator.java */
    public class a implements i {
        public a() {
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.j0.i
        public final void a(View view) {
            j0 j0Var = j0.this;
            if (j0Var.b) {
                return;
            }
            j0Var.b = true;
            new Handler().postDelayed(new om1(view, 1), 1000L);
        }
    }

    /* JADX INFO: compiled from: TabListMediator.java */
    public class b implements l {
        public b() {
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.j0.l
        public final void a(int i) {
            j0 j0Var = j0.this;
            if (j0Var.f.F(i) == -1) {
                return;
            }
            j0Var.u = i;
            boolean z = j0Var.r;
            y13 y13Var = j0Var.h;
            if (!z) {
                Tab tabI = y13Var.i();
                Tab tabF = z7.F(y13Var.z(), i);
                int iR = y13Var.u().c().r(tabI);
                int iR2 = y13Var.u().c().r(tabF);
                StringBuilder sb = new StringBuilder("MobileTabSwitched.");
                String str = j0Var.p;
                sb.append(str);
                n3.X0(sb.toString());
                if (iR == iR2) {
                    oj2.a0(z7.G(y13Var.z(), tabI.getId()) - z7.G(y13Var.z(), tabF.getId()), C0175b.j("Tabs.TabOffsetOfSwitch.", str));
                }
            }
            h hVar = j0Var.l;
            if (hVar != null) {
                ((s1) hVar).r(i, true);
            } else {
                y13Var.z().q(z7.G(y13Var.z(), i), 3, false);
            }
        }
    }

    /* JADX INFO: compiled from: TabListMediator.java */
    public class c implements l {
        public c() {
        }

        @Override // org.chromium.chrome.browser.tasks.tab_management.j0.l
        public final void a(int i) {
            j0 j0Var = j0.this;
            int iF = j0Var.f.F(i);
            if (iF == -1) {
                return;
            }
            n0 n0Var = j0Var.f;
            PropertyModel propertyModel = n0Var.get(iF).b;
            PropertyModel.n nVar = u0.k;
            boolean zK = propertyModel.k(nVar);
            if (zK) {
                ac2.c(10);
            } else {
                ac2.c(11);
            }
            n0Var.get(iF).b.l(nVar, !zK);
            y13 y13Var = j0Var.h;
            g03 g03Var = (g03) y13Var.u().c();
            Tab tabS = y13Var.s(i);
            if (tabS == null || !g03Var.F(tabS)) {
                return;
            }
            n0Var.get(iF).b.p(u0.g, new o(j0Var.q, i, false, false));
        }
    }

    /* JADX INFO: compiled from: TabListMediator.java */
    public class d extends nh0 {
        public d() {
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void C0(TabImpl tabImpl, NavigationHandle navigationHandle) {
            if (navigationHandle.c || bk3.l(tabImpl.getUrl()) || tabImpl.getUrl().equals(navigationHandle.e)) {
                return;
            }
            j0 j0Var = j0.this;
            if (j0Var.f.F(tabImpl.getId()) == -1) {
                return;
            }
            boolean zC = oj2.C(j0Var.e);
            h0 h0Var = j0Var.n;
            n0 n0Var = j0Var.f;
            if (!zC) {
                n0Var.get(n0Var.F(tabImpl.getId())).b.p(u0.d, h0Var.e(tabImpl.isIncognito()));
                return;
            }
            PropertyModel propertyModel = n0Var.get(n0Var.F(tabImpl.getId())).b;
            PropertyModel.r<h0.d> rVar = u0.f;
            boolean zIsIncognito = tabImpl.isIncognito();
            h0Var.getClass();
            propertyModel.p(rVar, new d0(h0Var, zIsIncognito));
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void X(Tab tab, Bitmap bitmap, GURL gurl) {
            j0.this.o(g72.a(tab), bitmap, gurl);
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void r1(Tab tab) {
            j0 j0Var = j0.this;
            int iF = j0Var.f.F(tab.getId());
            if (iF == -1 || j0Var.h.s(tab.getId()) == null) {
                return;
            }
            j0Var.f.get(iF).b.p(u0.j, j0Var.e(g72.a(tab)));
        }

        @Override // defpackage.nh0, defpackage.n23
        public final void x0(TabImpl tabImpl) {
            j0 j0Var = j0.this;
            if (oj2.C(j0Var.e)) {
                int id = tabImpl.getId();
                n0 n0Var = j0Var.f;
                int iF = n0Var.F(id);
                if (iF == -1 && j0Var.r) {
                    Tab tabA = r03.a(j0Var.h, tabImpl);
                    if (tabA == null) {
                        return;
                    } else {
                        iF = n0Var.F(tabA.getId());
                    }
                }
                if (iF == -1) {
                    return;
                }
                n0Var.get(iF).b.p(u0.v, j0Var.d(tabImpl));
            }
        }
    }

    /* JADX INFO: compiled from: TabListMediator.java */
    public class f extends h03 {
        public f(y13 y13Var) {
            super(y13Var);
        }

        @Override // defpackage.h03
        public final void a(int i) {
            xz.a.getSharedPreferences("tab_group_titles", 0).edit().remove(String.valueOf(i)).apply();
        }

        @Override // defpackage.h03
        public final String b(int i) {
            return i03.a(i);
        }

        @Override // defpackage.h03
        public final void c(int i, String str) {
            xz.a.getSharedPreferences("tab_group_titles", 0).edit().putString(String.valueOf(i), str).apply();
        }

        @Override // defpackage.h03
        public final void d(Tab tab, String str) {
            j0 j0Var = j0.this;
            if (j0Var.r) {
                int iF = j0Var.f.F(r03.a(j0Var.h, tab).getId());
                if (iF == -1) {
                    return;
                }
                j0Var.f.get(iF).b.p(u0.j, str);
                j0Var.n(g72.a(tab), j0Var.f.get(iF).b);
                if (oj2.C(j0Var.e)) {
                    j0Var.m(g72.a(tab), j0Var.f.get(iF).b);
                }
            }
        }
    }

    /* JADX INFO: compiled from: TabListMediator.java */
    public static class g {
        public final Tab a;

        public g(Tab tab) {
            this.a = tab;
        }
    }

    /* JADX INFO: compiled from: TabListMediator.java */
    public interface h {
    }

    /* JADX INFO: compiled from: TabListMediator.java */
    public interface i {
        void a(View view);
    }

    /* JADX INFO: compiled from: TabListMediator.java */
    public interface j {
    }

    /* JADX INFO: compiled from: TabListMediator.java */
    public static class k {
        public final Tab a;
        public final s1.c b;

        public k(Tab tab, s1.c cVar) {
            this.a = tab;
            this.b = cVar;
        }
    }

    /* JADX INFO: compiled from: TabListMediator.java */
    public interface l {
        void a(int i);
    }

    /* JADX INFO: compiled from: TabListMediator.java */
    public interface m {
    }

    /* JADX INFO: compiled from: TabListMediator.java */
    public interface n {
    }

    /* JADX INFO: compiled from: TabListMediator.java */
    public static class o {
        public final p a;
        public final int b;
        public final boolean c;
        public final boolean d;

        public o(p pVar, int i, boolean z, boolean z2) {
            this.a = pVar;
            this.b = i;
            this.c = z;
            this.d = z2;
        }
    }

    /* JADX INFO: compiled from: TabListMediator.java */
    public interface p {
        void l(int i, Size size, t02 t02Var, boolean z, boolean z2, boolean z3);
    }

    public j0(Context context, n0 n0Var, int i2, y13 y13Var, p pVar, g72.a aVar, h0 h0Var, boolean z, g33 g33Var, s1 s1Var, m.c cVar, s1.c cVar2, String str, int i3, p.a aVar2) {
        this.e = context;
        this.h = y13Var;
        this.q = pVar;
        this.f = n0Var;
        this.g = i2;
        this.n = h0Var;
        this.p = str;
        this.j = aVar;
        this.k = g33Var;
        this.l = s1Var;
        this.m = cVar;
        this.r = z;
        this.v = i3;
        this.o = cVar2;
        l0 l0Var = new l0(this);
        this.i = l0Var;
        this.t = new org.chromium.chrome.browser.tasks.tab_management.p(context, n0Var, y13Var, l0Var, cVar, str, i2, aVar2);
        if (i2 == 0 && i3 != 0 && z7.R()) {
            m0 m0Var = new m0(this);
            this.D = m0Var;
            n0Var.g(m0Var);
        }
    }

    public static int a(j0 j0Var, Tab tab) {
        j0Var.getClass();
        int id = tab.getId();
        p13 p13VarC = j0Var.h.u().c();
        List<Integer> arrayList = p13VarC == null ? new ArrayList<>() : p13VarC.A(id);
        if (!arrayList.isEmpty()) {
            int i2 = 0;
            while (true) {
                n0 n0Var = j0Var.f;
                if (i2 >= n0Var.size()) {
                    break;
                }
                if (arrayList.contains(Integer.valueOf(n0Var.get(i2).b.h(u0.a)))) {
                    return i2;
                }
                i2++;
            }
        }
        return -1;
    }

    public static String c(Tab tab) {
        if (!tab.isInitialized()) {
            return "";
        }
        String strB = bk3.b(tab.getUrl().j(), false);
        return strB.isEmpty() ? tab.getUrl().j() : strB;
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(defpackage.g72 r17, int r18, boolean r19) {
        /*
            Method dump skipped, instruction units count: 543
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tasks.tab_management.j0.b(g72, int, boolean):void");
    }

    public final String d(Tab tab) {
        if (!oj2.C(this.e)) {
            return "";
        }
        if (!this.r) {
            return c(tab);
        }
        List<Tab> listF = f(tab.getId());
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < listF.size(); i2++) {
            arrayList.add(c(listF.get(i2)));
        }
        return TextUtils.join(", ", arrayList);
    }

    public final String e(g72 g72Var) {
        g72.a aVar = this.j;
        String strE = aVar != null ? aVar.e(this.e, g72Var) : g72Var.i();
        if (!this.r || this.E == null || f(g72Var.c()).size() <= 1) {
            return strE;
        }
        f fVar = this.E;
        int iF = g72Var.f();
        fVar.getClass();
        String strA = i03.a(iF);
        return strA == null ? strE : strA;
    }

    public final List<Tab> f(int i2) {
        p13 p13VarC = this.h.u().c();
        return p13VarC == null ? new ArrayList() : p13VarC.B(i2);
    }

    public final void g() {
        y13 y13Var = this.h;
        this.n.f(y13Var.g(false).f());
        y13Var.u().a(this.C);
        if (y13Var.u().c() instanceof g03) {
            this.F = new e();
            ((g03) y13Var.u().d(false)).K(this.F);
            ((g03) y13Var.u().d(true)).K(this.F);
        }
        if (oj2.C(this.e)) {
            this.E = new f(y13Var);
        }
    }

    public final boolean h(g72 g72Var, int i2) {
        j jVar = this.k;
        org.chromium.components.browser_ui.widget.selectable_list.a<Integer> aVar = jVar == null ? null : ((g33) jVar).a.d;
        return aVar == null ? g72Var.c() == i2 : aVar.d(Integer.valueOf(g72Var.c()));
    }

    public final boolean i(int i2) {
        return i2 != -1 && i2 < this.f.size();
    }

    /* JADX WARN: Removed duplicated region for block: B:4:0x0006  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int j(org.chromium.chrome.browser.tab.Tab r6, boolean r7) {
        /*
            r5 = this;
            r0 = 0
            y13 r1 = r5.h
            r2 = -1
            if (r6 != 0) goto L8
        L6:
            r7 = r2
            goto L40
        L8:
            org.chromium.chrome.browser.tasks.tab_management.n0 r3 = r5.f
            if (r7 == 0) goto L2c
            int r7 = r3.size()
            if (r7 != 0) goto L13
            goto L6
        L13:
            java.lang.Object r7 = r3.get(r0)
            xa1 r7 = (defpackage.xa1) r7
            org.chromium.ui.modelutil.PropertyModel r7 = r7.b
            org.chromium.ui.modelutil.PropertyModel$p r3 = org.chromium.chrome.browser.tasks.tab_management.u0.a
            int r7 = r7.h(r3)
            java.util.List r7 = r5.f(r7)
            int r7 = r7.indexOf(r6)
            if (r7 != r2) goto L40
            goto L6
        L2c:
            q13 r7 = r1.u()
            p13 r7 = r7.c()
            int r4 = r6.getId()
            int r7 = defpackage.z7.G(r7, r4)
            int r7 = r3.G(r7)
        L40:
            if (r7 != r2) goto L43
            return r7
        L43:
            g72 r2 = defpackage.g72.a(r6)
            org.chromium.chrome.browser.tab.Tab r1 = r1.i()
            if (r1 != r6) goto L4e
            r0 = 1
        L4e:
            r5.b(r2, r7, r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tasks.tab_management.j0.j(org.chromium.chrome.browser.tab.Tab, boolean):int");
    }

    public final void k(int i2, int i3) {
        p pVar = this.q;
        n0 n0Var = this.f;
        if (i2 != -1) {
            int iH = n0Var.get(i2).b.h(u0.a);
            n0Var.get(i2).b.l(u0.k, false);
            if (this.r && pVar != null && this.a) {
                n0Var.get(i2).b.p(u0.g, new o(pVar, iH, true, false));
            }
        }
        if (i3 != -1) {
            int iH2 = n0Var.get(i3).b.h(u0.a);
            n0Var.get(i3).b.l(u0.k, true);
            if (pVar == null || !this.a) {
                return;
            }
            n0Var.get(i3).b.p(u0.g, new o(pVar, iH2, true, false));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l(defpackage.g72 r10, int r11) {
        /*
            r9 = this;
            int r0 = r9.g
            r1 = 0
            org.chromium.chrome.browser.tasks.tab_management.n0 r2 = r9.f
            if (r0 != 0) goto L98
            org.chromium.chrome.browser.tab.Tab r0 = r10.g()
            r3 = 1
            r4 = 0
            if (r0 == 0) goto L11
            r0 = r3
            goto L12
        L11:
            r0 = r4
        L12:
            if (r0 == 0) goto L98
            boolean r0 = r10.k()
            if (r0 != 0) goto L98
            boolean r0 = defpackage.k42.c()
            if (r0 == 0) goto L4c
            int r0 = r10.c()
            java.util.List r0 = r9.f(r0)
            int r0 = r0.size()
            if (r0 != r3) goto L30
            r0 = r3
            goto L31
        L30:
            r0 = r4
        L31:
            if (r0 == 0) goto L4c
            java.lang.Object r0 = r2.get(r11)
            xa1 r0 = (defpackage.xa1) r0
            org.chromium.ui.modelutil.PropertyModel r0 = r0.b
            org.chromium.ui.modelutil.PropertyModel$r<org.chromium.chrome.browser.tasks.tab_management.j0$k> r5 = org.chromium.chrome.browser.tasks.tab_management.u0.x
            org.chromium.chrome.browser.tasks.tab_management.j0$k r6 = new org.chromium.chrome.browser.tasks.tab_management.j0$k
            org.chromium.chrome.browser.tab.Tab r7 = r10.g()
            org.chromium.chrome.browser.tasks.tab_management.s1$c r8 = r9.o
            r6.<init>(r7, r8)
            r0.p(r5, r6)
            goto L59
        L4c:
            java.lang.Object r0 = r2.get(r11)
            xa1 r0 = (defpackage.xa1) r0
            org.chromium.ui.modelutil.PropertyModel r0 = r0.b
            org.chromium.ui.modelutil.PropertyModel$r<org.chromium.chrome.browser.tasks.tab_management.j0$k> r5 = org.chromium.chrome.browser.tasks.tab_management.u0.x
            r0.p(r5, r1)
        L59:
            sk r0 = defpackage.bq.u
            boolean r0 = r0.a()
            if (r0 == 0) goto L8a
            int r0 = r10.c()
            java.util.List r9 = r9.f(r0)
            int r9 = r9.size()
            if (r9 != r3) goto L70
            goto L71
        L70:
            r3 = r4
        L71:
            if (r3 == 0) goto L8a
            java.lang.Object r9 = r2.get(r11)
            xa1 r9 = (defpackage.xa1) r9
            org.chromium.ui.modelutil.PropertyModel r9 = r9.b
            org.chromium.ui.modelutil.PropertyModel$r<org.chromium.chrome.browser.tasks.tab_management.j0$g> r11 = org.chromium.chrome.browser.tasks.tab_management.u0.y
            org.chromium.chrome.browser.tasks.tab_management.j0$g r0 = new org.chromium.chrome.browser.tasks.tab_management.j0$g
            org.chromium.chrome.browser.tab.Tab r10 = r10.g()
            r0.<init>(r10)
            r9.p(r11, r0)
            goto Lb2
        L8a:
            java.lang.Object r9 = r2.get(r11)
            xa1 r9 = (defpackage.xa1) r9
            org.chromium.ui.modelutil.PropertyModel r9 = r9.b
            org.chromium.ui.modelutil.PropertyModel$r<org.chromium.chrome.browser.tasks.tab_management.j0$g> r10 = org.chromium.chrome.browser.tasks.tab_management.u0.y
            r9.p(r10, r1)
            goto Lb2
        L98:
            java.lang.Object r9 = r2.get(r11)
            xa1 r9 = (defpackage.xa1) r9
            org.chromium.ui.modelutil.PropertyModel r9 = r9.b
            org.chromium.ui.modelutil.PropertyModel$r<org.chromium.chrome.browser.tasks.tab_management.j0$g> r10 = org.chromium.chrome.browser.tasks.tab_management.u0.y
            r9.p(r10, r1)
            java.lang.Object r9 = r2.get(r11)
            xa1 r9 = (defpackage.xa1) r9
            org.chromium.ui.modelutil.PropertyModel r9 = r9.b
            org.chromium.ui.modelutil.PropertyModel$r<org.chromium.chrome.browser.tasks.tab_management.j0$k> r10 = org.chromium.chrome.browser.tasks.tab_management.u0.x
            r9.p(r10, r1)
        Lb2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tasks.tab_management.j0.l(g72, int):void");
    }

    public final void m(g72 g72Var, PropertyModel propertyModel) {
        int size;
        Context context = this.e;
        if (oj2.C(context)) {
            if (!this.r || (size = f(g72Var.c()).size()) <= 1) {
                propertyModel.p(u0.A, context.getString(R$string.accessibility_tabstrip_btn_close_tab, g72Var.i()));
                return;
            }
            String strE = e(g72Var);
            g72.a aVar = this.j;
            if (strE.equals(aVar != null ? aVar.e(context, g72Var) : g72Var.i())) {
                strE = "";
            }
            if (strE.isEmpty()) {
                propertyModel.p(u0.A, context.getString(R$string.accessibility_close_tab_group_button, String.valueOf(size)));
            } else {
                propertyModel.p(u0.A, context.getString(R$string.accessibility_close_tab_group_button_with_group_name, strE, String.valueOf(size)));
            }
        }
    }

    public final void n(g72 g72Var, PropertyModel propertyModel) {
        if (this.r) {
            int size = f(g72Var.c()).size();
            if (size <= 1) {
                propertyModel.p(u0.z, null);
                return;
            }
            String strE = e(g72Var);
            g72.a aVar = this.j;
            Context context = this.e;
            if (strE.equals(aVar != null ? aVar.e(context, g72Var) : g72Var.i())) {
                strE = "";
            }
            propertyModel.p(u0.z, strE.isEmpty() ? context.getString(R$string.accessibility_expand_tab_group, String.valueOf(size)) : context.getString(R$string.accessibility_expand_tab_group_with_group_name, strE, String.valueOf(size)));
        }
    }

    public final void o(g72 g72Var, Bitmap bitmap, GURL gurl) {
        int iC = g72Var.c();
        n0 n0Var = this.f;
        int iF = n0Var.F(iC);
        if (iF == -1) {
            return;
        }
        List<Tab> listF = f(g72Var.c());
        q0 q0Var = new q0(this, 2, g72Var);
        boolean z = this.r;
        Context context = this.e;
        h0 h0Var = this.n;
        if (z && listF.size() > 1) {
            if (!oj2.C(context)) {
                n0Var.get(iF).b.p(u0.d, null);
                n0Var.get(iF).b.p(u0.f, null);
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(g72Var.j());
            for (int i2 = 0; arrayList.size() < 4 && i2 < listF.size(); i2++) {
                if (g72Var.c() != listF.get(i2).getId()) {
                    arrayList.add(listF.get(i2).getUrl());
                }
            }
            PropertyModel propertyModel = n0Var.get(iF).b;
            PropertyModel.r<h0.d> rVar = u0.f;
            boolean zK = g72Var.k();
            h0Var.getClass();
            propertyModel.p(rVar, new g0(h0Var, arrayList, zK));
            return;
        }
        if (h0Var.i) {
            if (bitmap == null || gurl == null) {
                if (!oj2.C(context)) {
                    h0Var.c(g72Var.j(), g72Var.k(), q0Var);
                    return;
                } else {
                    n0Var.get(iF).b.p(u0.f, new e0(h0Var, g72Var.j(), g72Var.k()));
                    return;
                }
            }
            boolean zC = oj2.C(context);
            boolean z2 = h0Var.g;
            if (zC) {
                n0Var.get(iF).b.p(u0.f, new f0(h0Var.g(bitmap, z2), gurl));
                return;
            }
            Drawable drawableG = h0Var.g(bitmap, z2);
            n0Var.get(iF).b.p(u0.d, new h0.e(drawableG, drawableG, false, gurl));
        }
    }

    public final void p() {
        n0 n0Var;
        if (k42.b()) {
            GridLayoutManager gridLayoutManager = this.w;
            int i2 = gridLayoutManager.F;
            GridLayoutManager.b bVar = gridLayoutManager.K;
            int i3 = 0;
            int iC = 0;
            while (true) {
                n0Var = this.f;
                if (i3 >= n0Var.size()) {
                    break;
                }
                iC += bVar.c(i3);
                if (iC == i2) {
                    iC = 0;
                } else if (iC <= i2) {
                    continue;
                } else {
                    if (n0Var.get(i3).a == 6) {
                        break;
                    }
                    iC = 0;
                }
                i3++;
            }
            if (iC <= i2) {
                return;
            }
            int iC2 = i2 - (iC - bVar.c(i3));
            for (int i4 = i3 + 1; i4 < n0Var.size(); i4++) {
                if (bVar.c(i4) <= iC2) {
                    n0Var.w(i4, i3);
                    return;
                }
            }
        }
    }

    public final void q(GridLayoutManager gridLayoutManager) {
        Context context = this.e;
        if (DeviceFormFactor.a(context)) {
            oj2.z(context);
        }
        gridLayoutManager.s1(2);
        gridLayoutManager.K = new e13(this, gridLayoutManager);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void r(int r16, defpackage.g72 r17, boolean r18, boolean r19, boolean r20) {
        /*
            Method dump skipped, instruction units count: 358
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.tasks.tab_management.j0.r(int, g72, boolean, boolean, boolean):void");
    }

    /* JADX INFO: compiled from: TabListMediator.java */
    public class e extends kh0 {
        public e() {
        }

        @Override // defpackage.kh0, g03.a
        public final void c(int i, Tab tab) {
            j0 j0Var = j0.this;
            if (j0Var.a) {
                y13 y13Var = j0Var.h;
                g03 g03Var = (g03) y13Var.u().c();
                Tab tabAt = g03Var.getTabAt(i);
                boolean z = tabAt.getId() == tab.getId();
                boolean z2 = j0Var.r;
                n0 n0Var = j0Var.f;
                if (!z2) {
                    int iF = n0Var.F(tab.getId());
                    if (j0Var.i(iF)) {
                        n0Var.y(iF);
                        n nVar = j0Var.m;
                        if (nVar != null) {
                            int id = z ? -1 : g03Var.getTabAt(i).getId();
                            org.chromium.chrome.browser.tasks.tab_management.m mVar = org.chromium.chrome.browser.tasks.tab_management.m.this;
                            mVar.v = id;
                            mVar.l();
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (z) {
                    return;
                }
                Tab tabI = y13Var.i();
                if (!o1.v(j0Var.g)) {
                    if (!g03Var.F(tab)) {
                        j0Var.b(g72.a(tab), n0Var.G(g03Var.r(tab)), tabI.getId() == tab.getId());
                    }
                    j0Var.r(j0Var.f.G(i), g72.a(tabAt), y13Var.k() == tabAt.getId(), true, false);
                    return;
                }
                int iF2 = n0Var.F(tabAt.getId());
                if (iF2 == -1) {
                    iF2 = n0Var.F(tab.getId());
                }
                if (j0Var.i(iF2)) {
                    boolean z3 = tabI.getId() == tabAt.getId();
                    int iC = n0Var.C(tabAt.getId());
                    j0.this.r(iF2, g72.a(tabAt), z3, true, false);
                    if (iF2 != iC) {
                        if (iF2 < iC) {
                            iC--;
                        }
                        n0Var.w(iF2, iC);
                    }
                    j0Var.b(g72.a(tab), n0Var.C(tab.getId()), tabI.getId() == tab.getId());
                }
            }
        }

        @Override // defpackage.kh0, g03.a
        public final void d(Tab tab) {
            int iF;
            j0 j0Var = j0.this;
            if (j0Var.a && j0Var.r) {
                List<Tab> listF = j0Var.f(tab.getId());
                y13 y13Var = j0Var.h;
                TabModel tabModelZ = y13Var.z();
                n0 n0Var = j0Var.f;
                n0Var.getClass();
                int iR = tabModelZ.r(listF.get(0));
                int iR2 = tabModelZ.r(listF.get(listF.size() - 1));
                int i = -1;
                while (true) {
                    if (iR > iR2) {
                        iF = -1;
                        break;
                    }
                    iF = n0Var.F(tabModelZ.getTabAt(iR).getId());
                    if (iF != -1 && i == -1) {
                        i = iF;
                    } else if (iF != -1) {
                        break;
                    }
                    iR++;
                }
                Pair pair = new Pair(Integer.valueOf(i), Integer.valueOf(iF));
                int iIntValue = ((Integer) pair.second).intValue();
                int iIntValue2 = ((Integer) pair.first).intValue();
                if (iIntValue2 != -1 && iIntValue == -1) {
                    Iterator<Tab> it = listF.iterator();
                    boolean z = false;
                    while (it.hasNext()) {
                        z |= it.next() == y13Var.i();
                    }
                    j0Var.r(iIntValue2, g72.a(j0Var.h.s(j0Var.f.get(iIntValue2).b.h(u0.a))), z, false, false);
                    return;
                }
                if (j0Var.i(iIntValue) && j0Var.i(iIntValue2)) {
                    boolean zV = o1.v(j0Var.g);
                    Tab tabAt = zV ? y13Var.u().c().getTabAt(y13Var.u().c().r(y13Var.s(n0Var.get(iIntValue2).b.h(u0.a)))) : null;
                    n0Var.y(iIntValue);
                    if (j0Var.f(tab.getId()).size() == 2) {
                        n3.X0("TabGroup.Created.DropToMerge");
                    } else {
                        n3.X0("TabGrid.Drag.DropToMerge");
                    }
                    if (iIntValue <= iIntValue2) {
                        iIntValue2 = n0Var.E(iIntValue2);
                    }
                    if (!zV) {
                        tabAt = y13Var.u().c().getTabAt(n0Var.D(iIntValue2));
                    }
                    boolean z2 = y13Var.i() == tabAt;
                    j0.this.r(iIntValue2, g72.a(tabAt), z2, true, false);
                    if (z2 && zV && iIntValue2 != 0) {
                        n0Var.w(iIntValue2, 0);
                    }
                }
            }
        }

        @Override // defpackage.kh0, g03.a
        public final void f(int i, int i2, Tab tab) {
            j0 j0Var = j0.this;
            if (j0Var.a && j0Var.r && i2 != i) {
                y13 y13Var = j0Var.h;
                g03 g03Var = (g03) y13Var.u().c();
                List<Tab> listF = j0Var.f(tab.getId());
                Tab tabA = r03.a(y13Var, tab);
                TabModel tabModelZ = y13Var.z();
                int id = tabA.getId();
                n0 n0Var = j0Var.f;
                int iF = n0Var.F(id);
                if (iF == -1) {
                    n0Var.L(n0Var.G(g03Var.r(tabModelZ.getTabAt(i))), tabA);
                    iF = n0Var.F(tabA.getId());
                }
                if (j0Var.i(iF)) {
                    Tab tabAt = tabModelZ.getTabAt(i2 > i ? i2 - listF.size() : i2 + 1);
                    Tab tabA2 = r03.a(y13Var, tabAt);
                    int iF2 = n0Var.F(tabA2.getId());
                    if (iF2 == -1) {
                        n0Var.L(n0Var.G(g03Var.r(tabAt) + (i2 > i ? 1 : -1)), tabA2);
                        iF2 = n0Var.F(tabA2.getId());
                    }
                    if (j0Var.i(iF2)) {
                        n0Var.w(iF, iF2);
                    }
                }
            }
        }

        @Override // defpackage.kh0, g03.a
        public final void g(int i, int i2, Tab tab) {
            p pVar;
            j0 j0Var = j0.this;
            if (!j0Var.a || i2 == i) {
                return;
            }
            y13 y13Var = j0Var.h;
            TabModel tabModelZ = y13Var.z();
            n0 n0Var = j0Var.f;
            if (j0Var.r && (pVar = j0Var.q) != null && !o1.v(j0Var.g)) {
                int iA = j0.a(j0Var, tab);
                if (iA == -1) {
                    return;
                }
                p13 p13VarC = y13Var.u().c();
                n0Var.get(iA).b.p(u0.g, new o(pVar, p13VarC.getTabAt(p13VarC.r(tab)).getId(), true, false));
                return;
            }
            int iF = n0Var.F(tab.getId());
            if (j0Var.i(iF)) {
                int iF2 = n0Var.F(tabModelZ.getTabAt(i2 > i ? i2 - 1 : i2 + 1).getId());
                if (j0Var.i(iF2)) {
                    n0Var.w(iF, iF2);
                }
            }
        }

        @Override // defpackage.kh0, g03.a
        public final void e(List list, ArrayList arrayList, ArrayList arrayList2) {
        }
    }
}
