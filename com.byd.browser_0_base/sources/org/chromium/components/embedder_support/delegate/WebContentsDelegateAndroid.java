package org.chromium.components.embedder_support.delegate;

import android.view.KeyEvent;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class WebContentsDelegateAndroid {
    public int a() {
        return 0;
    }

    @CalledByNative
    public boolean addMessageToConsole(int i, String str, int i2, String str2) {
        return false;
    }

    @CalledByNative
    public boolean controlsResizeView() {
        return false;
    }

    @CalledByNative
    public int getBottomControlsHeight() {
        return 0;
    }

    @CalledByNative
    public int getBottomControlsMinHeight() {
        return 0;
    }

    @CalledByNative
    public final int getDisplayModeChecked() {
        return a();
    }

    @CalledByNative
    public int getTopControlsHeight() {
        return 0;
    }

    @CalledByNative
    public int getTopControlsMinHeight() {
        return 0;
    }

    @CalledByNative
    public int getVirtualKeyboardHeight() {
        return 0;
    }

    @CalledByNative
    public boolean isFullscreenForTabOrPending() {
        return false;
    }

    @CalledByNative
    public boolean shouldAnimateBrowserControlsHeightChanges() {
        return false;
    }

    @CalledByNative
    public boolean shouldBlockMediaRequest(GURL gurl) {
        return false;
    }

    @CalledByNative
    public boolean shouldCreateWebContents(GURL gurl) {
        return true;
    }

    @CalledByNative
    public boolean takeFocus(boolean z) {
        return false;
    }

    @CalledByNative
    public void activateContents() {
    }

    @CalledByNative
    public void closeContents() {
    }

    @CalledByNative
    public void exitFullscreenModeForTab() {
    }

    @CalledByNative
    public void rendererResponsive() {
    }

    @CalledByNative
    public void rendererUnresponsive() {
    }

    @CalledByNative
    public void showRepostFormWarningDialog() {
    }

    @CalledByNative
    public void visibleSSLStateChanged() {
    }

    @CalledByNative
    public void handleKeyboardEvent(KeyEvent keyEvent) {
    }

    @CalledByNative
    public void loadingStateChanged(boolean z) {
    }

    @CalledByNative
    public void navigationStateChanged(int i) {
    }

    @CalledByNative
    public void onUpdateUrl(GURL gurl) {
    }

    @CalledByNative
    public void enterFullscreenModeForTab(boolean z, boolean z2) {
    }

    @CalledByNative
    public void fullscreenStateChangedForTab(boolean z, boolean z2) {
    }

    @CalledByNative
    public void openNewTab(GURL gurl, String str, ResourceRequestBody resourceRequestBody, int i, boolean z) {
    }

    @CalledByNative
    public void webContentsCreated(WebContents webContents, long j, long j2, String str, GURL gurl, WebContents webContents2) {
    }
}
