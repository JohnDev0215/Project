package java9.util.stream;

import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$44 implements Function {
    private static final Collectors$$Lambda$44 instance = new Collectors$$Lambda$44();

    private Collectors$$Lambda$44() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Collectors.lambda$averagingInt$34((long[]) obj);
    }
}
