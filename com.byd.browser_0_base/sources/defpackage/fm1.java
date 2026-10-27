package defpackage;

import org.chromium.chrome.browser.ntp.NewTabPageLayout;
import org.chromium.chrome.browser.ntp.h;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;

/* JADX INFO: compiled from: NewTabPage.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fm1 extends nh0 {
    public final /* synthetic */ h e;

    public fm1(h hVar) {
        this.e = hVar;
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void S0(Tab tab, boolean z) {
        h hVar = this.e;
        if (z) {
            hVar.i.a(3);
        } else {
            hVar.i.b(3);
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void T0(int i, Tab tab) {
        h hVar = this.e;
        if (hVar.C) {
            hVar.w();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void g0(int i, Tab tab, LoadUrlParams loadUrlParams) {
        NewTabPageLayout newTabPageLayout = this.e.u;
        if (bk3.l(tab.getUrl())) {
            newTabPageLayout.getClass();
        } else {
            newTabPageLayout.getClass();
        }
    }

    @Override // defpackage.nh0, defpackage.n23
    public final void l0(int i, Tab tab) {
        h hVar = this.e;
        if (hVar.C) {
            h.s(hVar);
        }
        hVar.u.getClass();
    }
}
