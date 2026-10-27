package java9.util.stream;

import java9.util.LongSummaryStatistics;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$93 implements Supplier {
    private static final Collectors$$Lambda$93 instance = new Collectors$$Lambda$93();

    private Collectors$$Lambda$93() {
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return new LongSummaryStatistics();
    }
}
