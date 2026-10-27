package java9.util.stream;

import java.util.Set;
import java9.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$96 implements BiConsumer {
    private static final Collectors$$Lambda$96 instance = new Collectors$$Lambda$96();

    private Collectors$$Lambda$96() {
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        ((Set) obj).add(obj2);
    }
}
