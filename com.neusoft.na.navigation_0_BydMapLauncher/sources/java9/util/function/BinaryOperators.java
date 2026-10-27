package java9.util.function;

import java.util.Comparator;
import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class BinaryOperators {
    public static <T> BinaryOperator<T> minBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return BinaryOperators$$Lambda$1.lambdaFactory$(comparator);
    }

    static /* synthetic */ Object lambda$minBy$0(Comparator comparator, Object obj, Object obj2) {
        return comparator.compare(obj, obj2) <= 0 ? obj : obj2;
    }

    public static <T> BinaryOperator<T> maxBy(Comparator<? super T> comparator) {
        Objects.requireNonNull(comparator);
        return BinaryOperators$$Lambda$2.lambdaFactory$(comparator);
    }

    static /* synthetic */ Object lambda$maxBy$1(Comparator comparator, Object obj, Object obj2) {
        return comparator.compare(obj, obj2) >= 0 ? obj : obj2;
    }

    private BinaryOperators() {
    }
}
