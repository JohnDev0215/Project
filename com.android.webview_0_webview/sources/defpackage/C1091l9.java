package defpackage;

import J.N;
import android.webkit.ValueCallback;
import android.webkit.WebStorage;
import com.android.webview.chromium.WebViewChromiumFactoryProvider;
import org.chromium.android_webview.AwQuotaManagerBridge;
import org.chromium.base.Callback;
import org.chromium.base.ThreadUtils;

/* JADX INFO: renamed from: l9, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1091l9 extends WebStorage {
    public final WebViewChromiumFactoryProvider a;
    public final AwQuotaManagerBridge b;

    public C1091l9(WebViewChromiumFactoryProvider webViewChromiumFactoryProvider, AwQuotaManagerBridge awQuotaManagerBridge) {
        this.a = webViewChromiumFactoryProvider;
        this.b = awQuotaManagerBridge;
    }

    public static boolean a() {
        return !ThreadUtils.j();
    }

    @Override // android.webkit.WebStorage
    public void deleteAllData() {
        if (!a()) {
            AwQuotaManagerBridge awQuotaManagerBridge = this.b;
            N.MMBD8oze(awQuotaManagerBridge.a, awQuotaManagerBridge);
        } else {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.a;
            webViewChromiumFactoryProvider.a.a(new RunnableC1035k9(this));
        }
    }

    @Override // android.webkit.WebStorage
    public void deleteOrigin(String str) {
        if (!a()) {
            AwQuotaManagerBridge awQuotaManagerBridge = this.b;
            N.MVmZoL1a(awQuotaManagerBridge.a, awQuotaManagerBridge, str);
        } else {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.a;
            webViewChromiumFactoryProvider.a.a(new RunnableC0979j9(this, str));
        }
    }

    @Override // android.webkit.WebStorage
    public void getOrigins(ValueCallback valueCallback) {
        C0756f9 c0756f9 = new C0756f9(this, valueCallback);
        if (a()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.a;
            webViewChromiumFactoryProvider.a.a(new RunnableC0812g9(this, c0756f9));
        } else {
            AwQuotaManagerBridge awQuotaManagerBridge = this.b;
            int iA = awQuotaManagerBridge.a();
            awQuotaManagerBridge.c.put(iA, c0756f9);
            N.MBqEItUo(awQuotaManagerBridge.a, awQuotaManagerBridge, iA);
        }
    }

    @Override // android.webkit.WebStorage
    public void getQuotaForOrigin(String str, ValueCallback valueCallback) {
        if (a()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.a;
            webViewChromiumFactoryProvider.a.a(new RunnableC0924i9(this, str, valueCallback));
        } else {
            AwQuotaManagerBridge awQuotaManagerBridge = this.b;
            Callback callbackA = AbstractC0532b8.a(valueCallback);
            int iA = awQuotaManagerBridge.a();
            awQuotaManagerBridge.d.put(iA, callbackA);
            N.MJn4yzAu(awQuotaManagerBridge.a, awQuotaManagerBridge, str, iA, true);
        }
    }

    @Override // android.webkit.WebStorage
    public void getUsageForOrigin(String str, ValueCallback valueCallback) {
        if (a()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.a;
            webViewChromiumFactoryProvider.a.a(new RunnableC0868h9(this, str, valueCallback));
        } else {
            AwQuotaManagerBridge awQuotaManagerBridge = this.b;
            Callback callbackA = AbstractC0532b8.a(valueCallback);
            int iA = awQuotaManagerBridge.a();
            awQuotaManagerBridge.e.put(iA, callbackA);
            N.MJn4yzAu(awQuotaManagerBridge.a, awQuotaManagerBridge, str, iA, false);
        }
    }

    @Override // android.webkit.WebStorage
    public void setQuotaForOrigin(String str, long j) {
    }
}
