package java9.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java9.util.ImmutableCollections;
import java9.util.function.UnaryOperator;

/* JADX INFO: loaded from: classes3.dex */
public final class Lists {
    public static <E> void sort(List<E> list, Comparator<? super E> comparator) {
        Collections.sort(list, comparator);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <E> void replaceAll(List<E> list, UnaryOperator<E> unaryOperator) {
        Objects.requireNonNull(list);
        Objects.requireNonNull(unaryOperator);
        ListIterator<E> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            listIterator.set(unaryOperator.apply(listIterator.next()));
        }
    }

    public static <E> Spliterator<E> spliterator(List<E> list) {
        return Spliterators.spliterator(list);
    }

    public static <E> List<E> of() {
        return (List<E>) ImmutableCollections.ListN.EMPTY_LIST;
    }

    public static <E> List<E> of(E e) {
        return new ImmutableCollections.List12(e);
    }

    public static <E> List<E> of(E e, E e2) {
        return new ImmutableCollections.List12(e, e2);
    }

    public static <E> List<E> of(E e, E e2, E e3) {
        return new ImmutableCollections.ListN(e, e2, e3);
    }

    public static <E> List<E> of(E e, E e2, E e3, E e4) {
        return new ImmutableCollections.ListN(e, e2, e3, e4);
    }

    public static <E> List<E> of(E e, E e2, E e3, E e4, E e5) {
        return new ImmutableCollections.ListN(e, e2, e3, e4, e5);
    }

    public static <E> List<E> of(E e, E e2, E e3, E e4, E e5, E e6) {
        return new ImmutableCollections.ListN(e, e2, e3, e4, e5, e6);
    }

    public static <E> List<E> of(E e, E e2, E e3, E e4, E e5, E e6, E e7) {
        return new ImmutableCollections.ListN(e, e2, e3, e4, e5, e6, e7);
    }

    public static <E> List<E> of(E e, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
        return new ImmutableCollections.ListN(e, e2, e3, e4, e5, e6, e7, e8);
    }

    public static <E> List<E> of(E e, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
        return new ImmutableCollections.ListN(e, e2, e3, e4, e5, e6, e7, e8, e9);
    }

    public static <E> List<E> of(E e, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
        return new ImmutableCollections.ListN(e, e2, e3, e4, e5, e6, e7, e8, e9, e10);
    }

    public static <E> List<E> of(E... eArr) {
        int length = eArr.length;
        if (length == 0) {
            return (List<E>) ImmutableCollections.ListN.EMPTY_LIST;
        }
        if (length == 1) {
            return new ImmutableCollections.List12(eArr[0]);
        }
        if (length == 2) {
            return new ImmutableCollections.List12(eArr[0], eArr[1]);
        }
        return new ImmutableCollections.ListN(eArr);
    }

    public static <E> List<E> copyOf(Collection<? extends E> collection) {
        return ImmutableCollections.listCopy(collection);
    }

    private Lists() {
    }
}
