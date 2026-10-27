package t4;

/* JADX INFO: compiled from: Disposables.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static b a() {
        return w4.c.INSTANCE;
    }

    public static b b(Runnable runnable) {
        x4.b.c(runnable, "run is null");
        return new e(runnable);
    }
}
