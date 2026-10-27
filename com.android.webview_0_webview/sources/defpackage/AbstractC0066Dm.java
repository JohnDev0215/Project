package defpackage;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: renamed from: Dm, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0066Dm {
    public static final Logger a = Logger.getLogger(AbstractC0066Dm.class.getName());
    public static final Unsafe b = o();
    public static final Class c = AbstractC0369Uk.a;
    public static final boolean d = e(Long.TYPE);
    public static final boolean e = e(Integer.TYPE);
    public static final AbstractC0048Cm f;
    public static final boolean g;
    public static final boolean h;
    public static final long i;
    public static final boolean j;

    /* JADX WARN: Removed duplicated region for block: B:42:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x02cb  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x015a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        /*
            Method dump skipped, instruction units count: 719
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC0066Dm.<clinit>():void");
    }

    public static Object a(Class cls) {
        try {
            return b.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static int b(Class cls) {
        if (h) {
            return f.a(cls);
        }
        return -1;
    }

    public static int c(Class cls) {
        if (h) {
            return f.b(cls);
        }
        return -1;
    }

    public static Field d() {
        Field declaredField;
        Field declaredField2;
        if (AbstractC0369Uk.a()) {
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
    public static boolean e(Class cls) {
        Class cls2 = Boolean.TYPE;
        if (!AbstractC0369Uk.a()) {
            return false;
        }
        try {
            Class cls3 = c;
            cls3.getMethod("peekLong", cls, cls2);
            cls3.getMethod("pokeLong", cls, Long.TYPE, cls2);
            cls3.getMethod("pokeInt", cls, Integer.TYPE, cls2);
            cls3.getMethod("peekInt", cls, cls2);
            cls3.getMethod("pokeByte", cls, Byte.TYPE);
            cls3.getMethod("peekByte", cls);
            cls3.getMethod("pokeByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            cls3.getMethod("peekByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean f(Object obj, long j2) {
        return f.c(obj, j2);
    }

    public static byte g(byte[] bArr, long j2) {
        return f.d(bArr, i + j2);
    }

    public static byte h(Object obj, long j2) {
        return (byte) ((l(obj, (-4) & j2) >>> ((int) (((~j2) & 3) << 3))) & 255);
    }

    public static byte i(Object obj, long j2) {
        return (byte) ((l(obj, (-4) & j2) >>> ((int) ((j2 & 3) << 3))) & 255);
    }

    public static double j(Object obj, long j2) {
        return f.e(obj, j2);
    }

    public static float k(Object obj, long j2) {
        return f.f(obj, j2);
    }

    public static int l(Object obj, long j2) {
        return f.g(obj, j2);
    }

    public static long m(Object obj, long j2) {
        return f.h(obj, j2);
    }

    public static Object n(Object obj, long j2) {
        return f.i(obj, j2);
    }

    public static Unsafe o() {
        try {
            return (Unsafe) AccessController.doPrivileged(new C1837ym());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void p(Object obj, long j2, boolean z) {
        f.k(obj, j2, z);
    }

    public static void q(byte[] bArr, long j2, byte b2) {
        f.l(bArr, i + j2, b2);
    }

    public static void r(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int iL = l(obj, j3);
        int i2 = ((~((int) j2)) & 3) << 3;
        f.o(obj, j3, ((255 & b2) << i2) | (iL & (~(255 << i2))));
    }

    public static void s(Object obj, long j2, byte b2) {
        long j3 = (-4) & j2;
        int i2 = (((int) j2) & 3) << 3;
        f.o(obj, j3, ((255 & b2) << i2) | (l(obj, j3) & (~(255 << i2))));
    }

    public static void t(Object obj, long j2, double d2) {
        f.m(obj, j2, d2);
    }

    public static void u(Object obj, long j2, float f2) {
        f.n(obj, j2, f2);
    }

    public static void v(Object obj, long j2, int i2) {
        f.o(obj, j2, i2);
    }

    public static void w(Object obj, long j2, long j3) {
        f.p(obj, j2, j3);
    }

    public static void x(Object obj, long j2, Object obj2) {
        f.q(obj, j2, obj2);
    }
}
