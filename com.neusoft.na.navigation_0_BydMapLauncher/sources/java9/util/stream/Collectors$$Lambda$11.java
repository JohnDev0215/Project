package java9.util.stream;

import java.util.List;
import java9.util.Lists;
import java9.util.function.Function;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$11 implements Function {
    private static final Collectors$$Lambda$11 instance = new Collectors$$Lambda$11();

    private Collectors$$Lambda$11() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Lists.of(((List) obj).toArray());
    }
}
