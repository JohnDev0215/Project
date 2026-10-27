package j9;

import java.net.SocketTimeoutException;
import r9.C2315e;

/* JADX INFO: loaded from: classes.dex */
public final class t extends C2315e {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ u f17579m;

    public t(u uVar) {
        this.f17579m = uVar;
    }

    @Override // r9.C2315e
    public final void k() {
        this.f17579m.e(9);
        m mVar = this.f17579m.f17581b;
        synchronized (mVar) {
            long j3 = mVar.f17559y;
            long j10 = mVar.f17558x;
            if (j3 < j10) {
                return;
            }
            mVar.f17558x = j10 + 1;
            mVar.f17560z = System.nanoTime() + ((long) 1000000000);
            mVar.f17553s.c(new e9.f(android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder(), mVar.f17549n, " ping"), mVar, 3), 0L);
        }
    }

    public final void l() {
        if (j()) {
            throw new SocketTimeoutException("timeout");
        }
    }
}
