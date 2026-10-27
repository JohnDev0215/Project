package kotlin.sequences;

import kotlin.Metadata;
import kotlin.jvm.b.l;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: Add missing generic type declarations: [T] */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u0002H\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "T", "it", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
final class SequencesKt___SequencesKt$onEach$1<T> extends Lambda implements l<T, T> {
    final /* synthetic */ l $action;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SequencesKt___SequencesKt$onEach$1(l lVar) {
        super(1);
        this.$action = lVar;
    }

    @Override // kotlin.jvm.b.l
    public final T invoke(T t) {
        this.$action.invoke(t);
        return t;
    }
}
