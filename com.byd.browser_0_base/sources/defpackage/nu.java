package defpackage;

import com.google.protobuf.b1;
import com.google.protobuf.c0;
import com.google.protobuf.f0;
import com.google.protobuf.i;
import com.google.protobuf.r0;
import defpackage.sk3;
import java.io.IOException;

/* JADX INFO: compiled from: CodedOutputStream.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class nu extends vd1 {
    public static final boolean l = dj3.e;
    public i k;

    /* JADX INFO: compiled from: CodedOutputStream.java */
    public static class a extends nu {
        public final byte[] m;
        public final int n;
        public int o;

        public a(byte[] bArr, int i) {
            int i2 = 0 + i;
            if ((0 | i | (bArr.length - i2)) < 0) {
                throw new IllegalArgumentException(String.format("Array range is invalid. Buffer.length=%d, offset=%d, length=%d", Integer.valueOf(bArr.length), 0, Integer.valueOf(i)));
            }
            this.m = bArr;
            this.o = 0;
            this.n = i2;
        }

        @Override // defpackage.nu
        public final void C0(int i) throws b {
            if (i >= 0) {
                O0(i);
            } else {
                Q0(i);
            }
        }

        @Override // defpackage.nu
        public final void D0(int i, r0 r0Var) throws b {
            M0(i, 2);
            G0(r0Var);
        }

        @Override // defpackage.nu
        public final void E0(int i, r0 r0Var, b1 b1Var) throws b {
            M0(i, 2);
            com.google.protobuf.b bVar = (com.google.protobuf.b) r0Var;
            int iX = bVar.x();
            if (iX == -1) {
                iX = b1Var.h(bVar);
                bVar.A(iX);
            }
            O0(iX);
            b1Var.b(r0Var, this.k);
        }

        @Override // defpackage.nu
        public final void G0(r0 r0Var) throws b {
            O0(r0Var.m());
            r0Var.p(this);
        }

        @Override // defpackage.vd1
        public final void I(byte[] bArr, int i, int i2) throws b {
            S0(bArr, i, i2);
        }

        @Override // defpackage.nu
        public final void I0(int i, r0 r0Var) throws b {
            M0(1, 3);
            N0(2, i);
            D0(3, r0Var);
            M0(1, 4);
        }

        @Override // defpackage.nu
        public final void J0(int i, lk lkVar) throws b {
            M0(1, 3);
            N0(2, i);
            r0(3, lkVar);
            M0(1, 4);
        }

        @Override // defpackage.nu
        public final void K0(int i, String str) throws b {
            M0(i, 2);
            L0(str);
        }

        @Override // defpackage.nu
        public final void L0(String str) throws b {
            int i = this.o;
            try {
                int iK0 = nu.k0(str.length() * 3);
                int iK02 = nu.k0(str.length());
                int i2 = this.n;
                byte[] bArr = this.m;
                if (iK02 == iK0) {
                    int i3 = i + iK02;
                    this.o = i3;
                    int iC = sk3.c(str, bArr, i3, i2 - i3);
                    this.o = i;
                    O0((iC - i) - iK02);
                    this.o = iC;
                } else {
                    O0(sk3.d(str));
                    int i4 = this.o;
                    this.o = sk3.c(str, bArr, i4, i2 - i4);
                }
            } catch (IndexOutOfBoundsException e) {
                throw new b(e);
            } catch (sk3.d unused) {
                this.o = i;
                byte[] bytes = str.getBytes(c0.a);
                try {
                    O0(bytes.length);
                    S0(bytes, 0, bytes.length);
                } catch (IndexOutOfBoundsException e2) {
                    throw new b(e2);
                } catch (b e3) {
                    throw e3;
                }
            }
        }

        @Override // defpackage.nu
        public final void M0(int i, int i2) throws b {
            O0((i << 3) | i2);
        }

        @Override // defpackage.nu
        public final void N0(int i, int i2) throws b {
            M0(i, 0);
            O0(i2);
        }

        @Override // defpackage.nu
        public final void O0(int i) throws b {
            if (nu.l) {
                Class<?> cls = q4.a;
            }
            while (true) {
                int i2 = i & (-128);
                byte[] bArr = this.m;
                if (i2 == 0) {
                    int i3 = this.o;
                    this.o = i3 + 1;
                    bArr[i3] = (byte) i;
                    return;
                } else {
                    try {
                        int i4 = this.o;
                        this.o = i4 + 1;
                        bArr[i4] = (byte) ((i & 127) | 128);
                        i >>>= 7;
                    } catch (IndexOutOfBoundsException e) {
                        throw new b(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.o), Integer.valueOf(this.n), 1), e);
                    }
                }
                throw new b(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.o), Integer.valueOf(this.n), 1), e);
            }
        }

        @Override // defpackage.nu
        public final void P0(int i, long j) throws b {
            M0(i, 0);
            Q0(j);
        }

        @Override // defpackage.nu
        public final void Q0(long j) throws b {
            boolean z = nu.l;
            int i = this.n;
            byte[] bArr = this.m;
            if (z && i - this.o >= 10) {
                while ((j & (-128)) != 0) {
                    int i2 = this.o;
                    this.o = i2 + 1;
                    dj3.s(bArr, i2, (byte) ((((int) j) & 127) | 128));
                    j >>>= 7;
                }
                int i3 = this.o;
                this.o = i3 + 1;
                dj3.s(bArr, i3, (byte) j);
                return;
            }
            while ((j & (-128)) != 0) {
                try {
                    int i4 = this.o;
                    this.o = i4 + 1;
                    bArr[i4] = (byte) ((((int) j) & 127) | 128);
                    j >>>= 7;
                } catch (IndexOutOfBoundsException e) {
                    throw new b(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.o), Integer.valueOf(i), 1), e);
                }
            }
            int i5 = this.o;
            this.o = i5 + 1;
            bArr[i5] = (byte) j;
        }

        public final int R0() {
            return this.n - this.o;
        }

        public final void S0(byte[] bArr, int i, int i2) throws b {
            try {
                System.arraycopy(bArr, i, this.m, this.o, i2);
                this.o += i2;
            } catch (IndexOutOfBoundsException e) {
                throw new b(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.o), Integer.valueOf(this.n), Integer.valueOf(i2)), e);
            }
        }

        @Override // defpackage.nu
        public final void o0(byte b) throws b {
            try {
                byte[] bArr = this.m;
                int i = this.o;
                this.o = i + 1;
                bArr[i] = b;
            } catch (IndexOutOfBoundsException e) {
                throw new b(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.o), Integer.valueOf(this.n), 1), e);
            }
        }

        @Override // defpackage.nu
        public final void p0(int i, boolean z) throws b {
            M0(i, 0);
            o0(z ? (byte) 1 : (byte) 0);
        }

        @Override // defpackage.nu
        public final void q0(byte[] bArr, int i) throws b {
            O0(i);
            S0(bArr, 0, i);
        }

        @Override // defpackage.nu
        public final void r0(int i, lk lkVar) throws b {
            M0(i, 2);
            t0(lkVar);
        }

        @Override // defpackage.nu
        public final void t0(lk lkVar) throws b {
            O0(lkVar.size());
            lkVar.A(this);
        }

        @Override // defpackage.nu
        public final void u0(int i, int i2) throws b {
            M0(i, 5);
            v0(i2);
        }

        @Override // defpackage.nu
        public final void v0(int i) throws b {
            try {
                byte[] bArr = this.m;
                int i2 = this.o;
                int i3 = i2 + 1;
                bArr[i2] = (byte) (i & 255);
                int i4 = i3 + 1;
                bArr[i3] = (byte) ((i >> 8) & 255);
                int i5 = i4 + 1;
                bArr[i4] = (byte) ((i >> 16) & 255);
                this.o = i5 + 1;
                bArr[i5] = (byte) ((i >> 24) & 255);
            } catch (IndexOutOfBoundsException e) {
                throw new b(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.o), Integer.valueOf(this.n), 1), e);
            }
        }

        @Override // defpackage.nu
        public final void w0(int i, long j) throws b {
            M0(i, 1);
            x0(j);
        }

        @Override // defpackage.nu
        public final void x0(long j) throws b {
            try {
                byte[] bArr = this.m;
                int i = this.o;
                int i2 = i + 1;
                bArr[i] = (byte) (((int) j) & 255);
                int i3 = i2 + 1;
                bArr[i2] = (byte) (((int) (j >> 8)) & 255);
                int i4 = i3 + 1;
                bArr[i3] = (byte) (((int) (j >> 16)) & 255);
                int i5 = i4 + 1;
                bArr[i4] = (byte) (((int) (j >> 24)) & 255);
                int i6 = i5 + 1;
                bArr[i5] = (byte) (((int) (j >> 32)) & 255);
                int i7 = i6 + 1;
                bArr[i6] = (byte) (((int) (j >> 40)) & 255);
                int i8 = i7 + 1;
                bArr[i7] = (byte) (((int) (j >> 48)) & 255);
                this.o = i8 + 1;
                bArr[i8] = (byte) (((int) (j >> 56)) & 255);
            } catch (IndexOutOfBoundsException e) {
                throw new b(String.format("Pos: %d, limit: %d, len: %d", Integer.valueOf(this.o), Integer.valueOf(this.n), 1), e);
            }
        }

        @Override // defpackage.nu
        public final void z0(int i, int i2) throws b {
            M0(i, 0);
            C0(i2);
        }
    }

    public static int J(int i) {
        return i0(i) + 1;
    }

    public static int K(int i, lk lkVar) {
        int iI0 = i0(i);
        int size = lkVar.size();
        return k0(size) + size + iI0;
    }

    public static int L(lk lkVar) {
        int size = lkVar.size();
        return k0(size) + size;
    }

    public static int M(int i) {
        return i0(i) + 8;
    }

    public static int N(int i, int i2) {
        return T(i2) + i0(i);
    }

    public static int O(int i) {
        return i0(i) + 4;
    }

    public static int P(int i) {
        return i0(i) + 8;
    }

    public static int Q(int i) {
        return i0(i) + 4;
    }

    @Deprecated
    public static int R(int i, r0 r0Var, b1 b1Var) {
        int iI0 = i0(i) * 2;
        com.google.protobuf.b bVar = (com.google.protobuf.b) r0Var;
        int iX = bVar.x();
        if (iX == -1) {
            iX = b1Var.h(bVar);
            bVar.A(iX);
        }
        return iX + iI0;
    }

    public static int S(int i, int i2) {
        return T(i2) + i0(i);
    }

    public static int T(int i) {
        if (i >= 0) {
            return k0(i);
        }
        return 10;
    }

    public static int U(int i, long j) {
        return n0(j) + i0(i);
    }

    public static int V(int i, f0 f0Var) {
        return W(f0Var) + i0(i);
    }

    public static int W(f0 f0Var) {
        int size;
        if (f0Var.d != null) {
            size = f0Var.d.size();
        } else {
            lk lkVar = f0Var.a;
            size = lkVar != null ? lkVar.size() : f0Var.c != null ? f0Var.c.m() : 0;
        }
        return k0(size) + size;
    }

    public static int Z(int i, r0 r0Var) {
        return a0(3, r0Var) + j0(2, i) + (i0(1) * 2);
    }

    public static int a0(int i, r0 r0Var) {
        int iI0 = i0(i);
        int iM = r0Var.m();
        return k0(iM) + iM + iI0;
    }

    public static int b0(int i) {
        return i0(i) + 4;
    }

    public static int c0(int i) {
        return i0(i) + 8;
    }

    public static int d0(int i, int i2) {
        return k0((i2 >> 31) ^ (i2 << 1)) + i0(i);
    }

    public static int e0(int i, long j) {
        return n0((j >> 63) ^ (j << 1)) + i0(i);
    }

    public static int f0(int i, String str) {
        return g0(str) + i0(i);
    }

    public static int g0(String str) {
        int length;
        try {
            length = sk3.d(str);
        } catch (sk3.d unused) {
            length = str.getBytes(c0.a).length;
        }
        return k0(length) + length;
    }

    public static int i0(int i) {
        return k0((i << 3) | 0);
    }

    public static int j0(int i, int i2) {
        return k0(i2) + i0(i);
    }

    public static int k0(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int m0(int i, long j) {
        return n0(j) + i0(i);
    }

    public static int n0(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            j >>>= 28;
            i = 6;
        } else {
            i = 2;
        }
        if (((-2097152) & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public abstract void C0(int i);

    public abstract void D0(int i, r0 r0Var);

    public abstract void E0(int i, r0 r0Var, b1 b1Var);

    public abstract void G0(r0 r0Var);

    public abstract void I0(int i, r0 r0Var);

    public abstract void J0(int i, lk lkVar);

    public abstract void K0(int i, String str);

    public abstract void L0(String str);

    public abstract void M0(int i, int i2);

    public abstract void N0(int i, int i2);

    public abstract void O0(int i);

    public abstract void P0(int i, long j);

    public abstract void Q0(long j);

    public abstract void o0(byte b2);

    public abstract void p0(int i, boolean z);

    public abstract void q0(byte[] bArr, int i);

    public abstract void r0(int i, lk lkVar);

    public abstract void t0(lk lkVar);

    public abstract void u0(int i, int i2);

    public abstract void v0(int i);

    public abstract void w0(int i, long j);

    public abstract void x0(long j);

    @Deprecated
    public final void y0(int i, r0 r0Var) {
        M0(i, 3);
        r0Var.p(this);
        M0(i, 4);
    }

    public abstract void z0(int i, int i2);

    /* JADX INFO: compiled from: CodedOutputStream.java */
    public static class b extends IOException {
        private static final long serialVersionUID = -6947486886997889499L;

        public b(String str, IndexOutOfBoundsException indexOutOfBoundsException) {
            super(C0175b.j("CodedOutputStream was writing to a flat byte array and ran out of space.: ", str), indexOutOfBoundsException);
        }

        public b(IndexOutOfBoundsException indexOutOfBoundsException) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space.", indexOutOfBoundsException);
        }
    }
}
