package com.squareup.moshi;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import p.ds2;

/* JADX INFO: loaded from: classes.dex */
abstract class c<T> {

    public class a extends c<T> {
        final /* synthetic */ Constructor a;
        final /* synthetic */ Class b;

        public a(Constructor constructor, Class cls) {
            this.a = constructor;
            this.b = cls;
        }

        @Override // com.squareup.moshi.c
        public T b() {
            return (T) this.a.newInstance(null);
        }

        public String toString() {
            return this.b.getName();
        }
    }

    public class b extends c<T> {
        final /* synthetic */ Method a;
        final /* synthetic */ Object b;
        final /* synthetic */ Class c;

        public b(Method method, Object obj, Class cls) {
            this.a = method;
            this.b = obj;
            this.c = cls;
        }

        @Override // com.squareup.moshi.c
        public T b() {
            return (T) this.a.invoke(this.b, this.c);
        }

        public String toString() {
            return this.c.getName();
        }
    }

    /* JADX INFO: renamed from: com.squareup.moshi.c$c, reason: collision with other inner class name */
    public class C0007c extends c<T> {
        final /* synthetic */ Method a;
        final /* synthetic */ Class b;
        final /* synthetic */ int c;

        public C0007c(Method method, Class cls, int i) {
            this.a = method;
            this.b = cls;
            this.c = i;
        }

        @Override // com.squareup.moshi.c
        public T b() {
            return (T) this.a.invoke(null, this.b, Integer.valueOf(this.c));
        }

        public String toString() {
            return this.b.getName();
        }
    }

    public class d extends c<T> {
        final /* synthetic */ Method a;
        final /* synthetic */ Class b;

        public d(Method method, Class cls) {
            this.a = method;
            this.b = cls;
        }

        @Override // com.squareup.moshi.c
        public T b() {
            return (T) this.a.invoke(null, this.b, Object.class);
        }

        public String toString() {
            return this.b.getName();
        }
    }

    public static <T> c<T> a(Class<?> cls) {
        try {
            Constructor<?> declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            return new a(declaredConstructor, cls);
        } catch (NoSuchMethodException unused) {
            try {
                Class<?> cls2 = Class.forName("sun.misc.Unsafe");
                Field declaredField = cls2.getDeclaredField("theUnsafe");
                declaredField.setAccessible(true);
                return new b(cls2.getMethod("allocateInstance", Class.class), declaredField.get(null), cls);
            } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException unused2) {
                try {
                    try {
                        Method declaredMethod = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", Class.class);
                        declaredMethod.setAccessible(true);
                        int iIntValue = ((Integer) declaredMethod.invoke(null, Object.class)).intValue();
                        Method declaredMethod2 = ObjectStreamClass.class.getDeclaredMethod("newInstance", Class.class, Integer.TYPE);
                        declaredMethod2.setAccessible(true);
                        return new C0007c(declaredMethod2, cls, iIntValue);
                    } catch (IllegalAccessException unused3) {
                        throw new AssertionError();
                    } catch (NoSuchMethodException unused4) {
                        Method declaredMethod3 = ObjectInputStream.class.getDeclaredMethod("newInstance", Class.class, Class.class);
                        declaredMethod3.setAccessible(true);
                        return new d(declaredMethod3, cls);
                    } catch (InvocationTargetException e) {
                        throw ds2.t(e);
                    }
                } catch (Exception unused5) {
                    throw new IllegalArgumentException("cannot construct instances of ".concat(cls.getName()));
                }
            } catch (IllegalAccessException unused6) {
                throw new AssertionError();
            }
        }
    }

    public abstract T b();
}
