package defpackage;

import org.chromium.support_lib_boundary.WebResourceRequestBoundaryInterface;

/* JADX INFO: renamed from: bN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0547bN implements WebResourceRequestBoundaryInterface {
    public final C0426Xn a;

    public C0547bN(C0426Xn c0426Xn) {
        this.a = c0426Xn;
    }

    @Override // org.chromium.support_lib_boundary.WebResourceRequestBoundaryInterface
    public boolean isRedirect() {
        C0826gN.a(38);
        return this.a.d;
    }
}
