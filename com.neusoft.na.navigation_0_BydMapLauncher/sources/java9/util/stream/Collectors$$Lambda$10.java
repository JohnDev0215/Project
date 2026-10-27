package java9.util.stream;

import java.util.List;
import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$10 implements BinaryOperator {
    private static final Collectors$$Lambda$10 instance = new Collectors$$Lambda$10();

    private Collectors$$Lambda$10() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$toUnmodifiableList$5((List) obj, (List) obj2);
    }
}
