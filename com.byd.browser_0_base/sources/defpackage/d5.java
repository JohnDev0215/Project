package defpackage;

import org.chromium.components.payments.JniPaymentApp;
import org.chromium.components.payments.PaymentApp;
import org.chromium.components.payments.a;
import org.chromium.components.payments.e;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes3.dex */
public final /* synthetic */ class d5 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ boolean f;
    public final /* synthetic */ PaymentApp g;

    public /* synthetic */ d5(PaymentApp paymentApp, boolean z, int i) {
        this.e = i;
        this.g = paymentApp;
        this.f = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.e;
        boolean z = this.f;
        PaymentApp paymentApp = this.g;
        switch (i) {
            case 0:
                ((a) paymentApp).A(z);
                break;
            default:
                JniPaymentApp jniPaymentApp = (JniPaymentApp) paymentApp;
                PaymentApp.a aVar = jniPaymentApp.n;
                if (aVar != null) {
                    ((e) aVar).n(z);
                    jniPaymentApp.n = null;
                    break;
                }
                break;
        }
    }
}
