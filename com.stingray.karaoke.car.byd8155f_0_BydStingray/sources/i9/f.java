package i9;

import A7.m;
import java.io.IOException;
import r9.C2319i;

/* JADX INFO: loaded from: classes.dex */
public final class f extends a {
    public boolean o;

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f15800m) {
            return;
        }
        if (!this.o) {
            b();
        }
        this.f15800m = true;
    }

    @Override // i9.a, r9.J
    public final long p(C2319i c2319i, long j3) throws IOException {
        m.f(c2319i, "sink");
        if (j3 < 0) {
            throw new IllegalArgumentException(M5.b.f("byteCount < 0: ", j3).toString());
        }
        if (this.f15800m) {
            throw new IllegalStateException("closed");
        }
        if (this.o) {
            return -1L;
        }
        long jP = super.p(c2319i, j3);
        if (jP != -1) {
            return jP;
        }
        this.o = true;
        b();
        return -1L;
    }
}
