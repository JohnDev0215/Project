package defpackage;

import J.N;
import org.chromium.android_webview.AwQuotaManagerBridge;

/* JADX INFO: renamed from: j9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0979j9 implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ C1091l9 t;

    public RunnableC0979j9(C1091l9 c1091l9, String str) {
        this.t = c1091l9;
        this.s = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        AwQuotaManagerBridge awQuotaManagerBridge = this.t.b;
        N.MVmZoL1a(awQuotaManagerBridge.a, awQuotaManagerBridge, this.s);
    }
}
