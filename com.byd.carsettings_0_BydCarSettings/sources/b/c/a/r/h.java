package b.c.a.r;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/* JADX INFO: compiled from: UnsafeAllocator.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class h {

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    public static class a extends h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Method f2245a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ Object f2246b;

        public a(Method method, Object obj) {
            this.f2245a = method;
            this.f2246b = obj;
        }

        @Override // b.c.a.r.h
        public <T> T d(Class<T> cls) throws Exception {
            h.b(cls);
            return (T) this.f2245a.invoke(this.f2246b, cls);
        }
    }

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    public static class b extends h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Method f2247a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f2248b;

        public b(Method method, int i) {
            this.f2247a = method;
            this.f2248b = i;
        }

        @Override // b.c.a.r.h
        public <T> T d(Class<T> cls) throws Exception {
            h.b(cls);
            return (T) this.f2247a.invoke(null, cls, Integer.valueOf(this.f2248b));
        }
    }

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    public static class c extends h {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Method f2249a;

        public c(Method method) {
            this.f2249a = method;
        }

        @Override // b.c.a.r.h
        public <T> T d(Class<T> cls) throws Exception {
            h.b(cls);
            return (T) this.f2249a.invoke(null, cls, Object.class);
        }
    }

    /* JADX INFO: compiled from: UnsafeAllocator.java */
    public static class d extends h {
        @Override // b.c.a.r.h
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
                    return new d();
                }
            } catch (Exception unused3) {
                Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                declaredMethod3.setAccessible(true);
                return new c(declaredMethod3);
            }
        }
    }

    public abstract <T> T d(Class<T> cls) throws Exception;
}
