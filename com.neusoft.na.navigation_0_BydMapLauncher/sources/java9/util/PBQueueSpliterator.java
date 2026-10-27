package java9.util;

import java.util.Comparator;
import java.util.concurrent.PriorityBlockingQueue;
import java9.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
final class PBQueueSpliterator<E> implements Spliterator<E> {
    private Object[] array;
    private int fence;
    private int index;
    private final PriorityBlockingQueue<E> queue;

    @Override // java9.util.Spliterator
    public int characteristics() {
        return 16704;
    }

    private PBQueueSpliterator(PriorityBlockingQueue<E> priorityBlockingQueue, Object[] objArr, int i, int i2) {
        this.queue = priorityBlockingQueue;
        this.array = objArr;
        this.index = i;
        this.fence = i2;
    }

    static <T> Spliterator<T> spliterator(PriorityBlockingQueue<T> priorityBlockingQueue) {
        return new PBQueueSpliterator(priorityBlockingQueue, null, 0, -1);
    }

    private int getFence() {
        if (this.array == null) {
            Object[] array = this.queue.toArray();
            this.array = array;
            this.fence = array.length;
        }
        return this.fence;
    }

    @Override // java9.util.Spliterator
    public PBQueueSpliterator<E> trySplit() {
        int fence = getFence();
        int i = this.index;
        int i2 = (fence + i) >>> 1;
        if (i >= i2) {
            return null;
        }
        PriorityBlockingQueue<E> priorityBlockingQueue = this.queue;
        Object[] objArr = this.array;
        this.index = i2;
        return new PBQueueSpliterator<>(priorityBlockingQueue, objArr, i, i2);
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
    }

    @Override // java9.util.Spliterator
    public boolean tryAdvance(Consumer<? super E> consumer) {
        Objects.requireNonNull(consumer);
        int fence = getFence();
        int i = this.index;
        if (fence <= i || i < 0) {
            return false;
        }
        Object[] objArr = this.array;
        this.index = i + 1;
        consumer.accept(objArr[i]);
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
}
