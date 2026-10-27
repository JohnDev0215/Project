package rx.internal.util;

import rx.Scheduler;
import rx.Single;
import rx.SingleSubscriber;
import rx.functions.Action0;
import rx.internal.schedulers.EventLoopsScheduler;

/* JADX INFO: loaded from: classes8.dex */
public final class ScalarSynchronousSingle<T> extends Single<T> {
    final T value;

    public Single<T> scalarScheduleOn(Scheduler scheduler) {
        if (scheduler instanceof EventLoopsScheduler) {
            return Single.create(new DirectScheduledEmission((EventLoopsScheduler) scheduler, this.value));
        }
        return Single.create(new NormalScheduledEmission(scheduler, this.value));
    }

    static final class DirectScheduledEmission<T> implements Single.OnSubscribe<T> {
        private final EventLoopsScheduler es;
        private final T value;

        DirectScheduledEmission(EventLoopsScheduler eventLoopsScheduler, T t) {
            this.es = eventLoopsScheduler;
            this.value = t;
        }

        @Override // rx.functions.Action1
        public void call(SingleSubscriber<? super T> singleSubscriber) {
            singleSubscriber.add(this.es.scheduleDirect(new ScalarSynchronousSingleAction(singleSubscriber, this.value)));
        }
    }

    static final class NormalScheduledEmission<T> implements Single.OnSubscribe<T> {
        private final Scheduler scheduler;
        private final T value;

        NormalScheduledEmission(Scheduler scheduler, T t) {
            this.scheduler = scheduler;
            this.value = t;
        }

        @Override // rx.functions.Action1
        public void call(SingleSubscriber<? super T> singleSubscriber) {
            Scheduler.Worker workerCreateWorker = this.scheduler.createWorker();
            singleSubscriber.add(workerCreateWorker);
            workerCreateWorker.schedule(new ScalarSynchronousSingleAction(singleSubscriber, this.value));
        }
    }

    static final class ScalarSynchronousSingleAction<T> implements Action0 {
        private final SingleSubscriber<? super T> subscriber;
        private final T value;

        ScalarSynchronousSingleAction(SingleSubscriber<? super T> singleSubscriber, T t) {
            this.subscriber = singleSubscriber;
            this.value = t;
        }

        @Override // rx.functions.Action0
        public void call() {
            try {
                this.subscriber.onSuccess(this.value);
            } catch (Throwable th) {
                this.subscriber.onError(th);
            }
        }
    }
}
