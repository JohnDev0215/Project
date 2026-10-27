package com.google.common.collect;

import java.lang.Comparable;
import java.util.Map;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
public interface RangeMap<K extends Comparable, V> {
    Map<Range<K>, V> asMapOfRanges();

    void clear();

    @CheckForNull
    V get(K k);

    void put(Range<K> range, V v);
}
