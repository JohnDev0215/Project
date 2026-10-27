package e.a.k;

/* JADX INFO: compiled from: RunnableDisposable.java */
/* JADX INFO: loaded from: classes.dex */
final class e extends d<Runnable> {
    private static final long serialVersionUID = -8219729196779211169L;

    e(Runnable runnable) {
        super(runnable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // e.a.k.d
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public void b(Runnable runnable) {
        runnable.run();
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        return "RunnableDisposable(disposed=" + a() + ", " + get() + ")";
    }
}
