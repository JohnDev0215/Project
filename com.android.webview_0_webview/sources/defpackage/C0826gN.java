package defpackage;

import J.N;
import android.webkit.WebView;
import com.android.webview.chromium.WebViewChromiumFactoryProvider;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface;

/* JADX INFO: renamed from: gN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0826gN implements WebViewProviderFactoryBoundaryInterface {
    public InvocationHandler d;
    public InvocationHandler e;
    public InvocationHandler f;
    public InvocationHandler g;
    public final String[] c = {"VISUAL_STATE_CALLBACK", "OFF_SCREEN_PRERASTER", "SAFE_BROWSING_ENABLE", "DISABLED_ACTION_MODE_MENU_ITEMS", "START_SAFE_BROWSING", "SAFE_BROWSING_WHITELIST", "SAFE_BROWSING_PRIVACY_POLICY_URL", "SERVICE_WORKER_BASIC_USAGE", "SERVICE_WORKER_CACHE_MODE", "SERVICE_WORKER_CONTENT_ACCESS", "SERVICE_WORKER_FILE_ACCESS", "SERVICE_WORKER_BLOCK_NETWORK_LOADS", "SERVICE_WORKER_SHOULD_INTERCEPT_REQUEST", "RECEIVE_WEB_RESOURCE_ERROR", "RECEIVE_HTTP_ERROR", "SAFE_BROWSING_HIT", "SHOULD_OVERRIDE_WITH_REDIRECTS", "WEB_RESOURCE_REQUEST_IS_REDIRECT", "WEB_RESOURCE_ERROR_GET_DESCRIPTION", "WEB_RESOURCE_ERROR_GET_CODE", "SAFE_BROWSING_RESPONSE_BACK_TO_SAFETY", "SAFE_BROWSING_RESPONSE_PROCEED", "SAFE_BROWSING_RESPONSE_SHOW_INTERSTITIAL", "WEB_MESSAGE_PORT_POST_MESSAGE", "WEB_MESSAGE_PORT_CLOSE", "WEB_MESSAGE_PORT_SET_MESSAGE_CALLBACK", "CREATE_WEB_MESSAGE_CHANNEL", "POST_WEB_MESSAGE", "WEB_MESSAGE_CALLBACK_ON_MESSAGE", "GET_WEB_VIEW_CLIENT", "GET_WEB_CHROME_CLIENT", "PROXY_OVERRIDE:3", "SUPPRESS_ERROR_PAGE:dev", "GET_WEB_VIEW_RENDERER", "WEB_VIEW_RENDERER_TERMINATE", "TRACING_CONTROLLER_BASIC_USAGE", "WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE", "MULTI_PROCESS_QUERY", "FORCE_DARK", "FORCE_DARK_BEHAVIOR", "WEB_MESSAGE_LISTENER", "SET_SUPPORT_LIBRARY_VERSION:dev"};
    public final InvocationHandler a = JM.c(new C0993jN());
    public final C1551tb b = WebViewChromiumFactoryProvider.e().b;

    public static void a(int i) {
        It.d("Android.WebView.AndroidX.ApiCall", i, 52);
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface
    public InvocationHandler createWebView(WebView webView) {
        return JM.c(new C0714eN(webView));
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface
    public InvocationHandler getProxyController() {
        a(2);
        synchronized (this.b.n) {
            if (this.g == null) {
                C0070Dq c0070Dq = this.b.p.a;
                C1551tb c1551tb = this.b;
                synchronized (c1551tb.n) {
                    if (c1551tb.m == null) {
                        c1551tb.b(true);
                    }
                }
                this.g = JM.c(new RM(c0070Dq, c1551tb.m));
            }
        }
        return this.g;
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface
    public InvocationHandler getServiceWorkerController() {
        a(4);
        synchronized (this.b.n) {
            if (this.e == null) {
                this.e = JM.c(new TM(this.b.f()));
            }
        }
        return this.e;
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface
    public InvocationHandler getStatics() {
        synchronized (this.b.n) {
            if (this.d == null) {
                this.d = JM.c(new C0770fN(WebViewChromiumFactoryProvider.e().b.g()));
            }
        }
        return this.d;
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface
    public String[] getSupportedFeatures() {
        return this.c;
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface
    public InvocationHandler getTracingController() {
        a(6);
        synchronized (this.b.n) {
            if (this.f == null) {
                this.f = JM.c(new VM(new K8(this.b.p.a, this.b.d())));
            }
        }
        return this.f;
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface
    public InvocationHandler getWebkitToCompatConverter() {
        return this.a;
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderFactoryBoundaryInterface
    public void setSupportLibraryVersion(String str) {
        N.M06DsVuS(str);
    }
}
