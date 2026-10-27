package java9.util.stream;

import java9.util.function.BiConsumer;
import java9.util.function.BiFunction;
import java9.util.function.BinaryOperator;
import java9.util.function.Function;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$81 implements Supplier {
    private final Supplier arg$1;
    private final Supplier arg$2;
    private final BiConsumer arg$3;
    private final BiConsumer arg$4;
    private final BinaryOperator arg$5;
    private final BinaryOperator arg$6;
    private final Function arg$7;
    private final Function arg$8;
    private final BiFunction arg$9;

    private Collectors$$Lambda$81(Supplier supplier, Supplier supplier2, BiConsumer biConsumer, BiConsumer biConsumer2, BinaryOperator binaryOperator, BinaryOperator binaryOperator2, Function function, Function function2, BiFunction biFunction) {
        this.arg$1 = supplier;
        this.arg$2 = supplier2;
        this.arg$3 = biConsumer;
        this.arg$4 = biConsumer2;
        this.arg$5 = binaryOperator;
        this.arg$6 = binaryOperator2;
        this.arg$7 = function;
        this.arg$8 = function2;
        this.arg$9 = biFunction;
    }

    public static Supplier lambdaFactory$(Supplier supplier, Supplier supplier2, BiConsumer biConsumer, BiConsumer biConsumer2, BinaryOperator binaryOperator, BinaryOperator binaryOperator2, Function function, Function function2, BiFunction biFunction) {
        return new Collectors$$Lambda$81(supplier, supplier2, biConsumer, biConsumer2, binaryOperator, binaryOperator2, function, function2, biFunction);
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return Collectors.lambda$teeing0$75(this.arg$1, this.arg$2, this.arg$3, this.arg$4, this.arg$5, this.arg$6, this.arg$7, this.arg$8, this.arg$9);
    }
}
