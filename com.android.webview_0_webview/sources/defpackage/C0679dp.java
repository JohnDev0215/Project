package defpackage;

import org.chromium.android_webview.AwContentsBackgroundThreadClient;
import org.chromium.android_webview.AwContentsIoThreadClient;
import org.chromium.android_webview.safe_browsing.AwSafeBrowsingConfigHelper;

/* JADX INFO: renamed from: dp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0679dp extends AwContentsIoThreadClient {
    public final /* synthetic */ C0735ep a;

    public C0679dp(C0735ep c0735ep, AbstractC0568bp abstractC0568bp) {
        this.a = c0735ep;
    }

    @Override // org.chromium.android_webview.AwContentsIoThreadClient
    public AwContentsBackgroundThreadClient getBackgroundThreadClient() {
        return this.a.c;
    }

    @Override // org.chromium.android_webview.AwContentsIoThreadClient
    public int getCacheMode() {
        return this.a.d.d();
    }

    @Override // org.chromium.android_webview.AwContentsIoThreadClient
    public boolean getSafeBrowsingEnabled() {
        return AwSafeBrowsingConfigHelper.b;
    }

    @Override // org.chromium.android_webview.AwContentsIoThreadClient
    public boolean shouldAcceptThirdPartyCookies() {
        return false;
    }

    @Override // org.chromium.android_webview.AwContentsIoThreadClient
    public boolean shouldBlockContentUrls() {
        return !this.a.d.a();
    }

    @Override // org.chromium.android_webview.AwContentsIoThreadClient
    public boolean shouldBlockFileUrls() {
        return !this.a.d.b();
    }

    @Override // org.chromium.android_webview.AwContentsIoThreadClient
    public boolean shouldBlockNetworkLoads() {
        return this.a.d.c();
    }
}
