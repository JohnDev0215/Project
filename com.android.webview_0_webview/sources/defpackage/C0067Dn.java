package defpackage;

import J.N;
import org.chromium.android_webview.AwContents;
import org.chromium.android_webview.AwSettings;

/* JADX INFO: renamed from: Dn, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0067Dn extends AbstractC0715eO implements InterfaceC0771fO {
    public final /* synthetic */ AwContents s;

    public C0067Dn(AwContents awContents, C1838yn c1838yn) {
        this.s = awContents;
    }

    @Override // defpackage.AbstractC0715eO, defpackage.InterfaceC0771fO
    public void e(float f) {
        AwContents awContents = this.s;
        N.MYYge55A(awContents.s, awContents, f);
        AwContents awContents2 = this.s;
        double d = f;
        awContents2.N.h = d;
        AwSettings awSettings = awContents2.S;
        synchronized (awSettings.f) {
            awSettings.e = d;
        }
    }

    @Override // defpackage.AbstractC0715eO, defpackage.InterfaceC0771fO
    public void x(int i) {
    }
}
