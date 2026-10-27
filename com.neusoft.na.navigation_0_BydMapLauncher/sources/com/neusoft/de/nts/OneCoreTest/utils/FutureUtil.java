package com.neusoft.de.nts.OneCoreTest.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java9.util.concurrent.CompletableFuture;
import java9.util.concurrent.CompletionStage;
import java9.util.function.Consumer;
import java9.util.function.Function;
import java9.util.function.Supplier;
import org.apache.commons.lang3.ArrayUtils;

/* JADX INFO: loaded from: classes2.dex */
public final class FutureUtil {
    private static final Logger logger = Logger.getLogger((Class<?>) FutureUtil.class);
    private static final Runnable EMPTY_RUNNABLE = new Runnable() { // from class: com.neusoft.de.nts.OneCoreTest.utils.FutureUtil.1
        @Override // java.lang.Runnable
        public void run() {
        }
    };

    private FutureUtil() {
    }

    public static <T> CompletableFuture<List<T>> allResultsOf(final Collection<CompletableFuture<T>> collection) {
        if (collection.isEmpty()) {
            return CompletableFuture.completedFuture(new ArrayList());
        }
        return (CompletableFuture<List<T>>) CompletableFuture.allOf((CompletableFuture[]) collection.toArray(new CompletableFuture[0])).thenApply((Function<? super Void, ? extends U>) new Function<Void, List<T>>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.FutureUtil.2
            @Override // java9.util.function.Function
            public List<T> apply(Void r3) {
                ArrayList arrayList = new ArrayList(collection.size());
                Iterator it = collection.iterator();
                while (it.hasNext()) {
                    arrayList.add(((CompletableFuture) it.next()).join());
                }
                return arrayList;
            }
        });
    }

    public static <T> CompletableFuture<T> resultOfFirst(final CompletableFuture<T> completableFuture, CompletableFuture<?>... completableFutureArr) {
        return (CompletableFuture<T>) CompletableFuture.allOf((CompletableFuture[]) ArrayUtils.add((CompletableFuture<T>[]) completableFutureArr, completableFuture)).thenCompose((Function<? super Void, ? extends CompletionStage<U>>) new Function<Void, CompletionStage<T>>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.FutureUtil.3
            @Override // java9.util.function.Function
            public CompletionStage<T> apply(Void r1) {
                return completableFuture;
            }
        });
    }

    public static <T> T handleException(Throwable th, T t) {
        logger.error(th, "Exception occurred: " + th.getMessage(), new Object[0]);
        return t;
    }

    public static <T> T handleException(Throwable th) {
        return (T) handleException(th, null);
    }

    public static <T> T logAndThrow(Throwable th) {
        logger.error("" + th.getMessage());
        throw new RuntimeException(th);
    }

    public static <T> T handleExceptionWithConsumer(Throwable th, Consumer<Throwable> consumer) {
        handleException(th);
        consumer.accept(th);
        return null;
    }

    public static <T> T handleExceptionWithFunction(Throwable th, Function<Throwable, T> function) {
        handleException(th);
        return function.apply(th);
    }

    public static <T> CompletableFuture<T> exceptionallyCompleted(Throwable th) {
        CompletableFuture<T> completableFuture = new CompletableFuture<>();
        completableFuture.completeExceptionally(th);
        return completableFuture;
    }

    public static CompletableFuture<Void> runOnOtherThread(Runnable runnable, Executor executor) {
        return CompletableFuture.runAsync(runnable).thenApplyAsync((Function<? super Void, ? extends U>) new Function<Void, Void>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.FutureUtil.4
            @Override // java9.util.function.Function
            public Void apply(Void r1) {
                return r1;
            }
        }, executor);
    }

    public static <T, U> CompletableFuture<U> repeatCompose(int i, CompletableFuture<T> completableFuture, final Supplier<CompletableFuture<U>> supplier) {
        CompletableFuture<U> completableFutureThenApply = completableFuture.thenApply((Function) new Function<T, U>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.FutureUtil.5
            @Override // java9.util.function.Function
            public U apply(T t) {
                return null;
            }
        });
        for (int i2 = 0; i2 < i; i2++) {
            completableFutureThenApply = completableFutureThenApply.thenCompose((Function<? super U, ? extends CompletionStage<U>>) new Function<U, CompletionStage<U>>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.FutureUtil.6
                @Override // java9.util.function.Function
                public CompletionStage<U> apply(U u) {
                    return (CompletionStage) supplier.get();
                }
            });
        }
        return completableFutureThenApply;
    }

    public static <U> CompletableFuture<U> repeatCompose(int i, final Supplier<CompletableFuture<U>> supplier) {
        CompletableFuture<U> completableFutureCompletedFuture = CompletableFuture.completedFuture(null);
        for (int i2 = 0; i2 < i; i2++) {
            completableFutureCompletedFuture = completableFutureCompletedFuture.thenCompose((Function<? super U, ? extends CompletionStage<U>>) new Function<U, CompletionStage<U>>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.FutureUtil.7
                @Override // java9.util.function.Function
                public CompletionStage<U> apply(U u) {
                    return (CompletionStage) supplier.get();
                }
            });
        }
        return completableFutureCompletedFuture;
    }

    public static <U> CompletableFuture<U> repeatComposeAsync(int i, Executor executor, final Supplier<CompletableFuture<U>> supplier) {
        CompletableFuture<U> completableFutureSupplyAsync = CompletableFuture.supplyAsync(new Supplier<U>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.FutureUtil.8
            @Override // java9.util.function.Supplier
            public U get() {
                return null;
            }
        }, executor);
        for (int i2 = 0; i2 < i; i2++) {
            completableFutureSupplyAsync = completableFutureSupplyAsync.thenCompose((Function<? super U, ? extends CompletionStage<U>>) new Function<U, CompletionStage<U>>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.FutureUtil.9
                @Override // java9.util.function.Function
                public CompletionStage<U> apply(U u) {
                    return (CompletionStage) supplier.get();
                }
            });
        }
        return completableFutureSupplyAsync;
    }

    public static <T, U> CompletableFuture<U> repeatSupply(int i, CompletableFuture<T> completableFuture, final Supplier<U> supplier) {
        CompletableFuture<U> completableFutureThenApply = completableFuture.thenApply((Function) new Function<T, U>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.FutureUtil.10
            @Override // java9.util.function.Function
            public U apply(T t) {
                return null;
            }
        });
        for (int i2 = 0; i2 < i; i2++) {
            completableFutureThenApply = completableFutureThenApply.thenApply((Function<? super U, ? extends U>) new Function<U, U>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.FutureUtil.11
                @Override // java9.util.function.Function
                public U apply(U u) {
                    return (U) supplier.get();
                }
            });
        }
        return completableFutureThenApply;
    }

    public static <U> CompletableFuture<U> repeatSupply(int i, final Supplier<U> supplier) {
        CompletableFuture<U> completableFutureCompletedFuture = CompletableFuture.completedFuture(null);
        for (int i2 = 0; i2 < i; i2++) {
            completableFutureCompletedFuture = completableFutureCompletedFuture.thenApply((Function<? super U, ? extends U>) new Function<U, U>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.FutureUtil.12
                @Override // java9.util.function.Function
                public U apply(U u) {
                    return (U) supplier.get();
                }
            });
        }
        return completableFutureCompletedFuture;
    }

    public static <U> CompletableFuture<U> repeatSupplyAsync(int i, Executor executor, final Supplier<U> supplier) {
        CompletableFuture<U> completableFutureSupplyAsync = CompletableFuture.supplyAsync(new Supplier<U>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.FutureUtil.13
            @Override // java9.util.function.Supplier
            public U get() {
                return null;
            }
        }, executor);
        for (int i2 = 0; i2 < i; i2++) {
            completableFutureSupplyAsync = completableFutureSupplyAsync.thenApply((Function<? super U, ? extends U>) new Function<U, U>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.FutureUtil.14
                @Override // java9.util.function.Function
                public U apply(U u) {
                    return (U) supplier.get();
                }
            });
        }
        return completableFutureSupplyAsync;
    }

    public static <U> CompletableFuture<Void> repeatRun(int i, CompletableFuture<U> completableFuture, Runnable runnable) {
        CompletableFuture<Void> completableFutureThenApply = completableFuture.thenApply((Function<? super U, ? extends U>) new Function<U, Void>() { // from class: com.neusoft.de.nts.OneCoreTest.utils.FutureUtil.15
            @Override // java9.util.function.Function
            public Void apply(U u) {
                return null;
            }
        });
        for (int i2 = 0; i2 < i; i2++) {
            completableFutureThenApply = completableFutureThenApply.thenRun(runnable);
        }
        return completableFutureThenApply;
    }

    public static CompletableFuture<Void> repeatRun(int i, Runnable runnable) {
        CompletableFuture<Void> completableFutureCompletedFuture = CompletableFuture.completedFuture(null);
        for (int i2 = 0; i2 < i; i2++) {
            completableFutureCompletedFuture = completableFutureCompletedFuture.thenRun(runnable);
        }
        return completableFutureCompletedFuture;
    }

    public static CompletableFuture<Void> repeatRunAsync(int i, Executor executor, Runnable runnable) {
        CompletableFuture<Void> completableFutureRunAsync = CompletableFuture.runAsync(EMPTY_RUNNABLE, executor);
        for (int i2 = 0; i2 < i; i2++) {
            completableFutureRunAsync = completableFutureRunAsync.thenRun(runnable);
        }
        return completableFutureRunAsync;
    }
}
