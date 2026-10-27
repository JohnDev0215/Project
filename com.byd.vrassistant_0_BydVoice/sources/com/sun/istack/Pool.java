package com.sun.istack;

import java.lang.ref.WeakReference;
import java.util.concurrent.ConcurrentLinkedQueue;

/* JADX INFO: loaded from: classes3.dex */
public interface Pool<T> {
    void recycle(T t);

    T take();

    public static abstract class Impl<T> implements Pool<T> {
        private volatile WeakReference<ConcurrentLinkedQueue<T>> queue;

        protected abstract T create();

        @Override // com.sun.istack.Pool
        public final T take() {
            T tPoll = getQueue().poll();
            return tPoll == null ? create() : tPoll;
        }

        @Override // com.sun.istack.Pool
        public final void recycle(T t) {
            getQueue().offer(t);
        }

        private ConcurrentLinkedQueue<T> getQueue() {
            ConcurrentLinkedQueue<T> concurrentLinkedQueue;
            WeakReference<ConcurrentLinkedQueue<T>> weakReference = this.queue;
            if (weakReference != null && (concurrentLinkedQueue = weakReference.get()) != null) {
                return concurrentLinkedQueue;
            }
            ConcurrentLinkedQueue<T> concurrentLinkedQueue2 = new ConcurrentLinkedQueue<>();
            this.queue = new WeakReference<>(concurrentLinkedQueue2);
            return concurrentLinkedQueue2;
        }
    }
}
