package kotlin.coroutines.experimental;

import kotlin.Metadata;
import kotlin.coroutines.experimental.c;
import kotlin.jvm.b.p;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.q;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lkotlin/coroutines/experimental/CoroutineContext;", "acc", "element", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "invoke"}, k = 3, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
final class CoroutineContext$plus$1 extends Lambda implements p<c, c.a, c> {
    public static final CoroutineContext$plus$1 INSTANCE = new CoroutineContext$plus$1();

    CoroutineContext$plus$1() {
        super(2);
    }

    @Override // kotlin.jvm.b.p
    @NotNull
    public final c invoke(@NotNull c cVar, @NotNull c.a aVar) {
        q.c(cVar, "acc");
        q.c(aVar, "element");
        c cVarB = cVar.b(aVar.getKey());
        if (cVarB == d.f1495b) {
            return aVar;
        }
        b bVar = (b) cVarB.a(b.f1493a);
        if (bVar == null) {
            return new CombinedContext(cVarB, aVar);
        }
        c cVarB2 = cVarB.b(b.f1493a);
        return cVarB2 == d.f1495b ? new CombinedContext(aVar, bVar) : new CombinedContext(new CombinedContext(cVarB2, aVar), bVar);
    }
}
