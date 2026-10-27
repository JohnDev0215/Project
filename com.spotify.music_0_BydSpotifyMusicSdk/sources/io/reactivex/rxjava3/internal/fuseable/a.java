package io.reactivex.rxjava3.internal.fuseable;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements io.reactivex.rxjava3.operators.d, io.reactivex.rxjava3.operators.b {
    @Override // p.tg2
    public final void a(long j) {
    }

    @Override // io.reactivex.rxjava3.operators.c
    public final int b(int i) {
        return i & 2;
    }

    @Override // p.tg2
    public void cancel() {
    }

    @Override // io.reactivex.rxjava3.operators.g
    public final void clear() {
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return false;
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
