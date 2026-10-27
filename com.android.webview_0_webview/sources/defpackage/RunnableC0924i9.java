package defpackage;

import J.N;
import android.webkit.ValueCallback;
import org.chromium.android_webview.AwQuotaManagerBridge;
import org.chromium.base.Callback;

/* JADX INFO: renamed from: i9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC0924i9 implements Runnable {
    public final /* synthetic */ String s;
    public final /* synthetic */ ValueCallback t;
    public final /* synthetic */ C1091l9 u;

    public RunnableC0924i9(C1091l9 c1091l9, String str, ValueCallback valueCallback) {
        this.u = c1091l9;
        this.s = str;
        this.t = valueCallback;
    }

    @Override // java.lang.Runnable
    public void run() {
        AwQuotaManagerBridge awQuotaManagerBridge = this.u.b;
        String str = this.s;
        Callback callbackA = AbstractC0532b8.a(this.t);
        int iA = awQuotaManagerBridge.a();
        awQuotaManagerBridge.d.put(iA, callbackA);
        N.MJn4yzAu(awQuotaManagerBridge.a, awQuotaManagerBridge, str, iA, true);
    }
}
