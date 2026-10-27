package defpackage;

import android.webkit.ValueCallback;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class VQ implements DD {
    public final /* synthetic */ QR a;
    public final /* synthetic */ ValueCallback b;
    public final /* synthetic */ XQ c;

    public VQ(XQ xq, QR qr, ValueCallback valueCallback) {
        this.c = xq;
        this.a = qr;
        this.b = valueCallback;
    }

    @Override // defpackage.DD
    public void a() {
        this.c.v();
        this.b.onReceiveValue(Boolean.TRUE);
    }

    @Override // defpackage.DD
    public void b() {
        this.b.onReceiveValue(Boolean.FALSE);
    }
}
