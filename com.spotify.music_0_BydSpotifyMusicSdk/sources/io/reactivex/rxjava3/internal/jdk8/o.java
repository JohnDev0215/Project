package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.p;
import io.reactivex.rxjava3.internal.operators.observable.k3;

/* JADX INFO: loaded from: classes.dex */
public final class o extends Maybe {
    public final /* synthetic */ int c;
    public final Object e;
    public final Object k;

    public /* synthetic */ o(Object obj, int i, Object obj2) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }

    @Override // io.reactivex.rxjava3.core.Maybe
    public final void c(MaybeObserver maybeObserver) {
        int i = this.c;
        Object obj = this.k;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                ((Single) obj2).subscribe(new n(maybeObserver, (io.reactivex.rxjava3.functions.o) obj));
                break;
            case 1:
                ((SingleSource) obj2).subscribe(new io.reactivex.rxjava3.internal.operators.maybe.b(maybeObserver, (p) obj));
                break;
            default:
                ((ObservableSource) obj2).subscribe(new k3(maybeObserver, (io.reactivex.rxjava3.functions.c) obj));
                break;
        }
    }
}
