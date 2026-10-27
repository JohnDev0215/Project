package java9.util.stream;

import java.util.Map;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$94 implements Function {
    private static final Collectors$$Lambda$94 instance = new Collectors$$Lambda$94();

    private Collectors$$Lambda$94() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Collectors.lambda$static$0((Map) obj);
    }
}
