package java9.util.stream;

import java.util.Set;
import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$12 implements BinaryOperator {
    private static final Collectors$$Lambda$12 instance = new Collectors$$Lambda$12();

    private Collectors$$Lambda$12() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$toSet$7((Set) obj, (Set) obj2);
    }
}
