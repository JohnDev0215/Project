package com.fasterxml.jackson.datatype.guava.deser.util;

import com.google.common.collect.BoundType;
import com.google.common.collect.Range;

/* JADX INFO: loaded from: classes6.dex */
public class RangeFactory {
    public static <C extends Comparable<?>> Range<C> open(C c, C c2) {
        BoundType boundType = BoundType.OPEN;
        return range(c, boundType, c2, boundType);
    }

    public static <C extends Comparable<?>> Range<C> openClosed(C c, C c2) {
        return range(c, BoundType.OPEN, c2, BoundType.CLOSED);
    }

    public static <C extends Comparable<?>> Range<C> closedOpen(C c, C c2) {
        return range(c, BoundType.CLOSED, c2, BoundType.OPEN);
    }

    public static <C extends Comparable<?>> Range<C> closed(C c, C c2) {
        BoundType boundType = BoundType.CLOSED;
        return range(c, boundType, c2, boundType);
    }

    public static <C extends Comparable<?>> Range<C> range(C c, BoundType boundType, C c2, BoundType boundType2) {
        return Range.range(c, boundType, c2, boundType2);
    }

    public static <C extends Comparable<?>> Range<C> greaterThan(C c) {
        return downTo(c, BoundType.OPEN);
    }

    public static <C extends Comparable<?>> Range<C> atLeast(C c) {
        return downTo(c, BoundType.CLOSED);
    }

    public static <C extends Comparable<?>> Range<C> downTo(C c, BoundType boundType) {
        return Range.downTo(c, boundType);
    }

    public static <C extends Comparable<?>> Range<C> lessThan(C c) {
        return upTo(c, BoundType.OPEN);
    }

    public static <C extends Comparable<?>> Range<C> atMost(C c) {
        return upTo(c, BoundType.CLOSED);
    }

    public static <C extends Comparable<?>> Range<C> upTo(C c, BoundType boundType) {
        return Range.upTo(c, boundType);
    }

    public static <C extends Comparable<?>> Range<C> all() {
        return Range.all();
    }

    public static <C extends Comparable<?>> Range<C> singleton(C c) {
        return Range.singleton(c);
    }

    private RangeFactory() {
    }
}
