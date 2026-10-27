package java9.util;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java9.lang.Integers;
import java9.lang.Longs;
import java9.util.function.Function;
import java9.util.function.ToDoubleFunction;
import java9.util.function.ToIntFunction;
import java9.util.function.ToLongFunction;

/* JADX INFO: loaded from: classes3.dex */
public final class Comparators {

    private enum NaturalOrderComparator implements Comparator<Comparable<Object>> {
        INSTANCE;

        @Override // java.util.Comparator
        public int compare(Comparable<Object> comparable, Comparable<Object> comparable2) {
            return comparable.compareTo(comparable2);
        }

        @Override // java.util.Comparator
        public Comparator<Comparable<Object>> reversed() {
            return Comparators.reverseOrder();
        }
    }

    private static final class NullComparator<T> implements Comparator<T>, Serializable {
        private static final long serialVersionUID = -7569533591570686392L;
        private final boolean nullFirst;
        private final Comparator<T> real;

        /* JADX WARN: Multi-variable type inference failed */
        NullComparator(boolean z, Comparator<? super T> comparator) {
            this.nullFirst = z;
            this.real = comparator;
        }

        @Override // java.util.Comparator
        public int compare(T t, T t2) {
            if (t == null) {
                if (t2 == null) {
                    return 0;
                }
                return this.nullFirst ? -1 : 1;
            }
            if (t2 == null) {
                return this.nullFirst ? 1 : -1;
            }
            Comparator<T> comparator = this.real;
            if (comparator == null) {
                return 0;
            }
            return comparator.compare(t, t2);
        }

        @Override // java.util.Comparator
        public Comparator<T> thenComparing(Comparator<? super T> comparator) {
            Objects.requireNonNull(comparator);
            boolean z = this.nullFirst;
            Comparator<T> comparator2 = this.real;
            if (comparator2 != null) {
                comparator = Comparators.thenComparing(comparator2, comparator);
            }
            return new NullComparator(z, comparator);
        }

        @Override // java.util.Comparator
        public Comparator<T> reversed() {
            boolean z = !this.nullFirst;
            Comparator<T> comparator = this.real;
            return new NullComparator(z, comparator == null ? null : Collections.reverseOrder(comparator));
        }
    }

    public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
        return Collections.reverseOrder();
    }

    public static <T extends Comparable<? super T>> Comparator<T> naturalOrder() {
        return NaturalOrderComparator.INSTANCE;
    }

    public static <T, U> Comparator<T> comparing(Function<? super T, ? extends U> function, Comparator<? super U> comparator) {
        Objects.requireNonNull(function);
        Objects.requireNonNull(comparator);
        return (Comparator) ((Serializable) Comparators$$Lambda$1.lambdaFactory$(comparator, function));
    }

    static /* synthetic */ int lambda$comparing$b1478148$1(Comparator comparator, Function function, Object obj, Object obj2) {
        return comparator.compare(function.apply(obj), function.apply(obj2));
    }

    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> function) {
        Objects.requireNonNull(function);
        return (Comparator) ((Serializable) Comparators$$Lambda$2.lambdaFactory$(function));
    }

    static /* synthetic */ int lambda$comparing$628f93f7$1(Function function, Object obj, Object obj2) {
        return ((Comparable) function.apply(obj)).compareTo(function.apply(obj2));
    }

    public static <T> Comparator<T> comparingInt(ToIntFunction<? super T> toIntFunction) {
        Objects.requireNonNull(toIntFunction);
        return (Comparator) ((Serializable) Comparators$$Lambda$3.lambdaFactory$(toIntFunction));
    }

    static /* synthetic */ int lambda$comparingInt$134fc06a$1(ToIntFunction toIntFunction, Object obj, Object obj2) {
        return Integers.compare(toIntFunction.applyAsInt(obj), toIntFunction.applyAsInt(obj2));
    }

    public static <T> Comparator<T> comparingLong(ToLongFunction<? super T> toLongFunction) {
        Objects.requireNonNull(toLongFunction);
        return (Comparator) ((Serializable) Comparators$$Lambda$4.lambdaFactory$(toLongFunction));
    }

    static /* synthetic */ int lambda$comparingLong$c3a37e6$1(ToLongFunction toLongFunction, Object obj, Object obj2) {
        return Longs.compare(toLongFunction.applyAsLong(obj), toLongFunction.applyAsLong(obj2));
    }

    public static <T> Comparator<T> comparingDouble(ToDoubleFunction<? super T> toDoubleFunction) {
        Objects.requireNonNull(toDoubleFunction);
        return (Comparator) ((Serializable) Comparators$$Lambda$5.lambdaFactory$(toDoubleFunction));
    }

    static /* synthetic */ int lambda$comparingDouble$57709dbc$1(ToDoubleFunction toDoubleFunction, Object obj, Object obj2) {
        return Double.compare(toDoubleFunction.applyAsDouble(obj), toDoubleFunction.applyAsDouble(obj2));
    }

    public static <T> Comparator<T> thenComparing(Comparator<? super T> comparator, Comparator<? super T> comparator2) {
        Objects.requireNonNull(comparator);
        Objects.requireNonNull(comparator2);
        if (comparator instanceof NullComparator) {
            return ((NullComparator) comparator).thenComparing(comparator2);
        }
        return (Comparator) ((Serializable) Comparators$$Lambda$6.lambdaFactory$(comparator, comparator2));
    }

    static /* synthetic */ int lambda$thenComparing$aafead48$1(Comparator comparator, Comparator comparator2, Object obj, Object obj2) {
        int iCompare = comparator.compare(obj, obj2);
        return iCompare != 0 ? iCompare : comparator2.compare(obj, obj2);
    }

    public static <T, U> Comparator<T> thenComparing(Comparator<? super T> comparator, Function<? super T, ? extends U> function, Comparator<? super U> comparator2) {
        return thenComparing(comparator, comparing(function, comparator2));
    }

    public static <T, U extends Comparable<? super U>> Comparator<T> thenComparing(Comparator<? super T> comparator, Function<? super T, ? extends U> function) {
        return thenComparing(comparator, comparing(function));
    }

    public static <T> Comparator<T> thenComparingInt(Comparator<? super T> comparator, ToIntFunction<? super T> toIntFunction) {
        return thenComparing(comparator, comparingInt(toIntFunction));
    }

    public static <T> Comparator<T> thenComparingLong(Comparator<? super T> comparator, ToLongFunction<? super T> toLongFunction) {
        return thenComparing(comparator, comparingLong(toLongFunction));
    }

    public static <T> Comparator<T> thenComparingDouble(Comparator<? super T> comparator, ToDoubleFunction<? super T> toDoubleFunction) {
        return thenComparing(comparator, comparingDouble(toDoubleFunction));
    }

    public static <T> Comparator<T> reversed(Comparator<T> comparator) {
        if (comparator instanceof NullComparator) {
            return ((NullComparator) comparator).reversed();
        }
        return Collections.reverseOrder(comparator);
    }

    public static <T> Comparator<T> nullsFirst(Comparator<? super T> comparator) {
        return new NullComparator(true, comparator);
    }

    public static <T> Comparator<T> nullsLast(Comparator<? super T> comparator) {
        return new NullComparator(false, comparator);
    }

    private Comparators() {
    }
}
