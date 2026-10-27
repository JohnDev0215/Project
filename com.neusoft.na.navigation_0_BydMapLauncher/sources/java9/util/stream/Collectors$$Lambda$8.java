package java9.util.stream;

import java.util.Collection;
import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$8 implements BinaryOperator {
    private static final Collectors$$Lambda$8 instance = new Collectors$$Lambda$8();

    private Collectors$$Lambda$8() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$toCollection$3((Collection) obj, (Collection) obj2);
    }
}
