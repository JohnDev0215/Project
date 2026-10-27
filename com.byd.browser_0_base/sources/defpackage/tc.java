package defpackage;

import defpackage.uc;
import defpackage.wc;
import org.chromium.gms.ChromiumPlayServicesAvailability;

/* JADX INFO: compiled from: BarcodeDetectionProviderImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class tc implements sc {
    public static tc t() {
        if (!ChromiumPlayServicesAvailability.isGooglePlayServicesAvailable(xz.c())) {
            fa1.j();
            return null;
        }
        int iD = zt1.d("com.google.android.gms");
        if (iD >= 19742000) {
            return new tc();
        }
        if (iD < 0) {
            fa1.j();
        } else {
            fa1.k("BarcodeProviderImpl", "Detection disabled (%d < 19.7.42)", Integer.valueOf(iD));
        }
        return null;
    }

    @Override // defpackage.sc
    public final void N(f21<qc> f21Var, xc xcVar) {
        rc rcVar = new rc(xcVar);
        ch1 ch1VarF0 = f21Var.f0();
        xc2 xc2Var = new xc2(ch1VarF0);
        k00 k00VarW0 = ch1VarF0.w0();
        xc2Var.e.i = rcVar;
        xc2Var.f = new wc.g(k00VarW0, rcVar);
        xc2Var.q();
    }

    @Override // defpackage.sc
    public final void m(uc.f fVar) {
        fVar.c(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 13});
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
    }
}
