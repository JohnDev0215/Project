package java9.util.stream;

import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$18 implements Function {
    private static final Collectors$$Lambda$18 instance = new Collectors$$Lambda$18();

    private Collectors$$Lambda$18() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return ((StringBuilder) obj).toString();
    }
}
