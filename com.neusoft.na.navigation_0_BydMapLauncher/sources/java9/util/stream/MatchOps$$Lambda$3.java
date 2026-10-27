package java9.util.stream;

import java9.util.function.LongPredicate;
import java9.util.function.Supplier;
import java9.util.stream.MatchOps;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class MatchOps$$Lambda$3 implements Supplier {
    private final MatchOps.MatchKind arg$1;
    private final LongPredicate arg$2;

    private MatchOps$$Lambda$3(MatchOps.MatchKind matchKind, LongPredicate longPredicate) {
        this.arg$1 = matchKind;
        this.arg$2 = longPredicate;
    }

    public static Supplier lambdaFactory$(MatchOps.MatchKind matchKind, LongPredicate longPredicate) {
        return new MatchOps$$Lambda$3(matchKind, longPredicate);
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return MatchOps.lambda$makeLong$2(this.arg$1, this.arg$2);
    }
}
