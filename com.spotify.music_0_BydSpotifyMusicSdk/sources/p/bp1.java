package p;

import io.reactivex.rxjava3.core.Observable;

/* JADX INFO: loaded from: classes.dex */
public final class bp1 implements io.reactivex.rxjava3.functions.o {
    public final int c = 3;
    public final long e = 100;

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        return ((Observable) obj).zipWith(Observable.range(1, this.c + 1), new lj(2)).flatMap(new dd1(12, this));
    }
}
