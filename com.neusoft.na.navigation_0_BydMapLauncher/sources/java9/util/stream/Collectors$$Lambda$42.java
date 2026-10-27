package java9.util.stream;

import java9.util.function.BiConsumer;
import java9.util.function.ToIntFunction;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$42 implements BiConsumer {
    private final ToIntFunction arg$1;

    private Collectors$$Lambda$42(ToIntFunction toIntFunction) {
        this.arg$1 = toIntFunction;
    }

    public static BiConsumer lambdaFactory$(ToIntFunction toIntFunction) {
        return new Collectors$$Lambda$42(toIntFunction);
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        Collectors.lambda$averagingInt$32(this.arg$1, (long[]) obj, obj2);
    }
}
