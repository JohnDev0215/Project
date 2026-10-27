package defpackage;

import J.N;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: compiled from: AuthenticatorFactory.java */
/* JADX INFO: loaded from: classes3.dex */
public final class q9 implements a21<p9> {
    public final RenderFrameHost e;

    public q9(RenderFrameHost renderFrameHost) {
        this.e = renderFrameHost;
    }

    @Override // defpackage.a21
    public final y11 d() {
        WebContents webContentsR;
        RenderFrameHost renderFrameHost = this.e;
        if (renderFrameHost != null && (webContentsR = n3.R(renderFrameHost)) != null) {
            long jM6q_dnW6 = N.M6q_dnW6();
            if (N.Mh_TQYmi(jM6q_dnW6, webContentsR) != 0) {
                return new r9((iq3) N.MOw11ALs(jM6q_dnW6, webContentsR), renderFrameHost);
            }
        }
        return null;
    }
}
