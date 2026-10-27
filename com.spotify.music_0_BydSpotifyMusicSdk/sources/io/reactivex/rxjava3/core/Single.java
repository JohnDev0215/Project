package io.reactivex.rxjava3.core;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.functions.l;
import io.reactivex.rxjava3.functions.n;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.functions.p;
import io.reactivex.rxjava3.functions.q;
import io.reactivex.rxjava3.internal.functions.k;
import io.reactivex.rxjava3.internal.fuseable.c;
import io.reactivex.rxjava3.internal.jdk8.m;
import io.reactivex.rxjava3.internal.observers.BiConsumerSingleObserver;
import io.reactivex.rxjava3.internal.observers.ConsumerSingleObserver;
import io.reactivex.rxjava3.internal.observers.DisposableAutoReleaseMultiObserver;
import io.reactivex.rxjava3.internal.observers.f;
import io.reactivex.rxjava3.internal.operators.flowable.d0;
import io.reactivex.rxjava3.internal.operators.flowable.e0;
import io.reactivex.rxjava3.internal.operators.flowable.r;
import io.reactivex.rxjava3.internal.operators.flowable.s0;
import io.reactivex.rxjava3.internal.operators.flowable.v;
import io.reactivex.rxjava3.internal.operators.flowable.x0;
import io.reactivex.rxjava3.internal.operators.observable.t2;
import io.reactivex.rxjava3.internal.operators.observable.v2;
import io.reactivex.rxjava3.internal.operators.single.a0;
import io.reactivex.rxjava3.internal.operators.single.b;
import io.reactivex.rxjava3.internal.operators.single.c0;
import io.reactivex.rxjava3.internal.operators.single.d;
import io.reactivex.rxjava3.internal.operators.single.e;
import io.reactivex.rxjava3.internal.operators.single.f0;
import io.reactivex.rxjava3.internal.operators.single.g0;
import io.reactivex.rxjava3.internal.operators.single.h;
import io.reactivex.rxjava3.internal.operators.single.h0;
import io.reactivex.rxjava3.internal.operators.single.i;
import io.reactivex.rxjava3.internal.operators.single.j;
import io.reactivex.rxjava3.internal.operators.single.j0;
import io.reactivex.rxjava3.internal.operators.single.k0;
import io.reactivex.rxjava3.internal.operators.single.l0;
import io.reactivex.rxjava3.internal.operators.single.m0;
import io.reactivex.rxjava3.internal.operators.single.n0;
import io.reactivex.rxjava3.internal.operators.single.u;
import io.reactivex.rxjava3.internal.operators.single.w;
import io.reactivex.rxjava3.internal.operators.single.x;
import io.reactivex.rxjava3.internal.operators.single.y;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import p.az1;
import p.du;
import p.he1;
import p.u4;
import p.ux1;

/* JADX INFO: loaded from: classes.dex */
public abstract class Single<T> implements SingleSource<T> {
    public static <T> Single<T> amb(Iterable<? extends SingleSource<? extends T>> iterable) {
        Objects.requireNonNull(iterable, "sources is null");
        return new h(null, 4, iterable);
    }

    @SafeVarargs
    public static <T> Single<T> ambArray(SingleSource<? extends T>... singleSourceArr) {
        Objects.requireNonNull(singleSourceArr, "sources is null");
        if (singleSourceArr.length == 0) {
            return error(f0.a());
        }
        if (singleSourceArr.length == 1) {
            return wrap(singleSourceArr[0]);
        }
        return new h(singleSourceArr, 4, null);
    }

    public static <T> Flowable<T> concat(Iterable<? extends SingleSource<? extends T>> iterable) {
        return Flowable.i(iterable).e(false, 2);
    }

    @SafeVarargs
    public static <T> Flowable<T> concatArray(SingleSource<? extends T>... singleSourceArr) {
        return Flowable.h(singleSourceArr).e(false, 2);
    }

    @SafeVarargs
    public static <T> Flowable<T> concatArrayDelayError(SingleSource<? extends T>... singleSourceArr) {
        return Flowable.h(singleSourceArr).e(true, 2);
    }

    @SafeVarargs
    public static <T> Flowable<T> concatArrayEager(SingleSource<? extends T>... singleSourceArr) {
        Flowable flowableH = Flowable.h(singleSourceArr);
        o oVarB = f0.b();
        int i = Flowable.c;
        return flowableH.b(oVarB, i, i);
    }

    @SafeVarargs
    public static <T> Flowable<T> concatArrayEagerDelayError(SingleSource<? extends T>... singleSourceArr) {
        Flowable flowableH = Flowable.h(singleSourceArr);
        o oVarB = f0.b();
        int i = Flowable.c;
        return flowableH.c(oVarB, true, i, i);
    }

    public static <T> Flowable<T> concatDelayError(Iterable<? extends SingleSource<? extends T>> iterable) {
        return Flowable.i(iterable).e(true, 2);
    }

    public static <T> Flowable<T> concatEager(Iterable<? extends SingleSource<? extends T>> iterable) {
        v vVarI = Flowable.i(iterable);
        o oVarB = f0.b();
        int i = Flowable.c;
        return vVarI.c(oVarB, false, i, i);
    }

    public static <T> Flowable<T> concatEagerDelayError(Iterable<? extends SingleSource<? extends T>> iterable) {
        v vVarI = Flowable.i(iterable);
        o oVarB = f0.b();
        int i = Flowable.c;
        return vVarI.c(oVarB, true, i, i);
    }

    public static <T> Single<T> create(SingleOnSubscribe<T> singleOnSubscribe) {
        Objects.requireNonNull(singleOnSubscribe, "source is null");
        return new d(0, singleOnSubscribe);
    }

    public static <T> Single<T> defer(q qVar) {
        Objects.requireNonNull(qVar, "supplier is null");
        return new e(0, qVar);
    }

    public static <T> Single<T> error(q qVar) {
        Objects.requireNonNull(qVar, "supplier is null");
        return new e(1, qVar);
    }

    public static <T> Single<T> fromCallable(Callable<? extends T> callable) {
        Objects.requireNonNull(callable, "callable is null");
        return new d(1, callable);
    }

    public static <T> Single<T> fromCompletionStage(CompletionStage<T> completionStage) {
        Objects.requireNonNull(completionStage, "stage is null");
        return new m(completionStage);
    }

    public static <T> Single<T> fromFuture(Future<? extends T> future) {
        int i = Flowable.c;
        Objects.requireNonNull(future, "future is null");
        return toSingle(new x0(future, 0L, (TimeUnit) null));
    }

    public static <T> Single<T> fromMaybe(MaybeSource<T> maybeSource) {
        Objects.requireNonNull(maybeSource, "maybe is null");
        return new v2(maybeSource, 2, null);
    }

    public static <T> Single<T> fromObservable(ObservableSource<? extends T> observableSource) {
        Objects.requireNonNull(observableSource, "observable is null");
        return new v2(observableSource, 1, null);
    }

    public static <T> Single<T> fromPublisher(az1 az1Var) {
        Objects.requireNonNull(az1Var, "publisher is null");
        return new d(2, az1Var);
    }

    public static <T> Single<T> fromSupplier(q qVar) {
        Objects.requireNonNull(qVar, "supplier is null");
        return new e(2, qVar);
    }

    public static <T> Single<T> just(T t) {
        Objects.requireNonNull(t, "item is null");
        return new d(3, t);
    }

    public static <T> Flowable<T> merge(Iterable<? extends SingleSource<? extends T>> iterable) {
        return Flowable.i(iterable).g(false, Integer.MAX_VALUE);
    }

    @SafeVarargs
    public static <T> Flowable<T> mergeArray(SingleSource<? extends T>... singleSourceArr) {
        return Flowable.h(singleSourceArr).g(false, Math.max(1, singleSourceArr.length));
    }

    @SafeVarargs
    public static <T> Flowable<T> mergeArrayDelayError(SingleSource<? extends T>... singleSourceArr) {
        return Flowable.h(singleSourceArr).g(true, Math.max(1, singleSourceArr.length));
    }

    public static <T> Flowable<T> mergeDelayError(Iterable<? extends SingleSource<? extends T>> iterable) {
        return Flowable.i(iterable).g(true, Integer.MAX_VALUE);
    }

    public static <T> Single<T> never() {
        return g0.c;
    }

    public static <T> Single<Boolean> sequenceEqual(SingleSource<? extends T> singleSource, SingleSource<? extends T> singleSource2) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        return new j(singleSource, singleSource2, 1);
    }

    public static <T> Flowable<T> switchOnNext(az1 az1Var) {
        Objects.requireNonNull(az1Var, "sources is null");
        return new io.reactivex.rxjava3.internal.operators.mixed.d(az1Var, false);
    }

    public static <T> Flowable<T> switchOnNextDelayError(az1 az1Var) {
        Objects.requireNonNull(az1Var, "sources is null");
        return new io.reactivex.rxjava3.internal.operators.mixed.d(az1Var, true);
    }

    private Single<T> timeout0(long j, TimeUnit timeUnit, Scheduler scheduler, SingleSource<? extends T> singleSource) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new j0(this, j, timeUnit, scheduler, singleSource);
    }

    public static Single<Long> timer(long j, TimeUnit timeUnit) {
        return timer(j, timeUnit, io.reactivex.rxjava3.schedulers.e.b);
    }

    private static <T> Single<T> toSingle(Flowable<T> flowable) {
        return new s0(flowable);
    }

    public static <T> Single<T> unsafeCreate(SingleSource<T> singleSource) {
        Objects.requireNonNull(singleSource, "onSubscribe is null");
        if (singleSource instanceof Single) {
            throw new IllegalArgumentException("unsafeCreate(Single) should be upgraded");
        }
        return new io.reactivex.rxjava3.internal.operators.single.m(singleSource, 1);
    }

    public static <T, U> Single<T> using(q qVar, o oVar, g gVar) {
        return using(qVar, oVar, gVar, true);
    }

    public static <T> Single<T> wrap(SingleSource<T> singleSource) {
        Objects.requireNonNull(singleSource, "source is null");
        return singleSource instanceof Single ? (Single) singleSource : new io.reactivex.rxjava3.internal.operators.single.m(singleSource, 1);
    }

    public static <T, R> Single<R> zip(Iterable<? extends SingleSource<? extends T>> iterable, o oVar) {
        Objects.requireNonNull(oVar, "zipper is null");
        Objects.requireNonNull(iterable, "sources is null");
        return new h(iterable, 3, oVar);
    }

    @SafeVarargs
    public static <T, R> Single<R> zipArray(o oVar, SingleSource<? extends T>... singleSourceArr) {
        Objects.requireNonNull(oVar, "zipper is null");
        Objects.requireNonNull(singleSourceArr, "sources is null");
        return singleSourceArr.length == 0 ? error(new NoSuchElementException()) : new h(singleSourceArr, 2, oVar);
    }

    public final Single<T> ambWith(SingleSource<? extends T> singleSource) {
        Objects.requireNonNull(singleSource, "other is null");
        return ambArray(this, singleSource);
    }

    public final T blockingGet() {
        f fVar = new f(1);
        subscribe(fVar);
        return (T) fVar.a();
    }

    public final void blockingSubscribe() {
        blockingSubscribe(k.d, k.e);
    }

    public final Single<T> cache() {
        return new b(this);
    }

    public final <U> Single<U> cast(Class<? extends U> cls) {
        Objects.requireNonNull(cls, "clazz is null");
        return (Single<U>) map(new ux1(21, cls));
    }

    public final <R> Single<R> compose(SingleTransformer<? super T, ? extends R> singleTransformer) {
        Objects.requireNonNull(singleTransformer, "transformer is null");
        return wrap(singleTransformer.e(this));
    }

    public final <R> Single<R> concatMap(o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new u(this, oVar, 0);
    }

    public final Completable concatMapCompletable(o oVar) {
        return flatMapCompletable(oVar);
    }

    public final <R> Maybe<R> concatMapMaybe(o oVar) {
        return flatMapMaybe(oVar);
    }

    public final Flowable<T> concatWith(SingleSource<? extends T> singleSource) {
        return concat(this, singleSource);
    }

    public final Single<Boolean> contains(Object obj) {
        return contains(obj, k.j);
    }

    public final Single<T> delay(long j, TimeUnit timeUnit) {
        return delay(j, timeUnit, io.reactivex.rxjava3.schedulers.e.b, false);
    }

    public final Single<T> delaySubscription(long j, TimeUnit timeUnit) {
        return delaySubscription(j, timeUnit, io.reactivex.rxjava3.schedulers.e.b);
    }

    public final <R> Maybe<R> dematerialize(o oVar) {
        Objects.requireNonNull(oVar, "selector is null");
        return new a0(this, oVar, 1);
    }

    public final Single<T> doAfterSuccess(g gVar) {
        Objects.requireNonNull(gVar, "onAfterSuccess is null");
        return new io.reactivex.rxjava3.internal.operators.single.o(this, gVar, 0);
    }

    public final Single<T> doAfterTerminate(io.reactivex.rxjava3.functions.a aVar) {
        Objects.requireNonNull(aVar, "onAfterTerminate is null");
        return new io.reactivex.rxjava3.internal.operators.single.q(this, aVar, 2);
    }

    public final Single<T> doFinally(io.reactivex.rxjava3.functions.a aVar) {
        Objects.requireNonNull(aVar, "onFinally is null");
        return new io.reactivex.rxjava3.internal.operators.single.q(this, aVar, 0);
    }

    public final Single<T> doOnDispose(io.reactivex.rxjava3.functions.a aVar) {
        Objects.requireNonNull(aVar, "onDispose is null");
        return new io.reactivex.rxjava3.internal.operators.single.q(this, aVar, 1);
    }

    public final Single<T> doOnError(g gVar) {
        Objects.requireNonNull(gVar, "onError is null");
        return new io.reactivex.rxjava3.internal.operators.single.o(this, gVar, 1);
    }

    public final Single<T> doOnEvent(io.reactivex.rxjava3.functions.b bVar) {
        Objects.requireNonNull(bVar, "onEvent is null");
        return new h(this, 5, bVar);
    }

    public final Single<T> doOnLifecycle(g gVar, io.reactivex.rxjava3.functions.a aVar) {
        Objects.requireNonNull(gVar, "onSubscribe is null");
        Objects.requireNonNull(aVar, "onDispose is null");
        return new c0(this, gVar, aVar, 2);
    }

    public final Single<T> doOnSubscribe(g gVar) {
        Objects.requireNonNull(gVar, "onSubscribe is null");
        return new io.reactivex.rxjava3.internal.operators.single.o(this, gVar, 2);
    }

    public final Single<T> doOnSuccess(g gVar) {
        Objects.requireNonNull(gVar, "onSuccess is null");
        return new io.reactivex.rxjava3.internal.operators.single.o(this, gVar, 3);
    }

    public final Single<T> doOnTerminate(io.reactivex.rxjava3.functions.a aVar) {
        Objects.requireNonNull(aVar, "onTerminate is null");
        return new io.reactivex.rxjava3.internal.operators.single.q(this, aVar, 3);
    }

    public final Maybe<T> filter(p pVar) {
        Objects.requireNonNull(pVar, "predicate is null");
        return new io.reactivex.rxjava3.internal.jdk8.o(this, 1, pVar);
    }

    public final <R> Single<R> flatMap(o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new u(this, oVar, 0);
    }

    public final Completable flatMapCompletable(o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new w(this, oVar);
    }

    public final <R> Maybe<R> flatMapMaybe(o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new a0(this, oVar, 0);
    }

    public final <R> Observable<R> flatMapObservable(o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new io.reactivex.rxjava3.internal.operators.mixed.a(this, 1, oVar);
    }

    public final <R> Flowable<R> flatMapPublisher(o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new x(this, oVar, 1);
    }

    public final <U> Flowable<U> flattenAsFlowable(o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new x(this, oVar, 0);
    }

    public final <U> Observable<U> flattenAsObservable(o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new y(this, oVar);
    }

    public final <R> Flowable<R> flattenStreamAsFlowable(o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new io.reactivex.rxjava3.internal.jdk8.k(this, oVar);
    }

    public final <R> Observable<R> flattenStreamAsObservable(o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new io.reactivex.rxjava3.internal.jdk8.b(this, 1, oVar);
    }

    public final Single<T> hide() {
        return new io.reactivex.rxjava3.internal.operators.single.m(this, 2);
    }

    public final Completable ignoreElement() {
        return new io.reactivex.rxjava3.internal.operators.completable.e(5, this);
    }

    public final <R> Single<R> lift(SingleOperator<? extends R, ? super T> singleOperator) {
        Objects.requireNonNull(singleOperator, "lift is null");
        return new h(this, 6, singleOperator);
    }

    public final <R> Single<R> map(o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new u(this, oVar, 2);
    }

    public final <R> Maybe<R> mapOptional(o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new io.reactivex.rxjava3.internal.jdk8.o(this, 0, oVar);
    }

    public final Single<Notification<T>> materialize() {
        return new d(4, this);
    }

    public final Flowable<T> mergeWith(SingleSource<? extends T> singleSource) {
        return merge(this, singleSource);
    }

    public final Single<T> observeOn(Scheduler scheduler) {
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new h0(this, scheduler, 0);
    }

    public final <U> Maybe<U> ofType(Class<U> cls) {
        Objects.requireNonNull(cls, "clazz is null");
        Maybe<T> maybeFilter = filter(new du(3, cls));
        maybeFilter.getClass();
        return new io.reactivex.rxjava3.internal.operators.maybe.e(maybeFilter, new ux1(21, cls));
    }

    public final Maybe<T> onErrorComplete() {
        return onErrorComplete(k.g);
    }

    public final Single<T> onErrorResumeNext(o oVar) {
        Objects.requireNonNull(oVar, "fallbackSupplier is null");
        return new u(this, oVar, 1);
    }

    public final Single<T> onErrorResumeWith(SingleSource<? extends T> singleSource) {
        Objects.requireNonNull(singleSource, "fallback is null");
        return onErrorResumeNext(new io.reactivex.rxjava3.internal.functions.e(singleSource));
    }

    public final Single<T> onErrorReturn(o oVar) {
        Objects.requireNonNull(oVar, "itemSupplier is null");
        return new c0(this, oVar, null, 4);
    }

    public final Single<T> onErrorReturnItem(T t) {
        Objects.requireNonNull(t, "item is null");
        return new c0(this, null, t, 4);
    }

    public final Single<T> onTerminateDetach() {
        return new io.reactivex.rxjava3.internal.operators.single.m(this, 0);
    }

    public final Flowable<T> repeat() {
        return toFlowable().k(Long.MAX_VALUE);
    }

    public final Flowable<T> repeatUntil(io.reactivex.rxjava3.functions.e eVar) {
        Flowable<T> flowable = toFlowable();
        flowable.getClass();
        Objects.requireNonNull(eVar, "stop is null");
        return new e0(flowable, eVar, 1);
    }

    public final Flowable<T> repeatWhen(o oVar) {
        Flowable<T> flowable = toFlowable();
        flowable.getClass();
        Objects.requireNonNull(oVar, "handler is null");
        return new d0(flowable, oVar, 1);
    }

    public final Single<T> retry() {
        return toSingle(toFlowable().l(Long.MAX_VALUE, k.g));
    }

    public final Single<T> retryUntil(io.reactivex.rxjava3.functions.e eVar) {
        Objects.requireNonNull(eVar, "stop is null");
        return retry(Long.MAX_VALUE, new du(2, null));
    }

    public final Single<T> retryWhen(o oVar) {
        Flowable<T> flowable = toFlowable();
        flowable.getClass();
        Objects.requireNonNull(oVar, "handler is null");
        return toSingle(new d0(flowable, oVar, 2));
    }

    public final void safeSubscribe(SingleObserver<? super T> singleObserver) {
        Objects.requireNonNull(singleObserver, "observer is null");
        subscribe(new io.reactivex.rxjava3.internal.observers.m(singleObserver));
    }

    public final Flowable<T> startWith(CompletableSource completableSource) {
        Objects.requireNonNull(completableSource, "other is null");
        CompletableSource eVar = completableSource instanceof Completable ? (Completable) completableSource : new io.reactivex.rxjava3.internal.operators.completable.e(6, completableSource);
        return Flowable.a(eVar instanceof c ? ((c) eVar).b() : new io.reactivex.rxjava3.internal.operators.completable.m(eVar), toFlowable());
    }

    public final Disposable subscribe() {
        return subscribe(k.d, k.f);
    }

    public abstract void subscribeActual(SingleObserver<? super T> singleObserver);

    public final Single<T> subscribeOn(Scheduler scheduler) {
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new h0(this, scheduler, 1);
    }

    public final <E extends SingleObserver<? super T>> E subscribeWith(E e) {
        subscribe(e);
        return e;
    }

    public final Single<T> takeUntil(CompletableSource completableSource) {
        Objects.requireNonNull(completableSource, "other is null");
        return takeUntil(new io.reactivex.rxjava3.internal.operators.completable.m(completableSource));
    }

    public final io.reactivex.rxjava3.observers.e test() {
        io.reactivex.rxjava3.observers.e eVar = new io.reactivex.rxjava3.observers.e();
        subscribe(eVar);
        return eVar;
    }

    public final Single<io.reactivex.rxjava3.schedulers.f> timeInterval(TimeUnit timeUnit) {
        return timeInterval(timeUnit, io.reactivex.rxjava3.schedulers.e.b);
    }

    public final Single<T> timeout(long j, TimeUnit timeUnit) {
        return timeout0(j, timeUnit, io.reactivex.rxjava3.schedulers.e.b, null);
    }

    public final Single<io.reactivex.rxjava3.schedulers.f> timestamp(TimeUnit timeUnit) {
        return timestamp(timeUnit, io.reactivex.rxjava3.schedulers.e.b);
    }

    public final <R> R to(SingleConverter<T, ? extends R> singleConverter) {
        Objects.requireNonNull(singleConverter, "converter is null");
        u4.z(singleConverter);
        throw null;
    }

    public final CompletionStage<T> toCompletionStage() {
        return (CompletionStage) subscribeWith(new io.reactivex.rxjava3.internal.jdk8.a());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Flowable<T> toFlowable() {
        return this instanceof c ? ((c) this).b() : new l0(this);
    }

    public final Future<T> toFuture() {
        return (Future) subscribeWith(new io.reactivex.rxjava3.internal.observers.h());
    }

    public final Maybe<T> toMaybe() {
        return new t2(2, this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Observable<T> toObservable() {
        return this instanceof io.reactivex.rxjava3.internal.fuseable.d ? ((io.reactivex.rxjava3.internal.fuseable.d) this).a() : new m0(this);
    }

    public final Single<T> unsubscribeOn(Scheduler scheduler) {
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new h0(this, scheduler, 2);
    }

    public final <U, R> Single<R> zipWith(SingleSource<U> singleSource, io.reactivex.rxjava3.functions.c cVar) {
        return zip(this, singleSource, cVar);
    }

    public static <T> Flowable<T> mergeDelayError(az1 az1Var) {
        Objects.requireNonNull(az1Var, "sources is null");
        return new r(az1Var, true);
    }

    public static <T, U> Single<T> using(q qVar, o oVar, g gVar, boolean z) {
        Objects.requireNonNull(qVar, "resourceSupplier is null");
        Objects.requireNonNull(oVar, "sourceSupplier is null");
        Objects.requireNonNull(gVar, "resourceCleanup is null");
        return new n0(qVar, oVar, gVar, z, 0);
    }

    public final void blockingSubscribe(g gVar) {
        blockingSubscribe(gVar, k.e);
    }

    public final Single<Boolean> contains(Object obj, io.reactivex.rxjava3.functions.d dVar) {
        Objects.requireNonNull(obj, "item is null");
        Objects.requireNonNull(dVar, "comparer is null");
        return new c0(this, obj, dVar, 1);
    }

    public final Maybe<T> onErrorComplete(p pVar) {
        Objects.requireNonNull(pVar, "predicate is null");
        return new a0(this, pVar, 2);
    }

    public final Disposable subscribe(io.reactivex.rxjava3.functions.b bVar) {
        Objects.requireNonNull(bVar, "onCallback is null");
        BiConsumerSingleObserver biConsumerSingleObserver = new BiConsumerSingleObserver(bVar);
        subscribe(biConsumerSingleObserver);
        return biConsumerSingleObserver;
    }

    public static <T> Observable<T> concat(ObservableSource<? extends SingleSource<? extends T>> observableSource) {
        Objects.requireNonNull(observableSource, "sources is null");
        return new io.reactivex.rxjava3.internal.operators.mixed.f(observableSource, k.a, ErrorMode.c, 2, 1);
    }

    public static <T> Flowable<T> concatDelayError(az1 az1Var) {
        return Flowable.j(az1Var).e(true, 2);
    }

    public static <T> Flowable<T> concatEager(Iterable<? extends SingleSource<? extends T>> iterable, int i) {
        return Flowable.i(iterable).c(f0.b(), false, i, 1);
    }

    public static <T> Flowable<T> concatEagerDelayError(Iterable<? extends SingleSource<? extends T>> iterable, int i) {
        return Flowable.i(iterable).c(f0.b(), true, i, 1);
    }

    public static <T> Single<T> error(Throwable th) {
        Objects.requireNonNull(th, "throwable is null");
        return error(new io.reactivex.rxjava3.internal.functions.e(th));
    }

    public static <T> Single<T> fromMaybe(MaybeSource<T> maybeSource, T t) {
        Objects.requireNonNull(maybeSource, "maybe is null");
        Objects.requireNonNull(t, "defaultItem is null");
        return new v2(maybeSource, 2, t);
    }

    public static <T> Flowable<T> merge(az1 az1Var) {
        Objects.requireNonNull(az1Var, "sources is null");
        return new r(az1Var, false);
    }

    public static Single<Long> timer(long j, TimeUnit timeUnit, Scheduler scheduler) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new k0(j, timeUnit, scheduler);
    }

    public final void blockingSubscribe(g gVar, g gVar2) {
        Objects.requireNonNull(gVar, "onSuccess is null");
        Objects.requireNonNull(gVar2, "onError is null");
        f fVar = new f(1);
        subscribe(fVar);
        try {
            if (fVar.getCount() != 0) {
                try {
                    fVar.await();
                } catch (InterruptedException e) {
                    fVar.l = true;
                    Disposable disposable = fVar.k;
                    if (disposable != null) {
                        disposable.dispose();
                    }
                    gVar2.accept(e);
                    return;
                }
            }
            Throwable th = fVar.e;
            if (th != null) {
                gVar2.accept(th);
                return;
            }
            Object obj = fVar.c;
            if (obj != null) {
                gVar.accept(obj);
            }
        } catch (Throwable th2) {
            he1.R(th2);
            RxJavaPlugins.b(th2);
        }
    }

    public final Single<T> delay(long j, TimeUnit timeUnit, boolean z) {
        return delay(j, timeUnit, io.reactivex.rxjava3.schedulers.e.b, z);
    }

    public final Single<T> delaySubscription(CompletableSource completableSource) {
        Objects.requireNonNull(completableSource, "subscriptionIndicator is null");
        return new h(this, 0, completableSource);
    }

    public final <U, R> Single<R> flatMap(o oVar, io.reactivex.rxjava3.functions.c cVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        Objects.requireNonNull(cVar, "combiner is null");
        return new c0(this, oVar, cVar, 3);
    }

    public final Flowable<T> repeat(long j) {
        return toFlowable().k(j);
    }

    public final <E> Single<T> takeUntil(az1 az1Var) {
        Objects.requireNonNull(az1Var, "other is null");
        return new i(this, az1Var, 1);
    }

    public final io.reactivex.rxjava3.observers.e test(boolean z) {
        io.reactivex.rxjava3.observers.e eVar = new io.reactivex.rxjava3.observers.e();
        if (z) {
            eVar.dispose();
        }
        subscribe(eVar);
        return eVar;
    }

    public final Single<io.reactivex.rxjava3.schedulers.f> timeInterval() {
        return timeInterval(TimeUnit.MILLISECONDS, io.reactivex.rxjava3.schedulers.e.b);
    }

    public final Single<T> timeout(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return timeout0(j, timeUnit, scheduler, null);
    }

    public final Single<io.reactivex.rxjava3.schedulers.f> timestamp() {
        return timestamp(TimeUnit.MILLISECONDS, io.reactivex.rxjava3.schedulers.e.b);
    }

    public static <T> Flowable<T> concatEager(az1 az1Var) {
        Flowable flowableJ = Flowable.j(az1Var);
        o oVarB = f0.b();
        int i = Flowable.c;
        return flowableJ.b(oVarB, i, i);
    }

    public static <T> Flowable<T> concatEagerDelayError(az1 az1Var) {
        Flowable flowableJ = Flowable.j(az1Var);
        o oVarB = f0.b();
        int i = Flowable.c;
        return flowableJ.c(oVarB, true, i, i);
    }

    public static <T> Flowable<T> mergeDelayError(SingleSource<? extends T> singleSource, SingleSource<? extends T> singleSource2) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        return Flowable.h(singleSource, singleSource2).g(true, Integer.MAX_VALUE);
    }

    public static <T1, T2, R> Single<R> zip(SingleSource<? extends T1> singleSource, SingleSource<? extends T2> singleSource2, io.reactivex.rxjava3.functions.c cVar) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        Objects.requireNonNull(cVar, "zipper is null");
        return zipArray(new ux1(13, cVar), singleSource, singleSource2);
    }

    public final Single<T> retry(long j) {
        return toSingle(toFlowable().l(j, k.g));
    }

    public final Single<T> timeout(long j, TimeUnit timeUnit, Scheduler scheduler, SingleSource<? extends T> singleSource) {
        Objects.requireNonNull(singleSource, "fallback is null");
        return timeout0(j, timeUnit, scheduler, singleSource);
    }

    public static <T> Flowable<T> concat(az1 az1Var) {
        return concat(az1Var, 2);
    }

    public static <T> Flowable<T> concatDelayError(az1 az1Var, int i) {
        return Flowable.j(az1Var).e(true, i);
    }

    public static <T> Single<T> fromFuture(Future<? extends T> future, long j, TimeUnit timeUnit) {
        int i = Flowable.c;
        Objects.requireNonNull(future, "future is null");
        Objects.requireNonNull(timeUnit, "unit is null");
        return toSingle(new x0(future, j, timeUnit));
    }

    public static <T> Single<T> merge(SingleSource<? extends SingleSource<? extends T>> singleSource) {
        Objects.requireNonNull(singleSource, "source is null");
        return new u(singleSource, k.a, 0);
    }

    public final Single<T> delay(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return delay(j, timeUnit, scheduler, false);
    }

    public final <U> Single<T> delaySubscription(SingleSource<U> singleSource) {
        Objects.requireNonNull(singleSource, "subscriptionIndicator is null");
        return new j(this, singleSource, 0);
    }

    public final Disposable subscribe(g gVar) {
        return subscribe(gVar, k.f);
    }

    public final <E> Single<T> takeUntil(SingleSource<? extends E> singleSource) {
        Objects.requireNonNull(singleSource, "other is null");
        return takeUntil(new l0(singleSource));
    }

    public static <T> Flowable<T> concat(az1 az1Var, int i) {
        Objects.requireNonNull(az1Var, "sources is null");
        k.c(i, "prefetch");
        return new io.reactivex.rxjava3.internal.operators.mixed.c(az1Var, ErrorMode.c, i, 1);
    }

    public final Single<T> delay(long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new io.reactivex.rxjava3.internal.operators.single.g(this, j, timeUnit, scheduler, z);
    }

    public final <R> Single<R> flatMap(o oVar, o oVar2) {
        Objects.requireNonNull(oVar, "onSuccessMapper is null");
        Objects.requireNonNull(oVar2, "onErrorMapper is null");
        return new c0(this, oVar, oVar2, 0);
    }

    public final Disposable subscribe(g gVar, g gVar2) {
        Objects.requireNonNull(gVar, "onSuccess is null");
        Objects.requireNonNull(gVar2, "onError is null");
        ConsumerSingleObserver consumerSingleObserver = new ConsumerSingleObserver(gVar, gVar2);
        subscribe(consumerSingleObserver);
        return consumerSingleObserver;
    }

    public final Single<io.reactivex.rxjava3.schedulers.f> timeInterval(Scheduler scheduler) {
        return timeInterval(TimeUnit.MILLISECONDS, scheduler);
    }

    public final Single<T> timeout(long j, TimeUnit timeUnit, SingleSource<? extends T> singleSource) {
        Objects.requireNonNull(singleSource, "fallback is null");
        return timeout0(j, timeUnit, io.reactivex.rxjava3.schedulers.e.b, singleSource);
    }

    public final Single<io.reactivex.rxjava3.schedulers.f> timestamp(Scheduler scheduler) {
        return timestamp(TimeUnit.MILLISECONDS, scheduler);
    }

    public static <T> Flowable<T> concatEager(az1 az1Var, int i) {
        return Flowable.j(az1Var).b(f0.b(), i, 1);
    }

    public static <T> Flowable<T> concatEagerDelayError(az1 az1Var, int i) {
        return Flowable.j(az1Var).c(f0.b(), true, i, 1);
    }

    public static <T> Flowable<T> merge(SingleSource<? extends T> singleSource, SingleSource<? extends T> singleSource2) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        return Flowable.h(singleSource, singleSource2).g(false, Integer.MAX_VALUE);
    }

    public static <T> Flowable<T> mergeDelayError(SingleSource<? extends T> singleSource, SingleSource<? extends T> singleSource2, SingleSource<? extends T> singleSource3) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        Objects.requireNonNull(singleSource3, "source3 is null");
        return Flowable.h(singleSource, singleSource2, singleSource3).g(true, Integer.MAX_VALUE);
    }

    public final <U> Single<T> delaySubscription(ObservableSource<U> observableSource) {
        Objects.requireNonNull(observableSource, "subscriptionIndicator is null");
        return new h(this, 1, observableSource);
    }

    public final Single<T> retry(io.reactivex.rxjava3.functions.d dVar) {
        Flowable<T> flowable = toFlowable();
        flowable.getClass();
        Objects.requireNonNull(dVar, "predicate is null");
        return toSingle(new e0(flowable, dVar, 2));
    }

    public final Single<io.reactivex.rxjava3.schedulers.f> timeInterval(TimeUnit timeUnit, Scheduler scheduler) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new n0(this, timeUnit, scheduler, true, 1);
    }

    public final Single<io.reactivex.rxjava3.schedulers.f> timestamp(TimeUnit timeUnit, Scheduler scheduler) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new n0(this, timeUnit, scheduler, false, 1);
    }

    public static <T> Flowable<T> concat(SingleSource<? extends T> singleSource, SingleSource<? extends T> singleSource2) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        return Flowable.h(singleSource, singleSource2).e(false, 2);
    }

    public static <T1, T2, T3, R> Single<R> zip(SingleSource<? extends T1> singleSource, SingleSource<? extends T2> singleSource2, SingleSource<? extends T3> singleSource3, io.reactivex.rxjava3.functions.h hVar) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        Objects.requireNonNull(singleSource3, "source3 is null");
        Objects.requireNonNull(hVar, "zipper is null");
        return zipArray(new ux1(14, hVar), singleSource, singleSource2, singleSource3);
    }

    public final <U> Single<T> delaySubscription(az1 az1Var) {
        Objects.requireNonNull(az1Var, "subscriptionIndicator is null");
        return new i(this, az1Var, 0);
    }

    public final Flowable<T> startWith(SingleSource<T> singleSource) {
        Objects.requireNonNull(singleSource, "other is null");
        return Flowable.a(wrap(singleSource).toFlowable(), toFlowable());
    }

    public static <T> Flowable<T> merge(SingleSource<? extends T> singleSource, SingleSource<? extends T> singleSource2, SingleSource<? extends T> singleSource3) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        Objects.requireNonNull(singleSource3, "source3 is null");
        return Flowable.h(singleSource, singleSource2, singleSource3).g(false, Integer.MAX_VALUE);
    }

    public final Disposable subscribe(g gVar, g gVar2, io.reactivex.rxjava3.disposables.b bVar) {
        Objects.requireNonNull(gVar, "onSuccess is null");
        Objects.requireNonNull(gVar2, "onError is null");
        Objects.requireNonNull(bVar, "container is null");
        DisposableAutoReleaseMultiObserver disposableAutoReleaseMultiObserver = new DisposableAutoReleaseMultiObserver(gVar, gVar2, k.c, bVar);
        bVar.c(disposableAutoReleaseMultiObserver);
        subscribe(disposableAutoReleaseMultiObserver);
        return disposableAutoReleaseMultiObserver;
    }

    public static <T> Flowable<T> mergeDelayError(SingleSource<? extends T> singleSource, SingleSource<? extends T> singleSource2, SingleSource<? extends T> singleSource3, SingleSource<? extends T> singleSource4) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        Objects.requireNonNull(singleSource3, "source3 is null");
        Objects.requireNonNull(singleSource4, "source4 is null");
        return Flowable.h(singleSource, singleSource2, singleSource3, singleSource4).g(true, Integer.MAX_VALUE);
    }

    public final Single<T> delaySubscription(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return delaySubscription(Observable.timer(j, timeUnit, scheduler));
    }

    public final Single<T> retry(long j, p pVar) {
        return toSingle(toFlowable().l(j, pVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Flowable<T> startWith(MaybeSource<T> maybeSource) {
        Maybe jVar;
        Flowable gVar;
        Objects.requireNonNull(maybeSource, "other is null");
        if (maybeSource instanceof Maybe) {
            jVar = (Maybe) maybeSource;
        } else {
            jVar = new io.reactivex.rxjava3.internal.operators.maybe.j(maybeSource);
        }
        if (jVar instanceof c) {
            gVar = ((c) jVar).b();
        } else {
            gVar = new io.reactivex.rxjava3.internal.operators.maybe.g(jVar);
        }
        return Flowable.a(gVar, toFlowable());
    }

    public final Single<T> retry(p pVar) {
        return toSingle(toFlowable().l(Long.MAX_VALUE, pVar));
    }

    public static <T> Flowable<T> concat(SingleSource<? extends T> singleSource, SingleSource<? extends T> singleSource2, SingleSource<? extends T> singleSource3) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        Objects.requireNonNull(singleSource3, "source3 is null");
        return Flowable.h(singleSource, singleSource2, singleSource3).e(false, 2);
    }

    public static <T> Flowable<T> merge(SingleSource<? extends T> singleSource, SingleSource<? extends T> singleSource2, SingleSource<? extends T> singleSource3, SingleSource<? extends T> singleSource4) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        Objects.requireNonNull(singleSource3, "source3 is null");
        Objects.requireNonNull(singleSource4, "source4 is null");
        return Flowable.h(singleSource, singleSource2, singleSource3, singleSource4).g(false, Integer.MAX_VALUE);
    }

    public static <T1, T2, T3, T4, R> Single<R> zip(SingleSource<? extends T1> singleSource, SingleSource<? extends T2> singleSource2, SingleSource<? extends T3> singleSource3, SingleSource<? extends T4> singleSource4, io.reactivex.rxjava3.functions.i iVar) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        Objects.requireNonNull(singleSource3, "source3 is null");
        Objects.requireNonNull(singleSource4, "source4 is null");
        Objects.requireNonNull(iVar, "zipper is null");
        return zipArray(new ux1(15, iVar), singleSource, singleSource2, singleSource3, singleSource4);
    }

    public final void blockingSubscribe(SingleObserver<? super T> singleObserver) {
        Objects.requireNonNull(singleObserver, "observer is null");
        io.reactivex.rxjava3.internal.observers.d dVar = new io.reactivex.rxjava3.internal.observers.d();
        singleObserver.onSubscribe(dVar);
        subscribe(dVar);
        if (dVar.getCount() != 0) {
            try {
                dVar.await();
            } catch (InterruptedException e) {
                dVar.dispose();
                singleObserver.onError(e);
                return;
            }
        }
        if (dVar.isDisposed()) {
            return;
        }
        Throwable th = dVar.e;
        if (th != null) {
            singleObserver.onError(th);
        } else {
            singleObserver.onSuccess(dVar.c);
        }
    }

    @Override // io.reactivex.rxjava3.core.SingleSource
    public final void subscribe(SingleObserver<? super T> singleObserver) {
        Objects.requireNonNull(singleObserver, "observer is null");
        io.reactivex.rxjava3.functions.c cVar = RxJavaPlugins.e;
        if (cVar != null) {
            try {
                singleObserver = (SingleObserver) cVar.k(this, singleObserver);
            } catch (Throwable th) {
                throw io.reactivex.rxjava3.internal.util.c.f(th);
            }
        }
        Objects.requireNonNull(singleObserver, "The RxJavaPlugins.onSubscribe hook returned a null SingleObserver. Please check the handler provided to RxJavaPlugins.setOnSingleSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
        try {
            subscribeActual(singleObserver);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th2) {
            he1.R(th2);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th2);
            throw nullPointerException;
        }
    }

    public static <T> Flowable<T> concat(SingleSource<? extends T> singleSource, SingleSource<? extends T> singleSource2, SingleSource<? extends T> singleSource3, SingleSource<? extends T> singleSource4) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        Objects.requireNonNull(singleSource3, "source3 is null");
        Objects.requireNonNull(singleSource4, "source4 is null");
        return Flowable.h(singleSource, singleSource2, singleSource3, singleSource4).e(false, 2);
    }

    public final Observable<T> startWith(ObservableSource<T> observableSource) {
        Objects.requireNonNull(observableSource, "other is null");
        return Observable.wrap(observableSource).concatWith(toObservable());
    }

    public static <T1, T2, T3, T4, T5, R> Single<R> zip(SingleSource<? extends T1> singleSource, SingleSource<? extends T2> singleSource2, SingleSource<? extends T3> singleSource3, SingleSource<? extends T4> singleSource4, SingleSource<? extends T5> singleSource5, io.reactivex.rxjava3.functions.j jVar) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        Objects.requireNonNull(singleSource3, "source3 is null");
        Objects.requireNonNull(singleSource4, "source4 is null");
        Objects.requireNonNull(singleSource5, "source5 is null");
        Objects.requireNonNull(jVar, "zipper is null");
        return zipArray(new ux1(16, null), singleSource, singleSource2, singleSource3, singleSource4, singleSource5);
    }

    public final Flowable<T> startWith(az1 az1Var) {
        Objects.requireNonNull(az1Var, "other is null");
        Flowable<T> flowable = toFlowable();
        flowable.getClass();
        return new io.reactivex.rxjava3.internal.operators.flowable.b(new az1[]{az1Var, flowable});
    }

    public static <T1, T2, T3, T4, T5, T6, R> Single<R> zip(SingleSource<? extends T1> singleSource, SingleSource<? extends T2> singleSource2, SingleSource<? extends T3> singleSource3, SingleSource<? extends T4> singleSource4, SingleSource<? extends T5> singleSource5, SingleSource<? extends T6> singleSource6, io.reactivex.rxjava3.functions.k kVar) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        Objects.requireNonNull(singleSource3, "source3 is null");
        Objects.requireNonNull(singleSource4, "source4 is null");
        Objects.requireNonNull(singleSource5, "source5 is null");
        Objects.requireNonNull(singleSource6, "source6 is null");
        Objects.requireNonNull(kVar, "zipper is null");
        return zipArray(new ux1(17, null), singleSource, singleSource2, singleSource3, singleSource4, singleSource5, singleSource6);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> Single<R> zip(SingleSource<? extends T1> singleSource, SingleSource<? extends T2> singleSource2, SingleSource<? extends T3> singleSource3, SingleSource<? extends T4> singleSource4, SingleSource<? extends T5> singleSource5, SingleSource<? extends T6> singleSource6, SingleSource<? extends T7> singleSource7, l lVar) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        Objects.requireNonNull(singleSource3, "source3 is null");
        Objects.requireNonNull(singleSource4, "source4 is null");
        Objects.requireNonNull(singleSource5, "source5 is null");
        Objects.requireNonNull(singleSource6, "source6 is null");
        Objects.requireNonNull(singleSource7, "source7 is null");
        Objects.requireNonNull(lVar, "zipper is null");
        return zipArray(new ux1(18, null), singleSource, singleSource2, singleSource3, singleSource4, singleSource5, singleSource6, singleSource7);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Single<R> zip(SingleSource<? extends T1> singleSource, SingleSource<? extends T2> singleSource2, SingleSource<? extends T3> singleSource3, SingleSource<? extends T4> singleSource4, SingleSource<? extends T5> singleSource5, SingleSource<? extends T6> singleSource6, SingleSource<? extends T7> singleSource7, SingleSource<? extends T8> singleSource8, io.reactivex.rxjava3.functions.m mVar) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        Objects.requireNonNull(singleSource3, "source3 is null");
        Objects.requireNonNull(singleSource4, "source4 is null");
        Objects.requireNonNull(singleSource5, "source5 is null");
        Objects.requireNonNull(singleSource6, "source6 is null");
        Objects.requireNonNull(singleSource7, "source7 is null");
        Objects.requireNonNull(singleSource8, "source8 is null");
        Objects.requireNonNull(mVar, "zipper is null");
        return zipArray(new ux1(19, null), singleSource, singleSource2, singleSource3, singleSource4, singleSource5, singleSource6, singleSource7, singleSource8);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Single<R> zip(SingleSource<? extends T1> singleSource, SingleSource<? extends T2> singleSource2, SingleSource<? extends T3> singleSource3, SingleSource<? extends T4> singleSource4, SingleSource<? extends T5> singleSource5, SingleSource<? extends T6> singleSource6, SingleSource<? extends T7> singleSource7, SingleSource<? extends T8> singleSource8, SingleSource<? extends T9> singleSource9, n nVar) {
        Objects.requireNonNull(singleSource, "source1 is null");
        Objects.requireNonNull(singleSource2, "source2 is null");
        Objects.requireNonNull(singleSource3, "source3 is null");
        Objects.requireNonNull(singleSource4, "source4 is null");
        Objects.requireNonNull(singleSource5, "source5 is null");
        Objects.requireNonNull(singleSource6, "source6 is null");
        Objects.requireNonNull(singleSource7, "source7 is null");
        Objects.requireNonNull(singleSource8, "source8 is null");
        Objects.requireNonNull(singleSource9, "source9 is null");
        Objects.requireNonNull(nVar, "zipper is null");
        return zipArray(new ux1(20, null), singleSource, singleSource2, singleSource3, singleSource4, singleSource5, singleSource6, singleSource7, singleSource8, singleSource9);
    }
}
