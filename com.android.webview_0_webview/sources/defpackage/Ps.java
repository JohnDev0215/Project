package defpackage;

import J.N;
import org.chromium.base.JavaHandlerThread;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Ps implements Runnable {
    public final /* synthetic */ long s;
    public final /* synthetic */ long t;

    public Ps(JavaHandlerThread javaHandlerThread, long j, long j2) {
        this.s = j;
        this.t = j2;
    }

    @Override // java.lang.Runnable
    public void run() {
        N.MJcct7gJ(this.s, this.t);
    }
}
