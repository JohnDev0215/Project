package org.chromium.components.web_contents_delegate_android;

import android.view.KeyEvent;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.content_public.browser.WebContents;
import org.chromium.content_public.common.ResourceRequestBody;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("web_contents_delegate_android")
public class WebContentsDelegateAndroid {
    public static final int LOG_LEVEL_ERROR = 3;
    public static final int LOG_LEVEL_LOG = 1;
    public static final int LOG_LEVEL_TIP = 0;
    public static final int LOG_LEVEL_WARNING = 2;
    private int mMostRecentProgress = 100;

    public void activateContents() {
    }

    public boolean addMessageToConsole(int i, String str, int i2, String str2) {
        return false;
    }

    public void closeContents() {
    }

    public void handleKeyboardEvent(KeyEvent keyEvent) {
    }

    public boolean isFullscreenForTabOrPending() {
        return false;
    }

    public void navigationStateChanged(int i) {
    }

    public boolean onGoToEntryOffset(int i) {
        return true;
    }

    public void onLoadProgressChanged(int i) {
    }

    public void onLoadStarted(boolean z) {
    }

    public void onLoadStopped() {
    }

    public void onUpdateUrl(String str) {
    }

    public void openNewTab(String str, String str2, ResourceRequestBody resourceRequestBody, int i, boolean z) {
    }

    public void rendererResponsive() {
    }

    public void rendererUnresponsive() {
    }

    public boolean shouldCreateWebContents(String str) {
        return true;
    }

    public void showRepostFormWarningDialog() {
    }

    public boolean takeFocus(boolean z) {
        return false;
    }

    public void toggleFullscreenModeForTab(boolean z) {
    }

    public void visibleSSLStateChanged() {
    }

    public void webContentsCreated(WebContents webContents, long j, String str, String str2, WebContents webContents2) {
    }

    public int getMostRecentProgress() {
        return this.mMostRecentProgress;
    }

    private final void notifyLoadProgressChanged(double d) {
        int i = (int) (d * 100.0d);
        this.mMostRecentProgress = i;
        onLoadProgressChanged(i);
    }
}
