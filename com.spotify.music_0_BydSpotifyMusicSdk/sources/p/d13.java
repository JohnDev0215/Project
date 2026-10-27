package p;

import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class d13 extends s31 {
    public final s31 c;
    public final long e;
    public final long k;

    public d13(com.google.android.play.core.assetpacks.d dVar, long j, long j2) {
        this.c = dVar;
        long jE = E(j);
        this.e = jE;
        this.k = E(jE + j2);
    }

    public final long E(long j) {
        if (j < 0) {
            return 0L;
        }
        s31 s31Var = this.c;
        return j > s31Var.i() ? s31Var.i() : j;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
    }

    @Override // p.s31
    public final long i() {
        return this.k - this.e;
    }

    @Override // p.s31
    public final InputStream q(long j, long j2) {
        long jE = E(this.e);
        return this.c.q(jE, E(j2 + jE) - jE);
    }
}
