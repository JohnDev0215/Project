package java9.util.stream;

import java.util.List;
import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$9 implements BinaryOperator {
    private static final Collectors$$Lambda$9 instance = new Collectors$$Lambda$9();

    private Collectors$$Lambda$9() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$toList$4((List) obj, (List) obj2);
    }
}
