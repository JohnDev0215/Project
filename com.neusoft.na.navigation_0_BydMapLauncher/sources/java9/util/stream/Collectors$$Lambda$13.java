package java9.util.stream;

import java.util.Set;
import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$13 implements BinaryOperator {
    private static final Collectors$$Lambda$13 instance = new Collectors$$Lambda$13();

    private Collectors$$Lambda$13() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$toUnmodifiableSet$8((Set) obj, (Set) obj2);
    }
}
