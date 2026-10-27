package defpackage;

import org.chromium.android_webview.AwContents;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class O8 implements Runnable {
    public final /* synthetic */ long s;
    public final /* synthetic */ AwContents.VisualStateCallback t;
    public final /* synthetic */ T8 u;

    public O8(T8 t8, long j, AwContents.VisualStateCallback visualStateCallback) {
        this.u = t8;
        this.s = j;
        this.t = visualStateCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.u.f(this.s, this.t);
    }
}
