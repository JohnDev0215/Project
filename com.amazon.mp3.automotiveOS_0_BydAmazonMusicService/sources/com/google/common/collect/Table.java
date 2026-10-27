package com.google.common.collect;

import java.util.Map;
import java.util.Set;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public interface Table<R, C, V> {

    public interface Cell<R, C, V> {
        C getColumnKey();

        R getRowKey();

        V getValue();
    }

    Set<Cell<R, C, V>> cellSet();

    boolean contains(@CheckForNull Object obj, @CheckForNull Object obj2);

    @CheckForNull
    V get(@CheckForNull Object obj, @CheckForNull Object obj2);

    boolean isEmpty();

    @CheckForNull
    V put(R r, C c, V v);

    Map<C, V> row(R r);

    Map<R, Map<C, V>> rowMap();

    int size();
}
