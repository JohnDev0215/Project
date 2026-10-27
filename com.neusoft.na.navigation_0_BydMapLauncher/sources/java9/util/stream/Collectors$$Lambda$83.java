package java9.util.stream;

import java9.util.function.BinaryOperator;
import java9.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$83 implements BinaryOperator {
    private static final Collectors$$Lambda$83 instance = new Collectors$$Lambda$83();

    private Collectors$$Lambda$83() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return ((Collectors.C1PairBox) obj).combine((Collectors.C1PairBox) obj2);
    }
}
