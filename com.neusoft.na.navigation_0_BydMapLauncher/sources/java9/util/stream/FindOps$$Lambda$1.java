package java9.util.stream;

import java9.util.Optional;
import java9.util.function.Predicate;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class FindOps$$Lambda$1 implements Predicate {
    private static final FindOps$$Lambda$1 instance = new FindOps$$Lambda$1();

    private FindOps$$Lambda$1() {
    }

    @Override // java9.util.function.Predicate
    public boolean test(Object obj) {
        return ((Optional) obj).isPresent();
    }
}
