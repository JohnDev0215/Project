package defpackage;

import android.webkit.SafeBrowsingResponse;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: u8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1585u8 extends SafeBrowsingResponse {
    public final Callback a;

    public C1585u8(Callback callback) {
        this.a = callback;
    }

    @Override // android.webkit.SafeBrowsingResponse
    public void backToSafety(boolean z) {
        this.a.a(new C0627cs(2, z));
    }

    @Override // android.webkit.SafeBrowsingResponse
    public void proceed(boolean z) {
        this.a.a(new C0627cs(1, z));
    }

    @Override // android.webkit.SafeBrowsingResponse
    public void showInterstitial(boolean z) {
        this.a.a(new C0627cs(0, z));
    }
}
