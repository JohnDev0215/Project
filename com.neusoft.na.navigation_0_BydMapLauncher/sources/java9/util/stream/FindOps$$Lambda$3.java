package java9.util.stream;

import java9.util.OptionalLong;
import java9.util.function.Predicate;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class FindOps$$Lambda$3 implements Predicate {
    private static final FindOps$$Lambda$3 instance = new FindOps$$Lambda$3();

    private FindOps$$Lambda$3() {
    }

    @Override // java9.util.function.Predicate
    public boolean test(Object obj) {
        return ((OptionalLong) obj).isPresent();
    }
}
