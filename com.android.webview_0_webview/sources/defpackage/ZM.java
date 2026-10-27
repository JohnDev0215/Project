package defpackage;

import org.chromium.content_public.browser.MessagePort;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ZM implements UD {
    public final /* synthetic */ XM a;
    public final /* synthetic */ C0491aN b;

    public ZM(C0491aN c0491aN, XM xm) {
        this.b = c0491aN;
        this.a = xm;
    }

    @Override // defpackage.UD
    public void a(String str, MessagePort[] messagePortArr) {
        XM xm = this.a;
        C0491aN c0491aN = this.b;
        WM wm = new WM(str, messagePortArr);
        if (JM.b(xm.a.getSupportedFeatures(), "WEB_MESSAGE_CALLBACK_ON_MESSAGE")) {
            xm.a.onMessage(JM.c(c0491aN), JM.c(wm));
        }
    }
}
