package com.google.protobuf;

import java.util.logging.Level;
import java.util.logging.Logger;
import p.dr2;
import p.k72;
import p.p01;
import p.p02;
import p.w51;
import p.wj1;

/* JADX INFO: loaded from: classes.dex */
public abstract class h extends p02 {
    public static final Logger h = Logger.getLogger(h.class.getName());
    public static final boolean i = dr2.e;
    public p01 g;

    public static int A(int i2) {
        return L(i2) + 4;
    }

    public static int B(int i2, wj1 wj1Var, k72 k72Var) {
        return ((a) wj1Var).getSerializedSize(k72Var) + (L(i2) * 2);
    }

    public static int C(int i2, int i3) {
        return D(i3) + L(i2);
    }

    public static int D(int i2) {
        if (i2 >= 0) {
            return N(i2);
        }
        return 10;
    }

    public static int E(int i2, long j) {
        return P(j) + L(i2);
    }

    public static int F(int i2) {
        return L(i2) + 4;
    }

    public static int G(int i2) {
        return L(i2) + 8;
    }

    public static int H(int i2, int i3) {
        return N((i3 >> 31) ^ (i3 << 1)) + L(i2);
    }

    public static int I(int i2, long j) {
        return P((j >> 63) ^ (j << 1)) + L(i2);
    }

    public static int J(int i2, String str) {
        return K(str) + L(i2);
    }

    public static int K(String str) {
        int length;
        try {
            length = r.b(str);
        } catch (Utf8$UnpairedSurrogateException unused) {
            length = str.getBytes(w51.a).length;
        }
        return N(length) + length;
    }

    public static int L(int i2) {
        return N(i2 << 3);
    }

    public static int M(int i2, int i3) {
        return N(i3) + L(i2);
    }

    public static int N(int i2) {
        if ((i2 & (-128)) == 0) {
            return 1;
        }
        if ((i2 & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i2) == 0) {
            return 3;
        }
        return (i2 & (-268435456)) == 0 ? 4 : 5;
    }

    public static int O(int i2, long j) {
        return P(j) + L(i2);
    }

    public static int P(long j) {
        int i2;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            j >>>= 28;
            i2 = 6;
        } else {
            i2 = 2;
        }
        if (((-2097152) & j) != 0) {
            i2 += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? i2 + 1 : i2;
    }

    public static int u(int i2) {
        return L(i2) + 1;
    }

    public static int v(int i2, ByteString byteString) {
        int iL = L(i2);
        int size = byteString.size();
        return N(size) + size + iL;
    }

    public static int w(int i2) {
        return L(i2) + 8;
    }

    public static int x(int i2, int i3) {
        return D(i3) + L(i2);
    }

    public static int y(int i2) {
        return L(i2) + 4;
    }

    public static int z(int i2) {
        return L(i2) + 8;
    }

    public final void Q(String str, Utf8$UnpairedSurrogateException utf8$UnpairedSurrogateException) throws CodedOutputStream$OutOfSpaceException {
        h.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) utf8$UnpairedSurrogateException);
        byte[] bytes = str.getBytes(w51.a);
        try {
            i0(bytes.length);
            t(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new CodedOutputStream$OutOfSpaceException(e);
        }
    }

    public abstract void R(byte b);

    public abstract void S(int i2, boolean z);

    public abstract void T(byte[] bArr, int i2);

    public abstract void U(int i2, ByteString byteString);

    public abstract void V(ByteString byteString);

    public abstract void W(int i2, int i3);

    public abstract void X(int i2);

    public abstract void Y(int i2, long j);

    public abstract void Z(long j);

    public abstract void a0(int i2, int i3);

    public abstract void b0(int i2);

    public abstract void c0(int i2, wj1 wj1Var, k72 k72Var);

    public abstract void d0(wj1 wj1Var);

    public abstract void e0(int i2, String str);

    public abstract void f0(String str);

    public abstract void g0(int i2, int i3);

    public abstract void h0(int i2, int i3);

    public abstract void i0(int i2);

    public abstract void j0(int i2, long j);

    public abstract void k0(long j);
}
