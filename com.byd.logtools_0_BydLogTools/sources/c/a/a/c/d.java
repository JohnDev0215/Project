package c.a.a.c;

import java.util.function.Supplier;

/* JADX INFO: compiled from: Assert.java */
/* JADX INFO: loaded from: classes.dex */
public class d {
    static /* synthetic */ IllegalArgumentException a(String str, Object[] objArr) {
        return new IllegalArgumentException(c.a.a.d.b.g(str, objArr));
    }

    static /* synthetic */ IllegalArgumentException b(String str, Object[] objArr) {
        return new IllegalArgumentException(c.a.a.d.b.g(str, objArr));
    }

    static /* synthetic */ IllegalArgumentException c(String str, Object[] objArr) {
        return new IllegalArgumentException(c.a.a.d.b.g(str, objArr));
    }

    public static <T extends CharSequence> T d(T t, final String str, final Object... objArr) throws IllegalArgumentException {
        return (T) e(t, new Supplier() { // from class: c.a.a.c.a
            @Override // java.util.function.Supplier
            public final Object get() {
                return d.a(str, objArr);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <T extends CharSequence, X extends Throwable> T e(T t, Supplier<X> supplier) throws Throwable {
        if (c.a.a.d.b.i(t)) {
            throw supplier.get();
        }
        return t;
    }

    public static <T extends CharSequence> T f(T t) throws IllegalArgumentException {
        return (T) g(t, "[Assertion failed] - this String argument must have length; it must not be null or empty", new Object[0]);
    }

    public static <T extends CharSequence> T g(T t, final String str, final Object... objArr) throws IllegalArgumentException {
        return (T) h(t, new Supplier() { // from class: c.a.a.c.b
            @Override // java.util.function.Supplier
            public final Object get() {
                return d.b(str, objArr);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <T extends CharSequence, X extends Throwable> T h(T t, Supplier<X> supplier) throws Throwable {
        if (c.a.a.d.b.j(t)) {
            throw supplier.get();
        }
        return t;
    }

    public static <T> T i(T t) throws IllegalArgumentException {
        return (T) j(t, "[Assertion failed] - this argument is required; it must not be null", new Object[0]);
    }

    public static <T> T j(T t, final String str, final Object... objArr) throws IllegalArgumentException {
        return (T) k(t, new Supplier() { // from class: c.a.a.c.c
            @Override // java.util.function.Supplier
            public final Object get() {
                return d.c(str, objArr);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: X extends java.lang.Throwable */
    public static <T, X extends Throwable> T k(T t, Supplier<X> supplier) throws Throwable {
        if (t != null) {
            return t;
        }
        throw supplier.get();
    }
}
