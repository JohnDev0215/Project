package org.chromium.chrome.browser.toolbar.top;

import defpackage.cs2;
import defpackage.e23;
import org.chromium.chrome.browser.tabmodel.TabModel;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: StartSurfaceToolbarMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e implements e23 {
    public final /* synthetic */ g e;

    public e(g gVar) {
        this.e = gVar;
    }

    @Override // defpackage.e23
    public final void g() {
        g gVar = this.e;
        if (gVar.l.getAsBoolean()) {
            PropertyModel.r<org.chromium.chrome.browser.toolbar.a> rVar = cs2.r;
            org.chromium.chrome.browser.toolbar.a aVar = gVar.w;
            PropertyModel propertyModel = gVar.e;
            propertyModel.p(rVar, aVar);
            propertyModel.p(cs2.s, gVar.v);
        }
    }

    @Override // defpackage.e23
    public final void k(TabModel tabModel) {
        g gVar = this.e;
        gVar.e.l(cs2.i, gVar.v.r());
        gVar.d(gVar.i.get());
    }
}
