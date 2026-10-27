package java9.util.stream;

import java9.util.function.Predicate;
import java9.util.function.Supplier;
import java9.util.stream.MatchOps;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class MatchOps$$Lambda$1 implements Supplier {
    private final MatchOps.MatchKind arg$1;
    private final Predicate arg$2;

    private MatchOps$$Lambda$1(MatchOps.MatchKind matchKind, Predicate predicate) {
        this.arg$1 = matchKind;
        this.arg$2 = predicate;
    }

    public static Supplier lambdaFactory$(MatchOps.MatchKind matchKind, Predicate predicate) {
        return new MatchOps$$Lambda$1(matchKind, predicate);
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return MatchOps.lambda$makeRef$0(this.arg$1, this.arg$2);
    }
}
