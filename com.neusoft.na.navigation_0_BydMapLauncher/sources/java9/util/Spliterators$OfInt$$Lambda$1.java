package java9.util;

import java9.util.function.Consumer;
import java9.util.function.IntConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Spliterators$OfInt$$Lambda$1 implements IntConsumer {
    private final Consumer arg$1;

    private Spliterators$OfInt$$Lambda$1(Consumer consumer) {
        this.arg$1 = consumer;
    }

    public static IntConsumer lambdaFactory$(Consumer consumer) {
        return new Spliterators$OfInt$$Lambda$1(consumer);
    }

    @Override // java9.util.function.IntConsumer
    public void accept(int i) {
        this.arg$1.accept(Integer.valueOf(i));
    }
}
