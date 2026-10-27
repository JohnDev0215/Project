package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class DoublePredicates {
    public static DoublePredicate and(DoublePredicate doublePredicate, DoublePredicate doublePredicate2) {
        Objects.requireNonNull(doublePredicate);
        Objects.requireNonNull(doublePredicate2);
        return DoublePredicates$$Lambda$1.lambdaFactory$(doublePredicate, doublePredicate2);
    }

    static /* synthetic */ boolean lambda$and$0(DoublePredicate doublePredicate, DoublePredicate doublePredicate2, double d) {
        return doublePredicate.test(d) && doublePredicate2.test(d);
    }

    public static DoublePredicate negate(DoublePredicate doublePredicate) {
        Objects.requireNonNull(doublePredicate);
        return DoublePredicates$$Lambda$2.lambdaFactory$(doublePredicate);
    }

    static /* synthetic */ boolean lambda$negate$1(DoublePredicate doublePredicate, double d) {
        return !doublePredicate.test(d);
    }

    public static DoublePredicate or(DoublePredicate doublePredicate, DoublePredicate doublePredicate2) {
        Objects.requireNonNull(doublePredicate);
        Objects.requireNonNull(doublePredicate2);
        return DoublePredicates$$Lambda$3.lambdaFactory$(doublePredicate, doublePredicate2);
    }

    static /* synthetic */ boolean lambda$or$2(DoublePredicate doublePredicate, DoublePredicate doublePredicate2, double d) {
        return doublePredicate.test(d) || doublePredicate2.test(d);
    }

    private DoublePredicates() {
    }
}
