package defpackage;

import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import com.android.webview.chromium.WebViewChromiumFactoryProvider;
import org.chromium.base.ThreadUtils;

/* JADX INFO: renamed from: j8, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C0978j8 extends GeolocationPermissions {
    public final WebViewChromiumFactoryProvider a;
    public final C0212Lo b;

    public C0978j8(WebViewChromiumFactoryProvider webViewChromiumFactoryProvider, C0212Lo c0212Lo) {
        this.a = webViewChromiumFactoryProvider;
        this.b = c0212Lo;
    }

    public static boolean a() {
        return !ThreadUtils.j();
    }

    @Override // android.webkit.GeolocationPermissions
    public void allow(String str) {
        if (!a()) {
            this.b.a(str);
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.a;
        webViewChromiumFactoryProvider.a.a(new RunnableC0699e8(this, str));
    }

    @Override // android.webkit.GeolocationPermissions
    public void clear(String str) {
        if (a()) {
            WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.a;
            webViewChromiumFactoryProvider.a.a(new RunnableC0755f8(this, str));
        } else {
            C0212Lo c0212Lo = this.b;
            String strD = c0212Lo.d(str);
            if (strD != null) {
                c0212Lo.a.edit().remove(strD).apply();
            }
        }
    }

    @Override // android.webkit.GeolocationPermissions
    public void clearAll() {
        if (!a()) {
            this.b.b();
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.a;
        webViewChromiumFactoryProvider.a.a(new RunnableC0811g8(this));
    }

    @Override // android.webkit.GeolocationPermissions
    public void getAllowed(String str, ValueCallback valueCallback) {
        if (!a()) {
            this.b.c(str, AbstractC0532b8.a(valueCallback));
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.a;
        webViewChromiumFactoryProvider.a.a(new RunnableC0867h8(this, str, valueCallback));
    }

    @Override // android.webkit.GeolocationPermissions
    public void getOrigins(ValueCallback valueCallback) {
        if (!a()) {
            this.b.e(AbstractC0532b8.a(valueCallback));
            return;
        }
        WebViewChromiumFactoryProvider webViewChromiumFactoryProvider = this.a;
        webViewChromiumFactoryProvider.a.a(new RunnableC0923i8(this, valueCallback));
    }
}
