package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.SingleSource;

/* JADX INFO: loaded from: classes.dex */
public final class w extends Completable {
    public final SingleSource c;
    public final io.reactivex.rxjava3.functions.o e;

    public w(SingleSource singleSource, io.reactivex.rxjava3.functions.o oVar) {
        this.c = singleSource;
        this.e = oVar;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public final void d(CompletableObserver completableObserver) {
        SingleFlatMapCompletable$FlatMapCompletableObserver singleFlatMapCompletable$FlatMapCompletableObserver = new SingleFlatMapCompletable$FlatMapCompletableObserver(completableObserver, this.e);
        completableObserver.onSubscribe(singleFlatMapCompletable$FlatMapCompletableObserver);
        this.c.subscribe(singleFlatMapCompletable$FlatMapCompletableObserver);
    }
}
