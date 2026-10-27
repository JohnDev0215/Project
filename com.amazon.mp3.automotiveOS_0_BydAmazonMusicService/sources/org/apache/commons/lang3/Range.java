package org.apache.commons.lang3;

import java.io.Serializable;
import java.util.Comparator;

/* JADX INFO: loaded from: classes7.dex */
public final class Range<T> implements Serializable {
    private final Comparator<T> comparator;
    private transient int hashCode;
    private final T maximum;
    private final T minimum;
    private transient String toString;

    private enum ComparableComparator implements Comparator {
        INSTANCE;

        @Override // java.util.Comparator
        public int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo(obj2);
        }
    }

    /* JADX WARN: Incorrect types in method signature: <T::Ljava/lang/Comparable<TT;>;>(TT;TT;)Lorg/apache/commons/lang3/Range<TT;>; */
    public static Range between(Comparable comparable, Comparable comparable2) {
        return between(comparable, comparable2, null);
    }

    public static <T> Range<T> between(T t, T t2, Comparator<T> comparator) {
        return new Range<>(t, t2, comparator);
    }

    private Range(T t, T t2, Comparator<T> comparator) {
        if (t == null || t2 == null) {
            throw new IllegalArgumentException("Elements in a range must not be null: element1=" + t + ", element2=" + t2);
        }
        if (comparator == null) {
            this.comparator = ComparableComparator.INSTANCE;
        } else {
            this.comparator = comparator;
        }
        if (this.comparator.compare(t, t2) < 1) {
            this.minimum = t;
            this.maximum = t2;
        } else {
            this.minimum = t2;
            this.maximum = t;
        }
    }

    public boolean contains(T t) {
        return t != null && this.comparator.compare(t, this.minimum) > -1 && this.comparator.compare(t, this.maximum) < 1;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != Range.class) {
            return false;
        }
        Range range = (Range) obj;
        return this.minimum.equals(range.minimum) && this.maximum.equals(range.maximum);
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i != 0) {
            return i;
        }
        int iHashCode = ((((629 + Range.class.hashCode()) * 37) + this.minimum.hashCode()) * 37) + this.maximum.hashCode();
        this.hashCode = iHashCode;
        return iHashCode;
    }

    public String toString() {
        if (this.toString == null) {
            this.toString = "[" + this.minimum + ".." + this.maximum + "]";
        }
        return this.toString;
    }
}
