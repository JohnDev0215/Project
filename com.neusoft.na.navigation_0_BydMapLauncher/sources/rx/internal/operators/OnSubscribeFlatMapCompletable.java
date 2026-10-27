package rx.internal.operators;

import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.functions.Func1;
import rx.internal.util.ExceptionsUtils;
import rx.plugins.RxJavaHooks;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: loaded from: classes4.dex */
public final class OnSubscribeFlatMapCompletable<T> implements Observable.OnSubscribe<T> {
    final boolean delayErrors;
    final Func1<? super T, ? extends Completable> mapper;
    final int maxConcurrency;
    final Observable<T> source;

    public OnSubscribeFlatMapCompletable(Observable<T> observable, Func1<? super T, ? extends Completable> func1, boolean z, int i) {
        if (func1 == null) {
            throw new NullPointerException("mapper is null");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("maxConcurrency > 0 required but it was " + i);
        }
        this.source = observable;
        this.mapper = func1;
        this.delayErrors = z;
        this.maxConcurrency = i;
    }

    @Override // rx.functions.Action1
    public void call(Subscriber<? super T> subscriber) {
        FlatMapCompletableSubscriber flatMapCompletableSubscriber = new FlatMapCompletableSubscriber(subscriber, this.mapper, this.delayErrors, this.maxConcurrency);
        subscriber.add(flatMapCompletableSubscriber);
        subscriber.add(flatMapCompletableSubscriber.set);
        this.source.unsafeSubscribe(flatMapCompletableSubscriber);
    }

    static final class FlatMapCompletableSubscriber<T> extends Subscriber<T> {
        final Subscriber<? super T> actual;
        final boolean delayErrors;
        final Func1<? super T, ? extends Completable> mapper;
        final int maxConcurrency;
        final AtomicInteger wip = new AtomicInteger(1);
        final AtomicReference<Throwable> errors = new AtomicReference<>();
        final CompositeSubscription set = new CompositeSubscription();

        FlatMapCompletableSubscriber(Subscriber<? super T> subscriber, Func1<? super T, ? extends Completable> func1, boolean z, int i) {
            this.actual = subscriber;
            this.mapper = func1;
            this.delayErrors = z;
            this.maxConcurrency = i;
            request(i != Integer.MAX_VALUE ? i : LongCompanionObject.MAX_VALUE);
        }

        @Override // rx.Observer
        public void onNext(T t) {
            try {
                Completable completableCall = this.mapper.call(t);
                if (completableCall == null) {
                    throw new NullPointerException("The mapper returned a null Completable");
                }
                InnerSubscriber innerSubscriber = new InnerSubscriber();
                this.set.add(innerSubscriber);
                this.wip.getAndIncrement();
                completableCall.unsafeSubscribe(innerSubscriber);
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                unsubscribe();
                onError(th);
            }
        }

        @Override // rx.Observer
        public void onError(Throwable th) {
            if (this.delayErrors) {
                ExceptionsUtils.addThrowable(this.errors, th);
                onCompleted();
                return;
            }
            this.set.unsubscribe();
            if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.errors, null, th)) {
                this.actual.onError(ExceptionsUtils.terminate(this.errors));
            } else {
                RxJavaHooks.onError(th);
            }
        }

        @Override // rx.Observer
        public void onCompleted() {
            done();
        }

        boolean done() {
            if (this.wip.decrementAndGet() != 0) {
                return false;
            }
            Throwable thTerminate = ExceptionsUtils.terminate(this.errors);
            if (thTerminate != null) {
                this.actual.onError(thTerminate);
                return true;
            }
            this.actual.onCompleted();
            return true;
        }

        public void innerError(FlatMapCompletableSubscriber<T>.InnerSubscriber innerSubscriber, Throwable th) {
            this.set.remove(innerSubscriber);
            if (this.delayErrors) {
                ExceptionsUtils.addThrowable(this.errors, th);
                if (done() || this.maxConcurrency == Integer.MAX_VALUE) {
                    return;
                }
                request(1L);
                return;
            }
            this.set.unsubscribe();
            unsubscribe();
            if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(this.errors, null, th)) {
                this.actual.onError(ExceptionsUtils.terminate(this.errors));
            } else {
                RxJavaHooks.onError(th);
            }
        }

        public void innerComplete(FlatMapCompletableSubscriber<T>.InnerSubscriber innerSubscriber) {
            this.set.remove(innerSubscriber);
            if (done() || this.maxConcurrency == Integer.MAX_VALUE) {
                return;
            }
            request(1L);
        }

        final class InnerSubscriber extends AtomicReference<Subscription> implements CompletableSubscriber, Subscription {
            private static final long serialVersionUID = -8588259593722659900L;

            InnerSubscriber() {
            }

            @Override // rx.Subscription
            public void unsubscribe() {
                Subscription andSet = getAndSet(this);
                if (andSet == null || andSet == this) {
                    return;
                }
                andSet.unsubscribe();
            }

            @Override // rx.Subscription
            public boolean isUnsubscribed() {
                return get() == this;
            }

            @Override // rx.CompletableSubscriber
            public void onCompleted() {
                FlatMapCompletableSubscriber.this.innerComplete(this);
            }

            @Override // rx.CompletableSubscriber
            public void onError(Throwable th) {
                FlatMapCompletableSubscriber.this.innerError(this, th);
            }

            @Override // rx.CompletableSubscriber
            public void onSubscribe(Subscription subscription) {
                if (compareAndSet(null, subscription)) {
                    return;
                }
                subscription.unsubscribe();
                if (get() != this) {
                    RxJavaHooks.onError(new IllegalStateException("Subscription already set!"));
                }
            }
        }
    }
}
