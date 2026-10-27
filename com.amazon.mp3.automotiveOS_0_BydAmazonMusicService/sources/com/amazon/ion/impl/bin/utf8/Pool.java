package com.amazon.ion.impl.bin.utf8;

import com.amazon.ion.impl.bin.utf8.Poolable;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX INFO: loaded from: classes3.dex */
abstract class Pool<T extends Poolable<?>> {
    private final Allocator<T> allocator;
    private final ArrayBlockingQueue<T> bufferQueue = new ArrayBlockingQueue<>(128);

    interface Allocator<T extends Poolable<?>> {
        T newInstance(Pool<T> pool);
    }

    Pool(Allocator<T> allocator) {
        this.allocator = allocator;
    }

    public T getOrCreate() {
        T tPoll = this.bufferQueue.poll();
        return tPoll == null ? (T) this.allocator.newInstance(this) : tPoll;
    }

    public void returnToPool(T t) {
        this.bufferQueue.offer(t);
    }
}
