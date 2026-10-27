package defpackage;

import org.chromium.android_webview.AwBrowserProcess;

/* JADX INFO: renamed from: jn, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RunnableC1012jn implements Runnable {
    public final boolean s;

    public RunnableC1012jn(boolean z) {
        this.s = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        AwBrowserProcess.f(this.s);
    }
}
