package java9.util;

import java.util.AbstractList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Vector;
import java9.util.function.Consumer;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
final class VectorSpliterator<E> implements Spliterator<E> {
    private static final long DATA_OFF;
    private static final long MODCOUNT_OFF;
    private static final long SIZE_OFF;
    private static final Unsafe U;
    private Object[] array;
    private int expectedModCount;
    private int fence;
    private int index;
    private final Vector<E> list;

    @Override // java9.util.Spliterator
    public int characteristics() {
        return 16464;
    }

    private VectorSpliterator(Vector<E> vector, Object[] objArr, int i, int i2, int i3) {
        this.list = vector;
        this.array = objArr;
        this.index = i;
        this.fence = i2;
        this.expectedModCount = i3;
    }

    static <T> Spliterator<T> spliterator(Vector<T> vector) {
        return new VectorSpliterator(vector, null, 0, -1, 0);
    }

    private int getFence() {
        int size = this.fence;
        if (size < 0) {
            synchronized (this.list) {
                this.array = getData(this.list);
                this.expectedModCount = getModCount(this.list);
                size = getSize(this.list);
                this.fence = size;
            }
        }
        return size;
    }

    @Override // java9.util.Spliterator
    public Spliterator<E> trySplit() {
        int fence = getFence();
        int i = this.index;
        int i2 = (fence + i) >>> 1;
        if (i >= i2) {
            return null;
        }
        Vector<E> vector = this.list;
        Object[] objArr = this.array;
        this.index = i2;
        return new VectorSpliterator(vector, objArr, i, i2, this.expectedModCount);
    }

    @Override // java9.util.Spliterator
    public boolean tryAdvance(Consumer<? super E> consumer) {
        Objects.requireNonNull(consumer);
        int fence = getFence();
        int i = this.index;
        if (fence <= i) {
            return false;
        }
        this.index = i + 1;
        consumer.accept(this.array[i]);
        if (this.expectedModCount == getModCount(this.list)) {
            return true;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java9.util.Spliterator
    public void forEachRemaining(Consumer<? super E> consumer) {
        Objects.requireNonNull(consumer);
        int fence = getFence();
        Object[] objArr = this.array;
        this.index = fence;
        for (int i = this.index; i < fence; i++) {
            consumer.accept(objArr[i]);
        }
        if (getModCount(this.list) != this.expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java9.util.Spliterator
    public long estimateSize() {
        return getFence() - this.index;
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

    private static <T> int getSize(Vector<T> vector) {
        return U.getInt(vector, SIZE_OFF);
    }

    private static <T> int getModCount(Vector<T> vector) {
        return U.getInt(vector, MODCOUNT_OFF);
    }

    private static <T> Object[] getData(Vector<T> vector) {
        return (Object[]) U.getObject(vector, DATA_OFF);
    }

    static {
        Unsafe unsafe = UnsafeAccess.unsafe;
        U = unsafe;
        try {
            MODCOUNT_OFF = unsafe.objectFieldOffset(AbstractList.class.getDeclaredField("modCount"));
            SIZE_OFF = unsafe.objectFieldOffset(Vector.class.getDeclaredField("elementCount"));
            DATA_OFF = unsafe.objectFieldOffset(Vector.class.getDeclaredField("elementData"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
