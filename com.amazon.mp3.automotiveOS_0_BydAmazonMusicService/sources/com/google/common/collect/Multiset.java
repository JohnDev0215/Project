package com.google.common.collect;

import java.util.Collection;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public interface Multiset<E> extends Collection<E> {

    public interface Entry<E> {
        int getCount();

        E getElement();

        String toString();
    }

    int add(E e, int i);

    boolean add(E e);

    boolean contains(@CheckForNull Object obj);

    @Override // java.util.Collection
    boolean containsAll(Collection<?> collection);

    int count(@CheckForNull Object obj);

    Set<E> elementSet();

    Set<Entry<E>> entrySet();

    boolean equals(@CheckForNull Object obj);

    int hashCode();

    int remove(@CheckForNull Object obj, int i);

    boolean remove(@CheckForNull Object obj);

    int setCount(E e, int i);

    boolean setCount(E e, int i, int i2);

    int size();
}
