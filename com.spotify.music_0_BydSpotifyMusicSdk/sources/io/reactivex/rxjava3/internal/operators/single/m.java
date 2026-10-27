package io.reactivex.rxjava3.internal.operators.single;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.core.SingleSource;

/* JADX INFO: loaded from: classes.dex */
public final class m extends Single {
    public final /* synthetic */ int c;
    public final SingleSource e;

    public /* synthetic */ m(SingleSource singleSource, int i) {
        this.c = i;
        this.e = singleSource;
    }

    @Override // io.reactivex.rxjava3.core.Single
    public final void subscribeActual(SingleObserver singleObserver) {
        int i = this.c;
        SingleSource singleSource = this.e;
        switch (i) {
            case 0:
                l lVar = new l();
                lVar.c = singleObserver;
                singleSource.subscribe(lVar);
                break;
            case 1:
                singleSource.subscribe(singleObserver);
                break;
            default:
                singleSource.subscribe(new e0(singleObserver));
                break;
        }
    }
}
