package c.a.a.d;

import java.util.function.Supplier;

/* JADX INFO: compiled from: Assert.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    static /* synthetic */ IllegalArgumentException a(String str, Object[] objArr) {
        return new IllegalArgumentException(c.a.a.f.b.f(str, objArr));
    }

    static /* synthetic */ IllegalArgumentException b(String str, Object[] objArr) {
        return new IllegalArgumentException(c.a.a.f.b.f(str, objArr));
    }

    static /* synthetic */ IllegalArgumentException c(String str, Object[] objArr) {
        return new IllegalArgumentException(c.a.a.f.b.f(str, objArr));
    }

    public static <T extends CharSequence> T d(T t, final String str, final Object... objArr) {
        return (T) e(t, new Supplier() { // from class: c.a.a.d.a
            @Override // java.util.function.Supplier
            public final Object get() {
                return d.a(str, objArr);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <T extends CharSequence, X extends Throwable> T e(T t, Supplier<X> supplier) throws X {
        if (c.a.a.f.b.h(t)) {
            throw supplier.get();
        }
        return t;
    }

    public static <T extends CharSequence> T f(T t) {
        return (T) g(t, "[Assertion failed] - this String argument must have length; it must not be null or empty", new Object[0]);
    }

    public static <T extends CharSequence> T g(T t, final String str, final Object... objArr) {
        return (T) h(t, new Supplier() { // from class: c.a.a.d.b
            @Override // java.util.function.Supplier
            public final Object get() {
                return d.b(str, objArr);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <T extends CharSequence, X extends Throwable> T h(T t, Supplier<X> supplier) throws X {
        if (c.a.a.f.b.i(t)) {
            throw supplier.get();
        }
        return t;
    }

    public static <T> T i(T t) {
        return (T) j(t, "[Assertion failed] - this argument is required; it must not be null", new Object[0]);
    }

    public static <T> T j(T t, final String str, final Object... objArr) {
        return (T) k(t, new Supplier() { // from class: c.a.a.d.c
            @Override // java.util.function.Supplier
            public final Object get() {
                return d.c(str, objArr);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <T, X extends Throwable> T k(T t, Supplier<X> supplier) throws X {
        if (t != null) {
            return t;
        }
        throw supplier.get();
    }
}
