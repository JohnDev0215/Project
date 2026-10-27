package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Comparator;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public abstract class Ordering<T> implements Comparator<T> {
    @Override // java.util.Comparator
    public abstract int compare(T t, T t2);

    public static <C extends Comparable> Ordering<C> natural() {
        return NaturalOrdering.INSTANCE;
    }

    public static <T> Ordering<T> from(Comparator<T> comparator) {
        if (comparator instanceof Ordering) {
            return (Ordering) comparator;
        }
        return new ComparatorOrdering(comparator);
    }

    protected Ordering() {
    }

    public <S extends T> Ordering<S> reverse() {
        return new ReverseOrdering(this);
    }

    public <F> Ordering<F> onResultOf(Function<F, ? extends T> function) {
        return new ByFunctionOrdering(function, this);
    }

    <T2 extends T> Ordering<Map.Entry<T2, ?>> onKeys() {
        return (Ordering<Map.Entry<T2, ?>>) onResultOf(Maps.keyFunction());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <E extends T> E min(E e, E e2) {
        return compare(e, e2) <= 0 ? e : e2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <E extends T> E max(E e, E e2) {
        return compare(e, e2) >= 0 ? e : e2;
    }

    public <E extends T> ImmutableList<E> immutableSortedCopy(Iterable<E> iterable) {
        return ImmutableList.sortedCopyOf(this, iterable);
    }
}
