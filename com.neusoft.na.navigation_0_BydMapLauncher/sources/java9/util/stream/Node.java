package java9.util.stream;

import java9.util.Spliterator;
import java9.util.function.Consumer;
import java9.util.function.DoubleConsumer;
import java9.util.function.IntConsumer;
import java9.util.function.IntFunction;
import java9.util.function.LongConsumer;
import java9.util.stream.Sink;

/* JADX INFO: loaded from: classes3.dex */
interface Node<T> {

    public interface Builder<T> extends Sink<T> {

        public interface OfDouble extends Builder<Double>, Sink.OfDouble {
            @Override // java9.util.stream.Node.Builder
            /* JADX INFO: renamed from: build */
            Node<Double> build2();
        }

        public interface OfInt extends Builder<Integer>, Sink.OfInt {
            @Override // java9.util.stream.Node.Builder
            /* JADX INFO: renamed from: build */
            Node<Integer> build2();
        }

        public interface OfLong extends Builder<Long>, Sink.OfLong {
            @Override // java9.util.stream.Node.Builder
            /* JADX INFO: renamed from: build */
            Node<Long> build2();
        }

        /* JADX INFO: renamed from: build */
        Node<T> build2();
    }

    public interface OfDouble extends OfPrimitive<Double, DoubleConsumer, double[], Spliterator.OfDouble, OfDouble> {
        void copyInto(Double[] dArr, int i);

        @Override // java9.util.stream.Node
        void forEach(Consumer<? super Double> consumer);

        @Override // java9.util.stream.Node
        StreamShape getShape();

        @Override // java9.util.stream.Node.OfPrimitive
        double[] newArray(int i);

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        OfDouble truncate(long j, long j2, IntFunction<Double[]> intFunction);
    }

    public interface OfInt extends OfPrimitive<Integer, IntConsumer, int[], Spliterator.OfInt, OfInt> {
        void copyInto(Integer[] numArr, int i);

        @Override // java9.util.stream.Node
        void forEach(Consumer<? super Integer> consumer);

        @Override // java9.util.stream.Node
        StreamShape getShape();

        @Override // java9.util.stream.Node.OfPrimitive
        int[] newArray(int i);

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        OfInt truncate(long j, long j2, IntFunction<Integer[]> intFunction);
    }

    public interface OfLong extends OfPrimitive<Long, LongConsumer, long[], Spliterator.OfLong, OfLong> {
        void copyInto(Long[] lArr, int i);

        @Override // java9.util.stream.Node
        void forEach(Consumer<? super Long> consumer);

        @Override // java9.util.stream.Node
        StreamShape getShape();

        @Override // java9.util.stream.Node.OfPrimitive
        long[] newArray(int i);

        @Override // java9.util.stream.Node.OfPrimitive, java9.util.stream.Node
        OfLong truncate(long j, long j2, IntFunction<Long[]> intFunction);
    }

    public interface OfPrimitive<T, T_CONS, T_ARR, T_SPLITR extends Spliterator.OfPrimitive<T, T_CONS, T_SPLITR>, T_NODE extends OfPrimitive<T, T_CONS, T_ARR, T_SPLITR, T_NODE>> extends Node<T> {
        @Override // java9.util.stream.Node
        T[] asArray(IntFunction<T[]> intFunction);

        T_ARR asPrimitiveArray();

        void copyInto(T_ARR t_arr, int i);

        void forEach(T_CONS t_cons);

        @Override // java9.util.stream.Node
        T_NODE getChild(int i);

        T_ARR newArray(int i);

        @Override // java9.util.stream.Node
        T_SPLITR spliterator();

        @Override // java9.util.stream.Node
        T_NODE truncate(long j, long j2, IntFunction<T[]> intFunction);
    }

    T[] asArray(IntFunction<T[]> intFunction);

    void copyInto(T[] tArr, int i);

    long count();

    void forEach(Consumer<? super T> consumer);

    Node<T> getChild(int i);

    int getChildCount();

    StreamShape getShape();

    Spliterator<T> spliterator();

    Node<T> truncate(long j, long j2, IntFunction<T[]> intFunction);
}
