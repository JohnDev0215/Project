package java9.util.stream;

import java.util.Map;
import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$23 implements BinaryOperator {
    private final BinaryOperator arg$1;

    private Collectors$$Lambda$23(BinaryOperator binaryOperator) {
        this.arg$1 = binaryOperator;
    }

    public static BinaryOperator lambdaFactory$(BinaryOperator binaryOperator) {
        return new Collectors$$Lambda$23(binaryOperator);
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$mapMerger$12(this.arg$1, (Map) obj, (Map) obj2);
    }
}
