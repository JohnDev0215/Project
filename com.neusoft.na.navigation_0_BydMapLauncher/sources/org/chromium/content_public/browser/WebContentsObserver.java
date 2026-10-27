package org.chromium.content_public.browser;

import java.lang.ref.WeakReference;
import org.chromium.content_public.common.MediaMetadata;

/* JADX INFO: loaded from: classes3.dex */
public abstract class WebContentsObserver {
    private WeakReference<WebContents> mWebContents;

    public void didAttachInterstitialPage() {
    }

    public void didChangeThemeColor(int i) {
    }

    public void didCommitProvisionalLoadForFrame(long j, boolean z, String str, int i) {
    }

    public void didDetachInterstitialPage() {
    }

    public void didFailLoad(boolean z, boolean z2, int i, String str, String str2, boolean z3) {
    }

    public void didFinishLoad(long j, String str, boolean z) {
    }

    public void didFinishNavigation(boolean z, boolean z2, boolean z3) {
    }

    public void didFirstVisuallyNonEmptyPaint() {
    }

    public void didNavigateAnyFrame(String str, String str2, boolean z) {
    }

    public void didNavigateMainFrame(String str, String str2, boolean z, boolean z2, int i) {
    }

    public void didStartLoading(String str) {
    }

    public void didStartNavigationToPendingEntry(String str) {
    }

    public void didStartProvisionalLoadForFrame(long j, long j2, boolean z, String str, boolean z2, boolean z3) {
    }

    public void didStopLoading(String str) {
    }

    public void documentAvailableInMainFrame() {
    }

    public void documentLoadedInFrame(long j, boolean z) {
    }

    public void mediaSessionStateChanged(boolean z, boolean z2, MediaMetadata mediaMetadata) {
    }

    public void navigationEntryCommitted() {
    }

    public void renderProcessGone(boolean z) {
    }

    public void renderViewReady() {
    }

    public WebContentsObserver(WebContents webContents) {
        this.mWebContents = new WeakReference<>(webContents);
        webContents.addObserver(this);
    }

    public void destroy() {
        WeakReference<WebContents> weakReference = this.mWebContents;
        if (weakReference == null) {
            return;
        }
        WebContents webContents = weakReference.get();
        this.mWebContents = null;
        if (webContents == null) {
            return;
        }
        webContents.removeObserver(this);
    }

    protected WebContentsObserver() {
    }
}
