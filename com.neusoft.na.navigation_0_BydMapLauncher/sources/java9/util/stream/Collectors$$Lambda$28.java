package java9.util.stream;

import java9.util.function.ToLongFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$28 implements ToLongFunction {
    private static final Collectors$$Lambda$28 instance = new Collectors$$Lambda$28();

    private Collectors$$Lambda$28() {
    }

    @Override // java9.util.function.ToLongFunction
    public long applyAsLong(Object obj) {
        return Collectors.lambda$counting$18(obj);
    }
}
