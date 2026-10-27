package java9.util.stream;

import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$35 implements BinaryOperator {
    private static final Collectors$$Lambda$35 instance = new Collectors$$Lambda$35();

    private Collectors$$Lambda$35() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$summingLong$25((long[]) obj, (long[]) obj2);
    }
}
