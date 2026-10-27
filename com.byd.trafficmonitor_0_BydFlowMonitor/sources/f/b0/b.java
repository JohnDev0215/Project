package f.b0;

/* JADX INFO: compiled from: NamedRunnable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class b implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f5011b;

    public b(String str, Object... objArr) {
        this.f5011b = c.l(str, objArr);
    }

    public abstract void k();

    @Override // java.lang.Runnable
    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.f5011b);
        try {
            k();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
