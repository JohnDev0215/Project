package java9.util.stream;

import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$36 implements Function {
    private static final Collectors$$Lambda$36 instance = new Collectors$$Lambda$36();

    private Collectors$$Lambda$36() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Long.valueOf(((long[]) obj)[0]);
    }
}
