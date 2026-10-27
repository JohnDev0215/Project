package g;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* JADX INFO: compiled from: GzipSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class h implements p {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final e f5476c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Inflater f5477d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final i f5478e;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5475b = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final CRC32 f5479f = new CRC32();

    public h(p pVar) {
        if (pVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        Inflater inflater = new Inflater(true);
        this.f5477d = inflater;
        e eVarB = j.b(pVar);
        this.f5476c = eVarB;
        this.f5478e = new i(eVarB, inflater);
    }

    public final void O() throws IOException {
        w("CRC", this.f5476c.B(), (int) this.f5479f.getValue());
        w("ISIZE", this.f5476c.B(), (int) this.f5477d.getBytesWritten());
    }

    public final void P(c cVar, long j, long j2) {
        m mVar = cVar.f5470c;
        while (true) {
            int i2 = mVar.f5498c;
            int i3 = mVar.f5497b;
            if (j < i2 - i3) {
                break;
            }
            j -= (long) (i2 - i3);
            mVar = mVar.f5501f;
        }
        while (j2 > 0) {
            int i4 = (int) (((long) mVar.f5497b) + j);
            int iMin = (int) Math.min(mVar.f5498c - i4, j2);
            this.f5479f.update(mVar.f5496a, i4, iMin);
            j2 -= (long) iMin;
            mVar = mVar.f5501f;
            j = 0;
        }
    }

    @Override // g.p
    public q b() {
        return this.f5476c.b();
    }

    @Override // g.p, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f5478e.close();
    }

    @Override // g.p
    public long m(c cVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (j == 0) {
            return 0L;
        }
        if (this.f5475b == 0) {
            z();
            this.f5475b = 1;
        }
        if (this.f5475b == 1) {
            long j2 = cVar.f5471d;
            long jM = this.f5478e.m(cVar, j);
            if (jM != -1) {
                P(cVar, j2, jM);
                return jM;
            }
            this.f5475b = 2;
        }
        if (this.f5475b == 2) {
            O();
            this.f5475b = 3;
            if (!this.f5476c.D()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    public final void w(String str, int i2, int i3) throws IOException {
        if (i3 != i2) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i3), Integer.valueOf(i2)));
        }
    }

    public final void z() throws IOException {
        this.f5476c.y(10L);
        byte bT = this.f5476c.a().T(3L);
        boolean z = ((bT >> 1) & 1) == 1;
        if (z) {
            P(this.f5476c.a(), 0L, 10L);
        }
        w("ID1ID2", 8075, this.f5476c.p());
        this.f5476c.o(8L);
        if (((bT >> 2) & 1) == 1) {
            this.f5476c.y(2L);
            if (z) {
                P(this.f5476c.a(), 0L, 2L);
            }
            long jI = this.f5476c.a().i();
            this.f5476c.y(jI);
            if (z) {
                P(this.f5476c.a(), 0L, jI);
            }
            this.f5476c.o(jI);
        }
        if (((bT >> 3) & 1) == 1) {
            long jF = this.f5476c.F((byte) 0);
            if (jF == -1) {
                throw new EOFException();
            }
            if (z) {
                P(this.f5476c.a(), 0L, jF + 1);
            }
            this.f5476c.o(jF + 1);
        }
        if (((bT >> 4) & 1) == 1) {
            long jF2 = this.f5476c.F((byte) 0);
            if (jF2 == -1) {
                throw new EOFException();
            }
            if (z) {
                P(this.f5476c.a(), 0L, jF2 + 1);
            }
            this.f5476c.o(jF2 + 1);
        }
        if (z) {
            w("FHCRC", this.f5476c.i(), (short) this.f5479f.getValue());
            this.f5479f.reset();
        }
    }
}
