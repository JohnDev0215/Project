package java9.util;

import java9.util.function.Consumer;
import java9.util.function.LongConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Spliterators$OfLong$$Lambda$1 implements LongConsumer {
    private final Consumer arg$1;

    private Spliterators$OfLong$$Lambda$1(Consumer consumer) {
        this.arg$1 = consumer;
    }

    public static LongConsumer lambdaFactory$(Consumer consumer) {
        return new Spliterators$OfLong$$Lambda$1(consumer);
    }

    @Override // java9.util.function.LongConsumer
    public void accept(long j) {
        this.arg$1.accept(Long.valueOf(j));
    }
}
