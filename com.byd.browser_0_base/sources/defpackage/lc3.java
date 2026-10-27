package defpackage;

import defpackage.l61;
import org.chromium.chrome.browser.toolbar.LocationBarModel;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.chrome.browser.toolbar.top.ToolbarControlContainer;
import org.chromium.chrome.browser.toolbar.top.m;

/* JADX INFO: compiled from: ToolbarManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lc3 implements l61.a {
    public final /* synthetic */ g e;

    public lc3(g gVar) {
        this.e = gVar;
    }

    @Override // l61.a
    public final void f(int i) {
        if (i == 2 || i == 16) {
            g gVar = this.e;
            gVar.k.a.x();
            gVar.D();
            if (oj2.I(gVar.T)) {
                gVar.f();
                gVar.q();
            }
        }
    }

    @Override // l61.a
    public final void g(int i, boolean z) {
        this.e.E(i, z);
    }

    @Override // l61.a
    public final void h(int i) {
        if (i == 2) {
            this.e.k.a.x();
        }
    }

    @Override // l61.a
    public final void i(int i, boolean z, boolean z2) {
        if (i == 2 || i == 16) {
            g gVar = this.e;
            LocationBarModel locationBarModel = gVar.z;
            locationBarModel.q = false;
            locationBarModel.r = false;
            locationBarModel.y();
            locationBarModel.z();
            locationBarModel.w();
            locationBarModel.x();
            m mVar = gVar.k;
            mVar.r(false, z, z2);
            gVar.D();
            if (mVar.c(true)) {
                ((ToolbarControlContainer.b) gVar.l.getToolbarResourceAdapter()).g(null);
            }
        }
    }
}
