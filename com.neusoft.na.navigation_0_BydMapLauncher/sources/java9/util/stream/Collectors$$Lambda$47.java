package java9.util.stream;

import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$47 implements BinaryOperator {
    private static final Collectors$$Lambda$47 instance = new Collectors$$Lambda$47();

    private Collectors$$Lambda$47() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$averagingLong$37((long[]) obj, (long[]) obj2);
    }
}
