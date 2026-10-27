package java9.util.stream;

import java.util.Comparator;
import java9.util.Optional;
import java9.util.function.BiConsumer;
import java9.util.function.BiFunction;
import java9.util.function.BinaryOperator;
import java9.util.function.Consumer;
import java9.util.function.Function;
import java9.util.function.IntFunction;
import java9.util.function.Predicate;
import java9.util.function.Supplier;
import java9.util.function.ToDoubleFunction;
import java9.util.function.ToIntFunction;
import java9.util.function.ToLongFunction;

/* JADX INFO: loaded from: classes3.dex */
public interface Stream<T> extends BaseStream<T, Stream<T>> {

    public interface Builder<T> extends Consumer<T> {
        @Override // java9.util.function.Consumer
        void accept(T t);

        Builder<T> add(T t);

        Stream<T> build();
    }

    boolean allMatch(Predicate<? super T> predicate);

    boolean anyMatch(Predicate<? super T> predicate);

    <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> biConsumer, BiConsumer<R, R> biConsumer2);

    <R, A> R collect(Collector<? super T, A, R> collector);

    long count();

    Stream<T> distinct();

    Stream<T> dropWhile(Predicate<? super T> predicate);

    Stream<T> filter(Predicate<? super T> predicate);

    Optional<T> findAny();

    Optional<T> findFirst();

    <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> function);

    DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> function);

    IntStream flatMapToInt(Function<? super T, ? extends IntStream> function);

    LongStream flatMapToLong(Function<? super T, ? extends LongStream> function);

    void forEach(Consumer<? super T> consumer);

    void forEachOrdered(Consumer<? super T> consumer);

    Stream<T> limit(long j);

    <R> Stream<R> map(Function<? super T, ? extends R> function);

    DoubleStream mapToDouble(ToDoubleFunction<? super T> toDoubleFunction);

    IntStream mapToInt(ToIntFunction<? super T> toIntFunction);

    LongStream mapToLong(ToLongFunction<? super T> toLongFunction);

    Optional<T> max(Comparator<? super T> comparator);

    Optional<T> min(Comparator<? super T> comparator);

    boolean noneMatch(Predicate<? super T> predicate);

    Stream<T> peek(Consumer<? super T> consumer);

    <U> U reduce(U u, BiFunction<U, ? super T, U> biFunction, BinaryOperator<U> binaryOperator);

    T reduce(T t, BinaryOperator<T> binaryOperator);

    Optional<T> reduce(BinaryOperator<T> binaryOperator);

    Stream<T> skip(long j);

    Stream<T> sorted();

    Stream<T> sorted(Comparator<? super T> comparator);

    Stream<T> takeWhile(Predicate<? super T> predicate);

    Object[] toArray();

    <A> A[] toArray(IntFunction<A[]> intFunction);
}
