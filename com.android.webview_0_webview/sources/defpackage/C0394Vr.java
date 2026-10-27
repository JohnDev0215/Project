package defpackage;

import org.chromium.android_webview.AwContents;

/* JADX INFO: renamed from: Vr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0394Vr implements InterfaceC0194Ko {
    public RunnableC0376Ur a;
    public C0248Np b;

    public C0394Vr(String str, AwContents awContents) {
        this.a = new RunnableC0376Ur(awContents, str);
        this.b = new C0248Np(this, this.a);
    }

    public void a(String str, boolean z, boolean z2) {
        C0248Np c0248Np;
        RunnableC0376Ur runnableC0376Ur = this.a;
        if (runnableC0376Ur == null || (c0248Np = this.b) == null) {
            Ts.f("Geolocation", "Response for this geolocation request has been received. Ignoring subsequent responses", new Object[0]);
            return;
        }
        runnableC0376Ur.v = str;
        runnableC0376Ur.t = z;
        runnableC0376Ur.u = z2;
        c0248Np.b(2);
        this.b = null;
        this.a = null;
    }
}
