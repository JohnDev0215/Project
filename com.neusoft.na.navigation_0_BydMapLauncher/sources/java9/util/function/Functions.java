package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class Functions {
    static /* synthetic */ Object lambda$identity$2(Object obj) {
        return obj;
    }

    public static <R, T, V> Function<V, R> compose(Function<? super T, ? extends R> function, Function<? super V, ? extends T> function2) {
        Objects.requireNonNull(function);
        Objects.requireNonNull(function2);
        return Functions$$Lambda$1.lambdaFactory$(function, function2);
    }

    public static <R, T, V> Function<T, V> andThen(Function<? super T, ? extends R> function, Function<? super R, ? extends V> function2) {
        Objects.requireNonNull(function);
        Objects.requireNonNull(function2);
        return Functions$$Lambda$2.lambdaFactory$(function2, function);
    }

    static /* synthetic */ Object lambda$andThen$1(Function function, Function function2, Object obj) {
        return function.apply(function2.apply(obj));
    }

    public static <T> Function<T, T> identity() {
        return Functions$$Lambda$3.instance;
    }

    private Functions() {
    }
}
