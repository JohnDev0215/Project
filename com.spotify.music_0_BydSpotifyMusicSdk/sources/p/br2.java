package p;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
public final class br2 extends cr2 {
    @Override // p.cr2
    public final void c(long j, byte[] bArr, long j2) {
        this.a.copyMemory((Object) null, j, bArr, dr2.f, j2);
    }

    @Override // p.cr2
    public final boolean d(long j, Object obj) {
        return this.a.getBoolean(obj, j);
    }

    @Override // p.cr2
    public final byte e(long j) {
        return this.a.getByte(j);
    }

    @Override // p.cr2
    public final byte f(long j, Object obj) {
        return this.a.getByte(obj, j);
    }

    @Override // p.cr2
    public final double g(long j, Object obj) {
        return this.a.getDouble(obj, j);
    }

    @Override // p.cr2
    public final float h(long j, Object obj) {
        return this.a.getFloat(obj, j);
    }

    @Override // p.cr2
    public final void m(Object obj, long j, boolean z) {
        this.a.putBoolean(obj, j, z);
    }

    @Override // p.cr2
    public final void n(Object obj, long j, byte b) {
        this.a.putByte(obj, j, b);
    }

    @Override // p.cr2
    public final void o(Object obj, long j, double d) {
        this.a.putDouble(obj, j, d);
    }

    @Override // p.cr2
    public final void p(Object obj, long j, float f) {
        this.a.putFloat(obj, j, f);
    }

    @Override // p.cr2
    public final boolean t() {
        if (!super.t()) {
            return false;
        }
        try {
            Class<?> cls = this.a.getClass();
            Class<?> cls2 = Long.TYPE;
            cls.getMethod("getByte", Object.class, cls2);
            cls.getMethod("putByte", Object.class, cls2, Byte.TYPE);
            cls.getMethod("getBoolean", Object.class, cls2);
            cls.getMethod("putBoolean", Object.class, cls2, Boolean.TYPE);
            cls.getMethod("getFloat", Object.class, cls2);
            cls.getMethod("putFloat", Object.class, cls2, Float.TYPE);
            cls.getMethod("getDouble", Object.class, cls2);
            cls.getMethod("putDouble", Object.class, cls2, Double.TYPE);
            return true;
        } catch (Throwable th) {
            dr2.a(th);
            return false;
        }
    }

    @Override // p.cr2
    public final boolean u() {
        Unsafe unsafe = this.a;
        if (unsafe != null) {
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", Field.class);
                Class<?> cls2 = Long.TYPE;
                cls.getMethod("getLong", Object.class, cls2);
                if (dr2.e() != null) {
                    try {
                        Class<?> cls3 = unsafe.getClass();
                        cls3.getMethod("getByte", cls2);
                        cls3.getMethod("putByte", cls2, Byte.TYPE);
                        cls3.getMethod("getInt", cls2);
                        cls3.getMethod("putInt", cls2, Integer.TYPE);
                        cls3.getMethod("getLong", cls2);
                        cls3.getMethod("putLong", cls2, cls2);
                        cls3.getMethod("copyMemory", cls2, cls2, cls2);
                        cls3.getMethod("copyMemory", Object.class, cls2, Object.class, cls2, cls2);
                        return true;
                    } catch (Throwable th) {
                        dr2.a(th);
                        return false;
                    }
                }
            } catch (Throwable th2) {
                dr2.a(th2);
            }
        }
        return false;
    }
}
