package java9.util.stream;

import java9.util.DoubleSummaryStatistics;
import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$80 implements BinaryOperator {
    private static final Collectors$$Lambda$80 instance = new Collectors$$Lambda$80();

    private Collectors$$Lambda$80() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$summarizingDouble$74((DoubleSummaryStatistics) obj, (DoubleSummaryStatistics) obj2);
    }
}
