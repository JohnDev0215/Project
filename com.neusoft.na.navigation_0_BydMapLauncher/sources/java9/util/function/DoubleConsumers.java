package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class DoubleConsumers {
    public static DoubleConsumer andThen(DoubleConsumer doubleConsumer, DoubleConsumer doubleConsumer2) {
        Objects.requireNonNull(doubleConsumer);
        Objects.requireNonNull(doubleConsumer2);
        return DoubleConsumers$$Lambda$1.lambdaFactory$(doubleConsumer, doubleConsumer2);
    }

    static /* synthetic */ void lambda$andThen$0(DoubleConsumer doubleConsumer, DoubleConsumer doubleConsumer2, double d) {
        doubleConsumer.accept(d);
        doubleConsumer2.accept(d);
    }

    private DoubleConsumers() {
    }
}
