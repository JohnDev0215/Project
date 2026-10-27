package io.reactivex.rxjava3.internal.jdk8;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.internal.disposables.EmptyDisposable;
import java.util.stream.Collector;
import p.he1;

/* JADX INFO: loaded from: classes.dex */
public final class b extends Observable {
    public final /* synthetic */ int c;
    public final Object e;
    public final Object k;

    public /* synthetic */ b(Object obj, int i, Object obj2) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        int i = this.c;
        Object obj = this.k;
        Object obj2 = this.e;
        switch (i) {
            case 0:
                try {
                    ((Observable) obj2).subscribe(new ObservableCollectWithCollector$CollectorObserver(observer, ((Collector) obj).supplier().get(), ((Collector) obj).accumulator(), ((Collector) obj).finisher()));
                } catch (Throwable th) {
                    he1.R(th);
                    observer.onSubscribe(EmptyDisposable.c);
                    observer.onError(th);
                    return;
                }
                break;
            default:
                ((Single) obj2).subscribe(new MaybeFlattenStreamAsObservable$FlattenStreamMultiObserver(observer, (io.reactivex.rxjava3.functions.o) obj));
                break;
        }
    }
}
