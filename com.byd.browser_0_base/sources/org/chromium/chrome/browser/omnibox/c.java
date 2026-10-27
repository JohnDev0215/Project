package org.chromium.chrome.browser.omnibox;

import J.N;
import android.content.Context;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.View;
import defpackage.aq1;
import defpackage.ax0;
import defpackage.bq;
import defpackage.bz0;
import defpackage.ca;
import defpackage.ce;
import defpackage.dw2;
import defpackage.et2;
import defpackage.f2;
import defpackage.g3;
import defpackage.ga;
import defpackage.ge0;
import defpackage.gs;
import defpackage.he0;
import defpackage.hk1;
import defpackage.i31;
import defpackage.ie0;
import defpackage.it3;
import defpackage.jg2;
import defpackage.ko0;
import defpackage.ll;
import defpackage.nw2;
import defpackage.ot1;
import defpackage.p2;
import defpackage.pd;
import defpackage.pr1;
import defpackage.ps;
import defpackage.q2;
import defpackage.qr1;
import defpackage.qs1;
import defpackage.rj3;
import defpackage.rs1;
import defpackage.s61;
import defpackage.sk;
import defpackage.so3;
import defpackage.sr1;
import defpackage.td2;
import defpackage.u8;
import defpackage.ub;
import defpackage.ur1;
import defpackage.v91;
import defpackage.vj;
import defpackage.vr1;
import defpackage.vt;
import defpackage.wt2;
import defpackage.yp;
import defpackage.zb;
import defpackage.zf1;
import defpackage.zp1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import org.chromium.base.Callback;
import org.chromium.chrome.R$color;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.locale.LocaleManager;
import org.chromium.chrome.browser.omnibox.g;
import org.chromium.chrome.browser.omnibox.status.StatusView;
import org.chromium.chrome.browser.omnibox.suggestions.OmniboxSuggestionsDropdown;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.favicon.LargeIconBridge;
import org.chromium.components.search_engines.TemplateUrlService;
import org.chromium.ui.base.DeviceFormFactor;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: LocationBarCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements v91, hk1, ga {
    public LocationBarLayout e;
    public a f;
    public q2 g;
    public h h;
    public org.chromium.chrome.browser.omnibox.suggestions.c i;
    public et2 j;
    public g k;
    public View l;
    public View m;
    public View n;
    public View o;
    public ll p = new ll();
    public boolean q;
    public boolean r;
    public final int s;
    public final int t;
    public final int u;
    public final int v;

    /* JADX INFO: compiled from: LocationBarCoordinator.java */
    public interface a {
        void destroy();
    }

    public c(View view, View view2, zp1 zp1Var, f fVar, ActionMode.Callback callback, it3 it3Var, g3 g3Var, nw2 nw2Var, zp1 zp1Var2, nw2 nw2Var2, bz0 bz0Var, q2 q2Var, ot1 ot1Var, ub ubVar, jg2 jg2Var, et2.a aVar, ps psVar, pd pdVar, g.d dVar, nw2 nw2Var3, ce.a aVar2, BooleanSupplier booleanSupplier, i31 i31Var, aq1 aq1Var, f2 f2Var, vj vjVar, Callback callback2, zb zbVar, vr1 vr1Var) {
        boolean z;
        this.e = (LocationBarLayout) view;
        this.g = q2Var;
        q2Var.f(this);
        Context context = this.e.getContext();
        rs1 rs1Var = new rs1();
        ur1 ur1Var = new ur1(g3Var, it3Var, view2, this.e);
        this.l = this.e.findViewById(R$id.url_bar);
        this.k = new g(context, this.e, fVar, zp1Var, ot1Var, LocaleManager.getInstance(), rs1Var, ubVar, g3Var, DeviceFormFactor.b(g3Var) && (this.e instanceof LocationBarTablet), jg2Var, s61.b, pdVar, dVar, booleanSupplier, ur1Var);
        if (zbVar != null && zb.b()) {
            zbVar.a(this.k, 9);
        }
        boolean z2 = bz0Var != null && bz0Var.b();
        UrlBar urlBar = (UrlBar) this.l;
        ll llVar = this.p;
        g gVar = this.k;
        Objects.requireNonNull(gVar);
        this.h = new h(urlBar, it3Var, callback, llVar.b(new ko0(20, gVar)), this.k, (p2) g3Var.f, z2, callback2);
        this.i = new org.chromium.chrome.browser.omnibox.suggestions.c(this.e, ca.a(g3Var), this, ur1Var, this.h, zp1Var2, nw2Var, nw2Var2, fVar, zp1Var, psVar, nw2Var3, aVar2, i31Var, f2Var, vr1Var);
        StatusView statusView = (StatusView) this.e.findViewById(R$id.location_bar_status);
        View view3 = this.l;
        if (view3 instanceof UrlBarApi26) {
            UrlBarApi26 urlBarApi26 = (UrlBarApi26) view3;
            if (!TextUtils.isEmpty(urlBarApi26.getText()) || urlBarApi26.isFocusable()) {
                z = false;
                statusView.setVisibility(0);
            } else {
                statusView.setVisibility(4);
                z = false;
            }
            urlBarApi26.addTextChangedListener(new org.chromium.chrome.browser.omnibox.a(urlBarApi26, statusView));
        } else {
            z = false;
        }
        boolean z3 = z;
        et2 et2Var = new et2(DeviceFormFactor.b(g3Var), statusView, fVar, rs1Var, jg2Var, zp1Var, g3Var, aVar, aq1Var, vjVar);
        this.j = et2Var;
        g gVar2 = this.k;
        h hVar = this.h;
        org.chromium.chrome.browser.omnibox.suggestions.c cVar = this.i;
        gVar2.m = hVar;
        gVar2.k = cVar;
        gVar2.j = et2Var;
        gVar2.p0();
        gVar2.n0();
        boolean zIsIncognito = gVar2.h.isIncognito();
        gVar2.z.getClass();
        if (!zIsIncognito) {
            LocationBarLayout locationBarLayout = gVar2.f;
            if (locationBarLayout instanceof LocationBarPhone) {
                locationBarLayout.setClipToPadding(z3);
            }
        }
        this.k.C(this.i);
        this.k.C(this.h);
        View viewFindViewById = this.e.findViewById(R$id.delete_button);
        this.m = viewFindViewById;
        g gVar3 = this.k;
        Objects.requireNonNull(gVar3);
        viewFindViewById.setOnClickListener(new vt(15, gVar3));
        View viewFindViewById2 = this.e.findViewById(R$id.mic_button);
        this.n = viewFindViewById2;
        g gVar4 = this.k;
        Objects.requireNonNull(gVar4);
        viewFindViewById2.setOnClickListener(new gs(5, gVar4));
        View viewFindViewById3 = this.e.findViewById(R$id.lens_camera_button);
        this.o = viewFindViewById3;
        g gVar5 = this.k;
        Objects.requireNonNull(gVar5);
        viewFindViewById3.setOnClickListener(new ie0(14, gVar5));
        this.l.setOnKeyListener(this.k);
        this.h.i.k.add(this.i);
        this.h.i.e.p(j.i, this.p.b(new ko0(21, this)));
        context.registerComponentCallbacks(this.k);
        this.e.a(this.i, this.h, this.j, fVar, jg2Var);
        this.s = yp.c(context, R$dimen.omnibox_suggestion_dropdown_bg_elevation);
        this.t = context.getColor(bq.d.a() ? R$color.default_bg_color_dark_elev_1_gm3_baseline : R$color.omnibox_dropdown_bg_incognito);
        this.u = yp.c(context, R$dimen.omnibox_suggestion_bg_elevation);
        this.v = context.getColor(R$color.omnibox_suggestion_bg_incognito);
        zp1Var.p(new b(rs1Var, zp1Var));
        LocationBarLayout locationBarLayout2 = this.e;
        if (locationBarLayout2 instanceof LocationBarPhone) {
            this.f = new d((LocationBarPhone) view, this.j);
        } else if (locationBarLayout2 instanceof LocationBarTablet) {
            this.f = new e();
        }
    }

    @Override // defpackage.hk1
    public final void G0() {
        this.g.e(this);
        this.g = null;
        g gVar = this.k;
        gVar.D = true;
        gVar.l = new qr1();
        gVar.r.h(new td2(6, gVar));
        LocationBarLayout locationBarLayout = gVar.f;
        locationBarLayout.c();
        Profile profile = gVar.n.get();
        if (profile != null && gVar.D) {
            qr1 qr1Var = gVar.l;
            N.MXz11HdP(qr1Var.a, qr1Var, profile);
        }
        gVar.I();
        ArrayList arrayList = gVar.q;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            locationBarLayout.post((Runnable) it.next());
        }
        arrayList.clear();
        gVar.n0();
        h hVar = this.h;
        UrlBar urlBar = hVar.f;
        urlBar.p = true;
        hVar.o = pr1.e(urlBar.getContext());
        org.chromium.chrome.browser.omnibox.suggestions.d dVar = this.i.h;
        dVar.v = true;
        sk skVar = bq.a;
        dVar.z = N.M09VlOh_("ClearOmniboxFocusAfterNavigation");
        dVar.A = N.M6bsIDpc("ClearOmniboxFocusAfterNavigation", "clear_focus_asynchronously", false);
        he0 he0Var = dVar.n;
        he0Var.f = pr1.e(he0Var.a);
        ge0 ge0Var = dVar.m;
        ge0Var.e.a();
        int i = 0;
        while (true) {
            ArrayList arrayList2 = ge0Var.a;
            if (i >= arrayList2.size()) {
                break;
            }
            ((dw2) arrayList2.get(i)).a();
            i++;
        }
        dVar.l();
        et2 et2Var = this.j;
        wt2 wt2Var = et2Var.f;
        wt2Var.f(0);
        wt2Var.setStatusClickListener(et2Var);
        wt2Var.g();
        nw2<zf1> nw2Var = wt2Var.i;
        if (nw2Var != null && nw2Var.get() != null) {
            nw2Var.get().k = wt2Var;
        }
        this.r = true;
    }

    public final void a(String str) {
        this.h.g(rj3.b(str), 0, 1);
        this.k.n0();
    }

    @Override // defpackage.v91
    public final rj3 b() {
        return this.h.i.h;
    }

    public final void c(boolean z) {
        i iVar = this.h.i;
        iVar.getClass();
        PropertyModel.n nVar = j.b;
        PropertyModel propertyModel = iVar.e;
        propertyModel.l(nVar, z);
        if (z) {
            propertyModel.l(j.f, iVar.g);
        }
    }

    @Override // defpackage.v91
    public final so3 d() {
        return this.k.d();
    }

    @Override // defpackage.v91
    public final void destroy() {
        a aVar = this.f;
        if (aVar != null) {
            aVar.destroy();
            this.f = null;
        }
        this.l.setOnKeyListener(null);
        this.l = null;
        this.m.setOnClickListener(null);
        this.m = null;
        this.n.setOnClickListener(null);
        this.n = null;
        this.o.setOnClickListener(null);
        this.o = null;
        this.k.H(this.h);
        h hVar = this.h;
        i iVar = hVar.i;
        iVar.k.clear();
        iVar.l.clear();
        iVar.f = new ps(7);
        hVar.i = null;
        hVar.j.h(hVar);
        hVar.f.removeCallbacks(hVar.l);
        hVar.f.removeCallbacks(hVar.m);
        UrlBar urlBar = hVar.f;
        urlBar.setAllowFocus(false);
        urlBar.u = null;
        urlBar.setOnFocusChangeListener(null);
        urlBar.x = null;
        urlBar.v = null;
        urlBar.w = null;
        hVar.f = null;
        hVar.n = null;
        this.h = null;
        this.e.getContext().unregisterComponentCallbacks(this.k);
        this.k.H(this.i);
        org.chromium.chrome.browser.omnibox.suggestions.c cVar = this.i;
        cVar.f.d(cVar.g);
        org.chromium.chrome.browser.omnibox.suggestions.d dVar = cVar.h;
        if (dVar.w != null) {
            dVar.n(false);
            dVar.w.removeOnSuggestionsReceivedListener(dVar);
        }
        dVar.j.removeCallbacks(dVar.r);
        ge0 ge0Var = dVar.m;
        ax0 ax0Var = ge0Var.g;
        if (ax0Var != null) {
            ax0Var.b();
            ge0Var.g = null;
        }
        LargeIconBridge largeIconBridge = ge0Var.i;
        if (largeIconBridge != null) {
            largeIconBridge.a();
            ge0Var.i = null;
        }
        OmniboxSuggestionsDropdown omniboxSuggestionsDropdown = cVar.j;
        if (omniboxSuggestionsDropdown != null) {
            omniboxSuggestionsDropdown.getRecycledViewPool().a();
            omniboxSuggestionsDropdown.V0 = null;
            omniboxSuggestionsDropdown.W0 = null;
            omniboxSuggestionsDropdown.X0 = null;
            omniboxSuggestionsDropdown.Y0 = null;
            cVar.j = null;
        }
        this.i = null;
        et2 et2Var = this.j;
        wt2 wt2Var = et2Var.f;
        wt2Var.C.removeCallbacksAndMessages(null);
        wt2Var.B.e.c(wt2Var);
        wt2Var.D.removeCallbacksAndMessages(null);
        nw2<zf1> nw2Var = wt2Var.i;
        if (nw2Var != null && nw2Var.get() != null) {
            nw2Var.get().k = null;
        }
        qs1<TemplateUrlService> qs1Var = wt2Var.g;
        if (qs1Var.f()) {
            qs1Var.get().i(wt2Var);
        }
        et2Var.j.o(et2Var);
        et2Var.j = null;
        this.j = null;
        LocationBarLayout locationBarLayout = this.e;
        if (locationBarLayout.l != null) {
            locationBarLayout.l = null;
        }
        locationBarLayout.k = null;
        this.e = null;
        this.p.a();
        this.p = null;
        g gVar = this.k;
        rs1<u8> rs1Var = gVar.i;
        if (rs1Var.get() != null) {
            u8 u8Var = rs1Var.get();
            u8Var.h.i(u8Var);
            u8Var.j.c(u8Var);
            u8Var.k.j(u8Var);
        }
        qs1<TemplateUrlService> qs1Var2 = gVar.r;
        if (qs1Var2.f()) {
            qs1Var2.get().i(gVar);
        }
        gVar.j = null;
        gVar.k = null;
        gVar.m = null;
        gVar.g.f.c(gVar);
        gVar.g = null;
        gVar.h.o(gVar);
        gVar.q.clear();
        gVar.x.clear();
        this.k = null;
        this.q = true;
    }

    @Override // defpackage.v91
    public final View e() {
        return this.e;
    }

    @Override // defpackage.v91
    public final void g() {
        this.h.f.selectAll();
    }

    @Override // defpackage.v91
    public final sr1 i() {
        return this.k;
    }

    @Override // defpackage.v91
    public final void k() {
        if (this.r) {
            this.i.a();
        }
    }

    @Override // defpackage.v91
    public final void m(boolean z) {
        this.k.getClass();
    }

    @Override // defpackage.v91
    public final void n() {
        this.k.Z();
    }

    @Override // defpackage.v91
    public final View o() {
        return this.e.getSecurityIconView();
    }

    @Override // defpackage.v91
    public final void p() {
        this.k.I();
    }

    @Override // defpackage.v91
    public final void s() {
        this.h.f.sendAccessibilityEvent(8);
    }
}
