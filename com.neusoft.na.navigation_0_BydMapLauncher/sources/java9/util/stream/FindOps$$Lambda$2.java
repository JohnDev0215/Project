package java9.util.stream;

import java9.util.OptionalInt;
import java9.util.function.Predicate;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class FindOps$$Lambda$2 implements Predicate {
    private static final FindOps$$Lambda$2 instance = new FindOps$$Lambda$2();

    private FindOps$$Lambda$2() {
    }

    @Override // java9.util.function.Predicate
    public boolean test(Object obj) {
        return ((OptionalInt) obj).isPresent();
    }
}
