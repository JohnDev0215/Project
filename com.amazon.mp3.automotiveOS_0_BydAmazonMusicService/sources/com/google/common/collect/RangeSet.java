package com.google.common.collect;

import java.lang.Comparable;
import java.util.Set;

/* JADX INFO: loaded from: classes6.dex */
public interface RangeSet<C extends Comparable> {
    Set<Range<C>> asRanges();
}
