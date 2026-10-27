package org.apache.commons.compress.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes4.dex */
public class Iterators {
    public static <T> boolean addAll(Collection<T> collection, Iterator<? extends T> it) {
        Objects.requireNonNull(collection);
        Objects.requireNonNull(it);
        boolean zAdd = false;
        while (it.hasNext()) {
            zAdd |= collection.add(it.next());
        }
        return zAdd;
    }

    private Iterators() {
    }
}
