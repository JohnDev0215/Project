package com.google.common.collect;

import java.util.Queue;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public abstract class ForwardingQueue<E> extends ForwardingCollection<E> implements Queue<E> {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    public abstract Queue<E> delegate();

    protected ForwardingQueue() {
    }

    public boolean offer(E e) {
        return delegate().offer(e);
    }

    @Override // java.util.Queue
    @CheckForNull
    public E poll() {
        return delegate().poll();
    }

    @Override // java.util.Queue
    public E remove() {
        return delegate().remove();
    }

    @Override // java.util.Queue
    @CheckForNull
    public E peek() {
        return delegate().peek();
    }

    @Override // java.util.Queue
    public E element() {
        return delegate().element();
    }
}
