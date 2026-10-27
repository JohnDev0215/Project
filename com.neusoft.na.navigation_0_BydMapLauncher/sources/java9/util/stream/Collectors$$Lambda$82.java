package java9.util.stream;

import java9.util.function.BiConsumer;
import java9.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$82 implements BiConsumer {
    private static final Collectors$$Lambda$82 instance = new Collectors$$Lambda$82();

    private Collectors$$Lambda$82() {
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        ((Collectors.C1PairBox) obj).add(obj2);
    }
}
