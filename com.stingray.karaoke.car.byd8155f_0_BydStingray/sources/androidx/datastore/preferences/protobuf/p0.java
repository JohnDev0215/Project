package androidx.datastore.preferences.protobuf;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes.dex */
public final class p0 extends q0 {
    @Override // androidx.datastore.preferences.protobuf.q0
    public final boolean c(long j3, Object obj) {
        return this.f11880a.getBoolean(obj, j3);
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final byte d(long j3, Object obj) {
        return this.f11880a.getByte(obj, j3);
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final double e(long j3, Object obj) {
        return this.f11880a.getDouble(obj, j3);
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final float f(long j3, Object obj) {
        return this.f11880a.getFloat(obj, j3);
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final void k(Object obj, long j3, boolean z6) {
        this.f11880a.putBoolean(obj, j3, z6);
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final void l(Object obj, long j3, byte b10) {
        this.f11880a.putByte(obj, j3, b10);
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final void m(Object obj, long j3, double d) {
        this.f11880a.putDouble(obj, j3, d);
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final void n(Object obj, long j3, float f10) {
        this.f11880a.putFloat(obj, j3, f10);
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final boolean r() {
        if (!super.r()) {
            return false;
        }
        try {
            Class<?> cls = this.f11880a.getClass();
            Class cls2 = Long.TYPE;
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
            r0.a(th);
            return false;
        }
    }

    @Override // androidx.datastore.preferences.protobuf.q0
    public final boolean s() {
        Unsafe unsafe = this.f11880a;
        if (unsafe != null) {
            try {
                Class<?> cls = unsafe.getClass();
                cls.getMethod("objectFieldOffset", Field.class);
                Class cls2 = Long.TYPE;
                cls.getMethod("getLong", Object.class, cls2);
                if (r0.e() != null) {
                    try {
                        Class<?> cls3 = this.f11880a.getClass();
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
                        r0.a(th);
                        return false;
                    }
                }
            } catch (Throwable th2) {
                r0.a(th2);
            }
        }
        return false;
    }
}
