package defpackage;

import J.N;
import android.os.SystemClock;
import org.chromium.content.browser.input.ImeAdapterImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class AB implements Runnable {
    public final /* synthetic */ int s;
    public final /* synthetic */ int t;
    public final /* synthetic */ QB u;

    public AB(QB qb, int i, int i2) {
        this.u = qb;
        this.s = i;
        this.t = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        QB qb = this.u;
        if (qb.i != 0) {
            QB.a(qb);
        }
        ImeAdapterImpl imeAdapterImpl = this.u.e;
        int i = this.s;
        int i2 = this.t;
        imeAdapterImpl.V();
        if (imeAdapterImpl.S()) {
            N.M1qwlrOP(imeAdapterImpl.s, imeAdapterImpl, null, 7, 0, SystemClock.uptimeMillis(), 229, 0, false, 0);
            N.M26GCjn5(imeAdapterImpl.s, imeAdapterImpl, i, i2);
            N.M1qwlrOP(imeAdapterImpl.s, imeAdapterImpl, null, 9, 0, SystemClock.uptimeMillis(), 229, 0, false, 0);
        }
    }
}
