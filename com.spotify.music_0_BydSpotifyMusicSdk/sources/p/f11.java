package p;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: classes.dex */
public final class f11 implements de2 {
    public byte c;
    public final w02 e;
    public final Inflater k;
    public final m41 l;
    public final CRC32 m;

    public f11(de2 de2Var) {
        v03.j(de2Var, "source");
        w02 w02Var = new w02(de2Var);
        this.e = w02Var;
        Inflater inflater = new Inflater(true);
        this.k = inflater;
        this.l = new m41(w02Var, inflater);
        this.m = new CRC32();
    }

    public static void b(String str, int i, int i2) throws IOException {
        if (i2 == i) {
            return;
        }
        String str2 = String.format("%s: actual 0x%08x != expected 0x%08x", Arrays.copyOf(new Object[]{str, Integer.valueOf(i2), Integer.valueOf(i)}, 3));
        v03.i(str2, "format(...)");
        throw new IOException(str2);
    }

    public final void c(long j, long j2, fo foVar) {
        bb2 bb2Var = foVar.c;
        v03.h(bb2Var);
        while (true) {
            long j3 = bb2Var.c - bb2Var.b;
            if (j < j3) {
                break;
            }
            j -= j3;
            bb2Var = bb2Var.f;
            v03.h(bb2Var);
        }
        while (j2 > 0) {
            int i = (int) (((long) bb2Var.b) + j);
            int iMin = (int) Math.min(bb2Var.c - i, j2);
            this.m.update(bb2Var.a, i, iMin);
            j2 -= (long) iMin;
            bb2Var = bb2Var.f;
            v03.h(bb2Var);
            j = 0;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.l.close();
    }

    @Override // p.de2
    public final tl2 e() {
        return this.e.c.e();
    }

    @Override // p.de2
    public final long t(fo foVar, long j) throws IOException {
        w02 w02Var;
        long j2;
        v03.j(foVar, "sink");
        if (j < 0) {
            throw new IllegalArgumentException(u4.g("byteCount < 0: ", j).toString());
        }
        if (j == 0) {
            return 0L;
        }
        byte b = this.c;
        CRC32 crc32 = this.m;
        w02 w02Var2 = this.e;
        if (b == 0) {
            w02Var2.q0(10L);
            fo foVar2 = w02Var2.e;
            byte bG0 = foVar2.G0(3L);
            boolean z = ((bG0 >> 1) & 1) == 1;
            if (z) {
                c(0L, 10L, w02Var2.e);
            }
            b("ID1ID2", 8075, w02Var2.readShort());
            w02Var2.y(8L);
            if (((bG0 >> 2) & 1) == 1) {
                w02Var2.q0(2L);
                if (z) {
                    c(0L, 2L, w02Var2.e);
                }
                long jO0 = foVar2.O0() & 65535;
                w02Var2.q0(jO0);
                if (z) {
                    c(0L, jO0, w02Var2.e);
                    j2 = jO0;
                } else {
                    j2 = jO0;
                }
                w02Var2.y(j2);
            }
            if (((bG0 >> 3) & 1) == 1) {
                long jB = w02Var2.b((byte) 0, 0L, Long.MAX_VALUE);
                if (jB == -1) {
                    throw new EOFException();
                }
                if (z) {
                    w02Var = w02Var2;
                    c(0L, jB + 1, w02Var2.e);
                } else {
                    w02Var = w02Var2;
                }
                w02Var.y(jB + 1);
            } else {
                w02Var = w02Var2;
            }
            if (((bG0 >> 4) & 1) == 1) {
                long jB2 = w02Var.b((byte) 0, 0L, Long.MAX_VALUE);
                if (jB2 == -1) {
                    throw new EOFException();
                }
                if (z) {
                    c(0L, jB2 + 1, w02Var.e);
                }
                w02Var.y(jB2 + 1);
            }
            if (z) {
                b("FHCRC", w02Var.i(), (short) crc32.getValue());
                crc32.reset();
            }
            this.c = (byte) 1;
        } else {
            w02Var = w02Var2;
        }
        if (this.c == 1) {
            long j3 = foVar.e;
            long jT = this.l.t(foVar, j);
            if (jT != -1) {
                c(j3, jT, foVar);
                return jT;
            }
            this.c = (byte) 2;
        }
        if (this.c != 2) {
            return -1L;
        }
        b("CRC", w02Var.O(), (int) crc32.getValue());
        b("ISIZE", w02Var.O(), (int) this.k.getBytesWritten());
        this.c = (byte) 3;
        if (w02Var.Q()) {
            return -1L;
        }
        throw new IOException("gzip finished without exhausting source");
    }
}
