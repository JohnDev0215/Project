package io.reactivex.rxjava3.disposables;

import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.internal.functions.Functions;
import java.util.Objects;

/* JADX INFO: loaded from: classes7.dex */
public interface Disposable {
    void dispose();

    boolean isDisposed();

    static Disposable fromRunnable(Runnable run) {
        Objects.requireNonNull(run, "run is null");
        return new RunnableDisposable(run);
    }

    static Disposable fromAction(Action action) {
        Objects.requireNonNull(action, "action is null");
        return new ActionDisposable(action);
    }

    static Disposable empty() {
        return fromRunnable(Functions.EMPTY_RUNNABLE);
    }

    static Disposable disposed() {
        return EmptyDisposable.INSTANCE;
    }
}
