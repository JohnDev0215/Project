package h.p.a;

import h.l;
import java.util.Objects;

/* JADX INFO: compiled from: Result.java */
/* JADX INFO: loaded from: classes.dex */
public final class d<T> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final l<T> f5864a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Throwable f5865b;

    public d(l<T> lVar, Throwable th) {
        this.f5864a = lVar;
        this.f5865b = th;
    }

    public static <T> d<T> a(Throwable th) {
        Objects.requireNonNull(th, "error == null");
        return new d<>(null, th);
    }

    public static <T> d<T> b(l<T> lVar) {
        Objects.requireNonNull(lVar, "response == null");
        return new d<>(lVar, null);
    }
}
