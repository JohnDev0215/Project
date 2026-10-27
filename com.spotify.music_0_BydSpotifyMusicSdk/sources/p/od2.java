package p;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleTransformer;

/* JADX INFO: loaded from: classes.dex */
public final class od2 implements SingleTransformer {
    public final cp1 c = new cp1();

    @Override // io.reactivex.rxjava3.core.SingleTransformer
    public final Single e(Single single) {
        return single.toObservable().compose(this.c).singleOrError();
    }
}
