package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class BiPredicates {
    public static <T, U> BiPredicate<T, U> and(BiPredicate<? super T, ? super U> biPredicate, BiPredicate<? super T, ? super U> biPredicate2) {
        Objects.requireNonNull(biPredicate);
        Objects.requireNonNull(biPredicate2);
        return BiPredicates$$Lambda$1.lambdaFactory$(biPredicate, biPredicate2);
    }

    static /* synthetic */ boolean lambda$and$0(BiPredicate biPredicate, BiPredicate biPredicate2, Object obj, Object obj2) {
        return biPredicate.test(obj, obj2) && biPredicate2.test(obj, obj2);
    }

    public static <T, U> BiPredicate<T, U> negate(BiPredicate<? super T, ? super U> biPredicate) {
        Objects.requireNonNull(biPredicate);
        return BiPredicates$$Lambda$2.lambdaFactory$(biPredicate);
    }

    static /* synthetic */ boolean lambda$negate$1(BiPredicate biPredicate, Object obj, Object obj2) {
        return !biPredicate.test(obj, obj2);
    }

    public static <T, U> BiPredicate<T, U> or(BiPredicate<? super T, ? super U> biPredicate, BiPredicate<? super T, ? super U> biPredicate2) {
        Objects.requireNonNull(biPredicate);
        Objects.requireNonNull(biPredicate2);
        return BiPredicates$$Lambda$3.lambdaFactory$(biPredicate, biPredicate2);
    }

    static /* synthetic */ boolean lambda$or$2(BiPredicate biPredicate, BiPredicate biPredicate2, Object obj, Object obj2) {
        return biPredicate.test(obj, obj2) || biPredicate2.test(obj, obj2);
    }

    private BiPredicates() {
    }
}
