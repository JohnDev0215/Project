package defpackage;

import J.N;
import org.chromium.base.JavaHandlerThread;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Qs implements Runnable {
    public final /* synthetic */ long s;
    public final /* synthetic */ JavaHandlerThread t;

    public Qs(JavaHandlerThread javaHandlerThread, long j) {
        this.t = javaHandlerThread;
        this.s = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.t.a.quit();
        N.MYwg$x8E(this.s);
    }
}
