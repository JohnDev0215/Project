package c.a.a.d;

import c.a.a.g.k;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: compiled from: Opt.java */
/* JADX INFO: loaded from: classes.dex */
public class f<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final f<?> f3040a = new f<>(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final T f3041b;

    private f(T t) {
        this.f3041b = t;
    }

    public static <T> f<T> a() {
        return (f<T>) f3040a;
    }

    public static <T> f<T> e(T t) {
        return t == null ? a() : new f<>(t);
    }

    public T b() {
        return this.f3041b;
    }

    public boolean c() {
        return this.f3041b == null;
    }

    public <U> f<U> d(Function<? super T, ? extends U> function) {
        Objects.requireNonNull(function);
        return c() ? a() : e(function.apply(this.f3041b));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof f) {
            return Objects.equals(this.f3041b, ((f) obj).f3041b);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.f3041b);
    }

    public String toString() {
        return k.v(this.f3041b);
    }
}
