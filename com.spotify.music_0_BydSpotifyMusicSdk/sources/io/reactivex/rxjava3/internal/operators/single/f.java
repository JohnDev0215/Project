package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.disposables.SequentialDisposable;
import p.z13;

/* JADX INFO: loaded from: classes.dex */
public final class f implements SingleObserver {
    public final SequentialDisposable c;
    public final SingleObserver e;
    public final /* synthetic */ g k;

    public f(g gVar, SequentialDisposable sequentialDisposable, SingleObserver singleObserver) {
        this.k = gVar;
        this.c = sequentialDisposable;
        this.e = singleObserver;
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onError(Throwable th) {
        g gVar = this.k;
        Disposable disposableD = gVar.l.d(new z13(this, 3, th), gVar.m ? gVar.e : 0L, gVar.k);
        SequentialDisposable sequentialDisposable = this.c;
        sequentialDisposable.getClass();
        DisposableHelper.c(sequentialDisposable, disposableD);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSubscribe(Disposable disposable) {
        SequentialDisposable sequentialDisposable = this.c;
        sequentialDisposable.getClass();
        DisposableHelper.c(sequentialDisposable, disposable);
    }

    @Override // io.reactivex.rxjava3.core.SingleObserver
    public final void onSuccess(Object obj) {
        g gVar = this.k;
        Disposable disposableD = gVar.l.d(new z13(this, 4, obj), gVar.e, gVar.k);
        SequentialDisposable sequentialDisposable = this.c;
        sequentialDisposable.getClass();
        DisposableHelper.c(sequentialDisposable, disposableD);
    }
}
