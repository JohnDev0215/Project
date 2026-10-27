package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import defpackage.ds2;
import defpackage.ll;
import defpackage.lr2;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.feed.FeedPlaceholderLayout;
import org.chromium.chrome.browser.feed.FeedSwipeRefreshLayout;
import org.chromium.chrome.browser.feed.c;
import org.chromium.chrome.browser.feed.e;
import org.chromium.chrome.browser.logo.LogoView;
import org.chromium.chrome.browser.logo.a;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.chrome.browser.tasks.tab_management.n1;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.k;

/* JADX INFO: compiled from: StartSurfaceMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yr2 implements n1.d, View.OnClickListener, lr2.a, vb, a.InterfaceC0093a, hy1 {
    public final Context A;
    public final wr2 B;
    public int C;
    public int D;
    public int E;
    public TabModel F;
    public final tr2 G;
    public final sr2 H;
    public final kj I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final vr2 f103J;
    public final a K;
    public final qs1<lr2> L;
    public boolean M;
    public boolean N;
    public Boolean O;
    public Boolean P;
    public hl0 Q;
    public final boolean R;
    public lr2.a T;
    public final ViewGroup U;
    public final n1 V;
    public wp2 W;
    public boolean X;
    public final boolean Z;
    public final View.OnClickListener a0;
    public final n1.a f;
    public final y13 g;
    public final PropertyModel h;
    public final b i;
    public final boolean j;
    public final boolean l;
    public final Runnable m;
    public final nw2<Tab> n;
    public final i31 o;
    public final View r;
    public final boolean s;
    public final q2 t;
    public ri0 u;
    public n1.a v;
    public PropertyModel w;
    public org.chromium.chrome.browser.logo.a x;
    public boolean y;
    public sr1 z;
    public final org.chromium.base.b<lr2.c> e = new org.chromium.base.b<>();
    public final org.chromium.base.b<lr2.b> k = new org.chromium.base.b<>();
    public final aq1<Boolean> p = new aq1<>();
    public final ll q = new ll();
    public boolean S = true;
    public long Y = -1;

    /* JADX INFO: compiled from: StartSurfaceMediator.java */
    public interface a {
    }

    /* JADX INFO: compiled from: StartSurfaceMediator.java */
    public interface b {
    }

    public yr2(n1.a aVar, ViewGroup viewGroup, n1 n1Var, a23 a23Var, PropertyModel propertyModel, im2 im2Var, boolean z, Context context, kj kjVar, gc3 gc3Var, boolean z2, rs1 rs1Var, boolean z3, j31 j31Var, Runnable runnable, aq1 aq1Var, View view, zb zbVar, ViewGroup viewGroup2, r2 r2Var, tr trVar) {
        this.U = viewGroup;
        this.V = n1Var;
        n1.a aVarE = n1Var != null ? n1Var.e() : aVar;
        this.f = aVarE;
        this.g = a23Var;
        this.h = propertyModel;
        this.i = im2Var;
        this.j = z;
        this.A = context;
        this.I = kjVar;
        this.K = gc3Var;
        this.l = z2;
        this.L = rs1Var;
        this.o = j31Var;
        this.E = 0;
        this.m = runnable;
        this.n = aq1Var;
        this.r = view;
        this.t = r2Var;
        r2Var.f(this);
        boolean zJ = zb2.j(context);
        this.s = zJ;
        this.Z = zb2.e(context);
        this.a0 = trVar;
        if (propertyModel != null) {
            if (n1Var != null) {
                boolean z4 = aVarE.L() == 1;
                propertyModel.l(n73.g, z4);
                propertyModel.l(n73.h, z4);
                propertyModel.l(n73.f, true);
                propertyModel.l(n73.a, true);
                propertyModel.l(n73.i, false);
                propertyModel.l(n73.e, false);
            }
            if (this.P == null) {
                this.P = Boolean.valueOf(SharedPreferencesManager.getInstance().readBoolean("Chrome.Feed.ArticlesListVisible", true));
            }
            if (z && bq.F.a() && SharedPreferencesManager.getInstance().readBoolean("Chrome.Feed.ArticlesListVisible", true) && !z3 && !this.R) {
                this.Q = new hl0(context, viewGroup2);
                this.R = true;
            }
            boolean zR = a23Var.r();
            this.y = zR;
            this.H = new sr2(this);
            propertyModel.l(n73.b, zR);
            propertyModel.p(n73.s, this);
            this.G = new tr2(this);
            if (a23Var.a.isEmpty()) {
                a23Var.m(new ur2(this));
            } else {
                this.F = a23Var.g(false);
            }
            this.f103J = new vr2(this);
            this.B = new wr2(this);
            Resources resources = context.getResources();
            propertyModel.n(n73.w, resources.getDimensionPixelSize(R$dimen.tasks_surface_body_top_margin));
            PropertyModel.p pVar = n73.x;
            propertyModel.n(pVar, resources.getDimensionPixelSize(R$dimen.mv_tiles_container_top_margin));
            propertyModel.n(n73.z, resources.getDimensionPixelSize(R$dimen.tab_switcher_title_top_margin));
            if (zJ) {
                propertyModel.n(pVar, resources.getDimensionPixelOffset(R$dimen.ntp_search_box_bottom_margin) + resources.getDimensionPixelOffset(R$dimen.tile_grid_layout_top_margin));
                propertyModel.n(n73.y, resources.getDimensionPixelSize(R$dimen.ntp_header_lateral_paddings_v2));
                if (aVarE.L() == 2) {
                    propertyModel.n(n73.A, resources.getDimensionPixelOffset(R$dimen.single_tab_view_top_margin_for_feed_improvement));
                }
            }
        }
        aVarE.u0(this);
        this.D = 0;
        this.C = 0;
        if (zbVar == null || !zb.b()) {
            return;
        }
        zbVar.a(this, 4);
        if (propertyModel != null) {
            propertyModel.a(new k.a() { // from class: rr2
                @Override // org.chromium.ui.modelutil.k.a
                public final void a(k kVar, Object obj) {
                    v62 v62Var = (v62) obj;
                    yr2 yr2Var = this.e;
                    yr2Var.getClass();
                    if (v62Var == n73.b) {
                        yr2Var.O();
                    }
                }
            });
        }
        aVarE.getHandleBackPressChangedSupplier().p(new pr2(this, 1));
        aVarE.E0().p(new qr2(this, 1));
        O();
    }

    public final void C() {
        qi0 qi0Var;
        PropertyModel propertyModel = this.h;
        if (propertyModel == null || (qi0Var = (qi0) propertyModel.j(zr2.f)) == null) {
            return;
        }
        qi0Var.b.getClass();
    }

    public final int D() {
        y13 y13Var = this.g;
        return !y13Var.t() ? SharedPreferencesManager.getInstance().d(0, "Chrome.StartSurface.RegularTabCount") : y13Var.g(false).getCount();
    }

    public final boolean H() {
        return this.Z ? this.M : this.C == 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean I() {
        /*
            r3 = this;
            org.chromium.chrome.browser.logo.a r3 = r3.x
            r0 = 0
            if (r3 == 0) goto L1c
            org.chromium.chrome.browser.logo.b r3 = r3.a
            boolean r1 = r3.r
            r2 = 1
            if (r1 == 0) goto L18
            org.chromium.ui.modelutil.PropertyModel$n r1 = org.chromium.chrome.browser.logo.d.e
            org.chromium.ui.modelutil.PropertyModel r3 = r3.e
            boolean r3 = r3.k(r1)
            if (r3 == 0) goto L18
            r3 = r2
            goto L19
        L18:
            r3 = r0
        L19:
            if (r3 == 0) goto L1c
            r0 = r2
        L1c:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yr2.I():boolean");
    }

    public final void K() {
        if (H() && this.Y == -1) {
            this.Y = System.currentTimeMillis();
        }
    }

    public final void M() {
        if (this.Y != -1) {
            oj2.Y(System.currentTimeMillis() - this.Y, "StartSurface.TimeSpent");
            this.Y = -1L;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0036  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void O() {
        /*
            r4 = this;
            org.chromium.chrome.browser.tasks.tab_management.n1$a r0 = r4.v
            r1 = 1
            if (r0 == 0) goto Lc
            boolean r0 = r0.w()
            if (r0 == 0) goto Lc
            goto L52
        Lc:
            org.chromium.chrome.browser.tasks.tab_management.n1$a r0 = r4.f
            boolean r2 = r0.w()
            if (r2 == 0) goto L15
            goto L52
        L15:
            int r2 = r4.C
            r3 = 2
            if (r2 != r3) goto L36
            int r2 = r4.D
            if (r2 != r1) goto L23
            boolean r2 = r4.y
            if (r2 != 0) goto L23
            goto L52
        L23:
            org.chromium.chrome.browser.tasks.tab_management.n1$a r1 = r4.v
            if (r1 == 0) goto L36
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            zp1 r1 = r1.getHandleBackPressChangedSupplier()
            java.lang.Object r1 = r1.get()
            boolean r1 = r0.equals(r1)
            goto L52
        L36:
            boolean r1 = r4.Z
            if (r1 == 0) goto L44
            android.content.Context r1 = r4.A
            boolean r1 = defpackage.zb2.d(r1)
            if (r1 == 0) goto L44
            r1 = 0
            goto L52
        L44:
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            zp1 r0 = r0.getHandleBackPressChangedSupplier()
            java.lang.Object r0 = r0.get()
            boolean r1 = r1.equals(r0)
        L52:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            aq1<java.lang.Boolean> r4 = r4.p
            r4.r(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yr2.O():void");
    }

    public final void P() {
        this.f.v0();
        O();
        if (this.Z) {
            return;
        }
        n1.a aVar = this.v;
        if (aVar != null) {
            aVar.v0();
        }
        this.L.h(new qr2(this, 2));
    }

    public final boolean Q() {
        boolean zH = H();
        n1.a aVar = this.v;
        if (aVar != null && aVar.w()) {
            return this.v.a();
        }
        n1.a aVar2 = this.f;
        if (aVar2.w()) {
            return aVar2.a();
        }
        if (this.C != 2) {
            if (zH) {
                C();
            }
            return aVar2.a();
        }
        if (this.D == 1 && !this.y) {
            p0(1);
            zb2.h("FromTabSwitcher");
            return true;
        }
        n1.a aVar3 = this.v;
        if (aVar3 != null) {
            return aVar3.a();
        }
        return false;
    }

    public final void S() {
        hl0 hl0Var = this.Q;
        if (hl0Var != null) {
            FeedPlaceholderLayout feedPlaceholderLayout = hl0Var.b;
            if (feedPlaceholderLayout != null) {
                hl0Var.a.removeView(feedPlaceholderLayout);
                hl0Var.b = null;
            }
            this.Q = null;
        }
        n1 n1Var = this.V;
        if (n1Var != null) {
            n1Var.s().p();
        }
    }

    public final void T(int i) {
        this.h.n(zr2.a, i);
    }

    public final void V(boolean z) {
        FeedSwipeRefreshLayout feedSwipeRefreshLayout;
        PropertyModel.n nVar = zr2.c;
        PropertyModel propertyModel = this.h;
        if (z == propertyModel.k(nVar)) {
            return;
        }
        if (z && propertyModel.k(zr2.e) && propertyModel.j(zr2.f) == null && !((gc3) this.K).e()) {
            v();
        }
        propertyModel.l(nVar, z);
        qi0 qi0Var = (qi0) propertyModel.j(zr2.f);
        if (qi0Var == null || (feedSwipeRefreshLayout = qi0Var.b.H) == null) {
            return;
        }
        if (!z) {
            feedSwipeRefreshLayout.k();
        } else {
            if (feedSwipeRefreshLayout.isEnabled()) {
                return;
            }
            feedSwipeRefreshLayout.setEnabled(true);
        }
    }

    public final void X(boolean z) {
        PropertyModel propertyModel = this.h;
        if (propertyModel == null) {
            return;
        }
        propertyModel.l(n73.a, z);
        ThreadUtils.e(new f52(23, this));
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void Y(boolean z) {
        PropertyModel.n nVar = n73.d;
        PropertyModel propertyModel = this.h;
        if (z == propertyModel.k(nVar)) {
            return;
        }
        PropertyModel.n nVar2 = n73.c;
        if (!propertyModel.k(nVar2)) {
            propertyModel.l(nVar2, true);
        }
        propertyModel.l(nVar, z);
        PropertyModel.n nVar3 = n73.f;
        propertyModel.l(nVar3, !z);
        PropertyModel propertyModel2 = this.w;
        if (propertyModel2 != null) {
            if (!propertyModel2.k(nVar2)) {
                this.w.l(nVar2, true);
            }
            this.w.l(nVar, z);
            this.w.l(nVar3, !z);
        }
    }

    public final void Z(int i) {
        qi0 qi0Var;
        if (i == 1) {
            ds2.a.a.getClass();
        }
        this.E = i;
        PropertyModel propertyModel = this.h;
        if (propertyModel == null || (qi0Var = (qi0) propertyModel.j(zr2.f)) == null) {
            return;
        }
        int i2 = this.E;
        c cVar = qi0Var.b;
        cVar.getClass();
        int iA = i2 != 1 ? -1 : 1;
        e eVar = cVar.j;
        if (iA == -1) {
            eVar.getClass();
            iA = zk0.a();
        }
        if (eVar.y.size() <= iA) {
            iA = 0;
        }
        eVar.k.n(xg2.c, iA);
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
    public final void d() {
        Iterator<lr2.c> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((lr2.c) aVar.next()).d();
            }
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
    public final void e() {
        Iterator<lr2.c> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((lr2.c) aVar.next()).e();
            }
        }
    }

    @Override // defpackage.hy1
    public final void f() {
        K();
    }

    public final void g0(boolean z) {
        if (this.s) {
            if (z && this.x == null) {
                ll.b bVarB = this.q.b(new x10(21, this));
                View view = this.r;
                view.setVisibility(0);
                org.chromium.chrome.browser.logo.a aVar = new org.chromium.chrome.browser.logo.a(this.A, bVarB, (LogoView) view.findViewById(R$id.search_provider_logo), true, null, null, H(), this);
                this.x = aVar;
                if (this.X) {
                    aVar.b();
                }
            }
            if (this.x != null) {
                boolean zH = H();
                this.x.a.d(zH && z, !zH, false);
            }
        }
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.p;
    }

    @Override // defpackage.hy1
    public final void h() {
        M();
    }

    public final void h0(boolean z) {
        Runnable runnable = this.m;
        if (runnable == null) {
            return;
        }
        if (z && runnable != null) {
            runnable.run();
        }
        this.h.l(n73.t, z);
    }

    public final void j0() {
        int i = this.C;
        boolean z = false;
        if (i == 6 || i == 5) {
            PropertyModel propertyModel = this.h;
            if (propertyModel != null) {
                PropertyModel.r rVar = n73.C;
                Boolean bool = Boolean.TRUE;
                propertyModel.p(rVar, bool);
                propertyModel.p(zr2.h, bool);
                ds2.a.a.getClass();
            }
        } else if (i == 4) {
            S();
            n0(true, true);
        } else {
            kj kjVar = this.I;
            if (i == 1) {
                if (this.D == 2) {
                    K();
                }
                boolean z2 = D() > 0;
                h0(!this.y);
                g0(!this.y);
                s0(z2 && !this.y);
                V((this.y || this.u == null) ? false : true);
                k0(!this.y);
                X(!this.y);
                n0(this.y, false);
                x0();
                w0(kjVar.a());
                T(kjVar.g());
                TabModel tabModel = this.F;
                if (tabModel != null) {
                    tabModel.j(this.G);
                } else {
                    this.N = true;
                }
            } else if (i == 2) {
                if (this.D == 1) {
                    M();
                }
                s0(false);
                h0(false);
                g0(false);
                k0(false);
                X(false);
                n0(true, false);
                V(false);
                x0();
                w0(kjVar.l());
                T(0);
            } else if (i == 0 && this.w != null) {
                n0(false, false);
            }
        }
        int i2 = this.C;
        if (i2 == 1 || i2 == 2) {
            if (this.y && this.g.g(true).getCount() <= 0) {
                z = true;
            }
            Y(z);
        }
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
    public final void k() {
        PropertyModel propertyModel = this.h;
        if (propertyModel != null) {
            sr1 sr1Var = this.z;
            if (sr1Var != null) {
                sr1Var.H(this.B);
            }
            propertyModel.l(zr2.e, false);
            PropertyModel.r<qi0> rVar = zr2.f;
            qi0 qi0Var = (qi0) propertyModel.j(rVar);
            C();
            if (qi0Var != null) {
                qi0Var.b.f();
            }
            propertyModel.p(rVar, null);
            tr2 tr2Var = this.G;
            if (tr2Var != null) {
                TabModel tabModel = this.F;
                if (tabModel != null) {
                    tabModel.l(tr2Var);
                } else if (this.N) {
                    this.N = false;
                }
            }
            sr2 sr2Var = this.H;
            if (sr2Var != null) {
                this.g.l(sr2Var);
            }
            vr2 vr2Var = this.f103J;
            if (vr2Var != null) {
                this.I.s(vr2Var);
            }
            p0(0);
            n3.X0("StartSurface.Hidden");
            this.M = false;
        }
        Iterator<lr2.c> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((lr2.c) aVar.next()).f();
            }
        }
    }

    public final void k0(boolean z) {
        if (this.l) {
            return;
        }
        PropertyModel.n nVar = n73.u;
        PropertyModel propertyModel = this.h;
        if (z == propertyModel.k(nVar)) {
            return;
        }
        propertyModel.l(nVar, z);
    }

    public final void l0(n1.a aVar) {
        this.v = aVar;
        aVar.E0().p(new pr2(this, 0));
        this.v.getHandleBackPressChangedSupplier().p(new qr2(this, 0));
    }

    public final void n0(boolean z, boolean z2) {
        if (z) {
            if (this.w == null) {
                l0(((nr2) ((im2) this.i).e).J());
            }
            PropertyModel propertyModel = this.w;
            if (propertyModel != null) {
                propertyModel.l(n73.a, false);
                this.w.l(n73.b, this.y);
            }
            n1.a aVar = this.v;
            if (aVar != null && !z2) {
                aVar.Z0(true);
            }
        } else {
            n1.a aVar2 = this.v;
            if (aVar2 != null && !z2) {
                aVar2.g(false);
            }
        }
        this.h.l(zr2.d, z);
    }

    @Override // defpackage.vb
    public final int o() {
        boolean zQ = Q();
        O();
        return !zQ ? 1 : 0;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.Z) {
            this.a0.onClick(view);
        } else {
            if (this.w == null) {
                l0(((nr2) ((im2) this.i).e).J());
            }
            p0(2);
        }
        n3.X0("StartSurface.SinglePane.MoreTabs");
    }

    public final void p0(int i) {
        q0(i, this.E);
    }

    public final void q0(int i, int i2) {
        int i3;
        int i4;
        PropertyModel propertyModel = this.h;
        if (propertyModel == null || i == (i3 = this.C)) {
            return;
        }
        int i5 = this.D;
        if (i3 != 0) {
            this.D = i3;
        }
        this.C = i;
        j0();
        if (propertyModel.k(zr2.e) && (i4 = this.C) != 0) {
            if (!(i4 == 1 || i4 == 2)) {
                this.C = u();
                j0();
            }
        }
        P();
        int i6 = this.C;
        i31 i31Var = this.o;
        if (i6 == 0) {
            i31Var.b(7);
            i31Var.b(8);
        } else if (i6 == 1) {
            i31Var.a(7);
            i31Var.b(8);
        } else if (i6 == 2) {
            i31Var.b(7);
            i31Var.a(8);
        }
        Z(i2);
        int i7 = this.C;
        if (i7 == 1) {
            n3.X0("StartSurface.SinglePane.Home");
            return;
        }
        if (i7 == 2) {
            n3.X0("StartSurface.SinglePane.Tabswitcher");
        } else if (i7 == 7 && i5 == 1) {
            zb2.h("FromTab");
        }
    }

    @Override // org.chromium.chrome.browser.logo.a.InterfaceC0093a
    public final void r() {
        x0();
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.d
    public final void s() {
        Iterator<lr2.c> it = this.e.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((lr2.c) aVar.next()).h();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void s0(boolean r7) {
        /*
            r6 = this;
            r0 = 2
            org.chromium.chrome.browser.tasks.tab_management.n1$a r1 = r6.f
            r2 = 0
            r3 = 1
            if (r7 == 0) goto L42
            int r7 = r1.L()
            if (r7 != r0) goto Lf
            r7 = r3
            goto L10
        Lf:
            r7 = r2
        L10:
            if (r7 == 0) goto L40
            y13 r7 = r6.g
            org.chromium.chrome.browser.tab.Tab r4 = r7.i()
            boolean r7 = r7.t()
            if (r7 == 0) goto L2f
            if (r4 == 0) goto L2f
            org.chromium.url.GURL r7 = r4.getUrl()
            if (r7 == 0) goto L2f
            org.chromium.url.GURL r7 = r4.getUrl()
            boolean r7 = defpackage.bk3.l(r7)
            goto L3e
        L2f:
            org.chromium.chrome.browser.preferences.SharedPreferencesManager r7 = org.chromium.chrome.browser.preferences.SharedPreferencesManager.getInstance()
            java.lang.String r4 = "Chrome.AppLaunch.LastKnownActiveTabState"
            int r7 = r7.d(r2, r4)
            if (r7 != r3) goto L3d
            r7 = r3
            goto L3e
        L3d:
            r7 = r2
        L3e:
            if (r7 != 0) goto L42
        L40:
            r7 = r3
            goto L43
        L42:
            r7 = r2
        L43:
            org.chromium.ui.modelutil.PropertyModel$n r4 = defpackage.n73.g
            org.chromium.ui.modelutil.PropertyModel r6 = r6.h
            boolean r5 = r6.k(r4)
            if (r7 != r5) goto L4e
            return
        L4e:
            r6.l(r4, r7)
            org.chromium.ui.modelutil.PropertyModel$n r4 = defpackage.n73.h
            if (r7 == 0) goto L62
            int r7 = r1.L()
            if (r7 != r0) goto L5d
            r7 = r3
            goto L5e
        L5d:
            r7 = r2
        L5e:
            r7 = r7 ^ r3
            if (r7 == 0) goto L62
            r2 = r3
        L62:
            r6.l(r4, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yr2.s0(boolean):void");
    }

    public void setOnTabSelectingListener(lr2.a aVar) {
        this.T = aVar;
    }

    @Override // org.chromium.chrome.browser.tasks.tab_management.n1.b
    public final void t(int i, long j) {
        if (this.S) {
            this.T.t(i, j);
        } else {
            this.S = true;
        }
    }

    public final int u() {
        if (!this.j) {
            return 3;
        }
        int i = this.C;
        if (i == 7) {
            int i2 = this.D;
            if (i2 == 0) {
                return 1;
            }
            return i2;
        }
        if (i == 5) {
            return this.g.r() ? 2 : 1;
        }
        if (i == 4) {
            return 2;
        }
        if (i == 6) {
            return 1;
        }
        return i;
    }

    public final void v() {
        ri0 ri0Var = this.u;
        boolean zI = z7.I(this.A);
        boolean z = this.R;
        int i = this.E;
        ri0Var.getClass();
        qi0 qi0Var = new qi0(Profile.d(), ri0Var.a, zI, z, ri0Var.g, ri0Var.h, i, ri0Var.i, ri0Var.j, ri0Var.k, ri0Var.l, ri0Var.b, ri0Var.c, ri0Var.d, ri0Var.e);
        this.h.p(zr2.f, qi0Var);
        qi0Var.b.getClass();
    }

    public final void w0(int i) {
        this.h.n(zr2.g, i);
    }

    public final void x0() {
        PropertyModel.p pVar = n73.B;
        int dimensionPixelSize = 0;
        if (this.C != 2) {
            int i = R$dimen.control_container_height;
            Context context = this.A;
            int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(i);
            if (!I()) {
                dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.start_surface_fake_search_box_top_margin);
            }
            dimensionPixelSize += dimensionPixelSize2;
        }
        this.h.n(pVar, dimensionPixelSize);
    }
}
