package io.reactivex.disposables;

/* JADX INFO: loaded from: classes7.dex */
final class RunnableDisposable extends ReferenceDisposable<Runnable> {
    RunnableDisposable(Runnable runnable) {
        super(runnable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.reactivex.disposables.ReferenceDisposable
    public void onDisposed(Runnable runnable) {
        runnable.run();
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        return "RunnableDisposable(disposed=" + isDisposed() + ", " + get() + ")";
    }
}
