package java9.util.stream;

import java9.util.function.BiConsumer;
import java9.util.function.Predicate;
import java9.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$69 implements BiConsumer {
    private final BiConsumer arg$1;
    private final Predicate arg$2;

    private Collectors$$Lambda$69(BiConsumer biConsumer, Predicate predicate) {
        this.arg$1 = biConsumer;
        this.arg$2 = predicate;
    }

    public static BiConsumer lambdaFactory$(BiConsumer biConsumer, Predicate predicate) {
        return new Collectors$$Lambda$69(biConsumer, predicate);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        Collectors.lambda$partitioningBy$63(this.arg$1, this.arg$2, (Collectors.Partition) obj, obj2);
    }
}
