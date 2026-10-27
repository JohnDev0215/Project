package defpackage;

import org.chromium.android_webview.AwWebResourceResponse;
import org.chromium.support_lib_boundary.ServiceWorkerClientBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class SM extends AbstractC0512ap {
    public ServiceWorkerClientBoundaryInterface a;

    public SM(ServiceWorkerClientBoundaryInterface serviceWorkerClientBoundaryInterface) {
        this.a = serviceWorkerClientBoundaryInterface;
    }

    @Override // defpackage.AbstractC0512ap
    public AwWebResourceResponse a(C0426Xn c0426Xn) {
        if (JM.b(this.a.getSupportedFeatures(), "SERVICE_WORKER_SHOULD_INTERCEPT_REQUEST")) {
            return C1640v8.b(this.a.shouldInterceptRequest(new C0645d9(c0426Xn)));
        }
        return null;
    }
}
