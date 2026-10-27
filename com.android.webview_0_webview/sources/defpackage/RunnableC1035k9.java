package defpackage;

import J.N;
import org.chromium.android_webview.AwQuotaManagerBridge;

/* JADX INFO: renamed from: k9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1035k9 implements Runnable {
    public final /* synthetic */ C1091l9 s;

    public RunnableC1035k9(C1091l9 c1091l9) {
        this.s = c1091l9;
    }

    @Override // java.lang.Runnable
    public void run() {
        AwQuotaManagerBridge awQuotaManagerBridge = this.s.b;
        N.MMBD8oze(awQuotaManagerBridge.a, awQuotaManagerBridge);
    }
}
