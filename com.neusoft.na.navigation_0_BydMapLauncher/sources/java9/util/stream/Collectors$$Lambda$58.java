package java9.util.stream;

import java9.util.function.BiConsumer;
import java9.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$58 implements BiConsumer {
    private static final Collectors$$Lambda$58 instance = new Collectors$$Lambda$58();

    private Collectors$$Lambda$58() {
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        ((Collectors.C1OptionalBox) obj).accept(obj2);
    }
}
