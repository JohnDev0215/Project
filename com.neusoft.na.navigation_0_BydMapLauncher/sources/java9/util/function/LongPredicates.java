package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class LongPredicates {
    public static LongPredicate and(LongPredicate longPredicate, LongPredicate longPredicate2) {
        Objects.requireNonNull(longPredicate);
        Objects.requireNonNull(longPredicate2);
        return LongPredicates$$Lambda$1.lambdaFactory$(longPredicate, longPredicate2);
    }

    static /* synthetic */ boolean lambda$and$0(LongPredicate longPredicate, LongPredicate longPredicate2, long j) {
        return longPredicate.test(j) && longPredicate2.test(j);
    }

    public static LongPredicate negate(LongPredicate longPredicate) {
        Objects.requireNonNull(longPredicate);
        return LongPredicates$$Lambda$2.lambdaFactory$(longPredicate);
    }

    static /* synthetic */ boolean lambda$negate$1(LongPredicate longPredicate, long j) {
        return !longPredicate.test(j);
    }

    public static LongPredicate or(LongPredicate longPredicate, LongPredicate longPredicate2) {
        Objects.requireNonNull(longPredicate);
        Objects.requireNonNull(longPredicate2);
        return LongPredicates$$Lambda$3.lambdaFactory$(longPredicate, longPredicate2);
    }

    static /* synthetic */ boolean lambda$or$2(LongPredicate longPredicate, LongPredicate longPredicate2, long j) {
        return longPredicate.test(j) || longPredicate2.test(j);
    }

    private LongPredicates() {
    }
}
