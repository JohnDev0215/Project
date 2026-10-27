package java9.util.stream;

import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$55 implements Function {
    private static final Collectors$$Lambda$55 instance = new Collectors$$Lambda$55();

    private Collectors$$Lambda$55() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Collectors.lambda$reducing$45((Object[]) obj);
    }
}
