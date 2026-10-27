package p;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import okio.ByteString;

/* JADX INFO: loaded from: classes.dex */
public final class w02 implements ko {
    public final de2 c;
    public final fo e;
    public boolean k;

    public w02(de2 de2Var) {
        v03.j(de2Var, "source");
        this.c = de2Var;
        this.e = new fo();
    }

    @Override // p.ko
    public final String A0(Charset charset) {
        fo foVar = this.e;
        foVar.L(this.c);
        return foVar.P0(foVar.e, charset);
    }

    @Override // p.ko
    public final wo C0() {
        return new wo(this, 2);
    }

    @Override // p.ko
    public final String M() {
        return g0(Long.MAX_VALUE);
    }

    @Override // p.ko
    public final int O() throws EOFException {
        q0(4L);
        return this.e.O();
    }

    @Override // p.ko
    public final long P(ByteString byteString) {
        v03.j(byteString, "bytes");
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        long jMax = 0;
        while (true) {
            fo foVar = this.e;
            long jI0 = foVar.I0(jMax, byteString);
            if (jI0 != -1) {
                return jI0;
            }
            long j = foVar.e;
            if (this.c.t(foVar, 8192L) == -1) {
                return -1L;
            }
            jMax = Math.max(jMax, (j - ((long) byteString.e())) + 1);
        }
    }

    @Override // p.ko
    public final boolean Q() {
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        fo foVar = this.e;
        return foVar.Q() && this.c.t(foVar, 8192L) == -1;
    }

    @Override // p.ko
    public final boolean a(long j) {
        fo foVar;
        if (j < 0) {
            throw new IllegalArgumentException(u4.g("byteCount < 0: ", j).toString());
        }
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        do {
            foVar = this.e;
            if (foVar.e >= j) {
                return true;
            }
        } while (this.c.t(foVar, 8192L) != -1);
        return false;
    }

    public final long b(byte b, long j, long j2) {
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        long jMax = 0;
        if (0 > j2) {
            throw new IllegalArgumentException(u4.g("fromIndex=0 toIndex=", j2).toString());
        }
        while (jMax < j2) {
            long jH0 = this.e.H0(b, jMax, j2);
            if (jH0 != -1) {
                return jH0;
            }
            fo foVar = this.e;
            long j3 = foVar.e;
            if (j3 >= j2 || this.c.t(foVar, 8192L) == -1) {
                return -1L;
            }
            jMax = Math.max(jMax, j3);
        }
        return -1L;
    }

    @Override // p.ko
    public final long b0(ByteString byteString) {
        v03.j(byteString, "targetBytes");
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        long jMax = 0;
        while (true) {
            fo foVar = this.e;
            long jJ0 = foVar.J0(jMax, byteString);
            if (jJ0 != -1) {
                return jJ0;
            }
            long j = foVar.e;
            if (this.c.t(foVar, 8192L) == -1) {
                return -1L;
            }
            jMax = Math.max(jMax, j);
        }
    }

    public final long c() throws EOFException {
        fo foVar;
        byte bG0;
        q0(1L);
        long j = 0;
        while (true) {
            long j2 = j + 1;
            boolean zA = a(j2);
            foVar = this.e;
            if (!zA) {
                break;
            }
            bG0 = foVar.G0(j);
            if ((bG0 < 48 || bG0 > 57) && !(j == 0 && bG0 == 45)) {
                break;
            }
            j = j2;
        }
        if (j == 0) {
            bk0.k(16);
            String string = Integer.toString(bG0, 16);
            v03.i(string, "toString(...)");
            throw new NumberFormatException("Expected a digit or '-' but was 0x".concat(string));
        }
        return foVar.N0();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public final void close() throws IOException {
        if (this.k) {
            return;
        }
        this.k = true;
        this.c.close();
        this.e.X();
    }

    @Override // p.ko, p.io
    public final fo d() {
        return this.e;
    }

    @Override // p.ko
    public final long d0(io ioVar) {
        fo foVar;
        long j = 0;
        while (true) {
            de2 de2Var = this.c;
            foVar = this.e;
            if (de2Var.t(foVar, 8192L) == -1) {
                break;
            }
            long jO0 = foVar.o0();
            if (jO0 > 0) {
                j += jO0;
                ioVar.Y(foVar, jO0);
            }
        }
        long j2 = foVar.e;
        if (j2 <= 0) {
            return j;
        }
        long j3 = j + j2;
        ioVar.Y(foVar, j2);
        return j3;
    }

    @Override // p.de2
    public final tl2 e() {
        return this.c.e();
    }

    @Override // p.ko
    public final long f0() throws EOFException {
        q0(8L);
        return this.e.f0();
    }

    @Override // p.ko
    public final String g0(long j) throws EOFException {
        if (j < 0) {
            throw new IllegalArgumentException(u4.g("limit < 0: ", j).toString());
        }
        long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
        long jB = b((byte) 10, 0L, j2);
        fo foVar = this.e;
        if (jB != -1) {
            return d.b(foVar, jB);
        }
        if (j2 < Long.MAX_VALUE && a(j2) && foVar.G0(j2 - 1) == 13 && a(1 + j2) && foVar.G0(j2) == 10) {
            return d.b(foVar, j2);
        }
        fo foVar2 = new fo();
        foVar.E0(0L, Math.min(32, foVar.e), foVar2);
        throw new EOFException("\\n not found: limit=" + Math.min(foVar.e, j) + " content=" + foVar2.v(foVar2.e).f() + (char) 8230);
    }

    public final short i() throws EOFException {
        q0(2L);
        return this.e.O0();
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.k;
    }

    @Override // p.ko
    public final void p(fo foVar, long j) throws EOFException {
        fo foVar2 = this.e;
        v03.j(foVar, "sink");
        try {
            q0(j);
            foVar2.p(foVar, j);
        } catch (EOFException e) {
            foVar.L(foVar2);
            throw e;
        }
    }

    @Override // p.ko
    public final int p0(ar1 ar1Var) throws EOFException {
        v03.j(ar1Var, "options");
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        while (true) {
            fo foVar = this.e;
            int iC = d.c(foVar, ar1Var, true);
            if (iC != -2) {
                if (iC != -1) {
                    foVar.y(ar1Var.c[iC].e());
                    return iC;
                }
            } else if (this.c.t(foVar, 8192L) == -1) {
                break;
            }
        }
        return -1;
    }

    @Override // p.ko
    public final w02 peek() {
        return v03.d(new ss1(this));
    }

    public final String q(long j) throws EOFException {
        q0(j);
        fo foVar = this.e;
        foVar.getClass();
        return foVar.P0(j, cs.a);
    }

    @Override // p.ko
    public final void q0(long j) throws EOFException {
        if (!a(j)) {
            throw new EOFException();
        }
    }

    @Override // p.ko
    public final int read(byte[] bArr) {
        long length = bArr.length;
        q30.i(bArr.length, 0, length);
        fo foVar = this.e;
        if (foVar.e == 0 && this.c.t(foVar, 8192L) == -1) {
            return -1;
        }
        return foVar.K0(bArr, 0, (int) Math.min(length, foVar.e));
    }

    @Override // p.ko
    public final byte readByte() throws EOFException {
        q0(1L);
        return this.e.readByte();
    }

    @Override // p.ko
    public final void readFully(byte[] bArr) throws EOFException {
        fo foVar = this.e;
        try {
            q0(bArr.length);
            foVar.readFully(bArr);
        } catch (EOFException e) {
            int i = 0;
            while (true) {
                long j = foVar.e;
                if (j <= 0) {
                    throw e;
                }
                int iK0 = foVar.K0(bArr, i, (int) j);
                if (iK0 == -1) {
                    throw new AssertionError();
                }
                i += iK0;
            }
        }
    }

    @Override // p.ko
    public final int readInt() throws EOFException {
        q0(4L);
        return this.e.readInt();
    }

    @Override // p.ko
    public final long readLong() throws EOFException {
        q0(8L);
        return this.e.readLong();
    }

    @Override // p.ko
    public final short readShort() throws EOFException {
        q0(2L);
        return this.e.readShort();
    }

    @Override // p.de2
    public final long t(fo foVar, long j) {
        v03.j(foVar, "sink");
        if (j < 0) {
            throw new IllegalArgumentException(u4.g("byteCount < 0: ", j).toString());
        }
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        fo foVar2 = this.e;
        if (foVar2.e == 0 && this.c.t(foVar2, 8192L) == -1) {
            return -1L;
        }
        return foVar2.t(foVar, Math.min(j, foVar2.e));
    }

    public final String toString() {
        return "buffer(" + this.c + ')';
    }

    @Override // p.ko
    public final ByteString v(long j) throws EOFException {
        q0(j);
        return this.e.v(j);
    }

    @Override // p.ko
    public final boolean x0(long j, ByteString byteString) {
        v03.j(byteString, "bytes");
        int iE = byteString.e();
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        if (iE >= 0 && byteString.e() >= iE) {
            for (int i = 0; i < iE; i++) {
                long j2 = i;
                if (a(1 + j2) && this.e.G0(j2) == byteString.k(i)) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // p.ko
    public final void y(long j) throws EOFException {
        if (!(!this.k)) {
            throw new IllegalStateException("closed".toString());
        }
        while (j > 0) {
            fo foVar = this.e;
            if (foVar.e == 0 && this.c.t(foVar, 8192L) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, foVar.e);
            foVar.y(jMin);
            j -= jMin;
        }
    }

    @Override // p.ko
    public final long y0() throws EOFException {
        fo foVar;
        byte bG0;
        q0(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            boolean zA = a(i2);
            foVar = this.e;
            if (!zA) {
                break;
            }
            bG0 = foVar.G0(i);
            if ((bG0 < 48 || bG0 > 57) && ((bG0 < 97 || bG0 > 102) && (bG0 < 65 || bG0 > 70))) {
                break;
            }
            i = i2;
        }
        if (i == 0) {
            bk0.k(16);
            String string = Integer.toString(bG0, 16);
            v03.i(string, "toString(...)");
            throw new NumberFormatException("Expected leading [0-9a-fA-F] character but was 0x".concat(string));
        }
        return foVar.y0();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        v03.j(byteBuffer, "sink");
        fo foVar = this.e;
        if (foVar.e == 0 && this.c.t(foVar, 8192L) == -1) {
            return -1;
        }
        return foVar.read(byteBuffer);
    }
}
