package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.Objects;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class e extends Single {
    public final /* synthetic */ int c;
    public final io.reactivex.rxjava3.functions.q e;

    public /* synthetic */ e(int i, io.reactivex.rxjava3.functions.q qVar) {
        this.c = i;
        this.e = qVar;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        Object obj;
        EmptyDisposable emptyDisposable = EmptyDisposable.c;
        int i = this.c;
        io.reactivex.rxjava3.functions.q qVar = this.e;
        switch (i) {
            case 0:
                try {
                    Object obj2 = qVar.get();
                    Objects.requireNonNull(obj2, "The singleSupplier returned a null SingleSource");
                    ((SingleSource) obj2).subscribe(singleObserver);
                    return;
                } catch (Throwable th) {
                    he1.R(th);
                    singleObserver.onSubscribe(emptyDisposable);
                    singleObserver.onError(th);
                    return;
                }
            case 1:
                try {
                    obj = qVar.get();
                } catch (Throwable th2) {
                    th = th2;
                    he1.R(th);
                }
                if (obj == null) {
                    throw io.reactivex.rxjava3.internal.util.c.b("Supplier returned a null Throwable.");
                }
                Throwable th3 = io.reactivex.rxjava3.internal.util.c.a;
                th = (Throwable) obj;
                singleObserver.onSubscribe(emptyDisposable);
                singleObserver.onError(th);
                return;
            default:
                Disposable disposableE = Disposable.e(io.reactivex.rxjava3.internal.functions.k.b);
                singleObserver.onSubscribe(disposableE);
                if (disposableE.isDisposed()) {
                    return;
                }
                try {
                    Object obj3 = qVar.get();
                    Objects.requireNonNull(obj3, "The supplier returned a null value");
                    if (disposableE.isDisposed()) {
                        return;
                    }
                    singleObserver.onSuccess(obj3);
                    return;
                } catch (Throwable th4) {
                    he1.R(th4);
                    if (disposableE.isDisposed()) {
                        RxJavaPlugins.b(th4);
                        return;
                    } else {
                        singleObserver.onError(th4);
                        return;
                    }
                }
        }
    }
}
