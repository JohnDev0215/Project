package androidx.datastore.preferences.protobuf;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0948k extends d0 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Logger f11864h = Logger.getLogger(C0948k.class.getName());

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final boolean f11865i = r0.f11885e;
    public G c;
    public final byte[] d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f11866e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f11867f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final OutputStream f11868g;

    public C0948k(OutputStream outputStream, int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException("bufferSize must be >= 0");
        }
        int iMax = Math.max(i7, 20);
        this.d = new byte[iMax];
        this.f11866e = iMax;
        if (outputStream == null) {
            throw new NullPointerException("out");
        }
        this.f11868g = outputStream;
    }

    public static int A(long j3, int i7) {
        return H((j3 >> 63) ^ (j3 << 1)) + D(i7);
    }

    public static int B(int i7, String str) {
        return C(str) + D(i7);
    }

    public static int C(String str) {
        int length;
        try {
            length = u0.b(str);
        } catch (t0 unused) {
            length = str.getBytes(AbstractC0959w.f11890a).length;
        }
        return F(length) + length;
    }

    public static int D(int i7) {
        return F(i7 << 3);
    }

    public static int E(int i7, int i10) {
        return F(i10) + D(i7);
    }

    public static int F(int i7) {
        if ((i7 & (-128)) == 0) {
            return 1;
        }
        if ((i7 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i7) == 0) {
            return 3;
        }
        return (i7 & (-268435456)) == 0 ? 4 : 5;
    }

    public static int G(long j3, int i7) {
        return H(j3) + D(i7);
    }

    public static int H(long j3) {
        int i7;
        if (((-128) & j3) == 0) {
            return 1;
        }
        if (j3 < 0) {
            return 10;
        }
        if (((-34359738368L) & j3) != 0) {
            j3 >>>= 28;
            i7 = 6;
        } else {
            i7 = 2;
        }
        if (((-2097152) & j3) != 0) {
            i7 += 2;
            j3 >>>= 14;
        }
        return (j3 & (-16384)) != 0 ? i7 + 1 : i7;
    }

    public static int l(int i7) {
        return D(i7) + 1;
    }

    public static int m(int i7, C0944g c0944g) {
        return n(c0944g) + D(i7);
    }

    public static int n(C0944g c0944g) {
        int size = c0944g.size();
        return F(size) + size;
    }

    public static int o(int i7) {
        return D(i7) + 8;
    }

    public static int p(int i7, int i10) {
        return v(i10) + D(i7);
    }

    public static int q(int i7) {
        return D(i7) + 4;
    }

    public static int r(int i7) {
        return D(i7) + 8;
    }

    public static int s(int i7) {
        return D(i7) + 4;
    }

    public static int t(int i7, AbstractC0937a abstractC0937a, Y y10) {
        return abstractC0937a.a(y10) + (D(i7) * 2);
    }

    public static int u(int i7, int i10) {
        return v(i10) + D(i7);
    }

    public static int v(int i7) {
        if (i7 >= 0) {
            return F(i7);
        }
        return 10;
    }

    public static int w(long j3, int i7) {
        return H(j3) + D(i7);
    }

    public static int x(int i7) {
        return D(i7) + 4;
    }

    public static int y(int i7) {
        return D(i7) + 8;
    }

    public static int z(int i7, int i10) {
        return F((i10 >> 31) ^ (i10 << 1)) + D(i7);
    }

    public final void I() throws IOException {
        this.f11868g.write(this.d, 0, this.f11867f);
        this.f11867f = 0;
    }

    public final void J(int i7) throws IOException {
        if (this.f11866e - this.f11867f < i7) {
            I();
        }
    }

    public final void K(byte b10) throws IOException {
        if (this.f11867f == this.f11866e) {
            I();
        }
        int i7 = this.f11867f;
        this.f11867f = i7 + 1;
        this.d[i7] = b10;
    }

    public final void L(byte[] bArr, int i7, int i10) throws IOException {
        int i11 = this.f11867f;
        int i12 = this.f11866e;
        int i13 = i12 - i11;
        byte[] bArr2 = this.d;
        if (i13 >= i10) {
            System.arraycopy(bArr, i7, bArr2, i11, i10);
            this.f11867f += i10;
            return;
        }
        System.arraycopy(bArr, i7, bArr2, i11, i13);
        int i14 = i7 + i13;
        int i15 = i10 - i13;
        this.f11867f = i12;
        I();
        if (i15 > i12) {
            this.f11868g.write(bArr, i14, i15);
        } else {
            System.arraycopy(bArr, i14, bArr2, 0, i15);
            this.f11867f = i15;
        }
    }

    public final void M(int i7, boolean z6) throws IOException {
        J(11);
        i(i7, 0);
        byte b10 = z6 ? (byte) 1 : (byte) 0;
        int i10 = this.f11867f;
        this.f11867f = i10 + 1;
        this.d[i10] = b10;
    }

    public final void N(int i7, C0944g c0944g) throws IOException {
        X(i7, 2);
        O(c0944g);
    }

    public final void O(C0944g c0944g) throws IOException {
        Z(c0944g.size());
        f(c0944g.q(), c0944g.size(), c0944g.f11845m);
    }

    public final void P(int i7, int i10) throws IOException {
        J(14);
        i(i7, 5);
        g(i10);
    }

    public final void Q(int i7) throws IOException {
        J(4);
        g(i7);
    }

    public final void R(long j3, int i7) throws IOException {
        J(18);
        i(i7, 1);
        h(j3);
    }

    public final void S(long j3) throws IOException {
        J(8);
        h(j3);
    }

    public final void T(int i7, int i10) throws IOException {
        J(20);
        i(i7, 0);
        if (i10 >= 0) {
            j(i10);
        } else {
            k(i10);
        }
    }

    public final void U(int i7) throws IOException {
        if (i7 >= 0) {
            Z(i7);
        } else {
            b0(i7);
        }
    }

    public final void V(int i7, String str) throws IOException {
        X(i7, 2);
        W(str);
    }

    public final void W(String str) throws IOException {
        try {
            int length = str.length() * 3;
            int iF = F(length);
            int i7 = iF + length;
            int i10 = this.f11866e;
            if (i7 > i10) {
                byte[] bArr = new byte[length];
                int iB = u0.f11889a.b(str, bArr, 0, length);
                Z(iB);
                L(bArr, 0, iB);
                return;
            }
            if (i7 > i10 - this.f11867f) {
                I();
            }
            int iF2 = F(str.length());
            int i11 = this.f11867f;
            byte[] bArr2 = this.d;
            try {
                try {
                    if (iF2 == iF) {
                        int i12 = i11 + iF2;
                        this.f11867f = i12;
                        int iB2 = u0.f11889a.b(str, bArr2, i12, i10 - i12);
                        this.f11867f = i11;
                        j((iB2 - i11) - iF2);
                        this.f11867f = iB2;
                    } else {
                        int iB3 = u0.b(str);
                        j(iB3);
                        this.f11867f = u0.f11889a.b(str, bArr2, this.f11867f, iB3);
                    }
                } catch (t0 e10) {
                    this.f11867f = i11;
                    throw e10;
                }
            } catch (ArrayIndexOutOfBoundsException e11) {
                throw new E5.s(e11);
            }
        } catch (t0 e12) {
            f11864h.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) e12);
            byte[] bytes = str.getBytes(AbstractC0959w.f11890a);
            try {
                Z(bytes.length);
                f(0, bytes.length, bytes);
            } catch (IndexOutOfBoundsException e13) {
                throw new E5.s(e13);
            }
        }
    }

    public final void X(int i7, int i10) throws IOException {
        Z((i7 << 3) | i10);
    }

    public final void Y(int i7, int i10) throws IOException {
        J(20);
        i(i7, 0);
        j(i10);
    }

    public final void Z(int i7) throws IOException {
        J(5);
        j(i7);
    }

    public final void a0(long j3, int i7) throws IOException {
        J(20);
        i(i7, 0);
        k(j3);
    }

    public final void b0(long j3) throws IOException {
        J(10);
        k(j3);
    }

    @Override // androidx.datastore.preferences.protobuf.d0
    public final void f(int i7, int i10, byte[] bArr) throws IOException {
        L(bArr, i7, i10);
    }

    public final void g(int i7) {
        int i10 = this.f11867f;
        int i11 = i10 + 1;
        this.f11867f = i11;
        byte[] bArr = this.d;
        bArr[i10] = (byte) (i7 & 255);
        int i12 = i10 + 2;
        this.f11867f = i12;
        bArr[i11] = (byte) ((i7 >> 8) & 255);
        int i13 = i10 + 3;
        this.f11867f = i13;
        bArr[i12] = (byte) ((i7 >> 16) & 255);
        this.f11867f = i10 + 4;
        bArr[i13] = (byte) ((i7 >> 24) & 255);
    }

    public final void h(long j3) {
        int i7 = this.f11867f;
        int i10 = i7 + 1;
        this.f11867f = i10;
        byte[] bArr = this.d;
        bArr[i7] = (byte) (j3 & 255);
        int i11 = i7 + 2;
        this.f11867f = i11;
        bArr[i10] = (byte) ((j3 >> 8) & 255);
        int i12 = i7 + 3;
        this.f11867f = i12;
        bArr[i11] = (byte) ((j3 >> 16) & 255);
        int i13 = i7 + 4;
        this.f11867f = i13;
        bArr[i12] = (byte) (255 & (j3 >> 24));
        int i14 = i7 + 5;
        this.f11867f = i14;
        bArr[i13] = (byte) (((int) (j3 >> 32)) & 255);
        int i15 = i7 + 6;
        this.f11867f = i15;
        bArr[i14] = (byte) (((int) (j3 >> 40)) & 255);
        int i16 = i7 + 7;
        this.f11867f = i16;
        bArr[i15] = (byte) (((int) (j3 >> 48)) & 255);
        this.f11867f = i7 + 8;
        bArr[i16] = (byte) (((int) (j3 >> 56)) & 255);
    }

    public final void i(int i7, int i10) {
        j((i7 << 3) | i10);
    }

    public final void j(int i7) {
        boolean z6 = f11865i;
        byte[] bArr = this.d;
        if (z6) {
            while ((i7 & (-128)) != 0) {
                int i10 = this.f11867f;
                this.f11867f = i10 + 1;
                r0.n(bArr, i10, (byte) ((i7 & 127) | 128));
                i7 >>>= 7;
            }
            int i11 = this.f11867f;
            this.f11867f = i11 + 1;
            r0.n(bArr, i11, (byte) i7);
            return;
        }
        while ((i7 & (-128)) != 0) {
            int i12 = this.f11867f;
            this.f11867f = i12 + 1;
            bArr[i12] = (byte) ((i7 & 127) | 128);
            i7 >>>= 7;
        }
        int i13 = this.f11867f;
        this.f11867f = i13 + 1;
        bArr[i13] = (byte) i7;
    }

    public final void k(long j3) {
        boolean z6 = f11865i;
        byte[] bArr = this.d;
        if (z6) {
            while ((j3 & (-128)) != 0) {
                int i7 = this.f11867f;
                this.f11867f = i7 + 1;
                r0.n(bArr, i7, (byte) ((((int) j3) & 127) | 128));
                j3 >>>= 7;
            }
            int i10 = this.f11867f;
            this.f11867f = i10 + 1;
            r0.n(bArr, i10, (byte) j3);
            return;
        }
        while ((j3 & (-128)) != 0) {
            int i11 = this.f11867f;
            this.f11867f = i11 + 1;
            bArr[i11] = (byte) ((((int) j3) & 127) | 128);
            j3 >>>= 7;
        }
        int i12 = this.f11867f;
        this.f11867f = i12 + 1;
        bArr[i12] = (byte) j3;
    }
}
