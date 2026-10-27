package io.reactivex.rxjava3.internal.fuseable;

/* JADX INFO: loaded from: classes.dex */
public final class b extends a {
    public volatile boolean c;

    @Override // io.reactivex.rxjava3.internal.fuseable.a, p.tg2
    public final void cancel() {
        this.c = true;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.a, io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.c = true;
    }

    @Override // io.reactivex.rxjava3.internal.fuseable.a, io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.c;
    }
}
