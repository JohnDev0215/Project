package e.a.k;

/* JADX INFO: compiled from: Disposables.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static b a() {
        return e.a.n.a.c.INSTANCE;
    }

    public static b b(Runnable runnable) {
        e.a.n.b.b.c(runnable, "run is null");
        return new e(runnable);
    }
}
