package java9.util;

import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.PriorityQueue;
import java9.util.function.Consumer;
import sun.misc.Unsafe;

/* JADX INFO: loaded from: classes3.dex */
final class PQueueSpliterator<E> implements Spliterator<E> {
    private static final boolean IS_HARMONY;
    private static final long MODCOUNT_OFF;
    private static final long QUEUE_OFF;
    private static final long SIZE_OFF;
    private static final Unsafe U;
    private int expectedModCount;
    private int fence;
    private int index;
    private final PriorityQueue<E> pq;

    @Override // java9.util.Spliterator
    public int characteristics() {
        return 16704;
    }

    private PQueueSpliterator(PriorityQueue<E> priorityQueue, int i, int i2, int i3) {
        this.pq = priorityQueue;
        this.index = i;
        this.fence = i2;
        this.expectedModCount = i3;
    }

    static <T> Spliterator<T> spliterator(PriorityQueue<T> priorityQueue) {
        return new PQueueSpliterator(priorityQueue, 0, -1, 0);
    }

    private int getFence() {
        int i = this.fence;
        if (i >= 0) {
            return i;
        }
        this.expectedModCount = getModCount(this.pq);
        int size = getSize(this.pq);
        this.fence = size;
        return size;
    }

    @Override // java9.util.Spliterator
    public PQueueSpliterator<E> trySplit() {
        int fence = getFence();
        int i = this.index;
        int i2 = (fence + i) >>> 1;
        if (i >= i2) {
            return null;
        }
        PriorityQueue<E> priorityQueue = this.pq;
        this.index = i2;
        return new PQueueSpliterator<>(priorityQueue, i, i2, this.expectedModCount);
    }

    @Override // java9.util.Spliterator
    public void forEachRemaining(Consumer<? super E> consumer) {
        Objects.requireNonNull(consumer);
        PriorityQueue<E> priorityQueue = this.pq;
        if (this.fence < 0) {
            this.fence = getSize(priorityQueue);
            this.expectedModCount = getModCount(priorityQueue);
        }
        Object[] queue = getQueue(priorityQueue);
        int i = this.fence;
        this.index = i;
        for (int i2 = this.index; i2 < i; i2++) {
            Object obj = queue[i2];
            if (obj == null) {
                break;
            }
            consumer.accept(obj);
        }
        if (getModCount(priorityQueue) != this.expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }

    @Override // java9.util.Spliterator
    public boolean tryAdvance(Consumer<? super E> consumer) {
        Objects.requireNonNull(consumer);
        PriorityQueue<E> priorityQueue = this.pq;
        if (this.fence < 0) {
            this.fence = getSize(priorityQueue);
            this.expectedModCount = getModCount(priorityQueue);
        }
        int i = this.index;
        if (i >= this.fence) {
            return false;
        }
        this.index = i + 1;
        Object obj = getQueue(priorityQueue)[i];
        if (obj == null || getModCount(priorityQueue) != this.expectedModCount) {
            throw new ConcurrentModificationException();
        }
        consumer.accept(obj);
        return true;
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

    private static <T> int getSize(PriorityQueue<T> priorityQueue) {
        return U.getInt(priorityQueue, SIZE_OFF);
    }

    private static <T> int getModCount(PriorityQueue<T> priorityQueue) {
        if (IS_HARMONY) {
            return 0;
        }
        return U.getInt(priorityQueue, MODCOUNT_OFF);
    }

    private static <T> Object[] getQueue(PriorityQueue<T> priorityQueue) {
        return (Object[]) U.getObject(priorityQueue, QUEUE_OFF);
    }

    static {
        boolean z = Spliterators.IS_HARMONY_ANDROID;
        IS_HARMONY = z;
        Unsafe unsafe = UnsafeAccess.unsafe;
        U = unsafe;
        try {
            SIZE_OFF = unsafe.objectFieldOffset(PriorityQueue.class.getDeclaredField("size"));
            if (!z) {
                MODCOUNT_OFF = unsafe.objectFieldOffset(PriorityQueue.class.getDeclaredField("modCount"));
            } else {
                MODCOUNT_OFF = 0L;
            }
            QUEUE_OFF = unsafe.objectFieldOffset(PriorityQueue.class.getDeclaredField(z ? "elements" : "queue"));
        } catch (Exception e) {
            throw new Error(e);
        }
    }
}
