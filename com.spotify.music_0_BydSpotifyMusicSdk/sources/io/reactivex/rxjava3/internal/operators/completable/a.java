package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.functions.p;
import io.reactivex.rxjava3.functions.q;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class a implements CompletableObserver {
    public final /* synthetic */ int c;
    public final Object e;
    public final Object k;

    public /* synthetic */ a(Object obj, CompletableObserver completableObserver, int i) {
        this.c = i;
        this.k = obj;
        this.e = completableObserver;
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onComplete() {
        Object obj;
        int i = this.c;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                ((CompletableObserver) obj2).onComplete();
                break;
            case 1:
                ((CompletableObserver) obj2).onComplete();
                break;
            default:
                n nVar = (n) obj2;
                q qVar = (q) nVar.l;
                Object obj3 = this.k;
                if (qVar != null) {
                    try {
                        obj = ((q) ((n) obj2).l).get();
                    } catch (Throwable th) {
                        he1.R(th);
                        ((SingleObserver) obj3).onError(th);
                        return;
                    }
                } else {
                    obj = nVar.e;
                }
                if (obj != null) {
                    ((SingleObserver) obj3).onSuccess(obj);
                } else {
                    ((SingleObserver) obj3).onError(new NullPointerException("The value supplied is null"));
                }
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onError(Throwable th) {
        int i = this.c;
        Object obj = this.e;
        Object obj2 = this.k;
        switch (i) {
            case 0:
                ((CompletableObserver) obj).onError(th);
                break;
            case 1:
                try {
                    if (!((p) ((b) obj2).k).test(th)) {
                        ((CompletableObserver) obj).onError(th);
                    } else {
                        ((CompletableObserver) obj).onComplete();
                    }
                } catch (Throwable th2) {
                    he1.R(th2);
                    ((CompletableObserver) obj).onError(new CompositeException(th, th2));
                    return;
                }
                break;
            default:
                ((SingleObserver) obj2).onError(th);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.CompletableObserver
    public final void onSubscribe(Disposable disposable) {
        int i = this.c;
        Object obj = this.k;
        switch (i) {
            case 0:
                DisposableHelper.c((AtomicReference) obj, disposable);
                break;
            case 1:
                ((CompletableObserver) this.e).onSubscribe(disposable);
                break;
            default:
                ((SingleObserver) obj).onSubscribe(disposable);
                break;
        }
    }

    public a(n nVar, SingleObserver singleObserver) {
        this.c = 2;
        this.e = nVar;
        this.k = singleObserver;
    }
}
