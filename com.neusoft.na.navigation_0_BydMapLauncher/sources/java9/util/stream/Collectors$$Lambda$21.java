package java9.util.stream;

import java9.util.StringJoiner;
import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$21 implements BinaryOperator {
    private static final Collectors$$Lambda$21 instance = new Collectors$$Lambda$21();

    private Collectors$$Lambda$21() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return ((StringJoiner) obj).merge((StringJoiner) obj2);
    }
}
