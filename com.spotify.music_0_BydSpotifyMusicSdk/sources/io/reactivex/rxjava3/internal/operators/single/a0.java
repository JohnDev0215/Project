package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;

/* JADX INFO: loaded from: classes.dex */
public final class a0 extends Maybe {
    public final /* synthetic */ int c;
    public final Object e;
    public final SingleSource k;

    public /* synthetic */ a0(SingleSource singleSource, Object obj, int i) {
        this.c = i;
        this.e = obj;
        this.k = singleSource;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public final void c(MaybeObserver maybeObserver) {
        int i = this.c;
        Object obj = this.e;
        SingleSource singleSource = this.k;
        switch (i) {
            case 0:
                singleSource.subscribe(new SingleFlatMapMaybe$FlatMapSingleObserver(maybeObserver, (io.reactivex.rxjava3.functions.o) obj));
                break;
            case 1:
                ((Single) singleSource).subscribe(new k(maybeObserver, (io.reactivex.rxjava3.functions.o) obj));
                break;
            default:
                ((Single) singleSource).subscribe(new io.reactivex.rxjava3.internal.operators.maybe.f(maybeObserver, (io.reactivex.rxjava3.functions.p) obj));
                break;
        }
    }
}
