package rx.observers;

import rx.Observer;
import rx.exceptions.OnErrorNotImplementedException;

/* JADX INFO: loaded from: classes8.dex */
public final class Observers {
    private static final Observer<Object> EMPTY = new Observer<Object>() { // from class: rx.observers.Observers.1
        @Override // rx.Observer
        public final void onCompleted() {
        }

        @Override // rx.Observer
        public final void onNext(Object obj) {
        }

        @Override // rx.Observer
        public final void onError(Throwable th) {
            throw new OnErrorNotImplementedException(th);
        }
    };

    private Observers() {
        throw new IllegalStateException("No instances!");
    }

    public static <T> Observer<T> empty() {
        return (Observer<T>) EMPTY;
    }
}
