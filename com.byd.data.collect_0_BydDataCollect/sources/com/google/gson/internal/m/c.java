package com.google.gson.internal.m;

import com.google.gson.k;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: UnsafeReflectionAccessor.java */
/* JADX INFO: loaded from: classes.dex */
final class c extends b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Class f4592b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Object f4593c = d();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Field f4594d = c();

    c() {
    }

    private static Field c() {
        try {
            return AccessibleObject.class.getDeclaredField("override");
        } catch (NoSuchFieldException unused) {
            return null;
        }
    }

    private static Object d() {
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            f4592b = cls;
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.google.gson.internal.m.b
    public void b(AccessibleObject accessibleObject) {
        if (e(accessibleObject)) {
            return;
        }
        try {
            accessibleObject.setAccessible(true);
        } catch (SecurityException e2) {
            throw new k("Gson couldn't modify fields for " + accessibleObject + "\nand sun.misc.Unsafe not found.\nEither write a custom type adapter, or make fields accessible, or include sun.misc.Unsafe.", e2);
        }
    }

    boolean e(AccessibleObject accessibleObject) {
        if (this.f4593c != null && this.f4594d != null) {
            try {
                f4592b.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE).invoke(this.f4593c, accessibleObject, Long.valueOf(((Long) f4592b.getMethod("objectFieldOffset", Field.class).invoke(this.f4593c, this.f4594d)).longValue()), Boolean.TRUE);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }
}
