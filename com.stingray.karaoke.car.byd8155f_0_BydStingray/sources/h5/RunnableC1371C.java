package h5;

/* JADX INFO: renamed from: h5.C, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class RunnableC1371C extends i implements Runnable {

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final Runnable f15535s;

    public RunnableC1371C(Runnable runnable) {
        runnable.getClass();
        this.f15535s = runnable;
    }

    @Override // h5.p
    public final String j() {
        return "task=[" + this.f15535s + "]";
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.f15535s.run();
        } catch (Throwable th) {
            m(th);
            throw th;
        }
    }
}
