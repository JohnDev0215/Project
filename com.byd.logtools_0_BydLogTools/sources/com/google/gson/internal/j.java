package com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: compiled from: UnsafeAllocator.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class j {

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    class a extends j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Method f4050a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Object f4051b;

        a(Method method, Object obj) {
            this.f4050a = method;
            this.f4051b = obj;
        }

        @Override // com.google.gson.internal.j
        public <T> T c(Class<T> cls) throws Exception {
            j.a(cls);
            return (T) this.f4050a.invoke(this.f4051b, cls);
        }
    }

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    class b extends j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Method f4052a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f4053b;

        b(Method method, int i) {
            this.f4052a = method;
            this.f4053b = i;
        }

        @Override // com.google.gson.internal.j
        public <T> T c(Class<T> cls) throws Exception {
            j.a(cls);
            return (T) this.f4052a.invoke(null, cls, Integer.valueOf(this.f4053b));
        }
    }

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    class c extends j {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ Method f4054a;

        c(Method method) {
            this.f4054a = method;
        }

        @Override // com.google.gson.internal.j
        public <T> T c(Class<T> cls) throws Exception {
            j.a(cls);
            return (T) this.f4054a.invoke(null, cls, Object.class);
        }
    }

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    class d extends j {
        d() {
        }

        @Override // com.google.gson.internal.j
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

    public static j b() {
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

    public abstract <T> T c(Class<T> cls) throws Exception;
}
