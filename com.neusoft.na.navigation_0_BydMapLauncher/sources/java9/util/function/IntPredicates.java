package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class IntPredicates {
    public static IntPredicate and(IntPredicate intPredicate, IntPredicate intPredicate2) {
        Objects.requireNonNull(intPredicate);
        Objects.requireNonNull(intPredicate2);
        return IntPredicates$$Lambda$1.lambdaFactory$(intPredicate, intPredicate2);
    }

    static /* synthetic */ boolean lambda$and$0(IntPredicate intPredicate, IntPredicate intPredicate2, int i) {
        return intPredicate.test(i) && intPredicate2.test(i);
    }

    public static IntPredicate negate(IntPredicate intPredicate) {
        Objects.requireNonNull(intPredicate);
        return IntPredicates$$Lambda$4.lambdaFactory$(intPredicate);
    }

    static /* synthetic */ boolean lambda$negate$1(IntPredicate intPredicate, int i) {
        return !intPredicate.test(i);
    }

    public static IntPredicate or(IntPredicate intPredicate, IntPredicate intPredicate2) {
        Objects.requireNonNull(intPredicate);
        Objects.requireNonNull(intPredicate2);
        return IntPredicates$$Lambda$5.lambdaFactory$(intPredicate, intPredicate2);
    }

    static /* synthetic */ boolean lambda$or$2(IntPredicate intPredicate, IntPredicate intPredicate2, int i) {
        return intPredicate.test(i) || intPredicate2.test(i);
    }

    private IntPredicates() {
    }
}
