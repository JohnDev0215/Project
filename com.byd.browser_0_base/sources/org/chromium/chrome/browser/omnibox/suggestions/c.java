package org.chromium.chrome.browser.omnibox.suggestions;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import defpackage.ag;
import defpackage.b50;
import defpackage.bi1;
import defpackage.bw2;
import defpackage.ca;
import defpackage.ce;
import defpackage.dm0;
import defpackage.e2;
import defpackage.f2;
import defpackage.ga;
import defpackage.ge0;
import defpackage.he0;
import defpackage.hs;
import defpackage.hu;
import defpackage.i31;
import defpackage.im2;
import defpackage.in3;
import defpackage.jf0;
import defpackage.lj1;
import defpackage.mk0;
import defpackage.nw2;
import defpackage.o63;
import defpackage.om3;
import defpackage.pr1;
import defpackage.ps;
import defpackage.ql0;
import defpackage.qm0;
import defpackage.sj3;
import defpackage.td2;
import defpackage.ur1;
import defpackage.vh0;
import defpackage.vj3;
import defpackage.vr1;
import defpackage.wi1;
import defpackage.y5;
import defpackage.ya1;
import defpackage.yo;
import defpackage.yq2;
import defpackage.zp1;
import java.util.WeakHashMap;
import org.chromium.chrome.browser.omnibox.LocationBarLayout;
import org.chromium.chrome.browser.omnibox.UrlBar;
import org.chromium.chrome.browser.omnibox.suggestions.dividerline.DividerLineProcessor;
import org.chromium.chrome.browser.omnibox.suggestions.header.HeaderProcessor;
import org.chromium.chrome.browser.omnibox.suggestions.l;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AutocompleteCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c implements vj3, UrlBar.e {
    public final ViewGroup e;
    public final zp1<Profile> f;
    public final lj1 g;
    public final d h;
    public final nw2<bi1> i;
    public OmniboxSuggestionsDropdown j;
    public final org.chromium.base.b<vr1> k;

    public c(LocationBarLayout locationBarLayout, ca caVar, ga gaVar, ur1 ur1Var, org.chromium.chrome.browser.omnibox.h hVar, zp1 zp1Var, nw2 nw2Var, nw2 nw2Var2, org.chromium.chrome.browser.omnibox.f fVar, zp1 zp1Var2, ps psVar, nw2 nw2Var3, ce.a aVar, i31 i31Var, f2 f2Var, vr1 vr1Var) {
        org.chromium.base.b<vr1> bVar = new org.chromium.base.b<>();
        this.k = bVar;
        this.e = locationBarLayout;
        this.i = zp1Var;
        Context context = locationBarLayout.getContext();
        PropertyModel propertyModel = new PropertyModel(i.j);
        ya1 ya1Var = new ya1();
        propertyModel.p(i.b, ur1Var);
        PropertyModel.n nVar = i.a;
        propertyModel.l(nVar, false);
        propertyModel.p(i.c, ya1Var);
        d dVar = new d(context, caVar, gaVar, hVar, propertyModel, new Handler(), zp1Var, nw2Var, nw2Var2, fVar, psVar, nw2Var3, aVar, i31Var, f2Var);
        this.h = dVar;
        ga gaVar2 = dVar.g;
        sj3 sj3Var = dVar.h;
        ge0 ge0Var = dVar.m;
        ge0Var.getClass();
        yo yoVar = new yo(11, ge0Var);
        int i = 8;
        hs hsVar = new hs(i, ge0Var);
        yq2 yq2Var = new yq2(15, ge0Var);
        Context context2 = dVar.e;
        ge0Var.h = new mk0(context2, hsVar);
        if (pr1.e(context2) && !pr1.d()) {
            ge0Var.d = new DividerLineProcessor(context2);
        }
        ge0Var.e = new HeaderProcessor(context2);
        ge0Var.b(new jf0(context2, dVar, gaVar2, ge0Var.h, ge0Var.b, yq2Var));
        ge0Var.b(new y5(context2, dVar, ge0Var.c, sj3Var, yoVar));
        ge0Var.b(new hu(context2, dVar, ge0Var.h));
        e2 e2Var = ge0Var.c;
        ge0Var.b(new vh0(context2, dVar, e2Var, yoVar));
        ge0Var.b(new o63(context2, dVar, e2Var));
        ge0Var.b(new wi1(context2, dVar, ge0Var.h));
        ge0Var.b(new ce(context2, dVar, ge0Var.c, sj3Var, ge0Var.h, ge0Var.j));
        bVar.a(vr1Var);
        bVar.a(dVar);
        propertyModel.p(i.f, dVar);
        propertyModel.p(i.g, new td2(i, dVar));
        propertyModel.p(i.h, new ql0(21, this));
        propertyModel.p(i.i, new qm0(16, this));
        b bVar2 = new b(this, context, ya1Var);
        dm0 dm0Var = new dm0(18, this);
        l.a aVar2 = bVar2.b;
        if (aVar2 != null) {
            dm0Var.onResult(aVar2);
        } else {
            bVar2.a.add(dm0Var);
        }
        new org.chromium.ui.modelutil.b(propertyModel, new im2(nVar), bVar2, new ag(6));
        this.f = zp1Var2;
        lj1 lj1Var = new lj1(i, this);
        this.g = lj1Var;
        zp1Var2.p(lj1Var);
        c();
    }

    @Override // defpackage.vj3
    public final void J0(boolean z) {
        d dVar = this.h;
        dVar.getClass();
        if (!pr1.c()) {
            dVar.C = z ? 2 : 0;
            dVar.o();
            return;
        }
        PropertyModel.n nVar = i.a;
        PropertyModel propertyModel = dVar.i;
        boolean zK = propertyModel.k(nVar);
        propertyModel.l(nVar, z);
        if (!z || zK) {
            return;
        }
        dVar.L = true;
    }

    public final void a() {
        d dVar = this.h;
        dVar.j(new b50(dVar.k.j(false, true), 4, dVar), -1L);
    }

    @Override // org.chromium.chrome.browser.omnibox.UrlBar.e
    public final void b(String str, String str2) {
        this.h.i(str, str2);
    }

    public final void c() {
        WeakHashMap<View, in3> weakHashMap = om3.a;
        int iC = om3.e.c(this.e);
        he0 he0Var = this.h.n;
        if (he0Var.c == iC) {
            return;
        }
        he0Var.c = iC;
        for (int i = 0; i < he0Var.e.size(); i++) {
            he0Var.e.get(i).b.n(bw2.b, iC);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x00a6  */
    @Override // defpackage.vj3
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void k(boolean r12) {
        /*
            Method dump skipped, instruction units count: 234
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.chrome.browser.omnibox.suggestions.c.k(boolean):void");
    }
}
