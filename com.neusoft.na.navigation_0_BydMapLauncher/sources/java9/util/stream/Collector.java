package java9.util.stream;

import java.util.Set;
import java9.util.function.BiConsumer;
import java9.util.function.BinaryOperator;
import java9.util.function.Function;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
public interface Collector<T, A, R> {

    public enum Characteristics {
        CONCURRENT,
        UNORDERED,
        IDENTITY_FINISH
    }

    BiConsumer<A, T> accumulator();

    Set<Characteristics> characteristics();

    BinaryOperator<A> combiner();

    Function<A, R> finisher();

    Supplier<A> supplier();
}
