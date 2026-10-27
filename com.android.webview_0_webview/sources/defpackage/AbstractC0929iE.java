package defpackage;

import java.lang.ref.WeakReference;
import org.chromium.content_public.browser.NavigationHandle;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: iE, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0929iE {
    public WeakReference a;

    public AbstractC0929iE(WebContents webContents) {
        this.a = new WeakReference(webContents);
        webContents.d(this);
    }

    public void destroy() {
        WeakReference weakReference = this.a;
        if (weakReference == null) {
            return;
        }
        WebContents webContents = (WebContents) weakReference.get();
        this.a = null;
        if (webContents == null) {
            return;
        }
        webContents.A(this);
    }

    public void didAttachInterstitialPage() {
    }

    public void didChangeThemeColor() {
    }

    public void didChangeVisibleSecurityState() {
    }

    public void didDetachInterstitialPage() {
    }

    public void didFailLoad(boolean z, int i, String str) {
    }

    public void didFinishLoad(long j, String str, boolean z) {
    }

    public void didFinishNavigation(NavigationHandle navigationHandle) {
    }

    public void didFirstVisuallyNonEmptyPaint() {
    }

    public void didRedirectNavigation(NavigationHandle navigationHandle) {
    }

    public void didStartLoading(String str) {
    }

    public void didStartNavigation(NavigationHandle navigationHandle) {
    }

    public void didStopLoading(String str) {
    }

    public void documentAvailableInMainFrame() {
    }

    public void documentLoadedInFrame(long j, boolean z) {
    }

    public void hasEffectivelyFullscreenVideoChange(boolean z) {
    }

    public void loadProgressChanged(float f) {
    }

    public void navigationEntriesChanged() {
    }

    public void navigationEntriesDeleted() {
    }

    public void navigationEntryCommitted() {
    }

    public void onWebContentsFocused() {
    }

    public void onWebContentsLostFocus() {
    }

    public void renderProcessGone(boolean z) {
    }

    public void renderViewReady() {
    }

    public void titleWasSet(String str) {
    }

    public void viewportFitChanged(int i) {
    }

    public void wasHidden() {
    }

    public void wasShown() {
    }

    public AbstractC0929iE() {
    }
}
