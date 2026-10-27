package defpackage;

import J.N;
import org.chromium.android_webview.AwQuotaManagerBridge;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: g9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0812g9 implements Runnable {
    public final /* synthetic */ Callback s;
    public final /* synthetic */ C1091l9 t;

    public RunnableC0812g9(C1091l9 c1091l9, Callback callback) {
        this.t = c1091l9;
        this.s = callback;
    }

    @Override // java.lang.Runnable
    public void run() {
        AwQuotaManagerBridge awQuotaManagerBridge = this.t.b;
        Callback callback = this.s;
        int iA = awQuotaManagerBridge.a();
        awQuotaManagerBridge.c.put(iA, callback);
        N.MBqEItUo(awQuotaManagerBridge.a, awQuotaManagerBridge, iA);
    }
}
