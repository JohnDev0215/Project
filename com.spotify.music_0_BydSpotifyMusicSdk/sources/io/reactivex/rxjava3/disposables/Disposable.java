package io.reactivex.rxjava3.disposables;

/* JADX INFO: loaded from: classes.dex */
public interface Disposable {
    static Disposable e(Runnable runnable) {
        return new RunnableDisposable(runnable);
    }

    void dispose();

    boolean isDisposed();
}
