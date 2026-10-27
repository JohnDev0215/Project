package p;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* JADX INFO: loaded from: classes.dex */
public final class cp1 implements ObservableTransformer {
    public final int a = 5;
    public final bp1 b = new bp1();

    @Override // io.reactivex.rxjava3.core.ObservableTransformer
    public final ObservableSource apply(Observable observable) {
        return observable.timeout(this.a, TimeUnit.SECONDS, Observable.error(new TimeoutException())).retryWhen(this.b);
    }
}
