package io.reactivex.rxjava3.plugins;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.exceptions.CompositeException;
import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.exceptions.OnErrorNotImplementedException;
import io.reactivex.rxjava3.exceptions.UndeliverableException;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.BooleanSupplier;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.internal.util.ExceptionHelper;
import java.util.Objects;
import org.reactivestreams.Subscriber;

/* JADX INFO: loaded from: classes7.dex */
public final class RxJavaPlugins {
    static volatile Consumer<? super Throwable> errorHandler;
    static volatile boolean failNonBlockingScheduler;
    static volatile boolean lockdown;
    static volatile BooleanSupplier onBeforeBlocking;
    static volatile Function<? super Completable, ? extends Completable> onCompletableAssembly;
    static volatile BiFunction<? super Completable, ? super CompletableObserver, ? extends CompletableObserver> onCompletableSubscribe;
    static volatile Function<? super Scheduler, ? extends Scheduler> onComputationHandler;
    static volatile Function<? super Flowable, ? extends Flowable> onFlowableAssembly;
    static volatile BiFunction<? super Flowable, ? super Subscriber, ? extends Subscriber> onFlowableSubscribe;
    static volatile Function<? super Supplier<Scheduler>, ? extends Scheduler> onInitComputationHandler;
    static volatile Function<? super Supplier<Scheduler>, ? extends Scheduler> onInitIoHandler;
    static volatile Function<? super Supplier<Scheduler>, ? extends Scheduler> onInitNewThreadHandler;
    static volatile Function<? super Supplier<Scheduler>, ? extends Scheduler> onInitSingleHandler;
    static volatile Function<? super Scheduler, ? extends Scheduler> onIoHandler;
    static volatile Function<? super Maybe, ? extends Maybe> onMaybeAssembly;
    static volatile BiFunction<? super Maybe, ? super MaybeObserver, ? extends MaybeObserver> onMaybeSubscribe;
    static volatile Function<? super Observable, ? extends Observable> onObservableAssembly;
    static volatile BiFunction<? super Observable, ? super Observer, ? extends Observer> onObservableSubscribe;
    static volatile Function<? super Runnable, ? extends Runnable> onScheduleHandler;
    static volatile Function<? super Single, ? extends Single> onSingleAssembly;
    static volatile Function<? super Scheduler, ? extends Scheduler> onSingleHandler;
    static volatile BiFunction<? super Single, ? super SingleObserver, ? extends SingleObserver> onSingleSubscribe;

    public static boolean isFailOnNonBlockingScheduler() {
        return failNonBlockingScheduler;
    }

    public static Scheduler initComputationScheduler(Supplier<Scheduler> defaultScheduler) {
        Objects.requireNonNull(defaultScheduler, "Scheduler Supplier can't be null");
        Function<? super Supplier<Scheduler>, ? extends Scheduler> function = onInitComputationHandler;
        if (function == null) {
            return callRequireNonNull(defaultScheduler);
        }
        return applyRequireNonNull(function, defaultScheduler);
    }

    public static Scheduler initIoScheduler(Supplier<Scheduler> defaultScheduler) {
        Objects.requireNonNull(defaultScheduler, "Scheduler Supplier can't be null");
        Function<? super Supplier<Scheduler>, ? extends Scheduler> function = onInitIoHandler;
        if (function == null) {
            return callRequireNonNull(defaultScheduler);
        }
        return applyRequireNonNull(function, defaultScheduler);
    }

    public static Scheduler initNewThreadScheduler(Supplier<Scheduler> defaultScheduler) {
        Objects.requireNonNull(defaultScheduler, "Scheduler Supplier can't be null");
        Function<? super Supplier<Scheduler>, ? extends Scheduler> function = onInitNewThreadHandler;
        if (function == null) {
            return callRequireNonNull(defaultScheduler);
        }
        return applyRequireNonNull(function, defaultScheduler);
    }

    public static Scheduler initSingleScheduler(Supplier<Scheduler> defaultScheduler) {
        Objects.requireNonNull(defaultScheduler, "Scheduler Supplier can't be null");
        Function<? super Supplier<Scheduler>, ? extends Scheduler> function = onInitSingleHandler;
        if (function == null) {
            return callRequireNonNull(defaultScheduler);
        }
        return applyRequireNonNull(function, defaultScheduler);
    }

    public static Scheduler onComputationScheduler(Scheduler defaultScheduler) {
        Function<? super Scheduler, ? extends Scheduler> function = onComputationHandler;
        return function == null ? defaultScheduler : (Scheduler) apply(function, defaultScheduler);
    }

    public static void onError(Throwable error) {
        Consumer<? super Throwable> consumer = errorHandler;
        if (error == null) {
            error = ExceptionHelper.createNullPointerException("onError called with a null Throwable.");
        } else if (!isBug(error)) {
            error = new UndeliverableException(error);
        }
        if (consumer != null) {
            try {
                consumer.accept(error);
                return;
            } catch (Throwable th) {
                th.printStackTrace();
                uncaught(th);
            }
        }
        error.printStackTrace();
        uncaught(error);
    }

    static boolean isBug(Throwable error) {
        return (error instanceof OnErrorNotImplementedException) || (error instanceof MissingBackpressureException) || (error instanceof IllegalStateException) || (error instanceof NullPointerException) || (error instanceof IllegalArgumentException) || (error instanceof CompositeException);
    }

    static void uncaught(Throwable error) {
        Thread threadCurrentThread = Thread.currentThread();
        threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, error);
    }

    public static Scheduler onIoScheduler(Scheduler defaultScheduler) {
        Function<? super Scheduler, ? extends Scheduler> function = onIoHandler;
        return function == null ? defaultScheduler : (Scheduler) apply(function, defaultScheduler);
    }

    public static Runnable onSchedule(Runnable run) {
        Objects.requireNonNull(run, "run is null");
        Function<? super Runnable, ? extends Runnable> function = onScheduleHandler;
        return function == null ? run : (Runnable) apply(function, run);
    }

    public static Scheduler onSingleScheduler(Scheduler defaultScheduler) {
        Function<? super Scheduler, ? extends Scheduler> function = onSingleHandler;
        return function == null ? defaultScheduler : (Scheduler) apply(function, defaultScheduler);
    }

    public static void setErrorHandler(Consumer<? super Throwable> handler) {
        if (lockdown) {
            throw new IllegalStateException("Plugins can't be changed anymore");
        }
        errorHandler = handler;
    }

    public static <T> Subscriber<? super T> onSubscribe(Flowable<T> source, Subscriber<? super T> subscriber) {
        BiFunction<? super Flowable, ? super Subscriber, ? extends Subscriber> biFunction = onFlowableSubscribe;
        return biFunction != null ? (Subscriber) apply(biFunction, source, subscriber) : subscriber;
    }

    public static <T> Observer<? super T> onSubscribe(Observable<T> source, Observer<? super T> observer) {
        BiFunction<? super Observable, ? super Observer, ? extends Observer> biFunction = onObservableSubscribe;
        return biFunction != null ? (Observer) apply(biFunction, source, observer) : observer;
    }

    public static <T> SingleObserver<? super T> onSubscribe(Single<T> source, SingleObserver<? super T> observer) {
        BiFunction<? super Single, ? super SingleObserver, ? extends SingleObserver> biFunction = onSingleSubscribe;
        return biFunction != null ? (SingleObserver) apply(biFunction, source, observer) : observer;
    }

    public static CompletableObserver onSubscribe(Completable source, CompletableObserver observer) {
        BiFunction<? super Completable, ? super CompletableObserver, ? extends CompletableObserver> biFunction = onCompletableSubscribe;
        return biFunction != null ? (CompletableObserver) apply(biFunction, source, observer) : observer;
    }

    public static <T> MaybeObserver<? super T> onSubscribe(Maybe<T> source, MaybeObserver<? super T> observer) {
        BiFunction<? super Maybe, ? super MaybeObserver, ? extends MaybeObserver> biFunction = onMaybeSubscribe;
        return biFunction != null ? (MaybeObserver) apply(biFunction, source, observer) : observer;
    }

    public static <T> Maybe<T> onAssembly(Maybe<T> source) {
        Function<? super Maybe, ? extends Maybe> function = onMaybeAssembly;
        return function != null ? (Maybe) apply(function, source) : source;
    }

    public static <T> Flowable<T> onAssembly(Flowable<T> source) {
        Function<? super Flowable, ? extends Flowable> function = onFlowableAssembly;
        return function != null ? (Flowable) apply(function, source) : source;
    }

    public static <T> Observable<T> onAssembly(Observable<T> source) {
        Function<? super Observable, ? extends Observable> function = onObservableAssembly;
        return function != null ? (Observable) apply(function, source) : source;
    }

    public static <T> Single<T> onAssembly(Single<T> source) {
        Function<? super Single, ? extends Single> function = onSingleAssembly;
        return function != null ? (Single) apply(function, source) : source;
    }

    public static Completable onAssembly(Completable source) {
        Function<? super Completable, ? extends Completable> function = onCompletableAssembly;
        return function != null ? (Completable) apply(function, source) : source;
    }

    public static boolean onBeforeBlocking() {
        BooleanSupplier booleanSupplier = onBeforeBlocking;
        if (booleanSupplier == null) {
            return false;
        }
        try {
            return booleanSupplier.getAsBoolean();
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    static <T, R> R apply(Function<T, R> f, T t) {
        try {
            return f.apply(t);
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    static <T, U, R> R apply(BiFunction<T, U, R> f, T t, U u) {
        try {
            return f.apply(t, u);
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    static Scheduler callRequireNonNull(Supplier<Scheduler> s) {
        try {
            Scheduler scheduler = s.get();
            Objects.requireNonNull(scheduler, "Scheduler Supplier result can't be null");
            return scheduler;
        } catch (Throwable th) {
            throw ExceptionHelper.wrapOrThrow(th);
        }
    }

    static Scheduler applyRequireNonNull(Function<? super Supplier<Scheduler>, ? extends Scheduler> f, Supplier<Scheduler> s) {
        Object objApply = apply(f, s);
        Objects.requireNonNull(objApply, "Scheduler Supplier result can't be null");
        return (Scheduler) objApply;
    }

    private RxJavaPlugins() {
        throw new IllegalStateException("No instances!");
    }
}
