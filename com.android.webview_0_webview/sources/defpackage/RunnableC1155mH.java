package defpackage;

import org.chromium.media.MediaDrmBridge;

/* JADX INFO: renamed from: mH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1155mH implements Runnable {
    public final /* synthetic */ MediaDrmBridge s;

    public RunnableC1155mH(MediaDrmBridge mediaDrmBridge) {
        this.s = mediaDrmBridge;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.s.n();
    }
}
