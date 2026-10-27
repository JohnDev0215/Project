package defpackage;

import android.webkit.ServiceWorkerClient;
import android.webkit.ServiceWorkerController;
import android.webkit.ServiceWorkerWebSettings;

/* JADX INFO: renamed from: w8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1695w8 extends ServiceWorkerController {
    public C0735ep a;

    public C1695w8(C0735ep c0735ep) {
        this.a = c0735ep;
    }

    @Override // android.webkit.ServiceWorkerController
    public ServiceWorkerWebSettings getServiceWorkerWebSettings() {
        return new C1750x8(this.a.d);
    }

    @Override // android.webkit.ServiceWorkerController
    public void setServiceWorkerClient(ServiceWorkerClient serviceWorkerClient) {
        this.a.a(new C1640v8(serviceWorkerClient));
    }
}
