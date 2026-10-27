package r9;

import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public final class D implements InterfaceC2321k {
    public final J l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C2319i f21897m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f21898n;

    public D(J j3) {
        A7.m.f(j3, BYDAutoMultimediaDevice.PARAM_SOURCE);
        this.l = j3;
        this.f21897m = new C2319i();
    }

    public final short C() {
        I(2L);
        return this.f21897m.O();
    }

    public final String F(long j3) {
        I(j3);
        C2319i c2319i = this.f21897m;
        c2319i.getClass();
        return c2319i.P(j3, Q8.a.f7211a);
    }

    public final String G(long j3) {
        if (j3 < 0) {
            throw new IllegalArgumentException(M5.b.f("limit < 0: ", j3).toString());
        }
        long j10 = j3 == Long.MAX_VALUE ? Long.MAX_VALUE : j3 + 1;
        long jG = g((byte) 10, 0L, j10);
        C2319i c2319i = this.f21897m;
        if (jG != -1) {
            return s9.a.a(c2319i, jG);
        }
        if (j10 < Long.MAX_VALUE && l(j10) && c2319i.k(j10 - 1) == 13 && l(1 + j10) && c2319i.k(j10) == 10) {
            return s9.a.a(c2319i, j10);
        }
        C2319i c2319i2 = new C2319i();
        c2319i.i(c2319i2, 0L, Math.min(32, c2319i.f21932m));
        throw new EOFException("\\n not found: limit=" + Math.min(c2319i.f21932m, j3) + " content=" + c2319i2.F(c2319i2.f21932m).e() + (char) 8230);
    }

    @Override // r9.InterfaceC2321k
    public final String H(Charset charset) {
        C2319i c2319i = this.f21897m;
        c2319i.r(this.l);
        return c2319i.P(c2319i.f21932m, charset);
    }

    public final void I(long j3) {
        if (!l(j3)) {
            throw new EOFException();
        }
    }

    @Override // r9.InterfaceC2321k
    public final InputStream J() {
        return new C2318h(this, 1);
    }

    public final void K(long j3) {
        if (this.f21898n) {
            throw new IllegalStateException("closed");
        }
        while (j3 > 0) {
            C2319i c2319i = this.f21897m;
            if (c2319i.f21932m == 0 && this.l.p(c2319i, 8192L) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j3, c2319i.f21932m);
            c2319i.R(jMin);
            j3 -= jMin;
        }
    }

    public final boolean b() {
        if (this.f21898n) {
            throw new IllegalStateException("closed");
        }
        C2319i c2319i = this.f21897m;
        return c2319i.j() && this.l.p(c2319i, 8192L) == -1;
    }

    @Override // r9.InterfaceC2321k
    public final C2319i c() {
        return this.f21897m;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, java.nio.channels.Channel
    public final void close() throws IOException {
        if (this.f21898n) {
            return;
        }
        this.f21898n = true;
        this.l.close();
        this.f21897m.b();
    }

    @Override // r9.J
    public final L e() {
        return this.l.e();
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x012b, code lost:
    
        throw new java.lang.IllegalArgumentException(("size=" + r6.f21932m + " fromIndex=" + r4 + " toIndex=" + r2).toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final long g(byte r21, long r22, long r24) {
        /*
            Method dump skipped, instruction units count: 329
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: r9.D.g(byte, long, long):long");
    }

    public final long i(C2322l c2322l) {
        A7.m.f(c2322l, "targetBytes");
        if (this.f21898n) {
            throw new IllegalStateException("closed");
        }
        long jMax = 0;
        while (true) {
            C2319i c2319i = this.f21897m;
            long jM = c2319i.m(jMax, c2322l);
            if (jM != -1) {
                return jM;
            }
            long j3 = c2319i.f21932m;
            if (this.l.p(c2319i, 8192L) == -1) {
                return -1L;
            }
            jMax = Math.max(jMax, j3);
        }
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return !this.f21898n;
    }

    public final D j() {
        return AbstractC2312b.c(new A(this));
    }

    public final byte k() {
        I(1L);
        return this.f21897m.z();
    }

    @Override // r9.InterfaceC2321k
    public final boolean l(long j3) {
        C2319i c2319i;
        if (j3 < 0) {
            throw new IllegalArgumentException(M5.b.f("byteCount < 0: ", j3).toString());
        }
        if (this.f21898n) {
            throw new IllegalStateException("closed");
        }
        do {
            c2319i = this.f21897m;
            if (c2319i.f21932m >= j3) {
                return true;
            }
        } while (this.l.p(c2319i, 8192L) != -1);
        return false;
    }

    public final C2322l m(long j3) {
        I(j3);
        return this.f21897m.F(j3);
    }

    public final void o(C2319i c2319i, long j3) throws EOFException {
        C2319i c2319i2 = this.f21897m;
        try {
            I(j3);
            long j10 = c2319i2.f21932m;
            if (j10 >= j3) {
                c2319i.t(c2319i2, j3);
            } else {
                c2319i.t(c2319i2, j10);
                throw new EOFException();
            }
        } catch (EOFException e10) {
            c2319i.r(c2319i2);
            throw e10;
        }
    }

    @Override // r9.J
    public final long p(C2319i c2319i, long j3) {
        A7.m.f(c2319i, "sink");
        if (j3 < 0) {
            throw new IllegalArgumentException(M5.b.f("byteCount < 0: ", j3).toString());
        }
        if (this.f21898n) {
            throw new IllegalStateException("closed");
        }
        C2319i c2319i2 = this.f21897m;
        if (c2319i2.f21932m == 0 && this.l.p(c2319i2, 8192L) == -1) {
            return -1L;
        }
        return c2319i2.p(c2319i, Math.min(j3, c2319i2.f21932m));
    }

    public final int q() {
        I(4L);
        return this.f21897m.L();
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        A7.m.f(byteBuffer, "sink");
        C2319i c2319i = this.f21897m;
        if (c2319i.f21932m == 0 && this.l.p(c2319i, 8192L) == -1) {
            return -1;
        }
        return c2319i.read(byteBuffer);
    }

    public final String toString() {
        return "buffer(" + this.l + ')';
    }

    public final int v() {
        I(4L);
        int iL = this.f21897m.L();
        return ((iL & 255) << 24) | (((-16777216) & iL) >>> 24) | ((16711680 & iL) >>> 8) | ((65280 & iL) << 8);
    }

    @Override // r9.InterfaceC2321k
    public final long w(C c) {
        C2319i c2319i;
        long j3 = 0;
        while (true) {
            c2319i = this.f21897m;
            if (this.l.p(c2319i, 8192L) == -1) {
                break;
            }
            long jG = c2319i.g();
            if (jG > 0) {
                j3 += jG;
                c.t(c2319i, jG);
            }
        }
        long j10 = c2319i.f21932m;
        if (j10 <= 0) {
            return j3;
        }
        long j11 = j3 + j10;
        c.t(c2319i, j10);
        return j11;
    }

    @Override // r9.InterfaceC2321k
    public final int x(y yVar) {
        A7.m.f(yVar, "options");
        if (this.f21898n) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            C2319i c2319i = this.f21897m;
            int iB = s9.a.b(c2319i, yVar, true);
            if (iB != -2) {
                if (iB != -1) {
                    c2319i.R(yVar.l[iB].d());
                    return iB;
                }
            } else if (this.l.p(c2319i, 8192L) == -1) {
                break;
            }
        }
        return -1;
    }

    public final long y() throws EOFException {
        I(8L);
        long jM = this.f21897m.M();
        return ((jM & 255) << 56) | (((-72057594037927936L) & jM) >>> 56) | ((71776119061217280L & jM) >>> 40) | ((280375465082880L & jM) >>> 24) | ((1095216660480L & jM) >>> 8) | ((4278190080L & jM) << 8) | ((16711680 & jM) << 24) | ((65280 & jM) << 40);
    }

    public final short z() {
        I(2L);
        return this.f21897m.N();
    }
}
