package org.chromium.chrome.browser.net.nqe;

import J.N;
import defpackage.xl1;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.b;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkQualityProvider {
    public final b.a a = new b().d();
    public Integer b;

    public NetworkQualityProvider() {
        N.M0KGRuJY(this);
    }

    @CalledByNative
    public void onEffectiveConnectionTypeChanged(int i) {
        this.b = Integer.valueOf(i);
        b.a aVar = this.a;
        aVar.c();
        while (aVar.hasNext()) {
            xl1 xl1Var = (xl1) aVar.next();
            this.b.intValue();
            xl1Var.getClass();
        }
    }

    @CalledByNative
    public void onRTTOrThroughputEstimatesComputed(long j, long j2, int i) {
        b.a aVar = this.a;
        aVar.c();
        while (aVar.hasNext()) {
            ((xl1) aVar.next()).getClass();
        }
    }
}
