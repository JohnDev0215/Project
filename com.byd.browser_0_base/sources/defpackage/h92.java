package defpackage;

import android.os.SystemClock;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadCommittedDetails;
import org.chromium.content_public.browser.NavigationController;
import org.chromium.content_public.browser.NavigationEntry;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ReaderModeManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class h92 extends rq3 {
    public boolean f;
    public int g;
    public final /* synthetic */ i92 h;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h92(i92 i92Var, WebContents webContents) {
        super(webContents);
        this.h = i92Var;
    }

    @Override // defpackage.rq3
    public final void didFinishNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
        if (!navigationHandle.g || navigationHandle.c) {
            return;
        }
        if (this.f) {
            this.f = false;
            NavigationController navigationControllerM = this.e.get().m();
            if (navigationControllerM.d(this.g) != null) {
                navigationControllerM.p(this.g);
            }
        }
        i92 i92Var = this.h;
        if (i92Var.o) {
            return;
        }
        i92Var.h = 0;
        GURL gurl = i92Var.e;
        if (gurl == null || !navigationHandle.e.equals(a8.D(gurl))) {
            i92Var.h = 1;
            i92Var.f = false;
        }
        i92Var.e = null;
        if (i92Var.h == 0) {
            i92Var.B1();
        }
    }

    @Override // defpackage.rq3
    public final void didStartNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
        if (navigationHandle.c) {
            return;
        }
        NavigationController navigationControllerM = this.e.get().m();
        int iJ = navigationControllerM.j();
        NavigationEntry navigationEntryD = navigationControllerM.d(iJ);
        if (navigationEntryD != null && a8.M(navigationEntryD.b)) {
            this.f = true;
            this.g = iJ;
        }
        i92 i92Var = this.h;
        if (i92Var.o) {
            return;
        }
        GURL gurl = navigationHandle.e;
        i92Var.j = gurl;
        if (a8.M(gurl)) {
            i92Var.h = 2;
            i92Var.e = navigationHandle.e;
        }
    }

    @Override // defpackage.rq3
    public final void navigationEntryCommitted(LoadCommittedDetails loadCommittedDetails) {
        i92 i92Var = this.h;
        if (i92Var.o) {
            return;
        }
        i92Var.i = false;
        i92Var.r = false;
        Tab tab = i92Var.p;
        if (tab != null && !tab.isNativePage() && !tab.r()) {
            oj2.O("DomDistiller.ReaderShownForPageLoad", false);
        }
        i92Var.k = false;
        if (tab == null || a8.M(tab.getUrl()) || !i92Var.l) {
            return;
        }
        i92Var.l = false;
        oj2.X(SystemClock.elapsedRealtime() - i92Var.m, "DomDistiller.Time.ViewingReaderModePage");
    }
}
