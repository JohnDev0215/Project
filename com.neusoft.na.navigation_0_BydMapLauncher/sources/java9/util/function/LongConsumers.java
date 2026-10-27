package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class LongConsumers {
    public static LongConsumer andThen(LongConsumer longConsumer, LongConsumer longConsumer2) {
        Objects.requireNonNull(longConsumer);
        Objects.requireNonNull(longConsumer2);
        return LongConsumers$$Lambda$1.lambdaFactory$(longConsumer, longConsumer2);
    }

    static /* synthetic */ void lambda$andThen$0(LongConsumer longConsumer, LongConsumer longConsumer2, long j) {
        longConsumer.accept(j);
        longConsumer2.accept(j);
    }

    private LongConsumers() {
    }
}
