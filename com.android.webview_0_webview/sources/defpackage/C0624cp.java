package defpackage;

import org.chromium.android_webview.AwContentsBackgroundThreadClient;
import org.chromium.android_webview.AwWebResourceResponse;

/* JADX INFO: renamed from: cp, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0624cp extends AwContentsBackgroundThreadClient {
    public final /* synthetic */ C0735ep a;

    public C0624cp(C0735ep c0735ep, AbstractC0568bp abstractC0568bp) {
        this.a = c0735ep;
    }

    @Override // org.chromium.android_webview.AwContentsBackgroundThreadClient
    public AwWebResourceResponse a(C0426Xn c0426Xn) {
        return this.a.a.a(c0426Xn);
    }
}
