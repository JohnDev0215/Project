package defpackage;

import org.chromium.media.MediaCodecBridge;

/* JADX INFO: renamed from: fH, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0764fH implements Runnable {
    public int s;
    public final /* synthetic */ MediaCodecBridge t;

    public RunnableC0764fH(MediaCodecBridge mediaCodecBridge, int i) {
        this.t = mediaCodecBridge;
        this.s = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        MediaCodecBridge mediaCodecBridge = this.t;
        int i = this.s;
        synchronized (mediaCodecBridge) {
            if (mediaCodecBridge.j != i) {
                return;
            }
            mediaCodecBridge.h = false;
        }
    }
}
