package defpackage;

import org.chromium.media.MediaDrmBridge;

/* JADX INFO: renamed from: sH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1484sH implements Runnable {
    public final /* synthetic */ AH s;
    public final /* synthetic */ long t;
    public final /* synthetic */ C1539tH u;

    public RunnableC1484sH(C1539tH c1539tH, AH ah, long j) {
        this.u = c1539tH;
        this.s = ah;
        this.t = j;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.c();
        MediaDrmBridge.f(this.u.a, this.s, this.t);
    }
}
