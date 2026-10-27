package java9.util.stream;

import java9.util.function.BiConsumer;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$26 implements BiConsumer {
    private final Function arg$1;
    private final BiConsumer arg$2;

    private Collectors$$Lambda$26(Function function, BiConsumer biConsumer) {
        this.arg$1 = function;
        this.arg$2 = biConsumer;
    }

    public static BiConsumer lambdaFactory$(Function function, BiConsumer biConsumer) {
        return new Collectors$$Lambda$26(function, biConsumer);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) throws Throwable {
        Collectors.lambda$flatMapping$16(this.arg$1, this.arg$2, obj, obj2);
    }
}
