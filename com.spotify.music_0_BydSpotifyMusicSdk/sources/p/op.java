package p;

import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes.dex */
public final class op implements Disposable {
    public final jp c;
    public volatile boolean e;

    public op(jp jpVar) {
        this.c = jpVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        this.e = true;
        this.c.cancel();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.e;
    }
}
