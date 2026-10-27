package e.d0;

/* JADX INFO: compiled from: NamedRunnable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements Runnable {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f3335c;

    public b(String str, Object... objArr) {
        this.f3335c = c.l(str, objArr);
    }

    public abstract void k();

    @Override // java.lang.Runnable
    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.f3335c);
        try {
            k();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
