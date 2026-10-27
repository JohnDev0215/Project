package com.amazon.digitalmusicplayback.internal;

import java.util.LinkedHashSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: compiled from: HttpClientFactoryImpl.java */
/* JADX INFO: loaded from: classes2.dex */
class LimitedHashSet<E> extends LinkedHashSet<E> {
    private int limit;
    private ReentrantReadWriteLock lock;

    public LimitedHashSet() {
        this.limit = 1000;
        this.lock = new ReentrantReadWriteLock();
    }

    public LimitedHashSet(int i) {
        this.limit = 1000;
        this.lock = new ReentrantReadWriteLock();
        this.limit = i;
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        this.lock.readLock().lock();
        try {
            return super.contains(obj);
        } finally {
            this.lock.readLock().unlock();
        }
    }

    @Override // java.util.HashSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean add(E e) {
        this.lock.writeLock().lock();
        try {
            if (size() > this.limit) {
                remove(iterator().next());
            }
            return super.add(e);
        } finally {
            this.lock.writeLock().unlock();
        }
    }
}
