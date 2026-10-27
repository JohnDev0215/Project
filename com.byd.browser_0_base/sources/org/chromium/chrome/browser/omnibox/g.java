package org.chromium.chrome.browser.omnibox;

import J.N;
import android.animation.ObjectAnimator;
import android.content.ComponentCallbacks;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.net.Uri;
import android.text.TextUtils;
import android.util.FloatProperty;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.aq1;
import defpackage.bw2;
import defpackage.dm0;
import defpackage.et2;
import defpackage.g3;
import defpackage.he0;
import defpackage.jg2;
import defpackage.jk1;
import defpackage.ll;
import defpackage.n3;
import defpackage.oj2;
import defpackage.ol2;
import defpackage.ot1;
import defpackage.pd;
import defpackage.pr1;
import defpackage.qo;
import defpackage.qr1;
import defpackage.qs1;
import defpackage.rj3;
import defpackage.rr1;
import defpackage.rs1;
import defpackage.s61;
import defpackage.so3;
import defpackage.sr1;
import defpackage.u8;
import defpackage.ub;
import defpackage.ur1;
import defpackage.vb;
import defpackage.vj3;
import defpackage.vl;
import defpackage.wt2;
import defpackage.xt2;
import defpackage.y70;
import defpackage.yp;
import defpackage.zb;
import defpackage.zp1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BooleanSupplier;
import org.chromium.base.b;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.omnibox.UrlBar;
import org.chromium.chrome.browser.omnibox.f;
import org.chromium.chrome.browser.omnibox.suggestions.OmniboxSuggestionsDropdown;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: LocationBarMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g implements f.a, sr1, so3.a, so3.b, u8.a, UrlBar.c, View.OnKeyListener, ComponentCallbacks, TemplateUrlService.c, vb {
    public static Boolean Q;
    public final e A;
    public final d B;
    public final ur1 C;
    public boolean D;
    public boolean E;
    public boolean F;
    public final boolean G;
    public boolean H;
    public boolean I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public float f72J;
    public boolean K;
    public final s61 L;
    public final BooleanSupplier M;
    public boolean N;
    public int O;
    public final aq1<Boolean> P;
    public final a e = new a();
    public final LocationBarLayout f;
    public so3 g;
    public final f h;
    public final rs1<u8> i;
    public et2 j;
    public org.chromium.chrome.browser.omnibox.suggestions.c k;
    public qr1 l;
    public h m;
    public final zp1<Profile> n;
    public final ot1 o;
    public final LocaleManager p;
    public final ArrayList q;
    public final qs1<TemplateUrlService> r;
    public final Context s;
    public final ub t;
    public final WindowAndroid u;
    public String v;
    public ObjectAnimator w;
    public final org.chromium.base.b<vj3> x;
    public final Rect y;
    public final jg2 z;

    /* JADX INFO: compiled from: LocationBarMediator.java */
    public class a extends FloatProperty<g> {
        public a() {
            super("");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(g.this.f72J);
        }

        @Override // android.util.FloatProperty
        public final void setValue(g gVar, float f) {
            g.this.g0(f);
        }
    }

    /* JADX INFO: compiled from: LocationBarMediator.java */
    public class b extends FloatProperty<g> {
        public b() {
            super("");
        }

        @Override // android.util.Property
        public final Float get(Object obj) {
            return Float.valueOf(((LocationBarTablet) g.this.f).getWidthChangeFraction());
        }

        @Override // android.util.FloatProperty
        public final void setValue(g gVar, float f) {
            g gVar2 = g.this;
            ((LocationBarTablet) gVar2.f).setWidthChangeAnimationFraction(f);
            if (pr1.e(gVar2.s) && gVar2.K) {
                gVar2.C.f();
            }
        }
    }

    /* JADX INFO: compiled from: LocationBarMediator.java */
    public class c extends vl {
        public final /* synthetic */ boolean f;

        public c(boolean z) {
            this.f = z;
        }

        @Override // defpackage.vl
        public final void a() {
            g.this.h0(false);
        }

        @Override // defpackage.vl
        public final void b() {
            g gVar = g.this;
            boolean z = this.f;
            gVar.Q(z, z);
        }
    }

    /* JADX INFO: compiled from: LocationBarMediator.java */
    public interface d {
        void c(String str, int i, boolean z);
    }

    /* JADX INFO: compiled from: LocationBarMediator.java */
    public interface e {
    }

    public g(Context context, LocationBarLayout locationBarLayout, f fVar, zp1 zp1Var, ot1 ot1Var, LocaleManager localeManager, rs1 rs1Var, ub ubVar, g3 g3Var, boolean z, jg2 jg2Var, s61 s61Var, pd pdVar, d dVar, BooleanSupplier booleanSupplier, ur1 ur1Var) {
        new b();
        rs1<u8> rs1Var2 = new rs1<>();
        this.i = rs1Var2;
        ll llVar = new ll();
        this.q = new ArrayList();
        this.v = "";
        this.x = new org.chromium.base.b<>();
        this.y = new Rect();
        this.O = 3;
        this.P = new aq1<>();
        this.s = context;
        this.f = locationBarLayout;
        this.h = fVar;
        fVar.l(this);
        this.o = ot1Var;
        this.p = localeManager;
        so3 so3Var = new so3(this, rs1Var2, zp1Var);
        this.g = so3Var;
        so3Var.f.a(this);
        this.n = zp1Var;
        zp1Var.p(llVar.b(new dm0(16, this)));
        this.r = rs1Var;
        this.t = ubVar;
        this.u = g3Var;
        this.G = z;
        this.z = jg2Var;
        this.I = z;
        this.L = s61Var;
        this.A = pdVar;
        this.B = dVar;
        this.M = booleanSupplier;
        this.C = ur1Var;
    }

    @Override // defpackage.sr1
    public final void C(vj3 vj3Var) {
        this.x.a(vj3Var);
    }

    @Override // org.chromium.chrome.browser.omnibox.f.a
    public final void D() {
        Profile profile;
        f fVar = this.h;
        e0(fVar.n(), fVar.b());
        if (this.l != null && (profile = this.n.get()) != null) {
            qr1 qr1Var = this.l;
            N.MZa0jqjv(qr1Var.a, qr1Var, profile);
        }
        n0();
    }

    @Override // so3.b
    public final void E() {
        n0();
    }

    @Override // defpackage.sr1
    public final void H(vj3 vj3Var) {
        this.x.c(vj3Var);
    }

    @Override // org.chromium.chrome.browser.omnibox.f.a
    public final void I() {
        f fVar = this.h;
        boolean zIsIncognito = fVar.isIncognito();
        boolean z = this.K;
        Context context = this.s;
        this.O = rr1.a(z ? yp.a(context, fVar.isIncognito()) : fVar.a(), context, zIsIncognito);
        ColorStateList colorStateListY0 = n3.y0(context);
        LocationBarLayout locationBarLayout = this.f;
        locationBarLayout.setDeleteButtonTint(colorStateListY0);
        h hVar = this.m;
        int i = this.O;
        i iVar = hVar.i;
        iVar.getClass();
        PropertyModel.p pVar = j.l;
        PropertyModel propertyModel = iVar.e;
        int iH = propertyModel.h(pVar);
        propertyModel.n(pVar, i);
        if ((iH != i) && !this.K) {
            e0(fVar.n(), fVar.b());
        }
        et2 et2Var = this.j;
        int i2 = this.O;
        wt2 wt2Var = et2Var.f;
        if (wt2Var.t != i2) {
            wt2Var.t = i2;
            wt2Var.e();
        }
        et2Var.a();
        org.chromium.chrome.browser.omnibox.suggestions.c cVar = this.k;
        if (cVar != null) {
            int i3 = this.O;
            org.chromium.chrome.browser.omnibox.suggestions.d dVar = cVar.h;
            he0 he0Var = dVar.n;
            if (he0Var.d != i3) {
                he0Var.d = i3;
                for (int i4 = 0; i4 < he0Var.e.size(); i4++) {
                    he0Var.e.get(i4).b.n(bw2.a, i3);
                }
            }
            dVar.i.n(org.chromium.chrome.browser.omnibox.suggestions.i.e, i3);
        }
        l0();
        u8 u8Var = this.i.get();
        if (u8Var == null) {
            return;
        }
        locationBarLayout.setLensButtonTint(u8Var.o ? null : n3.y0(context));
    }

    @Override // defpackage.sr1
    public final void K() {
        this.h.isIncognito();
        Uri uri = Uri.EMPTY;
        uri.equals(uri);
        this.L.a.getClass();
    }

    @Override // org.chromium.components.search_engines.TemplateUrlService.c
    public final void M() {
        t();
        Q = Boolean.FALSE;
    }

    @Override // defpackage.sr1
    public final boolean O() {
        return this.K;
    }

    @Override // org.chromium.chrome.browser.omnibox.UrlBar.c
    public final void P() {
        if (!zb.b()) {
            zb.c(9);
        }
        if (this.t.C()) {
            return;
        }
        s(null, 12, false);
        f fVar = this.h;
        e0(fVar.n(), fVar.b());
        S();
    }

    public final void Q(boolean z, boolean z2) {
        h hVar = this.m;
        if (hVar == null) {
            return;
        }
        hVar.e(z2, true);
        h0(false);
        p0();
        if (this.G || z) {
            return;
        }
        this.f.setUrlActionContainerVisibility(8);
    }

    public final void S() {
        View view;
        f fVar = this.h;
        if (!fVar.q() || (view = fVar.d().getView()) == null) {
            return;
        }
        view.requestFocus();
    }

    public final void T() {
        this.k.b(this.m.c(), this.m.b());
    }

    public final void V(boolean z) {
        Iterator<vj3> it = this.x.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                break;
            } else {
                ((vj3) aVar.next()).k(z);
            }
        }
        if (this.G) {
            ObjectAnimator objectAnimator = this.w;
            if (objectAnimator != null && objectAnimator.isRunning()) {
                this.w.cancel();
                this.w = null;
            }
            if (this.h.g().i()) {
                Q(z, z);
                return;
            }
            this.f.getRootView().getLocalVisibleRect(this.y);
            float fHeight = r1.height() / Math.max(r1.height(), r1.width());
            float[] fArr = new float[1];
            fArr[0] = z ? 1.0f : 0.0f;
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, this.e, fArr);
            this.w = objectAnimatorOfFloat;
            objectAnimatorOfFloat.setDuration((long) (fHeight * 200.0f));
            this.w.addListener(new c(z));
            this.w.start();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void X(java.lang.String r15, int r16, long r17, java.lang.String r19, byte[] r20) {
        /*
            Method dump skipped, instruction units count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.omnibox.g.X(java.lang.String, int, long, java.lang.String, byte[]):void");
    }

    public final void Y() {
        this.f.b();
    }

    public final void Z() {
        boolean z = this.K;
        f fVar = this.h;
        if (!z) {
            e0(fVar.n(), fVar.b());
            return;
        }
        if (jk1.p(fVar.n(), fVar.isIncognito())) {
            this.m.g(rj3.h, 2, 0);
            T();
        } else {
            this.m.g(fVar.b(), 0, 0);
        }
        this.m.e(false, false);
    }

    public final void c0(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        int i = 9;
        if (!this.D) {
            this.q.add(new y70(this, i, str));
            return;
        }
        s(null, 9, true);
        this.m.g(rj3.b(str), 0, 0);
        org.chromium.chrome.browser.omnibox.suggestions.d dVar = this.k.h;
        if (dVar.v && dVar.w != null) {
            dVar.n(false);
            f fVar = dVar.k;
            if (fVar.q()) {
                dVar.w.b(fVar.j(false, false), -1, fVar.n(), str, false);
            }
        }
        this.m.e(true, false);
    }

    @Override // defpackage.sr1
    public final so3 d() {
        if (this.f == null) {
            return null;
        }
        return this.g;
    }

    @Override // defpackage.sr1
    public final void e(String str, List<String> list) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String strD = this.r.get().d(str, list);
        if (TextUtils.isEmpty(strD)) {
            c0(str);
        } else {
            X(strD, 5, 0L, null, null);
        }
    }

    public final void e0(String str, rj3 rj3Var) {
        if (this.m.f.hasFocus()) {
            return;
        }
        this.v = str;
        this.m.g(rj3Var, 1, 0);
    }

    public final void g0(float f) {
        this.f72J = f;
        if (this.G) {
            this.h.g().b(f);
            return;
        }
        boolean z = true;
        boolean z2 = f > 0.0f && !this.F;
        if (z2 != this.N) {
            this.N = z2;
            n0();
        }
        LocationBarLayout locationBarLayout = this.f;
        if (f > 0.0f) {
            locationBarLayout.setUrlActionContainerVisibility(0);
        } else if (f == 0.0f && !this.F) {
            locationBarLayout.setUrlActionContainerVisibility(8);
        }
        locationBarLayout.setUrlFocusChangePercent(f);
        wt2 wt2Var = this.j.f;
        float f2 = wt2Var.K;
        if ((f2 != 0.0f || f <= 0.0f) && (f != 0.0f || f2 <= 0.0f)) {
            z = false;
        }
        wt2Var.K = f;
        wt2Var.g();
        Profile profile = wt2Var.h.get();
        PropertyModel propertyModel = wt2Var.e;
        if (profile == null || !wt2Var.b()) {
            propertyModel.m(xt2.j, 1.0f);
        } else {
            propertyModel.m(xt2.j, f);
        }
        if (z) {
            wt2Var.f(0);
        }
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.P;
    }

    @Override // org.chromium.chrome.browser.omnibox.f.a
    public final void h() {
        this.k.a();
    }

    public final void h0(boolean z) {
        if (this.m == null) {
            return;
        }
        this.F = z;
        if (z) {
            return;
        }
        n0();
        if (this.E && this.K && qo.f().c()) {
            String strC = this.m.c();
            this.m.f.clearFocus();
            this.m.f.requestFocus();
            if (!TextUtils.isEmpty(strC)) {
                this.m.g(rj3.b(strC), 0, 1);
                T();
            }
        }
        Iterator<vj3> it = this.x.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((vj3) aVar.next()).J0(this.K);
            }
        }
    }

    @Override // org.chromium.chrome.browser.omnibox.f.a
    public final void j() {
        this.f.getClass();
    }

    public final boolean j0() {
        h hVar = this.m;
        return (hVar != null && !TextUtils.isEmpty(hVar.b())) && (this.K || this.F);
    }

    @Override // u8.a
    public final void k() {
        l0();
        u8 u8Var = this.i.get();
        if (u8Var == null) {
            return;
        }
        this.f.setLensButtonTint(u8Var.o ? null : n3.y0(this.s));
    }

    public final boolean k0() {
        if (j0() || !this.D) {
            return false;
        }
        f fVar = this.h;
        if (fVar.j(fVar.isIncognito(), false) == 16) {
            return false;
        }
        if (this.G && this.I) {
            if (!this.K && !this.F) {
                return false;
            }
            if (Q == null) {
                t();
                Q = Boolean.FALSE;
            }
            return Q.booleanValue();
        }
        if (!this.K && !this.F && !this.N) {
            return false;
        }
        if (Q == null) {
            t();
            Q = Boolean.FALSE;
        }
        return Q.booleanValue();
    }

    @Override // org.chromium.chrome.browser.omnibox.UrlBar.c
    public final View l() {
        Tab tabD = this.h.d();
        if (tabD == null) {
            return null;
        }
        return tabD.getView();
    }

    public final void l0() {
        u8 u8Var = this.i.get();
        if (u8Var == null) {
            return;
        }
        ColorStateList colorStateListY0 = u8Var.o ? null : n3.y0(this.s);
        LocationBarLayout locationBarLayout = this.f;
        locationBarLayout.setMicButtonTint(colorStateListY0);
        locationBarLayout.setMicButtonDrawable(AppCompatResources.a(u8Var.f, u8Var.o ? R$drawable.ic_colorful_mic : R$drawable.btn_mic));
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x00e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void n0() {
        /*
            Method dump skipped, instruction units count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.omnibox.g.n0():void");
    }

    @Override // defpackage.vb
    public final int o() {
        int i = !this.K ? 1 : 0;
        P();
        return i;
    }

    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        boolean z;
        boolean z2 = view.getLayoutDirection() == 1;
        org.chromium.chrome.browser.omnibox.suggestions.c cVar = this.k;
        cVar.getClass();
        if (keyEvent.getAction() == 0) {
            OmniboxSuggestionsDropdown omniboxSuggestionsDropdown = cVar.j;
            boolean z3 = omniboxSuggestionsDropdown != null && omniboxSuggestionsDropdown.getViewGroup().isShown();
            boolean z4 = n3.J0(keyEvent) || n3.M0(keyEvent) || n3.K0(keyEvent) || n3.L0(keyEvent);
            org.chromium.chrome.browser.omnibox.suggestions.d dVar = cVar.h;
            if (z3 && dVar.s.b.size() > 0 && z4) {
                dVar.L = false;
            }
            if (!z3 || !cVar.j.getViewGroup().onKeyDown(i, keyEvent)) {
                if (n3.I0(keyEvent) && cVar.e.getVisibility() == 0) {
                    long eventTime = keyEvent.getEventTime();
                    String strB = ((h) dVar.h).b();
                    dVar.d();
                    if (!dVar.v || dVar.w == null) {
                        dVar.u = new ol2(eventTime, dVar, 1, strB);
                    } else {
                        dVar.e(eventTime, strB);
                    }
                }
                z = false;
            }
            z = true;
        } else {
            z = false;
        }
        if (!z) {
            if (i == 4) {
                if (zb.b()) {
                    return false;
                }
                boolean z5 = keyEvent.getAction() == 0;
                LocationBarLayout locationBarLayout = this.f;
                if (z5 && keyEvent.getRepeatCount() == 0) {
                    locationBarLayout.getKeyDispatcherState().startTracking(keyEvent, this);
                } else {
                    if (!(keyEvent.getAction() == 1)) {
                        return false;
                    }
                    locationBarLayout.getKeyDispatcherState().handleUpEvent(keyEvent);
                    if (!keyEvent.isTracking() || keyEvent.isCanceled()) {
                        return false;
                    }
                    P();
                }
            } else if (i == 111) {
                if (!(keyEvent.getAction() == 0) || keyEvent.getRepeatCount() != 0) {
                    return false;
                }
                Z();
            } else {
                if ((z2 || !n3.L0(keyEvent)) && !(z2 && n3.K0(keyEvent))) {
                    return false;
                }
                TextView textView = (TextView) view;
                if (textView.getSelectionStart() != textView.getSelectionEnd() || textView.getSelectionEnd() != textView.getText().length()) {
                    return false;
                }
            }
        }
        return true;
    }

    public final void p0() {
        boolean z = this.G ? this.K : this.K || this.F;
        wt2 wt2Var = this.j.f;
        wt2Var.getClass();
        wt2Var.e.l(xt2.b, z);
    }

    @Override // org.chromium.chrome.browser.omnibox.UrlBar.c
    public final boolean q() {
        return !this.h.isIncognito();
    }

    @Override // org.chromium.chrome.browser.omnibox.f.a
    public final void r() {
        t();
        Q = Boolean.FALSE;
        n0();
        f fVar = this.h;
        boolean zIsIncognito = fVar.isIncognito();
        this.z.getClass();
        if (!zIsIncognito) {
            LocationBarLayout locationBarLayout = this.f;
            if (locationBarLayout instanceof LocationBarPhone) {
                locationBarLayout.setClipToPadding(false);
            }
        }
        this.m.i.e.l(j.m, fVar.isIncognito());
    }

    @Override // defpackage.sr1
    public final void s(String str, int i, boolean z) {
        boolean z2 = this.K;
        if (z) {
            if (!z2) {
                oj2.U(i, 15, "Android.OmniboxFocusReason");
                if (k0()) {
                    n3.X0("MobileOmniboxFocusedLensShown");
                }
            }
            if (i == 2 || i == 3 || i == 7 || i == 6) {
                this.E = true;
            }
            this.m.f.requestFocus();
        } else {
            this.m.f.clearFocus();
        }
        if (str != null) {
            this.m.g(rj3.b(str), 0, 1);
            T();
        }
    }

    @Override // defpackage.sr1
    public final void t() {
        this.h.isIncognito();
        this.L.a.getClass();
    }

    @Override // org.chromium.chrome.browser.omnibox.UrlBar.c
    public final void v(boolean z) {
        oj2.U(z ? 1 : 0, 15, "Android.OmniboxFocusReason");
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }
}
