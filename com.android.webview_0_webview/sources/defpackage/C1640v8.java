package defpackage;

import android.webkit.ServiceWorkerClient;
import android.webkit.WebResourceResponse;
import java.util.HashMap;
import java.util.Map;
import org.chromium.android_webview.AwWebResourceResponse;

/* JADX INFO: renamed from: v8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1640v8 extends AbstractC0512ap {
    public ServiceWorkerClient a;

    public C1640v8(ServiceWorkerClient serviceWorkerClient) {
        this.a = serviceWorkerClient;
    }

    public static AwWebResourceResponse b(WebResourceResponse webResourceResponse) {
        if (webResourceResponse == null) {
            return null;
        }
        Map<String, String> responseHeaders = webResourceResponse.getResponseHeaders();
        if (responseHeaders == null) {
            responseHeaders = new HashMap<>();
        }
        return new AwWebResourceResponse(webResourceResponse.getMimeType(), webResourceResponse.getEncoding(), webResourceResponse.getData(), webResourceResponse.getStatusCode(), webResourceResponse.getReasonPhrase(), responseHeaders);
    }

    @Override // defpackage.AbstractC0512ap
    public AwWebResourceResponse a(C0426Xn c0426Xn) {
        return b(this.a.shouldInterceptRequest(new C0645d9(c0426Xn)));
    }
}
