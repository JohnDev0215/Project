package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class BiFunctions {
    public static <R, T, U, V> BiFunction<T, U, V> andThen(BiFunction<? super T, ? super U, ? extends R> biFunction, Function<? super R, ? extends V> function) {
        Objects.requireNonNull(biFunction);
        Objects.requireNonNull(function);
        return BiFunctions$$Lambda$1.lambdaFactory$(function, biFunction);
    }

    private BiFunctions() {
    }
}
