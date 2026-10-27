package kotlin.coroutines.experimental.jvm.internal;

import kotlin.Metadata;
import kotlin.coroutines.experimental.b;
import kotlin.coroutines.experimental.c;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.q;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001d\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lkotlin/coroutines/experimental/c;", "context", "Lkotlin/coroutines/experimental/a;", "continuation", "a", "(Lkotlin/coroutines/experimental/c;Lkotlin/coroutines/experimental/a;)Lkotlin/coroutines/experimental/a;", "kotlin-stdlib-coroutines"}, k = 2, mv = {1, 1, 15}, pn = "", xi = 0, xs = "")
@JvmName(name = "CoroutineIntrinsics")
public final class a {
    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <T> kotlin.coroutines.experimental.a<T> a(@NotNull c cVar, @NotNull kotlin.coroutines.experimental.a<? super T> aVar) {
        kotlin.coroutines.experimental.a<T> aVarC;
        q.c(cVar, "context");
        q.c(aVar, "continuation");
        b bVar = (b) cVar.a(b.f1493a);
        return (bVar == null || (aVarC = bVar.c(aVar)) == null) ? aVar : aVarC;
    }
}
