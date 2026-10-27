package rx.internal.operators;

import rx.Observable;
import rx.Subscriber;

/* JADX INFO: loaded from: classes8.dex */
public final class OperatorAsObservable<T> implements Observable.Operator<T, T> {
    @Override // rx.functions.Func1
    public Subscriber<? super T> call(Subscriber<? super T> subscriber) {
        return subscriber;
    }

    static final class Holder {
        static final OperatorAsObservable<Object> INSTANCE = new OperatorAsObservable<>();

        Holder() {
        }
    }

    public static <T> OperatorAsObservable<T> instance() {
        return (OperatorAsObservable<T>) Holder.INSTANCE;
    }

    OperatorAsObservable() {
    }
}
