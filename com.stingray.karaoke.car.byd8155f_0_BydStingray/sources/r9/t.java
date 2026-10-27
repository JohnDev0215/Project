package r9;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes.dex */
public final class t implements J {
    public final D l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Inflater f21944m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f21945n;
    public boolean o;

    public t(D d, Inflater inflater) {
        this.l = d;
        this.f21944m = inflater;
    }

    public final long b(C2319i c2319i, long j3) throws IOException {
        Inflater inflater = this.f21944m;
        A7.m.f(c2319i, "sink");
        if (j3 < 0) {
            throw new IllegalArgumentException(M5.b.f("byteCount < 0: ", j3).toString());
        }
        if (this.o) {
            throw new IllegalStateException("closed");
        }
        if (j3 == 0) {
            return 0L;
        }
        try {
            E eT = c2319i.T(1);
            int iMin = (int) Math.min(j3, 8192 - eT.c);
            boolean zNeedsInput = inflater.needsInput();
            D d = this.l;
            if (zNeedsInput && !d.b()) {
                E e10 = d.f21897m.l;
                A7.m.c(e10);
                int i7 = e10.c;
                int i10 = e10.f21900b;
                int i11 = i7 - i10;
                this.f21945n = i11;
                inflater.setInput(e10.f21899a, i10, i11);
            }
            int iInflate = inflater.inflate(eT.f21899a, eT.c, iMin);
            int i12 = this.f21945n;
            if (i12 != 0) {
                int remaining = i12 - inflater.getRemaining();
                this.f21945n -= remaining;
                d.K(remaining);
            }
            if (iInflate > 0) {
                eT.c += iInflate;
                long j10 = iInflate;
                c2319i.f21932m += j10;
                return j10;
            }
            if (eT.f21900b == eT.c) {
                c2319i.l = eT.a();
                F.a(eT);
            }
            return 0L;
        } catch (DataFormatException e11) {
            throw new IOException(e11);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        if (this.o) {
            return;
        }
        this.f21944m.end();
        this.o = true;
        this.l.close();
    }

    @Override // r9.J
    public final L e() {
        return this.l.l.e();
    }

    @Override // r9.J
    public final long p(C2319i c2319i, long j3) throws IOException {
        A7.m.f(c2319i, "sink");
        do {
            long jB = b(c2319i, j3);
            if (jB > 0) {
                return jB;
            }
            Inflater inflater = this.f21944m;
            if (inflater.finished() || inflater.needsDictionary()) {
                return -1L;
            }
        } while (!this.l.b());
        throw new EOFException("source exhausted prematurely");
    }
}
