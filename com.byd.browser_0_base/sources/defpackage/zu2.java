package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListPopupWindow;
import defpackage.l61;
import java.util.ArrayList;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.compositor.CompositorViewHolder;
import org.chromium.chrome.browser.compositor.LayerTitleCache;
import org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller;
import org.chromium.chrome.browser.compositor.scene_layer.TabStripSceneLayer;
import org.chromium.chrome.browser.layouts.EventFilter;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.base.LocalizationUtils;
import org.chromium.ui.resources.ResourceManager;

/* JADX INFO: compiled from: StripLayoutHelperManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class zu2 implements je2, hy1 {
    public final nw2<LayerTitleCache> A;
    public y13 f;
    public final n61 g;
    public final h8 h;
    public boolean i;
    public final vu2 j;
    public final vu2 k;
    public float l;
    public final float m;
    public int n;
    public lw o;
    public Context p;
    public boolean q;
    public TabStripSceneLayer r;
    public final c s;
    public float t;
    public dv2 u;
    public ev2 v;
    public bv2 x;
    public final q2 y;
    public final String z;
    public final RectF e = new RectF();
    public final a w = new a();

    /* JADX INFO: compiled from: StripLayoutHelperManager.java */
    public class a implements e23 {
        public a() {
        }

        @Override // defpackage.e23
        public final void k(TabModel tabModel) {
            zu2.this.r(tabModel.isIncognito());
        }
    }

    public zu2(Context context, n61 n61Var, j61 j61Var, hs hsVar, r2 r2Var) {
        this.g = n61Var;
        this.A = hsVar;
        this.r = new TabStripSceneLayer(context);
        b bVar = new b();
        this.s = new c();
        this.y = r2Var;
        r2Var.f(this);
        this.z = context.getString(R$string.tab_loading_default_title);
        this.h = new h8(context, bVar);
        av2 av2Var = new av2(this);
        if (bq.f0.a()) {
            if (oj2.F()) {
                this.o = new ya3(context, 36.0f, 36.0f, av2Var);
                this.t = 36.0f;
            } else {
                this.o = new ya3(context, 38.0f, 38.0f, av2Var);
                this.t = 38.0f;
            }
            context.getResources().getColor(R$color.model_selector_button_icon_color);
            context.getResources().getColor(R$color.default_icon_color_secondary_light);
            context.getResources().getColor(R$color.model_selector_button_bg_color);
            sk skVar = bq.d;
            int i = skVar.a() ? R$color.default_bg_color_dark_elev_1_gm3_baseline : R$color.default_bg_color_dark_elev_1_baseline;
            int i2 = skVar.a() ? R$color.default_bg_color_dark_elev_2_gm3_baseline : R$color.default_bg_color_dark_elev_2_baseline;
            if (oj2.F()) {
                context.getResources().getColor(i);
            } else {
                context.getResources().getColor(i2);
            }
            ((ya3) this.o).getClass();
            ((ya3) this.o).getClass();
            this.o.f(0.0f);
        } else {
            lw lwVar = new lw(context, 24.0f, 24.0f, av2Var);
            this.o = lwVar;
            lwVar.f(10.0f);
            this.t = 24.0f;
        }
        lw lwVar2 = this.o;
        lwVar2.h = false;
        lwVar2.g = false;
        lwVar2.e = 12.0f;
        Resources resources = context.getResources();
        this.m = resources.getDimension(R$dimen.tab_strip_height) / resources.getDisplayMetrics().density;
        lw lwVar3 = this.o;
        String string = resources.getString(R$string.accessibility_tabstrip_btn_incognito_toggle_standard);
        String string2 = resources.getString(R$string.accessibility_tabstrip_btn_incognito_toggle_incognito);
        lwVar3.j = string;
        lwVar3.k = string2;
        this.q = false;
        vu2 vu2Var = new vu2(context, n61Var, j61Var, false, this.o);
        this.j = vu2Var;
        vu2 vu2Var2 = new vu2(context, n61Var, j61Var, true, this.o);
        this.k = vu2Var2;
        this.p = context;
        vu2Var.v = new StackScroller(context);
        vu2Var.W = context;
        vu2Var2.v = new StackScroller(context);
        vu2Var2.W = context;
    }

    public static void d(zu2 zu2Var, Tab tab) {
        nw2<LayerTitleCache> nw2Var = zu2Var.A;
        if (nw2Var.get() == null) {
            return;
        }
        String strA = nw2Var.get().a(tab, zu2Var.z);
        vu2 vu2VarK = zu2Var.k(tab.isIncognito());
        int id = tab.getId();
        Tab tabL = vu2VarK.l(id);
        if (tabL != null) {
            vu2VarK.r(vu2VarK.f(id), strA, tabL.isHidden());
        }
        ((h61) zu2Var.g).T(null);
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0108  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x011b  */
    @Override // defpackage.je2
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean G(long r17) {
        /*
            Method dump skipped, instruction units count: 360
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.zu2.G(long):boolean");
    }

    @Override // defpackage.je2
    public final void M0(float f, float f2, float f3, int i) {
        boolean z;
        this.l = f;
        if (this.n != i) {
            this.n = i;
            z = true;
        } else {
            z = false;
        }
        if (LocalizationUtils.isLayoutRtl()) {
            this.o.e(bq.f0.a() ? (48.0f - this.t) / 2.0f : 12.0f);
        } else {
            this.o.e((this.l - this.t) - (bq.f0.a() ? (48.0f - this.t) / 2.0f : 12.0f));
        }
        boolean z2 = z;
        this.j.n(this.l, this.m, z2, SystemClock.uptimeMillis());
        this.k.n(this.l, this.m, z2, SystemClock.uptimeMillis());
        RectF rectF = this.e;
        rectF.set(0.0f, 0.0f, this.l, Math.min(this.m, f3));
        this.h.p.set(rectF);
    }

    @Override // defpackage.je2
    public final ke2 U(RectF rectF, ResourceManager resourceManager) {
        Tab tabAt = this.f.z().getTabAt(this.f.z().index());
        if (tabAt != null) {
            tabAt.getId();
        }
        TabStripSceneLayer tabStripSceneLayer = this.r;
        this.A.get();
        fv2[] fv2VarArr = e().j;
        long j = tabStripSceneLayer.f;
        return this.r;
    }

    @Override // defpackage.je2
    public final boolean a() {
        return false;
    }

    public final vu2 e() {
        return k(this.i);
    }

    @Override // defpackage.hy1
    public final void f() {
        Tab tabI = this.f.i();
        if (tabI == null) {
            return;
        }
        vu2 vu2VarK = k(tabI.isIncognito());
        vu2VarK.a(SystemClock.uptimeMillis(), true);
        ((h61) vu2VarK.a).T(null);
    }

    @Override // defpackage.je2
    public final void f0(ArrayList arrayList) {
        if (this.q) {
            return;
        }
        vu2 vu2VarE = e();
        int i = 0;
        while (true) {
            fv2[] fv2VarArr = vu2VarE.h;
            if (i >= fv2VarArr.length) {
                break;
            }
            fv2 fv2Var = fv2VarArr[i];
            fv2Var.getClass();
            arrayList.add(fv2Var);
            if (fv2Var.x) {
                arrayList.add(fv2Var.f);
            }
            i++;
        }
        ya3 ya3Var = vu2VarE.n;
        if (ya3Var.g) {
            arrayList.add(ya3Var);
        }
        lw lwVar = this.o;
        if (lwVar.g) {
            arrayList.add(lwVar);
        }
    }

    public final vu2 k(boolean z) {
        return z ? this.k : this.j;
    }

    @Override // defpackage.je2
    public final boolean o0() {
        return false;
    }

    @Override // defpackage.je2
    public final boolean q1() {
        return true;
    }

    public final void r(boolean z) {
        if (z == this.i) {
            return;
        }
        this.i = z;
        vu2 vu2Var = this.k;
        if (z) {
            vu2Var.a(0L, false);
        } else {
            vu2Var.u.dismiss();
        }
        boolean z2 = !this.i;
        vu2 vu2Var2 = this.j;
        if (z2) {
            vu2Var2.a(0L, false);
        } else {
            vu2Var2.u.dismiss();
        }
        s();
        Activity activity = null;
        ((h61) this.g).T(null);
        if (at0.a().d()) {
            return;
        }
        Context baseContext = this.p;
        while (true) {
            if (!(baseContext instanceof ContextWrapper)) {
                break;
            }
            if (baseContext instanceof Activity) {
                activity = (Activity) baseContext;
                break;
            }
            baseContext = ((ContextWrapper) baseContext).getBaseContext();
        }
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView().getRootView();
        Context context = this.p;
        boolean z3 = this.i;
        viewGroup.setBackgroundColor(z3 ? yp.b(context, z3) : yp.c(context, R$dimen.default_elevation_0));
    }

    public final void s() {
        float f;
        float f2;
        float f3;
        this.o.h = this.i;
        y13 y13Var = this.f;
        if (y13Var != null) {
            boolean z = y13Var.g(true).getCount() != 0;
            this.o.g = z;
            if (z) {
                if (bq.f0.a()) {
                    f2 = this.t;
                    f3 = 8.0f;
                } else {
                    f2 = this.t;
                    f3 = 24.0f;
                }
                f = f2 + f3;
            } else {
                f = 0.0f;
            }
            vu2 vu2Var = this.j;
            vu2Var.getClass();
            boolean zIsLayoutRtl = LocalizationUtils.isLayoutRtl();
            float f4 = vu2Var.q;
            float f5 = vu2Var.R;
            if (zIsLayoutRtl) {
                vu2Var.P = f5 + f + f4;
            } else {
                vu2Var.Q = f5 + f + f4;
            }
            vu2 vu2Var2 = this.k;
            vu2Var2.getClass();
            boolean zIsLayoutRtl2 = LocalizationUtils.isLayoutRtl();
            float f6 = vu2Var2.q;
            float f7 = vu2Var2.R;
            if (zIsLayoutRtl2) {
                vu2Var2.P = f + f7 + f6;
            } else {
                vu2Var2.Q = f + f7 + f6;
            }
        }
    }

    @Override // defpackage.je2
    public final EventFilter x() {
        return this.h;
    }

    /* JADX INFO: compiled from: StripLayoutHelperManager.java */
    public class b implements ls0 {
        public b() {
        }

        @Override // defpackage.ls0
        public final void e() {
            TabModel tabModel;
            zu2 zu2Var = zu2.this;
            lw lwVar = zu2Var.o;
            boolean z = lwVar.f;
            lwVar.f = false;
            if (z && zu2Var.f != null) {
                zu2Var.e().g();
                if (zu2Var.o.d == 0.0f) {
                    zu2Var.e().s(true);
                }
                if (zu2Var.o.g) {
                    zu2Var.f.d(!r13.r());
                    return;
                }
                return;
            }
            vu2 vu2VarE = zu2Var.e();
            SystemClock.uptimeMillis();
            ya3 ya3Var = vu2VarE.M;
            if (ya3Var != null) {
                ya3Var.f = false;
            }
            vu2VarE.M = null;
            if (vu2VarE.A) {
                ArrayList arrayList = new ArrayList();
                vu2VarE.z = 0;
                vu2VarE.A = false;
                vu2VarE.g();
                h61 h61Var = (h61) vu2VarE.a;
                ew ewVar = h61Var.G;
                fv2 fv2Var = vu2VarE.L;
                arrayList.add(kw.g(ewVar, fv2Var, fv2.C, fv2Var.p, 0.0f, 125L));
                if (!bq.f0.a()) {
                    int i = 0;
                    while (true) {
                        fv2[] fv2VarArr = vu2VarE.h;
                        if (i >= fv2VarArr.length) {
                            break;
                        }
                        vu2VarE.v(fv2VarArr[i], false);
                        i++;
                    }
                } else {
                    int i2 = 0;
                    while (true) {
                        fv2[] fv2VarArr2 = vu2VarE.h;
                        if (i2 >= fv2VarArr2.length) {
                            break;
                        }
                        fv2 fv2Var2 = fv2VarArr2[i2];
                        if (fv2Var2 != vu2VarE.L) {
                            fv2Var2.l = 0.0f;
                            if (oj2.F()) {
                                vu2VarE.E(fv2Var2, true, null);
                            }
                        }
                        i2++;
                    }
                }
                vu2VarE.s(true);
                vu2VarE.L.getClass();
                if (oj2.F()) {
                    vu2VarE.E(vu2VarE.L, true, arrayList);
                }
                vu2VarE.x(arrayList, new yu2(vu2VarE, true));
                h61Var.T(null);
            }
            vu2VarE.L = null;
            vu2VarE.z = 0;
            ya3 ya3Var2 = vu2VarE.n;
            boolean z2 = ya3Var2.f;
            ya3Var2.f = false;
            if (z2 && (tabModel = vu2VarE.c) != null) {
                if (!tabModel.isIncognito()) {
                    vu2VarE.c.c();
                }
                vu2VarE.d.f(2);
            }
            vu2VarE.V = false;
        }

        @Override // defpackage.ls0
        public final void j(int i, float f, float f2, boolean z) {
            boolean z2;
            long jUptimeMillis = SystemClock.uptimeMillis();
            zu2 zu2Var = zu2.this;
            lw lwVar = zu2Var.o;
            boolean z3 = true;
            if (lwVar.c(f, f2)) {
                lwVar.f = false;
                z2 = true;
            } else {
                z2 = false;
            }
            if (z2) {
                zu2Var.o.a(jUptimeMillis);
                return;
            }
            vu2 vu2VarE = zu2Var.e();
            long jUptimeMillis2 = SystemClock.uptimeMillis();
            vu2VarE.q(false);
            ya3 ya3Var = vu2VarE.n;
            if (ya3Var.c(f, f2)) {
                ya3Var.f = false;
            } else {
                z3 = false;
            }
            if (z3) {
                n3.X0("MobileToolbarNewTab");
                ya3Var.a(jUptimeMillis2);
                return;
            }
            fv2 fv2VarK = vu2VarE.k(f);
            if (fv2VarK == null || fv2VarK.h) {
                return;
            }
            if (fv2VarK.f(f, f2) || (z && (i & 4) != 0)) {
                n3.X0("MobileToolbarCloseTab");
                fv2VarK.f.a(jUptimeMillis2);
                return;
            }
            n3.X0("MobileTabSwitched.TabletTabStrip");
            if (vu2VarE.f100J != null && vu2VarE.K != null) {
                long jElapsedRealtime = SystemClock.elapsedRealtime();
                long jLongValue = jElapsedRealtime - vu2VarE.f100J.longValue();
                if (jElapsedRealtime - vu2VarE.K.longValue() <= 60000) {
                    oj2.Y(jLongValue, "Android.TabStrip.TimeToSwitchTab");
                }
                vu2VarE.f100J = null;
                vu2VarE.K = null;
            }
            fv2VarK.a(jUptimeMillis2);
        }

        /* JADX WARN: Removed duplicated region for block: B:23:0x0052  */
        @Override // defpackage.ls0
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void l(int r11, float r12, float r13, boolean r14) {
            /*
                r10 = this;
                zu2 r10 = defpackage.zu2.this
                lw r0 = r10.o
                boolean r1 = r0.c(r12, r13)
                r2 = 0
                r3 = 1
                if (r1 == 0) goto L10
                r0.f = r3
                r0 = r3
                goto L11
            L10:
                r0 = r2
            L11:
                if (r0 == 0) goto L14
                return
            L14:
                vu2 r10 = r10.e()
                long r0 = android.os.SystemClock.uptimeMillis()
                r10.q(r2)
                ya3 r4 = r10.n
                boolean r5 = r4.c(r12, r13)
                if (r5 == 0) goto L2b
                r4.f = r3
                r4 = r3
                goto L2c
            L2b:
                r4 = r2
            L2c:
                j61 r5 = r10.b
                r6 = 0
                if (r4 == 0) goto L37
                org.chromium.chrome.browser.compositor.CompositorViewHolder r5 = (org.chromium.chrome.browser.compositor.CompositorViewHolder) r5
                r5.f(r6)
                goto L94
            L37:
                fv2 r4 = r10.k(r12)
                r7 = -1
                if (r4 == 0) goto L47
                org.chromium.chrome.browser.tabmodel.TabModel r8 = r10.c
                int r9 = r4.a
                int r8 = defpackage.z7.G(r8, r9)
                goto L48
            L47:
                r8 = r7
            L48:
                if (r8 == r7) goto L52
                fv2[] r7 = r10.h
                int r9 = r7.length
                if (r8 >= r9) goto L52
                r7 = r7[r8]
                goto L53
            L52:
                r7 = r6
            L53:
                r10.L = r7
                if (r4 == 0) goto L5e
                boolean r13 = r4.f(r12, r13)
                if (r13 == 0) goto L5e
                r2 = r3
            L5e:
                if (r2 == 0) goto L6b
                ya3 r13 = r4.f
                r13.f = r3
                r10.M = r13
                org.chromium.chrome.browser.compositor.CompositorViewHolder r5 = (org.chromium.chrome.browser.compositor.CompositorViewHolder) r5
                r5.f(r6)
            L6b:
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller r13 = r10.v
                boolean r13 = r13.a()
                if (r13 != 0) goto L7f
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller r13 = r10.v
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller$a r5 = r13.c
                r5.k = r3
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller$a r13 = r13.b
                r13.k = r3
                r10.L = r6
            L7f:
                if (r14 == 0) goto L94
                if (r2 != 0) goto L94
                if (r4 == 0) goto L94
                float r13 = r4.m
                r14 = 1065353216(0x3f800000, float:1.0)
                int r13 = (r13 > r14 ? 1 : (r13 == r14 ? 0 : -1))
                if (r13 < 0) goto L94
                r11 = r11 & 4
                if (r11 != 0) goto L94
                r10.y(r0, r12)
            L94:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: zu2.b.l(int, float, float, boolean):void");
        }

        @Override // defpackage.ls0
        public final void r(float f, float f2) {
            vu2 vu2VarE = zu2.this.e();
            long jUptimeMillis = SystemClock.uptimeMillis();
            fv2 fv2VarK = vu2VarE.k(f);
            if (fv2VarK == null || !fv2VarK.f(f, f2)) {
                vu2VarE.q(false);
                vu2VarE.y(jUptimeMillis, f);
                return;
            }
            fv2VarK.f.f = false;
            ((CompositorViewHolder) vu2VarE.b).f(null);
            vu2VarE.c.q(z7.G(vu2VarE.c, fv2VarK.a), 3, false);
            View view = z7.p(vu2VarE.c).getView();
            ListPopupWindow listPopupWindow = vu2VarE.u;
            listPopupWindow.setAnchorView(view);
            listPopupWindow.setVerticalOffset((-(view.getHeight() - ((int) vu2VarE.W.getResources().getDimension(R$dimen.tab_strip_height)))) - ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin);
            listPopupWindow.setHorizontalOffset(Math.max((Math.round((fv2VarK.s + fv2VarK.u) * vu2VarE.W.getResources().getDisplayMetrics().density) - listPopupWindow.getWidth()) - ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).leftMargin, 0));
            listPopupWindow.show();
        }

        @Override // defpackage.ls0
        public final void s(float f, float f2, float f3, float f4, float f5) {
            boolean z;
            zu2 zu2Var = zu2.this;
            lw lwVar = zu2Var.o;
            if (!lwVar.c(f, f2)) {
                lwVar.f = false;
            }
            vu2 vu2VarE = zu2Var.e();
            long jUptimeMillis = SystemClock.uptimeMillis();
            vu2VarE.q(false);
            vu2VarE.F = jUptimeMillis;
            if (LocalizationUtils.isLayoutRtl()) {
                f3 = -f3;
            }
            ya3 ya3Var = vu2VarE.n;
            if (!ya3Var.c(f, f2)) {
                ya3Var.f = false;
            }
            ya3 ya3Var2 = vu2VarE.M;
            if (ya3Var2 != null) {
                if (ya3Var2.c(f, f2)) {
                    z = ya3Var2.f;
                } else {
                    ya3Var2.f = false;
                    z = false;
                }
                if (!z) {
                    vu2VarE.M = null;
                }
            }
            if (vu2VarE.A) {
                float f6 = f - vu2VarE.B;
                if (Math.abs(f6) >= 1.0f) {
                    if (LocalizationUtils.isLayoutRtl()) {
                        if (f3 >= 1.0f) {
                            vu2VarE.z |= 1;
                        } else if (f3 <= -1.0f) {
                            vu2VarE.z |= 2;
                        }
                    } else if (f3 >= 1.0f) {
                        vu2VarE.z |= 2;
                    } else if (f3 <= -1.0f) {
                        vu2VarE.z |= 1;
                    }
                    vu2VarE.B = f;
                    vu2VarE.G(f6);
                }
            } else if (vu2VarE.v.a()) {
                float fB = vu2VarE.T ? vu2VarE.b(vu2VarE.L, true, true, true) : 0.0f;
                if (vu2VarE.L == null || fB == 0.0f) {
                    if (!vu2VarE.V) {
                        vu2VarE.V = true;
                        n3.X0("MobileToolbarSlideTabs");
                        long jElapsedRealtime = SystemClock.elapsedRealtime();
                        Long l = vu2VarE.K;
                        if (l == null || jElapsedRealtime - l.longValue() > 60000) {
                            vu2VarE.f100J = Long.valueOf(jElapsedRealtime);
                            vu2VarE.K = Long.valueOf(jElapsedRealtime);
                        } else {
                            vu2VarE.K = Long.valueOf(jElapsedRealtime);
                        }
                    }
                    vu2VarE.I(vu2VarE.w + f3);
                } else if ((fB > 0.0f && f3 > 0.0f) || (fB < 0.0f && f3 < 0.0f)) {
                    vu2VarE.v.b(Math.round(vu2VarE.w), (int) fB, vu2VarE.i(), jUptimeMillis);
                }
            } else {
                StackScroller.a aVar = vu2VarE.v.b;
                aVar.c = (int) (aVar.c + f3);
                aVar.k = false;
            }
            if (!bq.e0.a() && !vu2VarE.A) {
                float fAbs = Math.abs(f4);
                float fAbs2 = Math.abs(f5);
                float f7 = vu2VarE.t;
                if (f5 > f7 && fAbs < f7 * 2.0f && fAbs > 0.001f && fAbs2 / fAbs > vu2.a0) {
                    vu2VarE.y(jUptimeMillis, f - f4);
                }
            }
            if (!vu2VarE.A) {
                vu2VarE.L = null;
            }
            ((h61) vu2VarE.a).T(null);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x008b  */
        @Override // defpackage.ls0
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void t(float r20, float r21) {
            /*
                r19 = this;
                r0 = r19
                zu2 r0 = defpackage.zu2.this
                vu2 r0 = r0.e()
                long r9 = android.os.SystemClock.uptimeMillis()
                r11 = 0
                r0.q(r11)
                boolean r1 = org.chromium.ui.base.LocalizationUtils.isLayoutRtl()
                if (r1 == 0) goto L1a
                r1 = r20
                float r1 = -r1
                goto L1c
            L1a:
                r1 = r20
            L1c:
                boolean r2 = r0.A
                if (r2 == 0) goto L22
                goto Lb4
            L22:
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller r2 = r0.v
                boolean r2 = r2.a()
                r3 = 1
                r12 = 0
                if (r2 != 0) goto L47
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller r2 = r0.v
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller$a r2 = r2.b
                int r2 = r2.c
                float r4 = r0.w
                int r4 = java.lang.Math.round(r4)
                int r2 = r2 - r4
                r0.L = r12
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller r4 = r0.v
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller$a r5 = r4.c
                r5.k = r3
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller$a r4 = r4.b
                r4.k = r3
                r13 = r2
                goto L48
            L47:
                r13 = r11
            L48:
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller r14 = r0.v
                float r2 = r0.w
                int r2 = java.lang.Math.round(r2)
                int r1 = (int) r1
                float r4 = r0.x
                int r4 = (int) r4
                r5 = 0
                r6 = 0
                r16 = 0
                boolean r7 = r14.d
                if (r7 == 0) goto L8b
                boolean r7 = r14.a()
                if (r7 != 0) goto L8b
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller$a r7 = r14.b
                float r7 = r7.e
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller$a r8 = r14.c
                float r8 = r8.e
                float r12 = (float) r1
                float r17 = java.lang.Math.signum(r12)
                float r18 = java.lang.Math.signum(r7)
                int r17 = (r17 > r18 ? 1 : (r17 == r18 ? 0 : -1))
                if (r17 != 0) goto L8b
                float r15 = (float) r11
                float r17 = java.lang.Math.signum(r15)
                float r18 = java.lang.Math.signum(r8)
                int r17 = (r17 > r18 ? 1 : (r17 == r18 ? 0 : -1))
                if (r17 != 0) goto L8b
                float r12 = r12 + r7
                int r1 = (int) r12
                float r15 = r15 + r8
                int r7 = (int) r15
                r12 = r7
                r7 = r1
                goto L8d
            L8b:
                r7 = r1
                r12 = r11
            L8d:
                r14.a = r3
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller$a r1 = r14.b
                r3 = r7
                r7 = r9
                r1.c(r2, r3, r4, r5, r6, r7)
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller$a r1 = r14.c
                r2 = 0
                r4 = 0
                r3 = r12
                r5 = 0
                r6 = r16
                r1.c(r2, r3, r4, r5, r6, r7)
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller r1 = r0.v
                org.chromium.chrome.browser.compositor.layouts.phone.stack.StackScroller$a r1 = r1.b
                int r2 = r1.c
                int r2 = r2 + r13
                r1.c = r2
                r1.k = r11
                n61 r0 = r0.a
                h61 r0 = (defpackage.h61) r0
                r1 = 0
                r0.T(r1)
            Lb4:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: zu2.b.t(float, float):void");
        }

        @Override // defpackage.ls0
        public final void u() {
        }
    }

    @Override // defpackage.je2
    public final void N() {
    }

    @Override // defpackage.hy1
    public final void h() {
    }

    /* JADX INFO: compiled from: StripLayoutHelperManager.java */
    public class c implements l61.a {
        public c() {
        }

        @Override // l61.a
        public final void g(int i, boolean z) {
            if (i != 2) {
                return;
            }
            zu2 zu2Var = zu2.this;
            zu2Var.q = !g90.a(zu2Var.p);
        }

        @Override // l61.a
        public final void i(int i, boolean z, boolean z2) {
            if (i != 2) {
                return;
            }
            zu2.this.q = false;
        }

        @Override // l61.a
        public final void e(int i) {
        }
    }
}
