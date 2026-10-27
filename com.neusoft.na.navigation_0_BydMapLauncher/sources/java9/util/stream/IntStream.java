package java9.util.stream;

import java.util.Iterator;
import java9.util.IntSummaryStatistics;
import java9.util.OptionalDouble;
import java9.util.OptionalInt;
import java9.util.Spliterator;
import java9.util.function.BiConsumer;
import java9.util.function.IntBinaryOperator;
import java9.util.function.IntConsumer;
import java9.util.function.IntFunction;
import java9.util.function.IntPredicate;
import java9.util.function.IntToDoubleFunction;
import java9.util.function.IntToLongFunction;
import java9.util.function.IntUnaryOperator;
import java9.util.function.ObjIntConsumer;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
public interface IntStream extends BaseStream<Integer, IntStream> {

    public interface Builder extends IntConsumer {
        @Override // java9.util.function.IntConsumer
        void accept(int i);

        Builder add(int i);

        IntStream build();
    }

    boolean allMatch(IntPredicate intPredicate);

    boolean anyMatch(IntPredicate intPredicate);

    DoubleStream asDoubleStream();

    LongStream asLongStream();

    OptionalDouble average();

    Stream<Integer> boxed();

    <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> objIntConsumer, BiConsumer<R, R> biConsumer);

    long count();

    IntStream distinct();

    IntStream dropWhile(IntPredicate intPredicate);

    IntStream filter(IntPredicate intPredicate);

    OptionalInt findAny();

    OptionalInt findFirst();

    IntStream flatMap(IntFunction<? extends IntStream> intFunction);

    void forEach(IntConsumer intConsumer);

    void forEachOrdered(IntConsumer intConsumer);

    @Override // java9.util.stream.BaseStream
    Iterator<Integer> iterator();

    IntStream limit(long j);

    IntStream map(IntUnaryOperator intUnaryOperator);

    DoubleStream mapToDouble(IntToDoubleFunction intToDoubleFunction);

    LongStream mapToLong(IntToLongFunction intToLongFunction);

    <U> Stream<U> mapToObj(IntFunction<? extends U> intFunction);

    OptionalInt max();

    OptionalInt min();

    boolean noneMatch(IntPredicate intPredicate);

    @Override // java9.util.stream.BaseStream
    IntStream parallel();

    IntStream peek(IntConsumer intConsumer);

    int reduce(int i, IntBinaryOperator intBinaryOperator);

    OptionalInt reduce(IntBinaryOperator intBinaryOperator);

    @Override // java9.util.stream.BaseStream
    IntStream sequential();

    IntStream skip(long j);

    IntStream sorted();

    @Override // java9.util.stream.BaseStream
    /* JADX INFO: renamed from: spliterator */
    Spliterator<Integer> spliterator2();

    int sum();

    IntSummaryStatistics summaryStatistics();

    IntStream takeWhile(IntPredicate intPredicate);

    int[] toArray();
}
