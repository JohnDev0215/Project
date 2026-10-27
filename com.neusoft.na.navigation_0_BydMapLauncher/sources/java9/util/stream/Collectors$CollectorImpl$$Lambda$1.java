package java9.util.stream;

import java9.util.function.Function;
import java9.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$CollectorImpl$$Lambda$1 implements Function {
    private static final Collectors$CollectorImpl$$Lambda$1 instance = new Collectors$CollectorImpl$$Lambda$1();

    private Collectors$CollectorImpl$$Lambda$1() {
    }

    @Override // java9.util.function.Function
    public Object apply(Object obj) {
        return Collectors.CollectorImpl.lambda$new$0(obj);
    }
}
