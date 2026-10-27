package java9.util.stream;

import java9.util.function.Function;
import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$89 implements Function {
    private final Supplier arg$1;

    private Collectors$$Lambda$89(Supplier supplier) {
        this.arg$1 = supplier;
    }

    public static Function lambdaFactory$(Supplier supplier) {
        return new Collectors$$Lambda$89(supplier);
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Collectors.lambda$null$53(this.arg$1, obj);
    }
}
