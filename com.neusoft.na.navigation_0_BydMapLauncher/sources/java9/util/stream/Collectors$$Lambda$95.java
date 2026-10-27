package java9.util.stream;

import java.util.List;
import java9.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$95 implements BiConsumer {
    private static final Collectors$$Lambda$95 instance = new Collectors$$Lambda$95();

    private Collectors$$Lambda$95() {
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        ((List) obj).add(obj2);
    }
}
