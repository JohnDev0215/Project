package io.reactivex.disposables;

import io.reactivex.internal.disposables.EmptyDisposable;

/* JADX INFO: compiled from: Disposables.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static b a() {
        return EmptyDisposable.INSTANCE;
    }

    public static b b(Runnable runnable) {
        d.a.n.a.b.c(runnable, "run is null");
        return new RunnableDisposable(runnable);
    }
}
