package java9.util.stream;

import java9.util.function.BiConsumer;
import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class IntPipeline$$Lambda$12 implements BinaryOperator {
    private final BiConsumer arg$1;

    private IntPipeline$$Lambda$12(BiConsumer biConsumer) {
        this.arg$1 = biConsumer;
    }

    public static BinaryOperator lambdaFactory$(BiConsumer biConsumer) {
        return new IntPipeline$$Lambda$12(biConsumer);
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return IntPipeline.lambda$collect$4(this.arg$1, obj, obj2);
    }
}
