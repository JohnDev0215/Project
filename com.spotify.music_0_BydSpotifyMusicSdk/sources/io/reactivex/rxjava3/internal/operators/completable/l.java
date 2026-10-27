package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import p.ik;

/* JADX INFO: loaded from: classes.dex */
public final class l extends Completable {
    public final CompletableSource c;
    public final io.reactivex.rxjava3.functions.g e;
    public final io.reactivex.rxjava3.functions.g k;
    public final io.reactivex.rxjava3.functions.a l;
    public final io.reactivex.rxjava3.functions.a m;
    public final io.reactivex.rxjava3.functions.a n;
    public final io.reactivex.rxjava3.functions.a o;

    public l(CompletableSource completableSource, io.reactivex.rxjava3.functions.g gVar, io.reactivex.rxjava3.functions.a aVar) {
        ik ikVar = io.reactivex.rxjava3.internal.functions.k.d;
        io.reactivex.rxjava3.internal.functions.b bVar = io.reactivex.rxjava3.internal.functions.k.c;
        this.c = completableSource;
        this.e = ikVar;
        this.k = gVar;
        this.l = aVar;
        this.m = bVar;
        this.n = bVar;
        this.o = bVar;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public final void d(CompletableObserver completableObserver) {
        this.c.subscribe(new k(this, completableObserver));
    }
}
