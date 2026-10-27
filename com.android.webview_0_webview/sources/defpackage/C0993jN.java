package defpackage;

import android.webkit.SafeBrowsingResponse;
import android.webkit.ServiceWorkerWebSettings;
import android.webkit.WebMessagePort;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import com.android.webview.chromium.ContentSettingsAdapter;
import java.lang.reflect.InvocationHandler;
import org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface;

/* JADX INFO: renamed from: jN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0993jN implements WebkitToCompatConverterBoundaryInterface {
    @Override // org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface
    public InvocationHandler convertSafeBrowsingResponse(Object obj) {
        return JM.c(new KM(((C1585u8) ((SafeBrowsingResponse) obj)).a));
    }

    @Override // org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface
    public InvocationHandler convertServiceWorkerSettings(Object obj) {
        return JM.c(new UM(((C1750x8) ((ServiceWorkerWebSettings) obj)).a));
    }

    @Override // org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface
    public InvocationHandler convertSettings(WebSettings webSettings) {
        return JM.c(new C0603cN(((ContentSettingsAdapter) webSettings).a));
    }

    @Override // org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface
    public InvocationHandler convertWebMessagePort(Object obj) {
        return JM.c(new C0491aN(((C0533b9) ((WebMessagePort) obj)).a));
    }

    @Override // org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface
    public InvocationHandler convertWebResourceError(Object obj) {
        return JM.c(new LM(((C0589c9) ((WebResourceError) obj)).a));
    }

    @Override // org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface
    public InvocationHandler convertWebResourceRequest(WebResourceRequest webResourceRequest) {
        return JM.c(new C0547bN(((C0645d9) webResourceRequest).a));
    }

    @Override // org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface
    public Object convertSafeBrowsingResponse(InvocationHandler invocationHandler) {
        return new C1585u8(((KM) JM.d(invocationHandler)).a);
    }

    @Override // org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface
    public Object convertServiceWorkerSettings(InvocationHandler invocationHandler) {
        return new C1750x8(((UM) JM.d(invocationHandler)).a);
    }

    @Override // org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface
    public Object convertWebMessagePort(InvocationHandler invocationHandler) {
        return new C0533b9(((C0491aN) JM.d(invocationHandler)).a);
    }

    @Override // org.chromium.support_lib_boundary.WebkitToCompatConverterBoundaryInterface
    public Object convertWebResourceError(InvocationHandler invocationHandler) {
        return new C0589c9(((LM) JM.d(invocationHandler)).a);
    }
}
