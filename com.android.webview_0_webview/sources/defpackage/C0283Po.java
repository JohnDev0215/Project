package defpackage;

import org.chromium.android_webview.AwContentsLifecycleNotifier;

/* JADX INFO: renamed from: Po, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0283Po extends RK implements InterfaceC1894zo {
    @Override // defpackage.InterfaceC1894zo
    public void a() {
        this.a.g();
    }

    @Override // defpackage.InterfaceC1894zo
    public void b() {
        e();
    }

    @Override // defpackage.RK
    public void c() {
        AwContentsLifecycleNotifier.a.g(this);
    }

    @Override // defpackage.RK
    public void d(TK tk) {
        this.a = tk;
        AwContentsLifecycleNotifier.a.f(this);
    }
}
