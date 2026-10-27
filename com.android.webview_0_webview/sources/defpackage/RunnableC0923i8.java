package defpackage;

import android.webkit.ValueCallback;

/* JADX INFO: renamed from: i8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0923i8 implements Runnable {
    public final /* synthetic */ ValueCallback s;
    public final /* synthetic */ C0978j8 t;

    public RunnableC0923i8(C0978j8 c0978j8, ValueCallback valueCallback) {
        this.t = c0978j8;
        this.s = valueCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.b.e(AbstractC0532b8.a(this.s));
    }
}
