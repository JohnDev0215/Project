package com.google.gson.internal.l;

import com.google.gson.JsonIOException;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/* JADX INFO: compiled from: UnsafeReflectionAccessor.java */
/* JADX INFO: loaded from: classes.dex */
final class c extends b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Class f4134b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Object f4135c = d();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final Field f4136d = c();

    c() {
    }

    private static Field c() {
        try {
            return AccessibleObject.class.getDeclaredField("override");
        } catch (Exception unused) {
            return null;
        }
    }

    private static Object d() {
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            f4134b = cls;
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Exception unused) {
            return null;
        }
    }

    @Override // com.google.gson.internal.l.b
    public void b(AccessibleObject accessibleObject) {
        if (e(accessibleObject)) {
            return;
        }
        try {
            accessibleObject.setAccessible(true);
        } catch (SecurityException e2) {
            throw new JsonIOException("Gson couldn't modify fields for " + accessibleObject + "\nand sun.misc.Unsafe not found.\nEither write a custom type adapter, or make fields accessible, or include sun.misc.Unsafe.", e2);
        }
    }

    boolean e(AccessibleObject accessibleObject) {
        if (this.f4135c != null && this.f4136d != null) {
            try {
                f4134b.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE).invoke(this.f4135c, accessibleObject, Long.valueOf(((Long) f4134b.getMethod("objectFieldOffset", Field.class).invoke(this.f4135c, this.f4136d)).longValue()), Boolean.TRUE);
                return true;
            } catch (Exception unused) {
            }
        }
        return false;
    }
}
