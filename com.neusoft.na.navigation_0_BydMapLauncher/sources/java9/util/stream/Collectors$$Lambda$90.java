package java9.util.stream;

import java9.util.function.BiConsumer;
import java9.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$90 implements Consumer {
    private final BiConsumer arg$1;
    private final Object arg$2;

    private Collectors$$Lambda$90(BiConsumer biConsumer, Object obj) {
        this.arg$1 = biConsumer;
        this.arg$2 = obj;
    }

    public static Consumer lambdaFactory$(BiConsumer biConsumer, Object obj) {
        return new Collectors$$Lambda$90(biConsumer, obj);
    }

    @Override // java9.util.function.Consumer
    public void accept(Object obj) {
        this.arg$1.accept(this.arg$2, obj);
    }
}
