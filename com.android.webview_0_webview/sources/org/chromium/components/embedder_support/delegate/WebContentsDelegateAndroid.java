package org.chromium.components.embedder_support.delegate;

import android.view.KeyEvent;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WebContentsDelegateAndroid {
    public void activateContents() {
    }

    public boolean addMessageToConsole(int i, String str, int i2, String str2) {
        return false;
    }

    public void closeContents() {
    }

    public boolean controlsResizeView() {
        return false;
    }

    public void enterFullscreenModeForTab(boolean z) {
    }

    public void exitFullscreenModeForTab() {
    }

    public int getBottomControlsHeight() {
        return 0;
    }

    public int getBottomControlsMinHeight() {
        return 0;
    }

    public int getTopControlsHeight() {
        return 0;
    }

    public int getTopControlsMinHeight() {
        return 0;
    }

    public void handleKeyboardEvent(KeyEvent keyEvent) {
    }

    public boolean isFullscreenForTabOrPending() {
        return false;
    }

    public void loadingStateChanged(boolean z) {
    }

    public void navigationStateChanged(int i) {
    }

    public void onUpdateUrl(String str) {
    }

    public void openNewTab(String str, String str2, ResourceRequestBody resourceRequestBody, int i, boolean z) {
    }

    public void rendererResponsive() {
    }

    public void rendererUnresponsive() {
    }

    public boolean shouldAnimateBrowserControlsHeightChanges() {
        return false;
    }

    public boolean shouldBlockMediaRequest(String str) {
        return false;
    }

    public boolean shouldCreateWebContents(String str) {
        return true;
    }

    public void showRepostFormWarningDialog() {
    }

    public boolean takeFocus(boolean z) {
        return false;
    }

    public void visibleSSLStateChanged() {
    }

    public void webContentsCreated(WebContents webContents, long j, long j2, String str, String str2, WebContents webContents2) {
    }
}
