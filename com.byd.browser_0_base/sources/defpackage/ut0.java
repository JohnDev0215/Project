package defpackage;

import J.N;
import android.util.Log;
import org.chromium.mojo.system.impl.CoreImpl;

/* JADX INFO: compiled from: HandleBase.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class ut0 implements tt0 {
    public long e;
    public final CoreImpl f;

    public ut0(CoreImpl coreImpl, long j) {
        this.f = coreImpl;
        this.e = j;
    }

    @Override // defpackage.tt0
    public final ej3 A0() {
        return new fj3(this);
    }

    @Override // defpackage.tt0, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        long j = this.e;
        if (j != 0) {
            this.e = 0L;
            CoreImpl coreImpl = this.f;
            coreImpl.getClass();
            int iMLQe1QEw = N.MLQe1QEw(coreImpl, j);
            if (iMLQe1QEw != 0) {
                throw new ki1(iMLQe1QEw);
            }
        }
    }

    public final void finalize() throws Throwable {
        if (isValid()) {
            Log.w("cr_HandleImpl", "Handle was not closed.");
            long j = this.e;
            CoreImpl coreImpl = this.f;
            coreImpl.getClass();
            N.MLQe1QEw(coreImpl, j);
        }
        super.finalize();
    }

    @Override // defpackage.tt0
    public final boolean isValid() {
        return this.e != 0;
    }

    @Override // defpackage.tt0
    public final long s0() {
        long j = this.e;
        this.e = 0L;
        return j;
    }

    @Override // defpackage.tt0
    public final k00 w0() {
        return this.f;
    }

    public ut0(ut0 ut0Var) {
        this.f = ut0Var.f;
        long j = ut0Var.e;
        ut0Var.e = 0L;
        this.e = j;
    }
}
