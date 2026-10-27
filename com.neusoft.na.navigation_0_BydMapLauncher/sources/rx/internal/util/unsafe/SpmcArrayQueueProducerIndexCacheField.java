package rx.internal.util.unsafe;

/* JADX INFO: compiled from: SpmcArrayQueue.java */
/* JADX INFO: loaded from: classes4.dex */
abstract class SpmcArrayQueueProducerIndexCacheField<E> extends SpmcArrayQueueMidPad<E> {
    private volatile long producerIndexCache;

    public SpmcArrayQueueProducerIndexCacheField(int i) {
        super(i);
    }

    protected final long lvProducerIndexCache() {
        return this.producerIndexCache;
    }

    protected final void svProducerIndexCache(long j) {
        this.producerIndexCache = j;
    }
}
