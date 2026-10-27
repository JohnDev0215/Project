package java9.util.stream;

import java9.util.function.Function;
import java9.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$84 implements Function {
    private static final Collectors$$Lambda$84 instance = new Collectors$$Lambda$84();

    private Collectors$$Lambda$84() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return ((Collectors.C1PairBox) obj).get();
    }
}
