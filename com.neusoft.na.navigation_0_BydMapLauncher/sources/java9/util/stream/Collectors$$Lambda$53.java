package java9.util.stream;

import java9.util.function.BiConsumer;
import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$53 implements BiConsumer {
    private final BinaryOperator arg$1;

    private Collectors$$Lambda$53(BinaryOperator binaryOperator) {
        this.arg$1 = binaryOperator;
    }

    public static BiConsumer lambdaFactory$(BinaryOperator binaryOperator) {
        return new Collectors$$Lambda$53(binaryOperator);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        Collectors.lambda$reducing$43(this.arg$1, (Object[]) obj, obj2);
    }
}
