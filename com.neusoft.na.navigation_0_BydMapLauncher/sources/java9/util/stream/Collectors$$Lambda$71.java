package java9.util.stream;

import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$71 implements Supplier {
    private final Collector arg$1;

    private Collectors$$Lambda$71(Collector collector) {
        this.arg$1 = collector;
    }

    public static Supplier lambdaFactory$(Collector collector) {
        return new Collectors$$Lambda$71(collector);
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return Collectors.lambda$partitioningBy$65(this.arg$1);
    }
}
