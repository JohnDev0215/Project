package java9.util.stream;

import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$40 implements Function {
    private static final Collectors$$Lambda$40 instance = new Collectors$$Lambda$40();

    private Collectors$$Lambda$40() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Collectors.lambda$summingDouble$30((double[]) obj);
    }
}
