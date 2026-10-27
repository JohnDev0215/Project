package h;

import e.a0;
import e.b0;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Response.java */
/* JADX INFO: loaded from: classes.dex */
public final class l<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final a0 f5812a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    public final T f5813b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    public final b0 f5814c;

    public l(a0 a0Var, @Nullable T t, @Nullable b0 b0Var) {
        this.f5812a = a0Var;
        this.f5813b = t;
        this.f5814c = b0Var;
    }

    public static <T> l<T> c(b0 b0Var, a0 a0Var) {
        o.b(b0Var, "body == null");
        o.b(a0Var, "rawResponse == null");
        if (a0Var.K()) {
            throw new IllegalArgumentException("rawResponse should not be successful response");
        }
        return new l<>(a0Var, null, b0Var);
    }

    public static <T> l<T> f(@Nullable T t, a0 a0Var) {
        o.b(a0Var, "rawResponse == null");
        if (a0Var.K()) {
            return new l<>(a0Var, t, null);
        }
        throw new IllegalArgumentException("rawResponse must be successful response");
    }

    @Nullable
    public T a() {
        return this.f5813b;
    }

    public int b() {
        return this.f5812a.s();
    }

    public boolean d() {
        return this.f5812a.K();
    }

    public String e() {
        return this.f5812a.L();
    }

    public String toString() {
        return this.f5812a.toString();
    }
}
