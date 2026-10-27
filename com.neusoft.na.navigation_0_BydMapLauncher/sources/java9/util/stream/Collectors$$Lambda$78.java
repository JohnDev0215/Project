package java9.util.stream;

import java9.util.LongSummaryStatistics;
import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$78 implements BinaryOperator {
    private static final Collectors$$Lambda$78 instance = new Collectors$$Lambda$78();

    private Collectors$$Lambda$78() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$summarizingLong$72((LongSummaryStatistics) obj, (LongSummaryStatistics) obj2);
    }
}
