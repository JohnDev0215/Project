package defpackage;

import org.chromium.device.geolocation.LocationProviderAdapter;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class YE implements Runnable {
    public final /* synthetic */ boolean s;
    public final /* synthetic */ LocationProviderAdapter t;

    public YE(LocationProviderAdapter locationProviderAdapter, boolean z) {
        this.t = locationProviderAdapter;
        this.s = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.a.a(this.s);
    }
}
