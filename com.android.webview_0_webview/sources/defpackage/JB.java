package defpackage;

import J.N;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JB implements Runnable {
    public final /* synthetic */ QB s;

    public JB(QB qb) {
        this.s = qb;
    }

    @Override // java.lang.Runnable
    public void run() {
        ImeAdapterImpl imeAdapterImpl = this.s.e;
        boolean zM7o5Xhhi = false;
        if (imeAdapterImpl.S() && imeAdapterImpl.u != null) {
            zM7o5Xhhi = N.M7o5Xhhi(imeAdapterImpl.s, imeAdapterImpl);
        }
        if (zM7o5Xhhi) {
            return;
        }
        this.s.h();
    }
}
