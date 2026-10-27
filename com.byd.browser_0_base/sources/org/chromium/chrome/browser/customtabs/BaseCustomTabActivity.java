package org.chromium.chrome.browser.customtabs;

import J.N;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.View;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import defpackage.a30;
import defpackage.a40;
import defpackage.a8;
import defpackage.aq1;
import defpackage.b30;
import defpackage.bi1;
import defpackage.bq;
import defpackage.cp;
import defpackage.cw1;
import defpackage.d23;
import defpackage.es3;
import defpackage.f23;
import defpackage.fj;
import defpackage.fk3;
import defpackage.fy2;
import defpackage.g23;
import defpackage.g3;
import defpackage.g80;
import defpackage.gd;
import defpackage.gk2;
import defpackage.h61;
import defpackage.hp;
import defpackage.hz2;
import defpackage.i32;
import defpackage.il3;
import defpackage.jd;
import defpackage.js3;
import defpackage.k50;
import defpackage.kd;
import defpackage.l40;
import defpackage.lw1;
import defpackage.m7;
import defpackage.md;
import defpackage.n3;
import defpackage.nc2;
import defpackage.nw2;
import defpackage.ny2;
import defpackage.o23;
import defpackage.o30;
import defpackage.o70;
import defpackage.oj;
import defpackage.oj2;
import defpackage.on1;
import defpackage.or;
import defpackage.pe1;
import defpackage.pp;
import defpackage.q11;
import defpackage.q2;
import defpackage.q30;
import defpackage.qq0;
import defpackage.r2;
import defpackage.rq0;
import defpackage.s50;
import defpackage.sb0;
import defpackage.sk;
import defpackage.u20;
import defpackage.u40;
import defpackage.uj1;
import defpackage.ur;
import defpackage.v13;
import defpackage.v20;
import defpackage.v30;
import defpackage.w20;
import defpackage.wq0;
import defpackage.x20;
import defpackage.xd3;
import defpackage.xf3;
import defpackage.xr1;
import defpackage.xx0;
import defpackage.yq2;
import defpackage.z2;
import defpackage.z20;
import defpackage.z70;
import defpackage.zb;
import java.util.List;
import org.chromium.chrome.R$anim;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.app.ChromeActivity;
import org.chromium.chrome.browser.bookmarks.BookmarkModel;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.layouts.content.TabContentManager;
import org.chromium.chrome.browser.customtabs.BaseCustomTabActivity;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.chrome.browser.webapps.c;
import org.chromium.chrome.browser.webapps.e;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.base.d;
import s50.a;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseCustomTabActivity extends ChromeActivity<jd> {
    public static final /* synthetic */ int o1 = 0;
    public md a1;
    public oj b1;
    public o30 c1;
    public u40 d1;
    public w20 e1;
    public x20 f1;
    public a30 g1;
    public z20 h1;
    public v30 i1;
    public a40 j1;
    public c k1;
    public xf3 l1;
    public il3 m1;
    public boolean n1;

    public class a extends xr1 {
        public a() {
            super(true);
        }

        @Override // defpackage.xr1
        public final void a() {
            BaseCustomTabActivity.this.m();
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final int A2() {
        return R$dimen.custom_tabs_control_container_height;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final int B2() {
        return R$layout.custom_tabs_control_container;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, defpackage.e7
    public final m7 C0() {
        boolean z = this.b1.l() == 2;
        z2 z2Var = this.G0;
        uj1 uj1Var = this.L;
        d23 d23VarG2 = G2();
        g gVar = this.N0.x;
        View decorView = getWindow().getDecorView();
        aq1<BookmarkModel> aq1Var = this.k0;
        il3 il3Var = this.m1;
        int iJ = this.b1.J();
        List<String> listV = this.b1.v();
        boolean zN = this.b1.N();
        boolean zA0 = this.b1.a0();
        boolean zB0 = this.b1.b0();
        boolean Z = this.b1.Z();
        oj ojVar = this.b1;
        ojVar.getClass();
        return new b30(this, z2Var, uj1Var, d23VarG2, gVar, decorView, aq1Var, il3Var, iJ, listV, zN, zA0, zB0, Z, ojVar instanceof xx0, z);
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity
    public final on1 F1() {
        fy2 fy2VarA = fy2.a();
        if (i32.e == null) {
            i32.e = new i32();
        }
        a40 a40Var = new a40(this.K, fy2VarA, i32.e);
        this.j1 = a40Var;
        return a40Var;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    public final void G0() {
        super.G0();
        z20 z20Var = this.h1;
        if (z20Var.k == null) {
            z20Var.k = new k50();
        }
        z20Var.k.d(this.p0);
    }

    @Override // org.chromium.chrome.browser.ChromeBaseAppCompatActivity
    public final void G1() {
        a40 a40Var = this.j1;
        AppCompatDelegate appCompatDelegateA1 = A1();
        Intent intent = getIntent();
        a40Var.getClass();
        a40Var.j = q11.l(intent, "androidx.browser.customtabs.extra.COLOR_SCHEME", 0);
        a40Var.k = appCompatDelegateA1;
        a40Var.a();
        if (a40Var.j == 0) {
            a40Var.g.a.a(a40Var.h);
            a40Var.f.a.a(a40Var.i);
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final int I2() {
        return R$layout.custom_tabs_toolbar;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    public void K() {
        if (isTaskRoot()) {
            fk3.a(this, this.G0, this.o0);
        }
        if (!this.b1.U()) {
            this.f1.c();
        }
        super.K();
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final void K2() {
        c cVar = this.k1;
        if (cVar != null) {
            e eVar = cVar.h;
            eVar.getClass();
            g80.b().a(new es3(0, eVar));
        }
        g80.b().a(new wq0(23, this));
        super.K2();
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final void L2() {
        super.L2();
        if (zb.b()) {
            a aVar = new a();
            OnBackPressedDispatcher onBackPressedDispatcher = this.k;
            onBackPressedDispatcher.getClass();
            onBackPressedDispatcher.b(aVar);
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final void W2(long j) {
        super.W2(j);
        oj2.b0(j, "MobileStartup.IntentToCreationTime.CustomTabs");
        int iX2 = x2();
        if (iX2 == 3 || iX2 == 4) {
            oj2.b0(j, "MobileStartup.IntentToCreationTime.Webapp");
        }
        if (iX2 == 4) {
            oj2.b0(j, "MobileStartup.IntentToCreationTime.WebApk");
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final boolean Y2() {
        if (!super.Y2()) {
            return false;
        }
        int i = this.g1.c;
        return i == 4 || i == 3;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final boolean Z2() {
        sk skVar = bq.a;
        return (N.M09VlOh_("CCTPrefetchDelayShowOnStart") && !this.v0 && this.g1.c == 4) ? false : true;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity
    public void b2() {
        Intent intent = getIntent();
        a40 a40Var = this.j1;
        oj ojVarD3 = d3(intent, (a40Var == null || !a40Var.d()) ? 1 : 2);
        this.b1 = ojVarD3;
        if (ojVarD3 == null) {
            finishAndRemoveTask();
            return;
        }
        super.b2();
        if (this.b1.O()) {
            if (bq.o.a()) {
                oj ojVar = this.b1;
                int iF = ojVar.f();
                int iO = cw1.o(this, ojVar.s(), ojVar.r(), new yq2(10, this), ojVar.b(), bq.p.a());
                if (iO == 1 || iO == 3) {
                    iF = R$anim.slide_in_up;
                } else if (iO == 2) {
                    int iD = ojVar.D();
                    if (iD == 1) {
                        iF = R$anim.slide_in_up;
                    } else if (iD == 2) {
                        int iC = ojVar.C();
                        lw1.a aVar = lw1.L;
                        iF = ((iC == 1) ^ LocalizationUtils.isLayoutRtl()) ^ true ? R$anim.slide_in_right : R$anim.slide_in_left;
                    }
                }
                overridePendingTransition(iF, R$anim.no_anim);
            } else if (this.b1.Q()) {
                overridePendingTransition(R$anim.slide_in_up, R$anim.no_anim);
            }
        }
        js3 js3VarM = this.b1.M();
        if (js3VarM != null) {
            setTitle(js3VarM.f);
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final boolean b3() {
        if (this.b1.c0()) {
            return false;
        }
        return super.b3();
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    public final void d1() {
        int[] iArrQ = this.b1.q();
        if (iArrQ != null) {
            N.MwmPuE$v("GsaExperiments", iArrQ, false);
        }
        super.d1();
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public final boolean d2() {
        x20 x20Var = this.f1;
        x20Var.g.a.getClass();
        boolean z = !TextUtils.isEmpty(null);
        int i = x20Var.r.c;
        if (i != 3 && i != 4 && !z) {
            if (!(x20Var.k.e != null)) {
                return true;
            }
        }
        return false;
    }

    public abstract oj d3(Intent intent, int i);

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        Boolean boolG = n3.G(keyEvent, this.d1.m, (qq0) E2(), this);
        return boolG != null ? boolG.booleanValue() : super.dispatchKeyEvent(keyEvent);
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity
    public boolean e2(Intent intent) {
        return q11.i(intent, "android.support.customtabs.extra.LAUNCH_AS_TRUSTED_WEB_ACTIVITY", false);
    }

    /* JADX WARN: Type inference failed for: r5v0, types: [id] */
    @Override // org.chromium.chrome.browser.app.ChromeActivity
    /* JADX INFO: renamed from: e3, reason: merged with bridge method [inline-methods] */
    public s50.a j2(hp hpVar) {
        oj ojVar;
        Object u20Var;
        Object q30Var;
        Object v20Var;
        Object l40Var;
        ?? r5 = new v30.a() { // from class: id
            @Override // v30.a
            public final boolean a(Intent intent) {
                int i = BaseCustomTabActivity.o1;
                BaseCustomTabActivity baseCustomTabActivity = this.a;
                return baseCustomTabActivity.s0.z(intent, true, baseCustomTabActivity.M2());
            }
        };
        oj ojVar2 = this.b1;
        kd kdVar = new kd(ojVar2, this.j1, r5, this.N0.v, new o70());
        s50 s50Var = (s50) pp.d();
        s50Var.getClass();
        s50.a aVar = s50Var.new a(hpVar, kdVar);
        this.c1 = aVar.I();
        this.d1 = aVar.N();
        this.e1 = aVar.E();
        this.f1 = aVar.H();
        this.g1 = aVar.M();
        aVar.C();
        this.h1 = aVar.J();
        this.i1 = aVar.D();
        this.m1 = aVar.P();
        aVar.z();
        Object obj = aVar.Q;
        if (obj instanceof pe1) {
            synchronized (obj) {
                l40Var = aVar.Q;
                if (l40Var instanceof pe1) {
                    AppCompatActivity appCompatActivity = hpVar.a;
                    a8.n(appCompatActivity);
                    a30 a30VarM = aVar.M();
                    o23 o23VarK = aVar.K();
                    q2 q2Var = hpVar.h;
                    a8.n(q2Var);
                    xd3 xd3Var = kdVar.e;
                    a8.n(xd3Var);
                    ojVar = ojVar2;
                    l40Var = new l40(appCompatActivity, a30VarM, o23VarK, ojVar2, q2Var, xd3Var);
                    sb0.b(aVar.Q, l40Var);
                    aVar.Q = l40Var;
                } else {
                    ojVar = ojVar2;
                }
            }
            obj = l40Var;
        } else {
            ojVar = ojVar2;
        }
        Object obj2 = aVar.e;
        if (obj2 instanceof pe1) {
            synchronized (obj2) {
                v20Var = aVar.e;
                if (v20Var instanceof pe1) {
                    q2 q2Var2 = hpVar.h;
                    a8.n(q2Var2);
                    AppCompatActivity appCompatActivity2 = hpVar.a;
                    a8.n(appCompatActivity2);
                    nw2<Bundle> nw2Var = hpVar.x;
                    a8.n(nw2Var);
                    v20Var = new v20(q2Var2, ojVar, appCompatActivity2, nw2Var, n3.W0());
                    sb0.b(aVar.e, v20Var);
                    aVar.e = v20Var;
                }
            }
            obj2 = v20Var;
        }
        Object obj3 = aVar.D;
        if (obj3 instanceof pe1) {
            synchronized (obj3) {
                q30Var = aVar.D;
                if (q30Var instanceof pe1) {
                    AppCompatActivity appCompatActivity3 = hpVar.a;
                    a8.n(appCompatActivity3);
                    q30Var = new q30(appCompatActivity3, aVar.K());
                    sb0.b(aVar.D, q30Var);
                    aVar.D = q30Var;
                }
            }
            obj3 = q30Var;
        }
        Object obj4 = aVar.d;
        if (obj4 instanceof pe1) {
            synchronized (obj4) {
                u20Var = aVar.d;
                if (u20Var instanceof pe1) {
                    CustomTabsConnection customTabsConnectionW0 = n3.W0();
                    q2 q2Var3 = hpVar.h;
                    a8.n(q2Var3);
                    u20Var = new u20(customTabsConnectionW0, ojVar, q2Var3, aVar.M());
                    sb0.b(aVar.d, u20Var);
                    aVar.d = u20Var;
                }
            }
            obj4 = u20Var;
        }
        this.e1.n = new z70(this, 4, (u20) obj4);
        aVar.F();
        oj ojVar3 = this.b1;
        ojVar3.getClass();
        if (ojVar3 instanceof xx0) {
            aVar.B();
        }
        if (ojVar3.U()) {
            this.k1 = aVar.R();
        }
        if (ojVar3.T()) {
            aVar.Q();
        }
        if (this.b1.S()) {
            this.l1 = aVar.O();
        }
        return aVar;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    /* JADX INFO: renamed from: f3, reason: merged with bridge method [inline-methods] */
    public final d23 G2() {
        return (d23) super.G2();
    }

    @Override // android.app.Activity
    public final void finish() {
        super.finish();
        oj ojVar = this.b1;
        if (ojVar != null && ojVar.V()) {
            this.n1 = true;
            overridePendingTransition(ojVar.f(), ojVar.g());
            this.n1 = false;
        } else {
            if (ojVar == null || !ojVar.N()) {
                return;
            }
            overridePendingTransition(R$anim.no_anim, R$anim.activity_close_exit);
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, org.chromium.chrome.browser.init.AsyncInitializationActivity, defpackage.nj
    public final void g0() {
        super.g0();
        if (this.b1.U()) {
            this.f1.c();
        }
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, defpackage.y6
    public final boolean g1() {
        if (this.g1.b == null || !this.d1.m) {
            return false;
        }
        return super.g1();
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void g3() {
        /*
            r4 = this;
            aw r0 = new aw
            r1 = 18
            r0.<init>(r1, r4)
            oj r1 = r4.b1
            boolean r2 = r1.S()
            if (r2 != 0) goto L2e
            boolean r2 = r1.U()
            if (r2 == 0) goto L16
            goto L2e
        L16:
            boolean r2 = r1.O()
            if (r2 == 0) goto L2a
            boolean r1 = r1.V()
            if (r1 == 0) goto L2a
            md r4 = r4.a1
            r30 r4 = r4.J0
            r4.i(r0)
            goto L7d
        L2a:
            r0.run()
            goto L7d
        L2e:
            C extends ip r4 = r4.c0
            jd r4 = (defpackage.jd) r4
            vg3 r4 = r4.b()
            boolean r1 = r4.d
            if (r1 != 0) goto L3e
            r0.run()
            goto L7d
        L3e:
            oj r1 = r4.b
            cp3 r2 = r1.L()
            if (r2 == 0) goto L5e
            rp3 r1 = defpackage.rp3.a()
            r1.getClass()
            qp3 r3 = new qp3
            android.app.Activity r4 = r4.a
            r3.<init>()
            android.content.Context r4 = defpackage.xz.a
            tp3 r1 = r1.a
            java.lang.String r2 = r2.a
            r1.a(r4, r2, r3)
            goto L77
        L5e:
            androidx.browser.customtabs.CustomTabsSessionToken r1 = r1.z()
            java.lang.String r2 = "finishAndRemoveTask"
            r3 = 0
            org.chromium.chrome.browser.customtabs.CustomTabsConnection r4 = r4.c
            android.os.Bundle r4 = r4.s(r1, r2, r3)
            r1 = 0
            if (r4 == 0) goto L78
            java.lang.String r2 = "success"
            boolean r4 = r4.getBoolean(r2, r1)
            if (r4 == 0) goto L78
        L77:
            r1 = 1
        L78:
            if (r1 != 0) goto L7d
            r0.run()
        L7d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.BaseCustomTabActivity.g3():void");
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity, defpackage.jf1
    public boolean h1(int i, boolean z) {
        if (i == R$id.focus_url_bar || i == R$id.all_bookmarks_menu_id || i == R$id.help_id || i == R$id.recent_tabs_menu_id || i == R$id.new_incognito_tab_menu_id || i == R$id.new_tab_menu_id || i == R$id.open_history_menu_id) {
            return true;
        }
        return super.h1(i, z);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004a  */
    @Override // org.chromium.chrome.browser.app.ChromeActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean m() {
        /*
            r4 = this;
            w20 r4 = r4.e1
            sp r0 = r4.j
            boolean r0 = r0.f
            r1 = 0
            if (r0 != 0) goto Lb
            goto L74
        Lb:
            java.lang.String r0 = "CustomTabs.SystemBack"
            defpackage.n3.X0(r0)
            a30 r0 = r4.f
            org.chromium.chrome.browser.tab.Tab r2 = r0.b
            if (r2 != 0) goto L17
            goto L74
        L17:
            boolean r2 = defpackage.zb.b()
            r3 = 1
            if (r2 != 0) goto L4a
            org.chromium.chrome.browser.tab.Tab r0 = r0.b
            org.chromium.content_public.browser.WebContents r0 = r0.c()
            if (r0 == 0) goto L38
            org.chromium.content_public.browser.RenderFrameHost r0 = r0.U()
            if (r0 == 0) goto L38
            boolean r0 = r0.k()
            if (r0 == 0) goto L38
            r4 = 12
            defpackage.zb.c(r4)
            goto L73
        L38:
            org.chromium.chrome.browser.toolbar.g r0 = r4.m
            if (r0 == 0) goto L4a
            ad3 r0 = r0.c0
            boolean r0 = r0.a()
            if (r0 == 0) goto L4a
            r4 = 14
            defpackage.zb.c(r4)
            goto L73
        L4a:
            r0 = 17
            defpackage.zb.c(r0)
            x20 r0 = r4.e
            z20 r2 = r0.m
            a23 r2 = r2.c()
            org.chromium.chrome.browser.tabmodel.TabModel r2 = r2.z()
            int r2 = r2.getCount()
            if (r2 != r3) goto L63
            r2 = r3
            goto L64
        L63:
            r2 = r1
        L64:
            if (r2 == 0) goto L6d
            defpackage.yh1.d(r1)
            r4.a(r1)
            goto L73
        L6d:
            defpackage.yh1.d(r3)
            r0.a()
        L73:
            r1 = r3
        L74:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.customtabs.BaseCustomTabActivity.m():boolean");
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [fd] */
    /* JADX WARN: Type inference failed for: r2v10, types: [fd] */
    /* JADX WARN: Type inference failed for: r2v12, types: [hd] */
    /* JADX WARN: Type inference failed for: r2v5, types: [fd] */
    /* JADX WARN: Type inference failed for: r2v7, types: [hd] */
    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final nc2 m2() {
        gk2 gk2Var = this.d0;
        z2 z2Var = this.G0;
        f23 f23Var = this.j0;
        aq1<BookmarkModel> aq1Var = this.k0;
        aq1<ny2> aq1Var2 = this.l0;
        g23 g23Var = this.f0;
        fj fjVarZ2 = z2();
        g3 g3Var = this.O;
        r2 r2Var = this.K;
        aq1<h61> aq1Var3 = this.z0;
        final int i = 0;
        ?? r0 = new nw2(this) { // from class: fd
            public final /* synthetic */ BaseCustomTabActivity f;

            {
                this.f = this;
            }

            @Override // defpackage.nw2
            public final Object get() {
                int i2 = i;
                BaseCustomTabActivity baseCustomTabActivity = this.f;
                switch (i2) {
                    case 0:
                        return Integer.valueOf(baseCustomTabActivity.w2());
                    case 1:
                        return baseCustomTabActivity.q0();
                    default:
                        return baseCustomTabActivity.e1;
                }
            }
        };
        aq1<bi1> aq1Var4 = this.D;
        ur urVar = new ur(this, 2);
        cp cpVar = new cp(1, this);
        hz2 hz2Var = this.g0;
        rq0 rq0VarE2 = E2();
        aq1<CompositorViewHolder> aq1Var5 = this.y0;
        aq1<TabContentManager> aq1Var6 = this.o0;
        final int i2 = 1;
        ?? r2 = new nw2(this) { // from class: fd
            public final /* synthetic */ BaseCustomTabActivity f;

            {
                this.f = this;
            }

            @Override // defpackage.nw2
            public final Object get() {
                int i22 = i2;
                BaseCustomTabActivity baseCustomTabActivity = this.f;
                switch (i22) {
                    case 0:
                        return Integer.valueOf(baseCustomTabActivity.w2());
                    case 1:
                        return baseCustomTabActivity.q0();
                    default:
                        return baseCustomTabActivity.e1;
                }
            }
        };
        int iX2 = x2();
        gd gdVar = new gd(this, i2);
        final int i3 = 0;
        ?? r22 = new nw2(this) { // from class: hd
            public final /* synthetic */ BaseCustomTabActivity f;

            {
                this.f = this;
            }

            @Override // defpackage.nw2
            public final Object get() {
                int i4 = i3;
                BaseCustomTabActivity baseCustomTabActivity = this.f;
                switch (i4) {
                    case 0:
                        return Boolean.valueOf(baseCustomTabActivity.U);
                    default:
                        return baseCustomTabActivity.c1.v.get();
                }
            }
        };
        d dVar = this.M;
        yq2 yq2Var = new yq2(3, this);
        final int i4 = 2;
        ?? r23 = new nw2(this) { // from class: fd
            public final /* synthetic */ BaseCustomTabActivity f;

            {
                this.f = this;
            }

            @Override // defpackage.nw2
            public final Object get() {
                int i22 = i4;
                BaseCustomTabActivity baseCustomTabActivity = this.f;
                switch (i22) {
                    case 0:
                        return Integer.valueOf(baseCustomTabActivity.w2());
                    case 1:
                        return baseCustomTabActivity.q0();
                    default:
                        return baseCustomTabActivity.e1;
                }
            }
        };
        gd gdVar2 = new gd(this, i4);
        final int i5 = 1;
        md mdVar = new md(this, gk2Var, z2Var, f23Var, aq1Var, aq1Var2, g23Var, fjVarZ2, g3Var, r2Var, aq1Var3, this, r0, aq1Var4, this, urVar, cpVar, hz2Var, (qq0) rq0VarE2, aq1Var5, aq1Var6, r2, iX2, gdVar, r22, this, this, dVar, yq2Var, r23, gdVar2, new nw2(this) { // from class: hd
            public final /* synthetic */ BaseCustomTabActivity f;

            {
                this.f = this;
            }

            @Override // defpackage.nw2
            public final Object get() {
                int i42 = i5;
                BaseCustomTabActivity baseCustomTabActivity = this.f;
                switch (i42) {
                    case 0:
                        return Boolean.valueOf(baseCustomTabActivity.U);
                    default:
                        return baseCustomTabActivity.c1.v.get();
                }
            }
        }, this.S0, new gd(this, 0));
        this.a1 = mdVar;
        return mdVar;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final Pair<or, or> n2() {
        z20 z20Var = this.h1;
        return Pair.create(z20Var.a(false), z20Var.a(true));
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final v13 o2() {
        z20 z20Var = this.h1;
        z20Var.getClass();
        k50 k50Var = new k50();
        z20Var.k = k50Var;
        return k50Var;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        return !this.d1.m ? super.onKeyDown(i, keyEvent) : n3.S0(keyEvent, true, false, G2(), this, this.N0.x) || super.onKeyDown(i, keyEvent);
    }

    @Override // org.chromium.chrome.browser.init.AsyncInitializationActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onNewIntent(Intent intent) {
        Intent intent2 = getIntent();
        super.onNewIntent(intent);
        setIntent(intent2);
        this.i1.a(d3(intent, 1));
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final void p2() {
        this.h1.b();
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final void q2() {
        k50 k50Var;
        z20 z20Var = this.h1;
        if (z20Var == null || (k50Var = z20Var.k) == null) {
            return;
        }
        k50Var.b();
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final Tab v2() {
        return this.g1.b;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final int w2() {
        oj ojVar = this.b1;
        if (ojVar.m().f()) {
            return ojVar.m().d();
        }
        return 0;
    }

    @Override // org.chromium.chrome.browser.app.ChromeActivity
    public final int x2() {
        return this.b1.d();
    }
}
