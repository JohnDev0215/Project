package com.google.common.collect;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: classes6.dex */
public abstract class FluentIterable<E> implements Iterable<E> {
    private final Optional<Iterable<E>> iterableDelegate;

    protected FluentIterable() {
        this.iterableDelegate = Optional.absent();
    }

    FluentIterable(Iterable<E> iterable) {
        this.iterableDelegate = Optional.of(iterable);
    }

    private Iterable<E> getDelegate() {
        return this.iterableDelegate.or(this);
    }

    public static <E> FluentIterable<E> from(final Iterable<E> iterable) {
        if (iterable instanceof FluentIterable) {
            return (FluentIterable) iterable;
        }
        return new FluentIterable<E>(iterable) { // from class: com.google.common.collect.FluentIterable.1
            @Override // java.lang.Iterable
            public Iterator<E> iterator() {
                return iterable.iterator();
            }
        };
    }

    public static <E> FluentIterable<E> from(E[] eArr) {
        return from(Arrays.asList(eArr));
    }

    public static <T> FluentIterable<T> concat(Iterable<? extends T> iterable, Iterable<? extends T> iterable2) {
        return concatNoDefensiveCopy(iterable, iterable2);
    }

    private static <T> FluentIterable<T> concatNoDefensiveCopy(final Iterable<? extends T>... iterableArr) {
        for (Iterable<? extends T> iterable : iterableArr) {
            Preconditions.checkNotNull(iterable);
        }
        return new FluentIterable<T>() { // from class: com.google.common.collect.FluentIterable.3
            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                return Iterators.concat(new AbstractIndexedListIterator<Iterator<? extends T>>(iterableArr.length) { // from class: com.google.common.collect.FluentIterable.3.1
                    @Override // com.google.common.collect.AbstractIndexedListIterator
                    public Iterator<? extends T> get(int i) {
                        return iterableArr[i].iterator();
                    }
                });
            }
        };
    }

    public String toString() {
        return Iterables.toString(getDelegate());
    }

    public final FluentIterable<E> filter(Predicate<? super E> predicate) {
        return from(Iterables.filter(getDelegate(), predicate));
    }

    public final <T> FluentIterable<T> transform(Function<? super E, T> function) {
        return from(Iterables.transform(getDelegate(), function));
    }

    public final FluentIterable<E> limit(int i) {
        return from(Iterables.limit(getDelegate(), i));
    }

    public final ImmutableList<E> toList() {
        return ImmutableList.copyOf(getDelegate());
    }

    public final ImmutableSet<E> toSet() {
        return ImmutableSet.copyOf(getDelegate());
    }

    public final E[] toArray(Class<E> cls) {
        return (E[]) Iterables.toArray(getDelegate(), cls);
    }
}
