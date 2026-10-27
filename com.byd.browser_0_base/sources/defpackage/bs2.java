package defpackage;

import org.chromium.chrome.browser.toolbar.top.g;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: StartSurfaceToolbarMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bs2 extends vl {
    public final /* synthetic */ boolean f;
    public final /* synthetic */ g g;

    public bs2(g gVar, boolean z) {
        this.g = gVar;
        this.f = z;
    }

    @Override // defpackage.vl
    public final void b() {
        g gVar = this.g;
        gVar.getClass();
        PropertyModel.o oVar = cs2.w;
        PropertyModel propertyModel = gVar.e;
        propertyModel.m(oVar, 1.0f);
        PropertyModel.n nVar = cs2.h;
        boolean z = this.f;
        propertyModel.l(nVar, z);
        gVar.E.onResult(Boolean.valueOf(z));
        gVar.D = null;
    }
}
