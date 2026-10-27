package java9.util.stream;

import java9.util.function.DoublePredicate;
import java9.util.function.Supplier;
import java9.util.stream.MatchOps;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class MatchOps$$Lambda$4 implements Supplier {
    private final MatchOps.MatchKind arg$1;
    private final DoublePredicate arg$2;

    private MatchOps$$Lambda$4(MatchOps.MatchKind matchKind, DoublePredicate doublePredicate) {
        this.arg$1 = matchKind;
        this.arg$2 = doublePredicate;
    }

    public static Supplier lambdaFactory$(MatchOps.MatchKind matchKind, DoublePredicate doublePredicate) {
        return new MatchOps$$Lambda$4(matchKind, doublePredicate);
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return MatchOps.lambda$makeDouble$3(this.arg$1, this.arg$2);
    }
}
