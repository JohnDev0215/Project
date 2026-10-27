package defpackage;

import android.webkit.WebView;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.WebMessageListenerBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class YM implements InterfaceC0016Aq {
    public final WebView a;
    public WebMessageListenerBoundaryInterface b;
    public String[] c;

    public YM(WebView webView, InvocationHandler invocationHandler) {
        this.a = webView;
        WebMessageListenerBoundaryInterface webMessageListenerBoundaryInterface = (WebMessageListenerBoundaryInterface) JM.a(WebMessageListenerBoundaryInterface.class, invocationHandler);
        this.b = webMessageListenerBoundaryInterface;
        this.c = webMessageListenerBoundaryInterface.getSupportedFeatures();
    }
}
