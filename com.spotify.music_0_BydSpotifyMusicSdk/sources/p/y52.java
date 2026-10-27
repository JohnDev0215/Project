package p;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class y52 implements Observer {
    public final /* synthetic */ sx1 c;
    public final /* synthetic */ AtomicReference e;

    public y52(sx1 sx1Var, AtomicReference atomicReference) {
        this.c = sx1Var;
        this.e = atomicReference;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        ((rx1) this.c).h(null);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        ((rx1) this.c).h(th);
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) throws Throwable {
        try {
            kotlinx.coroutines.channels.b.b(this.c, obj);
        } catch (InterruptedException unused) {
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        AtomicReference atomicReference;
        do {
            atomicReference = this.e;
            if (atomicReference.compareAndSet(null, disposable)) {
                return;
            }
        } while (atomicReference.get() == null);
        disposable.dispose();
    }
}
