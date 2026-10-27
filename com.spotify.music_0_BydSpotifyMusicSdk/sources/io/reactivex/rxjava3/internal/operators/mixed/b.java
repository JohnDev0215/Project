package io.reactivex.rxjava3.internal.operators.mixed;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import p.vr2;

/* JADX INFO: loaded from: classes.dex */
public final class b extends Completable {
    public final /* synthetic */ int c;
    public final o e;
    public final ErrorMode k;
    public final int l;
    public final Object m;

    public /* synthetic */ b(Object obj, o oVar, ErrorMode errorMode, int i, int i2) {
        this.c = i2;
        this.m = obj;
        this.e = oVar;
        this.k = errorMode;
        this.l = i;
    }

    @Override // io.reactivex.rxjava3.core.Completable
    public final void d(CompletableObserver completableObserver) {
        int i = this.c;
        int i2 = this.l;
        ErrorMode errorMode = this.k;
        o oVar = this.e;
        Object obj = this.m;
        switch (i) {
            case 0:
                ((Flowable) obj).subscribe((FlowableSubscriber) new FlowableConcatMapCompletable$ConcatMapCompletableObserver(completableObserver, oVar, errorMode, i2));
                break;
            default:
                Observable observable = (Observable) obj;
                if (!vr2.u(observable, oVar, completableObserver)) {
                    observable.subscribe(new ObservableConcatMapCompletable$ConcatMapCompletableObserver(completableObserver, oVar, errorMode, i2));
                }
                break;
        }
    }
}
