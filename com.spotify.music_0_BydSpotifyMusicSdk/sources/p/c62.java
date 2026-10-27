package p;

import androidx.work.rxjava3.RxWorker;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes.dex */
public final class c62 implements SingleObserver, Runnable {
    public final androidx.work.impl.utils.futures.b c;
    public Disposable e;

    public c62() {
        androidx.work.impl.utils.futures.b bVar = new androidx.work.impl.utils.futures.b();
        this.c = bVar;
        bVar.a(this, RxWorker.n);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        this.c.k(th);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        this.e = disposable;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        this.c.j(obj);
    }

    @Override // java.lang.Runnable
    public final void run() {
        Disposable disposable;
        if (!(this.c.c instanceof d1) || (disposable = this.e) == null) {
            return;
        }
        disposable.dispose();
    }
}
