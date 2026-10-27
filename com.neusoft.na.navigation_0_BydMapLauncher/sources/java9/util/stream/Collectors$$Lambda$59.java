package java9.util.stream;

import java9.util.function.BinaryOperator;
import java9.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$59 implements BinaryOperator {
    private static final Collectors$$Lambda$59 instance = new Collectors$$Lambda$59();

    private Collectors$$Lambda$59() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$reducing$48((Collectors.C1OptionalBox) obj, (Collectors.C1OptionalBox) obj2);
    }
}
