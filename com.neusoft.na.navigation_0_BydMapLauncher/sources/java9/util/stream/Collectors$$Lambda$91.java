package java9.util.stream;

import java9.util.DoubleSummaryStatistics;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$91 implements Supplier {
    private static final Collectors$$Lambda$91 instance = new Collectors$$Lambda$91();

    private Collectors$$Lambda$91() {
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return new DoubleSummaryStatistics();
    }
}
