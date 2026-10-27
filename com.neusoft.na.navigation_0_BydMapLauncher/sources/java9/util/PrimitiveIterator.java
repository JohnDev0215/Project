package java9.util;

import java.util.Iterator;
import java9.util.function.DoubleConsumer;
import java9.util.function.IntConsumer;
import java9.util.function.LongConsumer;

/* JADX INFO: loaded from: classes3.dex */
public interface PrimitiveIterator<T, T_CONS> extends Iterator<T> {

    public interface OfDouble extends PrimitiveIterator<Double, DoubleConsumer> {
        void forEachRemaining(DoubleConsumer doubleConsumer);

        @Override // java.util.Iterator
        Double next();

        double nextDouble();
    }

    public interface OfInt extends PrimitiveIterator<Integer, IntConsumer> {
        void forEachRemaining(IntConsumer intConsumer);

        @Override // java.util.Iterator
        Integer next();

        int nextInt();
    }

    public interface OfLong extends PrimitiveIterator<Long, LongConsumer> {
        void forEachRemaining(LongConsumer longConsumer);

        @Override // java.util.Iterator
        Long next();

        long nextLong();
    }

    void forEachRemaining(T_CONS t_cons);
}
