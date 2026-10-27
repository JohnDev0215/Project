package java9.util;

import java9.util.function.Consumer;
import java9.util.function.DoubleConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Spliterators$OfDouble$$Lambda$1 implements DoubleConsumer {
    private final Consumer arg$1;

    private Spliterators$OfDouble$$Lambda$1(Consumer consumer) {
        this.arg$1 = consumer;
    }

    public static DoubleConsumer lambdaFactory$(Consumer consumer) {
        return new Spliterators$OfDouble$$Lambda$1(consumer);
    }

    @Override // java9.util.function.DoubleConsumer
    public void accept(double d) {
        this.arg$1.accept(Double.valueOf(d));
    }
}
