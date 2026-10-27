package io.reactivex.rxjava3.internal.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class VolatileSizeArrayList<T> extends AtomicInteger implements List<T>, RandomAccess {
    private static final long serialVersionUID = 3972397474470203923L;
    final ArrayList<T> list = new ArrayList<>();

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        boolean zAdd = this.list.add(obj);
        lazySet(this.list.size());
        return zAdd;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        boolean zAddAll = this.list.addAll(collection);
        lazySet(this.list.size());
        return zAddAll;
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        this.list.clear();
        lazySet(0);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return this.list.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.list.containsAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean equals(Object obj) {
        return obj instanceof VolatileSizeArrayList ? this.list.equals(((VolatileSizeArrayList) obj).list) : this.list.equals(obj);
    }

    @Override // java.util.List
    public final Object get(int i) {
        return this.list.get(i);
    }

    @Override // java.util.List, java.util.Collection
    public final int hashCode() {
        return this.list.hashCode();
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        return this.list.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return get() == 0;
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.list.iterator();
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        return this.list.lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return this.list.listIterator();
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        boolean zRemove = this.list.remove(obj);
        lazySet(this.list.size());
        return zRemove;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        boolean zRemoveAll = this.list.removeAll(collection);
        lazySet(this.list.size());
        return zRemoveAll;
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        boolean zRetainAll = this.list.retainAll(collection);
        lazySet(this.list.size());
        return zRetainAll;
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        return this.list.set(i, obj);
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return get();
    }

    @Override // java.util.List
    public final List subList(int i, int i2) {
        return this.list.subList(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return this.list.toArray();
    }

    @Override // java.util.concurrent.atomic.AtomicInteger
    public final String toString() {
        return this.list.toString();
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        return this.list.listIterator(i);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.list.toArray(objArr);
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        this.list.add(i, obj);
        lazySet(this.list.size());
    }

    @Override // java.util.List
    public final boolean addAll(int i, Collection collection) {
        boolean zAddAll = this.list.addAll(i, collection);
        lazySet(this.list.size());
        return zAddAll;
    }

    @Override // java.util.List
    public final Object remove(int i) {
        T tRemove = this.list.remove(i);
        lazySet(this.list.size());
        return tRemove;
    }
}
