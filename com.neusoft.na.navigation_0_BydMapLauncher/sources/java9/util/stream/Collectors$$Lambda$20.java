package java9.util.stream;

import java9.util.StringJoiner;
import java9.util.function.BiConsumer;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$20 implements BiConsumer {
    private static final Collectors$$Lambda$20 instance = new Collectors$$Lambda$20();

    private Collectors$$Lambda$20() {
    }

    @Override // java9.util.function.BiConsumer
    public void accept(Object obj, Object obj2) {
        ((StringJoiner) obj).add((CharSequence) obj2);
    }
}
