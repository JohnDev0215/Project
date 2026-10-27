package b.b.g.p;

/* JADX INFO: compiled from: BydMemoryMonitor.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile b f1363a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Object f1364b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile int f1365c = 0;

    public static b b() {
        if (f1363a == null) {
            synchronized (f1364b) {
                f1363a = new b();
            }
        }
        return f1363a;
    }

    public final void a(int i) {
        b.b.m.c.a.c("BydMemoryMonitor", "doReleaseOnLowMemory", ", level: " + i);
    }

    public void c(int i) {
        b.b.m.c.a.c("BydMemoryMonitor", "onTrimMemory", ", level: " + i);
        this.f1365c = i;
        if (i < 5) {
            return;
        }
        a(i);
    }
}
