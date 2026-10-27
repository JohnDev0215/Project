package org.apache.commons.lang3.compare;

import java.util.function.Predicate;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class ComparableUtils {

    /* JADX INFO: compiled from: caradapter-bug */
    public static class ComparableCheckBuilder<A extends Comparable<A>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final A f41a;

        private boolean betweenOrdered(A a2, A a3) {
            return greaterThanOrEqualTo(a2) && lessThanOrEqualTo(a3);
        }

        private boolean betweenOrderedExclusive(A a2, A a3) {
            return greaterThan(a2) && lessThan(a3);
        }

        public boolean between(A a2, A a3) {
            return betweenOrdered(a2, a3) || betweenOrdered(a3, a2);
        }

        public boolean betweenExclusive(A a2, A a3) {
            return betweenOrderedExclusive(a2, a3) || betweenOrderedExclusive(a3, a2);
        }

        public boolean equalTo(A a2) {
            return this.f41a.compareTo(a2) == 0;
        }

        public boolean greaterThan(A a2) {
            return this.f41a.compareTo(a2) > 0;
        }

        public boolean greaterThanOrEqualTo(A a2) {
            return this.f41a.compareTo(a2) >= 0;
        }

        public boolean lessThan(A a2) {
            return this.f41a.compareTo(a2) < 0;
        }

        public boolean lessThanOrEqualTo(A a2) {
            return this.f41a.compareTo(a2) <= 0;
        }

        private ComparableCheckBuilder(A a2) {
            this.f41a = a2;
        }
    }

    private ComparableUtils() {
    }

    public static <A extends Comparable<A>> Predicate<A> between(final A a2, final A a3) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.f
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ComparableUtils.is((Comparable) obj).between(a2, a3);
            }
        };
    }

    public static <A extends Comparable<A>> Predicate<A> betweenExclusive(final A a2, final A a3) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.a
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ComparableUtils.is((Comparable) obj).betweenExclusive(a2, a3);
            }
        };
    }

    public static <A extends Comparable<A>> Predicate<A> ge(final A a2) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.e
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ComparableUtils.is((Comparable) obj).greaterThanOrEqualTo(a2);
            }
        };
    }

    public static <A extends Comparable<A>> Predicate<A> gt(final A a2) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.d
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ComparableUtils.is((Comparable) obj).greaterThan(a2);
            }
        };
    }

    public static <A extends Comparable<A>> ComparableCheckBuilder<A> is(A a2) {
        return new ComparableCheckBuilder<>(a2);
    }

    public static <A extends Comparable<A>> Predicate<A> le(final A a2) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.b
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ComparableUtils.is((Comparable) obj).lessThanOrEqualTo(a2);
            }
        };
    }

    public static <A extends Comparable<A>> Predicate<A> lt(final A a2) {
        return new Predicate() { // from class: org.apache.commons.lang3.compare.c
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ComparableUtils.is((Comparable) obj).lessThan(a2);
            }
        };
    }
}
