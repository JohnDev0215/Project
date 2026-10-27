package f.k0;

/* JADX INFO: compiled from: NamedRunnable.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class d implements Runnable {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final String f5132b;

    public d(String str, Object... objArr) {
        this.f5132b = e.p(str, objArr);
    }

    protected abstract void k();

    @Override // java.lang.Runnable
    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.f5132b);
        try {
            k();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
