package defpackage;

import android.webkit.WebView;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import org.chromium.android_webview.AwRenderProcess;
import org.chromium.support_lib_boundary.WebViewRendererClientBoundaryInterface;

/* JADX INFO: renamed from: iN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0938iN extends V8 {
    public WebViewRendererClientBoundaryInterface a;
    public String[] b;

    public C0938iN(InvocationHandler invocationHandler) {
        WebViewRendererClientBoundaryInterface webViewRendererClientBoundaryInterface = (WebViewRendererClientBoundaryInterface) JM.a(WebViewRendererClientBoundaryInterface.class, invocationHandler);
        this.a = webViewRendererClientBoundaryInterface;
        this.b = webViewRendererClientBoundaryInterface.getSupportedFeatures();
    }

    @Override // defpackage.V8
    public InvocationHandler a() {
        return Proxy.getInvocationHandler(this.a);
    }

    @Override // defpackage.V8
    public void b(WebView webView, AwRenderProcess awRenderProcess) {
        if (JM.b(this.b, "WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE")) {
            this.a.onRendererResponsive(webView, JM.c(new C0882hN(awRenderProcess)));
        }
    }

    @Override // defpackage.V8
    public void c(WebView webView, AwRenderProcess awRenderProcess) {
        if (JM.b(this.b, "WEB_VIEW_RENDERER_CLIENT_BASIC_USAGE")) {
            this.a.onRendererUnresponsive(webView, JM.c(new C0882hN(awRenderProcess)));
        }
    }
}
