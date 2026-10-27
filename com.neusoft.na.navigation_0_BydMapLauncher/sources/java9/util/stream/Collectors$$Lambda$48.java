package java9.util.stream;

import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$48 implements Function {
    private static final Collectors$$Lambda$48 instance = new Collectors$$Lambda$48();

    private Collectors$$Lambda$48() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Collectors.lambda$averagingLong$38((long[]) obj);
    }
}
