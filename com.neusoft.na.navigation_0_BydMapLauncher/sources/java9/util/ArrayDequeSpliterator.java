package java9.util;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java9.util.function.Consumer;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
final class ArrayDequeSpliterator<E> implements Spliterator<E> {
    private static final long DATA_OFF;
    private static final long HEAD_OFF;
    private static final long TAIL_OFF;
    private static final Unsafe U;
    private final ArrayDeque<E> deq;
    private int fence;
    private int index;

    @Override // java9.util.Spliterator
    public int characteristics() {
        return 16720;
    }

    private ArrayDequeSpliterator(ArrayDeque<E> arrayDeque, int i, int i2) {
        this.deq = arrayDeque;
        this.index = i;
        this.fence = i2;
    }

    static <T> Spliterator<T> spliterator(ArrayDeque<T> arrayDeque) {
        return new ArrayDequeSpliterator(arrayDeque, -1, -1);
    }

    private int getFence() {
        int i = this.fence;
        if (i >= 0) {
            return i;
        }
        int tail = getTail(this.deq);
        this.fence = tail;
        this.index = getHead(this.deq);
        return tail;
    }

    @Override // java9.util.Spliterator
    public ArrayDequeSpliterator<E> trySplit() {
        int fence = getFence();
        int i = this.index;
        int length = getData(this.deq).length;
        if (i == fence) {
            return null;
        }
        int i2 = length - 1;
        if (((i + 1) & i2) == fence) {
            return null;
        }
        if (i > fence) {
            fence += length;
        }
        int i3 = ((fence + i) >>> 1) & i2;
        ArrayDeque<E> arrayDeque = this.deq;
        this.index = i3;
        return new ArrayDequeSpliterator<>(arrayDeque, i, i3);
    }

    @Override // java9.util.Spliterator
    public void forEachRemaining(Consumer<? super E> consumer) {
        Objects.requireNonNull(consumer);
        Object[] data = getData(this.deq);
        int length = data.length - 1;
        int fence = getFence();
        int i = this.index;
        this.index = fence;
        while (i != fence) {
            Object obj = data[i];
            i = (i + 1) & length;
            if (obj == null) {
                throw new ConcurrentModificationException();
            }
            consumer.accept(obj);
        }
    }

    @Override // java9.util.Spliterator
    public boolean tryAdvance(Consumer<? super E> consumer) {
        Objects.requireNonNull(consumer);
        Object[] data = getData(this.deq);
        int length = data.length - 1;
        getFence();
        int i = this.index;
        if (i == this.fence) {
            return false;
        }
        Object obj = data[i];
        this.index = length & (i + 1);
        if (obj == null) {
            throw new ConcurrentModificationException();
        }
        consumer.accept(obj);
        return true;
    }

    @Override // java9.util.Spliterator
    public long estimateSize() {
        int fence = getFence() - this.index;
        if (fence < 0) {
            fence += getData(this.deq).length;
        }
        return fence;
    }

    @Override // java9.util.Spliterator
    public Comparator<? super E> getComparator() {
        return Spliterators.getComparator(this);
    }

    @Override // java9.util.Spliterator
    public long getExactSizeIfKnown() {
        return Spliterators.getExactSizeIfKnown(this);
    }

    @Override // java9.util.Spliterator
    public boolean hasCharacteristics(int i) {
        return Spliterators.hasCharacteristics(this, i);
    }

    private static <T> int getTail(ArrayDeque<T> arrayDeque) {
        return U.getInt(arrayDeque, TAIL_OFF);
    }

    private static <T> int getHead(ArrayDeque<T> arrayDeque) {
        return U.getInt(arrayDeque, HEAD_OFF);
    }

    private static <T> Object[] getData(ArrayDeque<T> arrayDeque) {
        return (Object[]) U.getObject(arrayDeque, DATA_OFF);
    }

    static {
        Unsafe unsafe = UnsafeAccess.unsafe;
        U = unsafe;
        try {
            TAIL_OFF = unsafe.objectFieldOffset(ArrayDeque.class.getDeclaredField("tail"));
            HEAD_OFF = unsafe.objectFieldOffset(ArrayDeque.class.getDeclaredField("head"));
            DATA_OFF = unsafe.objectFieldOffset(ArrayDeque.class.getDeclaredField("elements"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
