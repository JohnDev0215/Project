package java9.util.stream;

import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$43 implements BinaryOperator {
    private static final Collectors$$Lambda$43 instance = new Collectors$$Lambda$43();

    private Collectors$$Lambda$43() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$averagingInt$33((long[]) obj, (long[]) obj2);
    }
}
