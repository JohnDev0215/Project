package i9;

import g9.m;
import i8.C1417b;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import r9.C2319i;

/* JADX INFO: loaded from: classes.dex */
public final class d extends a {
    public long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ C1417b f15807p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(C1417b c1417b, long j3) {
        super(c1417b);
        this.f15807p = c1417b;
        this.o = j3;
        if (j3 == 0) {
            b();
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.f15800m) {
            return;
        }
        if (this.o != 0 && !d9.b.h(this, TimeUnit.MILLISECONDS)) {
            ((m) this.f15807p.d).k();
            b();
        }
        this.f15800m = true;
    }

    @Override // i9.a, r9.J
    public final long p(C2319i c2319i, long j3) throws IOException {
        A7.m.f(c2319i, "sink");
        if (j3 < 0) {
            throw new IllegalArgumentException(M5.b.f("byteCount < 0: ", j3).toString());
        }
        if (this.f15800m) {
            throw new IllegalStateException("closed");
        }
        long j10 = this.o;
        if (j10 == 0) {
            return -1L;
        }
        long jP = super.p(c2319i, Math.min(j10, j3));
        if (jP == -1) {
            ((m) this.f15807p.d).k();
            ProtocolException protocolException = new ProtocolException("unexpected end of stream");
            b();
            throw protocolException;
        }
        long j11 = this.o - jP;
        this.o = j11;
        if (j11 == 0) {
            b();
        }
        return jP;
    }
}
