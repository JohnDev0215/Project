package java9.util.stream;

import java9.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$16 implements BiConsumer {
    private static final Collectors$$Lambda$16 instance = new Collectors$$Lambda$16();

    private Collectors$$Lambda$16() {
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        ((StringBuilder) obj).append((CharSequence) obj2);
    }
}
