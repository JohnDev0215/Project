package defpackage;

import J.N;
import android.app.Activity;
import defpackage.yq;
import org.chromium.chrome.browser.customtabs.CustomTabActivity;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: ChromePaymentRequestFactory.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xq implements a21<oz1> {
    public final RenderFrameHost e;

    /* JADX INFO: compiled from: ChromePaymentRequestFactory.java */
    public static class a implements yq.a {
        public final RenderFrameHost a;

        public a(RenderFrameHost renderFrameHost) {
            this.a = renderFrameHost;
        }

        public final String b() {
            WebContents webContentsE0 = n3.e0(this.a);
            if (webContentsE0 == null) {
                return null;
            }
            Activity activityN = z7.n(webContentsE0);
            if (!(activityN instanceof CustomTabActivity)) {
                return null;
            }
            xf3 xf3Var = ((CustomTabActivity) activityN).l1;
            if ((xf3Var == null ? false : xf3Var.a.l) && xf3Var != null) {
                return xf3Var.d.e;
            }
            return null;
        }
    }

    public xq(RenderFrameHost renderFrameHost) {
        this.e = renderFrameHost;
    }

    @Override // defpackage.a21
    public final y11 d() {
        RenderFrameHost renderFrameHost = this.e;
        if (renderFrameHost == null) {
            return new n21();
        }
        if (!renderFrameHost.f(8)) {
            renderFrameHost.j();
            return null;
        }
        if (!N.M1X7xdZV("WebPayments")) {
            return new n21();
        }
        a aVar = new a(renderFrameHost);
        WebContents webContentsR = n3.R(renderFrameHost);
        return (webContentsR == null || webContentsR.isDestroyed()) ? new n21() : new li1(new zo1(this, 4, aVar));
    }
}
