package java9.util.stream;

import java.util.Set;
import java9.util.Sets;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$14 implements Function {
    private static final Collectors$$Lambda$14 instance = new Collectors$$Lambda$14();

    private Collectors$$Lambda$14() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Sets.of(((Set) obj).toArray());
    }
}
