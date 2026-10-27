package rx.internal.operators;

import androidx.core.location.LocationRequestCompat;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

/* JADX INFO: loaded from: classes.dex */
public final class OperatorThrottleFirst<T> implements Observable.Operator<T, T> {
    final Scheduler scheduler;
    final long timeInMilliseconds;

    public OperatorThrottleFirst(long j, TimeUnit timeUnit, Scheduler scheduler) {
        this.timeInMilliseconds = timeUnit.toMillis(j);
        this.scheduler = scheduler;
    }

    @Override // rx.functions.Func1
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        return new Subscriber<T>(subscriber) { // from class: rx.internal.operators.OperatorThrottleFirst.1
            private long lastOnNext = -1;

            @Override // rx.Subscriber, rx.observers.AssertableSubscriber
            public void onStart() {
                request(LocationRequestCompat.PASSIVE_INTERVAL);
            }

            @Override // rx.Observer
            public void onNext(T t) {
                long jNow = OperatorThrottleFirst.this.scheduler.now();
                long j = this.lastOnNext;
                if (j == -1 || jNow < j || jNow - j >= OperatorThrottleFirst.this.timeInMilliseconds) {
                    this.lastOnNext = jNow;
                    subscriber.onNext(t);
                }
            }

            @Override // rx.Observer
            public void onCompleted() {
                subscriber.onCompleted();
            }

            @Override // rx.Observer
            public void onError(Throwable th) {
                subscriber.onError(th);
            }
        };
    }
}
