package defpackage;

import org.chromium.android_webview.AwContentsBackgroundThreadClient;

/* JADX INFO: renamed from: Vn, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0390Vn implements Runnable {
    public final /* synthetic */ Throwable s;

    public RunnableC0390Vn(AwContentsBackgroundThreadClient awContentsBackgroundThreadClient, Throwable th) {
        this.s = th;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        Ts.a("AwBgThreadClient", "The following exception was raised by shouldInterceptRequest:", new Object[0]);
        throw this.s;
    }
}
