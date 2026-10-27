package java9.util.stream;

import java.util.Iterator;
import java9.util.LongSummaryStatistics;
import java9.util.OptionalDouble;
import java9.util.OptionalLong;
import java9.util.Spliterator;
import java9.util.function.BiConsumer;
import java9.util.function.LongBinaryOperator;
import java9.util.function.LongConsumer;
import java9.util.function.LongFunction;
import java9.util.function.LongPredicate;
import java9.util.function.LongToDoubleFunction;
import java9.util.function.LongToIntFunction;
import java9.util.function.LongUnaryOperator;
import java9.util.function.ObjLongConsumer;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
public interface LongStream extends BaseStream<Long, LongStream> {

    public interface Builder extends LongConsumer {
        @Override // java9.util.function.LongConsumer
        void accept(long j);

        Builder add(long j);

        LongStream build();
    }

    boolean allMatch(LongPredicate longPredicate);

    boolean anyMatch(LongPredicate longPredicate);

    DoubleStream asDoubleStream();

    OptionalDouble average();

    Stream<Long> boxed();

    <R> R collect(Supplier<R> supplier, ObjLongConsumer<R> objLongConsumer, BiConsumer<R, R> biConsumer);

    long count();

    LongStream distinct();

    LongStream dropWhile(LongPredicate longPredicate);

    LongStream filter(LongPredicate longPredicate);

    OptionalLong findAny();

    OptionalLong findFirst();

    LongStream flatMap(LongFunction<? extends LongStream> longFunction);

    void forEach(LongConsumer longConsumer);

    void forEachOrdered(LongConsumer longConsumer);

    @Override // java9.util.stream.BaseStream
    Iterator<Long> iterator();

    LongStream limit(long j);

    LongStream map(LongUnaryOperator longUnaryOperator);

    DoubleStream mapToDouble(LongToDoubleFunction longToDoubleFunction);

    IntStream mapToInt(LongToIntFunction longToIntFunction);

    <U> Stream<U> mapToObj(LongFunction<? extends U> longFunction);

    OptionalLong max();

    OptionalLong min();

    boolean noneMatch(LongPredicate longPredicate);

    @Override // java9.util.stream.BaseStream
    LongStream parallel();

    LongStream peek(LongConsumer longConsumer);

    long reduce(long j, LongBinaryOperator longBinaryOperator);

    OptionalLong reduce(LongBinaryOperator longBinaryOperator);

    @Override // java9.util.stream.BaseStream
    LongStream sequential();

    LongStream skip(long j);

    LongStream sorted();

    @Override // java9.util.stream.BaseStream
    /* JADX INFO: renamed from: spliterator */
    Spliterator<Long> spliterator2();

    long sum();

    LongSummaryStatistics summaryStatistics();

    LongStream takeWhile(LongPredicate longPredicate);

    long[] toArray();
}
