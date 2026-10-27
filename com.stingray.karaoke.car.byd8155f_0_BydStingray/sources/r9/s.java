package r9;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes.dex */
public final class s implements J {
    public byte l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final D f21941m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Inflater f21942n;
    public final t o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final CRC32 f21943p;

    public s(J j3) {
        A7.m.f(j3, BYDAutoMultimediaDevice.PARAM_SOURCE);
        D d = new D(j3);
        this.f21941m = d;
        Inflater inflater = new Inflater(true);
        this.f21942n = inflater;
        this.o = new t(d, inflater);
        this.f21943p = new CRC32();
    }

    public static void b(String str, int i7, int i10) throws IOException {
        if (i10 == i7) {
            return;
        }
        StringBuilder sbL = A7.k.l(str, ": actual 0x");
        sbL.append(Q8.h.Q0(8, AbstractC2312b.l(i10)));
        sbL.append(" != expected 0x");
        sbL.append(Q8.h.Q0(8, AbstractC2312b.l(i7)));
        throw new IOException(sbL.toString());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.o.close();
    }

    @Override // r9.J
    public final L e() {
        return this.f21941m.l.e();
    }

    public final void g(C2319i c2319i, long j3, long j10) {
        E e10 = c2319i.l;
        A7.m.c(e10);
        while (true) {
            int i7 = e10.c;
            int i10 = e10.f21900b;
            if (j3 < i7 - i10) {
                break;
            }
            j3 -= (long) (i7 - i10);
            e10 = e10.f21902f;
            A7.m.c(e10);
        }
        while (j10 > 0) {
            int i11 = (int) (((long) e10.f21900b) + j3);
            int iMin = (int) Math.min(e10.c - i11, j10);
            this.f21943p.update(e10.f21899a, i11, iMin);
            j10 -= (long) iMin;
            e10 = e10.f21902f;
            A7.m.c(e10);
            j3 = 0;
        }
    }

    @Override // r9.J
    public final long p(C2319i c2319i, long j3) throws IOException {
        D d;
        C2319i c2319i2;
        long j10;
        A7.m.f(c2319i, "sink");
        if (j3 < 0) {
            throw new IllegalArgumentException(M5.b.f("byteCount < 0: ", j3).toString());
        }
        if (j3 == 0) {
            return 0L;
        }
        byte b10 = this.l;
        CRC32 crc32 = this.f21943p;
        D d10 = this.f21941m;
        if (b10 == 0) {
            d10.I(10L);
            C2319i c2319i3 = d10.f21897m;
            byte bK = c2319i3.k(3L);
            boolean z6 = ((bK >> 1) & 1) == 1;
            if (z6) {
                g(c2319i3, 0L, 10L);
            }
            b("ID1ID2", 8075, d10.z());
            d10.K(8L);
            if (((bK >> 2) & 1) == 1) {
                d10.I(2L);
                if (z6) {
                    g(c2319i3, 0L, 2L);
                }
                long jO = c2319i3.O() & 65535;
                d10.I(jO);
                if (z6) {
                    g(c2319i3, 0L, jO);
                    j10 = jO;
                } else {
                    j10 = jO;
                }
                d10.K(j10);
            }
            if (((bK >> 3) & 1) == 1) {
                c2319i2 = c2319i3;
                long jG = d10.g((byte) 0, 0L, Long.MAX_VALUE);
                if (jG == -1) {
                    throw new EOFException();
                }
                if (z6) {
                    d = d10;
                    g(c2319i2, 0L, jG + 1);
                } else {
                    d = d10;
                }
                d.K(jG + 1);
            } else {
                c2319i2 = c2319i3;
                d = d10;
            }
            if (((bK >> 4) & 1) == 1) {
                long jG2 = d.g((byte) 0, 0L, Long.MAX_VALUE);
                if (jG2 == -1) {
                    throw new EOFException();
                }
                if (z6) {
                    g(c2319i2, 0L, jG2 + 1);
                }
                d.K(jG2 + 1);
            }
            if (z6) {
                b("FHCRC", d.C(), (short) crc32.getValue());
                crc32.reset();
            }
            this.l = (byte) 1;
        } else {
            d = d10;
        }
        if (this.l == 1) {
            long j11 = c2319i.f21932m;
            long jP = this.o.p(c2319i, j3);
            if (jP != -1) {
                g(c2319i, j11, jP);
                return jP;
            }
            this.l = (byte) 2;
        }
        if (this.l != 2) {
            return -1L;
        }
        b("CRC", d.v(), (int) crc32.getValue());
        b("ISIZE", d.v(), (int) this.f21942n.getBytesWritten());
        this.l = (byte) 3;
        if (d.b()) {
            return -1L;
        }
        throw new IOException("gzip finished without exhausting source");
    }
}
