package com.google.common.collect;

import com.google.common.collect.Multiset;
import java.util.Comparator;
import java.util.NavigableSet;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public interface SortedMultiset<E> extends Multiset, SortedIterable<E> {
    Comparator<? super E> comparator();

    SortedMultiset<E> descendingMultiset();

    @Override // com.google.common.collect.Multiset
    NavigableSet<E> elementSet();

    @Override // com.google.common.collect.Multiset
    Set<Multiset.Entry<E>> entrySet();

    @CheckForNull
    Multiset.Entry<E> firstEntry();

    SortedMultiset<E> headMultiset(E e, BoundType boundType);

    @CheckForNull
    Multiset.Entry<E> lastEntry();

    @CheckForNull
    Multiset.Entry<E> pollFirstEntry();

    @CheckForNull
    Multiset.Entry<E> pollLastEntry();

    SortedMultiset<E> subMultiset(E e, BoundType boundType, E e2, BoundType boundType2);

    SortedMultiset<E> tailMultiset(E e, BoundType boundType);
}
