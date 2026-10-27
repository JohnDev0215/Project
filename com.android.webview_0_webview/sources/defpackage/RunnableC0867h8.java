package defpackage;

import android.webkit.ValueCallback;

/* JADX INFO: renamed from: h8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0867h8 implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ ValueCallback t;
    public final /* synthetic */ C0978j8 u;

    public RunnableC0867h8(C0978j8 c0978j8, String str, ValueCallback valueCallback) {
        this.u = c0978j8;
        this.s = str;
        this.t = valueCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.u.b.c(this.s, AbstractC0532b8.a(this.t));
    }
}
