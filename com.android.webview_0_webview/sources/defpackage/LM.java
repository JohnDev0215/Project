package defpackage;

import org.chromium.support_lib_boundary.WebResourceErrorBoundaryInterface;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class LM implements WebResourceErrorBoundaryInterface {
    public C0408Wn a;

    public LM(C0408Wn c0408Wn) {
        this.a = c0408Wn;
    }

    @Override // org.chromium.support_lib_boundary.WebResourceErrorBoundaryInterface
    public CharSequence getDescription() {
        return this.a.b;
    }

    @Override // org.chromium.support_lib_boundary.WebResourceErrorBoundaryInterface
    public int getErrorCode() {
        return this.a.a;
    }
}
