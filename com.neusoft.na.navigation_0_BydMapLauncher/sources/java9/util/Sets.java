package java9.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java9.util.ImmutableCollections;

/* JADX INFO: loaded from: classes3.dex */
public final class Sets {
    public static <E> Set<E> of() {
        return (Set<E>) ImmutableCollections.SetN.EMPTY_SET;
    }

    public static <E> Set<E> of(E e) {
        return new ImmutableCollections.Set12(e);
    }

    public static <E> Set<E> of(E e, E e2) {
        return new ImmutableCollections.Set12(e, e2);
    }

    public static <E> Set<E> of(E e, E e2, E e3) {
        return new ImmutableCollections.SetN(e, e2, e3);
    }

    public static <E> Set<E> of(E e, E e2, E e3, E e4) {
        return new ImmutableCollections.SetN(e, e2, e3, e4);
    }

    public static <E> Set<E> of(E e, E e2, E e3, E e4, E e5) {
        return new ImmutableCollections.SetN(e, e2, e3, e4, e5);
    }

    public static <E> Set<E> of(E e, E e2, E e3, E e4, E e5, E e6) {
        return new ImmutableCollections.SetN(e, e2, e3, e4, e5, e6);
    }

    public static <E> Set<E> of(E e, E e2, E e3, E e4, E e5, E e6, E e7) {
        return new ImmutableCollections.SetN(e, e2, e3, e4, e5, e6, e7);
    }

    public static <E> Set<E> of(E e, E e2, E e3, E e4, E e5, E e6, E e7, E e8) {
        return new ImmutableCollections.SetN(e, e2, e3, e4, e5, e6, e7, e8);
    }

    public static <E> Set<E> of(E e, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9) {
        return new ImmutableCollections.SetN(e, e2, e3, e4, e5, e6, e7, e8, e9);
    }

    public static <E> Set<E> of(E e, E e2, E e3, E e4, E e5, E e6, E e7, E e8, E e9, E e10) {
        return new ImmutableCollections.SetN(e, e2, e3, e4, e5, e6, e7, e8, e9, e10);
    }

    public static <E> Set<E> of(E... eArr) {
        int length = eArr.length;
        if (length == 0) {
            return (Set<E>) ImmutableCollections.SetN.EMPTY_SET;
        }
        if (length == 1) {
            return new ImmutableCollections.Set12(eArr[0]);
        }
        if (length == 2) {
            return new ImmutableCollections.Set12(eArr[0], eArr[1]);
        }
        return new ImmutableCollections.SetN(eArr);
    }

    public static <E> Set<E> copyOf(Collection<? extends E> collection) {
        if (collection instanceof ImmutableCollections.AbstractImmutableSet) {
            return (Set) collection;
        }
        return of(new HashSet(collection).toArray());
    }

    private Sets() {
    }
}
