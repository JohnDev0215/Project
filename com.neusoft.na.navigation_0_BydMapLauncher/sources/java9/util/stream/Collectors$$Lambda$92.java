package java9.util.stream;

import java9.util.IntSummaryStatistics;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$92 implements Supplier {
    private static final Collectors$$Lambda$92 instance = new Collectors$$Lambda$92();

    private Collectors$$Lambda$92() {
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return new IntSummaryStatistics();
    }
}
