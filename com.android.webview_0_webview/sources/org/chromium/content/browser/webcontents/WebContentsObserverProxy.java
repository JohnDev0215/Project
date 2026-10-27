package org.chromium.content.browser.webcontents;

import J.N;
import defpackage.AbstractC0929iE;
import defpackage.Ws;
import defpackage.Xs;
import defpackage.Ys;
import org.chromium.base.ThreadUtils;
import org.chromium.content_public.browser.NavigationHandle;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WebContentsObserverProxy extends AbstractC0929iE {
    public long b;
    public final Ys c;
    public final Xs d;

    public WebContentsObserverProxy(WebContentsImpl webContentsImpl) {
        ThreadUtils.b();
        this.b = N.MTpUzW91(this, webContentsImpl);
        Ys ys = new Ys();
        this.c = ys;
        this.d = ys.h();
    }

    @Override // defpackage.AbstractC0929iE
    public void destroy() {
        ThreadUtils.b();
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).destroy();
        }
        this.c.clear();
        long j = this.b;
        if (j != 0) {
            N.M7giG0Ri(j, this);
            this.b = 0L;
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void didAttachInterstitialPage() {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).didAttachInterstitialPage();
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void didChangeThemeColor() {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).didChangeThemeColor();
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void didChangeVisibleSecurityState() {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).didChangeVisibleSecurityState();
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void didDetachInterstitialPage() {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).didDetachInterstitialPage();
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void didFailLoad(boolean z, int i, String str) {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).didFailLoad(z, i, str);
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void didFinishLoad(long j, String str, boolean z) {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).didFinishLoad(j, str, z);
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void didFinishNavigation(NavigationHandle navigationHandle) {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).didFinishNavigation(navigationHandle);
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void didFirstVisuallyNonEmptyPaint() {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).didFirstVisuallyNonEmptyPaint();
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void didRedirectNavigation(NavigationHandle navigationHandle) {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).didRedirectNavigation(navigationHandle);
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void didStartLoading(String str) {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).didStartLoading(str);
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void didStartNavigation(NavigationHandle navigationHandle) {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).didStartNavigation(navigationHandle);
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void didStopLoading(String str) {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).didStopLoading(str);
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void documentAvailableInMainFrame() {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).documentAvailableInMainFrame();
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void documentLoadedInFrame(long j, boolean z) {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).documentLoadedInFrame(j, z);
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void hasEffectivelyFullscreenVideoChange(boolean z) {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).hasEffectivelyFullscreenVideoChange(z);
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void loadProgressChanged(float f) {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).loadProgressChanged(f);
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void navigationEntriesChanged() {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).navigationEntriesChanged();
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void navigationEntriesDeleted() {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).navigationEntriesDeleted();
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void navigationEntryCommitted() {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).navigationEntryCommitted();
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void onWebContentsFocused() {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).onWebContentsFocused();
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void onWebContentsLostFocus() {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).onWebContentsLostFocus();
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void renderProcessGone(boolean z) {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).renderProcessGone(z);
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void renderViewReady() {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).renderViewReady();
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void titleWasSet(String str) {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).titleWasSet(str);
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void viewportFitChanged(int i) {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).viewportFitChanged(i);
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void wasHidden() {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).wasHidden();
        }
    }

    @Override // defpackage.AbstractC0929iE
    public void wasShown() {
        ((Ws) this.d).b();
        while (this.d.hasNext()) {
            ((AbstractC0929iE) this.d.next()).wasShown();
        }
    }
}
