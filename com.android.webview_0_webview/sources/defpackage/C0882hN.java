package defpackage;

import J.N;
import org.chromium.android_webview.AwRenderProcess;
import org.chromium.support_lib_boundary.WebViewRendererBoundaryInterface;

/* JADX INFO: renamed from: hN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0882hN extends NM implements WebViewRendererBoundaryInterface {
    public AwRenderProcess a;

    public C0882hN(AwRenderProcess awRenderProcess) {
        this.a = awRenderProcess;
    }

    @Override // defpackage.NM
    public AbstractC1675vp a() {
        return this.a;
    }

    @Override // org.chromium.support_lib_boundary.WebViewRendererBoundaryInterface
    public boolean terminate() {
        C0826gN.a(51);
        AwRenderProcess awRenderProcess = this.a;
        long j = awRenderProcess.b;
        if (j == 0) {
            return false;
        }
        return N.MjS72mtS(j, awRenderProcess);
    }
}
