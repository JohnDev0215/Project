package p6;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: compiled from: JvmOkio.kt */
/* JADX INFO: loaded from: classes.dex */
final class n implements a0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final InputStream f8886e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final b0 f8887g;

    public n(InputStream input, b0 timeout) {
        kotlin.jvm.internal.l.e(input, "input");
        kotlin.jvm.internal.l.e(timeout, "timeout");
        this.f8886e = input;
        this.f8887g = timeout;
    }

    @Override // p6.a0
    public b0 c() {
        return this.f8887g;
    }

    @Override // p6.a0, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f8886e.close();
    }

    @Override // p6.a0
    public long i(e sink, long j7) throws IOException {
        kotlin.jvm.internal.l.e(sink, "sink");
        if (j7 == 0) {
            return 0L;
        }
        if (!(j7 >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
        }
        try {
            this.f8887g.f();
            v vVarM0 = sink.m0(1);
            int i7 = this.f8886e.read(vVarM0.f8902a, vVarM0.f8904c, (int) Math.min(j7, 8192 - vVarM0.f8904c));
            if (i7 != -1) {
                vVarM0.f8904c += i7;
                long j8 = i7;
                sink.i0(sink.j0() + j8);
                return j8;
            }
            if (vVarM0.f8903b != vVarM0.f8904c) {
                return -1L;
            }
            sink.f8866e = vVarM0.b();
            w.b(vVarM0);
            return -1L;
        } catch (AssertionError e7) {
            if (o.c(e7)) {
                throw new IOException(e7);
            }
            throw e7;
        }
    }

    public String toString() {
        return "source(" + this.f8886e + ')';
    }
}
