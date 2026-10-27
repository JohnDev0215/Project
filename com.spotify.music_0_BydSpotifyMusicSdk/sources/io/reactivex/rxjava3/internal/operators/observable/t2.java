package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.SingleSource;

/* JADX INFO: loaded from: classes.dex */
public final class t2 extends Maybe {
    public final /* synthetic */ int c;
    public final Object e;

    public /* synthetic */ t2(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public final void c(MaybeObserver maybeObserver) {
        int i = this.c;
        Object obj = this.e;
        switch (i) {
            case 0:
                ((ObservableSource) obj).subscribe(new s2(maybeObserver));
                break;
            case 1:
                ((ObservableSource) obj).subscribe(new g4(maybeObserver));
                break;
            default:
                ((SingleSource) obj).subscribe(new io.reactivex.rxjava3.internal.operators.maybe.c(maybeObserver));
                break;
        }
    }
}
