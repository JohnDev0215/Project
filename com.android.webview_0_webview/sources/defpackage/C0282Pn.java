package defpackage;

import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwContentsBackgroundThreadClient;
import org.chromium.android_webview.AwContentsIoThreadClient;
import org.chromium.android_webview.AwSettings;

/* JADX INFO: renamed from: Pn, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0282Pn extends AwContentsIoThreadClient {
    public final /* synthetic */ AwContents a;

    public C0282Pn(AwContents awContents, C1838yn c1838yn) {
        this.a = awContents;
    }

    @Override // org.chromium.android_webview.AwContentsIoThreadClient
    public AwContentsBackgroundThreadClient getBackgroundThreadClient() {
        return this.a.I;
    }

    @Override // org.chromium.android_webview.AwContentsIoThreadClient
    public int getCacheMode() {
        int i;
        AwSettings awSettings = this.a.S;
        synchronized (awSettings.f) {
            i = awSettings.d0;
        }
        return i;
    }

    @Override // org.chromium.android_webview.AwContentsIoThreadClient
    public boolean getSafeBrowsingEnabled() {
        return this.a.S.w();
    }

    @Override // org.chromium.android_webview.AwContentsIoThreadClient
    public boolean shouldAcceptThirdPartyCookies() {
        boolean z;
        AwSettings awSettings = this.a.S;
        synchronized (awSettings.f) {
            z = awSettings.T;
        }
        return z;
    }

    @Override // org.chromium.android_webview.AwContentsIoThreadClient
    public boolean shouldBlockContentUrls() {
        boolean z;
        AwSettings awSettings = this.a.S;
        synchronized (awSettings.f) {
            z = awSettings.b0;
        }
        return !z;
    }

    @Override // org.chromium.android_webview.AwContentsIoThreadClient
    public boolean shouldBlockFileUrls() {
        return !this.a.S.getAllowFileAccess();
    }

    @Override // org.chromium.android_webview.AwContentsIoThreadClient
    public boolean shouldBlockNetworkLoads() {
        return this.a.S.g();
    }
}
