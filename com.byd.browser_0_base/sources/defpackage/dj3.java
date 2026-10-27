package defpackage;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Objects;
import sun.misc.Unsafe;

/* JADX INFO: compiled from: UnsafeUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class dj3 {
    public static final Unsafe a;
    public static final Class<?> b;
    public static final d c;
    public static final boolean d;
    public static final boolean e;
    public static final long f;
    public static final long g;
    public static final boolean h;

    /* JADX INFO: compiled from: UnsafeUtil.java */
    public static class a implements PrivilegedExceptionAction<Unsafe> {
        public static Unsafe a() throws IllegalAccessException {
            for (Field field : Unsafe.class.getDeclaredFields()) {
                field.setAccessible(true);
                Object obj = field.get(null);
                if (Unsafe.class.isInstance(obj)) {
                    return (Unsafe) Unsafe.class.cast(obj);
                }
            }
            return null;
        }

        @Override // java.security.PrivilegedExceptionAction
        public final /* bridge */ /* synthetic */ Unsafe run() {
            return a();
        }
    }

    /* JADX INFO: compiled from: UnsafeUtil.java */
    public static final class b extends d {
        public b(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // dj3.d
        public final void c(long j, byte[] bArr, long j2, long j3) {
            throw new UnsupportedOperationException();
        }

        @Override // dj3.d
        public final boolean d(long j, Object obj) {
            return dj3.h ? dj3.i(j, obj) != 0 : dj3.j(j, obj) != 0;
        }

        @Override // dj3.d
        public final byte e(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // dj3.d
        public final double f(long j, Object obj) {
            return Double.longBitsToDouble(i(j, obj));
        }

        @Override // dj3.d
        public final float g(long j, Object obj) {
            return Float.intBitsToFloat(h(j, obj));
        }

        @Override // dj3.d
        public final Object k(Field field) {
            try {
                return field.get(null);
            } catch (IllegalAccessException unused) {
                return null;
            }
        }

        @Override // dj3.d
        public final void m(long j, Object obj, boolean z) {
            if (dj3.h) {
                dj3.t(obj, j, z ? (byte) 1 : (byte) 0);
            } else {
                dj3.u(obj, j, z ? (byte) 1 : (byte) 0);
            }
        }

        @Override // dj3.d
        public final void n(Object obj, long j, byte b) {
            if (dj3.h) {
                dj3.t(obj, j, b);
            } else {
                dj3.u(obj, j, b);
            }
        }

        @Override // dj3.d
        public final void o(long j, Object obj, double d) {
            r(j, Double.doubleToLongBits(d), obj);
        }

        @Override // dj3.d
        public final void p(float f, long j, Object obj) {
            q(j, Float.floatToIntBits(f), obj);
        }

        @Override // dj3.d
        public final boolean u() {
            return false;
        }
    }

    /* JADX INFO: compiled from: UnsafeUtil.java */
    public static final class c extends d {
        public c(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // dj3.d
        public final void c(long j, byte[] bArr, long j2, long j3) {
            throw new UnsupportedOperationException();
        }

        @Override // dj3.d
        public final boolean d(long j, Object obj) {
            return dj3.h ? dj3.i(j, obj) != 0 : dj3.j(j, obj) != 0;
        }

        @Override // dj3.d
        public final byte e(long j) {
            throw new UnsupportedOperationException();
        }

        @Override // dj3.d
        public final double f(long j, Object obj) {
            return Double.longBitsToDouble(i(j, obj));
        }

        @Override // dj3.d
        public final float g(long j, Object obj) {
            return Float.intBitsToFloat(h(j, obj));
        }

        @Override // dj3.d
        public final Object k(Field field) {
            try {
                return field.get(null);
            } catch (IllegalAccessException unused) {
                return null;
            }
        }

        @Override // dj3.d
        public final void m(long j, Object obj, boolean z) {
            if (dj3.h) {
                dj3.t(obj, j, z ? (byte) 1 : (byte) 0);
            } else {
                dj3.u(obj, j, z ? (byte) 1 : (byte) 0);
            }
        }

        @Override // dj3.d
        public final void n(Object obj, long j, byte b) {
            if (dj3.h) {
                dj3.t(obj, j, b);
            } else {
                dj3.u(obj, j, b);
            }
        }

        @Override // dj3.d
        public final void o(long j, Object obj, double d) {
            r(j, Double.doubleToLongBits(d), obj);
        }

        @Override // dj3.d
        public final void p(float f, long j, Object obj) {
            q(j, Float.floatToIntBits(f), obj);
        }

        @Override // dj3.d
        public final boolean u() {
            return false;
        }
    }

    /* JADX INFO: compiled from: UnsafeUtil.java */
    public static abstract class d {
        public final Unsafe a;

        public d(Unsafe unsafe) {
            this.a = unsafe;
        }

        public final int a(Class<?> cls) {
            return this.a.arrayBaseOffset(cls);
        }

        public final int b(Class<?> cls) {
            return this.a.arrayIndexScale(cls);
        }

        public abstract void c(long j, byte[] bArr, long j2, long j3);

        public abstract boolean d(long j, Object obj);

        public abstract byte e(long j);

        public abstract double f(long j, Object obj);

        public abstract float g(long j, Object obj);

        public final int h(long j, Object obj) {
            return this.a.getInt(obj, j);
        }

        public final long i(long j, Object obj) {
            return this.a.getLong(obj, j);
        }

        public final Object j(long j, Object obj) {
            return this.a.getObject(obj, j);
        }

        public abstract Object k(Field field);

        public final long l(Field field) {
            return this.a.objectFieldOffset(field);
        }

        public abstract void m(long j, Object obj, boolean z);

        public abstract void n(Object obj, long j, byte b);

        public abstract void o(long j, Object obj, double d);

        public abstract void p(float f, long j, Object obj);

        public final void q(long j, int i, Object obj) {
            this.a.putInt(obj, j, i);
        }

        public final void r(long j, long j2, Object obj) {
            this.a.putLong(obj, j, j2);
        }

        public final void s(long j, Object obj, Object obj2) {
            this.a.putObject(obj, j, obj2);
        }

        public boolean t() {
            Unsafe unsafe = this.a;
            if (unsafe == null) {
                return false;
            }
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", Field.class);
                cls.getMethod("arrayBaseOffset", Class.class);
                cls.getMethod("arrayIndexScale", Class.class);
                Class<?> cls2 = Long.TYPE;
                cls.getMethod("getInt", Object.class, cls2);
                cls.getMethod("putInt", Object.class, cls2, Integer.TYPE);
                cls.getMethod("getLong", Object.class, cls2);
                cls.getMethod("putLong", Object.class, cls2, cls2);
                cls.getMethod("getObject", Object.class, cls2);
                cls.getMethod("putObject", Object.class, cls2, Object.class);
                return true;
            } catch (Throwable th) {
                Unsafe unsafe2 = dj3.a;
                Objects.toString(th);
                return false;
            }
        }

        public abstract boolean u();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0029  */
    static {
        /*
            sun.misc.Unsafe r0 = p()
            defpackage.dj3.a = r0
            java.lang.Class<?> r1 = defpackage.q4.a
            defpackage.dj3.b = r1
            java.lang.Class r1 = java.lang.Long.TYPE
            boolean r1 = f(r1)
            java.lang.Class r2 = java.lang.Integer.TYPE
            boolean r2 = f(r2)
            if (r0 != 0) goto L19
            goto L29
        L19:
            if (r1 == 0) goto L21
            dj3$c r1 = new dj3$c
            r1.<init>(r0)
            goto L2a
        L21:
            if (r2 == 0) goto L29
            dj3$b r1 = new dj3$b
            r1.<init>(r0)
            goto L2a
        L29:
            r1 = 0
        L2a:
            defpackage.dj3.c = r1
            r0 = 0
            if (r1 != 0) goto L31
            r2 = r0
            goto L35
        L31:
            boolean r2 = r1.u()
        L35:
            defpackage.dj3.d = r2
            if (r1 != 0) goto L3b
            r2 = r0
            goto L3f
        L3b:
            boolean r2 = r1.t()
        L3f:
            defpackage.dj3.e = r2
            java.lang.Class<byte[]> r2 = byte[].class
            int r2 = b(r2)
            long r2 = (long) r2
            defpackage.dj3.f = r2
            java.lang.Class<boolean[]> r2 = boolean[].class
            b(r2)
            c(r2)
            java.lang.Class<int[]> r2 = int[].class
            b(r2)
            c(r2)
            java.lang.Class<long[]> r2 = long[].class
            b(r2)
            c(r2)
            java.lang.Class<float[]> r2 = float[].class
            b(r2)
            c(r2)
            java.lang.Class<double[]> r2 = double[].class
            b(r2)
            c(r2)
            java.lang.Class<java.lang.Object[]> r2 = java.lang.Object[].class
            b(r2)
            c(r2)
            java.lang.reflect.Field r2 = d()
            if (r2 == 0) goto L88
            if (r1 != 0) goto L83
            goto L88
        L83:
            long r1 = r1.l(r2)
            goto L8a
        L88:
            r1 = -1
        L8a:
            defpackage.dj3.g = r1
            java.nio.ByteOrder r1 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r2 = java.nio.ByteOrder.BIG_ENDIAN
            if (r1 != r2) goto L95
            r0 = 1
        L95:
            defpackage.dj3.h = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.dj3.<clinit>():void");
    }

    public static <T> T a(Class<T> cls) {
        try {
            return (T) a.allocateInstance(cls);
        } catch (InstantiationException e2) {
            throw new IllegalStateException(e2);
        }
    }

    public static int b(Class<?> cls) {
        if (e) {
            return c.a(cls);
        }
        return -1;
    }

    public static void c(Class cls) {
        if (e) {
            c.b(cls);
        }
    }

    public static Field d() {
        Field declaredField;
        Field declaredField2;
        Class<?> cls = q4.a;
        try {
            declaredField = Buffer.class.getDeclaredField("effectiveDirectAddress");
        } catch (Throwable unused) {
            declaredField = null;
        }
        if (declaredField != null) {
            return declaredField;
        }
        try {
            declaredField2 = Buffer.class.getDeclaredField("address");
        } catch (Throwable unused2) {
            declaredField2 = null;
        }
        if (declaredField2 == null || declaredField2.getType() != Long.TYPE) {
            return null;
        }
        return declaredField2;
    }

    public static void e(long j, byte[] bArr, long j2, long j3) {
        c.c(j, bArr, j2, j3);
    }

    public static boolean f(Class<?> cls) {
        Class<?> cls2 = q4.a;
        try {
            Class<?> cls3 = b;
            Class<?> cls4 = Boolean.TYPE;
            cls3.getMethod("peekLong", cls, cls4);
            cls3.getMethod("pokeLong", cls, Long.TYPE, cls4);
            Class<?> cls5 = Integer.TYPE;
            cls3.getMethod("pokeInt", cls, cls5, cls4);
            cls3.getMethod("peekInt", cls, cls4);
            cls3.getMethod("pokeByte", cls, Byte.TYPE);
            cls3.getMethod("peekByte", cls);
            cls3.getMethod("pokeByteArray", cls, byte[].class, cls5, cls5);
            cls3.getMethod("peekByteArray", cls, byte[].class, cls5, cls5);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean g(long j, Object obj) {
        return c.d(j, obj);
    }

    public static byte h(long j) {
        return c.e(j);
    }

    public static byte i(long j, Object obj) {
        return (byte) ((m((-4) & j, obj) >>> ((int) (((~j) & 3) << 3))) & 255);
    }

    public static byte j(long j, Object obj) {
        return (byte) ((m((-4) & j, obj) >>> ((int) ((j & 3) << 3))) & 255);
    }

    public static double k(long j, Object obj) {
        return c.f(j, obj);
    }

    public static float l(long j, Object obj) {
        return c.g(j, obj);
    }

    public static int m(long j, Object obj) {
        return c.h(j, obj);
    }

    public static long n(long j, Object obj) {
        return c.i(j, obj);
    }

    public static Object o(long j, Object obj) {
        return c.j(j, obj);
    }

    public static Unsafe p() {
        try {
            return (Unsafe) AccessController.doPrivileged(new a());
        } catch (Throwable unused) {
            return null;
        }
    }

    public static long q(Field field) {
        return c.l(field);
    }

    public static void r(long j, Object obj, boolean z) {
        c.m(j, obj, z);
    }

    public static void s(byte[] bArr, long j, byte b2) {
        c.n(bArr, f + j, b2);
    }

    public static void t(Object obj, long j, byte b2) {
        long j2 = (-4) & j;
        int iM = m(j2, obj);
        int i = ((~((int) j)) & 3) << 3;
        x(j2, ((255 & b2) << i) | (iM & (~(255 << i))), obj);
    }

    public static void u(Object obj, long j, byte b2) {
        long j2 = (-4) & j;
        int i = (((int) j) & 3) << 3;
        x(j2, ((255 & b2) << i) | (m(j2, obj) & (~(255 << i))), obj);
    }

    public static void v(long j, Object obj, double d2) {
        c.o(j, obj, d2);
    }

    public static void w(float f2, long j, Object obj) {
        c.p(f2, j, obj);
    }

    public static void x(long j, int i, Object obj) {
        c.q(j, i, obj);
    }

    public static void y(long j, long j2, Object obj) {
        c.r(j, j2, obj);
    }

    public static void z(long j, Object obj, Object obj2) {
        c.s(j, obj, obj2);
    }
}
