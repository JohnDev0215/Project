package java9.util.stream;

import java.util.Iterator;
import java9.util.DoubleSummaryStatistics;
import java9.util.OptionalDouble;
import java9.util.Spliterator;
import java9.util.function.BiConsumer;
import java9.util.function.DoubleBinaryOperator;
import java9.util.function.DoubleConsumer;
import java9.util.function.DoubleFunction;
import java9.util.function.DoublePredicate;
import java9.util.function.DoubleToIntFunction;
import java9.util.function.DoubleToLongFunction;
import java9.util.function.DoubleUnaryOperator;
import java9.util.function.ObjDoubleConsumer;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
public interface DoubleStream extends BaseStream<Double, DoubleStream> {

    public interface Builder extends DoubleConsumer {
        @Override // java9.util.function.DoubleConsumer
        void accept(double d);

        Builder add(double d);

        DoubleStream build();
    }

    boolean allMatch(DoublePredicate doublePredicate);

    boolean anyMatch(DoublePredicate doublePredicate);

    OptionalDouble average();

    Stream<Double> boxed();

    <R> R collect(Supplier<R> supplier, ObjDoubleConsumer<R> objDoubleConsumer, BiConsumer<R, R> biConsumer);

    long count();

    DoubleStream distinct();

    DoubleStream dropWhile(DoublePredicate doublePredicate);

    DoubleStream filter(DoublePredicate doublePredicate);

    OptionalDouble findAny();

    OptionalDouble findFirst();

    DoubleStream flatMap(DoubleFunction<? extends DoubleStream> doubleFunction);

    void forEach(DoubleConsumer doubleConsumer);

    void forEachOrdered(DoubleConsumer doubleConsumer);

    @Override // java9.util.stream.BaseStream
    Iterator<Double> iterator();

    DoubleStream limit(long j);

    DoubleStream map(DoubleUnaryOperator doubleUnaryOperator);

    IntStream mapToInt(DoubleToIntFunction doubleToIntFunction);

    LongStream mapToLong(DoubleToLongFunction doubleToLongFunction);

    <U> Stream<U> mapToObj(DoubleFunction<? extends U> doubleFunction);

    OptionalDouble max();

    OptionalDouble min();

    boolean noneMatch(DoublePredicate doublePredicate);

    @Override // java9.util.stream.BaseStream
    DoubleStream parallel();

    DoubleStream peek(DoubleConsumer doubleConsumer);

    double reduce(double d, DoubleBinaryOperator doubleBinaryOperator);

    OptionalDouble reduce(DoubleBinaryOperator doubleBinaryOperator);

    @Override // java9.util.stream.BaseStream
    DoubleStream sequential();

    DoubleStream skip(long j);

    DoubleStream sorted();

    @Override // java9.util.stream.BaseStream
    /* JADX INFO: renamed from: spliterator */
    Spliterator<Double> spliterator2();

    double sum();

    DoubleSummaryStatistics summaryStatistics();

    DoubleStream takeWhile(DoublePredicate doublePredicate);

    double[] toArray();
}
