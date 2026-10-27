package rx;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import rx.BackpressureOverflow;
import rx.exceptions.Exceptions;
import rx.exceptions.OnErrorFailedException;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Actions;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.functions.Func3;
import rx.functions.FuncN;
import rx.functions.Functions;
import rx.internal.operators.CachedObservable;
import rx.internal.operators.EmptyObservableHolder;
import rx.internal.operators.OnSubscribeCombineLatest;
import rx.internal.operators.OnSubscribeConcatMap;
import rx.internal.operators.OnSubscribeDelaySubscription;
import rx.internal.operators.OnSubscribeDoOnEach;
import rx.internal.operators.OnSubscribeFilter;
import rx.internal.operators.OnSubscribeFromCallable;
import rx.internal.operators.OnSubscribeFromIterable;
import rx.internal.operators.OnSubscribeLift;
import rx.internal.operators.OnSubscribeMap;
import rx.internal.operators.OnSubscribeRange;
import rx.internal.operators.OnSubscribeRedo;
import rx.internal.operators.OnSubscribeSingle;
import rx.internal.operators.OnSubscribeSwitchIfEmpty;
import rx.internal.operators.OnSubscribeThrow;
import rx.internal.operators.OnSubscribeTimeoutTimedWithFallback;
import rx.internal.operators.OnSubscribeTimerOnce;
import rx.internal.operators.OperatorAsObservable;
import rx.internal.operators.OperatorBufferWithTime;
import rx.internal.operators.OperatorDebounceWithTime;
import rx.internal.operators.OperatorDelay;
import rx.internal.operators.OperatorDistinctUntilChanged;
import rx.internal.operators.OperatorDoOnUnsubscribe;
import rx.internal.operators.OperatorMerge;
import rx.internal.operators.OperatorObserveOn;
import rx.internal.operators.OperatorOnBackpressureBuffer;
import rx.internal.operators.OperatorOnBackpressureLatest;
import rx.internal.operators.OperatorOnErrorResumeNextViaFunction;
import rx.internal.operators.OperatorReplay;
import rx.internal.operators.OperatorSampleWithTime;
import rx.internal.operators.OperatorSingle;
import rx.internal.operators.OperatorSubscribeOn;
import rx.internal.operators.OperatorTake;
import rx.internal.operators.OperatorThrottleFirst;
import rx.internal.operators.OperatorToObservableList;
import rx.internal.operators.OperatorZip;
import rx.internal.util.ActionObserver;
import rx.internal.util.ActionSubscriber;
import rx.internal.util.InternalObservableUtils;
import rx.internal.util.ObserverSubscriber;
import rx.internal.util.RxRingBuffer;
import rx.internal.util.ScalarSynchronousObservable;
import rx.internal.util.UtilityFunctions;
import rx.observables.BlockingObservable;
import rx.observables.ConnectableObservable;
import rx.observers.SafeSubscriber;
import rx.plugins.RxJavaHooks;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/* JADX INFO: loaded from: classes6.dex */
public class Observable<T> {
    final OnSubscribe<T> onSubscribe;

    public interface OnSubscribe<T> extends Action1<Subscriber<? super T>> {
    }

    public interface Operator<R, T> extends Func1<Subscriber<? super R>, Subscriber<? super T>> {
    }

    public interface Transformer<T, R> extends Func1<Observable<T>, Observable<R>> {
    }

    protected Observable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    @Deprecated
    public static <T> Observable<T> create(OnSubscribe<T> onSubscribe) {
        return new Observable<>(RxJavaHooks.onCreate(onSubscribe));
    }

    public static <T> Observable<T> unsafeCreate(OnSubscribe<T> onSubscribe) {
        return new Observable<>(RxJavaHooks.onCreate(onSubscribe));
    }

    public final <R> Observable<R> lift(Operator<? extends R, ? super T> operator) {
        return unsafeCreate(new OnSubscribeLift(this.onSubscribe, operator));
    }

    public <R> Observable<R> compose(Transformer<? super T, ? extends R> transformer) {
        return (Observable) transformer.call(this);
    }

    public Single<T> toSingle() {
        return new Single<>(OnSubscribeSingle.create(this));
    }

    public Completable toCompletable() {
        return Completable.fromObservable(this);
    }

    public static <T1, T2, R> Observable<R> combineLatest(Observable<? extends T1> observable, Observable<? extends T2> observable2, Func2<? super T1, ? super T2, ? extends R> func2) {
        return combineLatest(Arrays.asList(observable, observable2), Functions.fromFunc(func2));
    }

    public static <T1, T2, T3, R> Observable<R> combineLatest(Observable<? extends T1> observable, Observable<? extends T2> observable2, Observable<? extends T3> observable3, Func3<? super T1, ? super T2, ? super T3, ? extends R> func3) {
        return combineLatest(Arrays.asList(observable, observable2, observable3), Functions.fromFunc(func3));
    }

    public static <T, R> Observable<R> combineLatest(List<? extends Observable<? extends T>> list, FuncN<? extends R> funcN) {
        return unsafeCreate(new OnSubscribeCombineLatest(list, funcN));
    }

    public static <T> Observable<T> empty() {
        return EmptyObservableHolder.instance();
    }

    public static <T> Observable<T> error(Throwable th) {
        return unsafeCreate(new OnSubscribeThrow(th));
    }

    public static <T> Observable<T> from(Iterable<? extends T> iterable) {
        return unsafeCreate(new OnSubscribeFromIterable(iterable));
    }

    public static <T> Observable<T> fromCallable(Callable<? extends T> callable) {
        return unsafeCreate(new OnSubscribeFromCallable(callable));
    }

    public static <T> Observable<T> just(T t) {
        return ScalarSynchronousObservable.create(t);
    }

    public static <T> Observable<T> merge(Observable<? extends Observable<? extends T>> observable) {
        if (observable.getClass() == ScalarSynchronousObservable.class) {
            return ((ScalarSynchronousObservable) observable).scalarFlatMap(UtilityFunctions.identity());
        }
        return (Observable<T>) observable.lift(OperatorMerge.instance(false));
    }

    public static Observable<Integer> range(int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Count can not be negative");
        }
        if (i2 == 0) {
            return empty();
        }
        if (i > (Integer.MAX_VALUE - i2) + 1) {
            throw new IllegalArgumentException("start + count can not exceed Integer.MAX_VALUE");
        }
        if (i2 == 1) {
            return just(Integer.valueOf(i));
        }
        return unsafeCreate(new OnSubscribeRange(i, (i2 - 1) + i));
    }

    public static Observable<Long> timer(long j, TimeUnit timeUnit) {
        return timer(j, timeUnit, Schedulers.computation());
    }

    public static Observable<Long> timer(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return unsafeCreate(new OnSubscribeTimerOnce(j, timeUnit, scheduler));
    }

    public static <R> Observable<R> zip(Iterable<? extends Observable<?>> iterable, FuncN<? extends R> funcN) {
        ArrayList arrayList = new ArrayList();
        Iterator<? extends Observable<?>> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return just(arrayList.toArray(new Observable[arrayList.size()])).lift(new OperatorZip(funcN));
    }

    public static <T1, T2, R> Observable<R> zip(Observable<? extends T1> observable, Observable<? extends T2> observable2, Func2<? super T1, ? super T2, ? extends R> func2) {
        return just(new Observable[]{observable, observable2}).lift(new OperatorZip(func2));
    }

    public static <T1, T2, T3, R> Observable<R> zip(Observable<? extends T1> observable, Observable<? extends T2> observable2, Observable<? extends T3> observable3, Func3<? super T1, ? super T2, ? super T3, ? extends R> func3) {
        return just(new Observable[]{observable, observable2, observable3}).lift(new OperatorZip(func3));
    }

    public final Observable<T> asObservable() {
        return (Observable<T>) lift(OperatorAsObservable.instance());
    }

    public final Observable<List<T>> buffer(long j, TimeUnit timeUnit) {
        return buffer(j, timeUnit, Integer.MAX_VALUE, Schedulers.computation());
    }

    public final Observable<List<T>> buffer(long j, TimeUnit timeUnit, int i, Scheduler scheduler) {
        return (Observable<List<T>>) lift(new OperatorBufferWithTime(j, j, timeUnit, i, scheduler));
    }

    public final Observable<T> cache() {
        return CachedObservable.from(this);
    }

    public final <R> Observable<R> concatMap(Func1<? super T, ? extends Observable<? extends R>> func1) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable) this).scalarFlatMap(func1);
        }
        return unsafeCreate(new OnSubscribeConcatMap(this, func1, 2, 0));
    }

    public final Observable<T> debounce(long j, TimeUnit timeUnit) {
        return debounce(j, timeUnit, Schedulers.computation());
    }

    public final Observable<T> debounce(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return (Observable<T>) lift(new OperatorDebounceWithTime(j, timeUnit, scheduler));
    }

    public final Observable<T> switchIfEmpty(Observable<? extends T> observable) {
        if (observable == null) {
            throw new NullPointerException("alternate is null");
        }
        return unsafeCreate(new OnSubscribeSwitchIfEmpty(this, observable));
    }

    public final Observable<T> delay(long j, TimeUnit timeUnit) {
        return delay(j, timeUnit, Schedulers.computation());
    }

    public final Observable<T> delay(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return (Observable<T>) lift(new OperatorDelay(j, timeUnit, scheduler));
    }

    public final Observable<T> delaySubscription(long j, TimeUnit timeUnit) {
        return delaySubscription(j, timeUnit, Schedulers.computation());
    }

    public final Observable<T> delaySubscription(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return unsafeCreate(new OnSubscribeDelaySubscription(this, j, timeUnit, scheduler));
    }

    public final Observable<T> distinctUntilChanged() {
        return (Observable<T>) lift(OperatorDistinctUntilChanged.instance());
    }

    public final Observable<T> doOnCompleted(Action0 action0) {
        return unsafeCreate(new OnSubscribeDoOnEach(this, new ActionObserver(Actions.empty(), Actions.empty(), action0)));
    }

    public final Observable<T> doOnError(Action1<? super Throwable> action1) {
        return unsafeCreate(new OnSubscribeDoOnEach(this, new ActionObserver(Actions.empty(), action1, Actions.empty())));
    }

    public final Observable<T> doOnNext(Action1<? super T> action1) {
        return unsafeCreate(new OnSubscribeDoOnEach(this, new ActionObserver(action1, Actions.empty(), Actions.empty())));
    }

    public final Observable<T> doOnUnsubscribe(Action0 action0) {
        return (Observable<T>) lift(new OperatorDoOnUnsubscribe(action0));
    }

    public final Observable<T> filter(Func1<? super T, Boolean> func1) {
        return unsafeCreate(new OnSubscribeFilter(this, func1));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Observable<R> flatMap(Func1<? super T, ? extends Observable<? extends R>> func1) {
        if (getClass() == ScalarSynchronousObservable.class) {
            return ((ScalarSynchronousObservable) this).scalarFlatMap(func1);
        }
        return merge(map(func1));
    }

    public final <R> Observable<R> map(Func1<? super T, ? extends R> func1) {
        return unsafeCreate(new OnSubscribeMap(this, func1));
    }

    public final Observable<T> observeOn(Scheduler scheduler) {
        return observeOn(scheduler, RxRingBuffer.SIZE);
    }

    public final Observable<T> observeOn(Scheduler scheduler, int i) {
        return observeOn(scheduler, false, i);
    }

    public final Observable<T> observeOn(Scheduler scheduler, boolean z, int i) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable) this).scalarScheduleOn(scheduler);
        }
        return (Observable<T>) lift(new OperatorObserveOn(scheduler, z, i));
    }

    public final Observable<T> onBackpressureBuffer(long j, Action0 action0, BackpressureOverflow.Strategy strategy) {
        return (Observable<T>) lift(new OperatorOnBackpressureBuffer(j, action0, strategy));
    }

    public final Observable<T> onBackpressureLatest() {
        return (Observable<T>) lift(OperatorOnBackpressureLatest.instance());
    }

    public final Observable<T> onErrorResumeNext(Func1<? super Throwable, ? extends Observable<? extends T>> func1) {
        return (Observable<T>) lift(new OperatorOnErrorResumeNextViaFunction(func1));
    }

    public final Observable<T> onErrorResumeNext(Observable<? extends T> observable) {
        return (Observable<T>) lift(OperatorOnErrorResumeNextViaFunction.withOther(observable));
    }

    public final Observable<T> onErrorReturn(Func1<? super Throwable, ? extends T> func1) {
        return (Observable<T>) lift(OperatorOnErrorResumeNextViaFunction.withSingle(func1));
    }

    public final ConnectableObservable<T> replay() {
        return OperatorReplay.create(this);
    }

    public final ConnectableObservable<T> replay(int i) {
        return OperatorReplay.create(this, i);
    }

    public final ConnectableObservable<T> replay(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        if (i < 0) {
            throw new IllegalArgumentException("bufferSize < 0");
        }
        return OperatorReplay.create(this, j, timeUnit, scheduler, i);
    }

    public final ConnectableObservable<T> replay(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return OperatorReplay.create(this, j, timeUnit, scheduler);
    }

    public final Observable<T> retryWhen(Func1<? super Observable<? extends Throwable>, ? extends Observable<?>> func1) {
        return OnSubscribeRedo.retry(this, InternalObservableUtils.createRetryDematerializer(func1));
    }

    public final Observable<T> sample(long j, TimeUnit timeUnit) {
        return sample(j, timeUnit, Schedulers.computation());
    }

    public final Observable<T> sample(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return (Observable<T>) lift(new OperatorSampleWithTime(j, timeUnit, scheduler));
    }

    public final Observable<T> single() {
        return (Observable<T>) lift(OperatorSingle.instance());
    }

    public final Subscription subscribe() {
        return subscribe((Subscriber) new ActionSubscriber(Actions.empty(), InternalObservableUtils.ERROR_NOT_IMPLEMENTED, Actions.empty()));
    }

    public final Subscription subscribe(Action1<? super T> action1) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        return subscribe((Subscriber) new ActionSubscriber(action1, InternalObservableUtils.ERROR_NOT_IMPLEMENTED, Actions.empty()));
    }

    public final Subscription subscribe(Action1<? super T> action1, Action1<Throwable> action12) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        if (action12 == null) {
            throw new IllegalArgumentException("onError can not be null");
        }
        return subscribe((Subscriber) new ActionSubscriber(action1, action12, Actions.empty()));
    }

    public final Subscription subscribe(Action1<? super T> action1, Action1<Throwable> action12, Action0 action0) {
        if (action1 == null) {
            throw new IllegalArgumentException("onNext can not be null");
        }
        if (action12 == null) {
            throw new IllegalArgumentException("onError can not be null");
        }
        if (action0 == null) {
            throw new IllegalArgumentException("onComplete can not be null");
        }
        return subscribe((Subscriber) new ActionSubscriber(action1, action12, action0));
    }

    public final Subscription subscribe(Observer<? super T> observer) {
        if (observer instanceof Subscriber) {
            return subscribe((Subscriber) observer);
        }
        if (observer == null) {
            throw new NullPointerException("observer is null");
        }
        return subscribe((Subscriber) new ObserverSubscriber(observer));
    }

    public final Subscription unsafeSubscribe(Subscriber<? super T> subscriber) {
        try {
            subscriber.onStart();
            RxJavaHooks.onObservableStart(this, this.onSubscribe).call(subscriber);
            return RxJavaHooks.onObservableReturn(subscriber);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            try {
                subscriber.onError(RxJavaHooks.onObservableError(th));
                return Subscriptions.unsubscribed();
            } catch (Throwable th2) {
                Exceptions.throwIfFatal(th2);
                OnErrorFailedException onErrorFailedException = new OnErrorFailedException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th2);
                RxJavaHooks.onObservableError(onErrorFailedException);
                throw onErrorFailedException;
            }
        }
    }

    public final Subscription subscribe(Subscriber<? super T> subscriber) {
        return subscribe(subscriber, this);
    }

    static <T> Subscription subscribe(Subscriber<? super T> subscriber, Observable<T> observable) {
        if (subscriber == null) {
            throw new IllegalArgumentException("subscriber can not be null");
        }
        if (observable.onSubscribe == null) {
            throw new IllegalStateException("onSubscribe function can not be null.");
        }
        subscriber.onStart();
        if (!(subscriber instanceof SafeSubscriber)) {
            subscriber = new SafeSubscriber(subscriber);
        }
        try {
            RxJavaHooks.onObservableStart(observable, observable.onSubscribe).call(subscriber);
            return RxJavaHooks.onObservableReturn(subscriber);
        } catch (Throwable th) {
            Exceptions.throwIfFatal(th);
            if (subscriber.isUnsubscribed()) {
                RxJavaHooks.onError(RxJavaHooks.onObservableError(th));
            } else {
                try {
                    subscriber.onError(RxJavaHooks.onObservableError(th));
                } catch (Throwable th2) {
                    Exceptions.throwIfFatal(th2);
                    OnErrorFailedException onErrorFailedException = new OnErrorFailedException("Error occurred attempting to subscribe [" + th.getMessage() + "] and then again while trying to pass to onError.", th2);
                    RxJavaHooks.onObservableError(onErrorFailedException);
                    throw onErrorFailedException;
                }
            }
            return Subscriptions.unsubscribed();
        }
    }

    public final Observable<T> subscribeOn(Scheduler scheduler) {
        return subscribeOn(scheduler, true);
    }

    public final Observable<T> subscribeOn(Scheduler scheduler, boolean z) {
        if (this instanceof ScalarSynchronousObservable) {
            return ((ScalarSynchronousObservable) this).scalarScheduleOn(scheduler);
        }
        return unsafeCreate(new OperatorSubscribeOn(this, scheduler, z));
    }

    public final Observable<T> take(int i) {
        return (Observable<T>) lift(new OperatorTake(i));
    }

    public final Observable<T> throttleFirst(long j, TimeUnit timeUnit) {
        return throttleFirst(j, timeUnit, Schedulers.computation());
    }

    public final Observable<T> throttleFirst(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return (Observable<T>) lift(new OperatorThrottleFirst(j, timeUnit, scheduler));
    }

    public final Observable<T> throttleWithTimeout(long j, TimeUnit timeUnit) {
        return debounce(j, timeUnit);
    }

    public final Observable<T> timeout(long j, TimeUnit timeUnit) {
        return timeout(j, timeUnit, null, Schedulers.computation());
    }

    public final Observable<T> timeout(long j, TimeUnit timeUnit, Observable<? extends T> observable, Scheduler scheduler) {
        return unsafeCreate(new OnSubscribeTimeoutTimedWithFallback(this, j, timeUnit, scheduler, observable));
    }

    public final BlockingObservable<T> toBlocking() {
        return BlockingObservable.from(this);
    }

    public final Observable<List<T>> toList() {
        return (Observable<List<T>>) lift(OperatorToObservableList.instance());
    }

    public final <T2, R> Observable<R> zipWith(Observable<? extends T2> observable, Func2<? super T, ? super T2, ? extends R> func2) {
        return zip(this, observable, func2);
    }
}
