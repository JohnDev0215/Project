package kotlin.sequences;

import kotlin.Metadata;
import kotlin.jvm.b.l;
import kotlin.jvm.internal.q;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001f\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lkotlin/Function0;", "seedFunction", "Lkotlin/Function1;", "nextFunction", "Lkotlin/sequences/b;", "a", "(Lkotlin/jvm/b/a;Lkotlin/jvm/b/l;)Lkotlin/sequences/b;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 15}, pn = "", xi = 0, xs = "kotlin/sequences/d")
public class g extends f {
    @NotNull
    public static <T> b<T> a(@NotNull kotlin.jvm.b.a<? extends T> aVar, @NotNull l<? super T, ? extends T> lVar) {
        q.c(aVar, "seedFunction");
        q.c(lVar, "nextFunction");
        return new a(aVar, lVar);
    }
}
