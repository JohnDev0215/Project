package defpackage;

import J.N;
import org.chromium.android_webview.JsReplyProxy;
import org.chromium.base.task.PostTask;
import org.chromium.support_lib_boundary.JsReplyProxyBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class OM extends NM implements JsReplyProxyBoundaryInterface {
    public JsReplyProxy a;

    public OM(JsReplyProxy jsReplyProxy) {
        this.a = jsReplyProxy;
    }

    @Override // defpackage.NM
    public AbstractC1675vp a() {
        return this.a;
    }

    @Override // org.chromium.support_lib_boundary.JsReplyProxyBoundaryInterface
    public void postMessage(final String str) {
        C0826gN.a(14);
        final JsReplyProxy jsReplyProxy = this.a;
        if (jsReplyProxy.b == 0) {
            return;
        }
        PostTask.b(AbstractC0538bE.c, new Runnable(jsReplyProxy, str) { // from class: bq
            public final JsReplyProxy s;
            public final String t;

            {
                this.s = jsReplyProxy;
                this.t = str;
            }

            @Override // java.lang.Runnable
            public void run() {
                JsReplyProxy jsReplyProxy2 = this.s;
                N.MayS5i9E(jsReplyProxy2.b, this.t);
            }
        });
    }
}
