package java9.util.stream;

import java.util.Map;
import java9.util.function.BinaryOperator;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$5 implements BinaryOperator {
    private static final Collectors$$Lambda$5 instance = new Collectors$$Lambda$5();

    private Collectors$$Lambda$5() {
    }

    @Override // java9.util.function.BiFunction
    public Object apply(Object obj, Object obj2) {
        return Collectors.lambda$uniqKeysMapMerger$1((Map) obj, (Map) obj2);
    }
}
