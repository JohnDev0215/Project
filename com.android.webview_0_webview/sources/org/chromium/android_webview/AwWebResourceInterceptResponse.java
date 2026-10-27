package org.chromium.android_webview;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AwWebResourceInterceptResponse {
    public AwWebResourceResponse a;
    public boolean b;

    public AwWebResourceInterceptResponse(AwWebResourceResponse awWebResourceResponse, boolean z) {
        this.a = awWebResourceResponse;
        this.b = z;
    }

    public boolean getRaisedException() {
        return this.b;
    }

    public AwWebResourceResponse getResponse() {
        return this.a;
    }
}
