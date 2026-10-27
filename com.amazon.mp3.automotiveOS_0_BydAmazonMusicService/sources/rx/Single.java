package rx;

import java.util.concurrent.Callable;
import rx.exceptions.Exceptions;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.internal.operators.SingleDoOnEvent;
import rx.internal.operators.SingleFromCallable;
import rx.internal.operators.SingleObserveOn;
import rx.internal.util.ScalarSynchronousSingle;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes6.dex */
public class Single<T> {
    final OnSubscribe<T> onSubscribe;

    public interface OnSubscribe<T> extends Action1<SingleSubscriber<? super T>> {
    }

    protected Single(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = RxJavaHooks.onCreate(onSubscribe);
    }

    public static <T> Single<T> create(OnSubscribe<T> onSubscribe) {
        return new Single<>(onSubscribe);
    }

    public static <T> Single<T> fromCallable(Callable<? extends T> callable) {
        return create(new SingleFromCallable(callable));
    }

    public final Single<T> observeOn(Scheduler scheduler) {
        if (this instanceof ScalarSynchronousSingle) {
            return ((ScalarSynchronousSingle) this).scalarScheduleOn(scheduler);
        }
        if (scheduler == null) {
            throw new NullPointerException("scheduler is null");
        }
        return create(new SingleObserveOn(this.onSubscribe, scheduler));
    }

    public final Subscription subscribe(Action1<? super T> action1) {
        return subscribe(action1, Actions.errorNotImplemented());
    }

    public final Subscription subscribe(final Action1<? super T> action1, final Action1<Throwable> action12) {
        if (action1 == null) {
            throw new IllegalArgumentException("onSuccess can not be null");
        }
        if (action12 == null) {
            throw new IllegalArgumentException("onError can not be null");
        }
        return subscribe(new SingleSubscriber<T>() { // from class: rx.Single.11
            @Override // rx.SingleSubscriber
            public final void onError(Throwable th) {
                try {
                    action12.call(th);
                } finally {
                    unsubscribe();
                }
            }

            @Override // rx.SingleSubscriber
            public final void onSuccess(T t) {
                try {
                    action1.call(t);
                } finally {
                    unsubscribe();
                }
            }
        });
    }

    public final Subscription subscribe(SingleSubscriber<? super T> singleSubscriber) {
        if (singleSubscriber == null) {
            throw new IllegalArgumentException("te is null");
        }
        try {
            RxJavaHooks.onSingleStart(this, this.onSubscribe).call(singleSubscriber);
            return RxJavaHooks.onSingleReturn(singleSubscriber);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            try {
                singleSubscriber.onError(RxJavaHooks.onSingleError(th));
                return Subscriptions.empty();
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                RuntimeException runtimeException = new RuntimeException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th2);
                RxJavaHooks.onSingleError(runtimeException);
                throw runtimeException;
            }
        }
    }

    public final Single<T> doOnError(final Action1<Throwable> action1) {
        if (action1 == null) {
            throw new IllegalArgumentException("onError is null");
        }
        return create(new SingleDoOnEvent(this, Actions.empty(), new Action1<Throwable>() { // from class: rx.Single.15
            @Override // rx.functions.Action1
            public void call(Throwable th) {
                action1.call(th);
            }
        }));
    }
}
