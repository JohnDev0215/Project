package java9.util.stream;

import java9.util.function.BinaryOperator;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$57 implements Supplier {
    private final BinaryOperator arg$1;

    private Collectors$$Lambda$57(BinaryOperator binaryOperator) {
        this.arg$1 = binaryOperator;
    }

    public static Supplier lambdaFactory$(BinaryOperator binaryOperator) {
        return new Collectors$$Lambda$57(binaryOperator);
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return Collectors.lambda$reducing$47(this.arg$1);
    }
}
