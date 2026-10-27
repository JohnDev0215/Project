package defpackage;

import android.os.Handler;
import android.view.Surface;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class JA implements InterfaceC1862zA {
    public final InterfaceC1862zA t;
    public final Semaphore u = new Semaphore(0);
    public Handler s = new Handler();

    public JA(InterfaceC1862zA interfaceC1862zA) {
        this.t = interfaceC1862zA;
    }

    @Override // defpackage.InterfaceC1862zA
    public void A(Surface surface) {
        this.s.post(new GA(this, surface));
    }

    @Override // defpackage.InterfaceC1862zA
    public void G() {
        this.s.post(new IA(this));
    }

    @Override // defpackage.InterfaceC1862zA
    public void M() {
        while (true) {
            try {
                this.u.tryAcquire(2L, TimeUnit.SECONDS);
                return;
            } catch (InterruptedException unused) {
            }
        }
    }

    @Override // defpackage.InterfaceC1862zA
    public void x() {
        this.s.post(new HA(this));
    }
}
