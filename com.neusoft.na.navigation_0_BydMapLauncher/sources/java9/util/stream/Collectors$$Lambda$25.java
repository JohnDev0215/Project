package java9.util.stream;

import java9.util.function.BiConsumer;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$25 implements BiConsumer {
    private final BiConsumer arg$1;
    private final Function arg$2;

    private Collectors$$Lambda$25(BiConsumer biConsumer, Function function) {
        this.arg$1 = biConsumer;
        this.arg$2 = function;
    }

    public static BiConsumer lambdaFactory$(BiConsumer biConsumer, Function function) {
        return new Collectors$$Lambda$25(biConsumer, function);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        Collectors.lambda$mapping$14(this.arg$1, this.arg$2, obj, obj2);
    }
}
