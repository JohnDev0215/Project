package io.sentry;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;

/* JADX INFO: loaded from: classes.dex */
public final class Y1 implements Queue, Collection, Serializable {
    public final Queue l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Y1 f15995m = this;

    public Y1(Queue queue) {
        this.l = queue;
    }

    @Override // java.util.Queue, java.util.Collection
    public final boolean add(Object obj) {
        boolean zAdd;
        synchronized (this.f15995m) {
            zAdd = this.l.add(obj);
        }
        return zAdd;
    }

    @Override // java.util.Collection
    public final boolean addAll(Collection collection) {
        boolean zAddAll;
        synchronized (this.f15995m) {
            zAddAll = this.l.addAll(collection);
        }
        return zAddAll;
    }

    @Override // java.util.Collection
    public final void clear() {
        synchronized (this.f15995m) {
            this.l.clear();
        }
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        boolean zContains;
        synchronized (this.f15995m) {
            zContains = this.l.contains(obj);
        }
        return zContains;
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        boolean zContainsAll;
        synchronized (this.f15995m) {
            zContainsAll = this.l.containsAll(collection);
        }
        return zContainsAll;
    }

    @Override // java.util.Queue
    public final Object element() {
        Object objElement;
        synchronized (this.f15995m) {
            objElement = this.l.element();
        }
        return objElement;
    }

    @Override // java.util.Collection
    public final boolean equals(Object obj) {
        boolean zEquals;
        if (obj == this) {
            return true;
        }
        synchronized (this.f15995m) {
            zEquals = this.l.equals(obj);
        }
        return zEquals;
    }

    @Override // java.util.Collection
    public final int hashCode() {
        int iHashCode;
        synchronized (this.f15995m) {
            iHashCode = this.l.hashCode();
        }
        return iHashCode;
    }

    @Override // java.util.Collection
    public final boolean isEmpty() {
        boolean zIsEmpty;
        synchronized (this.f15995m) {
            zIsEmpty = this.l.isEmpty();
        }
        return zIsEmpty;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.l.iterator();
    }

    @Override // java.util.Queue
    public final boolean offer(Object obj) {
        boolean zOffer;
        synchronized (this.f15995m) {
            zOffer = this.l.offer(obj);
        }
        return zOffer;
    }

    @Override // java.util.Queue
    public final Object peek() {
        Object objPeek;
        synchronized (this.f15995m) {
            objPeek = this.l.peek();
        }
        return objPeek;
    }

    @Override // java.util.Queue
    public final Object poll() {
        Object objPoll;
        synchronized (this.f15995m) {
            objPoll = this.l.poll();
        }
        return objPoll;
    }

    @Override // java.util.Queue
    public final Object remove() {
        Object objRemove;
        synchronized (this.f15995m) {
            objRemove = this.l.remove();
        }
        return objRemove;
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        boolean zRemoveAll;
        synchronized (this.f15995m) {
            zRemoveAll = this.l.removeAll(collection);
        }
        return zRemoveAll;
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        boolean zRetainAll;
        synchronized (this.f15995m) {
            zRetainAll = this.l.retainAll(collection);
        }
        return zRetainAll;
    }

    @Override // java.util.Collection
    public final int size() {
        int size;
        synchronized (this.f15995m) {
            size = this.l.size();
        }
        return size;
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        Object[] array;
        synchronized (this.f15995m) {
            array = this.l.toArray();
        }
        return array;
    }

    public final String toString() {
        String string;
        synchronized (this.f15995m) {
            string = this.l.toString();
        }
        return string;
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        boolean zRemove;
        synchronized (this.f15995m) {
            zRemove = this.l.remove(obj);
        }
        return zRemove;
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        Object[] array;
        synchronized (this.f15995m) {
            array = this.l.toArray(objArr);
        }
        return array;
    }
}
