package defpackage;

import java.lang.ref.WeakReference;
import org.chromium.content_public.browser.LoadCommittedDetails;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: WebContentsObserver.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class rq3 {
    public WeakReference<WebContents> e;

    public rq3() {
    }

    public rq3(WebContents webContents) {
        this.e = new WeakReference<>(webContents);
        webContents.C(this);
    }

    public void destroy() {
        WeakReference<WebContents> weakReference = this.e;
        if (weakReference == null) {
            return;
        }
        WebContents webContents = weakReference.get();
        this.e = null;
        if (webContents == null) {
            return;
        }
        webContents.z(this);
    }

    public void didChangeThemeColor() {
    }

    public void didChangeVisibleSecurityState() {
    }

    public void didFirstVisuallyNonEmptyPaint() {
    }

    public void frameReceivedUserActivation() {
    }

    public void mediaStartedPlaying() {
    }

    public void mediaStoppedPlaying() {
    }

    public void navigationEntriesChanged() {
    }

    public void navigationEntriesDeleted() {
    }

    public void onWebContentsFocused() {
    }

    public void onWebContentsLostFocus() {
    }

    public void primaryMainDocumentElementAvailable() {
    }

    public void renderProcessGone() {
    }

    public void wasHidden() {
    }

    public void wasShown() {
    }

    public void didFinishNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
    }

    public void didRedirectNavigation(NavigationHandle navigationHandle) {
    }

    public void didStartLoading(GURL gurl) {
    }

    public void didStartNavigationInPrimaryMainFrame(NavigationHandle navigationHandle) {
    }

    public void h(WindowAndroid windowAndroid) {
    }

    public void hasEffectivelyFullscreenVideoChange(boolean z) {
    }

    public void i(ct0 ct0Var) {
    }

    public void j(ct0 ct0Var) {
    }

    public void loadProgressChanged(float f) {
    }

    public void navigationEntryCommitted(LoadCommittedDetails loadCommittedDetails) {
    }

    public void titleWasSet(String str) {
    }

    public void viewportFitChanged(int i) {
    }

    public void virtualKeyboardModeChanged(int i) {
    }

    public void didStopLoading(GURL gurl, boolean z) {
    }

    public void didToggleFullscreenModeForTab(boolean z, boolean z2) {
    }

    public void g(ct0 ct0Var, int i) {
    }

    public void didFailLoad(boolean z, int i, GURL gurl, int i2) {
    }

    public void f(ct0 ct0Var, GURL gurl, boolean z, int i) {
    }
}
