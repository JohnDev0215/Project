package java9.util;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java9.util.function.Consumer;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
final class ArrayListSpliterator<E> implements Spliterator<E> {
    private static final long DATA_OFF;
    private static final long MODCOUNT_OFF;
    private static final long SIZE_OFF;
    private static final Unsafe U;
    private int expectedModCount;
    private int fence;
    private int index;
    private final ArrayList<E> list;

    @Override // java9.util.Spliterator
    public int characteristics() {
        return 16464;
    }

    private ArrayListSpliterator(ArrayList<E> arrayList, int i, int i2, int i3) {
        this.list = arrayList;
        this.index = i;
        this.fence = i2;
        this.expectedModCount = i3;
    }

    static <T> Spliterator<T> spliterator(ArrayList<T> arrayList) {
        return new ArrayListSpliterator(arrayList, 0, -1, 0);
    }

    private int getFence() {
        int i = this.fence;
        if (i >= 0) {
            return i;
        }
        ArrayList<E> arrayList = this.list;
        this.expectedModCount = getModCount(arrayList);
        int size = getSize(arrayList);
        this.fence = size;
        return size;
    }

    @Override // java9.util.Spliterator
    public ArrayListSpliterator<E> trySplit() {
        int fence = getFence();
        int i = this.index;
        int i2 = (fence + i) >>> 1;
        if (i >= i2) {
            return null;
        }
        ArrayList<E> arrayList = this.list;
        this.index = i2;
        return new ArrayListSpliterator<>(arrayList, i, i2, this.expectedModCount);
    }

    @Override // java9.util.Spliterator
    public boolean tryAdvance(Consumer<? super E> consumer) {
        Objects.requireNonNull(consumer);
        int fence = getFence();
        int i = this.index;
        if (i >= fence) {
            return false;
        }
        this.index = i + 1;
        consumer.accept(getData(this.list)[i]);
        if (this.expectedModCount == getModCount(this.list)) {
            return true;
        }
        throw new ConcurrentModificationException();
    }

    @Override // java9.util.Spliterator
    public void forEachRemaining(Consumer<? super E> consumer) {
        int modCount;
        Objects.requireNonNull(consumer);
        ArrayList<E> arrayList = this.list;
        Object[] data = getData(arrayList);
        if (data != null) {
            int size = this.fence;
            if (size < 0) {
                modCount = getModCount(arrayList);
                size = getSize(arrayList);
            } else {
                modCount = this.expectedModCount;
            }
            int i = this.index;
            if (i >= 0) {
                this.index = size;
                if (size <= data.length) {
                    while (i < size) {
                        consumer.accept(data[i]);
                        i++;
                    }
                    if (modCount == getModCount(arrayList)) {
                        return;
                    }
                }
            }
        }
        throw new ConcurrentModificationException();
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

    private static <T> int getSize(ArrayList<T> arrayList) {
        return U.getInt(arrayList, SIZE_OFF);
    }

    private static <T> int getModCount(ArrayList<T> arrayList) {
        return U.getInt(arrayList, MODCOUNT_OFF);
    }

    private static <T> Object[] getData(ArrayList<T> arrayList) {
        return (Object[]) U.getObject(arrayList, DATA_OFF);
    }

    static {
        Unsafe unsafe = UnsafeAccess.unsafe;
        U = unsafe;
        try {
            MODCOUNT_OFF = unsafe.objectFieldOffset(AbstractList.class.getDeclaredField("modCount"));
            SIZE_OFF = unsafe.objectFieldOffset(ArrayList.class.getDeclaredField("size"));
            DATA_OFF = unsafe.objectFieldOffset(ArrayList.class.getDeclaredField(Spliterators.IS_HARMONY_ANDROID ? "array" : "elementData"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
