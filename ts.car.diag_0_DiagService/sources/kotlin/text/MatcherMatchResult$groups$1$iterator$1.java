package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.internal.Lambda;
import kotlin.text.g;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lkotlin/text/MatchGroup;", "it", "", "invoke"}, k = 3, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
final class MatcherMatchResult$groups$1$iterator$1 extends Lambda implements kotlin.jvm.b.l<Integer, e> {
    final /* synthetic */ g.a this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MatcherMatchResult$groups$1$iterator$1(g.a aVar) {
        super(1);
        this.this$0 = aVar;
    }

    @Override // kotlin.jvm.b.l
    public /* bridge */ /* synthetic */ e invoke(Integer num) {
        return invoke(num.intValue());
    }

    @Nullable
    public final e invoke(int i) {
        return this.this$0.b(i);
    }
}
