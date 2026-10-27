package defpackage;

import java.util.Objects;
import org.chromium.android_webview.AwBrowserContext;
import org.chromium.base.ThreadUtils;

/* JADX INFO: renamed from: hn, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0901hn implements InterfaceC1894zo {
    public C0901hn(AwBrowserContext awBrowserContext) {
    }

    @Override // defpackage.InterfaceC1894zo
    public void a() {
        Ct ct = Ct.i;
        Objects.requireNonNull(ct);
        ThreadUtils.b();
        if (ct.e) {
            ct.e = false;
        }
    }

    @Override // defpackage.InterfaceC1894zo
    public void b() {
        Integer num;
        Ct ct = Ct.i;
        Objects.requireNonNull(ct);
        ThreadUtils.b();
        if (ct.e) {
            return;
        }
        ct.e = true;
        if (ct.d || (num = (Integer) ct.f.get()) == null) {
            return;
        }
        ct.d(num.intValue());
    }
}
