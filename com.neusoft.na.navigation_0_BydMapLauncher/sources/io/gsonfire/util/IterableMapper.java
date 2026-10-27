package io.gsonfire.util;

import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class IterableMapper<F, T> implements Iterable<T> {
    private final Mapper<F, T> mapper;
    private final Iterable<F> source;

    private IterableMapper(Iterable<F> iterable, Mapper<F, T> mapper) {
        this.source = iterable;
        this.mapper = mapper;
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        final Iterator<F> it = this.source.iterator();
        return new Iterator<T>() { // from class: io.gsonfire.util.IterableMapper.1
            @Override // java.util.Iterator
            public void remove() {
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return it.hasNext();
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Iterator
            public T next() {
                return (T) IterableMapper.this.mapper.map(it.next());
            }
        };
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Iterable)) {
            return false;
        }
        Iterator<T> it = ((Iterable) obj).iterator();
        Iterator<T> it2 = iterator();
        while (it2.hasNext()) {
            if (!it.hasNext() || !areObjectEquals(it2.next(), it.next())) {
                return false;
            }
        }
        return !it.hasNext();
    }

    private static boolean areObjectEquals(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static <F, T> Iterable<T> create(Iterable<F> iterable, Mapper<F, T> mapper) {
        return new IterableMapper(iterable, mapper);
    }
}
