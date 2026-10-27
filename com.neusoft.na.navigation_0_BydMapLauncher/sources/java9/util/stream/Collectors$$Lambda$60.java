package java9.util.stream;

import java9.util.function.Function;
import java9.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$60 implements Function {
    private static final Collectors$$Lambda$60 instance = new Collectors$$Lambda$60();

    private Collectors$$Lambda$60() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Collectors.lambda$reducing$49((Collectors.C1OptionalBox) obj);
    }
}
