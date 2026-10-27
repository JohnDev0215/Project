package java9.util;

import java.util.AbstractList;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.List;
import java9.util.function.Consumer;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
final class RASpliterator<E> implements Spliterator<E> {
    private static final long MODCOUNT_OFF;
    private static final Unsafe U;
    private final AbstractList<E> alist;
    private int expectedModCount;
    private int fence;
    private int index;
    private final List<E> list;

    @Override // java9.util.Spliterator
    public int characteristics() {
        return 16464;
    }

    private RASpliterator(List<E> list, int i, int i2, int i3) {
        this.list = list;
        this.index = i;
        this.fence = i2;
        this.alist = list instanceof AbstractList ? (AbstractList) list : null;
        this.expectedModCount = i3;
    }

    static <T> Spliterator<T> spliterator(List<T> list) {
        return new RASpliterator(list, 0, -1, 0);
    }

    private int getFence() {
        List<E> list = this.list;
        int i = this.fence;
        if (i >= 0) {
            return i;
        }
        AbstractList<E> abstractList = this.alist;
        if (abstractList != null) {
            this.expectedModCount = getModCount(abstractList);
        }
        int size = list.size();
        this.fence = size;
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
        List<E> list = this.list;
        this.index = i2;
        return new RASpliterator(list, i, i2, this.expectedModCount);
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
        consumer.accept(this.list.get(i));
        checkAbsListModCount(this.alist, this.expectedModCount);
        return true;
    }

    @Override // java9.util.Spliterator
    public void forEachRemaining(Consumer<? super E> consumer) {
        Objects.requireNonNull(consumer);
        List<E> list = this.list;
        int fence = getFence();
        this.index = fence;
        for (int i = this.index; i < fence; i++) {
            try {
                consumer.accept(list.get(i));
            } catch (IndexOutOfBoundsException unused) {
                throw new ConcurrentModificationException();
            }
        }
        checkAbsListModCount(this.alist, this.expectedModCount);
    }

    @Override // java9.util.Spliterator
    public long estimateSize() {
        return getFence() - this.index;
    }

    @Override // java9.util.Spliterator
    public long getExactSizeIfKnown() {
        return Spliterators.getExactSizeIfKnown(this);
    }

    @Override // java9.util.Spliterator
    public boolean hasCharacteristics(int i) {
        return Spliterators.hasCharacteristics(this, i);
    }

    @Override // java9.util.Spliterator
    public Comparator<? super E> getComparator() {
        return Spliterators.getComparator(this);
    }

    private static void checkAbsListModCount(AbstractList<?> abstractList, int i) {
        if (abstractList != null && getModCount(abstractList) != i) {
            throw new ConcurrentModificationException();
        }
    }

    private static <T> int getModCount(List<T> list) {
        return U.getInt(list, MODCOUNT_OFF);
    }

    static {
        Unsafe unsafe = UnsafeAccess.unsafe;
        U = unsafe;
        try {
            MODCOUNT_OFF = unsafe.objectFieldOffset(AbstractList.class.getDeclaredField("modCount"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
