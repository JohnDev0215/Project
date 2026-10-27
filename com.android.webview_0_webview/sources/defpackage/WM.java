package defpackage;

import java.lang.reflect.InvocationHandler;
import org.chromium.content_public.browser.MessagePort;
import org.chromium.support_lib_boundary.WebMessageBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class WM implements WebMessageBoundaryInterface {
    public String a;
    public MessagePort[] b;

    public WM(String str, MessagePort[] messagePortArr) {
        this.a = str;
        this.b = messagePortArr;
    }

    @Override // org.chromium.support_lib_boundary.WebMessageBoundaryInterface
    public String getData() {
        C0826gN.a(32);
        return this.a;
    }

    @Override // org.chromium.support_lib_boundary.WebMessageBoundaryInterface
    public InvocationHandler[] getPorts() {
        C0826gN.a(33);
        return C0491aN.a(this.b);
    }

    @Override // org.chromium.support_lib_boundary.FeatureFlagHolderBoundaryInterface
    public String[] getSupportedFeatures() {
        return new String[0];
    }
}
