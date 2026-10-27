package java9.util.stream;

import java.util.Collection;
import java9.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$7 implements BiConsumer {
    private static final Collectors$$Lambda$7 instance = new Collectors$$Lambda$7();

    private Collectors$$Lambda$7() {
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        ((Collection) obj).add(obj2);
    }
}
