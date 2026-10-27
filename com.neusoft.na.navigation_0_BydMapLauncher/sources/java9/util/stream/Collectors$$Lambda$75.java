package java9.util.stream;

import java9.util.IntSummaryStatistics;
import java9.util.function.BiConsumer;
import java9.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$75 implements BiConsumer {
    private final ToIntFunction arg$1;

    private Collectors$$Lambda$75(ToIntFunction toIntFunction) {
        this.arg$1 = toIntFunction;
    }

    public static BiConsumer lambdaFactory$(ToIntFunction toIntFunction) {
        return new Collectors$$Lambda$75(toIntFunction);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        Collectors.lambda$summarizingInt$69(this.arg$1, (IntSummaryStatistics) obj, obj2);
    }
}
