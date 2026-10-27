package java9.util.stream;

import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$32 implements Function {
    private static final Collectors$$Lambda$32 instance = new Collectors$$Lambda$32();

    private Collectors$$Lambda$32() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Integer.valueOf(((int[]) obj)[0]);
    }
}
