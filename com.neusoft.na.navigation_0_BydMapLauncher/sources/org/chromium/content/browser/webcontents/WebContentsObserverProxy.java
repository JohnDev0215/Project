package org.chromium.content.browser.webcontents;

import org.chromium.base.ObserverList;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.WebContentsObserver;
import org.chromium.content_public.common.MediaMetadata;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("content")
class WebContentsObserverProxy extends WebContentsObserver {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private long mNativeWebContentsObserverProxy;
    private final ObserverList<WebContentsObserver> mObservers;
    private final ObserverList.RewindableIterator<WebContentsObserver> mObserversIterator;

    private native void nativeDestroy(long j);

    private native long nativeInit(WebContentsImpl webContentsImpl);

    public WebContentsObserverProxy(WebContentsImpl webContentsImpl) {
        ThreadUtils.assertOnUiThread();
        this.mNativeWebContentsObserverProxy = nativeInit(webContentsImpl);
        ObserverList<WebContentsObserver> observerList = new ObserverList<>();
        this.mObservers = observerList;
        this.mObserversIterator = observerList.rewindableIterator();
    }

    void addObserver(WebContentsObserver webContentsObserver) {
        this.mObservers.addObserver(webContentsObserver);
    }

    void removeObserver(WebContentsObserver webContentsObserver) {
        this.mObservers.removeObserver(webContentsObserver);
    }

    boolean hasObservers() {
        return !this.mObservers.isEmpty();
    }

    public ObserverList.RewindableIterator<WebContentsObserver> getObserversForTesting() {
        return this.mObservers.rewindableIterator();
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void renderViewReady() {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().renderViewReady();
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void renderProcessGone(boolean z) {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().renderProcessGone(z);
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void didFinishNavigation(boolean z, boolean z2, boolean z3) {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didFinishNavigation(z, z2, z3);
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void didStartLoading(String str) {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didStartLoading(str);
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void didStopLoading(String str) {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didStopLoading(str);
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void didFailLoad(boolean z, boolean z2, int i, String str, String str2, boolean z3) {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didFailLoad(z, z2, i, str, str2, z3);
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void didNavigateMainFrame(String str, String str2, boolean z, boolean z2, int i) {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didNavigateMainFrame(str, str2, z, z2, i);
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void didFirstVisuallyNonEmptyPaint() {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didFirstVisuallyNonEmptyPaint();
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void didNavigateAnyFrame(String str, String str2, boolean z) {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didNavigateAnyFrame(str, str2, z);
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void documentAvailableInMainFrame() {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().documentAvailableInMainFrame();
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void didStartProvisionalLoadForFrame(long j, long j2, boolean z, String str, boolean z2, boolean z3) {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didStartProvisionalLoadForFrame(j, j2, z, str, z2, z3);
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void didCommitProvisionalLoadForFrame(long j, boolean z, String str, int i) {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didCommitProvisionalLoadForFrame(j, z, str, i);
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void didFinishLoad(long j, String str, boolean z) {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didFinishLoad(j, str, z);
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void documentLoadedInFrame(long j, boolean z) {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().documentLoadedInFrame(j, z);
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void navigationEntryCommitted() {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().navigationEntryCommitted();
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void didAttachInterstitialPage() {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didAttachInterstitialPage();
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void didDetachInterstitialPage() {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didDetachInterstitialPage();
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void didChangeThemeColor(int i) {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didChangeThemeColor(i);
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void didStartNavigationToPendingEntry(String str) {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().didStartNavigationToPendingEntry(str);
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void mediaSessionStateChanged(boolean z, boolean z2, MediaMetadata mediaMetadata) {
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().mediaSessionStateChanged(z, z2, mediaMetadata);
        }
    }

    @Override // org.chromium.content_public.browser.WebContentsObserver
    public void destroy() {
        ThreadUtils.assertOnUiThread();
        this.mObserversIterator.rewind();
        while (this.mObserversIterator.hasNext()) {
            this.mObserversIterator.next().destroy();
        }
        this.mObservers.clear();
        long j = this.mNativeWebContentsObserverProxy;
        if (j != 0) {
            nativeDestroy(j);
            this.mNativeWebContentsObserverProxy = 0L;
        }
    }
}
