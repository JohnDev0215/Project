package defpackage;

import defpackage.z2;
import java.util.Iterator;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.chrome.browser.ntp.h;
import org.chromium.chrome.browser.omnibox.f;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.chrome.browser.tab.TabImpl;
import org.chromium.chrome.browser.toolbar.LocationBarModel;
import org.chromium.chrome.browser.toolbar.g;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ToolbarManager.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qc3 extends z2.b {
    public final /* synthetic */ g h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public qc3(g gVar, z2 z2Var) {
        super(z2Var, false);
        this.h = gVar;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void I0(TabImpl tabImpl) {
        g gVar = this.h;
        gVar.H();
        gVar.D();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void K(TabImpl tabImpl, boolean z) {
        if (z) {
            this.h.H();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void K0(Tab tab) {
        g gVar = this.h;
        if (tab == gVar.z.d()) {
            gVar.D();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void L0(TabImpl tabImpl, boolean z) {
        if (z) {
            this.h.H();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void Q(Tab tab, boolean z, boolean z2) {
        if (z) {
            g gVar = this.h;
            gVar.z.z();
            gVar.z.x();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void R0(TabImpl tabImpl) {
        g gVar = this.h;
        if (gVar.z.d() == null) {
            return;
        }
        LocationBarModel locationBarModel = gVar.z;
        locationBarModel.x();
        locationBarModel.z();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void a1(Tab tab, GURL gurl) {
        TraceEvent traceEventH0 = TraceEvent.H0("ToolbarManager::onPageLoadFinished", null);
        try {
            z1(gurl);
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g0(int i, Tab tab, LoadUrlParams loadUrlParams) {
        g gVar = this.h;
        h hVarJ = gVar.j();
        if (hVarJ == null || bk3.k(loadUrlParams.a) || i == 0) {
            return;
        }
        hVarJ.u.setUrlFocusAnimationsDisabled(true);
        gVar.t();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void l0(int i, Tab tab) {
        if (tab.getUrl().k()) {
            return;
        }
        this.h.l.setReadyForBitmapCapture(true);
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void o1(TabImpl tabImpl, NavigationHandle navigationHandle) {
        h hVarJ;
        NavigationController navigationControllerM;
        NavigationEntry navigationEntryT;
        boolean z = navigationHandle.g;
        g gVar = this.h;
        if (z && !navigationHandle.c) {
            gVar.k.a.r();
        }
        if (navigationHandle.l != 0) {
            WebContents webContents = tabImpl.g;
            if (((webContents == null || (navigationControllerM = webContents.m()) == null || (navigationEntryT = navigationControllerM.t()) == null) ? false : !bk3.l(navigationEntryT.b)) || (hVarJ = gVar.j()) == null) {
                return;
            }
            hVarJ.u.setUrlFocusAnimationsDisabled(false);
            gVar.t();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void r1(Tab tab) {
        this.h.z.y();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void u(Tab tab) {
        g gVar = this.h;
        gVar.f();
        gVar.k.a.v();
        gVar.q();
        gVar.z.x();
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void x0(TabImpl tabImpl) {
        g gVar = this.h;
        gVar.H();
        gVar.l.setReadyForBitmapCapture(true);
    }

    @Override // z2.b
    public final void y1(Tab tab) {
        g gVar = this.h;
        gVar.s();
        if (tab == null) {
            gVar.z.z();
            return;
        }
        gVar.u(tab);
        gVar.t();
        z1(tab.getUrl());
    }

    public final void z1(GURL gurl) {
        if (gurl == null) {
            return;
        }
        Iterator<f.a> it = this.h.z.u.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((f.a) aVar.next()).h();
            }
        }
    }
}
