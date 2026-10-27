package java9.util.stream;

import java9.util.function.BiConsumer;
import java9.util.function.ToDoubleFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$38 implements BiConsumer {
    private final ToDoubleFunction arg$1;

    private Collectors$$Lambda$38(ToDoubleFunction toDoubleFunction) {
        this.arg$1 = toDoubleFunction;
    }

    public static BiConsumer lambdaFactory$(ToDoubleFunction toDoubleFunction) {
        return new Collectors$$Lambda$38(toDoubleFunction);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        Collectors.lambda$summingDouble$28(this.arg$1, (double[]) obj, obj2);
    }
}
