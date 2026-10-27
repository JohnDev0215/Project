package org.chromium.components.payments;

import J.N;
import defpackage.ke0;
import defpackage.mk;
import defpackage.pz1;
import defpackage.yb;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes3.dex */
public class CSPCheckerBridge {
    public final mk a;
    public long b = N.MPASPGV9(this);

    public CSPCheckerBridge(mk mkVar) {
        this.a = mkVar;
    }

    @CalledByNative
    public void allowConnectToSource(GURL gurl, GURL gurl2, boolean z, int i) {
        yb ybVar = new yb(i, 3, this);
        pz1 pz1Var = ((e) this.a).A;
        if (pz1Var == null) {
            return;
        }
        pz1Var.v(gurl.n(), gurl2.n(), z, new ke0(2, ybVar));
    }
}
