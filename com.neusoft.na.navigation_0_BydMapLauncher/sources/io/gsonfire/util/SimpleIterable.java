package io.gsonfire.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public final class SimpleIterable<T> implements Iterable<T> {
    private final Iterable<T> iterable;

    private SimpleIterable(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return this.iterable.iterator();
    }

    public final Collection<T> toCollection() {
        ArrayList arrayList = new ArrayList();
        addTo(arrayList);
        return arrayList;
    }

    private final void addTo(Collection<T> collection) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            collection.add(it.next());
        }
    }

    public static <T> SimpleIterable<T> of(Iterable<T> iterable) {
        if (iterable == null) {
            throw new NullPointerException("The iterable parameter cannot be null");
        }
        return new SimpleIterable<>(iterable);
    }

    public static <T> SimpleIterable<T> of(T... tArr) {
        return of(Arrays.asList(tArr));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Iterable<T> iterable = this.iterable;
        Iterable<T> iterable2 = ((SimpleIterable) obj).iterable;
        if (iterable != null) {
            if (iterable.equals(iterable2)) {
                return true;
            }
        } else if (iterable2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Iterable<T> iterable = this.iterable;
        if (iterable != null) {
            return iterable.hashCode();
        }
        return 0;
    }

    public String toString() {
        return this.iterable.toString();
    }
}
