package java9.util.stream;

import java9.util.function.BiConsumer;
import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class DoublePipeline$$Lambda$14 implements BinaryOperator {
    private final BiConsumer arg$1;

    private DoublePipeline$$Lambda$14(BiConsumer biConsumer) {
        this.arg$1 = biConsumer;
    }

    public static BinaryOperator lambdaFactory$(BiConsumer biConsumer) {
        return new DoublePipeline$$Lambda$14(biConsumer);
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return DoublePipeline.lambda$collect$7(this.arg$1, obj, obj2);
    }
}
