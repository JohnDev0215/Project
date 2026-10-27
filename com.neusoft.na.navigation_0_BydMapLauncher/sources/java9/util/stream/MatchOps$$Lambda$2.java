package java9.util.stream;

import java9.util.function.IntPredicate;
import java9.util.function.Supplier;
import java9.util.stream.MatchOps;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class MatchOps$$Lambda$2 implements Supplier {
    private final MatchOps.MatchKind arg$1;
    private final IntPredicate arg$2;

    private MatchOps$$Lambda$2(MatchOps.MatchKind matchKind, IntPredicate intPredicate) {
        this.arg$1 = matchKind;
        this.arg$2 = intPredicate;
    }

    public static Supplier lambdaFactory$(MatchOps.MatchKind matchKind, IntPredicate intPredicate) {
        return new MatchOps$$Lambda$2(matchKind, intPredicate);
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return MatchOps.lambda$makeInt$1(this.arg$1, this.arg$2);
    }
}
