package defpackage;

import J.N;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class GB implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ QB t;

    public GB(QB qb, int i) {
        this.t = qb;
        this.s = i;
    }

    @Override // java.lang.Runnable
    public void run() {
        ImeAdapterImpl imeAdapterImpl = this.t.e;
        int i = this.s;
        boolean z = (i & 1) != 0;
        boolean z2 = (i & 2) != 0;
        if (imeAdapterImpl.S()) {
            N.MdwW1P2L(imeAdapterImpl.s, imeAdapterImpl, z, z2);
        }
        PA pa = imeAdapterImpl.z;
        if (pa == null) {
            return;
        }
        pa.a(z, z2, imeAdapterImpl.Q());
    }
}
