package java9.util.stream;

import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$39 implements BinaryOperator {
    private static final Collectors$$Lambda$39 instance = new Collectors$$Lambda$39();

    private Collectors$$Lambda$39() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$summingDouble$29((double[]) obj, (double[]) obj2);
    }
}
