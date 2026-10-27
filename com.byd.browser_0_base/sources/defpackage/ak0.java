package defpackage;

import J.N;
import org.chromium.chrome.browser.ui.fast_checkout.FastCheckoutBridge;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class ak0 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ ek0 f;

    public /* synthetic */ ak0(ek0 ek0Var, int i) {
        this.e = i;
        this.f = ek0Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        ek0 ek0Var = this.f;
        switch (i) {
            case 0:
                long j = ((FastCheckoutBridge) ek0Var.b).a;
                if (j != 0) {
                    N.MdJsVOZa(j);
                }
                C0173a.a(7);
                break;
            default:
                long j2 = ((FastCheckoutBridge) ek0Var.b).a;
                if (j2 != 0) {
                    N.MzJ7c8YJ(j2);
                }
                C0173a.a(8);
                break;
        }
    }
}
