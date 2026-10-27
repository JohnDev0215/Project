package java9.util.stream;

import java9.util.StringJoiner;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$22 implements Function {
    private static final Collectors$$Lambda$22 instance = new Collectors$$Lambda$22();

    private Collectors$$Lambda$22() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return ((StringJoiner) obj).toString();
    }
}
