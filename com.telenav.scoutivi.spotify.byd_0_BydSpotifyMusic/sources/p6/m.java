package p6;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX INFO: compiled from: InflaterSource.kt */
/* JADX INFO: loaded from: classes.dex */
public final class m implements a0 {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f8882e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f8883g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final g f8884h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private final Inflater f8885i;

    public m(g source, Inflater inflater) {
        kotlin.jvm.internal.l.e(source, "source");
        kotlin.jvm.internal.l.e(inflater, "inflater");
        this.f8884h = source;
        this.f8885i = inflater;
    }

    private final void A() {
        int i7 = this.f8882e;
        if (i7 == 0) {
            return;
        }
        int remaining = i7 - this.f8885i.getRemaining();
        this.f8882e -= remaining;
        this.f8884h.a(remaining);
    }

    @Override // p6.a0
    public b0 c() {
        return this.f8884h.c();
    }

    @Override // p6.a0, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.f8883g) {
            return;
        }
        this.f8885i.end();
        this.f8883g = true;
        this.f8884h.close();
    }

    @Override // p6.a0
    public long i(e sink, long j7) throws IOException {
        kotlin.jvm.internal.l.e(sink, "sink");
        do {
            long jM = m(sink, j7);
            if (jM > 0) {
                return jM;
            }
            if (this.f8885i.finished() || this.f8885i.needsDictionary()) {
                return -1L;
            }
        } while (!this.f8884h.J());
        throw new EOFException("source exhausted prematurely");
    }

    public final long m(e sink, long j7) throws IOException {
        kotlin.jvm.internal.l.e(sink, "sink");
        if (!(j7 >= 0)) {
            throw new IllegalArgumentException(("byteCount < 0: " + j7).toString());
        }
        if (!(!this.f8883g)) {
            throw new IllegalStateException("closed".toString());
        }
        if (j7 == 0) {
            return 0L;
        }
        try {
            v vVarM0 = sink.m0(1);
            int iMin = (int) Math.min(j7, 8192 - vVarM0.f8904c);
            z();
            int iInflate = this.f8885i.inflate(vVarM0.f8902a, vVarM0.f8904c, iMin);
            A();
            if (iInflate > 0) {
                vVarM0.f8904c += iInflate;
                long j8 = iInflate;
                sink.i0(sink.j0() + j8);
                return j8;
            }
            if (vVarM0.f8903b == vVarM0.f8904c) {
                sink.f8866e = vVarM0.b();
                w.b(vVarM0);
            }
            return 0L;
        } catch (DataFormatException e7) {
            throw new IOException(e7);
        }
    }

    public final boolean z() {
        if (!this.f8885i.needsInput()) {
            return false;
        }
        if (this.f8884h.J()) {
            return true;
        }
        v vVar = this.f8884h.b().f8866e;
        kotlin.jvm.internal.l.c(vVar);
        int i7 = vVar.f8904c;
        int i8 = vVar.f8903b;
        int i9 = i7 - i8;
        this.f8882e = i9;
        this.f8885i.setInput(vVar.f8902a, i8, i9);
        return false;
    }
}
