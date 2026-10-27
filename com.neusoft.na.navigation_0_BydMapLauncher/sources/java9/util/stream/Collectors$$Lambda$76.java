package java9.util.stream;

import java9.util.IntSummaryStatistics;
import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$76 implements BinaryOperator {
    private static final Collectors$$Lambda$76 instance = new Collectors$$Lambda$76();

    private Collectors$$Lambda$76() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$summarizingInt$70((IntSummaryStatistics) obj, (IntSummaryStatistics) obj2);
    }
}
