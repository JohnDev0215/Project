package defpackage;

import org.chromium.base.Callback;
import org.chromium.support_lib_boundary.SafeBrowsingResponseBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class KM implements SafeBrowsingResponseBoundaryInterface {
    public final Callback a;

    public KM(Callback callback) {
        this.a = callback;
    }

    @Override // org.chromium.support_lib_boundary.SafeBrowsingResponseBoundaryInterface
    public void backToSafety(boolean z) {
        this.a.a(new C0627cs(2, z));
    }

    @Override // org.chromium.support_lib_boundary.SafeBrowsingResponseBoundaryInterface
    public void proceed(boolean z) {
        this.a.a(new C0627cs(1, z));
    }

    @Override // org.chromium.support_lib_boundary.SafeBrowsingResponseBoundaryInterface
    public void showInterstitial(boolean z) {
        this.a.a(new C0627cs(0, z));
    }
}
