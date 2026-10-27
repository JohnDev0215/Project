package org.chromium.content.browser.webcontents;

import J.N;
import defpackage.C0173a;
import defpackage.ct0;
import defpackage.rq3;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;
import org.chromium.content_public.browser.LoadCommittedDetails;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class WebContentsObserverProxy extends rq3 {
    public long f;
    public final b<rq3> g;
    public int h;

    public WebContentsObserverProxy(WebContentsImpl webContentsImpl) {
        Object obj = ThreadUtils.a;
        this.f = N.MTpUzW91(this, webContentsImpl);
        this.g = new b<>();
        this.h = 0;
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void destroy() {
        Object obj = ThreadUtils.a;
        b<rq3> bVar = this.g;
        b.a aVarD = bVar.d();
        while (aVarD.hasNext()) {
            ((rq3) aVarD.next()).destroy();
        }
        if (!bVar.isEmpty()) {
            aVarD.c();
            String string = "These observers were not removed: ";
            while (aVarD.hasNext()) {
                StringBuilder sbJ = C0173a.j(string);
                sbJ.append(((rq3) aVarD.next()).getClass().getName());
                sbJ.append(" ");
                string = sbJ.toString();
            }
        }
        bVar.clear();
        long j = this.f;
        if (j != 0) {
            N.M7giG0Ri(j, this);
            this.f = 0L;
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void didChangeThemeColor() {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).didChangeThemeColor();
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void didChangeVisibleSecurityState() {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).didChangeVisibleSecurityState();
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void didFailLoad(boolean z, int i, GURL gurl, int i2) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).didFailLoad(z, i, gurl, i2);
        }
    }

    @CalledByNative
    public final void didFinishLoadInPrimaryMainFrame(int i, int i2, GURL gurl, boolean z, int i3) {
        f(new ct0(i, i2), gurl, z, i3);
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void didFinishNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).didFinishNavigationInPrimaryMainFrame(navigationHandle);
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void didFirstVisuallyNonEmptyPaint() {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).didFirstVisuallyNonEmptyPaint();
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void didRedirectNavigation(NavigationHandle navigationHandle) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).didRedirectNavigation(navigationHandle);
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void didStartLoading(GURL gurl) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).didStartLoading(gurl);
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void didStartNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).didStartNavigationInPrimaryMainFrame(navigationHandle);
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void didStopLoading(GURL gurl, boolean z) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).didStopLoading(gurl, z);
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void didToggleFullscreenModeForTab(boolean z, boolean z2) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).didToggleFullscreenModeForTab(z, z2);
        }
    }

    @CalledByNative
    public final void documentLoadedInPrimaryMainFrame(int i, int i2, int i3) {
        g(new ct0(i, i2), i3);
    }

    @Override // defpackage.rq3
    public final void f(ct0 ct0Var, GURL gurl, boolean z, int i) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).f(ct0Var, gurl, z, i);
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void frameReceivedUserActivation() {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).frameReceivedUserActivation();
        }
    }

    @Override // defpackage.rq3
    public final void g(ct0 ct0Var, int i) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).g(ct0Var, i);
        }
    }

    @Override // defpackage.rq3
    public final void h(WindowAndroid windowAndroid) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).h(windowAndroid);
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void hasEffectivelyFullscreenVideoChange(boolean z) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).hasEffectivelyFullscreenVideoChange(z);
        }
    }

    @Override // defpackage.rq3
    public final void i(ct0 ct0Var) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).i(ct0Var);
        }
    }

    @Override // defpackage.rq3
    public final void j(ct0 ct0Var) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).j(ct0Var);
        }
    }

    public final void k() {
        this.h--;
    }

    public final void l() {
        this.h++;
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void loadProgressChanged(float f) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).loadProgressChanged(f);
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void mediaStartedPlaying() {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).mediaStartedPlaying();
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void mediaStoppedPlaying() {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).mediaStoppedPlaying();
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void navigationEntriesChanged() {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).navigationEntriesChanged();
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void navigationEntriesDeleted() {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).navigationEntriesDeleted();
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void navigationEntryCommitted(LoadCommittedDetails loadCommittedDetails) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).navigationEntryCommitted(loadCommittedDetails);
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void onWebContentsFocused() {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).onWebContentsFocused();
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void onWebContentsLostFocus() {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).onWebContentsLostFocus();
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void primaryMainDocumentElementAvailable() {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).primaryMainDocumentElementAvailable();
        }
    }

    @CalledByNative
    public void renderFrameCreated(int i, int i2) {
        i(new ct0(i, i2));
    }

    @CalledByNative
    public void renderFrameDeleted(int i, int i2) {
        j(new ct0(i, i2));
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void renderProcessGone() {
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                return;
            } else {
                ((rq3) aVar.next()).renderProcessGone();
            }
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void titleWasSet(String str) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).titleWasSet(str);
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void viewportFitChanged(int i) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).viewportFitChanged(i);
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void virtualKeyboardModeChanged(int i) {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).virtualKeyboardModeChanged(i);
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void wasHidden() {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).wasHidden();
        }
    }

    @Override // defpackage.rq3
    @CalledByNative
    public void wasShown() {
        l();
        Iterator<rq3> it = this.g.iterator();
        while (true) {
            b.a aVar = (b.a) it;
            if (!aVar.hasNext()) {
                k();
                return;
            }
            ((rq3) aVar.next()).wasShown();
        }
    }
}
