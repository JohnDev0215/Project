package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class Consumers {
    public static <T> Consumer<T> andThen(Consumer<? super T> consumer, Consumer<? super T> consumer2) {
        Objects.requireNonNull(consumer);
        Objects.requireNonNull(consumer2);
        return Consumers$$Lambda$1.lambdaFactory$(consumer, consumer2);
    }

    static /* synthetic */ void lambda$andThen$0(Consumer consumer, Consumer consumer2, Object obj) {
        consumer.accept(obj);
        consumer2.accept(obj);
    }

    private Consumers() {
    }
}
