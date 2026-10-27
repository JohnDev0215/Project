package java9.util.function;

import java.util.Comparator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class BinaryOperators$$Lambda$1 implements BinaryOperator {
    private final Comparator arg$1;

    private BinaryOperators$$Lambda$1(Comparator comparator) {
        this.arg$1 = comparator;
    }

    public static BinaryOperator lambdaFactory$(Comparator comparator) {
        return new BinaryOperators$$Lambda$1(comparator);
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return BinaryOperators.lambda$minBy$0(this.arg$1, obj, obj2);
    }
}
