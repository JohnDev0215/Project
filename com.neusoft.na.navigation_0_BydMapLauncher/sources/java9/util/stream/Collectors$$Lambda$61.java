package java9.util.stream;

import java9.util.function.BiConsumer;
import java9.util.function.BinaryOperator;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$61 implements BiConsumer {
    private final BinaryOperator arg$1;
    private final Function arg$2;

    private Collectors$$Lambda$61(BinaryOperator binaryOperator, Function function) {
        this.arg$1 = binaryOperator;
        this.arg$2 = function;
    }

    public static BiConsumer lambdaFactory$(BinaryOperator binaryOperator, Function function) {
        return new Collectors$$Lambda$61(binaryOperator, function);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        Collectors.lambda$reducing$50(this.arg$1, this.arg$2, (Object[]) obj, obj2);
    }
}
