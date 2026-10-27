package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import p.yp;
import p.zp;

/* JADX INFO: loaded from: classes.dex */
public final class g implements SingleObserver {
    public final /* synthetic */ int c;
    public final Object e;

    public /* synthetic */ g(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                ((CompletableObserver) obj).onError(th);
                break;
            default:
                ((zp) ((yp) obj)).k(kotlin.a.b(th));
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                ((CompletableObserver) obj).onSubscribe(disposable);
                break;
            default:
                kotlinx.coroutines.rx3.a.d((yp) obj, disposable);
                break;
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        int i = this.c;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                ((CompletableObserver) obj2).onComplete();
                break;
            default:
                ((zp) ((yp) obj2)).k(obj);
                break;
        }
    }
}
