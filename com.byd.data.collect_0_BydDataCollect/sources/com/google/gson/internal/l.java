package com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: compiled from: UnsafeAllocator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class l {

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    static class a extends l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Method f4586a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f4587b;

        a(Method method, Object obj) {
            this.f4586a = method;
            this.f4587b = obj;
        }

        @Override // com.google.gson.internal.l
        public <T> T c(Class<T> cls) {
            l.a(cls);
            return (T) this.f4586a.invoke(this.f4587b, cls);
        }
    }

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    static class b extends l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Method f4588a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f4589b;

        b(Method method, int i2) {
            this.f4588a = method;
            this.f4589b = i2;
        }

        @Override // com.google.gson.internal.l
        public <T> T c(Class<T> cls) {
            l.a(cls);
            return (T) this.f4588a.invoke(null, cls, Integer.valueOf(this.f4589b));
        }
    }

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    static class c extends l {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Method f4590a;

        c(Method method) {
            this.f4590a = method;
        }

        @Override // com.google.gson.internal.l
        public <T> T c(Class<T> cls) {
            l.a(cls);
            return (T) this.f4590a.invoke(null, cls, Object.class);
        }
    }

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    static class d extends l {
        d() {
        }

        @Override // com.google.gson.internal.l
        public <T> T c(Class<T> cls) {
            throw new UnsupportedOperationException("Cannot allocate " + cls);
        }
    }

    static void a(Class<?> cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            throw new UnsupportedOperationException("Interface can't be instantiated! Interface name: " + cls.getName());
        }
        if (Modifier.isAbstract(modifiers)) {
            throw new UnsupportedOperationException("Abstract class can't be instantiated! Class name: " + cls.getName());
        }
    }

    public static l b() {
        try {
            Class<?> cls = Class.forName("sun.misc.Unsafe");
            Field declaredField = cls.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            return new a(cls.getMethod("allocateInstance", Class.class), declaredField.get(null));
        } catch (Exception unused) {
            try {
                try {
                    Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                    declaredMethod.setAccessible(true);
                    int iIntValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                    Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                    declaredMethod2.setAccessible(true);
                    return new b(declaredMethod2, iIntValue);
                } catch (Exception unused2) {
                    Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                    declaredMethod3.setAccessible(true);
                    return new c(declaredMethod3);
                }
            } catch (Exception unused3) {
                return new d();
            }
        }
    }

    public abstract <T> T c(Class<T> cls);
}
