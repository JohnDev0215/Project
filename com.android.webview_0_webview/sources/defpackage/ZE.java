package defpackage;

import org.chromium.device.geolocation.LocationProviderAdapter;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ZE implements Runnable {
    public final /* synthetic */ LocationProviderAdapter s;

    public ZE(LocationProviderAdapter locationProviderAdapter) {
        this.s = locationProviderAdapter;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.a.stop();
    }
}
