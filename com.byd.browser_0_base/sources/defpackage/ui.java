package defpackage;

import defpackage.l23;
import java.util.LinkedList;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.browser_ui.bottomsheet.f;
import org.chromium.components.browser_ui.bottomsheet.j;
import org.chromium.content.browser.selection.SelectionPopupControllerImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: BottomSheetManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ui extends bh0 implements v80 {
    public int A;
    public int B;
    public final zp1<Boolean> e;
    public final ri f;
    public final qi g;
    public final pi h;
    public final ll i;
    public final qs1<lr2> j;
    public si k;
    public final boolean l;
    public final qs1<l61> m;
    public ti n;
    public final mj o;
    public int p = -1;
    public int q = -1;
    public final j r;
    public final z2 s;
    public final nw2<bi1> t;
    public final nw2<wp2> u;
    public final l23 v;
    public l23.b w;
    public final nw2<nt1> x;
    public Tab y;
    public boolean z;

    public ui(j jVar, z2 z2Var, fj fjVar, zp1 zp1Var, fp fpVar, l23 l23Var, aq1 aq1Var, fp fpVar2, qs1 qs1Var, qs1 qs1Var2, boolean z) {
        this.r = jVar;
        this.s = z2Var;
        this.o = fjVar;
        this.t = zp1Var;
        this.u = fpVar;
        this.v = l23Var;
        this.e = aq1Var;
        this.x = fpVar2;
        ll llVar = new ll();
        this.i = llVar;
        this.l = z;
        this.j = qs1Var;
        if (!z) {
            qs1Var.h(llVar.b(new se3(1, this)));
        }
        this.m = qs1Var2;
        qs1Var2.h(llVar.b(new jb1(20, this)));
        f fVar = (f) jVar;
        fVar.g(this);
        fVar.p = qo.f();
        this.h = new pi(this, jVar);
        z2Var.p(new dr(23, this));
        g((Tab) z2Var.f);
        qi qiVar = new qi(this, jVar);
        this.g = qiVar;
        fjVar.o(qiVar);
        ri riVar = new ri(jVar);
        this.f = riVar;
        aq1Var.p(riVar);
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void a(float f) {
        nw2<wp2> nw2Var = this.u;
        if (nw2Var.get() == null) {
            return;
        }
        wp2 wp2Var = nw2Var.get();
        up2 up2Var = wp2Var.h;
        boolean zIsEmpty = up2Var.a.isEmpty();
        LinkedList linkedList = up2Var.b;
        if (zIsEmpty && linkedList.isEmpty()) {
            return;
        }
        while (true) {
            if (up2Var.a.isEmpty() && linkedList.isEmpty()) {
                wp2Var.e();
                return;
            }
            up2Var.b(false);
        }
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void c(int i) {
        mj mjVar = this.o;
        if (mjVar.i() != null) {
            mjVar.i().u(this.A);
        }
        ((f) this.r).h();
        if (this.z) {
            this.z = false;
            return;
        }
        this.v.f(this.w);
        this.w = null;
        nw2<bi1> nw2Var = this.t;
        if (nw2Var.get() != null && (this.p != -1 || this.q != -1)) {
            nw2Var.get().g(1, this.p);
            nw2Var.get().g(0, this.q);
        }
        this.p = -1;
        this.q = -1;
    }

    @Override // defpackage.bh0, defpackage.vi
    public final void e() {
        WebContents webContentsC;
        mj mjVar = this.o;
        if (mjVar.i() != null) {
            this.A = mjVar.i().v();
        }
        Tab tab = (Tab) this.s.f;
        if (tab != null && (webContentsC = tab.c()) != null) {
            SelectionPopupControllerImpl.j(webContentsC).d();
        }
        nw2<nt1> nw2Var = this.x;
        if (nw2Var.f() && nw2Var.get().c != null) {
            nw2Var.get().c.I(0, true);
        }
        ii iiVarH = ((f) this.r).h();
        if (iiVarH != null && iiVarH.f()) {
            this.z = true;
            return;
        }
        this.w = this.v.d(2);
        nw2<bi1> nw2Var2 = this.t;
        if (nw2Var2.get() != null) {
            this.p = nw2Var2.get().l(1);
            this.q = nw2Var2.get().l(0);
        }
    }

    public final void g(Tab tab) {
        Tab tab2;
        qs1<lr2> qs1Var = this.j;
        Integer numValueOf = qs1Var.get() == null ? null : Integer.valueOf(qs1Var.get().t());
        qs1<l61> qs1Var2 = this.m;
        i(tab, numValueOf, qs1Var2.get() != null ? Integer.valueOf(qs1Var2.get().d()) : null);
        if (tab == null || (tab2 = this.y) == tab) {
            return;
        }
        pi piVar = this.h;
        if (tab2 != null) {
            tab2.t(piVar);
        }
        this.y = tab;
        tab.x(piVar);
        ((f) this.r).p();
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0047  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i(org.chromium.chrome.browser.tab.Tab r5, java.lang.Integer r6, java.lang.Integer r7) {
        /*
            r4 = this;
            qs1<lr2> r0 = r4.j
            java.lang.Object r0 = r0.get()
            lr2 r0 = (defpackage.lr2) r0
            r1 = 0
            boolean r2 = r4.l
            r3 = 1
            if (r2 == 0) goto L26
            if (r7 != 0) goto L13
            if (r5 != 0) goto L4d
            goto L4e
        L13:
            int r6 = r7.intValue()
            r2 = 16
            if (r6 != r2) goto L1c
            goto L4d
        L1c:
            int r6 = r7.intValue()
            r7 = 2
            if (r6 != r7) goto L47
            if (r0 == 0) goto L4d
            goto L4e
        L26:
            if (r0 == 0) goto L4a
            if (r6 != 0) goto L2b
            goto L4a
        L2b:
            int r7 = r6.intValue()
            r0 = 6
            if (r7 == r0) goto L4d
            int r7 = r6.intValue()
            if (r7 != r3) goto L39
            goto L4d
        L39:
            int r7 = r6.intValue()
            if (r7 == 0) goto L47
            int r6 = r6.intValue()
            r7 = 3
            if (r6 == r7) goto L47
            goto L4e
        L47:
            if (r5 != 0) goto L4d
            goto L4e
        L4a:
            if (r5 != 0) goto L4d
            goto L4e
        L4d:
            r3 = r1
        L4e:
            org.chromium.components.browser_ui.bottomsheet.j r5 = r4.r
            if (r3 == 0) goto L60
            int r6 = r4.B
            if (r6 != 0) goto L6b
            r6 = 5
            org.chromium.components.browser_ui.bottomsheet.f r5 = (org.chromium.components.browser_ui.bottomsheet.f) r5
            int r5 = r5.v(r6)
            r4.B = r5
            goto L6b
        L60:
            int r6 = r4.B
            org.chromium.components.browser_ui.bottomsheet.f r5 = (org.chromium.components.browser_ui.bottomsheet.f) r5
            mb3 r5 = r5.o
            r5.c(r6)
            r4.B = r1
        L6b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ui.i(org.chromium.chrome.browser.tab.Tab, java.lang.Integer, java.lang.Integer):void");
    }

    @Override // defpackage.v80
    public final void onDestroy() {
        this.i.a();
        Tab tab = this.y;
        if (tab != null) {
            tab.t(this.h);
        }
        ((f) this.r).e(this);
        this.o.s(this.g);
        this.e.d(this.f);
        qs1<lr2> qs1Var = this.j;
        if (qs1Var.get() != null) {
            qs1Var.get().u(this.k);
        }
        qs1<l61> qs1Var2 = this.m;
        if (qs1Var2.get() != null) {
            qs1Var2.get().f(this.n);
        }
    }
}
