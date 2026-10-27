package io.reactivex.rxjava3.internal.operators.completable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.functions.o;

/* JADX INFO: loaded from: classes.dex */
public final class b extends Completable {
    public final /* synthetic */ int c;
    public final CompletableSource e;
    public final Object k;

    public /* synthetic */ b(CompletableSource completableSource, Object obj, int i) {
        this.c = i;
        this.e = completableSource;
        this.k = obj;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public final void d(CompletableObserver completableObserver) {
        int i = this.c;
        Object obj = this.k;
        CompletableSource completableSource = this.e;
        switch (i) {
            case 0:
                completableSource.subscribe(new CompletableAndThenCompletable$SourceObserver(completableObserver, (CompletableSource) obj));
                break;
            case 1:
                completableSource.subscribe(new CompletableDoFinally$DoFinallyObserver(completableObserver, (io.reactivex.rxjava3.functions.a) obj));
                break;
            case 2:
                CompletableResumeNext$ResumeNextObserver completableResumeNext$ResumeNextObserver = new CompletableResumeNext$ResumeNextObserver(completableObserver, (o) obj);
                completableObserver.onSubscribe(completableResumeNext$ResumeNextObserver);
                completableSource.subscribe(completableResumeNext$ResumeNextObserver);
                break;
            default:
                completableSource.subscribe(new a(this, completableObserver, 1));
                break;
        }
    }
}
