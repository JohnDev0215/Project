package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
public final class BiConsumers {
    public static <T, U> BiConsumer<T, U> andThen(BiConsumer<? super T, ? super U> biConsumer, BiConsumer<? super T, ? super U> biConsumer2) {
        Objects.requireNonNull(biConsumer);
        Objects.requireNonNull(biConsumer2);
        return BiConsumers$$Lambda$1.lambdaFactory$(biConsumer, biConsumer2);
    }

    static /* synthetic */ void lambda$andThen$0(BiConsumer biConsumer, BiConsumer biConsumer2, Object obj, Object obj2) {
        biConsumer.accept(obj, obj2);
        biConsumer2.accept(obj, obj2);
    }

    private BiConsumers() {
    }
}
