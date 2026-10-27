package p;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
public abstract class dr2 {
    public static final Unsafe a;
    public static final Class b;
    public static final cr2 c;
    public static final boolean d;
    public static final boolean e;
    public static final long f;
    public static final long g;
    public static final boolean h;

    static {
        Unsafe unsafeM = m();
        a = unsafeM;
        b = r6.a;
        boolean zF = f(Long.TYPE);
        boolean zF2 = f(Integer.TYPE);
        boolean z = true;
        int i = 0;
        cr2 br2Var = null;
        if (unsafeM != null) {
            if (!r6.a()) {
                br2Var = new br2(unsafeM);
            } else if (zF) {
                br2Var = new ar2(unsafeM, z ? 1 : 0);
            } else if (zF2) {
                br2Var = new ar2(unsafeM, i);
            }
        }
        c = br2Var;
        d = br2Var == null ? false : br2Var.u();
        e = br2Var == null ? false : br2Var.t();
        f = c(byte[].class);
        c(boolean[].class);
        d(boolean[].class);
        c(int[].class);
        d(int[].class);
        c(long[].class);
        d(long[].class);
        c(float[].class);
        d(float[].class);
        c(double[].class);
        d(double[].class);
        c(Object[].class);
        d(Object[].class);
        Field fieldE = e();
        g = (fieldE == null || br2Var == null) ? -1L : br2Var.l(fieldE);
        h = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    public static void a(Throwable th) {
        Logger.getLogger(dr2.class.getName()).log(Level.WARNING, "platform method missing - proto runtime falling back to safer methods: " + th);
    }

    public static Object b(Class cls) {
        try {
            return a.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static int c(Class cls) {
        if (e) {
            return c.a(cls);
        }
        return -1;
    }

    public static void d(Class cls) {
        if (e) {
            c.b(cls);
        }
    }

    public static Field e() {
        Field declaredField;
        Field declaredField2;
        if (r6.a()) {
            try {
                declaredField2 = Buffer.class.getDeclaredField("effectiveDirectAddress");
            } catch (Throwable unused) {
                declaredField2 = null;
            }
            if (declaredField2 != null) {
                return declaredField2;
            }
        }
        try {
            declaredField = Buffer.class.getDeclaredField("address");
        } catch (Throwable unused2) {
            declaredField = null;
        }
        if (declaredField == null || declaredField.getType() != Long.TYPE) {
            return null;
        }
        return declaredField;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean f(Class cls) {
        if (!r6.a()) {
            return false;
        }
        try {
            Class cls2 = b;
            Class cls3 = Boolean.TYPE;
            cls2.getMethod("peekLong", cls, cls3);
            cls2.getMethod("pokeLong", cls, Long.TYPE, cls3);
            Class cls4 = Integer.TYPE;
            cls2.getMethod("pokeInt", cls, cls4, cls3);
            cls2.getMethod("peekInt", cls, cls3);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, cls4, cls4);
            cls2.getMethod("peekByteArray", cls, byte[].class, cls4, cls4);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static byte g(byte[] bArr, long j) {
        return c.f(f + j, bArr);
    }

    public static byte h(long j, Object obj) {
        return (byte) ((c.i((-4) & j, obj) >>> ((int) (((~j) & 3) << 3))) & 255);
    }

    public static byte i(long j, Object obj) {
        return (byte) ((c.i((-4) & j, obj) >>> ((int) ((j & 3) << 3))) & 255);
    }

    public static int j(long j, Object obj) {
        return c.i(j, obj);
    }

    public static long k(long j, Object obj) {
        return c.j(j, obj);
    }

    public static Object l(long j, Object obj) {
        return c.k(j, obj);
    }

    public static Unsafe m() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zq2());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void n(Object obj, long j, boolean z) {
        c.m(obj, j, z);
    }

    public static void o(byte[] bArr, long j, byte b2) {
        c.n(bArr, f + j, b2);
    }

    public static void p(Object obj, long j, byte b2) {
        long j2 = (-4) & j;
        int i = c.i(j2, obj);
        int i2 = ((~((int) j)) & 3) << 3;
        t(((255 & b2) << i2) | (i & (~(255 << i2))), j2, obj);
    }

    public static void q(Object obj, long j, byte b2) {
        long j2 = (-4) & j;
        int i = (((int) j) & 3) << 3;
        t(((255 & b2) << i) | (c.i(j2, obj) & (~(255 << i))), j2, obj);
    }

    public static void r(Object obj, long j, double d2) {
        c.o(obj, j, d2);
    }

    public static void s(Object obj, long j, float f2) {
        c.p(obj, j, f2);
    }

    public static void t(int i, long j, Object obj) {
        c.q(i, j, obj);
    }

    public static void u(Object obj, long j, long j2) {
        c.r(obj, j, j2);
    }

    public static void v(long j, Object obj, Object obj2) {
        c.s(j, obj, obj2);
    }
}
