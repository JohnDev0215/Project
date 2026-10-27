package java9.util.stream;

import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$51 implements BinaryOperator {
    private static final Collectors$$Lambda$51 instance = new Collectors$$Lambda$51();

    private Collectors$$Lambda$51() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$averagingDouble$41((double[]) obj, (double[]) obj2);
    }
}
