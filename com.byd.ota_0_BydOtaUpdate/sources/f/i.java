package f;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* JADX INFO: compiled from: GzipSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class i implements q {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final e f3708d;
    public final Inflater s;
    public final j x;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3707c = 0;
    public final CRC32 y = new CRC32();

    public i(q qVar) {
        if (qVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        Inflater inflater = new Inflater(true);
        this.s = inflater;
        e eVarB = k.b(qVar);
        this.f3708d = eVarB;
        this.x = new j(eVarB, inflater);
    }

    public final void c(String str, int i2, int i3) throws IOException {
        if (i3 != i2) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i3), Integer.valueOf(i2)));
        }
    }

    @Override // f.q, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.x.close();
    }

    public final void d() throws IOException {
        this.f3708d.u(10L);
        byte bM = this.f3708d.a().M(3L);
        boolean z = ((bM >> 1) & 1) == 1;
        if (z) {
            v(this.f3708d.a(), 0L, 10L);
        }
        c("ID1ID2", 8075, this.f3708d.readShort());
        this.f3708d.skip(8L);
        if (((bM >> 2) & 1) == 1) {
            this.f3708d.u(2L);
            if (z) {
                v(this.f3708d.a(), 0L, 2L);
            }
            long j = this.f3708d.a().j();
            this.f3708d.u(j);
            if (z) {
                v(this.f3708d.a(), 0L, j);
            }
            this.f3708d.skip(j);
        }
        if (((bM >> 3) & 1) == 1) {
            long jB = this.f3708d.B((byte) 0);
            if (jB == -1) {
                throw new EOFException();
            }
            if (z) {
                v(this.f3708d.a(), 0L, jB + 1);
            }
            this.f3708d.skip(jB + 1);
        }
        if (((bM >> 4) & 1) == 1) {
            long jB2 = this.f3708d.B((byte) 0);
            if (jB2 == -1) {
                throw new EOFException();
            }
            if (z) {
                v(this.f3708d.a(), 0L, jB2 + 1);
            }
            this.f3708d.skip(jB2 + 1);
        }
        if (z) {
            c("FHCRC", this.f3708d.j(), (short) this.y.getValue());
            this.y.reset();
        }
    }

    @Override // f.q
    public r e() {
        return this.f3708d.e();
    }

    @Override // f.q
    public long n(c cVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (j == 0) {
            return 0L;
        }
        if (this.f3707c == 0) {
            d();
            this.f3707c = 1;
        }
        if (this.f3707c == 1) {
            long j2 = cVar.s;
            long jN = this.x.n(cVar, j);
            if (jN != -1) {
                v(cVar, j2, jN);
                return jN;
            }
            this.f3707c = 2;
        }
        if (this.f3707c == 2) {
            s();
            this.f3707c = 3;
            if (!this.f3708d.z()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    public final void s() throws IOException {
        c("CRC", this.f3708d.x(), (int) this.y.getValue());
        c("ISIZE", this.f3708d.x(), (int) this.s.getBytesWritten());
    }

    public final void v(c cVar, long j, long j2) {
        n nVar = cVar.f3701d;
        while (true) {
            int i2 = nVar.f3723c;
            int i3 = nVar.f3722b;
            if (j < i2 - i3) {
                break;
            }
            j -= (long) (i2 - i3);
            nVar = nVar.f3726f;
        }
        while (j2 > 0) {
            int i4 = (int) (((long) nVar.f3722b) + j);
            int iMin = (int) Math.min(nVar.f3723c - i4, j2);
            this.y.update(nVar.f3721a, i4, iMin);
            j2 -= (long) iMin;
            nVar = nVar.f3726f;
            j = 0;
        }
    }
}
