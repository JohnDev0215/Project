package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.functions.Functions;
import io.reactivex.rxjava3.internal.functions.ObjectHelper;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableConcatMap;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableDelay;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableDoOnEach;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableEmpty;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableError;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableFlatMap;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableFromIterable;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableFromPublisher;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableJust;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableMap;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableObserveOn;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableRetryWhen;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableScalarXMap;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableTakeUntilPredicate;
import io.reactivex.rxjava3.internal.operators.flowable.FlowableToListSingle;
import io.reactivex.rxjava3.internal.operators.mixed.FlowableConcatMapCompletable;
import io.reactivex.rxjava3.internal.subscribers.StrictSubscriber;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import io.reactivex.rxjava3.operators.ScalarSupplier;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: classes7.dex */
public abstract class Flowable<T> implements Publisher<T> {
    static final int BUFFER_SIZE = Math.max(1, Integer.getInteger("rx3.buffer-size", 128).intValue());

    protected abstract void subscribeActual(Subscriber<? super T> subscriber);

    public static int bufferSize() {
        return BUFFER_SIZE;
    }

    public static <T> Flowable<T> empty() {
        return RxJavaPlugins.onAssembly(FlowableEmpty.INSTANCE);
    }

    public static <T> Flowable<T> error(Supplier<? extends Throwable> supplier) {
        Objects.requireNonNull(supplier, "supplier is null");
        return RxJavaPlugins.onAssembly(new FlowableError(supplier));
    }

    public static <T> Flowable<T> error(Throwable throwable) {
        Objects.requireNonNull(throwable, "throwable is null");
        return error((Supplier<? extends Throwable>) Functions.justSupplier(throwable));
    }

    public static <T> Flowable<T> fromIterable(Iterable<? extends T> source) {
        Objects.requireNonNull(source, "source is null");
        return RxJavaPlugins.onAssembly(new FlowableFromIterable(source));
    }

    public static <T> Flowable<T> fromPublisher(Publisher<? extends T> publisher) {
        if (publisher instanceof Flowable) {
            return RxJavaPlugins.onAssembly((Flowable) publisher);
        }
        Objects.requireNonNull(publisher, "publisher is null");
        return RxJavaPlugins.onAssembly(new FlowableFromPublisher(publisher));
    }

    public static <T> Flowable<T> just(T item) {
        Objects.requireNonNull(item, "item is null");
        return RxJavaPlugins.onAssembly(new FlowableJust(item));
    }

    public final <R> Flowable<R> concatMap(Function<? super T, ? extends Publisher<? extends R>> mapper) {
        return concatMap(mapper, 2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Flowable<R> concatMap(Function<? super T, ? extends Publisher<? extends R>> mapper, int prefetch) {
        Objects.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        if (this instanceof ScalarSupplier) {
            Object obj = ((ScalarSupplier) this).get();
            if (obj == null) {
                return empty();
            }
            return FlowableScalarXMap.scalarXMap(obj, mapper);
        }
        return RxJavaPlugins.onAssembly(new FlowableConcatMap(this, mapper, prefetch, ErrorMode.IMMEDIATE));
    }

    public final Completable concatMapCompletable(Function<? super T, ? extends CompletableSource> mapper) {
        return concatMapCompletable(mapper, 2);
    }

    public final Completable concatMapCompletable(Function<? super T, ? extends CompletableSource> mapper, int prefetch) {
        Objects.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(prefetch, "prefetch");
        return RxJavaPlugins.onAssembly(new FlowableConcatMapCompletable(this, mapper, ErrorMode.IMMEDIATE, prefetch));
    }

    public final Flowable<T> delay(long time, TimeUnit unit, Scheduler scheduler) {
        return delay(time, unit, scheduler, false);
    }

    public final Flowable<T> delay(long time, TimeUnit unit, Scheduler scheduler, boolean delayError) {
        Objects.requireNonNull(unit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return RxJavaPlugins.onAssembly(new FlowableDelay(this, Math.max(0L, time), unit, scheduler, delayError));
    }

    private Flowable<T> doOnEach(Consumer<? super T> onNext, Consumer<? super Throwable> onError, Action onComplete, Action onAfterTerminate) {
        Objects.requireNonNull(onNext, "onNext is null");
        Objects.requireNonNull(onError, "onError is null");
        Objects.requireNonNull(onComplete, "onComplete is null");
        Objects.requireNonNull(onAfterTerminate, "onAfterTerminate is null");
        return RxJavaPlugins.onAssembly(new FlowableDoOnEach(this, onNext, onError, onComplete, onAfterTerminate));
    }

    public final Flowable<T> doOnNext(Consumer<? super T> onNext) {
        Consumer<? super Throwable> consumerEmptyConsumer = Functions.emptyConsumer();
        Action action = Functions.EMPTY_ACTION;
        return doOnEach(onNext, consumerEmptyConsumer, action, action);
    }

    public final <R> Flowable<R> flatMap(Function<? super T, ? extends Publisher<? extends R>> mapper) {
        return flatMap(mapper, false, bufferSize(), bufferSize());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Flowable<R> flatMap(Function<? super T, ? extends Publisher<? extends R>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
        Objects.requireNonNull(mapper, "mapper is null");
        ObjectHelper.verifyPositive(maxConcurrency, "maxConcurrency");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        if (this instanceof ScalarSupplier) {
            Object obj = ((ScalarSupplier) this).get();
            if (obj == null) {
                return empty();
            }
            return FlowableScalarXMap.scalarXMap(obj, mapper);
        }
        return RxJavaPlugins.onAssembly(new FlowableFlatMap(this, mapper, delayErrors, maxConcurrency, bufferSize));
    }

    public final <R> Flowable<R> map(Function<? super T, ? extends R> mapper) {
        Objects.requireNonNull(mapper, "mapper is null");
        return RxJavaPlugins.onAssembly(new FlowableMap(this, mapper));
    }

    public final Flowable<T> observeOn(Scheduler scheduler) {
        return observeOn(scheduler, false, bufferSize());
    }

    public final Flowable<T> observeOn(Scheduler scheduler, boolean delayError, int bufferSize) {
        Objects.requireNonNull(scheduler, "scheduler is null");
        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        return RxJavaPlugins.onAssembly(new FlowableObserveOn(this, scheduler, delayError, bufferSize));
    }

    public final Flowable<T> retryWhen(Function<? super Flowable<Throwable>, ? extends Publisher<?>> handler) {
        Objects.requireNonNull(handler, "handler is null");
        return RxJavaPlugins.onAssembly(new FlowableRetryWhen(this, handler));
    }

    @Override // org.reactivestreams.Publisher
    public final void subscribe(Subscriber<? super T> subscriber) {
        if (subscriber instanceof FlowableSubscriber) {
            subscribe((FlowableSubscriber) subscriber);
        } else {
            Objects.requireNonNull(subscriber, "subscriber is null");
            subscribe((FlowableSubscriber) new StrictSubscriber(subscriber));
        }
    }

    public final void subscribe(FlowableSubscriber<? super T> subscriber) {
        Objects.requireNonNull(subscriber, "subscriber is null");
        try {
            Subscriber<? super T> subscriberOnSubscribe = RxJavaPlugins.onSubscribe(this, subscriber);
            Objects.requireNonNull(subscriberOnSubscribe, "The RxJavaPlugins.onSubscribe hook returned a null FlowableSubscriber. Please check the handler provided to RxJavaPlugins.setOnFlowableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            subscribeActual(subscriberOnSubscribe);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            RxJavaPlugins.onError(th);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th);
            throw nullPointerException;
        }
    }

    public final Flowable<T> takeUntil(Predicate<? super T> stopPredicate) {
        Objects.requireNonNull(stopPredicate, "stopPredicate is null");
        return RxJavaPlugins.onAssembly(new FlowableTakeUntilPredicate(this, stopPredicate));
    }

    public final Single<List<T>> toList() {
        return RxJavaPlugins.onAssembly(new FlowableToListSingle(this));
    }
}
