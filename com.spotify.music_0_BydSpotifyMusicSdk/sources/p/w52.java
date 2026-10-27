package p;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes.dex */
public final class w52 implements CompletableObserver {
    public final /* synthetic */ yp c;

    public w52(zp zpVar) {
        this.c = zpVar;
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onComplete() {
        this.c.k(lq2.a);
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onError(Throwable th) {
        ((zp) this.c).k(kotlin.a.b(th));
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onSubscribe(Disposable disposable) {
        kotlinx.coroutines.rx3.a.d(this.c, disposable);
    }
}
