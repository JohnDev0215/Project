package java9.util.stream;

import java9.util.function.BinaryOperator;
import java9.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$70 implements BinaryOperator {
    private final BinaryOperator arg$1;

    private Collectors$$Lambda$70(BinaryOperator binaryOperator) {
        this.arg$1 = binaryOperator;
    }

    public static BinaryOperator lambdaFactory$(BinaryOperator binaryOperator) {
        return new Collectors$$Lambda$70(binaryOperator);
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$partitioningBy$64(this.arg$1, (Collectors.Partition) obj, (Collectors.Partition) obj2);
    }
}
