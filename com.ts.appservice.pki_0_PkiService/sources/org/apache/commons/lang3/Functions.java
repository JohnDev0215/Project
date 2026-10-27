package org.apache.commons.lang3;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;
import org.apache.commons.lang3.Streams;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class Functions {

    /* JADX INFO: compiled from: caradapter-bug */
    @FunctionalInterface
    public interface FailableBiConsumer<O1, O2, T extends Throwable> {
        void accept(O1 o1, O2 o2);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @FunctionalInterface
    public interface FailableBiFunction<I1, I2, O, T extends Throwable> {
        O apply(I1 i1, I2 i2);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @FunctionalInterface
    public interface FailableBiPredicate<O1, O2, T extends Throwable> {
        boolean test(O1 o1, O2 o2);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @FunctionalInterface
    public interface FailableCallable<O, T extends Throwable> {
        O call();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @FunctionalInterface
    public interface FailableConsumer<O, T extends Throwable> {
        void accept(O o);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @FunctionalInterface
    public interface FailableFunction<I, O, T extends Throwable> {
        O apply(I i);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @FunctionalInterface
    public interface FailablePredicate<O, T extends Throwable> {
        boolean test(O o);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @FunctionalInterface
    public interface FailableRunnable<T extends Throwable> {
        void run();
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @FunctionalInterface
    public interface FailableSupplier<O, T extends Throwable> {
        O get();
    }

    public static <O, T extends Throwable> void accept(final FailableConsumer<O, T> failableConsumer, final O o) {
        run(new FailableRunnable() { // from class: org.apache.commons.lang3.r
            @Override // org.apache.commons.lang3.Functions.FailableRunnable
            public final void run() {
                failableConsumer.accept(o);
            }
        });
    }

    public static <I, O, T extends Throwable> O apply(final FailableFunction<I, O, T> failableFunction, final I i) {
        return (O) get(new FailableSupplier() { // from class: org.apache.commons.lang3.n
            @Override // org.apache.commons.lang3.Functions.FailableSupplier
            public final Object get() {
                return failableFunction.apply(i);
            }
        });
    }

    public static <I1, I2> BiConsumer<I1, I2> asBiConsumer(final FailableBiConsumer<I1, I2, ?> failableBiConsumer) {
        return new BiConsumer() { // from class: org.apache.commons.lang3.l
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                Functions.accept(failableBiConsumer, obj, obj2);
            }
        };
    }

    public static <I1, I2, O> BiFunction<I1, I2, O> asBiFunction(final FailableBiFunction<I1, I2, O, ?> failableBiFunction) {
        return new BiFunction() { // from class: org.apache.commons.lang3.p
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return Functions.apply(failableBiFunction, obj, obj2);
            }
        };
    }

    public static <I1, I2> BiPredicate<I1, I2> asBiPredicate(final FailableBiPredicate<I1, I2, ?> failableBiPredicate) {
        return new BiPredicate() { // from class: org.apache.commons.lang3.h
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return Functions.test(failableBiPredicate, obj, obj2);
            }
        };
    }

    public static <O> Callable<O> asCallable(final FailableCallable<O, ?> failableCallable) {
        return new Callable() { // from class: org.apache.commons.lang3.j
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return Functions.call(failableCallable);
            }
        };
    }

    public static <I> Consumer<I> asConsumer(final FailableConsumer<I, ?> failableConsumer) {
        return new Consumer() { // from class: org.apache.commons.lang3.k
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Functions.accept(failableConsumer, obj);
            }
        };
    }

    public static <I, O> Function<I, O> asFunction(final FailableFunction<I, O, ?> failableFunction) {
        return new Function() { // from class: org.apache.commons.lang3.f
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Functions.apply(failableFunction, obj);
            }
        };
    }

    public static <I> Predicate<I> asPredicate(final FailablePredicate<I, ?> failablePredicate) {
        return new Predicate() { // from class: org.apache.commons.lang3.o
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Functions.test(failablePredicate, obj);
            }
        };
    }

    public static Runnable asRunnable(final FailableRunnable<?> failableRunnable) {
        return new Runnable() { // from class: org.apache.commons.lang3.s
            @Override // java.lang.Runnable
            public final void run() {
                Functions.run(failableRunnable);
            }
        };
    }

    public static <O> Supplier<O> asSupplier(final FailableSupplier<O, ?> failableSupplier) {
        return new Supplier() { // from class: org.apache.commons.lang3.i
            @Override // java.util.function.Supplier
            public final Object get() {
                return Functions.get(failableSupplier);
            }
        };
    }

    public static <O, T extends Throwable> O call(final FailableCallable<O, T> failableCallable) {
        failableCallable.getClass();
        return (O) get(new FailableSupplier() { // from class: org.apache.commons.lang3.t
            @Override // org.apache.commons.lang3.Functions.FailableSupplier
            public final Object get() {
                return failableCallable.call();
            }
        });
    }

    public static <O, T extends Throwable> O get(FailableSupplier<O, T> failableSupplier) {
        try {
            return failableSupplier.get();
        } catch (Throwable th) {
            throw rethrow(th);
        }
    }

    public static RuntimeException rethrow(Throwable th) {
        Objects.requireNonNull(th, "throwable");
        if (th instanceof RuntimeException) {
            throw ((RuntimeException) th);
        }
        if (th instanceof Error) {
            throw ((Error) th);
        }
        if (th instanceof IOException) {
            throw new UncheckedIOException((IOException) th);
        }
        throw new UndeclaredThrowableException(th);
    }

    public static <T extends Throwable> void run(FailableRunnable<T> failableRunnable) {
        try {
            failableRunnable.run();
        } catch (Throwable th) {
            throw rethrow(th);
        }
    }

    public static <O> Streams.FailableStream<O> stream(Stream<O> stream) {
        return new Streams.FailableStream<>(stream);
    }

    public static <O, T extends Throwable> boolean test(final FailablePredicate<O, T> failablePredicate, final O o) {
        return ((Boolean) get(new FailableSupplier() { // from class: org.apache.commons.lang3.q
            @Override // org.apache.commons.lang3.Functions.FailableSupplier
            public final Object get() {
                return Boolean.valueOf(failablePredicate.test(o));
            }
        })).booleanValue();
    }

    @SafeVarargs
    public static void tryWithResources(FailableRunnable<? extends Throwable> failableRunnable, FailableConsumer<Throwable, ? extends Throwable> failableConsumer, FailableRunnable<? extends Throwable>... failableRunnableArr) {
        if (failableConsumer == null) {
            failableConsumer = new FailableConsumer() { // from class: org.apache.commons.lang3.E
                @Override // org.apache.commons.lang3.Functions.FailableConsumer
                public final void accept(Object obj) {
                    Functions.rethrow((Throwable) obj);
                }
            };
        }
        if (failableRunnableArr != null) {
            for (FailableRunnable<? extends Throwable> failableRunnable2 : failableRunnableArr) {
                Objects.requireNonNull(failableRunnable2, "runnable");
            }
        }
        Throwable th = null;
        try {
            failableRunnable.run();
        } catch (Throwable th2) {
            th = th2;
        }
        if (failableRunnableArr != null) {
            for (FailableRunnable<? extends Throwable> failableRunnable3 : failableRunnableArr) {
                try {
                    failableRunnable3.run();
                } catch (Throwable th3) {
                    if (th == null) {
                        th = th3;
                    }
                }
            }
        }
        if (th != null) {
            try {
                failableConsumer.accept(th);
            } catch (Throwable th4) {
                throw rethrow(th4);
            }
        }
    }

    public static <O1, O2, T extends Throwable> void accept(final FailableBiConsumer<O1, O2, T> failableBiConsumer, final O1 o1, final O2 o2) {
        run(new FailableRunnable() { // from class: org.apache.commons.lang3.e
            @Override // org.apache.commons.lang3.Functions.FailableRunnable
            public final void run() {
                failableBiConsumer.accept(o1, o2);
            }
        });
    }

    public static <I1, I2, O, T extends Throwable> O apply(final FailableBiFunction<I1, I2, O, T> failableBiFunction, final I1 i1, final I2 i2) {
        return (O) get(new FailableSupplier() { // from class: org.apache.commons.lang3.g
            @Override // org.apache.commons.lang3.Functions.FailableSupplier
            public final Object get() {
                return failableBiFunction.apply(i1, i2);
            }
        });
    }

    public static <O> Streams.FailableStream<O> stream(Collection<O> collection) {
        return new Streams.FailableStream<>(collection.stream());
    }

    public static <O1, O2, T extends Throwable> boolean test(final FailableBiPredicate<O1, O2, T> failableBiPredicate, final O1 o1, final O2 o2) {
        return ((Boolean) get(new FailableSupplier() { // from class: org.apache.commons.lang3.m
            @Override // org.apache.commons.lang3.Functions.FailableSupplier
            public final Object get() {
                return Boolean.valueOf(failableBiPredicate.test(o1, o2));
            }
        })).booleanValue();
    }

    @SafeVarargs
    public static void tryWithResources(FailableRunnable<? extends Throwable> failableRunnable, FailableRunnable<? extends Throwable>... failableRunnableArr) {
        tryWithResources(failableRunnable, null, failableRunnableArr);
    }
}
