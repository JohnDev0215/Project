package java9.util;

import java.util.Comparator;
import java9.util.function.Consumer;
import java9.util.function.DoubleConsumer;
import java9.util.function.IntConsumer;
import java9.util.function.LongConsumer;

/* JADX INFO: loaded from: classes3.dex */
public interface Spliterator<T> {
    public static final int CONCURRENT = 4096;
    public static final int DISTINCT = 1;
    public static final int IMMUTABLE = 1024;
    public static final int NONNULL = 256;
    public static final int ORDERED = 16;
    public static final int SIZED = 64;
    public static final int SORTED = 4;
    public static final int SUBSIZED = 16384;

    public interface OfDouble extends OfPrimitive<Double, DoubleConsumer, OfDouble> {
        @Override // java9.util.Spliterator
        void forEachRemaining(Consumer<? super Double> consumer);

        void forEachRemaining(DoubleConsumer doubleConsumer);

        @Override // java9.util.Spliterator
        boolean tryAdvance(Consumer<? super Double> consumer);

        boolean tryAdvance(DoubleConsumer doubleConsumer);

        @Override // java9.util.Spliterator.OfPrimitive, java9.util.Spliterator
        OfDouble trySplit();
    }

    public interface OfInt extends OfPrimitive<Integer, IntConsumer, OfInt> {
        @Override // java9.util.Spliterator
        void forEachRemaining(Consumer<? super Integer> consumer);

        void forEachRemaining(IntConsumer intConsumer);

        @Override // java9.util.Spliterator
        boolean tryAdvance(Consumer<? super Integer> consumer);

        boolean tryAdvance(IntConsumer intConsumer);

        @Override // java9.util.Spliterator.OfPrimitive, java9.util.Spliterator
        OfInt trySplit();
    }

    public interface OfLong extends OfPrimitive<Long, LongConsumer, OfLong> {
        @Override // java9.util.Spliterator
        void forEachRemaining(Consumer<? super Long> consumer);

        void forEachRemaining(LongConsumer longConsumer);

        @Override // java9.util.Spliterator
        boolean tryAdvance(Consumer<? super Long> consumer);

        boolean tryAdvance(LongConsumer longConsumer);

        @Override // java9.util.Spliterator.OfPrimitive, java9.util.Spliterator
        OfLong trySplit();
    }

    public interface OfPrimitive<T, T_CONS, T_SPLITR extends OfPrimitive<T, T_CONS, T_SPLITR>> extends Spliterator<T> {
        void forEachRemaining(T_CONS t_cons);

        boolean tryAdvance(T_CONS t_cons);

        @Override // java9.util.Spliterator
        T_SPLITR trySplit();
    }

    int characteristics();

    long estimateSize();

    void forEachRemaining(Consumer<? super T> consumer);

    Comparator<? super T> getComparator();

    long getExactSizeIfKnown();

    boolean hasCharacteristics(int i);

    boolean tryAdvance(Consumer<? super T> consumer);

    Spliterator<T> trySplit();
}
