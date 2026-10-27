package defpackage;

import android.net.Uri;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.android.webview.chromium.WebViewChromium;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.VisualStateCallbackBoundaryInterface;
import org.chromium.support_lib_boundary.WebMessageBoundaryInterface;
import org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface;

/* JADX INFO: renamed from: eN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0714eN implements WebViewProviderBoundaryInterface {
    public final WebView a;
    public final T8 b;

    public C0714eN(WebView webView) {
        this.a = webView;
        this.b = ((WebViewChromium) webView.getWebViewProvider()).B;
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface
    public void addWebMessageListener(String str, String[] strArr, InvocationHandler invocationHandler) {
        C0826gN.a(0);
        this.b.a(str, strArr, new YM(this.a, invocationHandler));
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface
    public InvocationHandler[] createWebMessageChannel() {
        return C0491aN.a(this.b.c());
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface
    public WebChromeClient getWebChromeClient() {
        C0826gN.a(7);
        return this.b.f;
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface
    public WebViewClient getWebViewClient() {
        C0826gN.a(8);
        return this.b.e;
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface
    public InvocationHandler getWebViewRenderer() {
        C0826gN.a(9);
        return JM.c(new C0882hN(this.b.d()));
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface
    public InvocationHandler getWebViewRendererClient() {
        C0826gN.a(10);
        V8 v8E = this.b.e();
        if (v8E != null) {
            return v8E.a();
        }
        return null;
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface
    public void insertVisualStateCallback(long j, InvocationHandler invocationHandler) {
        C0826gN.a(12);
        this.b.f(j, new C0659dN(this, (VisualStateCallbackBoundaryInterface) JM.a(VisualStateCallbackBoundaryInterface.class, invocationHandler)));
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface
    public void postMessageToMainFrame(InvocationHandler invocationHandler, Uri uri) {
        C0826gN.a(15);
        WebMessageBoundaryInterface webMessageBoundaryInterface = (WebMessageBoundaryInterface) JM.a(WebMessageBoundaryInterface.class, invocationHandler);
        this.b.g(webMessageBoundaryInterface.getData(), uri.toString(), C0491aN.b(webMessageBoundaryInterface.getPorts()));
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface
    public void removeWebMessageListener(String str) {
        C0826gN.a(16);
        this.b.h(str);
    }

    @Override // org.chromium.support_lib_boundary.WebViewProviderBoundaryInterface
    public void setWebViewRendererClient(InvocationHandler invocationHandler) {
        C0826gN.a(28);
        this.b.i(invocationHandler != null ? new C0938iN(invocationHandler) : null);
    }
}
