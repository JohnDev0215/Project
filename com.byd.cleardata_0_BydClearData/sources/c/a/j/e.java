package c.a.j;

/* JADX INFO: loaded from: classes.dex */
final class e extends d<Runnable> {
    e(Runnable runnable) {
        super(runnable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // c.a.j.d
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void a(Runnable runnable) {
        runnable.run();
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        return "RunnableDisposable(disposed=" + g() + ", " + get() + ")";
    }
}
