package org.chromium.chrome.browser.keyboard_accessory;

import J.N;
import defpackage.bl;
import defpackage.bq;
import defpackage.l41;
import defpackage.m41;
import defpackage.sk;
import defpackage.ub1;
import defpackage.wb1;
import defpackage.x62;
import defpackage.xr;
import defpackage.zp1;
import java.util.Iterator;
import org.chromium.base.b;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.browser.keyboard_accessory.a;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.AsyncViewStub;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ManualFillingCoordinator.java */
/* JADX INFO: loaded from: classes.dex */
public final class b implements a {
    public final c e = new c();
    public final org.chromium.base.b<a.InterfaceC0088a> f = new org.chromium.base.b<>();

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final boolean J(ub1 ub1Var) {
        return this.f.a(ub1Var);
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void O0(AsyncViewStub asyncViewStub, AsyncViewStub asyncViewStub2) {
        if (asyncViewStub2 == null || asyncViewStub == null) {
            return;
        }
        sk skVar = bq.a;
        asyncViewStub2.setLayoutResource(N.M09VlOh_("AutofillKeyboardAccessory") ? R$layout.keyboard_accessory_modern : R$layout.keyboard_accessory);
        asyncViewStub.setLayoutResource(R$layout.keyboard_accessory_sheet);
        asyncViewStub2.setShouldInflateOnBackgroundThread(true);
        asyncViewStub.setShouldInflateOnBackgroundThread(true);
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final boolean Q0(ub1 ub1Var) {
        return this.f.a(ub1Var);
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final boolean R() {
        this.e.getClass();
        return false;
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void W0(WebContents webContents, a.b bVar) {
        this.e.getClass();
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final boolean a() {
        return this.e.a();
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void a0(WebContents webContents, int i, x62<l41> x62Var) {
        this.e.getClass();
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void b() {
        c cVar = this.e;
        PropertyModel propertyModel = cVar.e;
        propertyModel.l(wb1.a, false);
        if (cVar.z1()) {
            propertyModel.n(wb1.c, 4);
        }
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void b0() {
        c cVar = this.e;
        boolean zY1 = cVar.y1(3);
        PropertyModel.p pVar = wb1.c;
        PropertyModel propertyModel = cVar.e;
        if (zY1 || cVar.y1(1)) {
            propertyModel.n(pVar, 11);
        } else if (cVar.y1(11)) {
            propertyModel.n(pVar, 13);
        }
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void c(boolean z) {
        this.e.getClass();
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void d0() {
        this.e.getClass();
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void destroy() {
        Iterator<a.InterfaceC0088a> it = this.f.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                break;
            } else {
                ((a.InterfaceC0088a) aVar.next()).onDestroy();
            }
        }
        c cVar = this.e;
        if (cVar.z1()) {
            cVar.A1();
            throw null;
        }
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void dismiss() {
        c cVar = this.e;
        if (cVar.z1()) {
            cVar.A1();
        }
    }

    @Override // defpackage.vb
    public final zp1<Boolean> getHandleBackPressChangedSupplier() {
        return this.e.h;
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void m0() {
        if (this.e.z1()) {
            throw null;
        }
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void m1() {
        this.e.getClass();
    }

    @Override // defpackage.vb
    public final int o() {
        return this.e.o();
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void onPause() {
        this.e.A1();
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void onResume() {
        c cVar = this.e;
        if (cVar.z1()) {
            cVar.A1();
            cVar.B1();
        }
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final int r0() {
        c cVar = this.e;
        if (cVar == null || !cVar.e.k(wb1.a)) {
            return 0;
        }
        throw null;
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void s1(WebContents webContents, x62<m41[]> x62Var) {
        c cVar = this.e;
        if (cVar.z1()) {
            d dVarA = cVar.g.a(webContents);
            dVarA.d = new bl<>(x62Var, new m41[0], new xr(26, dVarA));
            throw null;
        }
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final zp1<Integer> t0() {
        return this.e.f;
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void y() {
        this.e.getClass();
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.a
    public final void z() {
        this.e.getClass();
        throw null;
    }
}
