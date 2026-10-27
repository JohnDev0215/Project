package defpackage;

/* JADX INFO: compiled from: ArchTaskExecutor.java */
/* JADX INFO: loaded from: classes.dex */
public final class g8 extends vd1 {
    public static volatile g8 l;
    public final c80 k = new c80();

    public static g8 J() {
        if (l != null) {
            return l;
        }
        synchronized (g8.class) {
            if (l == null) {
                l = new g8();
            }
        }
        return l;
    }
}
