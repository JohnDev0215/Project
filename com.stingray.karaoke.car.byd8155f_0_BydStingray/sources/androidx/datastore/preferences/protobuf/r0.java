package androidx.datastore.preferences.protobuf;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
public abstract class r0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Unsafe f11883a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Class f11884b;
    public static final q0 c;
    public static final boolean d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final boolean f11885e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final long f11886f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final boolean f11887g;

    static {
        Unsafe unsafeM = m();
        f11883a = unsafeM;
        f11884b = AbstractC0940c.f11830a;
        boolean zF = f(Long.TYPE);
        boolean zF2 = f(Integer.TYPE);
        q0 p0Var = null;
        if (unsafeM != null) {
            if (!AbstractC0940c.a()) {
                p0Var = new p0(unsafeM);
            } else if (zF) {
                p0Var = new o0(unsafeM, 1);
            } else if (zF2) {
                p0Var = new o0(unsafeM, 0);
            }
        }
        c = p0Var;
        d = p0Var == null ? false : p0Var.s();
        f11885e = p0Var == null ? false : p0Var.r();
        f11886f = c(byte[].class);
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
        if (fieldE != null && p0Var != null) {
            p0Var.j(fieldE);
        }
        f11887g = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }

    public static void a(Throwable th) {
        Logger.getLogger(r0.class.getName()).log(Level.WARNING, "platform method missing - proto runtime falling back to safer methods: " + th);
    }

    public static Object b(Class cls) {
        try {
            return f11883a.allocateInstance(cls);
        } catch (InstantiationException e10) {
            throw new IllegalStateException(e10);
        }
    }

    public static int c(Class cls) {
        if (f11885e) {
            return c.a(cls);
        }
        return -1;
    }

    public static void d(Class cls) {
        if (f11885e) {
            c.b(cls);
        }
    }

    public static Field e() {
        Field declaredField;
        Field declaredField2;
        if (AbstractC0940c.a()) {
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

    public static boolean f(Class cls) {
        if (!AbstractC0940c.a()) {
            return false;
        }
        try {
            Class cls2 = f11884b;
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

    public static byte g(long j3, byte[] bArr) {
        return c.d(f11886f + j3, bArr);
    }

    public static byte h(long j3, Object obj) {
        return (byte) ((c.g((-4) & j3, obj) >>> ((int) (((~j3) & 3) << 3))) & 255);
    }

    public static byte i(long j3, Object obj) {
        return (byte) ((c.g((-4) & j3, obj) >>> ((int) ((j3 & 3) << 3))) & 255);
    }

    public static int j(AbstractC0957u abstractC0957u, long j3) {
        return c.g(j3, abstractC0957u);
    }

    public static long k(AbstractC0957u abstractC0957u, long j3) {
        return c.h(j3, abstractC0957u);
    }

    public static Object l(AbstractC0957u abstractC0957u, long j3) {
        return c.i(j3, abstractC0957u);
    }

    public static Unsafe m() {
        try {
            return (Unsafe) AccessController.doPrivileged(new n0());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void n(byte[] bArr, long j3, byte b10) {
        c.l(bArr, f11886f + j3, b10);
    }

    public static void o(Object obj, long j3, byte b10) {
        long j10 = (-4) & j3;
        int iG = c.g(j10, obj);
        int i7 = ((~((int) j3)) & 3) << 3;
        q(((255 & b10) << i7) | (iG & (~(255 << i7))), j10, obj);
    }

    public static void p(Object obj, long j3, byte b10) {
        long j10 = (-4) & j3;
        int i7 = (((int) j3) & 3) << 3;
        q(((255 & b10) << i7) | (c.g(j10, obj) & (~(255 << i7))), j10, obj);
    }

    public static void q(int i7, long j3, Object obj) {
        c.o(i7, j3, obj);
    }

    public static void r(Object obj, long j3, long j10) {
        c.p(obj, j3, j10);
    }

    public static void s(long j3, Object obj, Object obj2) {
        c.q(j3, obj, obj2);
    }
}
