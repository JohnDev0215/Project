package java9.util.stream;

import java9.util.DoubleSummaryStatistics;
import java9.util.function.BiConsumer;
import java9.util.function.ToDoubleFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$79 implements BiConsumer {
    private final ToDoubleFunction arg$1;

    private Collectors$$Lambda$79(ToDoubleFunction toDoubleFunction) {
        this.arg$1 = toDoubleFunction;
    }

    public static BiConsumer lambdaFactory$(ToDoubleFunction toDoubleFunction) {
        return new Collectors$$Lambda$79(toDoubleFunction);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        Collectors.lambda$summarizingDouble$73(this.arg$1, (DoubleSummaryStatistics) obj, obj2);
    }
}
