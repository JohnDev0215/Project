package h.z.a;

import h.t;
import java.util.Objects;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: Result.java */
/* JADX INFO: loaded from: classes.dex */
public final class e<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    private final t<T> f5725a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    private final Throwable f5726b;

    private e(@Nullable t<T> tVar, @Nullable Throwable th) {
        this.f5725a = tVar;
        this.f5726b = th;
    }

    public static <T> e<T> a(Throwable th) {
        Objects.requireNonNull(th, "error == null");
        return new e<>(null, th);
    }

    public static <T> e<T> b(t<T> tVar) {
        Objects.requireNonNull(tVar, "response == null");
        return new e<>(tVar, null);
    }
}
