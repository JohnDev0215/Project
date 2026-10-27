package p;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class z31 implements List, RandomAccess {
    public final List c;

    public z31(List list) {
        this.c = Collections.unmodifiableList(list);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean add(Object obj) {
        return this.c.add(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean addAll(Collection collection) {
        return this.c.addAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public final void clear() {
        this.c.clear();
    }

    @Override // java.util.List, java.util.Collection
    public final boolean contains(Object obj) {
        return this.c.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean containsAll(Collection collection) {
        return this.c.containsAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean equals(Object obj) {
        return this.c.equals(obj);
    }

    @Override // java.util.List
    public final Object get(int i) {
        return this.c.get(i);
    }

    @Override // java.util.List, java.util.Collection
    public final int hashCode() {
        return this.c.hashCode();
    }

    @Override // java.util.List
    public final int indexOf(Object obj) {
        return this.c.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean isEmpty() {
        return this.c.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return this.c.iterator();
    }

    @Override // java.util.List
    public final int lastIndexOf(Object obj) {
        return this.c.lastIndexOf(obj);
    }

    @Override // java.util.List
    public final ListIterator listIterator() {
        return this.c.listIterator();
    }

    @Override // java.util.List, java.util.Collection
    public final boolean remove(Object obj) {
        return this.c.remove(obj);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean removeAll(Collection collection) {
        return this.c.removeAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public final boolean retainAll(Collection collection) {
        return this.c.retainAll(collection);
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        return this.c.set(i, obj);
    }

    @Override // java.util.List, java.util.Collection
    public final int size() {
        return this.c.size();
    }

    @Override // java.util.List
    public final List subList(int i, int i2) {
        return this.c.subList(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray() {
        return this.c.toArray();
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        this.c.add(i, obj);
    }

    @Override // java.util.List
    public final boolean addAll(int i, Collection collection) {
        return this.c.addAll(i, collection);
    }

    @Override // java.util.List
    public final ListIterator listIterator(int i) {
        return this.c.listIterator(i);
    }

    @Override // java.util.List
    public final Object remove(int i) {
        return this.c.remove(i);
    }

    @Override // java.util.List, java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return this.c.toArray(objArr);
    }
}
