package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class IntConsumers {
    public static IntConsumer andThen(IntConsumer intConsumer, IntConsumer intConsumer2) {
        Objects.requireNonNull(intConsumer);
        Objects.requireNonNull(intConsumer2);
        return IntConsumers$$Lambda$1.lambdaFactory$(intConsumer, intConsumer2);
    }

    static /* synthetic */ void lambda$andThen$0(IntConsumer intConsumer, IntConsumer intConsumer2, int i) {
        intConsumer.accept(i);
        intConsumer2.accept(i);
    }

    private IntConsumers() {
    }
}
