package java9.util.stream;

import java9.util.OptionalDouble;
import java9.util.function.Predicate;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class FindOps$$Lambda$4 implements Predicate {
    private static final FindOps$$Lambda$4 instance = new FindOps$$Lambda$4();

    private FindOps$$Lambda$4() {
    }

    @Override // java9.util.function.Predicate
    public boolean test(Object obj) {
        return ((OptionalDouble) obj).isPresent();
    }
}
