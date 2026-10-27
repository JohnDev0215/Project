package h;

import f.g0;
import f.h0;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Response.java */
/* JADX INFO: loaded from: classes.dex */
public final class t<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final g0 f5685a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    private final T f5686b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    private final h0 f5687c;

    private t(g0 g0Var, @Nullable T t, @Nullable h0 h0Var) {
        this.f5685a = g0Var;
        this.f5686b = t;
        this.f5687c = h0Var;
    }

    public static <T> t<T> c(h0 h0Var, g0 g0Var) {
        Objects.requireNonNull(h0Var, "body == null");
        Objects.requireNonNull(g0Var, "rawResponse == null");
        if (g0Var.T()) {
            throw new IllegalArgumentException("rawResponse should not be successful response");
        }
        return new t<>(g0Var, null, h0Var);
    }

    public static <T> t<T> f(@Nullable T t, g0 g0Var) {
        Objects.requireNonNull(g0Var, "rawResponse == null");
        if (g0Var.T()) {
            return new t<>(g0Var, t, null);
        }
        throw new IllegalArgumentException("rawResponse must be successful response");
    }

    @Nullable
    public T a() {
        return this.f5686b;
    }

    public int b() {
        return this.f5685a.j();
    }

    public boolean d() {
        return this.f5685a.T();
    }

    public String e() {
        return this.f5685a.U();
    }

    public String toString() {
        return this.f5685a.toString();
    }
}
