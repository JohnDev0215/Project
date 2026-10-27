package java9.util.stream;

import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$52 implements Function {
    private static final Collectors$$Lambda$52 instance = new Collectors$$Lambda$52();

    private Collectors$$Lambda$52() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Collectors.lambda$averagingDouble$42((double[]) obj);
    }
}
