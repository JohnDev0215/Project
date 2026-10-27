package java9.util.stream;

import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$31 implements BinaryOperator {
    private static final Collectors$$Lambda$31 instance = new Collectors$$Lambda$31();

    private Collectors$$Lambda$31() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$summingInt$21((int[]) obj, (int[]) obj2);
    }
}
