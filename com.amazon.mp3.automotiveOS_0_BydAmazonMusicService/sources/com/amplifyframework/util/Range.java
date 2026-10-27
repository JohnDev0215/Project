package com.amplifyframework.util;

import java.lang.Comparable;

/* JADX INFO: loaded from: classes5.dex */
public final class Range<T extends Comparable<T>> {
    private final T high;
    private final T low;

    public Range(T t, T t2) throws IllegalArgumentException {
        if (t.compareTo(t2) > 0) {
            throw new IllegalArgumentException("Low value should be lower than high");
        }
        this.low = t;
        this.high = t2;
    }

    public boolean contains(T t) {
        return t.compareTo(this.low) >= 0 && t.compareTo(this.high) <= 0;
    }
}
