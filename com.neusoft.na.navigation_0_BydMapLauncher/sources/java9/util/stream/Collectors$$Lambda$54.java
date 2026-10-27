package java9.util.stream;

import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$54 implements BinaryOperator {
    private final BinaryOperator arg$1;

    private Collectors$$Lambda$54(BinaryOperator binaryOperator) {
        this.arg$1 = binaryOperator;
    }

    public static BinaryOperator lambdaFactory$(BinaryOperator binaryOperator) {
        return new Collectors$$Lambda$54(binaryOperator);
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$reducing$44(this.arg$1, (Object[]) obj, (Object[]) obj2);
    }
}
