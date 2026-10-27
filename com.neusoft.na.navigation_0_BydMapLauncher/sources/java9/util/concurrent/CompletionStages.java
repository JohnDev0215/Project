package java9.util.concurrent;

import java.util.concurrent.Executor;
import java9.util.function.BiFunction;
import java9.util.function.Function;
import java9.util.function.Functions;

/* JADX INFO: loaded from: classes3.dex */
public final class CompletionStages {
    public static <T> CompletionStage<T> exceptionallyAsync(final CompletionStage<T> completionStage, final Function<Throwable, ? extends T> function) {
        return completionStage.handle(new BiFunction<T, Throwable, CompletionStage<T>>() { // from class: java9.util.concurrent.CompletionStages.1
            @Override // java9.util.function.BiFunction
            public CompletionStage<T> apply(T t, Throwable th) {
                CompletionStage<T> completionStage2 = completionStage;
                return th == null ? completionStage2 : (CompletionStage<T>) completionStage2.handleAsync(new BiFunction<T, Throwable, T>() { // from class: java9.util.concurrent.CompletionStages.1.1
                    @Override // java9.util.function.BiFunction
                    public T apply(T t2, Throwable th2) {
                        return (T) function.apply(th2);
                    }
                });
            }
        }).thenCompose(Functions.identity());
    }

    public static <T> CompletionStage<T> exceptionallyAsync(final CompletionStage<T> completionStage, final Function<Throwable, ? extends T> function, final Executor executor) {
        return completionStage.handle(new BiFunction<T, Throwable, CompletionStage<T>>() { // from class: java9.util.concurrent.CompletionStages.2
            @Override // java9.util.function.BiFunction
            public CompletionStage<T> apply(T t, Throwable th) {
                CompletionStage<T> completionStage2 = completionStage;
                return th == null ? completionStage2 : (CompletionStage<T>) completionStage2.handleAsync(new BiFunction<T, Throwable, T>() { // from class: java9.util.concurrent.CompletionStages.2.1
                    @Override // java9.util.function.BiFunction
                    public T apply(T t2, Throwable th2) {
                        return (T) function.apply(th2);
                    }
                }, executor);
            }
        }).thenCompose(Functions.identity());
    }

    public static <T> CompletionStage<T> exceptionallyCompose(final CompletionStage<T> completionStage, final Function<Throwable, ? extends CompletionStage<T>> function) {
        return completionStage.handle(new BiFunction<T, Throwable, CompletionStage<T>>() { // from class: java9.util.concurrent.CompletionStages.3
            @Override // java9.util.function.BiFunction
            public CompletionStage<T> apply(T t, Throwable th) {
                return th == null ? completionStage : (CompletionStage) function.apply(th);
            }
        }).thenCompose(Functions.identity());
    }

    public static <T> CompletionStage<T> exceptionallyComposeAsync(final CompletionStage<T> completionStage, final Function<Throwable, ? extends CompletionStage<T>> function) {
        return completionStage.handle(new BiFunction<T, Throwable, CompletionStage<T>>() { // from class: java9.util.concurrent.CompletionStages.4
            @Override // java9.util.function.BiFunction
            public CompletionStage<T> apply(T t, Throwable th) {
                CompletionStage<T> completionStage2 = completionStage;
                return th == null ? completionStage2 : completionStage2.handleAsync(new BiFunction<T, Throwable, CompletionStage<T>>() { // from class: java9.util.concurrent.CompletionStages.4.1
                    @Override // java9.util.function.BiFunction
                    public CompletionStage<T> apply(T t2, Throwable th2) {
                        return (CompletionStage) function.apply(th2);
                    }
                }).thenCompose(Functions.identity());
            }
        }).thenCompose(Functions.identity());
    }

    public static <T> CompletionStage<T> exceptionallyComposeAsync(final CompletionStage<T> completionStage, final Function<Throwable, ? extends CompletionStage<T>> function, final Executor executor) {
        return completionStage.handle(new BiFunction<T, Throwable, CompletionStage<T>>() { // from class: java9.util.concurrent.CompletionStages.5
            @Override // java9.util.function.BiFunction
            public CompletionStage<T> apply(T t, Throwable th) {
                CompletionStage<T> completionStage2 = completionStage;
                return th == null ? completionStage2 : completionStage2.handleAsync(new BiFunction<T, Throwable, CompletionStage<T>>() { // from class: java9.util.concurrent.CompletionStages.5.1
                    @Override // java9.util.function.BiFunction
                    public CompletionStage<T> apply(T t2, Throwable th2) {
                        return (CompletionStage) function.apply(th2);
                    }
                }, executor).thenCompose(Functions.identity());
            }
        }).thenCompose(Functions.identity());
    }

    private CompletionStages() {
    }
}
