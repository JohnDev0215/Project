package c.a.a.c;

import cn.hutool.core.util.j;
import java.util.Objects;
import java.util.function.Function;

/* JADX INFO: compiled from: Opt.java */
/* JADX INFO: loaded from: classes.dex */
public class f<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final f<?> f2897a = new f<>(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final T f2898b;

    private f(T t) {
        this.f2898b = t;
    }

    public static <T> f<T> a() {
        return (f<T>) f2897a;
    }

    public static <T> f<T> e(T t) {
        return t == null ? a() : new f<>(t);
    }

    public T b() {
        return this.f2898b;
    }

    public boolean c() {
        return this.f2898b == null;
    }

    public <U> f<U> d(Function<? super T, ? extends U> function) {
        Objects.requireNonNull(function);
        return c() ? a() : e(function.apply(this.f2898b));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof f) {
            return Objects.equals(this.f2898b, ((f) obj).f2898b);
        }
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(this.f2898b);
    }

    public String toString() {
        return j.w(this.f2898b);
    }
}
