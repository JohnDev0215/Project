package io.reactivex.rxjava3.internal.disposables;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.operators.b;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class EmptyDisposable implements b {
    public static final EmptyDisposable c;
    public static final EmptyDisposable e;
    public static final /* synthetic */ EmptyDisposable[] k;

    static {
        EmptyDisposable emptyDisposable = new EmptyDisposable("INSTANCE", 0);
        c = emptyDisposable;
        EmptyDisposable emptyDisposable2 = new EmptyDisposable("NEVER", 1);
        e = emptyDisposable2;
        k = new EmptyDisposable[]{emptyDisposable, emptyDisposable2};
    }

    public static void a(Throwable th, Observer observer) {
        observer.onSubscribe(c);
        observer.onError(th);
    }

    public static EmptyDisposable valueOf(String str) {
        return (EmptyDisposable) Enum.valueOf(EmptyDisposable.class, str);
    }

    public static EmptyDisposable[] values() {
        return (EmptyDisposable[]) k.clone();
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        return i & 2;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this == c;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean isEmpty() {
        return true;
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final Object poll() {
        return null;
    }
}
