package java9.util.stream;

import java9.util.function.BiConsumer;
import java9.util.function.Predicate;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$27 implements BiConsumer {
    private final Predicate arg$1;
    private final BiConsumer arg$2;

    private Collectors$$Lambda$27(Predicate predicate, BiConsumer biConsumer) {
        this.arg$1 = predicate;
        this.arg$2 = biConsumer;
    }

    public static BiConsumer lambdaFactory$(Predicate predicate, BiConsumer biConsumer) {
        return new Collectors$$Lambda$27(predicate, biConsumer);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        Collectors.lambda$filtering$17(this.arg$1, this.arg$2, obj, obj2);
    }
}
