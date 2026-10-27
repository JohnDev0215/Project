package org.chromium.components.payments;

import J.N;
import defpackage.my1;
import defpackage.ny1;
import defpackage.py1;
import defpackage.xq;
import org.chromium.base.ThreadUtils;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.RenderFrameHost;

/* JADX INFO: loaded from: classes3.dex */
public final class PaymentAppServiceBridge implements ny1 {

    public class PaymentAppServiceCallback {
        public final my1 a;
        public final CSPCheckerBridge b;

        public PaymentAppServiceCallback(py1.a aVar, CSPCheckerBridge cSPCheckerBridge) {
            this.a = aVar;
            this.b = cSPCheckerBridge;
        }

        @CalledByNative
        public final void onCanMakePaymentCalculated(boolean z) {
            Object obj = ThreadUtils.a;
            this.a.b(z);
        }

        @CalledByNative
        public final void onDoneCreatingPaymentApps() {
            Object obj = ThreadUtils.a;
            CSPCheckerBridge cSPCheckerBridge = this.b;
            long j = cSPCheckerBridge.b;
            if (j != 0) {
                N.M9IMexew(j);
                cSPCheckerBridge.b = 0L;
            }
            this.a.e(PaymentAppServiceBridge.this);
        }

        @CalledByNative
        public final void onPaymentAppCreated(PaymentApp paymentApp) {
            Object obj = ThreadUtils.a;
            this.a.i(paymentApp);
        }

        @CalledByNative
        public final void onPaymentAppCreationError(String str, int i) {
            Object obj = ThreadUtils.a;
            this.a.c(str, i);
        }

        @CalledByNative
        public final void setCanMakePaymentEvenWithoutApps() {
            Object obj = ThreadUtils.a;
            this.a.g();
        }

        @CalledByNative
        public final void setOptOutOffered() {
            Object obj = ThreadUtils.a;
            this.a.d();
        }
    }

    @Override // defpackage.ny1
    public final void a(my1 my1Var) {
        py1.a aVar = (py1.a) my1Var;
        if (((e) aVar.f()).u || ((e) aVar.f()).b.g() == null || ((e) aVar.f()).b.b() == null || ((e) aVar.f()).f.isDestroyed()) {
            return;
        }
        CSPCheckerBridge cSPCheckerBridge = new CSPCheckerBridge(aVar.h());
        PaymentAppServiceCallback paymentAppServiceCallback = new PaymentAppServiceCallback(aVar, cSPCheckerBridge);
        RenderFrameHost renderFrameHost = ((e) aVar.f()).b;
        String str = ((e) aVar.f()).h;
        PaymentRequestSpec paymentRequestSpec = ((e) aVar.f()).t;
        String strB = ((xq.a) ((e) aVar.f()).c).b();
        ((e) aVar.f()).B.getClass();
        N.MJN0LyJO(renderFrameHost, str, paymentRequestSpec, strB, true, ((e) aVar.f()).m, cSPCheckerBridge.b, paymentAppServiceCallback);
    }
}
