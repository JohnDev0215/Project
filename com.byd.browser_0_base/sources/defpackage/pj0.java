package defpackage;

import defpackage.sj0;
import org.chromium.gms.ChromiumPlayServicesAvailability;

/* JADX INFO: compiled from: FaceDetectionProviderImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class pj0 implements oj0 {
    @Override // defpackage.oj0
    public final void g0(f21<kj0> f21Var, tj0 tj0Var) {
        boolean zIsGooglePlayServicesAvailable = ChromiumPlayServicesAvailability.isGooglePlayServicesAvailable(xz.c());
        sj0.a aVar = sj0.a;
        if (zIsGooglePlayServicesAvailable) {
            aVar.c(new nj0(tj0Var), f21Var);
        } else {
            aVar.c(new mj0(tj0Var), f21Var);
        }
    }

    @Override // defpackage.y11, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
    }
}
