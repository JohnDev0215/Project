package io.reactivex.rxjava3.core;

import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.b;
import io.reactivex.rxjava3.functions.c;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.functions.l;
import io.reactivex.rxjava3.functions.p;
import io.reactivex.rxjava3.functions.q;
import io.reactivex.rxjava3.internal.functions.j;
import io.reactivex.rxjava3.internal.functions.k;
import io.reactivex.rxjava3.internal.observers.DisposableAutoReleaseObserver;
import io.reactivex.rxjava3.internal.observers.ForEachWhileObserver;
import io.reactivex.rxjava3.internal.observers.LambdaObserver;
import io.reactivex.rxjava3.internal.observers.f;
import io.reactivex.rxjava3.internal.operators.completable.n;
import io.reactivex.rxjava3.internal.operators.flowable.a0;
import io.reactivex.rxjava3.internal.operators.flowable.b0;
import io.reactivex.rxjava3.internal.operators.flowable.c0;
import io.reactivex.rxjava3.internal.operators.flowable.s;
import io.reactivex.rxjava3.internal.operators.maybe.h;
import io.reactivex.rxjava3.internal.operators.mixed.i;
import io.reactivex.rxjava3.internal.operators.observable.a1;
import io.reactivex.rxjava3.internal.operators.observable.a2;
import io.reactivex.rxjava3.internal.operators.observable.a3;
import io.reactivex.rxjava3.internal.operators.observable.b3;
import io.reactivex.rxjava3.internal.operators.observable.c2;
import io.reactivex.rxjava3.internal.operators.observable.c3;
import io.reactivex.rxjava3.internal.operators.observable.d;
import io.reactivex.rxjava3.internal.operators.observable.d5;
import io.reactivex.rxjava3.internal.operators.observable.e0;
import io.reactivex.rxjava3.internal.operators.observable.e5;
import io.reactivex.rxjava3.internal.operators.observable.f4;
import io.reactivex.rxjava3.internal.operators.observable.g2;
import io.reactivex.rxjava3.internal.operators.observable.g3;
import io.reactivex.rxjava3.internal.operators.observable.h0;
import io.reactivex.rxjava3.internal.operators.observable.h2;
import io.reactivex.rxjava3.internal.operators.observable.h3;
import io.reactivex.rxjava3.internal.operators.observable.i0;
import io.reactivex.rxjava3.internal.operators.observable.i2;
import io.reactivex.rxjava3.internal.operators.observable.i3;
import io.reactivex.rxjava3.internal.operators.observable.i5;
import io.reactivex.rxjava3.internal.operators.observable.j0;
import io.reactivex.rxjava3.internal.operators.observable.j2;
import io.reactivex.rxjava3.internal.operators.observable.j3;
import io.reactivex.rxjava3.internal.operators.observable.j4;
import io.reactivex.rxjava3.internal.operators.observable.j5;
import io.reactivex.rxjava3.internal.operators.observable.k0;
import io.reactivex.rxjava3.internal.operators.observable.k2;
import io.reactivex.rxjava3.internal.operators.observable.k4;
import io.reactivex.rxjava3.internal.operators.observable.l0;
import io.reactivex.rxjava3.internal.operators.observable.l1;
import io.reactivex.rxjava3.internal.operators.observable.l2;
import io.reactivex.rxjava3.internal.operators.observable.l5;
import io.reactivex.rxjava3.internal.operators.observable.m;
import io.reactivex.rxjava3.internal.operators.observable.m2;
import io.reactivex.rxjava3.internal.operators.observable.m3;
import io.reactivex.rxjava3.internal.operators.observable.n1;
import io.reactivex.rxjava3.internal.operators.observable.n2;
import io.reactivex.rxjava3.internal.operators.observable.n3;
import io.reactivex.rxjava3.internal.operators.observable.n4;
import io.reactivex.rxjava3.internal.operators.observable.o;
import io.reactivex.rxjava3.internal.operators.observable.o0;
import io.reactivex.rxjava3.internal.operators.observable.o1;
import io.reactivex.rxjava3.internal.operators.observable.o2;
import io.reactivex.rxjava3.internal.operators.observable.o3;
import io.reactivex.rxjava3.internal.operators.observable.p1;
import io.reactivex.rxjava3.internal.operators.observable.p2;
import io.reactivex.rxjava3.internal.operators.observable.q0;
import io.reactivex.rxjava3.internal.operators.observable.q1;
import io.reactivex.rxjava3.internal.operators.observable.q2;
import io.reactivex.rxjava3.internal.operators.observable.q4;
import io.reactivex.rxjava3.internal.operators.observable.q5;
import io.reactivex.rxjava3.internal.operators.observable.r;
import io.reactivex.rxjava3.internal.operators.observable.r0;
import io.reactivex.rxjava3.internal.operators.observable.r1;
import io.reactivex.rxjava3.internal.operators.observable.r2;
import io.reactivex.rxjava3.internal.operators.observable.r3;
import io.reactivex.rxjava3.internal.operators.observable.r4;
import io.reactivex.rxjava3.internal.operators.observable.s5;
import io.reactivex.rxjava3.internal.operators.observable.t;
import io.reactivex.rxjava3.internal.operators.observable.t1;
import io.reactivex.rxjava3.internal.operators.observable.t2;
import io.reactivex.rxjava3.internal.operators.observable.t3;
import io.reactivex.rxjava3.internal.operators.observable.u;
import io.reactivex.rxjava3.internal.operators.observable.v0;
import io.reactivex.rxjava3.internal.operators.observable.v2;
import io.reactivex.rxjava3.internal.operators.observable.v3;
import io.reactivex.rxjava3.internal.operators.observable.v4;
import io.reactivex.rxjava3.internal.operators.observable.w0;
import io.reactivex.rxjava3.internal.operators.observable.w3;
import io.reactivex.rxjava3.internal.operators.observable.x3;
import io.reactivex.rxjava3.internal.operators.observable.y2;
import io.reactivex.rxjava3.internal.operators.observable.y3;
import io.reactivex.rxjava3.internal.operators.observable.y4;
import io.reactivex.rxjava3.internal.operators.observable.z1;
import io.reactivex.rxjava3.internal.operators.observable.z2;
import io.reactivex.rxjava3.internal.operators.observable.z4;
import io.reactivex.rxjava3.internal.operators.single.m0;
import io.reactivex.rxjava3.internal.util.ArrayListSupplier;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import io.reactivex.rxjava3.internal.util.HashMapSupplier;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.e;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Spliterators;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import p.az1;
import p.co;
import p.du;
import p.he1;
import p.ik;
import p.jm;
import p.nv;
import p.qn;
import p.u4;
import p.ux1;

/* JADX INFO: loaded from: classes.dex */
public abstract class Observable<T> implements ObservableSource<T> {
    public static <T> Observable<T> amb(Iterable<? extends ObservableSource<? extends T>> iterable) {
        Objects.requireNonNull(iterable, "sources is null");
        return new o(null, 0, iterable);
    }

    @SafeVarargs
    public static <T> Observable<T> ambArray(ObservableSource<? extends T>... observableSourceArr) {
        Objects.requireNonNull(observableSourceArr, "sources is null");
        int length = observableSourceArr.length;
        if (length == 0) {
            return empty();
        }
        return length == 1 ? wrap(observableSourceArr[0]) : new o(observableSourceArr, 0, null);
    }

    public static int bufferSize() {
        return Flowable.c;
    }

    public static <T, R> Observable<R> combineLatest(Iterable<? extends ObservableSource<? extends T>> iterable, io.reactivex.rxjava3.functions.o oVar) {
        return combineLatest(iterable, oVar, bufferSize());
    }

    public static <T, R> Observable<R> combineLatestArray(ObservableSource<? extends T>[] observableSourceArr, io.reactivex.rxjava3.functions.o oVar) {
        return combineLatestArray(observableSourceArr, oVar, bufferSize());
    }

    public static <T, R> Observable<R> combineLatestArrayDelayError(ObservableSource<? extends T>[] observableSourceArr, io.reactivex.rxjava3.functions.o oVar) {
        return combineLatestArrayDelayError(observableSourceArr, oVar, bufferSize());
    }

    public static <T, R> Observable<R> combineLatestDelayError(Iterable<? extends ObservableSource<? extends T>> iterable, io.reactivex.rxjava3.functions.o oVar) {
        return combineLatestDelayError(iterable, oVar, bufferSize());
    }

    public static <T> Observable<T> concat(Iterable<? extends ObservableSource<? extends T>> iterable) {
        Objects.requireNonNull(iterable, "sources is null");
        return fromIterable(iterable).concatMapDelayError(k.a, false, bufferSize());
    }

    @SafeVarargs
    public static <T> Observable<T> concatArray(ObservableSource<? extends T>... observableSourceArr) {
        Objects.requireNonNull(observableSourceArr, "sources is null");
        return observableSourceArr.length == 0 ? empty() : observableSourceArr.length == 1 ? wrap(observableSourceArr[0]) : new j0(fromArray(observableSourceArr), k.a, bufferSize(), ErrorMode.e);
    }

    @SafeVarargs
    public static <T> Observable<T> concatArrayDelayError(ObservableSource<? extends T>... observableSourceArr) {
        Objects.requireNonNull(observableSourceArr, "sources is null");
        return observableSourceArr.length == 0 ? empty() : observableSourceArr.length == 1 ? wrap(observableSourceArr[0]) : concatDelayError(fromArray(observableSourceArr));
    }

    @SafeVarargs
    public static <T> Observable<T> concatArrayEager(ObservableSource<? extends T>... observableSourceArr) {
        return concatArrayEager(bufferSize(), bufferSize(), observableSourceArr);
    }

    @SafeVarargs
    public static <T> Observable<T> concatArrayEagerDelayError(ObservableSource<? extends T>... observableSourceArr) {
        return concatArrayEagerDelayError(bufferSize(), bufferSize(), observableSourceArr);
    }

    public static <T> Observable<T> concatDelayError(Iterable<? extends ObservableSource<? extends T>> iterable) {
        Objects.requireNonNull(iterable, "sources is null");
        return concatDelayError(fromIterable(iterable));
    }

    public static <T> Observable<T> concatEager(Iterable<? extends ObservableSource<? extends T>> iterable) {
        return concatEager(iterable, bufferSize(), bufferSize());
    }

    public static <T> Observable<T> concatEagerDelayError(Iterable<? extends ObservableSource<? extends T>> iterable) {
        return concatEagerDelayError(iterable, bufferSize(), bufferSize());
    }

    public static <T> Observable<T> create(ObservableOnSubscribe<T> observableOnSubscribe) {
        Objects.requireNonNull(observableOnSubscribe, "source is null");
        return new r0(0, observableOnSubscribe);
    }

    public static <T> Observable<T> defer(q qVar) {
        Objects.requireNonNull(qVar, "supplier is null");
        return new w0(0, qVar);
    }

    private Observable<T> doOnEach(g gVar, g gVar2, io.reactivex.rxjava3.functions.a aVar, io.reactivex.rxjava3.functions.a aVar2) {
        Objects.requireNonNull(gVar, "onNext is null");
        Objects.requireNonNull(gVar2, "onError is null");
        Objects.requireNonNull(aVar, "onComplete is null");
        Objects.requireNonNull(aVar2, "onAfterTerminate is null");
        return new c2(this, gVar, gVar2, aVar, aVar2, 2);
    }

    public static <T> Observable<T> empty() {
        return o1.c;
    }

    public static <T> Observable<T> error(q qVar) {
        Objects.requireNonNull(qVar, "supplier is null");
        return new w0(1, qVar);
    }

    public static <T> Observable<T> fromAction(io.reactivex.rxjava3.functions.a aVar) {
        Objects.requireNonNull(aVar, "action is null");
        return new t1(0, aVar);
    }

    @SafeVarargs
    public static <T> Observable<T> fromArray(T... tArr) {
        Objects.requireNonNull(tArr, "items is null");
        if (tArr.length == 0) {
            return empty();
        }
        return tArr.length == 1 ? just(tArr[0]) : new r0(1, tArr);
    }

    public static <T> Observable<T> fromCallable(Callable<? extends T> callable) {
        Objects.requireNonNull(callable, "callable is null");
        return new t1(1, callable);
    }

    public static <T> Observable<T> fromCompletable(CompletableSource completableSource) {
        Objects.requireNonNull(completableSource, "completableSource is null");
        return new r0(2, completableSource);
    }

    public static <T> Observable<T> fromCompletionStage(CompletionStage<T> completionStage) {
        Objects.requireNonNull(completionStage, "stage is null");
        return new io.reactivex.rxjava3.internal.jdk8.g(0, completionStage);
    }

    public static <T> Observable<T> fromFuture(Future<? extends T> future) {
        Objects.requireNonNull(future, "future is null");
        return new e5(future, 0L, (TimeUnit) null);
    }

    public static <T> Observable<T> fromIterable(Iterable<? extends T> iterable) {
        Objects.requireNonNull(iterable, "source is null");
        return new r0(3, iterable);
    }

    public static <T> Observable<T> fromMaybe(MaybeSource<T> maybeSource) {
        Objects.requireNonNull(maybeSource, "maybe is null");
        return new h(maybeSource);
    }

    public static <T> Observable<T> fromOptional(Optional<T> optional) {
        Objects.requireNonNull(optional, "optional is null");
        final int i = 1;
        return (Observable) optional.map(new Function() { // from class: p.fr1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                switch (i) {
                    case 0:
                        return ((AnnotatedMethod) obj).a();
                    default:
                        return Observable.just(obj);
                }
            }
        }).orElseGet(new a());
    }

    public static <T> Observable<T> fromPublisher(az1 az1Var) {
        Objects.requireNonNull(az1Var, "publisher is null");
        return new r0(4, az1Var);
    }

    public static <T> Observable<T> fromRunnable(Runnable runnable) {
        Objects.requireNonNull(runnable, "run is null");
        return new t1(2, runnable);
    }

    public static <T> Observable<T> fromSingle(SingleSource<T> singleSource) {
        Objects.requireNonNull(singleSource, "source is null");
        return new m0(singleSource);
    }

    public static <T> Observable<T> fromStream(Stream<T> stream) {
        Objects.requireNonNull(stream, "stream is null");
        return new io.reactivex.rxjava3.internal.jdk8.g(1, stream);
    }

    public static <T> Observable<T> fromSupplier(q qVar) {
        Objects.requireNonNull(qVar, "supplier is null");
        return new t1(3, qVar);
    }

    public static <T> Observable<T> generate(g gVar) {
        Objects.requireNonNull(gVar, "generator is null");
        return generate(k.i, new j2(1, gVar), k.d);
    }

    public static Observable<Long> interval(long j, long j2, TimeUnit timeUnit) {
        return interval(j, j2, timeUnit, e.b);
    }

    public static Observable<Long> intervalRange(long j, long j2, long j3, long j4, TimeUnit timeUnit) {
        return intervalRange(j, j2, j3, j4, timeUnit, e.b);
    }

    public static <T> Observable<T> just(T t) {
        Objects.requireNonNull(t, "item is null");
        return new r2(t);
    }

    public static <T> Observable<T> merge(Iterable<? extends ObservableSource<? extends T>> iterable, int i, int i2) {
        return fromIterable(iterable).flatMap((io.reactivex.rxjava3.functions.o) k.a, false, i, i2);
    }

    @SafeVarargs
    public static <T> Observable<T> mergeArray(int i, int i2, ObservableSource<? extends T>... observableSourceArr) {
        return fromArray(observableSourceArr).flatMap((io.reactivex.rxjava3.functions.o) k.a, false, i, i2);
    }

    @SafeVarargs
    public static <T> Observable<T> mergeArrayDelayError(int i, int i2, ObservableSource<? extends T>... observableSourceArr) {
        return fromArray(observableSourceArr).flatMap((io.reactivex.rxjava3.functions.o) k.a, true, i, i2);
    }

    public static <T> Observable<T> mergeDelayError(Iterable<? extends ObservableSource<? extends T>> iterable) {
        return fromIterable(iterable).flatMap((io.reactivex.rxjava3.functions.o) k.a, true);
    }

    public static <T> Observable<T> never() {
        return b3.c;
    }

    public static Observable<Integer> range(int i, int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException(u4.e("count >= 0 required but it was ", i2));
        }
        if (i2 == 0) {
            return empty();
        }
        if (i2 == 1) {
            return just(Integer.valueOf(i));
        }
        if (((long) i) + ((long) (i2 - 1)) <= 2147483647L) {
            return new i3(i, i2);
        }
        throw new IllegalArgumentException("Integer overflow");
    }

    public static Observable<Long> rangeLong(long j, long j2) {
        if (j2 < 0) {
            throw new IllegalArgumentException(u4.g("count >= 0 required but it was ", j2));
        }
        if (j2 == 0) {
            return empty();
        }
        if (j2 == 1) {
            return just(Long.valueOf(j));
        }
        long j3 = (j2 - 1) + j;
        if (j <= 0 || j3 >= 0) {
            return new j3(j, j2);
        }
        throw new IllegalArgumentException("Overflow! start + count is bigger than Long.MAX_VALUE");
    }

    public static <T> Single<Boolean> sequenceEqual(ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2) {
        return sequenceEqual(observableSource, observableSource2, k.j, bufferSize());
    }

    public static <T> Observable<T> switchOnNext(ObservableSource<? extends ObservableSource<? extends T>> observableSource, int i) {
        Objects.requireNonNull(observableSource, "sources is null");
        k.c(i, "bufferSize");
        return new r4(observableSource, k.a, i, false);
    }

    public static <T> Observable<T> switchOnNextDelayError(ObservableSource<? extends ObservableSource<? extends T>> observableSource) {
        return switchOnNextDelayError(observableSource, bufferSize());
    }

    private Observable<T> timeout0(long j, TimeUnit timeUnit, ObservableSource<? extends T> observableSource, Scheduler scheduler) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new d5(this, j, timeUnit, scheduler, observableSource);
    }

    public static Observable<Long> timer(long j, TimeUnit timeUnit) {
        return timer(j, timeUnit, e.b);
    }

    public static <T> Observable<T> unsafeCreate(ObservableSource<T> observableSource) {
        Objects.requireNonNull(observableSource, "onSubscribe is null");
        if (observableSource instanceof Observable) {
            throw new IllegalArgumentException("unsafeCreate(Observable) should be upgraded");
        }
        return new r0(5, observableSource);
    }

    public static <T, D> Observable<T> using(q qVar, io.reactivex.rxjava3.functions.o oVar, g gVar) {
        return using(qVar, oVar, gVar, true);
    }

    public static <T> Observable<T> wrap(ObservableSource<T> observableSource) {
        Objects.requireNonNull(observableSource, "source is null");
        return observableSource instanceof Observable ? (Observable) observableSource : new r0(5, observableSource);
    }

    public static <T, R> Observable<R> zip(Iterable<? extends ObservableSource<? extends T>> iterable, io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "zipper is null");
        Objects.requireNonNull(iterable, "sources is null");
        return new i0(null, iterable, oVar, bufferSize(), false, 1);
    }

    @SafeVarargs
    public static <T, R> Observable<R> zipArray(io.reactivex.rxjava3.functions.o oVar, boolean z, int i, ObservableSource<? extends T>... observableSourceArr) {
        Objects.requireNonNull(observableSourceArr, "sources is null");
        if (observableSourceArr.length == 0) {
            return empty();
        }
        Objects.requireNonNull(oVar, "zipper is null");
        k.c(i, "bufferSize");
        return new i0(observableSourceArr, null, oVar, i, z, 1);
    }

    public final Single<Boolean> all(p pVar) {
        Objects.requireNonNull(pVar, "predicate is null");
        return new m(this, pVar, 0);
    }

    public final Observable<T> ambWith(ObservableSource<? extends T> observableSource) {
        Objects.requireNonNull(observableSource, "other is null");
        return ambArray(this, observableSource);
    }

    public final Single<Boolean> any(p pVar) {
        Objects.requireNonNull(pVar, "predicate is null");
        return new m(this, pVar, 1);
    }

    public final T blockingFirst() {
        io.reactivex.rxjava3.internal.observers.e eVar = new io.reactivex.rxjava3.internal.observers.e(0);
        subscribe(eVar);
        T t = (T) eVar.a();
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException();
    }

    public final void blockingForEach(g gVar) {
        blockingForEach(gVar, bufferSize());
    }

    public final Iterable<T> blockingIterable() {
        return blockingIterable(bufferSize());
    }

    public final T blockingLast() {
        io.reactivex.rxjava3.internal.observers.e eVar = new io.reactivex.rxjava3.internal.observers.e(1);
        subscribe(eVar);
        T t = (T) eVar.a();
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException();
    }

    public final Iterable<T> blockingLatest() {
        return new d(this, 0);
    }

    public final Iterable<T> blockingMostRecent(T t) {
        Objects.requireNonNull(t, "initialItem is null");
        return new io.reactivex.rxjava3.internal.operators.observable.g(this, t);
    }

    public final Iterable<T> blockingNext() {
        return new d(this, 1);
    }

    public final T blockingSingle() {
        Maybe<T> maybeSingleElement = singleElement();
        maybeSingleElement.getClass();
        f fVar = new f(1);
        maybeSingleElement.subscribe(fVar);
        T t = (T) fVar.a();
        if (t != null) {
            return t;
        }
        throw new NoSuchElementException();
    }

    public final Stream<T> blockingStream() {
        return blockingStream(bufferSize());
    }

    public final void blockingSubscribe() {
        r.subscribe(this);
    }

    public final Observable<List<T>> buffer(long j, long j2, TimeUnit timeUnit) {
        return (Observable<List<T>>) buffer(j, j2, timeUnit, e.b, ArrayListSupplier.c);
    }

    public final Observable<T> cache() {
        return cacheWithInitialCapacity(16);
    }

    public final Observable<T> cacheWithInitialCapacity(int i) {
        k.c(i, "initialCapacity");
        return new e0(this, i);
    }

    public final <U> Observable<U> cast(Class<U> cls) {
        Objects.requireNonNull(cls, "clazz is null");
        return (Observable<U>) map(new ux1(21, cls));
    }

    public final <U> Single<U> collect(q qVar, b bVar) {
        Objects.requireNonNull(qVar, "initialItemSupplier is null");
        Objects.requireNonNull(bVar, "collector is null");
        return new h0(this, qVar, bVar);
    }

    public final <U> Single<U> collectInto(U u, b bVar) {
        Objects.requireNonNull(u, "initialItem is null");
        return collect(new io.reactivex.rxjava3.internal.functions.e(u), bVar);
    }

    public final <R> Observable<R> compose(ObservableTransformer<? super T, ? extends R> observableTransformer) {
        Objects.requireNonNull(observableTransformer, "composer is null");
        return wrap(observableTransformer.apply(this));
    }

    public final <R> Observable<R> concatMap(io.reactivex.rxjava3.functions.o oVar) {
        return concatMap(oVar, 2);
    }

    public final Completable concatMapCompletable(io.reactivex.rxjava3.functions.o oVar) {
        return concatMapCompletable(oVar, 2);
    }

    public final Completable concatMapCompletableDelayError(io.reactivex.rxjava3.functions.o oVar) {
        return concatMapCompletableDelayError(oVar, true, 2);
    }

    public final <R> Observable<R> concatMapDelayError(io.reactivex.rxjava3.functions.o oVar) {
        return concatMapDelayError(oVar, true, bufferSize());
    }

    public final <R> Observable<R> concatMapEager(io.reactivex.rxjava3.functions.o oVar) {
        return concatMapEager(oVar, Integer.MAX_VALUE, bufferSize());
    }

    public final <R> Observable<R> concatMapEagerDelayError(io.reactivex.rxjava3.functions.o oVar, boolean z) {
        return concatMapEagerDelayError(oVar, z, Integer.MAX_VALUE, bufferSize());
    }

    public final <U> Observable<U> concatMapIterable(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new h3(this, oVar, 3);
    }

    public final <R> Observable<R> concatMapMaybe(io.reactivex.rxjava3.functions.o oVar) {
        return concatMapMaybe(oVar, 2);
    }

    public final <R> Observable<R> concatMapMaybeDelayError(io.reactivex.rxjava3.functions.o oVar) {
        return concatMapMaybeDelayError(oVar, true, 2);
    }

    public final <R> Observable<R> concatMapSingle(io.reactivex.rxjava3.functions.o oVar) {
        return concatMapSingle(oVar, 2);
    }

    public final <R> Observable<R> concatMapSingleDelayError(io.reactivex.rxjava3.functions.o oVar) {
        return concatMapSingleDelayError(oVar, true, 2);
    }

    public final <R> Observable<R> concatMapStream(io.reactivex.rxjava3.functions.o oVar) {
        return flatMapStream(oVar);
    }

    public final Observable<T> concatWith(ObservableSource<? extends T> observableSource) {
        Objects.requireNonNull(observableSource, "other is null");
        return concat(this, observableSource);
    }

    public final Single<Boolean> contains(Object obj) {
        Objects.requireNonNull(obj, "item is null");
        return any(new du(4, obj));
    }

    public final Single<Long> count() {
        return new q0(this);
    }

    public final Observable<T> debounce(long j, TimeUnit timeUnit) {
        return debounce(j, timeUnit, e.b);
    }

    public final Observable<T> defaultIfEmpty(T t) {
        Objects.requireNonNull(t, "defaultItem is null");
        return switchIfEmpty(just(t));
    }

    public final Observable<T> delay(long j, TimeUnit timeUnit) {
        return delay(j, timeUnit, e.b, false);
    }

    public final Observable<T> delaySubscription(long j, TimeUnit timeUnit) {
        return delaySubscription(j, timeUnit, e.b);
    }

    public final <R> Observable<R> dematerialize(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "selector is null");
        return new h3(this, oVar, 2);
    }

    public final Observable<T> distinct() {
        return distinct(k.a, k.a());
    }

    public final Observable<T> distinctUntilChanged() {
        return distinctUntilChanged(k.a);
    }

    public final Observable<T> doAfterNext(g gVar) {
        Objects.requireNonNull(gVar, "onAfterNext is null");
        return new io.reactivex.rxjava3.internal.operators.observable.m0(this, gVar, 6);
    }

    public final Observable<T> doAfterTerminate(io.reactivex.rxjava3.functions.a aVar) {
        Objects.requireNonNull(aVar, "onAfterTerminate is null");
        ik ikVar = k.d;
        return doOnEach(ikVar, ikVar, k.c, aVar);
    }

    public final Observable<T> doFinally(io.reactivex.rxjava3.functions.a aVar) {
        Objects.requireNonNull(aVar, "onFinally is null");
        return new io.reactivex.rxjava3.internal.operators.observable.m0(this, aVar, 3);
    }

    public final Observable<T> doOnComplete(io.reactivex.rxjava3.functions.a aVar) {
        ik ikVar = k.d;
        return doOnEach(ikVar, ikVar, aVar, k.c);
    }

    public final Observable<T> doOnDispose(io.reactivex.rxjava3.functions.a aVar) {
        return doOnLifecycle(k.d, aVar);
    }

    public final Observable<T> doOnError(g gVar) {
        ik ikVar = k.d;
        io.reactivex.rxjava3.internal.functions.b bVar = k.c;
        return doOnEach(ikVar, gVar, bVar, bVar);
    }

    public final Observable<T> doOnLifecycle(g gVar, io.reactivex.rxjava3.functions.a aVar) {
        Objects.requireNonNull(gVar, "onSubscribe is null");
        Objects.requireNonNull(aVar, "onDispose is null");
        return new s5(this, gVar, aVar, 5);
    }

    public final Observable<T> doOnNext(g gVar) {
        ik ikVar = k.d;
        io.reactivex.rxjava3.internal.functions.b bVar = k.c;
        return doOnEach(gVar, ikVar, bVar, bVar);
    }

    public final Observable<T> doOnSubscribe(g gVar) {
        return doOnLifecycle(gVar, k.c);
    }

    public final Observable<T> doOnTerminate(io.reactivex.rxjava3.functions.a aVar) {
        Objects.requireNonNull(aVar, "onTerminate is null");
        return doOnEach(k.d, new du(9, aVar), aVar, k.c);
    }

    public final Maybe<T> elementAt(long j) {
        if (j >= 0) {
            return new l1(this, j);
        }
        throw new IndexOutOfBoundsException(u4.g("index >= 0 required but it was ", j));
    }

    public final Single<T> elementAtOrError(long j) {
        if (j >= 0) {
            return new n1(this, j, null);
        }
        throw new IndexOutOfBoundsException(u4.g("index >= 0 required but it was ", j));
    }

    public final Observable<T> filter(p pVar) {
        Objects.requireNonNull(pVar, "predicate is null");
        return new io.reactivex.rxjava3.internal.operators.observable.k(this, pVar, 2);
    }

    public final Single<T> first(T t) {
        return elementAt(0L, t);
    }

    public final Maybe<T> firstElement() {
        return elementAt(0L);
    }

    public final Single<T> firstOrError() {
        return elementAtOrError(0L);
    }

    public final CompletionStage<T> firstOrErrorStage() {
        return (CompletionStage) subscribeWith(new io.reactivex.rxjava3.internal.jdk8.e(false, null, 0 == true ? 1 : 0));
    }

    public final CompletionStage<T> firstStage(T t) {
        return (CompletionStage) subscribeWith(new io.reactivex.rxjava3.internal.jdk8.e(true, t, 0));
    }

    public final <R> Observable<R> flatMap(io.reactivex.rxjava3.functions.o oVar) {
        return flatMap(oVar, false);
    }

    public final Completable flatMapCompletable(io.reactivex.rxjava3.functions.o oVar) {
        return flatMapCompletable(oVar, false);
    }

    public final <U> Observable<U> flatMapIterable(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new h3(this, oVar, 3);
    }

    public final <R> Observable<R> flatMapMaybe(io.reactivex.rxjava3.functions.o oVar) {
        return flatMapMaybe(oVar, false);
    }

    public final <R> Observable<R> flatMapSingle(io.reactivex.rxjava3.functions.o oVar) {
        return flatMapSingle(oVar, false);
    }

    public final <R> Observable<R> flatMapStream(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new io.reactivex.rxjava3.internal.jdk8.f(this, oVar, 0);
    }

    public final Disposable forEach(g gVar) {
        return subscribe(gVar);
    }

    public final Disposable forEachWhile(p pVar) {
        return forEachWhile(pVar, k.f, k.c);
    }

    public final <K> Observable<z1> groupBy(io.reactivex.rxjava3.functions.o oVar) {
        return groupBy(oVar, k.a, false, bufferSize());
    }

    public final <TRight, TLeftEnd, TRightEnd, R> Observable<R> groupJoin(ObservableSource<? extends TRight> observableSource, io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2, c cVar) {
        Objects.requireNonNull(observableSource, "other is null");
        Objects.requireNonNull(oVar, "leftEnd is null");
        Objects.requireNonNull(oVar2, "rightEnd is null");
        Objects.requireNonNull(cVar, "resultSelector is null");
        return new c2(this, observableSource, oVar, oVar2, cVar, 0);
    }

    public final Observable<T> hide() {
        return new o0(this, 2);
    }

    public final Completable ignoreElements() {
        return new g2(this);
    }

    public final Single<Boolean> isEmpty() {
        return all(k.h);
    }

    public final <TRight, TLeftEnd, TRightEnd, R> Observable<R> join(ObservableSource<? extends TRight> observableSource, io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2, c cVar) {
        Objects.requireNonNull(observableSource, "other is null");
        Objects.requireNonNull(oVar, "leftEnd is null");
        Objects.requireNonNull(oVar2, "rightEnd is null");
        Objects.requireNonNull(cVar, "resultSelector is null");
        return new c2(this, observableSource, oVar, oVar2, cVar, 1);
    }

    public final Single<T> last(T t) {
        Objects.requireNonNull(t, "defaultItem is null");
        return new v2(this, 0, t);
    }

    public final Maybe<T> lastElement() {
        return new t2(0, this);
    }

    public final Single<T> lastOrError() {
        return new v2(this, 0, null);
    }

    public final CompletionStage<T> lastOrErrorStage() {
        return (CompletionStage) subscribeWith(new io.reactivex.rxjava3.internal.jdk8.e(false, null, 1));
    }

    public final CompletionStage<T> lastStage(T t) {
        return (CompletionStage) subscribeWith(new io.reactivex.rxjava3.internal.jdk8.e(true, t, true ? 1 : 0));
    }

    public final <R> Observable<R> lift(ObservableOperator<? extends R, ? super T> observableOperator) {
        Objects.requireNonNull(observableOperator, "lifter is null");
        return new io.reactivex.rxjava3.internal.operators.observable.m0(this, observableOperator, 7);
    }

    public final <R> Observable<R> map(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new h3(this, oVar, 4);
    }

    public final <R> Observable<R> mapOptional(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new io.reactivex.rxjava3.internal.jdk8.f(this, oVar, 1);
    }

    public final Observable<Notification<T>> materialize() {
        return new o0(this, 4);
    }

    public final Observable<T> mergeWith(ObservableSource<? extends T> observableSource) {
        Objects.requireNonNull(observableSource, "other is null");
        return merge(this, observableSource);
    }

    public final Observable<T> observeOn(Scheduler scheduler) {
        return observeOn(scheduler, false, bufferSize());
    }

    public final <U> Observable<U> ofType(Class<U> cls) {
        Objects.requireNonNull(cls, "clazz is null");
        return filter(new du(3, cls)).cast(cls);
    }

    public final Observable<T> onErrorComplete() {
        return onErrorComplete(k.g);
    }

    public final Observable<T> onErrorResumeNext(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "fallbackSupplier is null");
        return new h3(this, oVar, 5);
    }

    public final Observable<T> onErrorResumeWith(ObservableSource<? extends T> observableSource) {
        Objects.requireNonNull(observableSource, "fallback is null");
        return onErrorResumeNext(new io.reactivex.rxjava3.internal.functions.e(observableSource));
    }

    public final Observable<T> onErrorReturn(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "itemSupplier is null");
        return new h3(this, oVar, 6);
    }

    public final Observable<T> onErrorReturnItem(T t) {
        Objects.requireNonNull(t, "item is null");
        return onErrorReturn(new io.reactivex.rxjava3.internal.functions.e(t));
    }

    public final Observable<T> onTerminateDetach() {
        return new o0(this, 1);
    }

    public final io.reactivex.rxjava3.observables.a publish() {
        return new g3(this);
    }

    public final Maybe<T> reduce(c cVar) {
        Objects.requireNonNull(cVar, "reducer is null");
        return new io.reactivex.rxjava3.internal.jdk8.o(this, 2, cVar);
    }

    public final <R> Single<R> reduceWith(q qVar, c cVar) {
        Objects.requireNonNull(qVar, "seedSupplier is null");
        Objects.requireNonNull(cVar, "reducer is null");
        return new n(this, qVar, cVar, 2);
    }

    public final Observable<T> repeat() {
        return repeat(Long.MAX_VALUE);
    }

    public final Observable<T> repeatUntil(io.reactivex.rxjava3.functions.e eVar) {
        Objects.requireNonNull(eVar, "stop is null");
        return new io.reactivex.rxjava3.internal.operators.observable.m0(this, eVar, 4);
    }

    public final Observable<T> repeatWhen(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "handler is null");
        return new o3(this, oVar);
    }

    public final io.reactivex.rxjava3.observables.a replay() {
        return v3.c(this, v3.m);
    }

    public final Observable<T> retry() {
        return retry(Long.MAX_VALUE, k.g);
    }

    public final Observable<T> retryUntil(io.reactivex.rxjava3.functions.e eVar) {
        Objects.requireNonNull(eVar, "stop is null");
        return retry(Long.MAX_VALUE, new du(2, null));
    }

    public final Observable<T> retryWhen(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "handler is null");
        return new x3(this, oVar);
    }

    public final void safeSubscribe(Observer<? super T> observer) {
        Objects.requireNonNull(observer, "observer is null");
        if (observer instanceof io.reactivex.rxjava3.observers.c) {
            subscribe(observer);
        } else {
            subscribe(new io.reactivex.rxjava3.observers.c(observer));
        }
    }

    public final Observable<T> sample(long j, TimeUnit timeUnit) {
        return sample(j, timeUnit, e.b);
    }

    public final Observable<T> scan(c cVar) {
        Objects.requireNonNull(cVar, "accumulator is null");
        return new io.reactivex.rxjava3.internal.operators.observable.m0(this, cVar, 8);
    }

    public final <R> Observable<R> scanWith(q qVar, c cVar) {
        Objects.requireNonNull(qVar, "seedSupplier is null");
        Objects.requireNonNull(cVar, "accumulator is null");
        return new s5(this, qVar, cVar, 6, 0);
    }

    public final Observable<T> serialize() {
        return new o0(this, 5);
    }

    public final Observable<T> share() {
        io.reactivex.rxjava3.observables.a aVarPublish = publish();
        aVarPublish.getClass();
        return new m3(aVarPublish);
    }

    public final Single<T> single(T t) {
        Objects.requireNonNull(t, "defaultItem is null");
        return new v2(this, 1, t);
    }

    public final Maybe<T> singleElement() {
        return new t2(1, this);
    }

    public final Single<T> singleOrError() {
        return new v2(this, 1, null);
    }

    public final CompletionStage<T> singleOrErrorStage() {
        return (CompletionStage) subscribeWith(new io.reactivex.rxjava3.internal.jdk8.e(false, null, 2));
    }

    public final CompletionStage<T> singleStage(T t) {
        return (CompletionStage) subscribeWith(new io.reactivex.rxjava3.internal.jdk8.e(true, t, 2));
    }

    public final Observable<T> skip(long j) {
        if (j >= 0) {
            return j == 0 ? this : new n3(this, j, 1);
        }
        throw new IllegalArgumentException(u4.g("count >= 0 expected but it was ", j));
    }

    public final Observable<T> skipLast(long j, TimeUnit timeUnit) {
        return skipLast(j, timeUnit, e.d, false, bufferSize());
    }

    public final <U> Observable<T> skipUntil(ObservableSource<U> observableSource) {
        Objects.requireNonNull(observableSource, "other is null");
        return new n4(this, observableSource, 0);
    }

    public final Observable<T> skipWhile(p pVar) {
        Objects.requireNonNull(pVar, "predicate is null");
        return new io.reactivex.rxjava3.internal.operators.observable.k(this, pVar, 4);
    }

    public final Observable<T> sorted() {
        return toList().toObservable().map(new ux1(22, k.b())).flatMapIterable(k.a);
    }

    public final Observable<T> startWith(CompletableSource completableSource) {
        Objects.requireNonNull(completableSource, "other is null");
        Object eVar = completableSource instanceof Completable ? (Completable) completableSource : new io.reactivex.rxjava3.internal.operators.completable.e(6, completableSource);
        return concat(eVar instanceof io.reactivex.rxjava3.internal.fuseable.d ? ((io.reactivex.rxjava3.internal.fuseable.d) eVar).a() : new jm(2, eVar), this);
    }

    @SafeVarargs
    public final Observable<T> startWithArray(T... tArr) {
        Observable observableFromArray = fromArray(tArr);
        return observableFromArray == empty() ? this : concatArray(observableFromArray, this);
    }

    public final Observable<T> startWithItem(T t) {
        return concatArray(just(t), this);
    }

    public final Observable<T> startWithIterable(Iterable<? extends T> iterable) {
        return concatArray(fromIterable(iterable), this);
    }

    public final Disposable subscribe() {
        return subscribe(k.d, k.f, k.c);
    }

    public abstract void subscribeActual(Observer<? super T> observer);

    public final Observable<T> subscribeOn(Scheduler scheduler) {
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new q4(this, scheduler, 0);
    }

    public final <E extends Observer<? super T>> E subscribeWith(E e) {
        subscribe(e);
        return e;
    }

    public final Observable<T> switchIfEmpty(ObservableSource<? extends T> observableSource) {
        Objects.requireNonNull(observableSource, "other is null");
        return new n4(this, observableSource, 1);
    }

    public final <R> Observable<R> switchMap(io.reactivex.rxjava3.functions.o oVar) {
        return switchMap(oVar, bufferSize());
    }

    public final Completable switchMapCompletable(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new io.reactivex.rxjava3.internal.operators.mixed.h(this, oVar, false);
    }

    public final Completable switchMapCompletableDelayError(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new io.reactivex.rxjava3.internal.operators.mixed.h(this, oVar, true);
    }

    public final <R> Observable<R> switchMapDelayError(io.reactivex.rxjava3.functions.o oVar) {
        return switchMapDelayError(oVar, bufferSize());
    }

    public final <R> Observable<R> switchMapMaybe(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new i(this, oVar, false, 0 == true ? 1 : 0);
    }

    public final <R> Observable<R> switchMapMaybeDelayError(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new i(this, oVar, true, 0);
    }

    public final <R> Observable<R> switchMapSingle(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new i(this, oVar, false, 1);
    }

    public final <R> Observable<R> switchMapSingleDelayError(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new i(this, oVar, true, true ? 1 : 0);
    }

    public final Observable<T> take(long j) {
        if (j >= 0) {
            return new n3(this, j, 2);
        }
        throw new IllegalArgumentException(u4.g("count >= 0 required but it was ", j));
    }

    public final Observable<T> takeLast(long j, long j2, TimeUnit timeUnit) {
        return takeLast(j, j2, timeUnit, e.d, false, bufferSize());
    }

    public final <U> Observable<T> takeUntil(ObservableSource<U> observableSource) {
        Objects.requireNonNull(observableSource, "other is null");
        return new v4(this, observableSource);
    }

    public final Observable<T> takeWhile(p pVar) {
        Objects.requireNonNull(pVar, "predicate is null");
        return new io.reactivex.rxjava3.internal.operators.observable.k(this, pVar, 6);
    }

    public final io.reactivex.rxjava3.observers.e test() {
        io.reactivex.rxjava3.observers.e eVar = new io.reactivex.rxjava3.observers.e();
        subscribe(eVar);
        return eVar;
    }

    public final Observable<T> throttleFirst(long j, TimeUnit timeUnit) {
        return throttleFirst(j, timeUnit, e.b);
    }

    public final Observable<T> throttleLast(long j, TimeUnit timeUnit) {
        return sample(j, timeUnit);
    }

    public final Observable<T> throttleLatest(long j, TimeUnit timeUnit) {
        return throttleLatest(j, timeUnit, e.b, false);
    }

    public final Observable<T> throttleWithTimeout(long j, TimeUnit timeUnit) {
        return debounce(j, timeUnit);
    }

    public final Observable<io.reactivex.rxjava3.schedulers.f> timeInterval(TimeUnit timeUnit) {
        return timeInterval(timeUnit, e.b);
    }

    public final Observable<T> timeout(long j, TimeUnit timeUnit) {
        return timeout0(j, timeUnit, null, e.b);
    }

    public final Observable<io.reactivex.rxjava3.schedulers.f> timestamp(TimeUnit timeUnit) {
        return timestamp(timeUnit, e.b);
    }

    public final <R> R to(ObservableConverter<T, ? extends R> observableConverter) {
        Objects.requireNonNull(observableConverter, "converter is null");
        u4.z(observableConverter);
        throw null;
    }

    public final Flowable<T> toFlowable(BackpressureStrategy backpressureStrategy) {
        Objects.requireNonNull(backpressureStrategy, "strategy is null");
        s sVar = new s(2, this);
        int iOrdinal = backpressureStrategy.ordinal();
        if (iOrdinal == 0) {
            return sVar;
        }
        int i = 1;
        if (iOrdinal == 1) {
            return new c0(sVar, 0);
        }
        if (iOrdinal == 3) {
            return new b0(sVar);
        }
        if (iOrdinal == 4) {
            return new c0(sVar, i);
        }
        int i2 = Flowable.c;
        k.c(i2, "capacity");
        return new a0(sVar, i2);
    }

    public final Future<T> toFuture() {
        return (Future) subscribeWith(new io.reactivex.rxjava3.internal.observers.i());
    }

    public final Single<List<T>> toList() {
        return toList(16);
    }

    public final <K> Single<Map<K, T>> toMap(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "keySelector is null");
        return (Single<Map<K, T>>) collect(HashMapSupplier.c, new io.reactivex.rxjava3.internal.functions.h(oVar));
    }

    public final <K> Single<Map<K, Collection<T>>> toMultimap(io.reactivex.rxjava3.functions.o oVar) {
        return (Single<Map<K, Collection<T>>>) toMultimap(oVar, k.a, HashMapSupplier.c, ArrayListSupplier.c);
    }

    public final Single<List<T>> toSortedList() {
        return toSortedList(k.b());
    }

    public final Observable<T> unsubscribeOn(Scheduler scheduler) {
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new q4(this, scheduler, 1);
    }

    public final Observable<Observable<T>> window(long j, long j2, TimeUnit timeUnit) {
        return window(j, j2, timeUnit, e.b, bufferSize());
    }

    public final <U, R> Observable<R> withLatestFrom(ObservableSource<? extends U> observableSource, c cVar) {
        Objects.requireNonNull(observableSource, "other is null");
        Objects.requireNonNull(cVar, "combiner is null");
        return new s5(this, cVar, observableSource, 0);
    }

    public final <U, R> Observable<R> zipWith(Iterable<U> iterable, c cVar) {
        Objects.requireNonNull(iterable, "other is null");
        Objects.requireNonNull(cVar, "zipper is null");
        return new qn(this, iterable, cVar, 2);
    }

    public static <T, R> Observable<R> combineLatest(Iterable<? extends ObservableSource<? extends T>> iterable, io.reactivex.rxjava3.functions.o oVar, int i) {
        Objects.requireNonNull(iterable, "sources is null");
        Objects.requireNonNull(oVar, "combiner is null");
        k.c(i, "bufferSize");
        return new i0(null, iterable, oVar, i << 1, false, 0);
    }

    public static <T, R> Observable<R> combineLatestArray(ObservableSource<? extends T>[] observableSourceArr, io.reactivex.rxjava3.functions.o oVar, int i) {
        Objects.requireNonNull(observableSourceArr, "sources is null");
        if (observableSourceArr.length == 0) {
            return empty();
        }
        Objects.requireNonNull(oVar, "combiner is null");
        k.c(i, "bufferSize");
        return new i0(observableSourceArr, null, oVar, i << 1, false, 0);
    }

    public static <T, R> Observable<R> combineLatestArrayDelayError(ObservableSource<? extends T>[] observableSourceArr, io.reactivex.rxjava3.functions.o oVar, int i) {
        Objects.requireNonNull(observableSourceArr, "sources is null");
        Objects.requireNonNull(oVar, "combiner is null");
        k.c(i, "bufferSize");
        if (observableSourceArr.length == 0) {
            return empty();
        }
        return new i0(observableSourceArr, null, oVar, i << 1, true, 0);
    }

    public static <T, R> Observable<R> combineLatestDelayError(Iterable<? extends ObservableSource<? extends T>> iterable, io.reactivex.rxjava3.functions.o oVar, int i) {
        Objects.requireNonNull(iterable, "sources is null");
        Objects.requireNonNull(oVar, "combiner is null");
        k.c(i, "bufferSize");
        return new i0(null, iterable, oVar, i << 1, true, 0);
    }

    @SafeVarargs
    public static <T> Observable<T> concatArrayEager(int i, int i2, ObservableSource<? extends T>... observableSourceArr) {
        return fromArray(observableSourceArr).concatMapEagerDelayError(k.a, false, i, i2);
    }

    @SafeVarargs
    public static <T> Observable<T> concatArrayEagerDelayError(int i, int i2, ObservableSource<? extends T>... observableSourceArr) {
        return fromArray(observableSourceArr).concatMapEagerDelayError(k.a, true, i, i2);
    }

    public static <T> Observable<T> concatEager(Iterable<? extends ObservableSource<? extends T>> iterable, int i, int i2) {
        return fromIterable(iterable).concatMapEagerDelayError(k.a, false, i, i2);
    }

    public static <T> Observable<T> concatEagerDelayError(Iterable<? extends ObservableSource<? extends T>> iterable, int i, int i2) {
        return fromIterable(iterable).concatMapEagerDelayError(k.a, true, i, i2);
    }

    public static <T> Observable<T> merge(Iterable<? extends ObservableSource<? extends T>> iterable) {
        return fromIterable(iterable).flatMap(k.a);
    }

    @SafeVarargs
    public static <T> Observable<T> mergeArray(ObservableSource<? extends T>... observableSourceArr) {
        return fromArray(observableSourceArr).flatMap(k.a, observableSourceArr.length);
    }

    @SafeVarargs
    public static <T> Observable<T> mergeArrayDelayError(ObservableSource<? extends T>... observableSourceArr) {
        return fromArray(observableSourceArr).flatMap((io.reactivex.rxjava3.functions.o) k.a, true, observableSourceArr.length);
    }

    public static <T> Observable<T> mergeDelayError(Iterable<? extends ObservableSource<? extends T>> iterable, int i, int i2) {
        return fromIterable(iterable).flatMap((io.reactivex.rxjava3.functions.o) k.a, true, i, i2);
    }

    public static <T> Single<Boolean> sequenceEqual(ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2, io.reactivex.rxjava3.functions.d dVar) {
        return sequenceEqual(observableSource, observableSource2, dVar, bufferSize());
    }

    public static <T> Observable<T> switchOnNextDelayError(ObservableSource<? extends ObservableSource<? extends T>> observableSource, int i) {
        Objects.requireNonNull(observableSource, "sources is null");
        k.c(i, "bufferSize");
        return new r4(observableSource, k.a, i, true);
    }

    public static <T, D> Observable<T> using(q qVar, io.reactivex.rxjava3.functions.o oVar, g gVar, boolean z) {
        Objects.requireNonNull(qVar, "resourceSupplier is null");
        Objects.requireNonNull(oVar, "sourceSupplier is null");
        Objects.requireNonNull(gVar, "resourceCleanup is null");
        return new i5(qVar, oVar, gVar, z);
    }

    public final void blockingForEach(g gVar, int i) {
        Objects.requireNonNull(gVar, "onNext is null");
        Iterator<T> it = blockingIterable(i).iterator();
        while (it.hasNext()) {
            try {
                gVar.accept(it.next());
            } catch (Throwable th) {
                he1.R(th);
                ((Disposable) it).dispose();
                throw io.reactivex.rxjava3.internal.util.c.f(th);
            }
        }
    }

    public final Iterable<T> blockingIterable(int i) {
        k.c(i, "capacityHint");
        return new io.reactivex.rxjava3.internal.operators.observable.b(this, i);
    }

    public final Stream<T> blockingStream(int i) {
        Iterator<T> it = blockingIterable(i).iterator();
        Stream stream = StreamSupport.stream(Spliterators.spliteratorUnknownSize(it, 0), false);
        Disposable disposable = (Disposable) it;
        disposable.getClass();
        return (Stream) stream.onClose(new nv(15, disposable));
    }

    public final void blockingSubscribe(g gVar) {
        r.subscribe(this, gVar, k.f, k.c);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Observable<R> concatMap(io.reactivex.rxjava3.functions.o oVar, int i) {
        Objects.requireNonNull(oVar, "mapper is null");
        k.c(i, "bufferSize");
        if (!(this instanceof io.reactivex.rxjava3.operators.e)) {
            return new j0(this, oVar, i, ErrorMode.c);
        }
        Object obj = ((io.reactivex.rxjava3.operators.e) this).get();
        return obj == null ? empty() : new o(obj, 3, oVar);
    }

    public final Completable concatMapCompletable(io.reactivex.rxjava3.functions.o oVar, int i) {
        Objects.requireNonNull(oVar, "mapper is null");
        k.c(i, "capacityHint");
        return new io.reactivex.rxjava3.internal.operators.mixed.b(this, oVar, ErrorMode.c, i, 1);
    }

    public final Completable concatMapCompletableDelayError(io.reactivex.rxjava3.functions.o oVar, boolean z) {
        return concatMapCompletableDelayError(oVar, z, 2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Observable<R> concatMapDelayError(io.reactivex.rxjava3.functions.o oVar, boolean z, int i) {
        Objects.requireNonNull(oVar, "mapper is null");
        k.c(i, "bufferSize");
        if (!(this instanceof io.reactivex.rxjava3.operators.e)) {
            return new j0(this, oVar, i, z ? ErrorMode.k : ErrorMode.e);
        }
        Object obj = ((io.reactivex.rxjava3.operators.e) this).get();
        return obj == null ? empty() : new o(obj, 3, oVar);
    }

    public final <R> Observable<R> concatMapEager(io.reactivex.rxjava3.functions.o oVar, int i, int i2) {
        Objects.requireNonNull(oVar, "mapper is null");
        k.c(i, "maxConcurrency");
        k.c(i2, "bufferSize");
        return new k0(this, oVar, ErrorMode.c, i, i2);
    }

    public final <R> Observable<R> concatMapEagerDelayError(io.reactivex.rxjava3.functions.o oVar, boolean z, int i, int i2) {
        Objects.requireNonNull(oVar, "mapper is null");
        k.c(i, "maxConcurrency");
        k.c(i2, "bufferSize");
        return new k0(this, oVar, z ? ErrorMode.k : ErrorMode.e, i, i2);
    }

    public final <R> Observable<R> concatMapMaybe(io.reactivex.rxjava3.functions.o oVar, int i) {
        Objects.requireNonNull(oVar, "mapper is null");
        k.c(i, "bufferSize");
        return new io.reactivex.rxjava3.internal.operators.mixed.f(this, oVar, ErrorMode.c, i, 0);
    }

    public final <R> Observable<R> concatMapMaybeDelayError(io.reactivex.rxjava3.functions.o oVar, boolean z) {
        return concatMapMaybeDelayError(oVar, z, 2);
    }

    public final <R> Observable<R> concatMapSingle(io.reactivex.rxjava3.functions.o oVar, int i) {
        Objects.requireNonNull(oVar, "mapper is null");
        k.c(i, "bufferSize");
        return new io.reactivex.rxjava3.internal.operators.mixed.f(this, oVar, ErrorMode.c, i, 1);
    }

    public final <R> Observable<R> concatMapSingleDelayError(io.reactivex.rxjava3.functions.o oVar, boolean z) {
        return concatMapSingleDelayError(oVar, z, 2);
    }

    public final <K> Observable<T> distinct(io.reactivex.rxjava3.functions.o oVar) {
        return distinct(oVar, k.a());
    }

    public final <K> Observable<T> distinctUntilChanged(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "keySelector is null");
        return new s5(this, oVar, k.j, 4);
    }

    public final <R> Observable<R> flatMap(io.reactivex.rxjava3.functions.o oVar, boolean z) {
        return flatMap(oVar, z, Integer.MAX_VALUE);
    }

    public final Completable flatMapCompletable(io.reactivex.rxjava3.functions.o oVar, boolean z) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new r1(this, oVar, z);
    }

    public final <R> Observable<R> flatMapMaybe(io.reactivex.rxjava3.functions.o oVar, boolean z) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new q1(this, oVar, z, 1);
    }

    public final <R> Observable<R> flatMapSingle(io.reactivex.rxjava3.functions.o oVar, boolean z) {
        Objects.requireNonNull(oVar, "mapper is null");
        return new q1(this, oVar, z, 2);
    }

    public final Disposable forEachWhile(p pVar, g gVar) {
        return forEachWhile(pVar, gVar, k.c);
    }

    public final <K> Observable<z1> groupBy(io.reactivex.rxjava3.functions.o oVar, boolean z) {
        return groupBy(oVar, k.a, z, bufferSize());
    }

    public final Observable<T> observeOn(Scheduler scheduler, boolean z) {
        return observeOn(scheduler, z, bufferSize());
    }

    public final Observable<T> onErrorComplete(p pVar) {
        Objects.requireNonNull(pVar, "predicate is null");
        return new io.reactivex.rxjava3.internal.operators.observable.k(this, pVar, 3);
    }

    public final <R> Observable<R> publish(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "selector is null");
        return new h3(this, oVar, 0);
    }

    public final Observable<T> repeat(long j) {
        if (j >= 0) {
            return j == 0 ? empty() : new n3(this, j, 0);
        }
        throw new IllegalArgumentException(u4.g("times >= 0 required but it was ", j));
    }

    public final <R> Observable<R> replay(io.reactivex.rxjava3.functions.o oVar, int i, long j, TimeUnit timeUnit) {
        return replay(oVar, i, j, timeUnit, e.b);
    }

    public final Observable<T> retry(io.reactivex.rxjava3.functions.d dVar) {
        Objects.requireNonNull(dVar, "predicate is null");
        return new io.reactivex.rxjava3.internal.operators.observable.m0(this, dVar, 5);
    }

    public final Disposable subscribe(g gVar) {
        return subscribe(gVar, k.f, k.c);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Observable<R> switchMap(io.reactivex.rxjava3.functions.o oVar, int i) {
        Objects.requireNonNull(oVar, "mapper is null");
        k.c(i, "bufferSize");
        if (!(this instanceof io.reactivex.rxjava3.operators.e)) {
            return new r4(this, oVar, i, false);
        }
        Object obj = ((io.reactivex.rxjava3.operators.e) this).get();
        return obj == null ? empty() : new o(obj, 3, oVar);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Observable<R> switchMapDelayError(io.reactivex.rxjava3.functions.o oVar, int i) {
        Objects.requireNonNull(oVar, "mapper is null");
        k.c(i, "bufferSize");
        if (!(this instanceof io.reactivex.rxjava3.operators.e)) {
            return new r4(this, oVar, i, true);
        }
        Object obj = ((io.reactivex.rxjava3.operators.e) this).get();
        return obj == null ? empty() : new o(obj, 3, oVar);
    }

    public final Observable<T> throttleLast(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return sample(j, timeUnit, scheduler);
    }

    public final Observable<T> throttleWithTimeout(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return debounce(j, timeUnit, scheduler);
    }

    public final Single<List<T>> toList(int i) {
        k.c(i, "capacityHint");
        return new io.reactivex.rxjava3.internal.jdk8.d(this, i);
    }

    public final <K, V> Single<Map<K, Collection<V>>> toMultimap(io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2) {
        return toMultimap(oVar, oVar2, HashMapSupplier.c, ArrayListSupplier.c);
    }

    public final Single<List<T>> toSortedList(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator, "comparator is null");
        return (Single<List<T>>) toList().map(new ux1(22, comparator));
    }

    public static <T> Observable<T> concat(ObservableSource<? extends ObservableSource<? extends T>> observableSource) {
        return concat(observableSource, bufferSize());
    }

    public static <T> Observable<T> concatDelayError(ObservableSource<? extends ObservableSource<? extends T>> observableSource) {
        return concatDelayError(observableSource, bufferSize(), true);
    }

    public static <T> Observable<T> concatEager(ObservableSource<? extends ObservableSource<? extends T>> observableSource) {
        return concatEager(observableSource, bufferSize(), bufferSize());
    }

    public static <T> Observable<T> concatEagerDelayError(ObservableSource<? extends ObservableSource<? extends T>> observableSource) {
        return concatEagerDelayError(observableSource, bufferSize(), bufferSize());
    }

    public static <T> Observable<T> error(Throwable th) {
        Objects.requireNonNull(th, "throwable is null");
        return error(new io.reactivex.rxjava3.internal.functions.e(th));
    }

    public static <T> Observable<T> fromFuture(Future<? extends T> future, long j, TimeUnit timeUnit) {
        Objects.requireNonNull(future, "future is null");
        Objects.requireNonNull(timeUnit, "unit is null");
        return new e5(future, j, timeUnit);
    }

    public static Observable<Long> interval(long j, TimeUnit timeUnit) {
        return interval(j, j, timeUnit, e.b);
    }

    public static Observable<Long> intervalRange(long j, long j2, long j3, long j4, TimeUnit timeUnit, Scheduler scheduler) {
        if (j2 < 0) {
            throw new IllegalArgumentException(u4.g("count >= 0 required but it was ", j2));
        }
        if (j2 == 0) {
            return empty().delay(j3, timeUnit, scheduler);
        }
        long j5 = (j2 - 1) + j;
        if (j > 0 && j5 < 0) {
            throw new IllegalArgumentException("Overflow! start + count is bigger than Long.MAX_VALUE");
        }
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new q2(j, j5, Math.max(0L, j3), Math.max(0L, j4), timeUnit, scheduler);
    }

    public static <T> Observable<T> just(T t, T t2) {
        Objects.requireNonNull(t, "item1 is null");
        Objects.requireNonNull(t2, "item2 is null");
        return fromArray(t, t2);
    }

    public static <T> Observable<T> merge(Iterable<? extends ObservableSource<? extends T>> iterable, int i) {
        return fromIterable(iterable).flatMap(k.a, i);
    }

    public static <T> Observable<T> mergeDelayError(Iterable<? extends ObservableSource<? extends T>> iterable, int i) {
        return fromIterable(iterable).flatMap((io.reactivex.rxjava3.functions.o) k.a, true, i);
    }

    public static <T> Single<Boolean> sequenceEqual(ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2, io.reactivex.rxjava3.functions.d dVar, int i) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(dVar, "isEqual is null");
        k.c(i, "bufferSize");
        return new f4(observableSource, observableSource2, dVar, i);
    }

    public static Observable<Long> timer(long j, TimeUnit timeUnit, Scheduler scheduler) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new e5(Math.max(j, 0L), timeUnit, scheduler);
    }

    public final void blockingSubscribe(g gVar, g gVar2) {
        r.subscribe(this, gVar, gVar2, k.c);
    }

    public final Observable<List<T>> buffer(long j, TimeUnit timeUnit) {
        return buffer(j, timeUnit, e.b, Integer.MAX_VALUE);
    }

    public final Completable concatMapCompletableDelayError(io.reactivex.rxjava3.functions.o oVar, boolean z, int i) {
        Objects.requireNonNull(oVar, "mapper is null");
        k.c(i, "bufferSize");
        return new io.reactivex.rxjava3.internal.operators.mixed.b(this, oVar, z ? ErrorMode.k : ErrorMode.e, i, 1);
    }

    public final <R> Observable<R> concatMapMaybeDelayError(io.reactivex.rxjava3.functions.o oVar, boolean z, int i) {
        Objects.requireNonNull(oVar, "mapper is null");
        k.c(i, "bufferSize");
        return new io.reactivex.rxjava3.internal.operators.mixed.f(this, oVar, z ? ErrorMode.k : ErrorMode.e, i, 0);
    }

    public final <R> Observable<R> concatMapSingleDelayError(io.reactivex.rxjava3.functions.o oVar, boolean z, int i) {
        Objects.requireNonNull(oVar, "mapper is null");
        k.c(i, "bufferSize");
        return new io.reactivex.rxjava3.internal.operators.mixed.f(this, oVar, z ? ErrorMode.k : ErrorMode.e, i, 1);
    }

    public final Observable<T> concatWith(SingleSource<? extends T> singleSource) {
        Objects.requireNonNull(singleSource, "other is null");
        return new io.reactivex.rxjava3.internal.operators.observable.m0(this, singleSource, 2);
    }

    public final <U> Observable<T> debounce(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "debounceIndicator is null");
        return new h3(this, oVar, 1);
    }

    public final Observable<T> delay(long j, TimeUnit timeUnit, boolean z) {
        return delay(j, timeUnit, e.b, z);
    }

    public final <U> Observable<T> delaySubscription(ObservableSource<U> observableSource) {
        Objects.requireNonNull(observableSource, "subscriptionIndicator is null");
        return new o(this, 1, observableSource);
    }

    public final <K> Observable<T> distinct(io.reactivex.rxjava3.functions.o oVar, q qVar) {
        Objects.requireNonNull(oVar, "keySelector is null");
        Objects.requireNonNull(qVar, "collectionSupplier is null");
        return new s5(this, oVar, qVar, 3);
    }

    public final <R> Observable<R> flatMap(io.reactivex.rxjava3.functions.o oVar, boolean z, int i) {
        return flatMap(oVar, z, i, bufferSize());
    }

    public final <U, V> Observable<V> flatMapIterable(io.reactivex.rxjava3.functions.o oVar, c cVar) {
        Objects.requireNonNull(oVar, "mapper is null");
        Objects.requireNonNull(cVar, "combiner is null");
        return (Observable<V>) flatMap(new j2(0, oVar), cVar, false, bufferSize(), bufferSize());
    }

    public final Disposable forEachWhile(p pVar, g gVar, io.reactivex.rxjava3.functions.a aVar) {
        Objects.requireNonNull(pVar, "onNext is null");
        Objects.requireNonNull(gVar, "onError is null");
        Objects.requireNonNull(aVar, "onComplete is null");
        ForEachWhileObserver forEachWhileObserver = new ForEachWhileObserver(pVar, gVar, aVar);
        subscribe(forEachWhileObserver);
        return forEachWhileObserver;
    }

    public final <K, V> Observable<z1> groupBy(io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2) {
        return groupBy(oVar, oVar2, false, bufferSize());
    }

    public final Observable<T> mergeWith(SingleSource<? extends T> singleSource) {
        Objects.requireNonNull(singleSource, "other is null");
        return new a3(this, singleSource);
    }

    public final Observable<T> observeOn(Scheduler scheduler, boolean z, int i) {
        Objects.requireNonNull(scheduler, "scheduler is null");
        k.c(i, "bufferSize");
        return new c3(this, scheduler, z, i);
    }

    public final <R> Single<R> reduce(R r, c cVar) {
        Objects.requireNonNull(r, "seed is null");
        Objects.requireNonNull(cVar, "reducer is null");
        return new n(this, r, cVar);
    }

    public final Observable<T> sample(long j, TimeUnit timeUnit, boolean z) {
        return sample(j, timeUnit, e.b, z);
    }

    public final <R> Observable<R> scan(R r, c cVar) {
        Objects.requireNonNull(r, "initialValue is null");
        return scanWith(new io.reactivex.rxjava3.internal.functions.e(r), cVar);
    }

    public final Observable<T> skipLast(long j, TimeUnit timeUnit, boolean z) {
        return skipLast(j, timeUnit, e.d, z, bufferSize());
    }

    public final Disposable subscribe(g gVar, g gVar2) {
        return subscribe(gVar, gVar2, k.c);
    }

    public final Observable<T> takeLast(long j, TimeUnit timeUnit) {
        return takeLast(j, timeUnit, e.d, false, bufferSize());
    }

    public final Observable<T> takeUntil(p pVar) {
        Objects.requireNonNull(pVar, "stopPredicate is null");
        return new io.reactivex.rxjava3.internal.operators.observable.k(this, pVar, 5);
    }

    public final io.reactivex.rxjava3.observers.e test(boolean z) {
        io.reactivex.rxjava3.observers.e eVar = new io.reactivex.rxjava3.observers.e();
        if (z) {
            eVar.dispose();
        }
        subscribe(eVar);
        return eVar;
    }

    public final Observable<T> throttleFirst(long j, TimeUnit timeUnit, Scheduler scheduler) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new y4(j, timeUnit, this, scheduler);
    }

    public final Observable<T> throttleLatest(long j, TimeUnit timeUnit, boolean z) {
        return throttleLatest(j, timeUnit, e.b, z);
    }

    public final Observable<io.reactivex.rxjava3.schedulers.f> timeInterval() {
        return timeInterval(TimeUnit.MILLISECONDS, e.b);
    }

    public final <V> Observable<T> timeout(io.reactivex.rxjava3.functions.o oVar) {
        return timeout0(null, oVar, null);
    }

    public final Observable<io.reactivex.rxjava3.schedulers.f> timestamp() {
        return timestamp(TimeUnit.MILLISECONDS, e.b);
    }

    public final <K, V> Single<Map<K, Collection<V>>> toMultimap(io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2, q qVar, io.reactivex.rxjava3.functions.o oVar3) {
        Objects.requireNonNull(oVar, "keySelector is null");
        Objects.requireNonNull(oVar2, "valueSelector is null");
        Objects.requireNonNull(qVar, "mapSupplier is null");
        Objects.requireNonNull(oVar3, "collectionFactory is null");
        return (Single<Map<K, Collection<V>>>) collect(qVar, new j(oVar3, oVar2, oVar));
    }

    public final Observable<Observable<T>> window(long j, TimeUnit timeUnit) {
        return window(j, timeUnit, e.b, Long.MAX_VALUE, false);
    }

    public static <T> Observable<T> concat(ObservableSource<? extends ObservableSource<? extends T>> observableSource, int i) {
        Objects.requireNonNull(observableSource, "sources is null");
        k.c(i, "bufferSize");
        return new j0(observableSource, k.a, i, ErrorMode.c);
    }

    public static <T> Observable<T> concatDelayError(ObservableSource<? extends ObservableSource<? extends T>> observableSource, int i, boolean z) {
        Objects.requireNonNull(observableSource, "sources is null");
        k.c(i, "bufferSize is null");
        return new j0(observableSource, k.a, i, z ? ErrorMode.k : ErrorMode.e);
    }

    public static <T> Observable<T> concatEager(ObservableSource<? extends ObservableSource<? extends T>> observableSource, int i, int i2) {
        return wrap(observableSource).concatMapEager(k.a, i, i2);
    }

    public static <T> Observable<T> concatEagerDelayError(ObservableSource<? extends ObservableSource<? extends T>> observableSource, int i, int i2) {
        return wrap(observableSource).concatMapEagerDelayError(k.a, true, i, i2);
    }

    public static <T, S> Observable<T> generate(q qVar, b bVar) {
        Objects.requireNonNull(bVar, "generator is null");
        return generate(qVar, new j2(0, bVar), k.d);
    }

    public static <T> Observable<T> merge(ObservableSource<? extends ObservableSource<? extends T>> observableSource) {
        Objects.requireNonNull(observableSource, "sources is null");
        return new p1(observableSource, k.a, false, Integer.MAX_VALUE, bufferSize());
    }

    public static <T> Observable<T> mergeDelayError(ObservableSource<? extends ObservableSource<? extends T>> observableSource) {
        Objects.requireNonNull(observableSource, "sources is null");
        return new p1(observableSource, k.a, true, Integer.MAX_VALUE, bufferSize());
    }

    public static <T> Observable<T> switchOnNext(ObservableSource<? extends ObservableSource<? extends T>> observableSource) {
        return switchOnNext(observableSource, bufferSize());
    }

    private <U, V> Observable<T> timeout0(ObservableSource<U> observableSource, io.reactivex.rxjava3.functions.o oVar, ObservableSource<? extends T> observableSource2) {
        Objects.requireNonNull(oVar, "itemTimeoutIndicator is null");
        return new u(this, observableSource, oVar, observableSource2, 1);
    }

    public static <T, R> Observable<R> zip(Iterable<? extends ObservableSource<? extends T>> iterable, io.reactivex.rxjava3.functions.o oVar, boolean z, int i) {
        Objects.requireNonNull(oVar, "zipper is null");
        Objects.requireNonNull(iterable, "sources is null");
        k.c(i, "bufferSize");
        return new i0(null, iterable, oVar, i, z, 1);
    }

    public final void blockingSubscribe(g gVar, g gVar2, io.reactivex.rxjava3.functions.a aVar) {
        r.subscribe(this, gVar, gVar2, aVar);
    }

    public final <R, A> Single<R> collect(Collector<? super T, A, R> collector) {
        Objects.requireNonNull(collector, "collector is null");
        return new io.reactivex.rxjava3.internal.jdk8.d(this, collector, 0);
    }

    public final Observable<T> distinctUntilChanged(io.reactivex.rxjava3.functions.d dVar) {
        Objects.requireNonNull(dVar, "comparer is null");
        return new s5(this, k.a, dVar, 4);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <R> Observable<R> flatMap(io.reactivex.rxjava3.functions.o oVar, boolean z, int i, int i2) {
        Objects.requireNonNull(oVar, "mapper is null");
        k.c(i, "maxConcurrency");
        k.c(i2, "bufferSize");
        if (this instanceof io.reactivex.rxjava3.operators.e) {
            Object obj = ((io.reactivex.rxjava3.operators.e) this).get();
            if (obj == null) {
                return empty();
            }
            return new o(obj, 3, oVar);
        }
        return new p1(this, oVar, z, i, i2);
    }

    public final <K, V> Observable<z1> groupBy(io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2, boolean z) {
        return groupBy(oVar, oVar2, z, bufferSize());
    }

    public final <R> Observable<R> replay(io.reactivex.rxjava3.functions.o oVar, long j, TimeUnit timeUnit) {
        return replay(oVar, j, timeUnit, e.b);
    }

    public final Observable<T> retry(long j) {
        return retry(j, k.g);
    }

    public final Observable<T> sorted(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator, "comparator is null");
        return toList().toObservable().map(new ux1(22, comparator)).flatMapIterable(k.a);
    }

    public final Disposable subscribe(g gVar, g gVar2, io.reactivex.rxjava3.functions.a aVar) {
        Objects.requireNonNull(gVar, "onNext is null");
        Objects.requireNonNull(gVar2, "onError is null");
        Objects.requireNonNull(aVar, "onComplete is null");
        LambdaObserver lambdaObserver = new LambdaObserver(gVar, gVar2, aVar);
        subscribe(lambdaObserver);
        return lambdaObserver;
    }

    public final <V> Observable<T> timeout(io.reactivex.rxjava3.functions.o oVar, ObservableSource<? extends T> observableSource) {
        Objects.requireNonNull(observableSource, "fallback is null");
        return timeout0(null, oVar, observableSource);
    }

    public final <U extends Collection<? super T>> Single<U> toList(q qVar) {
        Objects.requireNonNull(qVar, "collectionSupplier is null");
        return new io.reactivex.rxjava3.internal.jdk8.d(this, qVar, 1);
    }

    public final <K, V> Single<Map<K, V>> toMap(io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2) {
        Objects.requireNonNull(oVar, "keySelector is null");
        Objects.requireNonNull(oVar2, "valueSelector is null");
        return (Single<Map<K, V>>) collect(HashMapSupplier.c, new io.reactivex.rxjava3.internal.functions.i(oVar2, oVar));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T1, T2, R> Observable<R> withLatestFrom(ObservableSource<T1> observableSource, ObservableSource<T2> observableSource2, io.reactivex.rxjava3.functions.h hVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(hVar, "combiner is null");
        return withLatestFrom((ObservableSource<?>[]) new ObservableSource[]{observableSource, observableSource2}, new ux1(14, hVar));
    }

    public final <U, R> Observable<R> zipWith(ObservableSource<? extends U> observableSource, c cVar) {
        Objects.requireNonNull(observableSource, "other is null");
        return zip(this, observableSource, cVar);
    }

    public static Observable<Long> interval(long j, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new p2(Math.max(0L, j), Math.max(0L, j2), timeUnit, scheduler);
    }

    public final T blockingFirst(T t) {
        Objects.requireNonNull(t, "defaultItem is null");
        io.reactivex.rxjava3.internal.observers.e eVar = new io.reactivex.rxjava3.internal.observers.e(0);
        subscribe(eVar);
        T t2 = (T) eVar.a();
        return t2 != null ? t2 : t;
    }

    public final T blockingLast(T t) {
        Objects.requireNonNull(t, "defaultItem is null");
        io.reactivex.rxjava3.internal.observers.e eVar = new io.reactivex.rxjava3.internal.observers.e(1);
        subscribe(eVar);
        T t2 = (T) eVar.a();
        return t2 != null ? t2 : t;
    }

    public final void blockingSubscribe(Observer<? super T> observer) {
        Objects.requireNonNull(observer, "observer is null");
        r.subscribe(this, observer);
    }

    public final Observable<List<T>> buffer(long j, TimeUnit timeUnit, int i) {
        return buffer(j, timeUnit, e.b, i);
    }

    public final Observable<T> concatWith(MaybeSource<? extends T> maybeSource) {
        Objects.requireNonNull(maybeSource, "other is null");
        return new io.reactivex.rxjava3.internal.operators.observable.m0(this, maybeSource, 1);
    }

    public final Observable<T> debounce(long j, TimeUnit timeUnit, Scheduler scheduler) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new v0(j, timeUnit, this, scheduler);
    }

    public final <U> Observable<T> delay(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "itemDelayIndicator is null");
        return (Observable<T>) flatMap(new j2(1, oVar));
    }

    public final Observable<T> delaySubscription(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return delaySubscription(timer(j, timeUnit, scheduler));
    }

    public final <K, V> Observable<z1> groupBy(io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2, boolean z, int i) {
        Objects.requireNonNull(oVar, "keySelector is null");
        Objects.requireNonNull(oVar2, "valueSelector is null");
        k.c(i, "bufferSize");
        return new a2(this, oVar, oVar2, i, z);
    }

    public final Observable<T> mergeWith(MaybeSource<? extends T> maybeSource) {
        Objects.requireNonNull(maybeSource, "other is null");
        return new z2(this, maybeSource);
    }

    public final Observable<T> retry(long j, p pVar) {
        if (j >= 0) {
            Objects.requireNonNull(pVar, "predicate is null");
            return new w3(this, j, pVar);
        }
        throw new IllegalArgumentException(u4.g("times >= 0 required but it was ", j));
    }

    public final Observable<T> sample(long j, TimeUnit timeUnit, Scheduler scheduler) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new y3(this, j, timeUnit, scheduler, false);
    }

    public final Observable<T> skipLast(int i) {
        if (i >= 0) {
            return i == 0 ? this : new j4(this, i, 0);
        }
        throw new IllegalArgumentException(u4.e("count >= 0 required but it was ", i));
    }

    public final Observable<T> takeLast(long j, TimeUnit timeUnit, boolean z) {
        return takeLast(j, timeUnit, e.d, z, bufferSize());
    }

    public final Observable<T> throttleLatest(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return throttleLatest(j, timeUnit, scheduler, false);
    }

    public final Observable<Observable<T>> window(long j, TimeUnit timeUnit, long j2) {
        return window(j, timeUnit, e.b, j2, false);
    }

    public static <T1, T2, R> Observable<R> combineLatest(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, c cVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(cVar, "combiner is null");
        return combineLatestArray(new ObservableSource[]{observableSource, observableSource2}, new ux1(13, cVar), bufferSize());
    }

    public static <T> Observable<T> just(T t, T t2, T t3) {
        Objects.requireNonNull(t, "item1 is null");
        Objects.requireNonNull(t2, "item2 is null");
        Objects.requireNonNull(t3, "item3 is null");
        return fromArray(t, t2, t3);
    }

    public static <T> Observable<T> merge(ObservableSource<? extends ObservableSource<? extends T>> observableSource, int i) {
        Objects.requireNonNull(observableSource, "sources is null");
        k.c(i, "maxConcurrency");
        return new p1(observableSource, k.a, false, i, bufferSize());
    }

    public static <T> Observable<T> mergeDelayError(ObservableSource<? extends ObservableSource<? extends T>> observableSource, int i) {
        Objects.requireNonNull(observableSource, "sources is null");
        k.c(i, "maxConcurrency");
        return new p1(observableSource, k.a, true, i, bufferSize());
    }

    public final Observable<T> doOnEach(g gVar) {
        Objects.requireNonNull(gVar, "onNotification is null");
        return doOnEach(new io.reactivex.rxjava3.internal.functions.f(gVar, 1), new io.reactivex.rxjava3.internal.functions.f(gVar, 0), new p.a0(2, gVar), k.c);
    }

    public final io.reactivex.rxjava3.observables.a replay(int i, long j, TimeUnit timeUnit) {
        return replay(i, j, timeUnit, e.b);
    }

    public final Observable<T> throttleLatest(long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new z4(this, j, timeUnit, scheduler, z);
    }

    public final Observable<io.reactivex.rxjava3.schedulers.f> timeInterval(Scheduler scheduler) {
        return timeInterval(TimeUnit.MILLISECONDS, scheduler);
    }

    public final Observable<T> timeout(long j, TimeUnit timeUnit, ObservableSource<? extends T> observableSource) {
        Objects.requireNonNull(observableSource, "fallback is null");
        return timeout0(j, timeUnit, observableSource, e.b);
    }

    public final Observable<io.reactivex.rxjava3.schedulers.f> timestamp(Scheduler scheduler) {
        return timestamp(TimeUnit.MILLISECONDS, scheduler);
    }

    public final Single<List<T>> toSortedList(Comparator<? super T> comparator, int i) {
        Objects.requireNonNull(comparator, "comparator is null");
        return (Single<List<T>>) toList(i).map(new ux1(22, comparator));
    }

    public final <U, R> Observable<R> zipWith(ObservableSource<? extends U> observableSource, c cVar, boolean z) {
        return zip(this, observableSource, cVar, z);
    }

    public static <T> Observable<T> concat(ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        return concatArray(observableSource, observableSource2);
    }

    public static <T, S> Observable<T> generate(q qVar, b bVar, g gVar) {
        Objects.requireNonNull(bVar, "generator is null");
        return generate(qVar, new j2(0, bVar), gVar);
    }

    public final T blockingSingle(T t) {
        return single(t).blockingGet();
    }

    public final Observable<List<T>> buffer(int i) {
        return buffer(i, i);
    }

    public final Observable<T> concatWith(CompletableSource completableSource) {
        Objects.requireNonNull(completableSource, "other is null");
        return new io.reactivex.rxjava3.internal.operators.observable.m0(this, completableSource, 0);
    }

    public final Observable<T> mergeWith(CompletableSource completableSource) {
        Objects.requireNonNull(completableSource, "other is null");
        return new y2(this, completableSource);
    }

    public final Observable<T> takeLast(int i) {
        if (i < 0) {
            throw new IllegalArgumentException(u4.e("count >= 0 required but it was ", i));
        }
        if (i == 0) {
            return new o0(this, 3);
        }
        int i2 = 1;
        if (i == 1) {
            return new o0(this, 6);
        }
        return new j4(this, i, i2);
    }

    public final Observable<io.reactivex.rxjava3.schedulers.f> timeInterval(TimeUnit timeUnit, Scheduler scheduler) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new s5(this, timeUnit, scheduler, 7, 0);
    }

    public final Observable<io.reactivex.rxjava3.schedulers.f> timestamp(TimeUnit timeUnit, Scheduler scheduler) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return map(new co(timeUnit, 10, scheduler));
    }

    public final Observable<Observable<T>> window(long j, TimeUnit timeUnit, long j2, boolean z) {
        return window(j, timeUnit, e.b, j2, z);
    }

    public final <U, R> Observable<R> zipWith(ObservableSource<? extends U> observableSource, c cVar, boolean z, int i) {
        return zip(this, observableSource, cVar, z, i);
    }

    public static Observable<Long> interval(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return interval(j, j, timeUnit, scheduler);
    }

    public static <T> Single<Boolean> sequenceEqual(ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2, int i) {
        return sequenceEqual(observableSource, observableSource2, k.j, i);
    }

    public static <T1, T2, R> Observable<R> zip(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, c cVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(cVar, "zipper is null");
        return zipArray(new ux1(13, cVar), false, bufferSize(), observableSource, observableSource2);
    }

    public final Observable<List<T>> buffer(int i, int i2) {
        return (Observable<List<T>>) buffer(i, i2, ArrayListSupplier.c);
    }

    public final Observable<T> delay(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return delay(j, timeUnit, scheduler, false);
    }

    public final io.reactivex.rxjava3.observables.a replay(long j, TimeUnit timeUnit) {
        return replay(j, timeUnit, e.b);
    }

    public final Observable<T> sample(long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new y3(this, j, timeUnit, scheduler, z);
    }

    public final <K, V> Single<Map<K, V>> toMap(io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2, q qVar) {
        Objects.requireNonNull(oVar, "keySelector is null");
        Objects.requireNonNull(oVar2, "valueSelector is null");
        Objects.requireNonNull(qVar, "mapSupplier is null");
        return (Single<Map<K, V>>) collect(qVar, new io.reactivex.rxjava3.internal.functions.i(oVar2, oVar));
    }

    public static <T> Observable<T> merge(ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        return fromArray(observableSource, observableSource2).flatMap((io.reactivex.rxjava3.functions.o) k.a, false, 2);
    }

    public static <T> Observable<T> mergeDelayError(ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        return fromArray(observableSource, observableSource2).flatMap((io.reactivex.rxjava3.functions.o) k.a, true, 2);
    }

    public final <U extends Collection<? super T>> Observable<U> buffer(int i, int i2, q qVar) {
        k.c(i, "count");
        k.c(i2, "skip");
        Objects.requireNonNull(qVar, "bufferSupplier is null");
        return new t(this, i, i2, qVar);
    }

    public final <R> Observable<R> concatMap(io.reactivex.rxjava3.functions.o oVar, int i, Scheduler scheduler) {
        Objects.requireNonNull(oVar, "mapper is null");
        k.c(i, "bufferSize");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new l0(this, oVar, i, ErrorMode.c, scheduler);
    }

    public final <R> Observable<R> concatMapDelayError(io.reactivex.rxjava3.functions.o oVar, boolean z, int i, Scheduler scheduler) {
        Objects.requireNonNull(oVar, "mapper is null");
        k.c(i, "bufferSize");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new l0(this, oVar, i, z ? ErrorMode.k : ErrorMode.e, scheduler);
    }

    public final Observable<T> delay(long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return new a1(this, j, timeUnit, scheduler, z);
    }

    public final Single<T> elementAt(long j, T t) {
        if (j >= 0) {
            Objects.requireNonNull(t, "defaultItem is null");
            return new n1(this, j, t);
        }
        throw new IndexOutOfBoundsException(u4.g("index >= 0 required but it was ", j));
    }

    public final Observable<T> skip(long j, TimeUnit timeUnit) {
        return skipUntil(timer(j, timeUnit));
    }

    public final Observable<T> startWith(SingleSource<T> singleSource) {
        Objects.requireNonNull(singleSource, "other is null");
        return concat(Single.wrap(singleSource).toObservable(), this);
    }

    public final Disposable subscribe(g gVar, g gVar2, io.reactivex.rxjava3.functions.a aVar, io.reactivex.rxjava3.disposables.b bVar) {
        Objects.requireNonNull(gVar, "onNext is null");
        Objects.requireNonNull(gVar2, "onError is null");
        Objects.requireNonNull(aVar, "onComplete is null");
        Objects.requireNonNull(bVar, "container is null");
        DisposableAutoReleaseObserver disposableAutoReleaseObserver = new DisposableAutoReleaseObserver(gVar, gVar2, aVar, bVar);
        bVar.c(disposableAutoReleaseObserver);
        subscribe(disposableAutoReleaseObserver);
        return disposableAutoReleaseObserver;
    }

    public final Observable<T> take(long j, TimeUnit timeUnit) {
        return takeUntil(timer(j, timeUnit));
    }

    public final Observable<T> timeout(long j, TimeUnit timeUnit, Scheduler scheduler, ObservableSource<? extends T> observableSource) {
        Objects.requireNonNull(observableSource, "fallback is null");
        return timeout0(j, timeUnit, observableSource, scheduler);
    }

    public final <K, V> Single<Map<K, Collection<V>>> toMultimap(io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2, q qVar) {
        return toMultimap(oVar, oVar2, qVar, ArrayListSupplier.c);
    }

    public final Observable<Observable<T>> window(long j) {
        return window(j, j, bufferSize());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T1, T2, T3, R> Observable<R> withLatestFrom(ObservableSource<T1> observableSource, ObservableSource<T2> observableSource2, ObservableSource<T3> observableSource3, io.reactivex.rxjava3.functions.i iVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(iVar, "combiner is null");
        return withLatestFrom((ObservableSource<?>[]) new ObservableSource[]{observableSource, observableSource2, observableSource3}, new ux1(15, iVar));
    }

    public static <T> Observable<T> concat(ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2, ObservableSource<? extends T> observableSource3) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        return concatArray(observableSource, observableSource2, observableSource3);
    }

    public static <T, S> Observable<T> generate(q qVar, c cVar) {
        return generate(qVar, cVar, k.d);
    }

    public static <T> Observable<T> just(T t, T t2, T t3, T t4) {
        Objects.requireNonNull(t, "item1 is null");
        Objects.requireNonNull(t2, "item2 is null");
        Objects.requireNonNull(t3, "item3 is null");
        Objects.requireNonNull(t4, "item4 is null");
        return fromArray(t, t2, t3, t4);
    }

    public final <R> Observable<R> replay(io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(oVar, "selector is null");
        return v3.d(oVar, new n2(this));
    }

    public final Observable<T> skip(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return skipUntil(timer(j, timeUnit, scheduler));
    }

    public final Observable<T> take(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return takeUntil(timer(j, timeUnit, scheduler));
    }

    public final Single<List<T>> toSortedList(int i) {
        return toSortedList(k.b(), i);
    }

    public final Observable<Observable<T>> window(long j, long j2) {
        return window(j, j2, bufferSize());
    }

    public static <T1, T2, T3, R> Observable<R> combineLatest(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, ObservableSource<? extends T3> observableSource3, io.reactivex.rxjava3.functions.h hVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(hVar, "combiner is null");
        return combineLatestArray(new ObservableSource[]{observableSource, observableSource2, observableSource3}, new ux1(14, hVar), bufferSize());
    }

    public static <T, S> Observable<T> generate(q qVar, c cVar, g gVar) {
        Objects.requireNonNull(qVar, "initialState is null");
        Objects.requireNonNull(cVar, "generator is null");
        Objects.requireNonNull(gVar, "disposeState is null");
        return new qn(qVar, cVar, gVar, 1);
    }

    public final Observable<T> doOnEach(Observer<? super T> observer) {
        Objects.requireNonNull(observer, "observer is null");
        return doOnEach(new m2(observer, 1), new m2(observer, 0), new l2(observer), k.c);
    }

    public final <U> Observable<T> sample(ObservableSource<U> observableSource) {
        Objects.requireNonNull(observableSource, "sampler is null");
        return new q1(this, observableSource, false, 3);
    }

    public final Observable<T> startWith(MaybeSource<T> maybeSource) {
        MaybeSource jVar;
        Observable hVar;
        Objects.requireNonNull(maybeSource, "other is null");
        if (maybeSource instanceof Maybe) {
            jVar = (Maybe) maybeSource;
        } else {
            jVar = new io.reactivex.rxjava3.internal.operators.maybe.j(maybeSource);
        }
        if (jVar instanceof io.reactivex.rxjava3.internal.fuseable.d) {
            hVar = ((io.reactivex.rxjava3.internal.fuseable.d) jVar).a();
        } else {
            hVar = new h(jVar);
        }
        return concat(hVar, this);
    }

    public final Observable<T> timeout(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return timeout0(j, timeUnit, null, scheduler);
    }

    public final Observable<Observable<T>> window(long j, long j2, int i) {
        k.d("count", j);
        k.d("skip", j2);
        k.c(i, "bufferSize");
        return new j5(this, j, j2, i);
    }

    public static <T> Observable<T> merge(ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2, ObservableSource<? extends T> observableSource3) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        return fromArray(observableSource, observableSource2, observableSource3).flatMap((io.reactivex.rxjava3.functions.o) k.a, false, 3);
    }

    public static <T> Observable<T> mergeDelayError(ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2, ObservableSource<? extends T> observableSource3) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        return fromArray(observableSource, observableSource2, observableSource3).flatMap((io.reactivex.rxjava3.functions.o) k.a, true, 3);
    }

    public final <U, V> Observable<T> delay(ObservableSource<U> observableSource, io.reactivex.rxjava3.functions.o oVar) {
        return delaySubscription(observableSource).delay(oVar);
    }

    public final <R> Observable<R> flatMap(io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2, q qVar) {
        Objects.requireNonNull(oVar, "onNextMapper is null");
        Objects.requireNonNull(oVar2, "onErrorMapper is null");
        Objects.requireNonNull(qVar, "onCompleteSupplier is null");
        return merge(new u(this, oVar, oVar2, qVar));
    }

    public final <U, V> Observable<T> timeout(ObservableSource<U> observableSource, io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(observableSource, "firstTimeoutIndicator is null");
        return timeout0(observableSource, oVar, null);
    }

    public static <T1, T2, R> Observable<R> zip(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, c cVar, boolean z) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(cVar, "zipper is null");
        return zipArray(new ux1(13, cVar), z, bufferSize(), observableSource, observableSource2);
    }

    public final <U extends Collection<? super T>> Observable<U> buffer(int i, q qVar) {
        return buffer(i, i, qVar);
    }

    public final <R> Observable<R> replay(io.reactivex.rxjava3.functions.o oVar, int i) {
        Objects.requireNonNull(oVar, "selector is null");
        k.c(i, "bufferSize");
        return v3.d(oVar, new h2(this, i, false));
    }

    public final <U> Observable<T> sample(ObservableSource<U> observableSource, boolean z) {
        Objects.requireNonNull(observableSource, "sampler is null");
        return new q1(this, observableSource, z, 3);
    }

    public final Observable<T> skipLast(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return skipLast(j, timeUnit, scheduler, false, bufferSize());
    }

    public static <T> Observable<T> concat(ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2, ObservableSource<? extends T> observableSource3, ObservableSource<? extends T> observableSource4) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        return concatArray(observableSource, observableSource2, observableSource3, observableSource4);
    }

    public final Observable<List<T>> buffer(long j, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return (Observable<List<T>>) buffer(j, j2, timeUnit, scheduler, ArrayListSupplier.c);
    }

    public final Observable<T> retry(p pVar) {
        return retry(Long.MAX_VALUE, pVar);
    }

    public final Observable<T> skipLast(long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        return skipLast(j, timeUnit, scheduler, z, bufferSize());
    }

    public final <U, V> Observable<T> timeout(ObservableSource<U> observableSource, io.reactivex.rxjava3.functions.o oVar, ObservableSource<? extends T> observableSource2) {
        Objects.requireNonNull(observableSource, "firstTimeoutIndicator is null");
        Objects.requireNonNull(observableSource2, "fallback is null");
        return timeout0(observableSource, oVar, observableSource2);
    }

    public static <T> Observable<T> just(T t, T t2, T t3, T t4, T t5) {
        Objects.requireNonNull(t, "item1 is null");
        Objects.requireNonNull(t2, "item2 is null");
        Objects.requireNonNull(t3, "item3 is null");
        Objects.requireNonNull(t4, "item4 is null");
        Objects.requireNonNull(t5, "item5 is null");
        return fromArray(t, t2, t3, t4, t5);
    }

    public final <U extends Collection<? super T>> Observable<U> buffer(long j, long j2, TimeUnit timeUnit, Scheduler scheduler, q qVar) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        Objects.requireNonNull(qVar, "bufferSupplier is null");
        return new io.reactivex.rxjava3.internal.operators.observable.c0(this, j, j2, timeUnit, scheduler, qVar, Integer.MAX_VALUE, false);
    }

    public final Observable<T> skipLast(long j, TimeUnit timeUnit, Scheduler scheduler, boolean z, int i) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        k.c(i, "bufferSize");
        return new k4(this, j, timeUnit, scheduler, i << 1, z);
    }

    public final Observable<Observable<T>> window(long j, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return window(j, j2, timeUnit, scheduler, bufferSize());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T1, T2, T3, T4, R> Observable<R> withLatestFrom(ObservableSource<T1> observableSource, ObservableSource<T2> observableSource2, ObservableSource<T3> observableSource3, ObservableSource<T4> observableSource4, io.reactivex.rxjava3.functions.j jVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        Objects.requireNonNull(jVar, "combiner is null");
        return withLatestFrom((ObservableSource<?>[]) new ObservableSource[]{observableSource, observableSource2, observableSource3, observableSource4}, new ux1(16, null));
    }

    public static <T> Observable<T> merge(ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2, ObservableSource<? extends T> observableSource3, ObservableSource<? extends T> observableSource4) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        return fromArray(observableSource, observableSource2, observableSource3, observableSource4).flatMap((io.reactivex.rxjava3.functions.o) k.a, false, 4);
    }

    public static <T> Observable<T> mergeDelayError(ObservableSource<? extends T> observableSource, ObservableSource<? extends T> observableSource2, ObservableSource<? extends T> observableSource3, ObservableSource<? extends T> observableSource4) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        return fromArray(observableSource, observableSource2, observableSource3, observableSource4).flatMap((io.reactivex.rxjava3.functions.o) k.a, true, 4);
    }

    public final <R> Observable<R> flatMap(io.reactivex.rxjava3.functions.o oVar, io.reactivex.rxjava3.functions.o oVar2, q qVar, int i) {
        Objects.requireNonNull(oVar, "onNextMapper is null");
        Objects.requireNonNull(oVar2, "onErrorMapper is null");
        Objects.requireNonNull(qVar, "onCompleteSupplier is null");
        return merge(new u(this, oVar, oVar2, qVar), i);
    }

    @Override // io.reactivex.rxjava3.core.ObservableSource
    public final void subscribe(Observer<? super T> observer) {
        Objects.requireNonNull(observer, "observer is null");
        try {
            c cVar = RxJavaPlugins.d;
            if (cVar != null) {
                try {
                    observer = (Observer) cVar.k(this, observer);
                } catch (Throwable th) {
                    throw io.reactivex.rxjava3.internal.util.c.f(th);
                }
            }
            Objects.requireNonNull(observer, "The RxJavaPlugins.onSubscribe hook returned a null Observer. Please change the handler provided to RxJavaPlugins.setOnObservableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            subscribeActual(observer);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th2) {
            he1.R(th2);
            RxJavaPlugins.b(th2);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th2);
            throw nullPointerException;
        }
    }

    public final Observable<Observable<T>> window(long j, long j2, TimeUnit timeUnit, Scheduler scheduler, int i) {
        k.d("timespan", j);
        k.d("timeskip", j2);
        k.c(i, "bufferSize");
        Objects.requireNonNull(scheduler, "scheduler is null");
        Objects.requireNonNull(timeUnit, "unit is null");
        return new q5(this, j, j2, timeUnit, scheduler, Long.MAX_VALUE, i, false);
    }

    public static <T1, T2, T3, T4, R> Observable<R> combineLatest(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, ObservableSource<? extends T3> observableSource3, ObservableSource<? extends T4> observableSource4, io.reactivex.rxjava3.functions.i iVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        Objects.requireNonNull(iVar, "combiner is null");
        return combineLatestArray(new ObservableSource[]{observableSource, observableSource2, observableSource3, observableSource4}, new ux1(15, iVar), bufferSize());
    }

    public final <R> Observable<R> replay(io.reactivex.rxjava3.functions.o oVar, int i, boolean z) {
        Objects.requireNonNull(oVar, "selector is null");
        k.c(i, "bufferSize");
        return v3.d(oVar, new h2(this, i, z));
    }

    public final Observable<T> takeLast(long j, long j2, TimeUnit timeUnit, Scheduler scheduler) {
        return takeLast(j, j2, timeUnit, scheduler, false, bufferSize());
    }

    public static <T1, T2, R> Observable<R> zip(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, c cVar, boolean z, int i) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(cVar, "zipper is null");
        return zipArray(new ux1(13, cVar), z, i, observableSource, observableSource2);
    }

    public final Observable<T> takeLast(long j, long j2, TimeUnit timeUnit, Scheduler scheduler, boolean z, int i) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        k.c(i, "bufferSize");
        if (j >= 0) {
            return new io.reactivex.rxjava3.internal.operators.observable.u4(this, j, j2, timeUnit, scheduler, i, z);
        }
        throw new IllegalArgumentException(u4.g("count >= 0 required but it was ", j));
    }

    public final Observable<List<T>> buffer(long j, TimeUnit timeUnit, Scheduler scheduler, int i) {
        return (Observable<List<T>>) buffer(j, timeUnit, scheduler, i, ArrayListSupplier.c, false);
    }

    public final <U extends Collection<? super T>> Observable<U> buffer(long j, TimeUnit timeUnit, Scheduler scheduler, int i, q qVar, boolean z) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        Objects.requireNonNull(qVar, "bufferSupplier is null");
        k.c(i, "count");
        return new io.reactivex.rxjava3.internal.operators.observable.c0(this, j, j, timeUnit, scheduler, qVar, i, z);
    }

    public final <R> Observable<R> flatMap(io.reactivex.rxjava3.functions.o oVar, int i) {
        return flatMap(oVar, false, i, bufferSize());
    }

    public final Observable<T> startWith(ObservableSource<? extends T> observableSource) {
        Objects.requireNonNull(observableSource, "other is null");
        return concatArray(observableSource, this);
    }

    public static <T> Observable<T> just(T t, T t2, T t3, T t4, T t5, T t6) {
        Objects.requireNonNull(t, "item1 is null");
        Objects.requireNonNull(t2, "item2 is null");
        Objects.requireNonNull(t3, "item3 is null");
        Objects.requireNonNull(t4, "item4 is null");
        Objects.requireNonNull(t5, "item5 is null");
        Objects.requireNonNull(t6, "item6 is null");
        return fromArray(t, t2, t3, t4, t5, t6);
    }

    public final <U, R> Observable<R> flatMap(io.reactivex.rxjava3.functions.o oVar, c cVar) {
        return flatMap(oVar, cVar, false, bufferSize(), bufferSize());
    }

    public final <R> Observable<R> replay(io.reactivex.rxjava3.functions.o oVar, int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        Objects.requireNonNull(oVar, "selector is null");
        k.c(i, "bufferSize");
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return v3.d(oVar, new i2(this, i, j, timeUnit, scheduler, false));
    }

    public final <U, R> Observable<R> flatMap(io.reactivex.rxjava3.functions.o oVar, c cVar, boolean z) {
        return flatMap(oVar, cVar, z, bufferSize(), bufferSize());
    }

    public final Observable<Observable<T>> window(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return window(j, timeUnit, scheduler, Long.MAX_VALUE, false);
    }

    public final <R> Observable<R> withLatestFrom(ObservableSource<?>[] observableSourceArr, io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(observableSourceArr, "others is null");
        Objects.requireNonNull(oVar, "combiner is null");
        return new u(this, observableSourceArr, oVar);
    }

    public static <T1, T2, T3, R> Observable<R> zip(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, ObservableSource<? extends T3> observableSource3, io.reactivex.rxjava3.functions.h hVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(hVar, "zipper is null");
        return zipArray(new ux1(14, hVar), false, bufferSize(), observableSource, observableSource2, observableSource3);
    }

    public final <U, R> Observable<R> flatMap(io.reactivex.rxjava3.functions.o oVar, c cVar, boolean z, int i) {
        return flatMap(oVar, cVar, z, i, bufferSize());
    }

    public final Observable<Observable<T>> window(long j, TimeUnit timeUnit, Scheduler scheduler, long j2) {
        return window(j, timeUnit, scheduler, j2, false);
    }

    public static <T1, T2, T3, T4, T5, R> Observable<R> combineLatest(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, ObservableSource<? extends T3> observableSource3, ObservableSource<? extends T4> observableSource4, ObservableSource<? extends T5> observableSource5, io.reactivex.rxjava3.functions.j jVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        Objects.requireNonNull(observableSource5, "source5 is null");
        Objects.requireNonNull(jVar, "combiner is null");
        return combineLatestArray(new ObservableSource[]{observableSource, observableSource2, observableSource3, observableSource4, observableSource5}, new ux1(16, null), bufferSize());
    }

    public final <U, R> Observable<R> flatMap(io.reactivex.rxjava3.functions.o oVar, c cVar, boolean z, int i, int i2) {
        Objects.requireNonNull(oVar, "mapper is null");
        Objects.requireNonNull(cVar, "combiner is null");
        return flatMap(new k2(cVar, oVar, 1), z, i, i2);
    }

    public final Observable<Observable<T>> window(long j, TimeUnit timeUnit, Scheduler scheduler, long j2, boolean z) {
        return window(j, timeUnit, scheduler, j2, z, bufferSize());
    }

    public final Observable<List<T>> buffer(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return (Observable<List<T>>) buffer(j, timeUnit, scheduler, Integer.MAX_VALUE, ArrayListSupplier.c, false);
    }

    public final Observable<Observable<T>> window(long j, TimeUnit timeUnit, Scheduler scheduler, long j2, boolean z, int i) {
        k.c(i, "bufferSize");
        Objects.requireNonNull(scheduler, "scheduler is null");
        Objects.requireNonNull(timeUnit, "unit is null");
        k.d("count", j2);
        return new q5(this, j, j, timeUnit, scheduler, j2, i, z);
    }

    public final <R> Observable<R> withLatestFrom(Iterable<? extends ObservableSource<?>> iterable, io.reactivex.rxjava3.functions.o oVar) {
        Objects.requireNonNull(iterable, "others is null");
        Objects.requireNonNull(oVar, "combiner is null");
        return new u(this, iterable, oVar);
    }

    public final <TOpening, TClosing> Observable<List<T>> buffer(ObservableSource<? extends TOpening> observableSource, io.reactivex.rxjava3.functions.o oVar) {
        return (Observable<List<T>>) buffer(observableSource, oVar, ArrayListSupplier.c);
    }

    public final <TOpening, TClosing, U extends Collection<? super T>> Observable<U> buffer(ObservableSource<? extends TOpening> observableSource, io.reactivex.rxjava3.functions.o oVar, q qVar) {
        Objects.requireNonNull(observableSource, "openingIndicator is null");
        Objects.requireNonNull(oVar, "closingIndicator is null");
        Objects.requireNonNull(qVar, "bufferSupplier is null");
        return new u(this, observableSource, oVar, qVar, 0);
    }

    public final <R> Observable<R> replay(io.reactivex.rxjava3.functions.o oVar, int i, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        Objects.requireNonNull(oVar, "selector is null");
        k.c(i, "bufferSize");
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return v3.d(oVar, new i2(this, i, j, timeUnit, scheduler, z));
    }

    public static <T> Observable<T> just(T t, T t2, T t3, T t4, T t5, T t6, T t7) {
        Objects.requireNonNull(t, "item1 is null");
        Objects.requireNonNull(t2, "item2 is null");
        Objects.requireNonNull(t3, "item3 is null");
        Objects.requireNonNull(t4, "item4 is null");
        Objects.requireNonNull(t5, "item5 is null");
        Objects.requireNonNull(t6, "item6 is null");
        Objects.requireNonNull(t7, "item7 is null");
        return fromArray(t, t2, t3, t4, t5, t6, t7);
    }

    public final <U, R> Observable<R> flatMap(io.reactivex.rxjava3.functions.o oVar, c cVar, int i) {
        return flatMap(oVar, cVar, false, i, bufferSize());
    }

    public static <T1, T2, T3, T4, R> Observable<R> zip(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, ObservableSource<? extends T3> observableSource3, ObservableSource<? extends T4> observableSource4, io.reactivex.rxjava3.functions.i iVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        Objects.requireNonNull(iVar, "zipper is null");
        return zipArray(new ux1(15, iVar), false, bufferSize(), observableSource, observableSource2, observableSource3, observableSource4);
    }

    public final Observable<T> takeLast(long j, TimeUnit timeUnit, Scheduler scheduler) {
        return takeLast(j, timeUnit, scheduler, false, bufferSize());
    }

    public final Observable<T> takeLast(long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        return takeLast(j, timeUnit, scheduler, z, bufferSize());
    }

    public final <B> Observable<Observable<T>> window(ObservableSource<B> observableSource) {
        return window(observableSource, bufferSize());
    }

    public final <B> Observable<List<T>> buffer(ObservableSource<B> observableSource) {
        return (Observable<List<T>>) buffer((ObservableSource) observableSource, (q) ArrayListSupplier.c);
    }

    public final Observable<T> takeLast(long j, TimeUnit timeUnit, Scheduler scheduler, boolean z, int i) {
        return takeLast(Long.MAX_VALUE, j, timeUnit, scheduler, z, i);
    }

    public final <B> Observable<Observable<T>> window(ObservableSource<B> observableSource, int i) {
        Objects.requireNonNull(observableSource, "boundaryIndicator is null");
        k.c(i, "bufferSize");
        return new l5(this, observableSource, i);
    }

    public static <T1, T2, T3, T4, T5, T6, R> Observable<R> combineLatest(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, ObservableSource<? extends T3> observableSource3, ObservableSource<? extends T4> observableSource4, ObservableSource<? extends T5> observableSource5, ObservableSource<? extends T6> observableSource6, io.reactivex.rxjava3.functions.k kVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        Objects.requireNonNull(observableSource5, "source5 is null");
        Objects.requireNonNull(observableSource6, "source6 is null");
        Objects.requireNonNull(kVar, "combiner is null");
        return combineLatestArray(new ObservableSource[]{observableSource, observableSource2, observableSource3, observableSource4, observableSource5, observableSource6}, new ux1(17, null), bufferSize());
    }

    public final <B> Observable<List<T>> buffer(ObservableSource<B> observableSource, int i) {
        k.c(i, "initialCapacity");
        return (Observable<List<T>>) buffer(observableSource, new io.reactivex.rxjava3.internal.functions.a(i));
    }

    public final <R> Observable<R> replay(io.reactivex.rxjava3.functions.o oVar, long j, TimeUnit timeUnit, Scheduler scheduler) {
        Objects.requireNonNull(oVar, "selector is null");
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return v3.d(oVar, new o2(this, j, timeUnit, scheduler, false));
    }

    public final <U, V> Observable<Observable<T>> window(ObservableSource<U> observableSource, io.reactivex.rxjava3.functions.o oVar) {
        return window(observableSource, oVar, bufferSize());
    }

    public final <B, U extends Collection<? super T>> Observable<U> buffer(ObservableSource<B> observableSource, q qVar) {
        Objects.requireNonNull(observableSource, "boundaryIndicator is null");
        Objects.requireNonNull(qVar, "bufferSupplier is null");
        return new s5(this, observableSource, qVar, 1, 0);
    }

    public final <U, V> Observable<Observable<T>> window(ObservableSource<U> observableSource, io.reactivex.rxjava3.functions.o oVar, int i) {
        Objects.requireNonNull(observableSource, "openingIndicator is null");
        Objects.requireNonNull(oVar, "closingIndicator is null");
        k.c(i, "bufferSize");
        return new j0(this, observableSource, oVar, i);
    }

    public static <T> Observable<T> just(T t, T t2, T t3, T t4, T t5, T t6, T t7, T t8) {
        Objects.requireNonNull(t, "item1 is null");
        Objects.requireNonNull(t2, "item2 is null");
        Objects.requireNonNull(t3, "item3 is null");
        Objects.requireNonNull(t4, "item4 is null");
        Objects.requireNonNull(t5, "item5 is null");
        Objects.requireNonNull(t6, "item6 is null");
        Objects.requireNonNull(t7, "item7 is null");
        Objects.requireNonNull(t8, "item8 is null");
        return fromArray(t, t2, t3, t4, t5, t6, t7, t8);
    }

    public static <T1, T2, T3, T4, T5, R> Observable<R> zip(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, ObservableSource<? extends T3> observableSource3, ObservableSource<? extends T4> observableSource4, ObservableSource<? extends T5> observableSource5, io.reactivex.rxjava3.functions.j jVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        Objects.requireNonNull(observableSource5, "source5 is null");
        Objects.requireNonNull(jVar, "zipper is null");
        return zipArray(new ux1(16, null), false, bufferSize(), observableSource, observableSource2, observableSource3, observableSource4, observableSource5);
    }

    public final <R> Observable<R> replay(io.reactivex.rxjava3.functions.o oVar, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        Objects.requireNonNull(oVar, "selector is null");
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return v3.d(oVar, new o2(this, j, timeUnit, scheduler, z));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> Observable<R> combineLatest(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, ObservableSource<? extends T3> observableSource3, ObservableSource<? extends T4> observableSource4, ObservableSource<? extends T5> observableSource5, ObservableSource<? extends T6> observableSource6, ObservableSource<? extends T7> observableSource7, l lVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        Objects.requireNonNull(observableSource5, "source5 is null");
        Objects.requireNonNull(observableSource6, "source6 is null");
        Objects.requireNonNull(observableSource7, "source7 is null");
        Objects.requireNonNull(lVar, "combiner is null");
        return combineLatestArray(new ObservableSource[]{observableSource, observableSource2, observableSource3, observableSource4, observableSource5, observableSource6, observableSource7}, new ux1(18, null), bufferSize());
    }

    public final io.reactivex.rxjava3.observables.a replay(int i) {
        k.c(i, "bufferSize");
        if (i == Integer.MAX_VALUE) {
            return v3.c(this, v3.m);
        }
        return v3.c(this, new r3(i, false));
    }

    public static <T1, T2, T3, T4, T5, T6, R> Observable<R> zip(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, ObservableSource<? extends T3> observableSource3, ObservableSource<? extends T4> observableSource4, ObservableSource<? extends T5> observableSource5, ObservableSource<? extends T6> observableSource6, io.reactivex.rxjava3.functions.k kVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        Objects.requireNonNull(observableSource5, "source5 is null");
        Objects.requireNonNull(observableSource6, "source6 is null");
        Objects.requireNonNull(kVar, "zipper is null");
        return zipArray(new ux1(17, null), false, bufferSize(), observableSource, observableSource2, observableSource3, observableSource4, observableSource5, observableSource6);
    }

    public static <T> Observable<T> just(T t, T t2, T t3, T t4, T t5, T t6, T t7, T t8, T t9) {
        Objects.requireNonNull(t, "item1 is null");
        Objects.requireNonNull(t2, "item2 is null");
        Objects.requireNonNull(t3, "item3 is null");
        Objects.requireNonNull(t4, "item4 is null");
        Objects.requireNonNull(t5, "item5 is null");
        Objects.requireNonNull(t6, "item6 is null");
        Objects.requireNonNull(t7, "item7 is null");
        Objects.requireNonNull(t8, "item8 is null");
        Objects.requireNonNull(t9, "item9 is null");
        return fromArray(t, t2, t3, t4, t5, t6, t7, t8, t9);
    }

    public final io.reactivex.rxjava3.observables.a replay(int i, boolean z) {
        k.c(i, "bufferSize");
        if (i == Integer.MAX_VALUE) {
            return v3.c(this, v3.m);
        }
        return v3.c(this, new r3(i, z));
    }

    public final io.reactivex.rxjava3.observables.a replay(int i, long j, TimeUnit timeUnit, Scheduler scheduler) {
        k.c(i, "bufferSize");
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return v3.c(this, new t3(i, j, timeUnit, scheduler, false));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Observable<R> combineLatest(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, ObservableSource<? extends T3> observableSource3, ObservableSource<? extends T4> observableSource4, ObservableSource<? extends T5> observableSource5, ObservableSource<? extends T6> observableSource6, ObservableSource<? extends T7> observableSource7, ObservableSource<? extends T8> observableSource8, io.reactivex.rxjava3.functions.m mVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        Objects.requireNonNull(observableSource5, "source5 is null");
        Objects.requireNonNull(observableSource6, "source6 is null");
        Objects.requireNonNull(observableSource7, "source7 is null");
        Objects.requireNonNull(observableSource8, "source8 is null");
        Objects.requireNonNull(mVar, "combiner is null");
        return combineLatestArray(new ObservableSource[]{observableSource, observableSource2, observableSource3, observableSource4, observableSource5, observableSource6, observableSource7, observableSource8}, new ux1(19, null), bufferSize());
    }

    public static <T1, T2, T3, T4, T5, T6, T7, R> Observable<R> zip(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, ObservableSource<? extends T3> observableSource3, ObservableSource<? extends T4> observableSource4, ObservableSource<? extends T5> observableSource5, ObservableSource<? extends T6> observableSource6, ObservableSource<? extends T7> observableSource7, l lVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        Objects.requireNonNull(observableSource5, "source5 is null");
        Objects.requireNonNull(observableSource6, "source6 is null");
        Objects.requireNonNull(observableSource7, "source7 is null");
        Objects.requireNonNull(lVar, "zipper is null");
        return zipArray(new ux1(18, null), false, bufferSize(), observableSource, observableSource2, observableSource3, observableSource4, observableSource5, observableSource6, observableSource7);
    }

    public final io.reactivex.rxjava3.observables.a replay(int i, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        k.c(i, "bufferSize");
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return v3.c(this, new t3(i, j, timeUnit, scheduler, z));
    }

    public static <T> Observable<T> just(T t, T t2, T t3, T t4, T t5, T t6, T t7, T t8, T t9, T t10) {
        Objects.requireNonNull(t, "item1 is null");
        Objects.requireNonNull(t2, "item2 is null");
        Objects.requireNonNull(t3, "item3 is null");
        Objects.requireNonNull(t4, "item4 is null");
        Objects.requireNonNull(t5, "item5 is null");
        Objects.requireNonNull(t6, "item6 is null");
        Objects.requireNonNull(t7, "item7 is null");
        Objects.requireNonNull(t8, "item8 is null");
        Objects.requireNonNull(t9, "item9 is null");
        Objects.requireNonNull(t10, "item10 is null");
        return fromArray(t, t2, t3, t4, t5, t6, t7, t8, t9, t10);
    }

    public final io.reactivex.rxjava3.observables.a replay(long j, TimeUnit timeUnit, Scheduler scheduler) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return v3.c(this, new t3(Integer.MAX_VALUE, j, timeUnit, scheduler, false));
    }

    public final io.reactivex.rxjava3.observables.a replay(long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        Objects.requireNonNull(timeUnit, "unit is null");
        Objects.requireNonNull(scheduler, "scheduler is null");
        return v3.c(this, new t3(Integer.MAX_VALUE, j, timeUnit, scheduler, z));
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Observable<R> combineLatest(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, ObservableSource<? extends T3> observableSource3, ObservableSource<? extends T4> observableSource4, ObservableSource<? extends T5> observableSource5, ObservableSource<? extends T6> observableSource6, ObservableSource<? extends T7> observableSource7, ObservableSource<? extends T8> observableSource8, ObservableSource<? extends T9> observableSource9, io.reactivex.rxjava3.functions.n nVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        Objects.requireNonNull(observableSource5, "source5 is null");
        Objects.requireNonNull(observableSource6, "source6 is null");
        Objects.requireNonNull(observableSource7, "source7 is null");
        Objects.requireNonNull(observableSource8, "source8 is null");
        Objects.requireNonNull(observableSource9, "source9 is null");
        Objects.requireNonNull(nVar, "combiner is null");
        return combineLatestArray(new ObservableSource[]{observableSource, observableSource2, observableSource3, observableSource4, observableSource5, observableSource6, observableSource7, observableSource8, observableSource9}, new ux1(20, null), bufferSize());
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, R> Observable<R> zip(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, ObservableSource<? extends T3> observableSource3, ObservableSource<? extends T4> observableSource4, ObservableSource<? extends T5> observableSource5, ObservableSource<? extends T6> observableSource6, ObservableSource<? extends T7> observableSource7, ObservableSource<? extends T8> observableSource8, io.reactivex.rxjava3.functions.m mVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        Objects.requireNonNull(observableSource5, "source5 is null");
        Objects.requireNonNull(observableSource6, "source6 is null");
        Objects.requireNonNull(observableSource7, "source7 is null");
        Objects.requireNonNull(observableSource8, "source8 is null");
        Objects.requireNonNull(mVar, "zipper is null");
        return zipArray(new ux1(19, null), false, bufferSize(), observableSource, observableSource2, observableSource3, observableSource4, observableSource5, observableSource6, observableSource7, observableSource8);
    }

    public static <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Observable<R> zip(ObservableSource<? extends T1> observableSource, ObservableSource<? extends T2> observableSource2, ObservableSource<? extends T3> observableSource3, ObservableSource<? extends T4> observableSource4, ObservableSource<? extends T5> observableSource5, ObservableSource<? extends T6> observableSource6, ObservableSource<? extends T7> observableSource7, ObservableSource<? extends T8> observableSource8, ObservableSource<? extends T9> observableSource9, io.reactivex.rxjava3.functions.n nVar) {
        Objects.requireNonNull(observableSource, "source1 is null");
        Objects.requireNonNull(observableSource2, "source2 is null");
        Objects.requireNonNull(observableSource3, "source3 is null");
        Objects.requireNonNull(observableSource4, "source4 is null");
        Objects.requireNonNull(observableSource5, "source5 is null");
        Objects.requireNonNull(observableSource6, "source6 is null");
        Objects.requireNonNull(observableSource7, "source7 is null");
        Objects.requireNonNull(observableSource8, "source8 is null");
        Objects.requireNonNull(observableSource9, "source9 is null");
        Objects.requireNonNull(nVar, "zipper is null");
        return zipArray(new ux1(20, null), false, bufferSize(), observableSource, observableSource2, observableSource3, observableSource4, observableSource5, observableSource6, observableSource7, observableSource8, observableSource9);
    }
}
