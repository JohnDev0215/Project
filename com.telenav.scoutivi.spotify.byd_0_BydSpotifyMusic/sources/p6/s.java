package p6;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: compiled from: JvmOkio.kt */
/* JADX INFO: loaded from: classes.dex */
final class s implements y {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final OutputStream f8892e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final b0 f8893g;

    public s(OutputStream out, b0 timeout) {
        kotlin.jvm.internal.l.e(out, "out");
        kotlin.jvm.internal.l.e(timeout, "timeout");
        this.f8892e = out;
        this.f8893g = timeout;
    }

    @Override // p6.y
    public b0 c() {
        return this.f8893g;
    }

    @Override // p6.y, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f8892e.close();
    }

    @Override // p6.y, java.io.Flushable
    public void flush() throws IOException {
        this.f8892e.flush();
    }

    @Override // p6.y
    public void l(e source, long j7) throws IOException {
        kotlin.jvm.internal.l.e(source, "source");
        c.b(source.j0(), 0L, j7);
        while (j7 > 0) {
            this.f8893g.f();
            v vVar = source.f8866e;
            kotlin.jvm.internal.l.c(vVar);
            int iMin = (int) Math.min(j7, vVar.f8904c - vVar.f8903b);
            this.f8892e.write(vVar.f8902a, vVar.f8903b, iMin);
            vVar.f8903b += iMin;
            long j8 = iMin;
            j7 -= j8;
            source.i0(source.j0() - j8);
            if (vVar.f8903b == vVar.f8904c) {
                source.f8866e = vVar.b();
                w.b(vVar);
            }
        }
    }

    public String toString() {
        return "sink(" + this.f8892e + ')';
    }
}
