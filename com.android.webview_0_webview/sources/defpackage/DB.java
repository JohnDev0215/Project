package defpackage;

import J.N;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class DB implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ int t;
    public final /* synthetic */ QB u;

    public DB(QB qb, int i, int i2) {
        this.u = qb;
        this.s = i;
        this.t = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        ImeAdapterImpl imeAdapterImpl = this.u.e;
        int i = this.s;
        int i2 = this.t;
        if (imeAdapterImpl.S()) {
            N.MmtjCblb(imeAdapterImpl.s, imeAdapterImpl, i, i2);
        }
    }
}
