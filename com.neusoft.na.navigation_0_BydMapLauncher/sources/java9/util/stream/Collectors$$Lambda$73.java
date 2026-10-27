package java9.util.stream;

import java.util.Map;
import java9.util.function.BiConsumer;
import java9.util.function.BinaryOperator;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$73 implements BiConsumer {
    private final Function arg$1;
    private final Function arg$2;
    private final BinaryOperator arg$3;

    private Collectors$$Lambda$73(Function function, Function function2, BinaryOperator binaryOperator) {
        this.arg$1 = function;
        this.arg$2 = function2;
        this.arg$3 = binaryOperator;
    }

    public static BiConsumer lambdaFactory$(Function function, Function function2, BinaryOperator binaryOperator) {
        return new Collectors$$Lambda$73(function, function2, binaryOperator);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        Collectors.lambda$toMap$67(this.arg$1, this.arg$2, this.arg$3, (Map) obj, obj2);
    }
}
