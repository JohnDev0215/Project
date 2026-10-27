package io.reactivex.rxjava3.disposables;

/* JADX INFO: loaded from: classes7.dex */
final class RunnableDisposable extends ReferenceDisposable<Runnable> {
    RunnableDisposable(Runnable value) {
        super(value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.reactivex.rxjava3.disposables.ReferenceDisposable
    public void onDisposed(Runnable value) {
        value.run();
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        return "RunnableDisposable(disposed=" + isDisposed() + ", " + get() + ")";
    }
}
