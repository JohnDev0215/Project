package java9.util.stream;

import java.util.concurrent.ConcurrentMap;
import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$24 implements BinaryOperator {
    private final BinaryOperator arg$1;

    private Collectors$$Lambda$24(BinaryOperator binaryOperator) {
        this.arg$1 = binaryOperator;
    }

    public static BinaryOperator lambdaFactory$(BinaryOperator binaryOperator) {
        return new Collectors$$Lambda$24(binaryOperator);
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$mapMergerConcurrent$13(this.arg$1, (ConcurrentMap) obj, (ConcurrentMap) obj2);
    }
}
