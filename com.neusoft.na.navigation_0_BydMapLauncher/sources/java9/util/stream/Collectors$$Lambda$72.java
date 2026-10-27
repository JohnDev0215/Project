package java9.util.stream;

import java9.util.function.Function;
import java9.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$72 implements Function {
    private final Collector arg$1;

    private Collectors$$Lambda$72(Collector collector) {
        this.arg$1 = collector;
    }

    public static Function lambdaFactory$(Collector collector) {
        return new Collectors$$Lambda$72(collector);
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Collectors.lambda$partitioningBy$66(this.arg$1, (Collectors.Partition) obj);
    }
}
