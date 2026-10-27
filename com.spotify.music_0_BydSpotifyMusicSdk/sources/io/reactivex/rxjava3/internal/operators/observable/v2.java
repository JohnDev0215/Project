package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;

/* JADX INFO: loaded from: classes.dex */
public final class v2 extends Single {
    public final /* synthetic */ int c;
    public final Object e;
    public final Object k;

    public /* synthetic */ v2(Object obj, int i, Object obj2) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        int i = this.c;
        Object obj = this.k;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                ((ObservableSource) obj2).subscribe(new u2(singleObserver, obj));
                break;
            case 1:
                ((ObservableSource) obj2).subscribe(new h4(singleObserver, obj));
                break;
            default:
                ((MaybeSource) obj2).subscribe(new io.reactivex.rxjava3.internal.operators.maybe.i(singleObserver, obj));
                break;
        }
    }
}
