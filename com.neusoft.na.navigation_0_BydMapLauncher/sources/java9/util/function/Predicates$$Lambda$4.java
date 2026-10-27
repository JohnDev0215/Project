package java9.util.function;

import java9.util.Objects;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Predicates$$Lambda$4 implements Predicate {
    private static final Predicates$$Lambda$4 instance = new Predicates$$Lambda$4();

    private Predicates$$Lambda$4() {
    }

    @Override // java9.util.function.Predicate
    public boolean test(Object obj) {
        return Objects.isNull(obj);
    }
}
