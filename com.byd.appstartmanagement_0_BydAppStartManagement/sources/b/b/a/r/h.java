package b.b.a.r;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: compiled from: UnsafeAllocator.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class h {

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    public static class a extends h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Method f429a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Object f430b;

        public a(Method method, Object obj) {
            this.f429a = method;
            this.f430b = obj;
        }

        @Override // b.b.a.r.h
        public <T> T d(Class<T> cls) throws Exception {
            h.b(cls);
            return (T) this.f429a.invoke(this.f430b, cls);
        }
    }

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    public static class b extends h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Method f431a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f432b;

        public b(Method method, int i) {
            this.f431a = method;
            this.f432b = i;
        }

        @Override // b.b.a.r.h
        public <T> T d(Class<T> cls) throws Exception {
            h.b(cls);
            return (T) this.f431a.invoke(null, cls, Integer.valueOf(this.f432b));
        }
    }

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    public static class c extends h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Method f433a;

        public c(Method method) {
            this.f433a = method;
        }

        @Override // b.b.a.r.h
        public <T> T d(Class<T> cls) throws Exception {
            h.b(cls);
            return (T) this.f433a.invoke(null, cls, Object.class);
        }
    }

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    public static class d extends h {
        @Override // b.b.a.r.h
        public <T> T d(Class<T> cls) {
            throw new UnsupportedOperationException("Cannot allocate " + cls);
        }
    }

    public static void b(Class<?> cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            throw new UnsupportedOperationException("Interface can't be instantiated! Interface name: " + cls.getName());
        }
        if (Modifier.isAbstract(modifiers)) {
            throw new UnsupportedOperationException("Abstract class can't be instantiated! Class name: " + cls.getName());
        }
    }

    public static h c() {
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

    public abstract <T> T d(Class<T> cls) throws Exception;
}
