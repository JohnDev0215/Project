package defpackage;

import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: renamed from: pl, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1342pl extends AbstractC0387Vk {
    public static final Logger b = Logger.getLogger(AbstractC1342pl.class.getName());
    public static final boolean c = AbstractC0066Dm.h;
    public C1397ql a;

    public AbstractC1342pl(AbstractC1122ll abstractC1122ll) {
    }

    public static int A(int i, long j) {
        return B(j) + x(i);
    }

    public static int B(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if (((-2097152) & j) != 0) {
            i += 2;
            j >>>= 14;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public static int C(int i) {
        return (i >> 31) ^ (i << 1);
    }

    public static long D(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int b(int i, boolean z) {
        return x(i) + 1;
    }

    public static int c(int i, AbstractC0731el abstractC0731el) {
        return x(i) + n(abstractC0731el.size());
    }

    public static int d(AbstractC0731el abstractC0731el) {
        return n(abstractC0731el.size());
    }

    public static int e(int i, double d) {
        return x(i) + 8;
    }

    public static int f(int i, int i2) {
        return x(i) + l(i2);
    }

    public static int g(int i, int i2) {
        return x(i) + 4;
    }

    public static int h(int i, long j) {
        return x(i) + 8;
    }

    public static int i(int i, float f) {
        return x(i) + 4;
    }

    @Deprecated
    public static int j(int i, InterfaceC0509am interfaceC0509am, InterfaceC1178mm interfaceC1178mm) {
        return (x(i) * 2) + ((AbstractC0315Rk) interfaceC0509am).a(interfaceC1178mm);
    }

    public static int k(int i, int i2) {
        return l(i2) + x(i);
    }

    public static int l(int i) {
        if (i >= 0) {
            return z(i);
        }
        return 10;
    }

    public static int m(int i, long j) {
        return x(i) + B(j);
    }

    public static int n(int i) {
        return z(i) + i;
    }

    public static int o(int i) {
        if (i > 4096) {
            return 4096;
        }
        return i;
    }

    public static int p(int i, int i2) {
        return x(i) + 4;
    }

    public static int q(int i, long j) {
        return x(i) + 8;
    }

    public static int r(int i, int i2) {
        return s(i2) + x(i);
    }

    public static int s(int i) {
        return z(C(i));
    }

    public static int t(int i, long j) {
        return u(j) + x(i);
    }

    public static int u(long j) {
        return B(D(j));
    }

    public static int v(int i, String str) {
        return w(str) + x(i);
    }

    public static int w(String str) {
        int length;
        try {
            length = AbstractC0174Jm.e(str);
        } catch (C0138Hm unused) {
            length = str.getBytes(AbstractC0119Gl.a).length;
        }
        return n(length);
    }

    public static int x(int i) {
        return z((i << 3) | 0);
    }

    public static int y(int i, int i2) {
        return z(i2) + x(i);
    }

    public static int z(int i) {
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

    public final void E(String str, C0138Hm c0138Hm) throws C1232nl {
        b.log(Level.WARNING, "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) c0138Hm);
        byte[] bytes = str.getBytes(AbstractC0119Gl.a);
        try {
            S(bytes.length);
            a(bytes, 0, bytes.length);
        } catch (IndexOutOfBoundsException e) {
            throw new C1232nl(e);
        } catch (C1232nl e2) {
            throw e2;
        }
    }

    public abstract void F(byte b2);

    public abstract void G(int i, boolean z);

    public abstract void H(int i, AbstractC0731el abstractC0731el);

    public abstract void I(int i, int i2);

    public abstract void J(int i);

    public abstract void K(int i, long j);

    public abstract void L(long j);

    public abstract void M(int i, int i2);

    public abstract void N(int i);

    public abstract void O(int i, InterfaceC0509am interfaceC0509am, InterfaceC1178mm interfaceC1178mm);

    public abstract void P(int i, String str);

    public abstract void Q(int i, int i2);

    public abstract void R(int i, int i2);

    public abstract void S(int i);

    public abstract void T(int i, long j);

    public abstract void U(long j);
}
