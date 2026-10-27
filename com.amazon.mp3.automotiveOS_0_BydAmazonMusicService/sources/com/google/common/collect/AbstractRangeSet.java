package com.google.common.collect;

import java.lang.Comparable;
import javax.annotation.CheckForNull;

/* JADX INFO: loaded from: classes6.dex */
abstract class AbstractRangeSet<C extends Comparable> implements RangeSet<C> {
    AbstractRangeSet() {
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RangeSet) {
            return asRanges().equals(((RangeSet) obj).asRanges());
        }
        return false;
    }

    public final int hashCode() {
        return asRanges().hashCode();
    }

    public final String toString() {
        return asRanges().toString();
    }
}
