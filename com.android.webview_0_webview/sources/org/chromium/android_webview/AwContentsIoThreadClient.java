package org.chromium.android_webview;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AwContentsIoThreadClient {
    public abstract AwContentsBackgroundThreadClient getBackgroundThreadClient();

    public abstract int getCacheMode();

    public abstract boolean getSafeBrowsingEnabled();

    public abstract boolean shouldAcceptThirdPartyCookies();

    public abstract boolean shouldBlockContentUrls();

    public abstract boolean shouldBlockFileUrls();

    public abstract boolean shouldBlockNetworkLoads();
}
