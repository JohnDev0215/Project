package rx.observables;

import java.util.concurrent.Future;
import rx.Observable;
import rx.internal.operators.BlockingOperatorToFuture;

/* JADX INFO: loaded from: classes8.dex */
public final class BlockingObservable<T> {
    static final Object ON_START = new Object();
    static final Object SET_PRODUCER = new Object();
    static final Object UNSUBSCRIBE = new Object();
    private final Observable<? extends T> o;

    private BlockingObservable(Observable<? extends T> observable) {
        this.o = observable;
    }

    public static <T> BlockingObservable<T> from(Observable<? extends T> observable) {
        return new BlockingObservable<>(observable);
    }

    public Future<T> toFuture() {
        return BlockingOperatorToFuture.toFuture(this.o);
    }
}
