package defpackage;

import org.chromium.net.AndroidCellularSignalStrength;

/* JADX INFO: renamed from: rK, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1432rK implements Runnable {
    public final /* synthetic */ AndroidCellularSignalStrength s;

    public RunnableC1432rK(AndroidCellularSignalStrength androidCellularSignalStrength) {
        this.s = androidCellularSignalStrength;
    }

    @Override // java.lang.Runnable
    public void run() {
        new C1487sK(this.s);
    }
}
